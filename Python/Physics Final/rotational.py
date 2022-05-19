from cmath import pi
import numpy as np
import math
import os

# plot angular velocity / time

#? I = rotaional inertia / I = L/W ### kg * m^2
#? L = angular momentum/ L = mass * velocity * radius ### kgm^2/sec
#? W = anuglar velocity / W = deltaTheta/deltaTime ### rad/sec
#? deltaTheta = angular rotation ### sec

angularRotation = np.rad2deg(2 * pi) #! deg
deltaTime = 7 #! sec
mass = 9.12 #! kg
radius = 8 #! meters
Vi = 1.23 #! m/s

angularVelocity = angularRotation/deltaTime

angularMomentum = mass * Vi * radius

rotationalInertia = angularMomentum/angularVelocity

os.system("clear")
time_increment2 = deltaTime / 11

for x in range(10):
    pass


print(f"{round(rotationalInertia, 2)}kg*m^2c")