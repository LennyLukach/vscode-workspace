import os

def clearScr():
    sysName = os.name
    if sysName == 'posix':
        os.system("clear")
    elif sysName == 'nt':
        os.system("cls")


def printOutTheWorld():
    print("hello")