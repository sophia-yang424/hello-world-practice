from library import dicts, deck, fiveCard
from library import createDeck, shuffelDeck, deal, openCard

# whether to continue the game
cont = True

# hands of players
p1Hand = []
p2Hand = []

# data collected in the following arrays
fiveCards = []
ratioToPot = []
ratioToChip = []

while cont == True:
    # initiating game 
    createDeck()
    shuffelDeck()

    print("Game starting!")

    # dealing first two hands 
    deal(p1Hand)
    deal(p2Hand)

    # betting

    for x in range(3):
        openCard(x)

    # betting

    openCard(3)

    # betting

    openCard(4)

    # betting

    # print("Who is the winner?")
    # or
    # ranking 

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


print(deck)
print(len(deck))
print(openCard)
