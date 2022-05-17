import matplotlib.pyplot as plt

fig = plt.figure()
ax = fig.add_subplot(1, 1, 1)

x = [0, 1, 2, 3]
y = [3, 2, 1, 0]

points = ax.scatter(x, y)
print(points.get_offsets().data)

plt.show()