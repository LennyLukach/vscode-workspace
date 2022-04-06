from multiprocessing import Event
from operator import ne
import tkinter as tk
from turtle import right

root = tk.Tk()
root.title("Temperature App")
root.geometry("400x400")

enteredDegFahrenheit = tk.IntVar()
enteredDegCelsius = tk.IntVar()
enteredDegFahrenheitBot = tk.IntVar()
enteredDegCelsiusBot = tk.IntVar()


#? Widgets

ent_tempLeft = tk.Entry(root, textvariable=enteredDegFahrenheit, bd=4)
ent_tempRight = tk.Entry(root, textvariable=enteredDegCelsius, bd=4)
ent_tempRightBot = tk.Entry(root, textvariable=enteredDegFahrenheitBot, bd=4)
ent_tempLeftBot = tk.Entry(root, textvariable=enteredDegCelsiusBot, bd=4)

lbl_fahrenheit = tk.Label(root, text="Fahrenheit")
lbl_celsius = tk.Label(root, text="Celsius")

#? ---------

ent_tempLeft.place(relx=0.08, rely=0.3)
ent_tempRight.place(relx=0.92, rely=0.3, anchor=tk.NE)
ent_tempLeftBot.place(relx=0.08, rely=0.6)
ent_tempRightBot.place(relx=0.92, rely=0.6, anchor=tk.NE)

lbl_fahrenheit.place(relx=0.23, rely=0.26, anchor=tk.CENTER)
lbl_celsius.place(relx=0.23, rely=0.56, anchor=tk.CENTER)

def FahtoCel():
    if len(ent_tempLeft.get()) != 0:
        enterFah = enteredDegFahrenheit.get()
        int(enterFah)
        enterFah -= 32
        enterFah *= 5
        enterFah /= 9
        enteredDegCelsius.set(enterFah)
    else:
        enteredDegCelsius.set("")

def CeltoFah():
    if len(ent_tempLeftBot.get()) != 0:
        enterCel = enteredDegCelsiusBot.get()
        int(enterCel)
        enterCel *= 9
        enterCel /= 5
        enterCel += 32
        enteredDegFahrenheitBot.set(enterCel)
    else:
        enteredDegFahrenheitBot.set("")

def mainLoop():

    FahtoCel()
    CeltoFah()



    root.update()

ent_tempLeft.delete(0, tk.END)
ent_tempRight.delete(0, tk.END)
ent_tempLeftBot.delete(0, tk.END)
ent_tempRightBot.delete(0, tk.END)
while True:
    mainLoop()