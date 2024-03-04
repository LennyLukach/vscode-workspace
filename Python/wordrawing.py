import turtle

root = turtle.Screen()
root.title("Writing")
root.screensize(4000, 500)

pen = turtle.Turtle()
pen.color("blue")
pen.pensize(10)
pen.speed(4)


# start
def start() :
    pen.hideturtle()
    pen.pu()
    pen.setx(-1800)
    pen.lt(90)
    pen.pd()
    pen.showturtle()


# letters

def a() :
    pen.rt(30)
    pen.forward(150)
    pen.rt(120)
    pen.forward(80)
    pen.rt(120)
    pen.forward(80)
    pen.backward(80)
    pen.lt(120)
    pen.forward(70)
    pen.pu()
    pen.lt(60)
    pen.fd(60)
    pen.lt(90)
    pen.pd()


def b() :
    pen.forward(150)
    pen.rt(90)
    for x in range(25) :
        pen.forward(5)
        pen.rt(7)
    pen.lt(180)
    for x in range(20) :
        pen.forward(6)
        pen.rt(10)
    pen.fd(5)
    pen.pu()
    pen.lt(195)
    pen.fd(90)
    pen.pd()
    pen.lt(90)


def c() :
    pen.pu()
    pen.rt(90)
    pen.fd(60)
    pen.lt(180)
    pen.pd()
    for x in range(46) :
        pen.fd(6)
        pen.rt(4)
    pen.pu()
    pen.rt(86)
    pen.fd(170)
    pen.lt(90)
    pen.fd(70)
    pen.lt(90)
    pen.pd()

def d():
    pen.fd(150)
    pen.rt(90)
    for x in range(46):
        pen.fd(5)
        pen.rt(4)
    pen.pu()
    pen.lt(94)
    pen.fd(7)
    pen.lt(90)
    pen.fd(120)
    pen.lt(90)
    pen.pd()

def e():
    pen.forward(150)
    pen.rt(90)
    pen.forward(60)
    pen.backward(60)
    pen.rt(90)
    pen.forward(75)
    pen.lt(90)
    pen.forward(60)
    pen.backward(60)
    pen.rt(90)
    pen.forward(75)
    pen.lt(90)
    pen.forward(60)
    pen.pu()
    pen.forward(70)
    pen.lt(90)
    pen.pd()

def f():
    pass




def l() :
    pen.forward(150)
    pen.backward(150)
    pen.rt(90)
    pen.forward(60)
    pen.pu()
    pen.forward(60)
    pen.lt(90)
    pen.pd()

start()

pen.setheading(90)

d()
a()
b()

d()
a()
d()

delay = input()
