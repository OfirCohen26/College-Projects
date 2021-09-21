#ifndef __SORT__
#define __SORT__
#include "City.h"
#include "Kindergarten.h"

void insertionSort(void* arr, int size, int typeSize,
		int (*compare)(const void* st1, const void* st2));

int compareGardensByName(const void* st1, const void* st2);
int compareChildrenByID(const void* st1, const void* st2);
int compareGardensByTypeAndNumberOfChildren(const void* st1, const void* st2);


int SortGardensByName(City* pCity);
int SortChildrenByID(City* pCity);
int sortGardenByTypeAndChildrenBycount(City* pCity);



#endif
