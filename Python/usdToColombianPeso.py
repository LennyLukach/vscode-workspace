import os

os.system("cls")

while(True):

    try:
        x = int(input())
        os.system("cls")
        x = x * 3725.07
        x = round(x , 2)
        tempX = str(x)
        tempX = tempX.split(".")
        x = (format (int(tempX[0]), ',d') + "." + tempX[1])
        print ("$" + x)

    
    except ValueError:
        os.system("cls")
        print ("$" + x)
        continue
