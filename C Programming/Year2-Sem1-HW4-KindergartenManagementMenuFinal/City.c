#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "General.h"
#include "linkedList.h"
#include "def.h"
#include "City.h"
#include "Kindergarten.h"

void readCity(City* pCity, int option) {
	if (pCity->pGardenList != NULL) {
		releaseCity(pCity);
		pCity->count = 0;
	}

	if (option == 0)
		pCity->pGardenList = readAllGardensFromFile(DATA_FILE_TEXT,
				&pCity->count,option);
	else
		pCity->pGardenList = readAllGardensFromFile(DATA_FILE_BINARY,
				&pCity->count,option);

	if (pCity->pGardenList == NULL)
		printf("Error reading city information\n");
}

void showCityGardens(City* pCity) {
	showAllGardens(pCity->pGardenList, pCity->count);
}

void showSpecificGardenInCity(City* pCity) {
	showGardenMenu(pCity->pGardenList, pCity->count);
}

void saveCity(City* pCity, int option) {
	if (option == 0)
		writeGardensToFile(pCity->pGardenList, pCity->count, DATA_FILE_TEXT,option);
	else
		writeGardensToFile(pCity->pGardenList, pCity->count, DATA_FILE_BINARY,option);

}

void cityAddGarden(City* pCity) {
	pCity->pGardenList = addGarden(pCity->pGardenList, &pCity->count);
	if (pCity->pGardenList == NULL) //Allocation error
		printf("Error adding kindergarten\n");
}

void addChildToSpecificGardenInCity(City* pCity) {
	addChildToGarden(pCity->pGardenList, pCity->count);
}

void birthdayToChild(City* pCity) {
	handleBirthdayToChild(pCity->pGardenList, pCity->count);
}

int countChova(City* pCity) {
	int i;
	int count = 0;
	for (i = 0; i < pCity->count; i++) {
		if (pCity->pGardenList[i]->type == Chova)
			count += pCity->pGardenList[i]->childCount;
	}
	return count;
}


// Function that check if kindergarten already exists
int findIndexGardenByName(City* theCity, char* key) {
	int i;
	for (i = 0; i < theCity->count; i++) {
		if (strcmp((theCity->pGardenList[i]->name), key) == 0) { // 0 equals
			return i; //if the name of the kindergarten exists, return the index of the kindergarten
		}
	}
	return -1; // If the name of the kindergarten does not exist return -1
}

void kindergartensLinkedList(City* pCity) {
	int num;
	num = (int) getTypeOption();
	printf("Kindergardens List:\n\n");
	LIST* l = createLinkedListForKindergartenType(pCity,num);
	kindergartensLinkedListAndFree(l);
}

void releaseCity(City* pCity) {
	release(pCity->pGardenList, pCity->count);
}
