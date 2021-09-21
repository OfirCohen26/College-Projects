#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include "garden.h"
#include "child.h"

const char* typeTilte[NofTypes] = { "Chova", "Trom Chova", "Trom Trom Chova" };

int initGarden(Garden* theGarden) {
	theGarden->nameOfGarden = createDynStr("Give me the Kinder garden name:");
	if (!theGarden->nameOfGarden)
		return 0; //allocation did not work
	return initGardenWithOutNameOfTheGarden(theGarden);
}

int initGardenWithOutNameOfTheGarden(Garden* theGarden) {
	int i;
	theGarden->typeOfGarden = getTypeFromUser();
	printf("\nEnter children details: \n\n");
	printf("Children count: \n");
	scanf("%d", &(theGarden->numOfChildren));
	if (theGarden->numOfChildren == 0) {
		theGarden->allChildren = NULL;
		return 1;
	}
	theGarden->allChildren = (Child**) malloc(
			sizeof(Child*) * theGarden->numOfChildren);
	if (!theGarden->allChildren)
		return 0;  //allocation did not work
	for (i = 0; i < theGarden->numOfChildren; i++) {
		theGarden->allChildren[i] = (Child*) malloc(sizeof(Child));
		if (!theGarden->allChildren[i])
			return 0; //allocation did not work
		if (!initChild(theGarden->allChildren[i]))
			return 0; //allocation did not work
	}
	return 1;
}

int initGardenWithNameOfTheGarden(Garden* theGarden, char* nameOfKinderGarten) {
	theGarden->nameOfGarden = nameOfKinderGarten;
	return initGardenWithOutNameOfTheGarden(theGarden);
}

// Function that prints details of "kindergarten"
void printGarden(const Garden* theGarden) {
	int i;
	printf("Name:%s	Type:%s	%d Children:\n", theGarden->nameOfGarden,
			typeTilte[theGarden->typeOfGarden], theGarden->numOfChildren);
	for (i = 0; i < theGarden->numOfChildren; i++)
		printChild(theGarden->allChildren[i]);
}

//Function that get type of Kindergarten from the user
Type getTypeFromUser() {
	int i, t;
	printf("Garden type: \n");  // Ask from the user to enter type of garden
	do {
		for (i = 0; i < NofTypes; i++)
			printf("Enter %d for %s\n", i, typeTilte[i]);
		scanf("%d", &t);
	} while (t < 0 || t >= NofTypes);
	return (Type) t;
}

// Function that write "Kindergartens" to file
void saveGardenToFile(FILE* fp, Garden* theGarden) {
	int i;
	fprintf(fp, "%s %d %d\n", theGarden->nameOfGarden, theGarden->typeOfGarden,
			theGarden->numOfChildren);
	for (i = 0; i < theGarden->numOfChildren; i++)
		saveChildToFile(fp, theGarden->allChildren[i]);
}

// Function that read "Kindergartens" from file
int readGardenFromFile(FILE* fp, Garden* theGarden) {
	int i, type;
	char temp[100];
	fscanf(fp, "%s", temp);
	theGarden->nameOfGarden = strdup(temp);
	if (!theGarden->nameOfGarden) //allocation did not work
		return 0;
	fscanf(fp, "%d %d \n", &type, &(theGarden->numOfChildren));
	(theGarden->typeOfGarden) = (Type) type;

	if (theGarden->numOfChildren == 0) {
		theGarden->allChildren = NULL;
		return 1;
	}
	theGarden->allChildren = (Child**) malloc(
			sizeof(Child*) * theGarden->numOfChildren);
	if (!theGarden->allChildren)
		return 0; //allocation did not work
	for (i = 0; i < theGarden->numOfChildren; i++) {
		theGarden->allChildren[i] = (Child*) malloc(sizeof(Child));
		if (!theGarden->allChildren[i])
			return 0; //allocation did not work
		if (!readChildfromFile(fp, theGarden->allChildren[i]))
			return 0; //allocation did not work
	}
	return 1;
}

// Function that assigns an exact length to a string
char* createDynStr(const char* msg1) {
	char* str;
	char temp[100];
	printf("%s \n", msg1);
	scanf("%s", temp);
	str = strdup(temp);
	return str;
}

//Free allocations
void freeGarden(Garden* garden) {
	int i;
	for (i = 0; i < garden->numOfChildren; i++) {
		free(garden->allChildren[i]);
	}
	free(garden->nameOfGarden);
	free(garden->allChildren);
}

