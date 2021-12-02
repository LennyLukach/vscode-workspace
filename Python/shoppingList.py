# Lenny Lukach
# Date made: 12/2/21
# Modified last: 12/2/21
# Stock buying and selling
# Honors Algorithms and Data Structures
import random
import os

def clearScr():
    sysName = os.name
    if sysName == 'posix':
        os.system("clear")
    elif sysName == 'nt':
        os.system("cls")


clearScr()

class Stock:
    def __init__(self, price, symbol, name):
        self.price = price
        self.symbol = symbol
        self.name = name

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
    print(f"The price will be {stockCost * buyAmt}")


portfo = {}
stocks = []
userMoney = random.randrange(4000, 20000)
stockNames = ["Tesla", "Apple", "Dropbox", "Netflix", "Facebook"]
stockSym = ["TSLA", "AAPL", "DBX", "NFLX", "FB"]
for x in range(5):
    stockPrice = round(random.random() * random.randint(27, 461), 2)
    stocks.append(Stock(stockPrice, stockSym[x], stockNames[x]))


buyStock(stocks, portfo, userMoney)