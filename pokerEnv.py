from library import dicts, deck, p1Hand, p2Hand, fiveCard, fiveCards, ratioToPot, ratioToChip, twoCards
from library import createDeck, shuffelDeck, deal, openCard, appendData

from betting3 import ratioToPot, ratioToChip
from betting3 import preflopBet, flopBet, riverBet, floodBet, resultsFunction

from handPredictor import predHandGen

# whether to continue the game
cont = True

while cont == True:
    # initiating game 
    createDeck()
    shuffelDeck()

    print("Game starting!")

    # dealing first two hands 
    deal(p1Hand)
    deal(p2Hand)

    print("player 1 hand: Hide this from your opponent! type yes to move forward")
    input()
    for card in p1Hand:
        print(dicts[card])
    print("type yes to continue")
    input()
    print("player 2 hand: Hide this from your opponent! type yes to move forward")
    input()
    for card in p2Hand:
        print(dicts[card])
    print("type yes to continue")
    input()

    # first bet before any card flipped
    preflopBet()
    appendData()

    # 3 cards flipped and bet
    for x in range(3):
        openCard(x)
    for card in fiveCard:
        print(dicts[card])
    flopBet()
    appendData()

    # 4th card flipped and bet
    openCard(3)
    for card in fiveCard:
        print(dicts[card])
    riverBet()
    appendData

    # 5th card flipped and bet
    openCard(4)
    for card in fiveCard:
        print(dicts[card])
    floodBet()
    appendData()

    # checking winner
    # will be replaced by the actual ranking program (currently in "main.java" file)
    resultsFunction()

    # hand-predictor-model generation if total dataset is increased by 100
    predHandGen(len(twoCards))

    passed = False

    while passed == False:
        print("Do you want to continue? <yes/no>")
        response = input()
        response = response.lower()

        if response == "no":
            cont = False
            passed = True
        elif response == "yes":
            passed = True
        else:
            print("Invalid input")