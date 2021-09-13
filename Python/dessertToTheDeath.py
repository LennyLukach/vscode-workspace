import random
import time
import os


class CakePie:

	def __init__(self, name, hp, strength, intelligence):
		self.name = name
		self.hp = hp
		self.strength = strength
		self.intelligence = intelligence

	def statSheet(self):
		return "Name: {}\nHp: {}\nStrength: {}\nIntelligence: {}".format(self.name, self.hp, self.strength, self.intelligence)

	@staticmethod
	def makeMoveCake(self):
		intell1 = ["Clobber", "Slam", "Punch"]
		intell2 = ["Body Slam", "Pummel", "Annihilate"]
		damage = 0
		if self.intelligence == 1:
			chosenCardCake = random.sample(intell1, k=1)
			if chosenCardCake == ["Clobber"]:
				damage += 1
			if chosenCardCake == ["Slam"]:
				damage += 2
			if chosenCardCake == ["Punch"]:
				damage += 3
			damage += self.strength
			pie.hp -= damage
			if pie.hp < 0:
				pie.hp = 0
		elif self.intelligence == 2:
			chosenCardCake = random.sample(intell2, k=1)
			if chosenCardCake == ["Body Slam"]:
				damage += 4
			if chosenCardCake == ["Pummel"]:
				damage += 5
			if chosenCardCake == ["Annihilate"]:
				damage += 6
			damage += self.strength
			pie.hp -= damage
			if pie.hp < 0:
				pie.hp = 0
		print ("{} used {} and dealt {} damage. Pie now has {}hp left.\n".format(self.name, chosenCardCake, damage, pie.hp))

	@staticmethod
	def makeMovePie(self):
		intell3 = ["Slay", "Crush", "Wack"]
		intell4 = ["Destroy", "Burn", "Devastate"]
		damage = 0
		if self.intelligence == 3:
			chosenCardPie = random.sample(intell3, k=1)
			if chosenCardPie == ["Slay"]:
				damage += 7
			if chosenCardPie == ["Crush"]:
				damage += 8
			if chosenCardPie == ["Wack"]:
				damage += 9
			damage += self.strength
			cake.hp -= damage
			if cake.hp < 0:
				cake.hp = 0
		if self.intelligence == 4:
			chosenCardPie = random.sample(intell4, k=1)
			if chosenCardPie == ["Destroy"]:
				damage += 10
			if chosenCardPie == ["Burn"]:
				damage += 11
			if chosenCardPie == ["Devastate"]:
				damage += 12
			damage += self.strength
			cake.hp -= damage
			if cake.hp < 0:
				cake.hp = 0
		print ("{} used {} and dealt {} damage. Cake now has {}hp left.\n".format(self.name, chosenCardPie, damage, cake.hp))
			

cake = CakePie("Cake", random.randint(40,50), random.randint(7,13), random.randint(1,2))
pie = CakePie("Pie", random.randint(50,55), random.randint(2,4), random.randint(3,4))

os.system("clear")
print ("| Dessert To The Death |\n\nThe two fighers we have are:\n\n{}\n\n{}\n\n".format(cake.statSheet(), pie.statSheet()))
pause = input("Press enter to continue...")
os.system("clear")
print ("The battle will begin in...")
time.sleep(1.5)
os.system("clear")
print ("3...")
time.sleep(1)
os.system("clear")
print ("2...")
time.sleep(1)
os.system("clear")
print ("1...")
time.sleep(1)
os.system("clear")
print ("GO")
time.sleep(1)
os.system("clear")
turn = random.randint(0,1)
while True:
	if turn == 0:
		CakePie.makeMoveCake(cake)
		turn += 1
	if pie.hp <= 0:
		print ("\n\nCake Wins!!")
		break
	if turn == 1:
		CakePie.makeMovePie(pie)
		turn -= 1
	if cake.hp <= 0:
		print ("\n\nPie Wins!!")
		break