from usefulCustomImports import *

clearScr()

sentence = input("Enter sentence:\n")
wordArr = sentence.split()
countLength = 0

for word in wordArr:
    countLength += 1

print(countLength)