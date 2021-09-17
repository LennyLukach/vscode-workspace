myList = [1, 2, 3, 4, 5, "uzi", 7, "trippie", 9, 10, 11, 12, 13, 14, 15, 16, 17]
count = len(myList) / 2
count = int(count)
print(myList)
for x in range(count):
    temp = myList[len(myList) - (x + 1)]
    myList[len(myList) - (x + 1)] = myList[x]
    myList[x] = temp

print(myList)