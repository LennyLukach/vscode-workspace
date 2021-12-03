# Lenny Lukach
# Date made: 12/2/21
# Modified last: 12/2/21
# Stock buying and selling
# Honors Algorithms and Data Structures
import random
import os
import time
import sys


def clearScr():
    sysName = os.name
    if sysName == 'posix':
        os.system("clear")
    elif sysName == 'nt':
        os.system("cls")

class Stock:
    def __init__(self, price, symbol, name):
        self.price = price
        self.symbol = symbol
        self.name = name

portfo = {"Tesla": 0, "Apple": 0, "Dropbox": 0, "Netflix": 0, "Facebook": 0}
stocks = []
userMoney = random.randrange(4000, 20000)
stockNames = ["Tesla", "Apple", "Dropbox", "Netflix", "Facebook"]
stockSym = ["TSLA", "AAPL", "DBX", "NFLX", "FB"]
for x in range(5):
    stockPrice = round(random.random() * random.randint(27, 461), 2)
    stocks.append(Stock(stockPrice, stockSym[x], stockNames[x]))

def mainMenu(portfo, userMoney):
    clearScr()
    print("1. See my portfolio.")
    print("2. View stock prices.")
    print("3. Purchase stocks.")
    print("4. Sell stocks.")
    print("5. Special money drop")
    print("6. Exit")

    print(f"Your balance is ${userMoney}")
    selection = int(input())
    clearScr()
    match selection:
        case 1:
            print ("Stock : Owned")
            for stock in portfo:
                print (f"{stock} : {portfo[stock]}")
            userWait = input("\nPress enter to continue")
            mainMenu(portfo, userMoney)
        case 2:
            listPrices(stocks)
            userWait = input("\nPress enter to continue")
            mainMenu(portfo, userMoney)
        case 3:
            postBuyList = buyStock(stocks, portfo, userMoney)
            portfo = postBuyList[0]
            userMoney = postBuyList[1]
            mainMenu(portfo, userMoney)
        case 4:
            mainMenu(portfo, userMoney)
        case 5:
            print(f"A most benevolent being has randomly given you $1,000,000 to buy some more stocks!")
            userMoney += 1000000
            time.sleep(4)
            mainMenu(portfo, userMoney)
        case 6:
            clearScr()
            print("Goodbye")
            time.sleep(3)
            clearScr()
            sys.exit()




def listPrices(stocks):
    count = 0
    for stock in stocks:
        count += 1
        print(f"{count}. {stock.name}: ${stock.price}")

def buyStock(stocks, portfo, userMoney):
    listPrices(stocks)
    chosenStock = int(input("\nWhich stock do you want to buy?\n"))
    chosenStock -= 1
    stockName = stocks[chosenStock].name
    stockCost = stocks[chosenStock].price
    print(f"{stockName} costs ${stockCost}. How much do you want to buy?")
    buyAmt = int(input())
    stockCost = round(stockCost * buyAmt, 2)
    print(f"The price will be ${stockCost}")
    confirmPurchase = str(input(f"Are you sure you want to purchase {buyAmt} stocks for {stockName} for ${stockCost}?\n'Y' to confirm 'N' to decline\n"))
    confirmPurchase.lower()
    if confirmPurchase == "y":
        if stockCost <= userMoney:
            userMoney -= stockCost
            print(f"You purchased {buyAmt} of {stockName} stocks for ${stockCost}. You now have ${userMoney} left to spend.")
            portfo[stockName] += buyAmt
            time.sleep(3.5)
            clearScr()
        else:
            print(f"You did not have enough money to make the purchase. You are ${stockCost - userMoney} short.")
            time.sleep(2)
            clearScr()
    elif confirmPurchase == "n":
        print("The purchase was cancelled.")
        time.sleep(1.5)

    return [portfo, userMoney]


mainMenu(portfo, userMoney)