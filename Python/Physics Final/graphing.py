from dis import dis
import math
from re import A
from turtle import distance
import matplotlib.pyplot as plt
import numpy as np

'''
How to get an A+
Cylinder, hollow cylinder
Rotational inertia
Rotational dynamics
'''
sind = lambda degrees: round(np.sin(np.deg2rad(degrees)), 2)
cosd = lambda degrees: round(np.cos(np.deg2rad(degrees)), 2)

distances = []
heights = []
pointSpots = []
speedX = []
speedY = []

launch_angle = 45
Vi = 15
accely = -9.8

Viy = Vi * sind(launch_angle)
Vfy = -Viy
Vix = Vi * cosd(launch_angle)
delta_V = Vfy - Viy

time = round(delta_V/accely, 2)
time_increment = time/10

'''
Viy = Vi * sind(launch_angle)
Vfy = -Viy
accely = -9.8
delta_V = Vfy - Viy
time = round(delta_V/accely, 2)

Vix = Vi * cosd(launch_angle)
Sx = round(Vix * time, 2)
'''

for x in range(11):
    Sx = round(Vix * time_increment * (x), 2)
    Sy = round(Viy * time_increment * (x) + (1/2) * accely * pow((time_increment * (x)), 2), 3)
    distances.append(Sx)
    heights.append(Sy)
    speedX.append(Vix)
    speedY.append(Viy)

print(f"Distance: {distance}")
print(f"Height: {heights}")

fig, ax = plt.subplots()

points = ax.scatter(distances, heights)
ax.plot(distances, heights)

ax.set_title("Projectile Trajectory Graph")
ax.set_xlabel("Distance")
ax.set_ylabel("Height")

for point in range(11):
    pointSpots.append((points.get_offsets().data[point][0], points.get_offsets().data[point][1]))

for point in pointSpots:
    plt.annotate(f"   Vx:{1}m/s\n   Vy:{2}m/s\n   X:{point[0]}m\n   Y:{point[1]}m", point, horizontalalignment='left', verticalalignment='center') 

print(f"SpeedX: {speedX}")
print(f"SpeedY: {speedY}")


plt.show()