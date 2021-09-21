import java.util.ArrayList;


public interface ManagmentInventory<T> {

	void addAllStringInstruments(ArrayList<? extends MusicalInstrument> instrumentsOut,
			ArrayList<? super MusicalInstrument> instrumentsIn);

	void addAllWindInstruments(ArrayList<? extends MusicalInstrument> instrumentsOut,
			ArrayList<? super MusicalInstrument> instrumentsIn);

	void SortByBrandAndPrice(ArrayList<T> instruments1);

	int binnarySreachByBrandAndPrice(ArrayList<T> instruments1, String brand, Number price);

	void addInstrument(ArrayList<T> instruments1, T instrument);

	boolean removeInstrument(ArrayList<T> instruments1, T instrument);

	boolean removeAll(ArrayList<T> instruments1);

}
