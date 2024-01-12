import math


n = 1000000

notations = [(n * n) * math.log(n), n, "big infinity 2^n", n^3, 2^n, pow(n, 1.5), n * math.log(n), 37, pow(n, 1/2), n^2, "small infinity 2^2/n", n * math.log(n) * math.log(n)]

for i in range(len(notations)):
    print(i + 1, notations[i])

#fsgdf