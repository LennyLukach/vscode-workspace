
import numpy as np


def cartesianMath(x, y, op):

    if op == 'add':
        return np.add.outer(x, y)
    elif op == 'mult':
        return np.multiply.outer(x, y)
    else:
        raise ValueError('op must be either add or mult')
    

print(cartesianMath(np.array([1,2,3]), np.array([4,5,6]), input()))
