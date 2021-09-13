import turtle
import math
import random

# Creating screen
from turtle import Turtle

root = turtle.Screen()
root.bgcolor("black")
root.title("Space Invaders")
# Drawing pen
borderPen = turtle.Turtle()
borderPen.speed(0)
borderPen.pensize(3)
borderPen.color("white")
borderPen.penup()
borderPen.setposition(-300, -300)
borderPen.pendown()
for side in range(4) :
    borderPen.fd(600)
    borderPen.lt(90)
    borderPen.hideturtle()
# Creating the player
player = turtle.Turtle()
player.color("blue")
player.shape("triangle")
player.penup()
player.speed(0)
player.setposition(0, -292)
player.setheading(90)

player_speed = 15

# Choose a number of enemies
number_of_enemies = 5
# Create an empty list of enemies
enemies = []

# Add enemies to the list
for i in range(number_of_enemies) :
    # Creating the enemies
    enemies.append(turtle.Turtle())

for enemy in enemies :
    enemy.color("red")
    enemy.shape("circle")
    enemy.penup()
    enemy.speed(0)
    x = random.randint(-200, 200)
    y = random.randint(100, 250)
    enemy.setposition(x, y)

enemy_speed = 6

# Creating weapons for player
bullet = turtle.Turtle()
bullet.color("yellow")
bullet.shape("triangle")
bullet.penup()
bullet.hideturtle()
bullet.speed(0)
bullet.setheading(90)
bullet.shapesize(0.5, 0.5)

bullet_speed = 25

# Defining bullet states
# ready - ready to be shot
# fire - bullet is being fired
bullet_state = "ready"


# Creating movement for player
def move_left() :
    x = player.xcor()
    x -= player_speed
    if x < -280 :
        x = -280
    player.setx(x)


def move_right() :
    x = player.xcor()
    x += player_speed
    if x > 280 :
        x = 280
    player.setx(x)


def fire_bullet() :
    # Declares bullet state as a global if it needs to be changed
    global bullet_state
    if bullet_state == "ready" :
        bullet_state = "fire"
        # Move the bullet to just above the player
        x = player.xcor()
        y = player.ycor() + 10
        bullet.setposition(x, y)
        bullet.showturtle()


def isCollision(t1, t2) :
    distance = math.sqrt(math.pow(t1.xcor() - t2.xcor(), 2) + math.pow(t1.ycor() - t2.ycor(), 2))
    if distance < 15 :
        return True
    else :
        return False


# Keyboard bindings
turtle.listen()
turtle.onkey(move_left, "Left")
turtle.onkey(move_right, "Right")
turtle.onkey(fire_bullet, 'space')

# Main game loop
while True :
    for enemy in enemies :
        # Moving the enemy
        x = enemy.xcor()
        x += enemy_speed
        enemy.setx(x)

        # Boundary checking for enemy
        if enemy.xcor() > 280 :
            y = enemy.ycor()
            y -= 40
            enemy_speed *= -1
            enemy.sety(y)
        if enemy.xcor() < -280 :
            y = enemy.ycor()
            y -= 40
            enemy_speed *= -1
            enemy.sety(y)

        # Check for a collision between enemy and bullet
        if isCollision(bullet, enemy) :
            # Reset the bullet
            bullet.hideturtle()
            bullet_state = "ready"
            bullet.setposition(0, -400)
            # Reset the enemy
            enemy.setposition(-200, 250)
        if isCollision(player, enemy) :
            player.hideturtle()
            enemy.hideturtle()
            print ("Game Over...")
            break

    # Move the bullet
    y = bullet.ycor()
    y += bullet_speed
    bullet.sety(y)

    # Check borders for bullet
    if not bullet.ycor() <= 275 :
        bullet.hideturtle()
        bullet_state = "ready"

delay = (raw_input("Press enter to continue..."))