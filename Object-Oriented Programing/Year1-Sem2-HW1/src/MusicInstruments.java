import java.util.*;
import java.util.InputMismatchException;

public class MusicInstruments {

	private double price;
	private String company;

	public MusicInstruments(double price, String company) throws InputMismatchException {
		setPrice(price);
		setCompany(company);
	}

	public MusicInstruments(Scanner in) throws InputMismatchException {
		try {
			double price = in.nextDouble();
			String company = in.next();
			setPrice(price);
			setCompany(company);
		} catch (InputMismatchException ex) {
			throw new InputMismatchException("Try again. (" + "Incorrect input: an integer is required)");
		}
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) throws IllegalArgumentException {
		if (price >= 0)
			this.price = price;
		else
			throw new IllegalArgumentException("\n" + "Price must be a positive number!");
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return String.format("%-9s%-9s", getCompany(), getClass().getCanonicalName())
				+ String.format(" |Price: %8.2f", getPrice());
	}

	@Override
	public boolean equals(Object instrument) {
		if ((instrument instanceof MusicInstruments))
			if (this.price == ((MusicInstruments) instrument).getPrice()
					&& this.company.compareTo(((MusicInstruments) instrument).getCompany()) == 0)
				return true;
		return false;
	}

}
