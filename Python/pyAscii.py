import pyfiglet
import random

words = ["poopy", "fecal", "excrement", "peepee", "urine", "urination", "farting", "flatulence", "gas", "breaking wind"]


phrase = words[random.randint(0, len(words) - 1)]

print(pyfiglet.figlet_format(phrase, font="standard"))