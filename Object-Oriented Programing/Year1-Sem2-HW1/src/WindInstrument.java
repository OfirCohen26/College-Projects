import java.util.Scanner;

public class WindInstrument extends MusicInstruments {

	private String material;

	public WindInstrument(int price, String company, String material) {
		super(price, company);
		setMaterial(material);
	}

	public WindInstrument(Scanner in) {
		super(in);
		String material = in.next();
		setMaterial(material);
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) throws IllegalArgumentException {
		if (this instanceof Saxophone)
			if (!(material.equals("Metal")))
				throw new IllegalArgumentException("You can only create Saxophone from Metal");
			else
				this.material = material;
		if (this instanceof Fluet)
			if (!(material.equals("Metal") || material.equals("Wood") || material.equals("Plastic")))
				throw new IllegalArgumentException("You can not create Fluet from this material");
			else
				this.material = material;
	}

	@Override
	public String toString() {
		return super.toString() + String.format(", Made of:%12s", getMaterial()) + "|";
	}

	@Override
	public boolean equals(Object instrument) {
		if (super.equals(instrument) == true)
			if (instrument instanceof WindInstrument)
				if (this.material.compareTo(((WindInstrument) instrument).getMaterial()) == 0)
					return true;
		return false;
	}
}
