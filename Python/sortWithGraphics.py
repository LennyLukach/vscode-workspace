import random
from usefulCustomImports import *
import time
import sys
#cool

clearScr()

lineLengths = []
for y in range(3):
    sys.stdout.write("Creating random list")
    for x in range(3):
        time.sleep(0.2)
        sys.stdout.write(".")
        sys.stdout.flush()
    clearScr()

for y in range(3):
    sys.stdout.write("Printing random list")
    for x in range(3):
        time.sleep(0.2)
        sys.stdout.write(".")
        sys.stdout.flush()
    clearScr()


for i in range(40):
    lineLengths.append(random.randint(1, 25))
    print("-" * lineLengths[i])
    time.sleep(0.02)

time.sleep(3)

clearScr()
for y in range(3):
    sys.stdout.write("Starting sorting process")
    for x in range(3):
        time.sleep(0.2)
        sys.stdout.write(".")
        sys.stdout.flush()
    clearScr()


for i in range(len(lineLengths)):
    clearScr()
    for length in lineLengths:
        print("-" * length)
    time.sleep(0.08)
    for j in range(len(lineLengths) - 1):
        if lineLengths[j] > lineLengths[j + 1]:
            lineLengths[j], lineLengths[j+1] = lineLengths[j+1], lineLengths[j]
