import functools
import datetime
# Q1
import words as words


def sum_lst(lst):
    total = functools.reduce(lambda x, y: x+y, lst)
    return total


tot = sum_lst([0, 2, 4, 6, 8, 10])
print(tot)


# Q2

while True:
    number = int(input("Please Enter a Number:"))
    if number < 0:
        break
    else:
        if number % 2 == 0:
            print("even")
        else:
            print("odd")
print("all done")


# Q3
# A + B

def my_decorator(func):
    def tran(*args, **kwargs):
        func(*args, **kwargs)
        now = datetime.datetime.now()
        print("transference was done in:")
        print(now.year, now.month, now.day, now.hour, now.minute, now.second)
    return tran


class Account(object):
    def __init__(self, name, account_number, balance, frame=1500):
        self.name = name
        self.account_number = account_number
        self.balance = balance
        self.frame = frame

    def deposit(self, amount):
        self.balance += amount
        return amount

    def withdraw(self, amount):
        if self.balance - amount > 0:
            self.balance -= amount
            return amount
        else:
            print("You can not withdraw because your balance is 0 !!!")

    def get_balance(self):
        return self.balance

    @my_decorator
    def transference(self, account1, amount):
        account1.balance += amount
        self.balance -= amount


a1 = Account('John Olsson', '19371554951', 2000, 4000)
a2 = Account('Liz Olsson',  '19371564761', 20000)
a3 = Account('Ofir cohen',  '19471564761', 20000)

#a1.withdraw(4000)
#a1.deposit(3000)
#print("the balance of a1 is")
#print(a1.get_balance())
#a1.transference(a2, 1000)
#print("the balance of a1 is ")
#print(a1.get_balance())
#print("the balance of a2 is ")
#print(a2.get_balance())

# C
accounts = [a1, a2, a3]


def bacount(listac):
    count = 0
    for i in listac:
        count = count + i.get_balance()
    yield count


for j in bacount(accounts):
    print(j)

#4


def q4(account, other_account):
    name_ls = account.__dir__()[6:10]
    print(name_ls)
    # deposit
    func = getattr(account, name_ls[0])
    func(600)
    # withdraw
    func = getattr(account, name_ls[1])
    func(300)
    # get_balance
    func = getattr(account, name_ls[2])
    func()
    # transference
    func = getattr(account, name_ls[3])
    func(other_account, 100)


q4(a1, a2)


#5


def words(file):
    for line in file:
        for word1 in line.split():
            if 'e' not in word1:
                yield word1


with open('words.txt', 'r') as wordFile:
    for word in words(wordFile):
            print(word)



