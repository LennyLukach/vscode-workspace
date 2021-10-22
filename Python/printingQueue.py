import queue

numPages = int(input("How many pages:\n"))

printQ = queue.Queue(numPages)
f = open("Python/printingQueueFile.txt", "w")


for x in range(numPages):
    page = input(f"Contents of the {x + 1} page:\n")
    printQ.put(page)

while not printQ.empty():
    f.write(printQ.get() + "\n")

f.close()