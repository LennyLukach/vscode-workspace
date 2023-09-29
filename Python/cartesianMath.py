# write function to do cartesian multiplication

import numpy as np

def cartesianMath(x, y, op):
    # x, y are 2D arrays
    # op is a string, either 'add' or 'mult'
    # returns a 3D array of shape (x.shape[0], y.shape[0], x.shape[1])
    # where the last dimension is the result of the operation
    # on the corresponding elements of x and y
    if op == 'add':
        return np.add.outer(x, y)
    elif op == 'mult':
        return np.multiply.outer(x, y)
    else:
        raise ValueError('op must be either add or mult')
    

cartesianMath(np.array([1,2,3]), np.array([4,5,6]), 'add')