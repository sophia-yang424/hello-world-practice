from library import dicts, deck, fiveCard, fiveCards, ratioToPot, ratioToChip, twoCards
from library import createDeck, shuffelDeck, deal, openCard, appendData

from handPredictor import predHandGen

# whether to continue the game
cont = True

# hands of players
p1Hand = []
p2Hand = []

while cont == True:
    # initiating game 
    createDeck()
    shuffelDeck()

    print("Game starting!")

    # dealing first two hands 
    deal(p1Hand)
    deal(p2Hand)

    # betting
    appendData()

    for x in range(3):
        openCard(x)

    # betting
    appendData()

    openCard(3)

    # betting
    appendData

    openCard(4)

    # betting
    appendData()

    # print("Who is the winner?")
    # or
    # ranking 

    predHandGen()

    passed = False

    while passed == False:
        print("Do you want to conitnue? <yes/no>")
        response = input()
        response = response.lower()

        if response == "no":
            cont = False
            passed = True
        elif response == "yes":
            passed = True
        else:
            print("Invalid input")