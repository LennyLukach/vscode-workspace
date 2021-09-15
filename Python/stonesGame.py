import random
import time
import os
from usefulCustomImports import clearScr


def printBoard(stoneCount):
    print (f"({stoneCount}) " + " ♥" * stoneCount)

clearScr()

print("Select a gamemode:")
gameModeSelect = int(input("1. Player versus Player\n2. Player versus AI\n"))

turnPlayer = 1

if gameModeSelect == 2:
    clearScr()
    print ("How do you want to start the game?:")
    orderStartSelect = int(input("1. Go First\n2. Go Second\n3. Rock / Paper / Scissors\n4. Random\n"))
    clearScr()
    if orderStartSelect == 1:
        turnPlayer = 1
    elif orderStartSelect == 2:
        turnPlayer = 2
    elif orderStartSelect == 3:
        rpsp = int(input("1. Rock\n2. Paper\n3. Scissors\n"))
        rpsa = random.randint(1, 3)
        clearScr()
        if rpsp == 1:
            print ("Player chose rock")
        elif rpsp == 2:
            print ("Player chose paper")
        elif rpsp == 3:
            print ("Player chose scissors")
        if rpsa == 1:
            print ("AI Chose rock")
        elif rpsa == 2:
            print ("AI chose paper")
        elif rpsa == 3:
            print ("AI chose scissors")
        
        if rpsp == 1 and rpsa == 2:
            turnPlayer = 2
            print ("AI wins")
        elif rpsp == 1 and rpsa == 3:
            turnPlayer = 1
            print ("Player wins")
        elif rpsp == 2 and rpsa == 3:
            turnPlayer = 2
            print ("AI wins")
        elif rpsp == 2 and rpsa == 1:
            turnPlayer = 1
            print ("Player wins")
        elif rpsp == 3 and rpsa == 1:
            turnPlayer = 2
            print ("AI wins")
        elif rpsp == 3 and rpsa == 2:
            turnPlayer = 1
            print ("Player wins")
        time.sleep(3.5)

    elif orderStartSelect == 4:
        tempVal = random.randint(1,2)
        if tempVal == 1:
            turnPlayer = 1
        else:
            turnPlayer = 2

clearScr()

stoneCount = random.randint(10, 25)

if gameModeSelect == 1:
    while True:
        if turnPlayer == 1:
            printBoard(stoneCount)
            minusStone = int(input("How many stones will player 1 remove? (1-3)\n"))
            stoneCount -= minusStone
            if stoneCount == 0:
                clearScr()
                print ("Player 1 removed all stones. Player wins!")
                break
            print (f"Player 1 removed {minusStone} stone(s).")
            turnPlayer = 2
        if turnPlayer == 2:
            printBoard(stoneCount)
            minusStone = int(input("How many stones will player 2 remove? (1-3)\n"))
            stoneCount -= minusStone
            if stoneCount == 0:
                clearScr()
                print ("Player 2 removed all stones. Player wins!")
                break
            print (f"Player 2 removed {minusStone} stone(s).")
            turnPlayer = 1

elif gameModeSelect == 2:
    while True:
        if turnPlayer == 1:
            printBoard(stoneCount)
            minusStone = int(input("How many stones will you remove? (1-3)\n"))
            stoneCount -= minusStone
            if stoneCount == 0:
                clearScr()
                print ("Player removed all stones. Player wins!")
                break
            print (f"Player removed {minusStone} stone(s).")
            turnPlayer = 2
        if turnPlayer == 2:
            printBoard(stoneCount)
            minusStone = stoneCount % 4
            if minusStone == 0:
                minusStone += random.randint(1, 3)
            stoneCount -= minusStone
            if stoneCount == 0:
                clearScr()
                print ("AI removed all stones. Player wins!")
                break
            print (f"AI removed {minusStone} stone(s).")
            turnPlayer = 1