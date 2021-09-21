#include "city.h"
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

int initCity(City* theCity) {
	int i;
	printf("How many gardens in city?"); // Ask the user to input the number of kinderGartens in the city
	scanf("%d", &(theCity->numOfGardens));
	if (theCity->numOfGardens == 0)
		return 0;
	// the function allocate appropriate place by the number of kinderGartens
	theCity->allGardens = (Garden**) malloc(
			theCity->numOfGardens * sizeof(Garden*));
	if (!theCity->allGardens)
		return 0;  //allocation did not work
	for (i = 0; i < theCity->numOfGardens; i++) {
		theCity->allGardens[i] = (Garden*) malloc(sizeof(Garden));
		if (!theCity->allGardens[i])
			return 0; //allocation did not work
		printf("City %d: ", (i + 1));
		if (!initGarden(theCity->allGardens[i]))
			return 0; //allocation did not work
	}
	return 1;

}

// Function that prints details of "city"
void showCityGardens(const City* theCity) {
	int i;
	//printf("There are %d gardens in city\n", theCity->numOfGardens);
	for (i = 0; i < theCity->numOfGardens; i++) {
		printf("\nKindergarten %d: \n", (i + 1));
		printGarden(theCity->allGardens[i]);
	}
}

// Function that write "Cities" to file
int saveCity(City* thecity) {
	int i;
	FILE* fp;
	fp = fopen("DataFile.txt", "w"); // Open file
	if (!fp)
		return -1; // file didn't open
	fprintf(fp, "%d\n", thecity->numOfGardens);
	for (i = 0; i < thecity->numOfGardens; i++)
		saveGardenToFile(fp, thecity->allGardens[i]);
	fclose(fp); // Close file
	return 0;
}

// Function that read "Cities" to file
int readCity(City* theCity) {
	FILE* fp;
	int i;
	fp = fopen("DataFile.txt", "r"); // Open file
	if (!fp)
		return 0;

	fscanf(fp, "%d\n", &(theCity->numOfGardens));
	if (theCity->numOfGardens == 0) {
		fclose(fp); // Close file
		return 0;
	}

	theCity->allGardens = (Garden**) malloc(
			theCity->numOfGardens * sizeof(Garden*));
	if (!theCity->allGardens) {
		fclose(fp); // Close file
		return 0;
	}
	for (i = 0; i < theCity->numOfGardens; i++) {
		theCity->allGardens[i] = (Garden*) malloc(sizeof(Garden));
		if (!theCity->allGardens[i]) {
			fclose(fp); // Close file
			return 0;
		}
		if (!readGardenFromFile(fp, theCity->allGardens[i])) {
			fclose(fp);
			return 0;
		}
	}

	fclose(fp); // Close file
	return 1;
}

// Function that check if kindergarten already exists
int ifGardenExist(City* theCity, char* key) {
	int i;
	for (i = 0; i < theCity->numOfGardens; i++) {
		if (strcmp((theCity->allGardens[i]->nameOfGarden), key) == 0) { // 0 equals
			return i; //if the name of the kindergarten exists, return the index of the kindergarten
		}
	}
	return -1; // If the name of the kindergarten does not exist return -1
}

// Function that check if child already exists
int ifChildExist(City* theCity, int key) {
	int i, j;
	for (i = 0; i < theCity->numOfGardens; i++) {
		for (j = 0; j < theCity->allGardens[i]->numOfChildren; j++) {
			if (theCity->allGardens[i]->allChildren[j]->idOfChild == key) { // 0 equals
				return j; // If the id of the child exists, return the index of the child
			}
		}
	}
	return -1; // If the id of the child does not exist
}


// Function that add a child
int addChildToSpecificGardenInCity(City* theCity) {
	int indexOfGarden, indexOfChild, childId;
	int flagChildExist = 0;
	char* gardenName;
	gardenName = createDynStr("\nGive me the Kinder garden Name:"); // Ask user to enter Kindergarten name
	indexOfGarden = ifGardenExist(theCity, gardenName); // Check if the kindergarten already exists, if so return its index
	if (indexOfGarden == -1) {
		printf("no such kinderGarten \n"); // If the kindergarten was not found
		return 0;
	}
	while (flagChildExist == 0) {
		printf("\nID No.: \n");
		scanf(" %d", &(childId)); // Ask user to enter child's ID
		indexOfChild = ifChildExist(theCity, childId); // Check if the child already exists, if so return its index
		if (indexOfChild != -1) {
			printf("This child is in the kinderGarten\n"); // If the child was found
		} else { // If the child was found - the function allocate another space for new child
			theCity->allGardens[indexOfGarden]->allChildren = (Child**) realloc(theCity->allGardens[indexOfGarden]->allChildren,
					sizeof(Child*) * (theCity->allGardens[indexOfGarden]->numOfChildren + 1));
			if (!theCity->allGardens[indexOfGarden]->allChildren)
				return 0; //allocation did not work
			theCity->allGardens[indexOfGarden]->allChildren[theCity->allGardens[indexOfGarden]->numOfChildren] = (Child*) malloc(
					sizeof(Child));
			if (!theCity->allGardens[indexOfGarden]->allChildren[theCity->allGardens[indexOfGarden]->numOfChildren])
				return 0;  //allocation did not work
			if (!initChildWithID(theCity->allGardens[indexOfGarden]->allChildren[theCity->allGardens[indexOfGarden]->numOfChildren], childId))
				return 0; //allocation did not work
		theCity->allGardens[indexOfGarden]->numOfChildren++; // Increases the number of the children in this kindergarten by 1
		flagChildExist = 1;
		}
	}
	return 1;

}


// Function that add a kindergarten
int cityAddGarden(City* theCity) {
	int indexOfGarden;
	int flagGardenExist = 0;
	char* gardenName;
	while (flagGardenExist == 0) {
		gardenName = createDynStr("\nName:");  // Ask user to enter Kindergarten name
		indexOfGarden = ifGardenExist(theCity, gardenName); // Check if the kindergarten exists, if so return its index else return -1
		if (indexOfGarden != -1) {
			printf("This kinderGarten already in the list\n"); // If the kindergarten was not found
		} else { // If the kindergarten was found - the function allocate another spaces for new kindergarten
			theCity->allGardens = (Garden**) realloc(theCity->allGardens,
					sizeof(Garden*) * (theCity->numOfGardens + 1));
			if (!theCity->allGardens)
				return 0; //allocation did not work
			theCity->allGardens[theCity->numOfGardens] = (Garden*) malloc(
					sizeof(Garden));
			if (!theCity->allGardens[theCity->numOfGardens])
				return 0; //allocation did not work
			if (!initGardenWithNameOfTheGarden(
					theCity->allGardens[theCity->numOfGardens], gardenName))
				return 0; //allocation did not work
			theCity->numOfGardens++; // Increases the number of kinderGartens by 1
			flagGardenExist = 1;
		}
	}
	return 1;
}

// Adding a year to the age of a particular child in a particular kindergarten
int birthdayToChild(City* theCity) {
	int indexOfGarden = 0, indexOfChild = 0, childId = 0;
	char* gardenName;
	gardenName = createDynStr("\nGive me the Kinder garden name:"); // Ask user to enter Kindergarten name
	indexOfGarden = ifGardenExist(theCity, gardenName); // Check if the kindergarten exists, if so return its index else return -1
	if (indexOfGarden == -1) {
		printf("No such Kindergarten"); // If the kindergarten was not found
		return 0;
	} else {
		printf("Enter child id \n"); // Ask the user to enter Kindergarten name
		scanf(" %d", &(childId));
		indexOfChild = ifChildExist(theCity, childId); // Check if the child already exists, if so return its index else return -1
		if (indexOfChild == -1) {
			printf("No Such child"); // If the child was not found
			return 0;
		}
	}
	theCity->allGardens[indexOfGarden]->allChildren[indexOfChild]->ageOfChild++; // If the garden and the child were found, adding a year to  the age
	return 1;
}

//Displays a specific kindergarten details on the screen
void showSpecificGardenInCity(City* theCity) {
	int index = 0;
	char* gardenName;
	gardenName = createDynStr("\nGive me the Kinder garden name"); // Ask the user to enter Kinder garden name
	index = ifGardenExist(theCity, gardenName);  // Check if the kindergarten exists, if so return its index else return -1
	if (index != -1) {
		printGarden(theCity->allGardens[index]); // If the Specific kindergarten was found  - print its details
	} else {
		printf("No Such kindergarten"); // If the Specific kindergarten was not found
	}
}

// Function that return the amount of children that going to school next year
int countChova(City* theCity) {
	int i, count = 0;
	for (i = 0; i < theCity->numOfGardens; i++) {
		if (theCity->allGardens[i]->typeOfGarden == 0) { // Check if the type of the kinder garden is "Chova"
			count = count + theCity->allGardens[i]->numOfChildren; // count the number of the children in "Chova" kindergarten
		}
	}
	return count; // Return the amount of children that going to school next year
}


//Free all allocations
void ReleaseCity(City* theCity) {
	int i;
	for (i = 0; i < theCity->numOfGardens; i++) {
		freeGarden(theCity->allGardens[i]);
		free(theCity->allGardens[i]);
	}
	free(theCity->allGardens);
}

