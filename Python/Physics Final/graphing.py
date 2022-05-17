from asyncio.windows_events import NULL
from cmath import nan
from contextlib import aclosing
import graphlib
from cv2 import solve, sqrt
import matplotlib.pyplot as plt
import numpy as np

#? CONSTANTS
INITIAL_VELOCITY = 4
TIME = 14
ACCELERATION = 1.23


#? Vars
Vi = INITIAL_VELOCITY
time = TIME
accel = ACCELERATION
distance = 0
Vf = 0

#? Lists
graph_points = []


#? Solve for time
def kinematic_Vf(Vi, accel, time):
    Vf = Vi + (accel * time)
    return Vf


#? Solve for distance
def kinematic_distance(Vf, Vi, time):
    distance = (1/2) * (Vf + Vi) * time
    return distance


#? Create points
for x in range(time):
    finalV = kinematic_Vf(Vi, accel, x + 1)
    dist = kinematic_distance(Vi, finalV, x + 1)
    graph_points.append((finalV, dist, x + 1))



#? Print organized points
'''
for point in range(len(graph_points)):
    velocity = graph_points[point][0]
    dist = graph_points[point][1]
    time = graph_points[point][2]
    print(f"Velocity: {velocity} Distance: {dist} Time: {time}")
'''