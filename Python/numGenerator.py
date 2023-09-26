import random


def numGenerator(x):
    f = open("randomNumbers.txt", "w")
    for i in range(x):
        f.write(str(random.randint(1,100)) + "\n")
    f.close()
    return

numGenerator(10000)