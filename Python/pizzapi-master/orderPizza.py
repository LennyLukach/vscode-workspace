from pizzapy import *
import os

os.system("clear")
"""
def setCustomer():
	firstName = input("Enter your first name:\n")
	lastName = input("Enter your last name:\n")
	email = input("Enter your email:\n")
	phone = input("Enter your phone number:\n")
	address = input("Enter your full address:\n")
	customer = Customer(firstName, lastName, email, phone, address)
	os.system("clear")
	customerChoice = input(f"Here is your customer info:\n{customer}\nType q if it is correct or t if it is not:\n")
	if customerChoice == "q":
		os.system("clear")
		gui()
	if customerChoice == "t":
		os.system("clear")
		setCustomer()
"""
customer = Customer("Lenny", "Lukach", "drlendog@gmail.com", "3238219688", "3352 Dona Rosa Drive, Studio City, CA, 91604")
my_local_dominos = StoreLocator.find_closest_store_to_customer(customer)
menu = my_local_dominos.get_menu()
order = Order.begin_customer_order(customer, my_local_dominos)
cartList = []

def gui():
	choice = input("1. Search Menu\n2. Find Local Store\n3. Add or Remove Items From Cart\n4. Create Order\n")
	if choice == "1":
		os.system("clear")
		searchMenu(menu)
	if choice == "2":
		os.system("clear")
		print(my_local_dominos)
		pause = input("")
		os.system("clear")
		gui()
	if choice == "3":
		os.system("clear")
		cart()
	if choice == "4":
		os.system("clear")
		createOrder()

def searchMenu(menu):
	while True:
		item = input("Type an item to search for on the menu or type" + " done " + "if you are done\n").strip().lower()
		os.system("clear")

		if item == "done":
			gui()

		if item != "" and len(item) > 1:
			item  = item[0].upper() + item[1:]

		else:
			print("Search invalid, try again.")
			searchMenu(menu)

		print(f"Results for: {item}")
		menu.search(Name=item)

def cart():
	choice = input("1. Add Items\n2. Remove Items\n3. View Cart\n4. Done\n")
	if choice == "1":
		os.system("clear")
		item = input("Enter the item tag:\n")
		order.add_item(item)
		cartList.append(item)
		os.system("clear")
		cart()
	if choice == "2":
		os.system("clear")
		item = input("Enter the item tag:\n")
		order.remove_item(item)
		cartList.remove(item)
		os.system("clear")
		cart()
	if choice == "3":
		os.system("clear")
		print(cartList)
		cart()
	if choice == "4":
		os.system("clear")
		gui()

def createOrder():
	os.system("clear")
	choice = input(f"Here is your cart\n{cartList}\n1. I Am Happy With My Order\n2. Let Me Change My Order\n")
	if choice == "1":
		print("Enter your credit card info here:")
		cardNumber = input("Enter your credit card number:\n")
		experDate = input("Enter the experiation date:\n")
		ccv = input("Enter the ccv here:\n")
		zipcode = input("Enter your zipcode:\n")
		card = CreditCard(cardNumber, experDate, ccv, zipcode)
		os.system("clear")
		cardChoice = input(f"Here is your credit card info:\n1. Card number: {cardNumber}\n2. Experiation Date: {experDate}\n3. CCV: {ccv}\n4. Zipcode: {zipcode}\n1. It is correct\n2. It is incorrect\n")
		if cardChoice == "1":
			order.place(card)
			my_local_dominos.place_order(order, card)
		if cardChoice == "2":
			createOrder()
	if choice == "2":
		os.system("clear")
		gui()
gui()