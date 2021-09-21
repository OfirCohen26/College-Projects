#include "child.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

// Function that initialize "child's" details
int initChild(Child* theChild) {
	printf("\nID No.: \n");
	scanf("%d", &(theChild->idOfChild));
	printf("\nAge:\n");
	scanf("%d", &(theChild->ageOfChild));
	return 1;
}


// Function that initialize "child's" details, according to ID it gets
int initChildWithID(Child* theChild, int idOfTheChild) {
	theChild->idOfChild = idOfTheChild;
	printf("\nAge: \n");
	scanf("%d", &(theChild->ageOfChild));
	return 1;
}

// Function that prints details of "child"
void printChild(const Child* theChild) {
	printf("ID:%d Age:%d \n", theChild->idOfChild, theChild->ageOfChild);
}


// Function that read from file details of "child"
int readChildfromFile(FILE* fp, Child* theChild) {
	fscanf(fp, "%d %d", &(theChild->idOfChild), &(theChild->ageOfChild));
	return 1;
}

// Function that write to file details of "child"
void saveChildToFile(FILE* fp, Child* theChild) {
	fprintf(fp, "%d %d\n", theChild->idOfChild, theChild->ageOfChild);
}

