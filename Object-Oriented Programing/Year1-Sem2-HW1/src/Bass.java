import java.util.InputMismatchException;
import java.util.Scanner;

public class Bass extends StringInstruments {
	private boolean fretless;

	public Bass(int price, String company, int numOfStrings, boolean fretless) {
		super(price, company, numOfStrings);
		setFretless(fretless);

	}

	public Bass(Scanner in) throws InputMismatchException {
		super(in);
		try {
			boolean fretless = in.nextBoolean();
			setFretless(fretless);
		} catch (InputMismatchException ex) {
			throw new InputMismatchException("\n" + "Whether a bass is fretless or not is boolean, "
					+ "any other string than \"True\" or" + "\n" + "\"False\" is not acceptable");
		}
	}

	public boolean isFretless() {
		return fretless;
	}

	public void setFretless(boolean fretless) {
		this.fretless = fretless;
	}

	@Override
	public String toString() {
		return super.toString() + "|" + " Fretless: " + ((isFretless() == true) ? "Yes" : "No");
	}

	@Override
	public boolean equals(Object instrument) {
		if (super.equals(instrument) == true)
			if (instrument instanceof Bass)
				if (this.fretless == (((Bass) instrument).isFretless()))
					return true;
		return false;
	}

}
