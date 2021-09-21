#include <stdio.h>
#include <stdlib.h>
#include "helpFunc.h"
#include "numberGame.h"
#include <time.h>

//Initializing a matrix with a range of values
void initMat(int* mat, int rows, int cols) {
	int i;
	for (i = 0; i < rows * cols; i++) {
		*mat = i;
		mat++;
	}
}

// Function for shuffling numbers randomly
void shuffle_Count(int* mat, int rows, int cols) {
	int i;
	int randRow1, randRow2;
	int randCol1, randCol2;
	srand(time(0));
	for (i = 0; i < SHUFFLE_COUNT; i++) {
		randRow1 = (rand() % ((rows - 1) + 1));
		randCol1 = (rand() % ((cols - 1) + 1));
		randRow2 = (rand() % ((cols - 1) + 1));
		randCol2 = (rand() % ((cols - 1) + 1));
		swap((int*) mat + cols * randRow1 + randCol1,
				(int*) mat + cols * randRow2 + randCol2);
	}
	printf("\n");
}

// Function that find the index of an element in an array
int findIndex(int* mat, int size, int key) {
	int i;
	for (i = 0; i < size; i++) {
		if ((*mat) == key) {
			return i;
		}
		mat++;
	}
	return -1;
}

//Function for checking if the user typed an invalid number
char checkIfmovingIsPossible(int* mat, int size, int key, int rows, int cols) {
	int indexForMove = findIndex(mat, size, key); // Return the index of the required number
	int *firstPlace = (int*) mat;
	firstPlace = (int*) mat;
	char flagMove = 'F'; //
	if (indexForMove == -1) {
		printf("Number out Of bounds!, Try again\n\n");
		return 'F';
	} else { // Try moving above
		if (indexForMove >= cols) {
			if (*(mat + (indexForMove - cols)) == 0) {
				flagMove = 'T';
				swap(firstPlace + indexForMove,
						firstPlace + (indexForMove - cols));
			}
		} //Try moving  below
		if (indexForMove <= cols * (rows - 1) - 1) {
			if (*(mat + (indexForMove + cols)) == 0) {
				flagMove = 'T';
				swap(firstPlace + indexForMove,
						firstPlace + (indexForMove + cols));
			}
		} //Try moving left
		if (indexForMove % cols != 0) {
			if (*(mat + (indexForMove - 1)) == 0) {
				flagMove = 'T';
				swap(firstPlace + indexForMove,
						firstPlace + (indexForMove - 1));
			}
		}  //Try moving right
		if (indexForMove % cols != cols - 1) {
			if (*(mat + (indexForMove + 1)) == 0) {
				flagMove = 'T';
				swap(firstPlace + indexForMove,
						firstPlace + (indexForMove + 1));
			}
		}	// Invalid step
		if (flagMove == 'F') {
			// Invalid step
			printf("Invalid step!\n\n");
			return 'F';
		}
	}
	return 'T';
}
// Return 1 if the array is sorted
// Return 0 if the array is unsorted
int isAscending(int* mat, int size) {
	int i, ifSorted;
	if (*mat == 0) {
		ifSorted = 0;
		return ifSorted;
	}
	for (i = 0; i < size - 2; i++) {
		if ((*mat) > *(mat + 1)) {
			ifSorted = 0;
			return ifSorted;
		}
		mat++;
	}
	ifSorted = 1;
	return ifSorted;
}

