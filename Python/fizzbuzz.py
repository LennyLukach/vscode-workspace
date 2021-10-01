import os
import time
import sys
import usefulCustomImports

os.system("cls")

print("\n\n\n")

for x in range(1, 101):
    if x % 3 == 0 and x % 5 == 0:
        print("fizzbuzz")
    elif x % 3 == 0:
        print("fizz")
    elif x % 5 == 0:
        print("buzz")
    else:
        print(x)
    time.sleep(0.08)