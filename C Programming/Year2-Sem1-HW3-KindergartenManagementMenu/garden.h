#ifndef __GARDEN_H
#define __GARDEN_H
#include <stdio.h>
#include <stdlib.h>
#include "child.h"

typedef enum {Chova, TromChova ,TromTromChova ,NofTypes} Type;

typedef struct {
	char* nameOfGarden;
	Type typeOfGarden;
	Child** allChildren;
	int numOfChildren;
} Garden;

const char* typeTilte[NofTypes];

int initGarden(Garden* theGarden);
int initGardenWithOutNameOfTheGarden(Garden* theGarden);
int initGardenWithNameOfTheGarden(Garden* theGarden, char* nameOfKinderGarten);
Type getTypeFromUser();
void printGarden(const Garden* theGarden);
void saveGardenToFile(FILE* fp, Garden* garden);
int readGardenFromFile(FILE* fp, Garden* garden);
char* createDynStr(const char* msg1);
void freeGarden(Garden* garden);

#endif // __GARDEN_H

