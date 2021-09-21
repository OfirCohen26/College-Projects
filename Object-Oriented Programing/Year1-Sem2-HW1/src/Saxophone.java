import java.util.Scanner;

public class Saxophone extends WindInstrument {

	public Saxophone(int price, String company, String material) {
		super(price, company, material);
	}

	public Saxophone(Scanner in) {
		super(in);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public boolean equals(Object instrument) {
		if (super.equals(instrument) == true)
			if ((instrument instanceof Saxophone))
				return true;
		return false;
	}

}
