from typing import Collection
import pygame
import sys
from pygame import mouse
from pygame.constants import MOUSEBUTTONDOWN, MOUSEBUTTONUP, QUIT


pygame.init()

SCREEN = pygame.display.set_mode((1280, 800))
pygame.display.set_caption("Drawing Shapes")
RED = (255, 0, 0)
left_click = False

#red, orange, yellow, green, blue, indigo, violet, white, eraser(black)
#small pen, big pen, clear screen

pygame.draw.rect(SCREEN, (155, 155, 155), (0, 725, 1280, 75))

pygame.draw.circle(SCREEN, RED, (40 * 1, 762.5), 25) 
pygame.draw.circle(SCREEN, RED, (100 * 1 + 60, 762.5), 25) 
pygame.draw.circle(SCREEN, RED, (100 * 2 + 60, 762.5), 25) 
pygame.draw.circle(SCREEN, RED, (100 * 3 + 60, 762.5), 25) 
pygame.draw.circle(SCREEN, RED, (100 * 4 + 60, 762.5), 25) 
pygame.draw.circle(SCREEN, RED, (100 * 5 + 60, 762.5), 25) 
pygame.draw.circle(SCREEN, RED, (100 * 6 + 60, 762.5), 25) 
pygame.draw.circle(SCREEN, RED, (100 * 7 + 60, 762.5), 25) 
pygame.draw.circle(SCREEN, RED, (100 * 8 + 60, 762.5), 25) 
pygame.draw.circle(SCREEN, RED, (100 * 9 + 60, 762.5), 25) 
pygame.draw.circle(SCREEN, RED, (100 * 10 + 60, 762.5), 25)
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
        elif event.type == MOUSEBUTTONUP:
            if event.button == 1:
                left_click = False
        if left_click:
            pygame.draw.ellipse(SCREEN, RED, (mouse_pos_x, mouse_pos_y, 5, 5))

    pygame.display.update()