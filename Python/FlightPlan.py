# Lenny Lukach
# Data Structures and Algorithms
# Flight Plan UI
# Date Made: 2/26/22
# Date Modified: 2/26/22

from audioop import add
import tkinter as tk
from typing import Text

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


#Functions
def temp_text(e):
       txt_addDest.delete(0, "end")

def addDestination():
    destList.append(txt_addDest.get())
    txt_addDest.delete(0, "end")
    print(destList)

def genList():
    addY = 0
    destListEntries = []
    for i in range(len(destList)):
        exec('tk.Label%d=tk.Label(root,text="%s")\ntk.Label%d.place(x=80, y=150+addY)' % (i,destList[i],i))
        if i > 0:
            new_entry = tk.Entry(root, width=2)
            new_entry.place(x=50, y=150+addY)
            destListEntries.append(new_entry)
            #exec('tk.Entry%d=tk.Entry(root, text="%s", width=2)\ntk.Entry%d.place(x=50, y=150+addY)' % (i, "#", i))
        addY += 27     

#Create Widgets
txt_addDest = tk.Entry(root, text="asdf", bd=5)
txt_addDest.insert(0, "Enter Destination")
txt_addDest.bind("<FocusIn>", temp_text)

lbl_enterDes = tk.Label(root, text="    Enter Desired Destination     ")

btn_addDes = tk.Button(root, text="Add", command=addDestination)
btn_genList = tk.Button(root, text="Generate List", command=genList)
btn_getVals

#Add Widgets
txt_addDest.place(x=40, y=50)

lbl_enterDes.place(x=40, y=30)

btn_addDes.place(x=173, y=50)
btn_genList.place(x=210, y=40)













root.mainloop()
