#ifndef __CHILD__
#define __CHILD__

typedef struct {
	int id;
	int age;
} Child;

void readChild(FILE* fp, Child* pChild,int option);
//void readChildFromBinaryF(FILE* fp, Child* pChild);
void getChildFromUser(Child* pChild, int id);
void showChild(const Child* pChild);
void writeChild(FILE* fp, const Child* pChild,int option);
//int findChildById(Child** pChildList, int count, int id);
int findChildById(Child** pChildList, int count, int id);
//void writeChildB(FILE* fp, const Child* pChild);
void birthday(Child* pChild);
//void	releaseChild(Child* pChild);
#endif
