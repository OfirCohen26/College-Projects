#include<stdio.h>
#include "city.h"
#define  READ_CITY      1
#define  SHOW_CITY      2
#define  SHOW_GARDEN 	3
#define  WRITE_CITY 	4
#define  ADD_GARDEN 	5
#define  ADD_CHILD      6
#define  CHILD_BIRTHDAY 7
#define  COUNT_GRADUATE 8
#define  EXIT 	        0

int menu() {
	int option;
	printf("Select: \n\n");
	printf("	%d - Read City information from file\n",READ_CITY);
	printf("	%d - Show all Kindergarten\n", SHOW_CITY);
	printf("	%d - Show a specific Kindergarten\n", SHOW_GARDEN);
	printf("	%d - Save city information to file\n", WRITE_CITY);
	printf("	%d - Add a Kindergarten\n", ADD_GARDEN);
	printf("	%d - Add a child\n", ADD_CHILD);
	printf("	%d - Birthday to a Child\n", CHILD_BIRTHDAY);
	printf("	%d - Count Hova childers \n", COUNT_GRADUATE);
	printf("	%d - Exit\n", EXIT);
	printf("	");
	scanf("%d", &option);
	return option;
}

int main() {
	City utz = { NULL, 0 };
	int uReq;

	//first time read
	readCity(&utz);
	do {
		uReq = menu();
		switch (uReq) {
		case READ_CITY:
			ReleaseCity(&utz);
			readCity(&utz); // Read city from file
			printf("========================================\n");
			break;
		case SHOW_CITY: //Displays all kindergartens details on the screen
			showCityGardens(&utz);
			printf("\n========================================\n");
			break;

		case SHOW_GARDEN:
			showSpecificGardenInCity(&utz);  //Displays a specific kindergarten details on the screen
			printf("\n========================================\n");
			break;

		case WRITE_CITY:
			saveCity(&utz); // Write city fo file
			printf("\n========================================\n");
			break;

		case ADD_GARDEN:
			cityAddGarden(&utz);  //  Adding a kindergarten
			printf("\n========================================\n");
			break;

		case ADD_CHILD:
			addChildToSpecificGardenInCity(&utz); //Adding a child
			printf("\n========================================\n");
			break;

		case CHILD_BIRTHDAY:
			birthdayToChild(&utz);  // Adding a year to the age of a particular child in a particular kindergarten
			printf("\n========================================\n");
			break;

		case COUNT_GRADUATE: // Displays the amount of children that going to school next year
			printf("There are %d children going to school next year\n",
					countChova(&utz));
			printf("\n========================================\n");
			break;

		}
	} while (uReq != EXIT);

	ReleaseCity(&utz); //free all allocations

	return 1;
}
