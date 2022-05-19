from cmath import pi
import numpy as np
import math



#I = rotaional inertia / I = L/W : kg * m^2
#L = angular momentum/ L = mass * velocity * radius : kgm^2/sec
#W = anuglar velocity / W = deltaTheta/deltaTime : rad/sec
#deltaTheta = angular rotation : sec

angularRotation = np.rad2deg(2 * pi) # deg
deltaTime = 8 # sec
mass = 5 # kg
radius = 10 # meters
Vi = 10 # m/s

angularVelocity = angularRotation/deltaTime

angularMomentum = mass * Vi * radius

rotationalInertia = angularMomentum/angularVelocity

print(round(rotationalInertia, 2))