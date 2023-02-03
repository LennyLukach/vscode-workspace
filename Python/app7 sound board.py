#pygame window

import pygame
from pygame import mouse
from pygame.locals import *
import sys
import random

#VARS
screenX = 840
screenY = 640

squareSizeY = screenY / 3
squareSizeX = screenX / 3
squareColor = (255, 255, 255)

pygame.init()
screen = pygame.display.set_mode((screenX, screenY))
pygame.display.set_caption("Sound Board")

startMenu = True

order = []

#FUNCTIONS

def getSquareLocations(squareSizeX, squareSizeY):
    locations = [
        (0, 0, squareSizeX, squareSizeY),
        (squareSizeX + 10, 0, squareSizeX, squareSizeY),
        (squareSizeX * 2 + 20, 0, squareSizeX, squareSizeY),
        (0, squareSizeY + 10, squareSizeX, squareSizeY),
        (squareSizeX + 10, squareSizeY + 10, squareSizeX, squareSizeY),
        (squareSizeX * 2 + 20, squareSizeY + 10, squareSizeX, squareSizeY),
        (0, squareSizeY * 2 + 20, squareSizeX, squareSizeY),
        (squareSizeX + 10, squareSizeY * 2 + 20, squareSizeX, squareSizeY),
        (squareSizeX * 2 + 20, squareSizeY * 2 + 20, squareSizeX, squareSizeY)
    ]

    return locations

def drawSquares(squareColor, squareSizeX, squareSizeY):
    buttons = []
    for x in range(9):
        button = pygame.draw.rect(screen, squareColor, (getSquareLocations(squareSizeX, squareSizeY)[x]))
        buttons.append(button)
    return buttons

def addLevel(order):
    order.append(random.randint(1, 9))
    return order

def onClick(buttons, num):
    pygame.draw.rect(screen, (0, 255, 0), buttons[num - 1])
    pygame.display.update()
    pygame.time.delay(200)

def detectClick():
    mpx = mouse.get_pos()[0]
    mpy = mouse.get_pos()[1]
    if mpx >= 0 and mpx <= 280 and mpy >= 0 and mpy <= 213:
        onClick(buttons, 1)
    elif mpx >= 290 and mpx <= 570 and mpy >= 0 and mpy <= 213:
        onClick(buttons, 2)
    elif mpx >= 580 and mpx <= 840 and mpy >= 0 and mpy <= 213:
        onClick(buttons, 3)
    elif mpx >= 0 and mpx <= 280 and mpy >= 223 and mpy <= 436:
        onClick(buttons, 4)
    elif mpx >= 290 and mpx <= 570 and mpy >= 223 and mpy <= 436:
        onClick(buttons, 5)
    elif mpx >= 580 and mpx <= 840 and mpy >= 223 and mpy <= 436:
        onClick(buttons, 6)
    elif mpx >= 0 and mpx <= 280 and mpy >= 446 and mpy <= 640:
        onClick(buttons, 7)
    elif mpx >= 290 and mpx <= 570 and mpy >= 446 and mpy <= 640:
        onClick(buttons, 8)
    elif mpx >= 580 and mpx <= 840 and mpy >= 446 and mpy <= 640:
        onClick(buttons, 9)

getSquareLocations(squareSizeX, squareSizeY)
while True:
    if startMenu:
        pygame.time.delay(1200)
        startMenu = False
    if not startMenu:
        buttons = drawSquares(squareColor, squareSizeX, squareSizeY)
        pygame.display.update()
    for event in pygame.event.get():
        if event.type == QUIT:
            pygame.quit()
            sys.exit()
        if event.type == pygame.MOUSEBUTTONDOWN:
            if not startMenu:
                detectClick()
