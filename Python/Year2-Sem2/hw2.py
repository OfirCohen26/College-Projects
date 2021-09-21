from collections import deque

# Q1
list1 = [[12, 4], [1], [2, 3]]


def calc(l):
    sum1 = sum([sum(j) for j in l])
    return sum1


sum2 = calc(list1)
print(sum2)

# Q2
# A


def hailstone(number):
    while number != 1:
        number = number / 2 if number % 2 == 0 else 3 * number + 1
        yield int(number)


for i in hailstone(5):
    print(i)


#B
class Hailstone(object):
    def __init__(self, z):
        self.number = z

    def __iter__(self):
        return self

    def __next__(self):
        if self.number != 1:
            self.number = self.number / 2 if self.number % 2 == 0 else 3 * self.number + 1
            return int(self.number)


k = Hailstone(5)
for i in range(5):
    print(next(k))


#C

def hailstone2(number1):
    iter_t = (j for j in hailstone(number1))
    for j in iter_t:
        print(j)


hailstone2(5)


#Q3
def bfs(graph, node):
    queue = deque([node])
    visited = []
    while queue:
        vertex = queue.popleft()
        if vertex not in visited:
            visited.append(vertex)
            queue.extend(graph[vertex])
    return visited


my_graph = {'a': ['b', 'c'], 'b': ['d'], 'c': [], 'd': ['a'], 'e': ['d']}
print(bfs(my_graph, 'a'))


# Q4

var1 = [1, 2, 3]
var2 = (1, 2, 3)
print(id(var1), id(var2))
var1 += [4,5]
var2 += (4,5)
print(id(var1), id(var2))

# Q5
#A
"""
We are creating an object of type
int. identifiers x and y points to the same object.
"""
x = 10
n = x
print("x's id:")
print(id(x))
print("n's id:")
print(id(n))
print("10's id:")
print(id(10))
""" id(x) == id(y)
id(y) == id(10)
if we do a simple operation"""
x = x + 1
""" Now
id(x) != id(y)
id(x) != id(10)"""
print("x's id after :")
print(id(x))
print("n's id after :")
print(id(n))
print("10's id:")
print(id(10))
"""The object in which x was tagged is changed. object 10 was never modified. Immutable objects doesn’t
allow modification after creation"""


#B


class MutableInt(object):
    def __init__(self, value):
        self.value = value

    def __call__(self, value):
        self.value = value

    def __str__(self):
        return str(self.value)

"""
w = MutableInt(8)
print(w)
print(id(w))
w.__call__(9)
print(id(w))
"""

# Q6


def average(u, o):
    return (u+o)/2


def improve(update, close, guess=1):
    while not close(guess):
        guess = update(guess)
    return guess


def approx_eq(u, o, tolerance=1e-3):
    return abs(u - o) < tolerance


def sqr(a):
    def sqr_update(u):
        return average(u, a/u)

    def sqr_close(u):
        return approx_eq(u * u, a)
    return improve(sqr_update, sqr_close)


"""print(sqr(256))"""

# Q7


#A

list1 = [1, 2, 13, 4]


def q7a(l):
    return list(filter(lambda z: z < 12, map(lambda z: z*z, l)))


print(q7a(list1))

#B

cameras = {'LEQ2B': ('Nikon', 3.68, 4995), 'CAE5D424105': ('Cannon', 3.40, 3899)}


def q7b(dic):
    return max(map(lambda a: a[1], map(lambda a: dic[a], dic.keys())))


res = q7b(cameras)
print(res)


#C

def q7c(dic, maximum):
    return list(filter(lambda q: dic[q][1] == maximum, dic))


print(q7c(cameras, res))

