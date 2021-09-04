import os

os.system("cls")
string = input("enter:\n")
string2 = input("enter:\n")
os.system("cls")

for x in range(len(string)):
    if string[x] != string2[x]:
        print("False")
        exit()
print("True")