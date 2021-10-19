import math
import random
from fractions import Fraction
import os

# TODO add negative / positive values

degList  = [0, 30, 45, 60, 90, 120, 135, 150, 180, 210, 225, 240, 270, 300, 315, 330, 360]
radList  = []
piList = []
trigs = ["sin", "cos", "tan", "csc", "sec", "cot"]
os.system("clear")

for deg in degList:
    radList.append(round(math.radians(deg), 2))
    newPi = (Fraction(deg / 180).limit_denominator())
    newPi = str(newPi)
    for char in newPi:
        if char == "/":
            indexA = newPi.index(char)
            newerPi = newPi[:indexA] + "pi"  + newPi[indexA:]
            piList.append(newerPi)


for x in range(20):
    isRad = random.randint(0, 1)
    if isRad == 0:
        trigVal = degList[random.randrange(0, len(degList))]
    else:
        trigVal = piList[random.randrange(0, len(piList))]

    isPos = random.randint(0, 1)

    if isPos == 0:
        integer = " -"
    else:
        integer = " "

    trigFunc = trigs[random.randrange(0, len(trigs))]

    if isRad == 0:
        print (str(x + 1) + ". " + str(trigFunc) + integer + str(trigVal) +  "°")
    else:
        print (str(x + 1) + ". " + str(trigFunc) + integer + str(trigVal))