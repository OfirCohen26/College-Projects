import java.util.InputMismatchException;
import java.util.Scanner;

public class StringInstruments extends MusicInstruments {
	private int numOfStrings;

	public StringInstruments(int price, String company, int numOfStrings) {
		super(price, company);
		setNumOfStrings(numOfStrings);
	}

	public StringInstruments(int price, String company) {
		super(price, company);
	}

	public StringInstruments(Scanner in) throws InputMismatchException {
		super(in);
		try {
			int numOfStrings = in.nextInt();
			setNumOfStrings(numOfStrings);
		} catch (InputMismatchException ex) {
			throw new InputMismatchException("Try again. (" + "Incorrect input: an integer is required)");
		}
	}

	public int getNumOfStrings() {
		return numOfStrings;
	}

	public void setNumOfStrings(int numOfStrings) throws IllegalArgumentException {
		if (this instanceof Bass)
			if ((numOfStrings < 4 || numOfStrings > 6))
				throw new IllegalArgumentException("\n" + "Bass number of strings is a number between 4 and 6");
		this.numOfStrings = numOfStrings;
	}

	@Override
	public String toString() {
		return super.toString() + ", Number of strings: " + getNumOfStrings();
	}

	@Override
	public boolean equals(Object instrument) {
		if (super.equals(instrument) == true)
			if (instrument instanceof StringInstruments)
				if (this.numOfStrings == ((StringInstruments) instrument).getNumOfStrings())
					return true;
		return false;
	}
}
