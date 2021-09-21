import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AfekaInstruments {

	public static void main(String[] args) {
		Scanner consoleScanner = new Scanner(System.in);
		ArrayList<MusicalInstrument> allInstruments = new ArrayList<MusicalInstrument>();
		File file = getInstrumentsFileFromUser(consoleScanner);

		loadInstrumentsFromFile(file, allInstruments);

		if (allInstruments.size() == 0) {
			System.out.println("There are no instruments in the store currently");
			return;
		}

		printInstruments(allInstruments);

		int different = getNumOfDifferentElements(allInstruments);

		System.out.println("\n\nDifferent Instruments: " + different);

		MusicalInstrument mostExpensive = getMostExpensiveInstrument(allInstruments);

		System.out.println("\n\nMost Expensive Instrument:\n" + mostExpensive);

		startInventoryMenu(allInstruments);

		consoleScanner.close();
	}

	public static File getInstrumentsFileFromUser(Scanner consoleScanner) {
		boolean stopLoop = true;
		File file;
		do {
			System.out.println("Please enter instruments file name / path:");
			String filepath = consoleScanner.nextLine();
			file = new File(filepath);
			stopLoop = file.exists() && file.canRead();

			if (!stopLoop)
				System.out.println("\nFile Error! Please try again\n\n");
		} while (!stopLoop);

		return file;
	}

	public static void loadInstrumentsFromFile(File file, ArrayList<MusicalInstrument> allInstruments) {
		Scanner scanner = null;

		try {

			scanner = new Scanner(file);

			addAllInstruments(allInstruments, loadGuitars(scanner));

			addAllInstruments(allInstruments, loadBassGuitars(scanner));

			addAllInstruments(allInstruments, loadFlutes(scanner));

			addAllInstruments(allInstruments, loadSaxophones(scanner));

		} catch (InputMismatchException | IllegalArgumentException ex) {
			System.err.println("\n" + ex.getMessage());
			System.exit(1);
		} catch (FileNotFoundException ex) {
			System.err.println("\nFile Error! File was not found");
			System.exit(2);
		} finally {
			scanner.close();
		}
		System.out.println("\nInstruments loaded from file successfully!\n");

	}

	public static ArrayList<Guitar> loadGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Guitar> guitars = new ArrayList<Guitar>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			guitars.add(new Guitar(scanner));

		return guitars;
	}

	public static ArrayList<Bass> loadBassGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Bass> bassGuitars = new ArrayList<Bass>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			bassGuitars.add(new Bass(scanner));

		return bassGuitars;
	}

	public static ArrayList<Flute> loadFlutes(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Flute> flutes = new ArrayList<Flute>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			flutes.add(new Flute(scanner));

		return flutes;
	}

	public static ArrayList<Saxophone> loadSaxophones(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Saxophone> saxophones = new ArrayList<Saxophone>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			saxophones.add(new Saxophone(scanner));

		return saxophones;
	}

	public static void addAllInstruments(ArrayList<MusicalInstrument> instruments,
			ArrayList<? extends MusicalInstrument> moreInstruments) {
		for (int i = 0; i < moreInstruments.size(); i++) {
			instruments.add(moreInstruments.get(i));
		}
	}

	public static void printInstruments(ArrayList<? extends MusicalInstrument> instruments) {
		for (int i = 0; i < instruments.size(); i++)
			System.out.println(instruments.get(i));
	}

	public static int getNumOfDifferentElements(ArrayList<? extends MusicalInstrument> instruments) {
		int numOfDifferentInstruments;
		ArrayList<MusicalInstrument> differentInstruments = new ArrayList<MusicalInstrument>();
		System.out.println();

		for (int i = 0; i < instruments.size(); i++) {
			if (!differentInstruments.contains((instruments.get(i)))) {
				differentInstruments.add(instruments.get(i));
			}
		}

		if (differentInstruments.size() == 1)
			numOfDifferentInstruments = 0;

		else
			numOfDifferentInstruments = differentInstruments.size();

		return numOfDifferentInstruments;
	}

	public static MusicalInstrument getMostExpensiveInstrument(ArrayList<MusicalInstrument> instruments) {
		Number maxPrice = 0;
		MusicalInstrument mostExpensive = (MusicalInstrument) instruments.get(0);

		for (int i = 0; i < instruments.size(); i++) {
			MusicalInstrument temp = (MusicalInstrument) instruments.get(i);

			if (temp.getPrice().doubleValue() > maxPrice.doubleValue()) {
				maxPrice = temp.getPrice();
				mostExpensive = temp;
			}
		}

		return mostExpensive;
	}

	public static void startInventoryMenu(ArrayList<? extends MusicalInstrument> instrumentsFromUser) {
		int option = 0;
		AfekaInventory<MusicalInstrument> inventory = new AfekaInventory<MusicalInstrument>();
		Scanner consoleScanner = new Scanner(System.in);
		do {
			System.out.println(" \n-------------------------------------------------------------------------------\n"
					+ "AFEKA MUSICAL INSTRUMENT INVENTORY MENU\n"
					+ " -------------------------------------------------------------------------------\n"
					+ "1. Copy All String Instruments To Inventory\n2. Copy All Wind Instruments To Inventory\n"
					+ "3. Sort Instruments By Brand And Price\n4. Search Instruments By Brand and Price\n5. Delete Instruments \n"
					+ "6. Delete all Instruments\n7. Print Inventory Instruments ");
			System.out.println("Choose your option or any other key to EXIT\n");
			System.out.print("Your Option: ");
			option = consoleScanner.nextInt();
			switch (option) {
			case 1:
				inventory.addAllStringInstruments(instrumentsFromUser, inventory.getInventoryInstru());
				System.out.println("\nAll String Instruments Added Successfully!");
				break;
			case 2:
				inventory.addAllWindInstruments(instrumentsFromUser, inventory.getInventoryInstru());
				System.out.println("\nAll Wind Instruments Added Successfully!");
				break;
			case 3:
				inventory.SortByBrandAndPrice(inventory.getInventoryInstru());
				System.out.println("\nInstruments Sorted Successfully!");
				break;
			case 4:
				System.out.println("\nSEARCH INSTRUMENT:");
				MusicalInstrument instrumentToFind = searchInstrument(inventory, consoleScanner);
				if (instrumentToFind == null) {
					System.out.println("Instrument Not found!");
				} else {
					System.out.println("Result:\n" + "     " + instrumentToFind);
				}
				break;
			case 5:
				System.out.println("\nDELETE INSTRUMENT:");
				String removeOrNot;
				MusicalInstrument instrumentToRemove = searchInstrument(inventory, consoleScanner);
				System.out.println("Result:\n" + "     " + instrumentToRemove);
				if (instrumentToRemove != null) {
					// ask if to remove
					System.out.print("Are you Sure? (Y/N) ");
					removeOrNot = consoleScanner.next();
					if (removeOrNot.compareTo("y") == 0) {
						inventory.removeInstrument(inventory.getInventoryInstru(), instrumentToRemove);
						System.out.println("Instrument Deleted Successfully!");
					}
				} else
					System.out.println("The action can not be performed");
				break;
			case 6:
				System.out.println("\nDELETE ALL INSTRUMENTS:");
				System.out.print("Are you sure? (Y/N) ");
				String ifToRemoveAll = consoleScanner.next();
				if (ifToRemoveAll.compareTo("y") == 0) {
					inventory.removeAll(inventory.getInventoryInstru());
					System.out.println("\nAll Instruments Deleted Successfully!");
				} else {
					System.out.println("\nAll Instruments have not been Deleted");
				}
				break;
			case 7:
				System.out.println("\n-------------------------------------------------------------------------------\n"
						+ "AFEKA MUSICAL INSTRUMENT INVENTORY\n"
						+ " -------------------------------------------------------------------------------");
				if (inventory.getInventoryInstru().isEmpty())
					System.out.println("There Is No Instruments To show");
				else {
					printInstruments(inventory.getInventoryInstru());
				}
				System.out.print(String.format("\nTotal Price: %.2f", inventory.getTotalPrice()));
				System.out.print("     Sorted: " + inventory.isSorted());
				System.out.println("");
				break;
			}
		} while (1 <= option && option <= 7);

		System.out.print("\nFinished!");
		consoleScanner.close();
	}

	public static MusicalInstrument searchInstrument(AfekaInventory<MusicalInstrument> inventory,
			Scanner consoleScanner) {
		try {
			// Handle when didn't find
			String brand;
			String priceString;
			Number price;
			System.out.print("Brand:");
			brand = consoleScanner.next();
			System.out.print("Price:");
			priceString = consoleScanner.next();
			String arr[] = priceString.split("\\.");
			if (arr.length > 1) {
				price = Double.parseDouble(priceString);
			} else {
				price = Integer.parseInt(priceString);
			}
			// the index of the required instrument
			int index = inventory.binnarySreachByBrandAndPrice(inventory.getInventoryInstru(), brand, price);
			// the required instrument
			if (index >= 0) {
				MusicalInstrument instrument = inventory.getInventoryInstru().get(index);
				return instrument;
			}
			// if index states that no element was found, return instrument as
			// null
			return null;
		} catch (NumberFormatException ex) {
			return null;
		}
	}
}
