#include<stdio.h>
#include "helpFunc.h"
#include <stdlib.h>
#include "numberGame.h"
#include <time.h>
#include "pictureManipulation.h"
#include "chooseOption.h"

void pictureManipulationOption() {
	int mat[SIZE][SIZE];
	initMatWithRandomalValues((int*) mat, SIZE, SIZE); //Initializing a matrix with randomly values
	printMat((int*) mat, SIZE, SIZE);
	printf("\n\n");
	int option = 0;
	do {
		printf("Please choose one of the following options: \n\n");
		printf("1 - 90 degree clockwise\n\n2 - 90 degree counter clockwise\n\n"
				"3 - Flip Horizontal\n\n4 - Flip Vertical\n\n-1 - Quit\n\n");
		scanf("%d", &option);
		if (option == 1) {
			rotatemat90Deright((int*) mat, SIZE, SIZE); // clockwise rotate matrix by 90 degree
		} else if (option == 2) {
			rotatemat90Left((int*) mat, SIZE, SIZE); // anticlockwise rotate matrix by 90 degree
		} else if (option == 3) {
			horizontallyRowsreversed((int*) mat, SIZE, SIZE); // horizontal transformation of matrix
		} else if (option == 4) {
			verticallyColsreversed((int*) mat, SIZE, SIZE); // vertical transformation of matrix
		}
		if (option != -1) {
			printf("\n---------Picture after manipulation--------- \n");
			printMat((int*) mat, SIZE, SIZE);
			printf("\n");
		}
	} while (option != -1);
}

void numberGameOption() {
	int mat[ROWS][COLS];
	int size = sizeof(mat) / sizeof(int);
	initMat((int*) mat, ROWS, COLS); //Initializing a matrix with a range of values
	printf("\n");
	shuffle_Count((int*) mat, ROWS, COLS); // Shuffling numbers randomly
	printMat((int*) mat, ROWS, COLS); // Printing matrix after Shuffling
	printf("\n");
	int flagSort, key;
	char invalidStepFlag;
	flagSort = isAscending((int*) mat, size); //Checking if the "board" is sorted before starting the game
	while (flagSort != 1) {
		scanf("%d", &key);
		printf("Your Step: %d \n", key);
		invalidStepFlag = checkIfmovingIsPossible((int*) mat, size, key, ROWS,
		COLS); // Performs the move or ask again for number
		if (invalidStepFlag != 'F') {
			printMat((int*) mat, ROWS, COLS); //Printing the matrix after manipulation
			printf("\n\n");
		}
		flagSort = isAscending((int*) mat, size); //Checking if the "board" is sorted
	}
	printf("You win! The game is over!"); // The game is over when the "board" is sorted
}

