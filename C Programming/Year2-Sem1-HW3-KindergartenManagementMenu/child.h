#ifndef __CHILD_H
#define __CHILD_H
#include <stdio.h>

typedef struct {
	int idOfChild; // The id of the child
	int ageOfChild; // The age of the child
} Child;

int initChild(Child* theChild);
int initChildWithID(Child* theChild, int idOfTheChild);
int readChildfromFile(FILE* fp, Child* theChild);
void printChild(const Child* theChild);
void saveChildToFile(FILE* fp, Child* theChild);
void freeChild(Child* theChild);

#endif // __CHILD_H
