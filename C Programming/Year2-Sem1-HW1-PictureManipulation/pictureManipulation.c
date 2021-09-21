#include<stdio.h>
#include <stdlib.h>
#include "helpFunc.h"
#include "pictureManipulation.h"
#include <time.h>

//Initializing a matrix with randomly values
void initMatWithRandomalValues(int* mat, int rows, int cols) {
	int i, j;
	int randValue;
	srand(time(NULL));
	for (i = 0; i < rows; i++) {
		for (j = 0; j < cols; j++) {
			randValue = MINPIC + (rand() % (MAXPIC - MINPIC + 1));
			(*mat) = randValue;
			mat++;
		}
	}
}

// Function to clockwise rotate matrix by 90 degree
void rotatemat90Deright(int* mat, int rows, int cols) {
	transpose(mat, rows, cols);
	verticallyColsreversed(mat, rows, cols);
}

// Function to anticlockwise rotate matrix by 90 degree
void rotatemat90Left(int* mat, int rows, int cols) {
	transpose(mat, rows, cols);
	horizontallyRowsreversed(mat, rows, cols);
}

// Function for horizontal transformation of matrix
void horizontallyRowsreversed(int* mat, int rows, int cols) {
	int i, j;
	for (i = 0; i < rows; i++) {
		for (j = 0; j < cols / 2; j++) {
			swap((int*) mat + cols * j + i,
					(int*) mat + cols * (rows - j - 1) + i);
		}
	}
}

// Function for vertical transformation of matrix
void verticallyColsreversed(int* mat, int rows, int cols) {
	int i, j;
	for (i = 0; i < rows; i++) {
		for (j = 0; j < cols / 2; j++) {
			swap((int*) mat + cols * i + j,
					(int*) mat + cols * i + (cols - j - 1));
		}
	}
}

