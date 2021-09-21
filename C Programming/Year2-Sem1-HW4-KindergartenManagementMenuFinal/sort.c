#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "General.h"
#include "Kindergarten.h"
#include "Child.h"
#include "City.h"
#include "sort.h"


// insertion sort function
void insertionSort(void* arr, int size, int typeSize,
		int (*compare)(const void* st1, const void* st2)) {
	int i, j;
	void* key;
	key = malloc(typeSize);
	for (i = 1; i < size; i++) {
		memcpy(key, (char*) arr + i * typeSize, typeSize);
		for (j = i - 1;
				j >= 0 && (compare((char*) arr + j * typeSize, key) > 0); j--)
			memcpy((char*) arr + (j + 1) * typeSize, (char*) arr + j * typeSize,
					typeSize);
		memcpy((char*) arr + (j + 1) * typeSize, key, typeSize);
	}
	free(key);
}

// Function the compare between gardens by their name
int compareGardensByName(const void* st1, const void* st2) {
	const Garden* pS1 = *(const Garden**) st1;
	const Garden* pS2 = *(const Garden**) st2;
	return strcmp(pS1->name, pS2->name);
}

// Function the compare between children by their ID
int compareChildrenByID(const void* st1, const void* st2) {
	const Child* pS1 = *(const Child**) st1;
	const Child* pS2 = *(const Child**) st2;
	return pS1->id - pS2->id;
}

// Function the compare between gardens by their type, if the type is equal compare  between gardens by count of children
int compareGardensByTypeAndNumberOfChildren(const void* st1, const void* st2) {
	int num;
	const Garden* pS1 = *(const Garden**) st1;
	const Garden* pS2 = *(const Garden**) st2;
	num = pS1->type - pS2->type; // main sort by type of garden
	if (num == 0) { // if the types of gardens are equals
		return pS1->childCount - pS2->childCount; // Secondary sort by count of children
	} else
		return num;
}

// sort gardens by name
int SortGardensByName(City* pCity) {
	insertionSort(pCity->pGardenList, pCity->count, sizeof(Garden*),
			compareGardensByName);
	return 1;
}

// sort children in specific garden by id
int SortChildrenByID(City* pCity) {
	int indexOfGarden;
	char gardenName[100];
	char* gardenNameAfterManipulation;
	puts("\nGive me the Kinder garden Name:");
	scanf("%s", gardenName);  // Ask user to enter Kindergarten name
	gardenNameAfterManipulation = getStrExactLength(gardenName);
	indexOfGarden = findIndexGardenByName(pCity, gardenNameAfterManipulation); // Check if the kindergarten already exists, if so return its index
	if (indexOfGarden == -1) {
		printf("no such kinderGarten \n"); // If the kindergarten was not found
		return 0;
	}
	insertionSort(pCity->pGardenList[indexOfGarden]->childPtrArr,
			pCity->pGardenList[indexOfGarden]->childCount, sizeof(Child*),
			compareChildrenByID);
	return 1;
}

// sort gardens by type and children count
int sortGardenByTypeAndChildrenBycount(City* pCity) {
	insertionSort(pCity->pGardenList, pCity->count, sizeof(Garden*),
			compareGardensByTypeAndNumberOfChildren);
	return 1;
}

