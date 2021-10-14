from typing import Collection
import pygame
import sys
from pygame import mouse
from pygame.constants import MOUSEBUTTONDOWN, MOUSEBUTTONUP, QUIT
import math

from pygame.draw import circle
from pygame.sprite import collide_circle


pygame.init()

SCREEN = pygame.display.set_mode((1280, 800))
pygame.display.set_caption("Drawing Shapes")

#! COLORS
RED = (255, 0, 0)
ORANGE = (255, 137, 0)
YELLOW = (255, 255, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
PURPLE = (127, 0, 255)
PINK = (255, 0, 127)
WHITE = (255, 255, 255)

#! Variables:
left_click = False
last_pos = None
pen_color = WHITE

optionBook = [RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE, PINK, WHITE]

#red, orange, yellow, green, blue, indigo, violet, white, eraser(black)
#small pen, big pen, clear screen

pygame.draw.rect(SCREEN, (155, 155, 155), (0, 725, 1280, 75))

pygame.draw.circle(SCREEN, RED, (40, 762.5), 25) 
pygame.draw.circle(SCREEN, ORANGE, (109 * 1 + 40, 762.5), 25) 
pygame.draw.circle(SCREEN, YELLOW, (109 * 2 + 40, 762.5), 25) 
pygame.draw.circle(SCREEN, GREEN, (109 * 3 + 40, 762.5), 25) 
pygame.draw.circle(SCREEN, BLUE, (109 * 4 + 40, 762.5), 25) 
pygame.draw.circle(SCREEN, PURPLE, (109 * 5 + 40, 762.5), 25) 
pygame.draw.circle(SCREEN, PINK, (109 * 6 + 40, 762.5), 25) 
pygame.draw.circle(SCREEN, WHITE, (109 * 7 + 40, 762.5), 25) 
pygame.draw.circle(SCREEN, RED, (109 * 8 + 40, 762.5), 25) 
pygame.draw.circle(SCREEN, RED, (109 * 9 + 40, 762.5), 25) 
pygame.draw.circle(SCREEN, RED, (109 * 10 + 40, 762.5), 25)
pygame.draw.circle(SCREEN, RED, (1240, 762.5), 25)

while True:
    for event in pygame.event.get():
        mouse_pos_x, mouse_pos_y = pygame.mouse.get_pos()
        if event.type == QUIT:
            pygame.quit()
            sys.exit()
        elif event.type == MOUSEBUTTONDOWN:
            if event.button == 1:
                left_click = True
                if math.sqrt(((mouse_pos_x % 109 - 40)**2) + ((mouse_pos_y - 762.5)**2)) < 25:
                    pen_color = SCREEN.get_at(pygame.mouse.get_pos())


        elif event.type == MOUSEBUTTONUP:
            if event.button == 1:
                left_click = False
                last_pos = None
        if left_click and mouse_pos_y < 725:
            mouse_position = pygame.mouse.get_pos()
            if last_pos is not None:
                pygame.draw.line(SCREEN, pen_color, last_pos, mouse_position, 2)
            last_pos = mouse_position
        

    pygame.display.update()