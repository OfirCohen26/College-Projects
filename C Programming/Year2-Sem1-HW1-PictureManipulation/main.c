#include<stdio.h>
#include "helpFunc.h"
#include <stdlib.h>
#include "numberGame.h"
#include <time.h>
#include "pictureManipulation.h"
#include "chooseOption.h"

int main() {
	int keepOn = 0;
	char option;
	do {
		printf(
				"Please choose one of the following options:\n\nP/p - Picture Manipulation\n\nN/n - Number Game\n\nE/e Quit\n");
		scanf(" %c", &option);
		getchar();
		switch (option) {
		case 'p':
		case 'P':
			pictureManipulationOption();
			break;
		case 'n':
		case 'N':
			numberGameOption();
			break;
		case 'e':
		case 'E':
			printf("Bye Bye\n");
			keepOn = 1;
			break;
		}
		printf("\n");
	} while (keepOn != 1);
	return 0;
}
