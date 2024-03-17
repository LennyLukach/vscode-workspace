import random

numRolls = int(input("Enter number of die rolls: "))

dieHistory = {"2": 0, "3": 0, "4": 0, "5": 0, "6": 0, "7": 0, "8": 0,"9": 0, "10": 0, "11": 0, "12": 0}

for x in range(numRolls):

    d1 = random.randint(1, 6)
    d2 = random.randint(1, 6)
    
    d3 = d1 + d2

    dieHistory[f"{d3}"] += 1

for x in range(len(dieHistory)):

    print(x + 2, " : ", dieHistory[f"{x + 2}"])
