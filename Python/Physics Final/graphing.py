from dis import dis
import math
from re import A
from turtle import distance
import matplotlib.pyplot as plt
import numpy as np
import os

'''
How to get an A+:

Cylinder, hollow cylinder
Rotational inertia
Rotational dynamics

'''
def clearScr():
    sysName = os.name
    if sysName == 'posix':
        os.system("clear")
    elif sysName == 'nt':
        os.system("cls")

clearScr()

sind = lambda degrees: (np.sin(np.deg2rad(degrees)))
cosd = lambda degrees: (np.cos(np.deg2rad(degrees)))

ball_distances = []
ball_heights = []
pointSpots = []
speedX = []
speedY = []
ball_energy = []

launch_angle = 30
Vi = 10
accely = -9.8

Viy = (Vi * sind(launch_angle))
Vfy = -Viy
Vix = round(Vi * cosd(launch_angle), 2)
delta_V = Vfy - Viy

time = round(delta_V/accely, 2)
time_increment = time/10


'''Viy = Vi * sind(launch_angle)
Vfy = -Viy
accely = -9.8
delta_V = Vfy - Viy
time = round(delta_V/accely, 2)

Vix = Vi * cosd(launch_angle)
Sx = round(Vix * time, 2)'''


for x in range(11):
    Sx = round(Vix * time_increment * (x), 2)
    Sy = round(Viy * time_increment * (x) + (1/2) * accely * pow((time_increment * (x)), 2), 2)
    ball_distances.append(Sx)
    ball_heights.append(Sy)
    Vfy = round(Viy + accely * time_increment * (x), 2)
    speedX.append(Vix)
    speedY.append(Vfy)
    Vf = math.sqrt(pow(Vfy, 2) + pow(Vix, 2))
    energy = round((1/2 * pow(Vf, 2)) + (Sy * accely), 2)
    ball_energy.append(energy)

fig, ax = plt.subplots()

points = ax.scatter(ball_distances, ball_heights, color="orange")
ax.plot(ball_distances, ball_heights)

ax.set_title("Ball Trajectory Graph")
ax.set_xlabel("Distance")
ax.set_ylabel("Height")

for point in range(11):
    pointSpots.append((points.get_offsets().data[point][0], points.get_offsets().data[point][1]))

count = 0
for point in pointSpots:
    plt.annotate(f"   Vx:{speedX[count]}m/s\n   Vy:{speedY[count]}m/s\n   X:{point[0]}m\n   Y:{point[1]}m\n   E:{ball_energy[count]}J", point, horizontalalignment='left', verticalalignment='top', fontsize=9) 
    count += 1



#print(f"SpeedX: {speedX}")
#print(f"SpeedY: {speedY}")
#print(f"Energy: {ball_energy}")
#print(f"Distance: {ball_distances}")
#print(f"Height: {ball_heights}")



'''
plt.figure()
plt.plot(ball_distances, ball_heights)
'''




plt.show()