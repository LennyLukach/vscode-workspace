def fibSeq(limit):
    curNum = 1
    prevNum = 0
    for x in range(limit):
        tempNum = curNum
        print(curNum)
        curNum += prevNum
        prevNum = tempNum


fibSeq(16)