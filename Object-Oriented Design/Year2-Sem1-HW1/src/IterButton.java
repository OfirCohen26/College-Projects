import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeSet;
import sun.reflect.generics.tree.Tree;
//Ofir Cohen

class IterButton extends CommandButton {
	private LinkedHashMap<String, String> map = new LinkedHashMap<>();
	private ListIterator<String> lit;
	private boolean flagFirstClick = false;

	public IterButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText("Iter");
		try {
			lit = listIterator(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Execute() {
		Iterator<String> iter;
		//For the first click
		if (!flagFirstClick) {
			flagFirstClick = true;
			// Copy from file to map
			while (lit.hasNext()) {
				String value = lit.next();
				String key = value.substring(0, CommandButton.RECORD_SIZE - CommandButton.ZIP_SIZE).trim();
				map.put(key, value);
			}
			iter = map.values().iterator();
		} else {
			// From the second click 
			TreeSet<String> tree = new TreeSet<>(new Comparator<String>() {
				@Override
				public int compare(String str1, String str2) {
					int a = str1
							.substring(CommandButton.NAME_SIZE - 1,
									CommandButton.NAME_SIZE + CommandButton.STREET_SIZE - 1)
							.compareTo(str2.substring(CommandButton.NAME_SIZE - 1,
									CommandButton.NAME_SIZE + CommandButton.STREET_SIZE - 1));
					return a == 0 ? 1 : a;
				}
			}); // Copy from map to tree
			tree.addAll(map.values());
			iter = tree.iterator();
		}
		updatefile(lit, iter);
	}

	//UpdateFile
	private void updatefile(ListIterator<String> lit, Iterator<String> iter) {
		// Clear the file
		while (lit.hasPrevious()) {
			lit.previous();
			lit.remove();
		} // Update the file
		while (iter.hasNext()) {
			lit.add(iter.next());
		}
	}

	public ListIterator<String> listIterator(int index) throws IOException, FileNotFoundException {
		return new ListIter(index);
	}

	public ListIterator<String> listIterator() throws IOException, FileNotFoundException {
		return listIterator(0);
	}

	// Class ListIterator implements ListIterator<String>
	private class ListIter implements ListIterator<String> {
		private int current = 0; // Next address to return
		private int last = -1; // Last address that was returned
		private long numRecords; // Number of records

		// Constructor
		ListIter(int index) throws IOException, FileNotFoundException {
			current = index;
			this.numRecords = raf.length() / (2 * RECORD_SIZE);
		}

		@Override
		public void add(String a) {
			if (current < 0 || current > (2 * RECORD_SIZE))
				throw new IndexOutOfBoundsException();
			ArrayList<String> arrayList;
			try {
				arrayList = FromFileToArray();
				arrayList.add(current, a);
				FromArrayToFile(arrayList);
			} catch (IOException e) {
				e.printStackTrace();
			}
			numRecords++;
			current++;
			last = -1;
		}

		@Override
		public void remove() {
			if (last == -1)
				throw new IllegalStateException();
			ArrayList<String> arrayList;
			try {
				arrayList = FromFileToArray();
				arrayList.remove(last);
				FromArrayToFile(arrayList);
			} catch (IOException e) {
				e.printStackTrace();
			}
			numRecords--;
			current = last;
			last = -1;
		}

		@Override
		public boolean hasNext() {
			return current < numRecords;
		}

		@Override
		public boolean hasPrevious() {
			if (current <= 0)
				return false;
			else
				return true;
		}

		@Override
		public String next() {
			String nextRecord = null;
			if (!hasNext())
				throw new NoSuchElementException();
			try {
				raf.seek(current * (RECORD_SIZE * 2));
				nextRecord = FixedLengthStringIO.readFixedLengthString(RECORD_SIZE, raf);
			} catch (IOException e) {
				e.printStackTrace();
			}
			last = current;
			current++;
			return nextRecord;
		}

		@Override
		public int nextIndex() {
			return current;
		}

		@Override
		public String previous() {
			String previousRecord = null;
			if (!hasPrevious())
				throw new NoSuchElementException();
			current--;
			try {
				raf.seek(current * (RECORD_SIZE * 2));
				previousRecord = FixedLengthStringIO.readFixedLengthString(RECORD_SIZE, raf);
			} catch (IOException e) {
				e.printStackTrace();
			}
			last = current;
			return previousRecord;
		}

		@Override
		public int previousIndex() {
			return current - 1;
		}

		@Override
		public void set(String str) {
			if (last == -1)
				throw new IllegalStateException();
			try {
				FixedLengthStringIO.writeFixedLengthString(str, RECORD_SIZE, raf);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Function that copy data from a file to an array
		private ArrayList<String> FromFileToArray() throws IOException {
			raf.seek(0);
			ArrayList<String> arrayList = new ArrayList<String>();
			for (int i = 0; i < raf.length(); i = i + (RECORD_SIZE * 2)) {
				arrayList.add(FixedLengthStringIO.readFixedLengthString(RECORD_SIZE, raf));
			}
			return arrayList;
		}

		// Function that copy data from an array to a file
		private void FromArrayToFile(ArrayList<String> arrayList) throws IOException {
			raf.seek(0);
			raf.setLength(0);
			for (String record : arrayList) {
				FixedLengthStringIO.writeFixedLengthString(record, RECORD_SIZE, raf);
			}
		}
	}
}
