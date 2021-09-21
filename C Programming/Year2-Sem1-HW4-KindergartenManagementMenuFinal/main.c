#include <stdio.h>
#include <stdlib.h>
#include "sort.h"
#include "General.h"
#include "Kindergarten.h"
#include "Child.h"
#include "City.h"
#include "linkedList.h"
#include "def.h"

int main(int argc, char* argv[]) {
	if (argc != 2) {
		printf(
				"You must enter 1 for binary file and 0 for text file\n For example .\\HW4 1");
		return 0;
	}
	int opt;
	sscanf(argv[1], "%d", &opt); // The option that the user enter - 1 for binary - 0 for text

	City utz = { NULL, 0 };
	int uReq;
		readCity(&utz,opt);

	do {
		uReq = menu();
		switch (uReq) {
		case READ_CITY:
			readCity(&utz,opt);
			break;

		case SHOW_CITY:
			showCityGardens(&utz);
			break;

		case SHOW_GARDEN:
			showSpecificGardenInCity(&utz);
			break;

		case WRITE_CITY:
			saveCity(&utz,opt);
			break;

		case ADD_GARDEN:
			cityAddGarden(&utz);
			break;

		case ADD_CHILD:
			addChildToSpecificGardenInCity(&utz);
			break;

		case CHILD_BIRTHDAY:
			birthdayToChild(&utz);
			break;

		case COUNT_GRADUATE:
			printf("There are %d children going to school next year\n",
					countChova(&utz));
			break;
		case SORT_GARDENBYNAME:
			SortGardensByName(&utz);
			break;
		case SORT_GARDENBYTYPEANDNUMOFCHILDREN:
			sortGardenByTypeAndChildrenBycount(&utz);
			break;
		case SORT_CHILDRENINGARDEN:
			SortChildrenByID(&utz);
			break;
		case GARDENIN_LINKEDLIST:
			kindergartensLinkedList(&utz);
			break;

		}
	} while (uReq != EXIT);

	releaseCity(&utz); //free all allocations

	return EXIT_SUCCESS;
}

