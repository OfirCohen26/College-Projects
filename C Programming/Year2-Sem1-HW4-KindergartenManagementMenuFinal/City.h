#ifndef __CITY__
#define __CITY__

#include "Kindergarten.h"

#define DATA_FILE_TEXT "DataFile.txt"
#define DATA_FILE_BINARY "DataFile.bin"
typedef struct {
	Garden** pGardenList;
	int count;
} City;

void readCity(City* pCity, int option);
void showCityGardens(City* pCity);
void showSpecificGardenInCity(City* pCity);
void saveCity(City* pCity, int option);
void cityAddGarden(City* pCity);
void addChildToSpecificGardenInCity(City* pCity);
void birthdayToChild(City* pCity);
int countChova(City* pCity);
void kindergartensLinkedList(City* pCity);
int findIndexGardenByName(City* theCity, char* key);
void releaseCity(City* pCity);

#endif
