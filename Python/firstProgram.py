import random
import time
import os
# TODO: Do something
# * Important Information.
# ? Why is this so cool?
# ! Critical Error causing crash starts here!
#  //// forget this line of code for now!
os.system("cls")

for x in range(5):
    print("Hello {0} world!".format(x))
    time.sleep(1)
    os.system("cls")

print("POGG")
os.system('cls')
amount = int(input("Enter a number:\n"))


def countMoney(x):
    for x in range(x):
        print("You have {0} dollars!".format(x + 1))
        time.sleep(0.3)


countMoney(amount)

numOccur = {'1': 0, '2': 0, '3': 0, '4': 0, '5': 0}
cont = True

while cont:
    x = random.randint(1, 5)
    if x == 1:
        numOccur['1'] = 1
        print("hi1")
    if x == 2:
        numOccur['2'] = 1
        print("hi2")
    if x == 3:
        numOccur['3'] = 1
        print("hi3")
    if x == 4:
        numOccur['4'] = 1
        print("hi4")
    if x == 5:
        numOccur['5'] = 1
        print("hi5")
    time.sleep(0.05)
    print(numOccur.items())
    amt = 0
    for x in numOccur:
        curNum = numOccur[x]
        if curNum != 1:
            print("{0} IS NOT 1".format(x))
            break
        else:
            amt += 1
    if amt == 5:
        cont = False
for x in numOccur:
    print(x)
