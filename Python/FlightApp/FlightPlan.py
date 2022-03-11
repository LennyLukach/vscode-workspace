# Lenny Lukach
# Data Structures and Algorithms
# Flight Plan UI
# Date Made: 2/26/22
# Date Modified: 2/26/22
#? preview plan
#? create plan
#? write plan to a file and provide file path
from audioop import add
import tkinter as tk
from turtle import back
from typing import Text
from PIL import ImageTk, Image

debugMode = True

root = tk.Tk()
root.title("Flight Plan")
root.geometry("800x800")
root.configure(bg="lightgray")
ws = root.winfo_screenwidth()
hs = root.winfo_screenheight()
h = 800
w = 800
x = (ws/2) - (w/2)
y = (hs/2) - (h/2)

root.geometry('%dx%d+%d+%d' % (w, h, x, y))

#Vars
destList = ["Start"]
destListNums = []
destListEntries = []


#Classes
class Location:
    def __init__(self, name, pos):
        self.name = name
        self.pos = pos

#Functions
def temp_text(e):
       txt_addDest.delete(0, "end")

def addDestination():
    destList.append(txt_addDest.get())
    txt_addDest.delete(0, "end")
    if debugMode:
        print(destList)

def genList():
    btn_getVals = tk.Button(root, text="Save Order", command=getVals)
    btn_createPlan = tk.Button(root, text="Create Plan")
    btn_getVals.place(x=50, y=120)
    btn_createPlan.place(x=300, y=200)
    destListEntries.clear()
    addY = 0
    for i in range(len(destList)):
        exec('tk.Label%d=tk.Label(root,text="%s")\ntk.Label%d.place(x=80, y=150+addY)' % (i,destList[i],i))
        if i > 0:
            new_entry = tk.Entry(root, width=2)
            new_entry.place(x=50, y=150+addY)
            destListEntries.append(new_entry)
        addY += 27   

def getVals():
    destListNums.clear()
    for entry in destListEntries:
        destListNums.append(entry.get())
    if debugMode:
        print(destListNums)

#?Images
background_image = ImageTk.PhotoImage(Image.open("Python\FlightApp\planeLanding.jpg.png"))
background_lbl = tk.Label(root, image=background_image).place(x=0, y=0)

#?Create Widgets
txt_addDest = tk.Entry(root, text="asdf", bd=4)
txt_addDest.insert(0, "Enter Destination")
txt_addDest.bind("<FocusIn>", temp_text)

lbl_enterDes = tk.Label(root, text="Enter Desired Destination")

btn_addDes = tk.Button(root, text="Add", command=addDestination)
btn_genList = tk.Button(root, text="Generate List", command=genList)

#?Add Widgets
txt_addDest.place(relx=0.052, rely=0.075)

lbl_enterDes.place(relx=0.05, rely=0.045)

btn_addDes.place(relx=0.23, rely=0.045)
btn_genList.place(relx=0.23, rely=0.075)










root.mainloop()
