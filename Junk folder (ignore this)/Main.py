class Card:

    def __init__(self, number, suite, cardId):
        self.number = number
        self.suite = suite
        self.cardId = cardId

    def getNumber(self):
        return self.number

    def getSuite(self):
        return self.suite

    def getCardId(self):
        return self.cardId


class Player:
    def __init__(self):
       pass
    
    def setPersonPersonalHand(self, card: Card):
        self.playerPersonalHand.append(card)
    
    def printPlayerHand(self):
        for i in range(2):
            print(self.playerPersonalHand[i].getNumber(), self.playerPersonalHand[i].getSuite(), end=" ")


deck = []
suits = ["Spade", "Diamonds", "Heart", "Club"]
numbers = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"]
player1 = Player()
player2 = Player()
sharedCard = []
currentCardNumber = 0

class Deck:
    def __init__(self):
        global deck, suits, numbers, player1, player2, sharedCard, currentCardNumber
        player1 = Player()
        player2 = Player()
        deck = []
        cardCounter = 0
        for i in range(13):
            for j in range(4):
                card = Card(numbers[i], suits[j], cardCounter)
                deck.append(card)
                cardCounter += 1

def resetDeck():
    cardCounter = 0
    for i in range(13):
        for j in range(4):
            card = Card(numbers[i], suits[j], cardCounter)
            deck[cardCounter] = card
            cardCounter += 1
    shuffelDeck()

def shuffelDeck():
    import random
    randomNum1 = random.randint(0, 51)
    randomNum2 = random.randint(0, 51)
    tempCard = None
    for i in range(3333):
        tempCard = deck[randomNum1]
        deck[randomNum1] = deck[randomNum2]
        deck[randomNum2] = tempCard
        randomNum1 = random.randint(0, 51)
        randomNum2 = random.randint(0, 51)

def printPublicCardFlop():
    for i in range(3):
        print(sharedCard[i].getNumber(), sharedCard[i].getSuite(), end="  ")

def printPublicCardTurn():
    print(sharedCard[3].getNumber(), sharedCard[3].getSuite(), end="  ")

def printPublicCardRiver():
    print(sharedCard[4].getNumber(), sharedCard[4].getSuite(), end="  ")

def getDeck():
    return deck

def dealPreFlop():
    for i in range(2):
        player1.setPersonPersonalHand(deck[currentCardNumber])
        currentCardNumber += 1
    for i in range(2):
        player2.setPersonPersonalHand(deck[currentCardNumber])
        currentCardNumber += 1
def dealFlop():
    for i in range(3):
        sharedCard.append(deck[currentCardNumber])
        currentCardNumber += 1

def dealTurn():
    sharedCard.append(deck[currentCardNumber])
    currentCardNumber += 1

def dealRiver():
    sharedCard.append(deck[currentCardNumber])
    currentCardNumber += 1

def getPlayer1():
    return player1

def getPlayer2():
    return player2

test = deck()
test.dealPreFlop()
print("Player 1's hand: ")
test.getPlayer1().printPlayerHand()
print("")
print("Player 2's hand: ")
test.getPlayer2().printPlayerHand()
print("")

print("Public Card: ")
test.dealFlop()
test.printPublicCardFlop()
test.dealTurn()
test.printPublicCardTurn()
test.dealTurn()
test.printPublicCardRiver()



