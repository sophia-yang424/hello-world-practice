## initialization
global roundNum
roundNum = 0
global pot
pot = 0
global recordList 
recordList = list()
global ratioToPot
ratioToPot = list()
global ratioToChip
ratioToPot = list()
sidepotBet = 0
smallBlind = 1
bigBlind = 2
global player1_wealth
global player2_wealth
player1_wealth = 500
player2_wealth = 500
global player1_bet_1 
player1_bet_1 = None
global player2_bet_1
player2_bet_1 = None
global player1_bet_2
player1_bet_2 = None
global player2_bet_2
player2_bet_2 = None
global player1_bet_3
player1_bet_3 = None
global player2_bet_3
player2_bet_3 = None
global player1_bet_4
player1_bet_4 = None
global player2_bet_4
player2_bet_4 = None

# pre-flop bet
'''
def blinds():
    if roundNum != 0 and roundNum % 2 == 0:
        smallBlind = 2
        bigBlind = 1
    elif roundNum != 0 and roundNum % 2 != 0:
        smallBlind = 1
        bigBlind = 2
    if smallBlind == 2:
    


        ## set up subtracting the small blind and big blind values from wealth and adding that value to the amount bet that round
'''
def preflopBet():
    global player1_wealth
    global player2_wealth
    global player1_bet_1
    global player2_bet_1
    global askFold
    global recordList 
    askFold = ""
    #player choice
    player1_choice = int(float(input("Player 1: Do you want to (0) bet, or (-1)) check, (-2)) fold: ")))
    #checking/fold
    if player1_choice == 0:
        player1_bet_1 = int(float(input("Player 1 bet amount: ")))
    elif player1_choice == -2:
        print("Player 1 folded! Player 2 wins!")
        exit()
    elif player1_choice == -1:
        player1_bet_1 = 0
        print("Check!")
    #outbetting
    if player1_bet_1 > player2_wealth:
        outBetBool = True
    else:
        outBetBool = False
    while outBetBool:
        player1_bet_1 = int(float(input("Please type in a value less than or equal to what the other player can afford: ")))
        if player1_bet_1 > player2_wealth:
            outBetBool = True
            continue
        else:
            outBetBool = False
            break
        #match bet
    if player2_bet_1 != None:
        if player1_bet_1 < player2_bet_1:
            notMatchBool = True
        elif player1_bet_1 >= player2_bet_1:
            notMatchBool = False
        while notMatchBool:
            player1_bet_1 = int(float(input("Please either match the other player's bet or raise: ")))
            if player1_bet_1 < player2_bet_1:
                notMatchBool = True
                continue  
            else:
                notMatchBool = False
                break
    #ask about meeting check, folding or raising
    if player1_choice == -1 :
        askCheck = input("Player 2: Do you want to check? (yes/no): ")
        if askCheck == "yes" or askCheck == "Yes":
            player2_bet_1 = 0
            print("Checks all around! Next round!")
        elif (askCheck == "No" or askCheck == "no"):
            askRaise = int(input("Do you want to (1) raise or (2) fold?: "))
            if askRaise == 1:
                raiseAmount = int(input("Raise amount: "))
                player2_bet_1 = raiseAmount
                meetBet = int(input("Player 1: Player 2 is raising! (-1) match the bet? or (-2) fold?: "))
                if meetBet == -1:
                    player1_bet_1 = raiseAmount
                    print(player1_bet_1)
                elif meetBet == -2:
                    print("Player 1 folded! Player 1 wins!")
                    exit()
            elif askRaise == 2:
                print("Player 2 folded! Player 1 wins!")
                exit()

    elif player1_choice == 0:
        player2_bet_1 = int(float(input("Player 2 bet amount: ")))
        #checking/folding
        if player2_bet_1 == -1:
            player2_bet_1 = 0
            print("Check!")
        elif player2_bet_1 == -2:
            print("You folded!")
            exit()
    #prevent outbetting
    if player2_bet_1 > player1_wealth:
        outBetBool = True
    else:
        outBetBool = False
    while outBetBool:
        player2_bet_1 = int(float(input("Please type in a value less than or equal to what the other player can afford: ")))
        if player2_bet_1 > player1_wealth:
            outBetBool = True
            continue
        else:
            outBetBool = False
            break
        #matching bet

    if player1_wealth != None:
        if player2_bet_1 < player1_bet_1:
            notMatchBool = True
        elif player2_bet_1 >= player1_bet_1:
            notMatchBool = False
        while notMatchBool:
            player2_bet_1 = int(float(input("Please either match the other player's bet or raise: ")))
            if player1_bet_1 == 0:
                askFold = input("Are you sure you want to fold?: ")
            if askFold == "yes" or askFold == "Yes":
                print("You folded!")
                exit()
            elif askFold == "no" or askFold == "No":
                continue
            else: 
                print("Please type a valid answer.")

            if player2_bet_1 < player1_bet_1:
                notMatchBool = True
                continue  
            else:
                notMatchBool = False
                break
    #list appending
    recordList.append(player1_bet_1 / player1_wealth)
    player1_wealth -= player1_bet_1
    recordList.append(player2_bet_1 / player2_wealth)
    player2_wealth -= player2_bet_1
    #pot addition
    global pot
    pot = 0
    pot = pot + player1_bet_1 + player2_bet_1
    potString = str(pot)
    print("The pot for pre-flop: " + potString)
    wealth_1_string = str(player1_wealth)
    wealth_2_string = str(player2_wealth)
    print("Player 1 has " + wealth_1_string + "! " + "Player 2 has " + wealth_2_string + "!")

## flop
def flopBet():
    global player1_wealth
    global player2_wealth
    global player1_bet_2
    global player2_bet_2
    player1_bet_2 = int(float(input("Player 1 bet amount: ")))
    #prevent outbetting
    if player1_bet_2 > player2_wealth:
        outBetBool = True
    else:
        outBetBool = False
    while outBetBool:
        player1_bet_2 = int(float(input("Please type in a value less than or equal to what the other player can afford: ")))
        if player1_bet_2 > player2_wealth:
            outBetBool = True
            continue
        else:
            outBetBool = False
            break
# match bet
    if player2_bet_2 != None:
        if player1_bet_2 < player2_bet_2:
            notMatchBool = True
        elif player1_bet_2 >= player2_bet_2:
            notMatchBool = False
        while notMatchBool:
            player1_bet_2 = int(float(input("Please either match the other player's bet or raise: ")))
            if player1_bet_2 < player2_bet_2:
                notMatchBool = True
                continue  
            else:
                notMatchBool = False
                break

    player1_wealth -= player1_bet_2
    global recordList 
    recordList.append(player1_bet_2 /player1_wealth)
    player2_bet_2 = int(float(input("Player 2 bet amount: ")))
    #prevent outbetting
    if player2_bet_2 > player1_wealth:
        outBetBool = True
    else:
        outBetBool = False
    while outBetBool:
        player2_bet_2 = int(float(input("Please type in a value less than or equal to what the other player can afford: ")))
        if player2_bet_2 > player1_wealth:
            outBetBool = True
            continue
        else:
            outBetBool = False
            break
    #match bet
    if player1_bet_2 != None:
        if player2_bet_2 < player1_bet_2:
            notMatchBool = True
        elif player2_bet_2 >= player1_bet_2:
            notMatchBool = False
        while notMatchBool:
            player2_bet_2 = int(float(input("Please either match the other player's bet or raise: ")))
            if player2_bet_2 < player1_bet_2:
                notMatchBool = True
                continue  
            else:
                notMatchBool = False
                break
    player2_wealth -= player2_bet_2
    recordList.append(player2_bet_2 / player2_wealth)
    global pot
    pot = pot + player1_bet_2 + player2_bet_2
    potString = str(pot)
    print("The pot for flop: " + potString)
    wealth_1_string = str(player1_wealth)
    wealth_2_string = str(player2_wealth)
    print("Player 1 has " + wealth_1_string + "! " + "Player 2 has " + wealth_2_string + "!")

## river 
def riverBet():
    global player1_wealth
    global player2_wealth
    global player1_bet_3
    global player2_bet_3
    player1_bet_3 = int(float(input("Player 1 bet amount: ")))
    #prevent outbetting
    if player1_bet_3 > player2_wealth:
        outBetBool = True
    else:
        outBetBool = False
    while outBetBool:
        player1_bet_3 = int(float(input("Please type in a value less than or equal to what the other player can afford: ")))
        if player1_bet_3 > player2_wealth:
            outBetBool = True
            continue
        else:
            outBetBool = False
            break
        #match bet
    if player2_bet_3 != None:
        if player1_bet_3 < player2_bet_3:
            notMatchBool = True
        elif player1_bet_3 >= player2_bet_3:
            notMatchBool = False
        while notMatchBool:
            player1_bet_3 = int(float(input("Please either match the other player's bet or raise: ")))
            if player1_bet_3 < player2_bet_3:
                notMatchBool = True
                continue  
            else:
                notMatchBool = False
                break
    player1_wealth -= player1_bet_3
    global recordList 
    recordList.append(player1_bet_3 / player1_wealth)
    player2_bet_3 = int(float(input("Player 2 bet amount: ")))
    #prevent outbetting
    if player2_bet_3 > player1_wealth:
        outBetBool = True
    else:
        outBetBool = False
    while outBetBool:
        player2_bet_3 = int(float(input("Please type in a value less than or equal to what the other player can afford: ")))
        if player2_bet_3 > player1_wealth:
            outBetBool = True
            continue
        else:
            outBetBool = False
            break
        #match bet
    if player1_bet_1 != None:
        if player2_bet_3 < player1_bet_3:
            notMatchBool = True
        elif player2_bet_3 >= player1_bet_3:
            notMatchBool = False
        while notMatchBool:
            player2_bet_3 = int(float(input("Please either match the other player's bet or raise: ")))
            if player2_bet_3 < player1_bet_3:
                notMatchBool = True
                continue  
            else:
                notMatchBool = False
                break
    player2_wealth -= player2_bet_3
    recordList.append(player2_bet_3 / player2_wealth)
    global pot
    pot = pot + player1_bet_3 + player2_bet_3
    potString = str(pot)
    print("The pot for river: " + potString)
    wealth_1_string = str(player1_wealth)
    wealth_2_string = str(player2_wealth)
    print("Player 1 has " + wealth_1_string + "! " + "Player 2 has " + wealth_2_string + "!")

## flood
def floodBet():
    global player1_wealth
    global player2_wealth
    global player1_bet_4
    global player2_bet_4
    player1_bet_4 = int(float(input("Player 1 bet amount: ")))
    #prevent outbetting
    if player1_bet_4 > player2_wealth:
        outBetBool = True
    else:
        outBetBool = False
    while outBetBool:
        player1_bet_4 = int(float(input("Please type in a value less than or equal to what the other player can afford: ")))
        if player1_bet_4 > player2_wealth:
            outBetBool = True
            continue
        else:
            outBetBool = False
            break
        #match bet
    if player2_bet_4 != None:
        if player1_bet_4 < player2_bet_4:
            notMatchBool = True
        elif player1_bet_4 >= player2_bet_4:
            notMatchBool = False
        while notMatchBool:
            player1_bet_4 = int(float(input("Please either match the other player's bet or raise: ")))
            if player1_bet_4 < player2_bet_4:
                notMatchBool = True
                continue  
            else:
                notMatchBool = False
                break
    player1_wealth -= player1_bet_4
    global recordList 
    recordList.append(player1_bet_4 / player1_wealth)
    player2_bet_4 = int(float(input("Player 2 bet amount: ")))
    #prevent overbetting
    if player2_bet_4 > player1_wealth:
        outBetBool = True
    else:
        outBetBool = False
    while outBetBool:
        player2_bet_4 = int(float(input("Please type in a value less than or equal to what the other player can afford: ")))
        if player2_bet_4 > player1_wealth:
            outBetBool = True
            continue
        else:
            outBetBool = False
            break
        #match bet
    if player1_bet_4 != None:
        if player2_bet_4 < player1_bet_4:
            notMatchBool = True
        elif player2_bet_4 >= player1_bet_4:
            notMatchBool = False
        while notMatchBool:
            player2_bet_4 = int(float(input("Please either match the other player's bet or raise: ")))
            if player2_bet_4 < player1_bet_4:
                notMatchBool = True
                continue  
            else:
                notMatchBool = False
                break
    player2_wealth -= player2_bet_4
    recordList.append(player2_bet_4 / player2_wealth)
    global pot
    pot = pot + player1_bet_4 + player2_bet_4
    potString = str(pot)
    print("The pot for flood: " + potString)
    wealth_1_string = str(player1_wealth)
    wealth_2_string = str(player2_wealth)
    print("Player 1 has " + wealth_1_string + "! " + "Player 2 has " + wealth_2_string + "!")

#results
def resultsFunction():
    global player1_wealth
    global player2_wealth
    continueLoop = True
    global roundNum
    roundNum += 1
    while continueLoop:
        winner = input("Which player won? (Player 1 or Player 2?)")
        if winner == "Player 1" or winner == "player 1" or winner == "1":
            player1_wealth += pot
            potString = str(pot)
            print("The winner is Player 1! " + potString + " was added to their wealth" )
            wealth_1_string = str(player1_wealth)
            wealth_2_string = str(player2_wealth)
            print("Player 1 has " + wealth_1_string + "! " + "Player 2 has " + wealth_2_string + "!")
            continueLoop = False
        elif winner == "Player 2" or winner == "winner 2" or winner == "2":
            player2_wealth += pot
            potString = str(pot)
            print("The winner is Player 2! " + potString + " was added to their wealth" )
            wealth_1_string = str(player1_wealth)
            wealth_2_string = str(player2_wealth)
            print("Player 1 has " + wealth_1_string + "! " + "Player 2 has " + wealth_2_string + "!")
            continueLoop = False
        else:
            print("Please type a valid player.")

            

#fix the outBet method and make a custom one for each round (we are doing outbetting prevention instead of side pot bc its only two player)
##small blind big blind stuff (dont append)

## game ends when a player has no chips, must match bets (meeting bet, checking, folding, raising etc also must be added) 
#do a do while loop for if the second person's (not necessarily player 2 bc of small blind) bet is less than the other players, they must do a higher
#for raising, if someone raises, must run a check that the other person's bet now must also match this
## flding must be added (they select this, their bet is set to 0 for that round and round ends) (make a fold method)
#game ends if someone runs out of chips (if chips = 0, then exit program and run the end game method) (make an end game method)
#checking => both bets are set to zero, and since they equal, the program continues without issue
#for whose turn it is to start first, use the %2 thing to determine small blind

#make separate methods for player 1 and 2 for flop etc
#to toChip ratio
#round the decimals for the list
#testing
# WHY DOESNT THE SECOND OVERBETTING FOR FLOOD NOT WORK?

preflopBet()
flopBet()
riverBet()
floodBet()
resultsFunction()
print(recordList)
preflopBet()
flopBet()
riverBet()
floodBet()
resultsFunction()
print(recordList)