#include "helpFunc.h"
#include <stdio.h>
#include <string.h>
#include <ctype.h>


// Function that upper case a specific word in the string
char *upperWord(char* str, char* word) {
	char *pos = str;
	pos = strstr(str, word);
	while (pos == strstr(pos, word)) {
		char *tmp = word;
		while (*(tmp++)) {
			*pos = toupper(*pos);
			pos++;
		}
	}
	return str;
}

// Function that replace "special" characters with selected char
char *replaceWithSpecialChars(char* str, char sChar) {
	char *ptr;
	ptr = str;
	//char space = ' ';
	while (*ptr) {
		if (!isalpha(*ptr) && !isdigit(*ptr)) {
			*ptr = sChar;
		}
		ptr++;
	}
	return str;
}
