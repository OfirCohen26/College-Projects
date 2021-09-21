import java.util.ArrayList;
import java.util.Collections;


public class AfekaInventory<T extends MusicalInstrument> implements ManagmentInventory<T> {

	private ArrayList<T> inventoryInstru;
	private double totalPrice;
	private boolean isSorted;

	public AfekaInventory() {
		setInventoryInstru(new ArrayList<T>());
	}

	public AfekaInventory(ArrayList<T> instruments) {
		setInventoryInstru(instruments);
	}

	public ArrayList<T> getInventoryInstru() {
		return inventoryInstru;
	}

	private void setInventoryInstru(ArrayList<T> inventoryInstru) {
		this.inventoryInstru = inventoryInstru;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	private void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isSorted() {
		return isSorted;
	}

	private void setSorted(boolean isSorted) {
		this.isSorted = isSorted;
	}

	@Override
	public void addAllStringInstruments(ArrayList<? extends MusicalInstrument> instrumentsOut,
			ArrayList<? super MusicalInstrument> instrumentsIn) {
		for (int i = 0; i < instrumentsOut.size(); i++) {
			if (instrumentsOut.get(i) instanceof StringInstrument) {
				instrumentsIn.add(instrumentsOut.get(i));
			}
		}

		setSorted(false);
		calculateTotalPrice();
	}

	@Override
	public void addAllWindInstruments(ArrayList<? extends MusicalInstrument> instrumentsOut,
			ArrayList<? super MusicalInstrument> instrumentsIn) {
		for (int i = 0; i < instrumentsOut.size(); i++) {
			if (instrumentsOut.get(i) instanceof WindInstrument) {
				instrumentsIn.add(instrumentsOut.get(i));
			}
		}

		setSorted(false);
		calculateTotalPrice();
	}

	@Override
	public void SortByBrandAndPrice(ArrayList<T> instruments1) {
		Collections.sort(instruments1);
		isSorted = true;
	}

	@Override
	public int binnarySreachByBrandAndPrice(ArrayList<T> musicalInstruments, String brand, Number price) {
		if (!isSorted)
			return -1;
		int low = 0;
		int mid;
		int high = musicalInstruments.size() - 1;
		while (high >= low) {
			mid = (low + high) / 2;
			if (musicalInstruments.get(mid).getBrand().compareTo(brand) == 0) {
				if (musicalInstruments.get(mid).getPrice().doubleValue() == price.doubleValue()) {
					return mid;
				} else if (musicalInstruments.get(mid).getPrice().doubleValue() > price.doubleValue()) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			} else if (musicalInstruments.get(mid).getBrand().compareTo(brand) > 0) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -low - 1;

	}

	@Override
	public void addInstrument(ArrayList<T> musicalList, T instrument) {
		musicalList.add(instrument);

		setSorted(false);
		calculateTotalPrice();
	}

	@Override
	public boolean removeInstrument(ArrayList<T> musicalList, T instrument) {
		boolean isFind = musicalList.remove(instrument);
		calculateTotalPrice();
		return isFind;
	}

	@Override
	public boolean removeAll(ArrayList<T> instruments) {
		boolean isRemoved = instruments.removeAll(instruments);
		calculateTotalPrice();
		setSorted(false);
		return isRemoved;
	}

	public double addition(Number num1, Number num2) {
		return num1.doubleValue() + num2.doubleValue();
	}

	private void calculateTotalPrice() {
		Number total = 0;
		for (int i = 0; i < this.inventoryInstru.size(); i++) {
			total = this.addition(total, this.inventoryInstru.get(i).getPrice());
		}
		setTotalPrice(total.doubleValue());
	}

}
