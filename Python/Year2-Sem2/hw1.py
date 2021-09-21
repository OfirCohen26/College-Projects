# Q1
# A
numbers = [[12, 4], [1], [2, 3]]
count = 0
for i in numbers:
    for j in i:
        count = count + j
print(count)

# Q2
#A
str1 = "original"
str1R = str1[::-1]
print(str1R)


#B
words = [["ofir", "dior"], "iris", ["lalo"], 's', 9, ["lalo", "popi", "pipo"]]
for i in words:
    if type(i) is list:
        i.reverse()
words.reverse()
print(words)

#C
aTuple = (1, 2, 3, 4,)
print(aTuple[::-1])

#Q3
#A
bTuple = ({1, 'r', 3}, {'r',3.5,'a'}, {5.7, 3, 3.8,'b'})
newSet = set()
list1 = []
for i in bTuple:
    newSet = i.union(newSet)
list1 = list(newSet)
list1.sort(key=lambda item: ([str, int, float].index(type(item)), item))
print(list1)

#B - i used the list from A
d = {}
d = {i: list1[i] for i in range(len(list1))}
print(d)

#Q6
#A
x = int(input("Please enter a number: "))
y = x > 10 and 6 or 9
print(y)
#B
y = 6 if x > 10 else 9
print(y)


#Q7
n1 = int(input("Please enter the first number: "))
n2 = int(input("Please enter the second number: "))
#A
#up
for i in range(n1, n2+1, 1):
    i % 2 == 0 and print(i)
#down
for i in range(n2, n1-1, -1):
    i % 2 == 0 and print(i)

#B
n1 = int(input("Please enter the first number: "))
n2 = int(input("Please enter the second number: "))
n3 = n1
#up
while n1 <= n2:
    n1 % 2 == 0 and print(n1)
    n1 = n1+1

#down
while n2 >= n3:
    n2 % 2 == 0 and print(n2)
    n2 = n2-1
