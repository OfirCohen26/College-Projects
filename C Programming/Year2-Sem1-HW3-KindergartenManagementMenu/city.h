#ifndef __CITY_H
#define __CITY_H
#include "child.h"
#include "garden.h"
#include <stdio.h>


typedef struct {
	Garden** allGardens;
	int numOfGardens;
} City;

int initCity(City* theCity);
void showCityGardens(const City* theCity);
int saveCity(City* thecity);
int readCity(City* theCity);
void showSpecificGardenInCity(City* theCity);
int cityAddGarden(City* theCity);
int countChova(City* theCity);
int birthdayToChild(City* theCity);
int ifGardenExist(City* theCity,char* key);
void ReleaseCity(City* theCity);
int addChildToSpecificGardenInCity(City* theCity);
int ifChildExist(City* theCity, int key);
#endif // __CITY_H

