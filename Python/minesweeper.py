from usefulCustomImports import *
import random

clearScr()
bSize = 5
coverIcon = "-"
bombIcon = "B"

class Bomb:
    def __init__(self, pos: tuple):
        self.pos = pos

class Cell:
    def __init__(self, pos: tuple, value, shown, icon):
        self.pos = pos
        self.value = value
        self.shown = shown
        self.icon = icon

board = [[None for x in range(bSize)] for y in range(bSize)]

for x in range(bSize):
    for y in range(bSize):
        cell = Cell((x, y), 0, False, coverIcon)
        board[x][y] = cell



def printBoard(board):
    for x in board:
        row = ""
        for y in x:
            row += "  " + y
        print(row)

def createBomb(board, maxBombs):
    numBombs = random.randint(1, maxBombs)
    bombArr = [] * numBombs
    for x in range(numBombs):
        bombX = random.randint(0, bSize - 1)
        bombY = random.randint(0, bSize - 1)
        newBomb = Bomb((bombX, bombY))
        if len(bombArr) > 0:
            for bomb in bombArr:
                while newBomb.pos == bomb.pos:
                    bombX = random.randint(0, bSize - 1)
                    bombY = random.randint(0, bSize - 1)
                    newBomb.pos = (bombX, bombY)
        bombArr.append(newBomb)

    for bomb in bombArr:
        board[bomb.pos[0]][bomb.pos[1]] = bombIcon

    return board
    
        


printBoard(board)
createBomb(board, 5)
print("\n")
printBoard(board)