import cv2
import mediapipe as mp
import time
import os


scale_percent = 50
cap = cv2.VideoCapture(0)
mpHands = mp.solutions.hands
hands = mpHands.Hands()
mpDraw = mp.solutions.drawing_utils

pTime = 0
cTime = 0

while True:

    success, img = cap.read()
    #img = cv2.resize(img, (1280, 720))
    imgRGB = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
    results = hands.process(imgRGB)
    #print(results.multi_hand_landmarks)

    if results.multi_hand_landmarks:
        for handLandMarks in results.multi_hand_landmarks:
            mpDraw.draw_landmarks(img, handLandMarks, mpHands.HAND_CONNECTIONS)



    img = cv2.flip(img, 1)
    cTime = time.time()
    fps = 1/(cTime - pTime)
    pTime = cTime
    cv2.putText(img, str(int(fps)), (10, 100), cv2.FONT_HERSHEY_SIMPLEX, 3, (0, 255, 0), 3)
    cv2.imshow("hello", img)
    if cv2.waitKey(1) & 0xFF == ord("q"):
        break