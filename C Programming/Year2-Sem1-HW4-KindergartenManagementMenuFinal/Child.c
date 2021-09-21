#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "sort.h"

#include "Child.h"
#include "General.h"

/**************************************************/
/*             Read a Child from a file           */
/**************************************************/
void readChild(FILE* fp, Child* pChild, int option) {
	unsigned char info[2];
	unsigned char mask = 31;
	if (option == 0) {
		//Child ID
		fscanf(fp, "%d", &pChild->id);
		fscanf(fp, "%d", &pChild->age);
	} else {
		fread(info, sizeof(unsigned char), 2, fp);
		pChild->id = (info[1] & mask) << 8 | (info[0]);
		pChild->age = info[1] >> 5;
	}
}

/**************************************************/
/*            show a Child				           */
/**************************************************/
void showChild(const Child* pChild) {
	printf("\nID:%d  ", pChild->id);
	printf("Age:%d  ", pChild->age);
}

/**************************************************/
void getChildFromUser(Child* pChild, int id)
/**************************************************/
/**************************************************/
{
	pChild->id = id;

	puts("\nAge:\t");
	scanf("%d", &pChild->age);
}

/**************************************************/
/*Write a Child to the open file				*/
/**************************************************/
void writeChild(FILE* fp, const Child* pChild, int option) {
	unsigned char info[2];
	if (option == 0)
		fprintf(fp, "%d %d\n", pChild->id, pChild->age);
	else {
		info[1] = (pChild->age) << 5 | (pChild->id) >> 8;
		info[0] = pChild->id & 255;
		fwrite(info, sizeof(unsigned char), 2, fp);
	}
}

//linear search
//int findChildById(Child** pChildList, int count, int id) {
//	int i;
//	for (i = 0; i < count; i++) {
//		if (pChildList[i]->id == id)
//			return i;
//	}
//return -1;
//}

int findChildById(Child** pChildList, int count, int id) {
	Child temp = { id, 0 };
	Child* pTemp = &temp;
	Child** pfound;
	qsort(pChildList, count, sizeof(Child*), compareChildrenByID);
	pfound = (Child**) bsearch(&pTemp, pChildList, count, sizeof(Child*),
			compareChildrenByID);
	if (!pfound)
		return -1;
	return (*pfound)->id;

}

void birthday(Child* pChild) {
	pChild->age++;
}

//void	releaseChild(Child* pChild)
//{
//	//nothing to release
//}
