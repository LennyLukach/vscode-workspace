#pygame window

import pygame
from pygame import mouse
from pygame.locals import *
import sys
import random
import os
import simpleaudio as sa

def playsound(file_path):
    wave_obj = sa.WaveObject.from_wave_file(file_path)
    play_obj = wave_obj.play()
    play_obj.wait_done()


cwd = "/Users/llukach/VSCode/vscode-workspace/Python/App7/"

#VARS
screenX = 840
screenY = 640

squareSizeY = screenY / 3
squareSizeX = screenX / 3
squareColor = (255, 255, 255)

startMenu = True
tutDone = False

cwd = "/Users/llukach/VSCode/vscode-workspace/Python/App7/"


played = None

pygame.init()
screen = pygame.display.set_mode((screenX, screenY))
pygame.display.set_caption("Sound Board")


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

def onClick(buttons, num):
    NOTES = ["do.wav", "re.wav", "mi.wav", "fa.wav", "kick.wav", "sol.wav", "la.wav", "ti.wav", "do-octave.wav"]
    pygame.draw.rect(screen, (0, 255, 0), buttons[num - 1])
    pygame.display.update()
    note = NOTES[num - 1]
    playsound(os.path.join(cwd, note))
    #pygame.time.delay(10)

def detectClickO(order):
    mpx = mouse.get_pos()[0]
    mpy = mouse.get_pos()[1]
    if mpx >= 0 and mpx <= 280 and mpy >= 0 and mpy <= 213:
        onClick(buttons, 1)
        if order == 1:
            return order
    elif mpx >= 290 and mpx <= 570 and mpy >= 0 and mpy <= 213:
        onClick(buttons, 2)
        if order == 2:
            return order
    elif mpx >= 580 and mpx <= 840 and mpy >= 0 and mpy <= 213:
        onClick(buttons, 3)
        if order == 3:
            return order
    elif mpx >= 0 and mpx <= 280 and mpy >= 223 and mpy <= 436:
        onClick(buttons, 4)
        if order == 4:
            return order
    elif mpx >= 290 and mpx <= 570 and mpy >= 223 and mpy <= 436:
        onClick(buttons, 5)
        if order == 5:
            return order
    elif mpx >= 580 and mpx <= 840 and mpy >= 223 and mpy <= 436:
        onClick(buttons, 6)
        if order == 6:
            return order
    elif mpx >= 0 and mpx <= 280 and mpy >= 446 and mpy <= 640:
        onClick(buttons, 7)
        if order == 7:
            return order
    elif mpx >= 290 and mpx <= 570 and mpy >= 446 and mpy <= 640:
        onClick(buttons, 8)
        if order == 8:
            return order
    elif mpx >= 580 and mpx <= 840 and mpy >= 446 and mpy <= 640:
        onClick(buttons, 9)
        if order == 9:
            return order

order = random.randint(1,9)
getSquareLocations(squareSizeX, squareSizeY)
while True:
    if startMenu and not tutDone:
        buttons = drawSquares(squareColor, squareSizeX, squareSizeY)
        pygame.display.update()
        print("Welcome to the soundboard app. Enter any key to begin.")
        input("")
        print("For the tutorial scene, you will be given a random note to play and you must play the corresponding note. Enter to continue.")
        input()
        print(f"Play note {order}")
        startMenu = False
    if not startMenu and tutDone:
        buttons = drawSquares(squareColor, squareSizeX, squareSizeY)
        pygame.display.update()
    for event in pygame.event.get():
        if event.type == QUIT:
            pygame.quit()
            sys.exit()
        if event.type == pygame.MOUSEBUTTONDOWN:
            if not startMenu:
                played = detectClickO(order)
                if not tutDone:
                    if played == order:
                        print("Good job! Feel free to make some music!")
                        tutDone = True
                    else:
                        print("Oops! Looks like you played the wrong note. Restart the program to try again.")
                        pygame.quit()
                        sys.exit()
