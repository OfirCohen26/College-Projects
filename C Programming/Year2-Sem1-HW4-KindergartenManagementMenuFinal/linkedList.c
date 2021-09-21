#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "General.h"
#include "Kindergarten.h"
#include "Child.h"
#include "City.h"
#include "sort.h"
#include "linkedList.h"
#include "def.h"




////////////////////////////////////////////////
// createLinkedListForKindergartenType
// Aim:		create list of all the gardens from specific type (in the city)
// Input:	pointer to  city
// Output:	list of all the gardens from specific type (in the city)
////////////////////////////////////////////////
LIST* createLinkedListForKindergartenType(City* pCity, int type) {
	LIST lst;
	LIST* pLst;
	int i;
	NODE* pNode;
	// Initialization
	L_init(&lst);
	//head Node
	pNode = &lst.head;
	for (i = 0; i < pCity->count; i++) {
		if (pCity->pGardenList[i]->type == type) {
			pNode = L_insert(pNode, &pCity->pGardenList[i]);
		}
	}

	pLst = (LIST*) malloc(sizeof(lst));
	if(!pLst){
		return NULL;
	}
	memcpy(pLst,&lst,sizeof(lst));
	return pLst;
}

////////////////////////////////////////////////
// displayKindergartensFroList
// Aim:		print the list content (assume the DATA is Garden)
// Input:	pointer to the list structure
// Output:	a number of the printed elements
////////////////////////////////////////////////
int displayKindergartensFroList(LIST* pList,void (*print)(const void*)) {
	NODE *tmp;
	int c = 0;

	tmp = pList->head.next;
	if (!pList)
		return 0;

	for (tmp = pList->head.next; tmp; tmp = tmp->next, c++) {
		print(tmp->key);
		printf("\n");

	}
	printf("\n");
	return c;
}


void kindergartensLinkedListAndFree(LIST* l) {
	LIST l2 = *l;
	displayKindergartensFroList(&l2,printGarden);
	L_free(&l2);
}



//////////////////////////////////////////
// Init
// Aim:		create new list
// Input:	pointer to the list structure
// Output:	TRUE if succeeded
//////////////////////////////////////////
BOOL L_init(LIST* pList) {
	if (pList == NULL)
		return False;	// no list to initialize

	pList->head.next = NULL;
	return True;
}

/////////////////////////////////////////////////////////////////
// Insert
// Aim:		add new node
// Input:	pointer to the node BEFORE the place for the new one
//			a value to be stored in the new node
// Output:	pointer to the new node
/////////////////////////////////////////////////////////////////
NODE* L_insert(NODE* pNode, DATA Value) {
	NODE* tmp;

	if (!pNode)
		return NULL;

	tmp = (NODE*) malloc(sizeof(NODE));	// new node

	if (tmp != NULL) {
		tmp->key = Value;
		tmp->next = pNode->next;
		pNode->next = tmp;
	}
	return tmp;
}

//////////////////////////////////////////////////////////////
// Delete
// Aim:		erase node
// Input:	pointer to the node BEFORE the node to be deleted
// Output:	TRUE if succeeded
//////////////////////////////////////////////////////////////
BOOL L_delete(NODE* pNode) {
	NODE* tmp;

	if (!pNode || !(tmp = pNode->next))
		return False;

	pNode->next = tmp->next;
	free(tmp);
	return True;
}


////////////////////////////////////////////////
// Free (additional function)
// Aim:		free the list memory
// Input:	pointer to the list structure
// Output:	TRUE if succeeded
////////////////////////////////////////////////
BOOL L_free(LIST* pList) {
	NODE *tmp;

	if (!pList)
		return False;

	for (tmp = &(pList->head); L_delete(tmp);)
		;
	return True;
}
