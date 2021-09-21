import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.io.*;

public class AfekaInstruments {

	// Printing method
	public static void printInstruments(ArrayList musicalInstruments) {
		for (int i = 0; i < musicalInstruments.size(); i++) {
			System.out.println(musicalInstruments.get(i).toString());
		}
		System.out.println();
	}

	// Inserting values of one dynamic array to another
	public static void addAllInstruments(ArrayList musicalInstruments, ArrayList instruments) {
		for (int i = 0; i < musicalInstruments.size(); i++) {
			instruments.add(musicalInstruments.get(i));
		}
	}

	// A method that returns the most expensive instrument
	public static MusicInstruments getMostExpensiveInstrument(ArrayList musicalInstruments) {
		MusicInstruments mostExpensInstru = (MusicInstruments) musicalInstruments.get(0);
		for (int i = 1; i < musicalInstruments.size(); i++) {
			if (((MusicInstruments) musicalInstruments.get(i)).getPrice() > mostExpensInstru.getPrice())
				mostExpensInstru = (MusicInstruments) musicalInstruments.get(i);
		}
		return mostExpensInstru;
	}

	// A method that returns the amount of different elements in the array
	public static int getNumOfDifferentElement(ArrayList musicalInstruments) {
		int count = 0;
		for (int i = 0; i < musicalInstruments.size(); i++) {
			int j;
			for (j = 0; j < i; j++)
				if ((musicalInstruments.get(i).equals(musicalInstruments.get(j))))
					break;
			if (i == j)
				count++;
		}
		return count;
	}

	// A method that create object type Guitar
	public static ArrayList createGuitars(Scanner s) {
		int count;
		ArrayList guitars = new ArrayList();
		count = s.nextInt();
		for (int i = 0; i < count; i++) {
			MusicInstruments guitar = new Guitar(s);
			guitars.add(guitar);
		}
		return guitars;
	}

	// A method that create object type Bass
	public static ArrayList createBass(Scanner s) {
		int count;
		ArrayList bass1 = new ArrayList();
		count = s.nextInt();
		for (int i = 0; i < count; i++) {
			MusicInstruments bass = new Bass(s);
			bass1.add(bass);
		}
		return bass1;
	}

	// A method that create object type Fluet
	public static ArrayList createFluet(Scanner s) {
		int count;
		ArrayList flutes = new ArrayList();
		count = s.nextInt();
		for (int i = 0; i < count; i++) {
			MusicInstruments flute1 = new Fluet(s);
			flutes.add(flute1);
		}
		return flutes;
	}

	// A method that create object type Saxophone
	public static ArrayList creatSaxsophone(Scanner s) {
		int count;
		ArrayList Saxsophones = new ArrayList();
		count = s.nextInt();
		for (int i = 0; i < count; i++) {
			MusicInstruments saxo = new Saxophone(s);
			Saxsophones.add(saxo);
		}
		return Saxsophones;
	}

	// A method that read from file and catch exceptions
	public static void readFromFile(Scanner inputFromConsole, ArrayList musicalInstruments) {
		boolean continueInput = false;
		do {
			// Prompt the user to enter a file name
			System.out.print("Please enter instruments file name/path:" + "\n");
			String filename = inputFromConsole.nextLine();
			try {
				inputFromConsole = new Scanner(new File(filename));
				addAllInstruments(createGuitars(inputFromConsole), musicalInstruments);
				addAllInstruments(createBass(inputFromConsole), musicalInstruments);
				addAllInstruments(createFluet(inputFromConsole), musicalInstruments);
				addAllInstruments(creatSaxsophone(inputFromConsole), musicalInstruments);
				inputFromConsole.close();
				continueInput = false;
			} catch (FileNotFoundException ex) {
				System.out.println("File Error! Please try again:");
				continueInput = true;
				inputFromConsole.nextLine();
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
				System.exit(1);
			} catch (InputMismatchException ex) {
				System.out.println(ex.getMessage());
				System.exit(1);
			}
		} while (continueInput);
	}

	public static void main(String[] args) {
		Scanner inputFromConsole = new Scanner(System.in);
		ArrayList instruments = new ArrayList();
		readFromFile(inputFromConsole, instruments);
		System.out.println("\n" + "Instruments loaded from file successfuly!" + "\n");
		if (instruments.isEmpty() == true)
			System.out.println("There are no instruments in the store currently");
		else {
			printInstruments(instruments);
			System.out.println("Different Instruments:" + getNumOfDifferentElement(instruments) + "\n");
			System.out.println("Most Expensive Instrument:" + "\n" + getMostExpensiveInstrument(instruments));
		}

	}
}