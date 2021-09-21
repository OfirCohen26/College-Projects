#include<stdio.h>
#include "stringFunc.h"

#define  NEW_STRING 1
#define  CURRENT_STRING 2
#define  COUNT_WORDS 	3
#define  LONGEST_WORD 	4
#define  REVERT_WORD 	5
#define  CHECK_ERASE_CHARS 6
#define  CHECK_PALINDROME 7
#define  EXIT 	-1
#define  EXIT_SUCCESS 0
#define  MAX_LEN 100

void printMenu() {
	printf("\n\n");
	printf("Please choose one of the following options\n");
	printf("%d - Enter new string\n", NEW_STRING);
	printf("%d - Print current string\n", CURRENT_STRING);
	printf("%d - Count words in string\n", COUNT_WORDS);
	printf("%d - Print longest word in string CAPITALIZED\n", LONGEST_WORD);
	printf("%d - Revert each word in string\n", REVERT_WORD);
	printf("%d - Check eraseCharsFromStr\n", CHECK_ERASE_CHARS);
	printf("%d - Check palindrome\n", CHECK_PALINDROME);
	printf("%d - Exit\n", EXIT);
}

void performChoice(int option, char *str) {
	switch (option) {
	case NEW_STRING:
		initString(str, MAX_LEN);
		break;
	case CURRENT_STRING:
		printString(str);
		break;

	case COUNT_WORDS: {
		int count = countWords(str);
		printf("There are %d words in string\n", count);
		break;
	}
	case LONGEST_WORD:
		longestInCaptital(str);
		printf("String after longest capital %s\n", str);
		break;

	case REVERT_WORD:
		revertWords(str);
		printf("String after revert %s\n", str);
		break;

	case CHECK_ERASE_CHARS:
		eraseCharsFromString(str, ":, ?!-;");
		printf("String after erase %s\n", str);
		break;

	case CHECK_PALINDROME:
		if (ispalindrome(str) == 0)
			printf("String %s is Palindrome\n", str);
		else
			printf("String %s is NOT Palindrome\n", str);
		break;

	case EXIT:
		printf("Bye bye\n");
		break;

	default:
		printf("Wrong option\n");
		break;
	}

}

int main() {
	int option;
	char str[MAX_LEN] = "\0";
	do {
		printMenu();
		scanf("%d", &option);
		performChoice(option, str);

	} while (option != EXIT);
	return EXIT_SUCCESS;
}
