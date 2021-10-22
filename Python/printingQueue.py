import queue

numPages = int(input("How many pages:\n"))

printQ = queue.Queue(numPages)

for x in range(numPages):
    page = input(f"Contents of the {x + 1} page:\n")
    printQ.put(page)

while not printQ.empty():
    print(printQ.get())