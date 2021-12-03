import time
from usefulCustomImports import *
import os

for x in range(os.get_terminal_size().lines - 3, 0, -1):
    clearScr()
    print("\n" * x)
    print(" " * int(((os.get_terminal_size().columns - 21) / 2)) + "Made by: Lenny")
    time.sleep(1)