#ifndef _LIST_
#define _LIST_

#include <stdio.h>
#include <stdlib.h>

#include "General.h"
#include "Kindergarten.h"
#include "Child.h"
#include "City.h"
#include "sort.h"
#include "def.h"

typedef struct {
	NODE head;
} LIST;

/*** Definitions ***/

/*** Function prototypes ***/

BOOL L_init(LIST* pList);					// create new list

NODE* L_insert(NODE* pNode, DATA Value);	// add new node after *pNode

BOOL L_delete(NODE* pNode);					// erase node after *pNode

BOOL L_free(LIST* pList);					// free list memory

int displayKindergartensFroList(LIST* pList,void (*print)(const void*));

LIST* createLinkedListForKindergartenType(City* pCity, int type);

void kindergartensLinkedListAndFree(LIST* l);

#endif

