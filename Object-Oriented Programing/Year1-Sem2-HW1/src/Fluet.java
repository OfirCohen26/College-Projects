import java.util.Scanner;

public class Fluet extends WindInstrument {

	private String type1;
	final private String[] flutesType = { "Recorder", "Side", "Bass" };

	public Fluet(int price, String company, String material, String type1) {
		super(price, company, material);
		setType1(type1);
	}

	public Fluet(Scanner in) {
		super(in);
		String type1 = in.next();
		setType1(type1);
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) throws IllegalArgumentException {
		if (type1.equals(flutesType[0]) || type1.equals(flutesType[1]) || type1.equals(flutesType[2]))
			this.type1 = type1;
		else
			throw new IllegalArgumentException("You can not create Flute from this type");
	}

	@Override
	public boolean equals(Object instrument) {
		if (super.equals(instrument) == true)
			if (instrument instanceof Fluet)
				if (this.getType1().compareTo(((Fluet) instrument).getType1()) == 0)
					return true;
		return false;
	}

	@Override
	public String toString() {
		return super.toString() + String.format(" Type: %s", getType1());
	}

}
