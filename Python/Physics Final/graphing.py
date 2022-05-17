from dis import dis
import math
import matplotlib.pyplot as plt
import numpy as np

sind = lambda degrees: round(np.sin(np.deg2rad(degrees)), 3)
cosd = lambda degrees: round(np.cos(np.deg2rad(degrees)), 3)

distances = []
heights = []

launch_angle = 30
Vi = 10
accely = -9.8

Viy = Vi * sind(launch_angle)
Vfy = -Viy
Vix = Vi * cosd(launch_angle)
delta_V = Vfy - Viy

time = round(delta_V/accely, 2)
time_increment = round(time/10, 2)

'''
Viy = Vi * sind(launch_angle)
Vfy = -Viy
accely = -9.8
delta_V = Vfy - Viy
time = round(delta_V/accely, 2)

Vix = Vi * cosd(launch_angle)
Sx = round(Vix * time, 2)
'''

for x in range(10):
    Sx = round(Vix * time, 2)
    Sy = round(Viy * time, 2)
    distances.append(Sx)
    heights.append(Sy)

print(distances)