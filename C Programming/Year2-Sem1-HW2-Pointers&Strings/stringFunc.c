#include <stdio.h>
#include "stringFunc.h"
#include <string.h>
#include <ctype.h>
#include "helpFunc.h"

void initString(char* str, int size) {
	printf("Please enter a new String: \n");
	fgets(str, size, stdin);
}

void printString(char* str) {
	puts(str);

}

int countWords(char* str) {
	int numOfWords = 0;
	while (*str) {
		while ((*str && isspace(*str)) || (*str && !isalpha(*str)))
			str++;
		if (*str)
			numOfWords++;
		while (*str && !isspace(*str))
			str++;
	}
	return numOfWords;
}

void longestInCaptital(char* str) {
	int strSize = strlen(str) + 1;
	char temp[strSize]; // Copy the original string
	char* words;
	strcpy(temp, str);
	words = strtok(temp, " ");
	int maxWord = 0, maxtemp = 0;
	int wordsSize = strlen(words) + 1;
	char longWord[wordsSize];
	while (words) {
		//maxtemp = charsInString(words); // Getting the number of chars in the current word
		maxtemp = strlen(words);
		if (maxtemp > maxWord) { // Comparing the number of chars in the current word with the previous word
			maxWord = maxtemp;
			strcpy(longWord, words);
		}
		words = strtok(NULL, " ");
	}
	str = upperWord(str, longWord);
}

void revertWords(char* str) {
	str = replaceWithSpecialChars(str, ' '); // replace all special chars
	char temp;
	char *start = str;
	char *end = start;
	char *endPtr = str;
	while (*endPtr != '\0') {
		start = endPtr;
		end = endPtr;
		while (!isspace(*end)) {
			end++;
			if (*end == '\0')
				break;
			endPtr = end;
			endPtr++;
		}
		end--;
		while (start < end) {
			temp = *start;
			*start = *end;
			*end = temp;
			start++;
			end--;
		}
		while (*endPtr && (isspace(*endPtr)))
			endPtr++;
	}

	str = replaceWithSpecialChars(str, '*'); // replace all special chars

}

void eraseCharsFromString(char *str, const char *symbols) {
	int strSize = strlen(str) + 1;
	char temp[strSize];
	char* words;
	strcpy(temp, str);
	words = strtok(str, symbols);
	int wordsSize = strlen(words) + 1;
	char newString[wordsSize];
	while (words) {
		strcat(newString, words);
		words = strtok(NULL, symbols);
	}
	strcpy(str, newString);
}

int ispalindrome(const char *str) {
	const char *k = str;
	while (*k)
		k++;
	k = k - 1;
	while (k > str) {
		if (!isalpha(*k)) {
			k--;
		} else if (!isalpha(*str))
			str++;
		else if (toupper(*k) != toupper(*str)) {
			return 1; // NOT Palindrome
		} else {
			k--;
			str++;
		}
	}
	return 0; // Palindrome
}

