
import java.util.InputMismatchException;
import java.util.Scanner;

abstract class MusicalInstrument implements InstrumentFunc {
	private Number price;
	private String brand;

	public MusicalInstrument(String brand, Number price) {
		setBrand(brand);
		setPrice(price);
	}

	public MusicalInstrument(Scanner scanner) {
		String price = "";
		String brand;

		try {
			price = scanner.next();
		} catch (InputMismatchException ex) {
			throw new InputMismatchException("Price not found!");
		}

		setPrice(price);
		scanner.nextLine();
		brand = scanner.nextLine();
		setBrand(brand);
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Number getPrice() {
		return price;
	}

	public void setPrice(String priceString) {
		String arr[] = priceString.split("\\.");
		Number price;
		if (arr.length > 1) {
			price = Double.parseDouble(priceString);
		} else {
			price = Integer.parseInt(priceString);
		}
		setPrice(price);
	}

	public void setPrice(Number price) {
		if (price.doubleValue() > 0)
			this.price = price;
		else
			throw new InputMismatchException("Price must be a positive number!");

	}

	protected boolean isValidType(String[] typeArr, String material) {
		for (int i = 0; i < typeArr.length; i++) {
			if (material.equals(typeArr[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof MusicalInstrument))
			return false;

		MusicalInstrument otherInstrument = (MusicalInstrument) o;

		return getPrice().equals(otherInstrument.getPrice()) && getBrand().equals(otherInstrument.getBrand());
	}

	@Override
	public String toString() {
		return String.format("%-8s %-9s| Price: %7s,", getBrand(), getClass().getCanonicalName(),
				getPrice().toString());
	}

	@Override
	public int compareTo(MusicalInstrument instrument) {
		if (getBrand().equals(instrument.getBrand())) {
			if (getPrice().doubleValue() > instrument.getPrice().doubleValue())
				return 1;
			else if (getPrice().doubleValue() < instrument.getPrice().doubleValue())
				return -1;
			else {
				return 0;
			}
		}
		return getBrand().compareTo(instrument.getBrand());
	}

}
