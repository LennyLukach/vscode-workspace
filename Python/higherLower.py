import random
import os

os.system("clear")
num = random.randint(1, 1000)
x = None
while x != num:
    x = int(input("Guess:\n"))
    if x > num:
        print("Lower")
    elif x < num:
        print("Higher")
    else:
        print("Correct")
        break