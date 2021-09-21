import java.util.Scanner;

public class Guitar extends StringInstruments {

	private String type;
	final private String[] guitarsType = { "Acoustic", "Electric", "Classic" };

	public Guitar(int price, String company, int numOfStrings, String type) {
		super(price, company, numOfStrings);
		setType(type);
	}

	public Guitar(int price, String company, String type) {
		super(price, company);
		super.setNumOfStrings(6);
		setType(type);
	}

	public Guitar(Scanner in) {
		super(in);
		String type = in.next();
		setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) throws IllegalArgumentException {
		if (type.equals(guitarsType[0]) || type.equals(guitarsType[2]) || type.equals(guitarsType[1])) {
			if ((type.equals(guitarsType[0]) || type.equals(guitarsType[2])) && (super.getNumOfStrings() != 6))
				throw new IllegalArgumentException(
						"\n" + type + " Guitars have 6 strings, not " + super.getNumOfStrings());
			if (type.equals(guitarsType[1]) && !(super.getNumOfStrings() >= 6 && super.getNumOfStrings() <= 8))
				throw new IllegalArgumentException("Electric Guitar's number of strings is a number between 6 and 8");
			this.type = type;
		} else
			throw new IllegalArgumentException("You can not create Guitar from this type");
	}

	@Override
	public String toString() {
		return super.toString() + "|" + String.format(" Type: %s", getType());
	}

	@Override
	public boolean equals(Object instrument) {
		if (super.equals(instrument) == true)
			if (instrument instanceof Guitar)
				if (this.type.compareTo(((Guitar) instrument).getType()) == 0)
					return true;
		return false;
	}

}
