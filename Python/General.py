from asyncio.windows_events import NULL
from cmath import nan
from contextlib import aclosing
import graphlib
from cv2 import solve, sqrt
import matplotlib.pyplot as plt
import numpy as np

#? CONSTANTS
X_INITIAL_VELOCITY = 5
X_ACCELERATION = 1.2
Y_INITIAL_VELOCITY = 0
TIME = 4

#? Vars
x_Vi = X_INITIAL_VELOCITY
x_accel = X_ACCELERATION
y_Vi = Y_INITIAL_VELOCITY
y_accel = -9.8
time = TIME
x_distance = 0
y_height = 0
x_Vf = 0
y_Vf = 0

#? Lists
x_graph_points = []
y_graph_points = []
x_dists = []
y_heights = []
times = []


#? Solve for x_Vf
def kinematic_Vf(Vi, accel, time):
    Vf = Vi + (accel * time)
    return Vf


#? Solve for x_distance
def kinematic_distance(Vf, Vi, time):
    distance = (1/2) * (Vf + Vi) * time
    return distance


#? Create points
for x in range(time):
    x_Vf = kinematic_Vf(x_Vi, x_accel, x + 1)
    x_dist = kinematic_distance(x_Vi, x_Vf, x + 1)
    x_graph_points.append((x_Vf, x_dist, x + 1))
    y_Vf = kinematic_Vf(y_Vi, y_accel, x + 1)
    y_height = kinematic_distance(y_Vi, y_Vf, x + 1)
    y_graph_points.append((y_Vf, y_height, x + 1))

#? Print organized points
for point in range(len(x_graph_points)):
    x_velocity = x_graph_points[point][0]
    x_dist = x_graph_points[point][1]
    y_height = y_graph_points[point][1]
    time = x_graph_points[point][2]
    x_dists.append(x_dist)
    y_heights.append(y_height)
    times.append(time)
    #print(f"Velocity: {velocity} Distance: {dist} Time: {time}")

plt.scatter(x_dists, y_heights)
plt.tight_layout()
plt.show()