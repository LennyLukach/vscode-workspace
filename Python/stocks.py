# Lenny Lukach
# Date made: 12/2/21
# Modified last: 12/3/21
# Stock buying and selling
# Honors Algorithms and Data Structures
import random
import os
import time
import sys
import pickle
import socket

# Get directory program ran in
savePath = os.getcwd() + "/Python/stockSaveFile.dat"

# Method to clear screen based on OS
def clearScr():
    sysName = os.name
    if sysName == 'posix':
        os.system("clear")
    elif sysName == 'nt':
        os.system("cls")

# Stock class to make different stocks
class Stock:
    def __init__(self, price, symbol, name):
        self.price = price
        self.symbol = symbol
        self.name = name

# Tries to load a save file if one exists, otherwie generates new file 
try:
    with open(savePath, "rb") as f:
        userMoney, portfo = pickle.load(f)
except:
    # Money stored as int and portfolio stored as dictionary
    portfo = {"Tesla": 0, "Apple": 0, "Dropbox": 0, "Netflix": 0, "Facebook": 0}
    userMoney = round(random.randrange(4000, 20000), 2)

# Initalize variables and lists
stocks = []
stockNames = ["Tesla", "Apple", "Dropbox", "Netflix", "Facebook"]
stockSym = ["TSLA", "AAPL", "DBX", "NFLX", "FB"]
for x in range(5):
    stockPrice = round(random.random() * random.randint(27, 461), 2)
    stocks.append(Stock(stockPrice, stockSym[x], stockNames[x]))


# Main menu method that controls GUI
def mainMenu(stocks, portfo, userMoney):
    clearScr()
    # Lists options to select from
    print("1. See my portfolio.")
    print("2. View stock prices.")
    print("3. Purchase stocks.")
    print("4. Sell stocks.")
    print("5. Developer tools")
    print("6. Exit")
    print("7. Credits")

    # Calls code depending on the user input
    print(f"Your balance is ${userMoney}")
    selection = int(input())
    clearScr()
    match selection:
        case 1: # List portfolio i.e. stocks and shares
            listPortfo(portfo)
            userWait = input("\nPress enter to continue")
            mainMenu(stocks, portfo, userMoney)
        case 2: # Lists price of each share
            listPrices(stocks)
            userWait = input("\nPress enter to continue")
            mainMenu(stocks, portfo, userMoney)
        case 3: # Calls the buying stocks method
            postBuyList = buyStock(stocks, portfo, userMoney)
            portfo = postBuyList[0]
            userMoney = postBuyList[1]
            mainMenu(stocks, portfo, userMoney)
        case 4: # Calls the selling stocks method
            postSellList = sellStock(stocks, portfo, userMoney)
            portfo = postSellList[0]
            userMoney = postSellList[1]
            mainMenu(stocks, portfo, userMoney)
        case 5: # Opens dev tools
            devChangeList = devTools(stocks, portfo, userMoney)
            stocks, portfo, userMoney = devChangeList[0], devChangeList[1],devChangeList[2]
            mainMenu(stocks, portfo, userMoney)
        case 6: # Saves and exits game
            clearScr()
            print("Saving")
            # Saves to existing file or makes new one if it doesn't exist
            with open(savePath, "wb") as f:
                pickle.dump([userMoney, portfo], f, protocol=5)
            time.sleep(3)
            clearScr()
            sys.exit()
        case 7: # Rolls credits
            for x in range(os.get_terminal_size().lines - 3, 0, -1):
                clearScr()
                print("\n" * x)
                print(" " * int(((os.get_terminal_size().columns - 21) / 2)) + "Made by: Lenny")
                time.sleep(0.8)
            mainMenu(portfo, userMoney)



# Method to list the prices
def listPrices(stocks):
    count = 0
    for stock in stocks:
        count += 1
        print(f"{count}. {stock.name}: ${stock.price}")

# Method to list the portfolio
def listPortfo(portfo):
    count = 0
    print ("Stock : Owned")
    for stock in portfo:
        count += 1
        print (f"{count}. {stock} : {portfo[stock]}")

#Method to buy stock
def buyStock(stocks, portfo, userMoney):
    listPrices(stocks)
    chosenStock = int(input("\nWhich stock do you want to buy?\n"))
    chosenStock -= 1
    # Gets chosen stock
    stockName = stocks[chosenStock].name
    stockCost = stocks[chosenStock].price
    print(f"{stockName} costs ${stockCost}. How much do you want to buy?")
    buyAmt = int(input())
    stockCost = round(stockCost * buyAmt, 2)
    print(f"The price will be ${stockCost}")
    confirmPurchase = str(input(f"Are you sure you want to purchase {buyAmt} shares of {stockName} for ${stockCost}?\n'Y' to confirm 'N' to decline\n"))
    confirmPurchase.lower()
    # Checks if user confirmed purchase then checks if purchase is possible
    if confirmPurchase == "y":
        if stockCost <= userMoney:
            userMoney -= stockCost
            userMoney = round(userMoney, 2)
            print(f"You purchased {buyAmt} shares of {stockName} for ${stockCost}. You now have ${userMoney} left to spend.")
            portfo[stockName] += buyAmt
            time.sleep(3.5)
            clearScr()
        else:
            print(f"You did not have enough money to make the purchase. You are ${round(stockCost - userMoney, 2)} short.")
            time.sleep(2)
            clearScr()
    elif confirmPurchase == "n":
        print("The purchase was cancelled.")
        time.sleep(2.5)

    return [portfo, userMoney]

# Method to sell stock
def sellStock(stocks, portfo, userMoney):
    listPortfo(portfo)
    chosenStock = int(input("\nWhich stock do you want to sell?\n"))
    chosenStock -= 1
    # Gets chosen stock
    stockName = stocks[chosenStock].name
    stockCost = stocks[chosenStock].price
    stockOwned = portfo.get(stockName)
    print(f"You have {stockOwned} shares in {stockName} and they are worth ${stockCost} each. How much do you want to sell?")
    sellAmt = int(input())
    stockCost = round(stockCost * sellAmt, 2)
    print(f"The sale will be worth ${stockCost}")
    confirmPurchase = str(input(f"Are you sure you want to sell {sellAmt} shares of {stockName} for ${stockCost}?\n'Y' to confirm 'N' to decline\n"))
    confirmPurchase.lower()
    # Checks if user confirmed the sale and then checks if the sale is possible
    if confirmPurchase == "y":
        if sellAmt <= stockOwned:
            userMoney += stockCost
            round(userMoney, 2)
            print(f"You sold {sellAmt} of {stockName} shares for ${stockCost}. You now have ${userMoney}.")
            portfo[stockName] -= sellAmt
            time.sleep(3.5)
            clearScr()
        else:
            print(f"You did not have enough shares to sell. You are {sellAmt - stockOwned} shares short.")
            time.sleep(2)
            clearScr()
    elif confirmPurchase == "n":
        print("The sale was cancelled.")
        time.sleep(2.5)
    return [portfo, userMoney]


def devTools(stocks, portfo, userMoney):
    isDev = False
    if socket.gethostname() == "Lennys-MacBook-Pro.local" or "DESKTOP-56N23MR":
        isDev = True
    
    print("1. Set money")
    print("2. Set owned shares")
    print("3. Set stock price")
    print("4. Randomize stock price")

    selection = int(input())
    if not isDev:
        print("You do not have access to this.")
        time.sleep(3)
        return
    match selection:
        case 1:
            userMoney = int(input("Value:\n"))
        case 2:
            pass
        case 3:
            pass
        case 4:
            pass
    return [stocks, portfo, userMoney]


# Driver code to call the main menu method
mainMenu(stocks, portfo, userMoney)