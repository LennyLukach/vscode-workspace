# import module
import numpy as np
import matplotlib.pyplot as plt
  
# initialize x and y coordinates
x = [0.1, 0.2, 0.3, 0.4, 0.5]
y = [6.2, -8.4, 8.5, 9.2, -6.3]
  
# set the title of a plot
plt.title("Connected Scatterplot points with lines")
  
# plot scatter plot with x and y data
plt.scatter(x, y)
  
# plot with x and y data
plt.plot(x, y)

plt.show()