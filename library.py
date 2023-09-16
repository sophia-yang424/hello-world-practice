import random

deck = []
suits = ["Spade", "Diamonds", "Heart", "Club"]
numbers = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"]

# dictoinary to match number (0~52) and the actual card (ex. 9Heart)
dicts = {}
key = 0
for i in range(13):
    for j in range(4):
        dicts[key] = numbers[i]+suits[j]
        key += 1
dicts[52] = "unknown"

# creating deck
def createDeck():
    for x in range(52):
        deck.append(x)

# shuffling deck
def shuffelDeck():
    randomNum1 = random.randint(0, 51)
    randomNum2 = random.randint(0, 51)
    for i in range(3333):
        randomNum1 = random.randint(0, 51)
        randomNum2 = random.randint(0, 51)
        tempCard = deck[randomNum1]
        deck[randomNum1] = deck[randomNum2]
        deck[randomNum2] = tempCard

# dealing players two cards: player input is p1Hand or p2Hand
def deal(player):
    player.append(deck[0])
    player.append(deck[1])
    tempCard1 = deck[0]
    tempCard2 = deck[1]
    for a in range(50):
        deck[a] = deck[a+2]
    deck[50] = tempCard1
    deck[51] = tempCard2


# five cards 
fiveCard = [52,52,52,52,52]

# opening card: phase means how many cards are being opened
def openCard(phase):
    fiveCard[phase] = deck[0]
    tempCard = deck[0]
    for a in range(51):
        deck[a] = deck[a+1]
    deck[51] = tempCard