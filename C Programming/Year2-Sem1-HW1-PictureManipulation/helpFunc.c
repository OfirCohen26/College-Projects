#include<stdio.h>
#include <stdlib.h>
#include "helpFunc.h"
#include "pictureManipulation.h"

// Function for print matrix
void printMat(const int* mat, int rows, int cols) {
	int i, j;
	for (i = 0; i < rows; i++) {
		for (j = 0; j < cols; j++) {
			printf("%5d", *mat);
			mat++;
		}
		printf("\n");
	}
}

//Function for swap two numbers using pointers
void swap(int* a, int* b) {
	int temp;
	temp = *b;
	*b = *a;
	*a = temp;
}

//Function for do transpose of matrix
void transpose(int* mat, int rows, int cols) {
	int i, j;
	for (i = 0; i < rows; i++) {
		for (j = i + 1; j < cols; j++) {
			swap((int*) mat + cols * i + j, (int*) mat + cols * j + i);
		}
	}
}

// Function for print matrix
void print(const int* mat, int size) {
	int i;
	for (i = 0; i < size; i++) {
		printf("%5d", *mat);
		mat++;
	}

}

