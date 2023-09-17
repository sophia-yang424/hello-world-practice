import tkinter as tk
#import library as lib

class Window(tk.Frame):
    def __init__(self, master = None):
        tk.Frame.__init__(self, master)
        self.master = master

root = tk.Tk()
class tkCard:

    def createCardBackground(self, repeatRange, bgCol, xloc, yloc, interval): #trying to make code more efficient
        for i in range(repeatRange):
            canvas = tk.Canvas(root, width=100, height=175, bg=bgCol)   
            canvas.place(x=xloc, y=yloc) 
            xloc += interval   

    


#tkCard.createCardBackground(root, 6, "purple", 134, 253, 200)   # test to see if creeateCardBackground works
 
app = Window(root)
root.wm_title("Poker Game +")
root.geometry("1000x800")
root.configure(bg='green')

canvas = tk.Canvas(root, width=25, height=1300, bg='black') #divider between actual cards (on left) and maybe selection or info about game (on right)
canvas.place(x=700,y=-10)
horzPlayerLocation = 300
for i in range(2):
    canvas = tk.Canvas(root, width=100, height=175, bg="blue")   #player 2 card 1, 2 blue bg
    canvas.place(x=horzPlayerLocation, y=50) 
    horzPlayerLocation += 200    

#symbolSpade = tk.Label(root, text = u'\u2660', bg='blue') #for player 2 card 1 symbol spade <- change so that spade can be interchangeable with suit
#symbolSpade.config(font =("Courier", 75))
#symbolSpade.place(x=325,y=100) 


horzComLocation = 50
for i in range(5):
    canvas = tk.Canvas(root, width=100, height=175, bg="red")   #communal card 1, 2, 3 + ?? card 4, 5
    canvas.place(x=horzComLocation,y=300)
    horzComLocation += 125

#canvas = tk.Canvas(root, width=100, height=175, bg="red")   #communal card 1
#canvas.place(x=50,y=300)

#canvas = tk.Canvas(root, width=100, height=175, bg="red")   #communal card 2
#canvas.place(x=175,y=300)

#canvas = tk.Canvas(root, width=100, height=175, bg="red")   #communal card 3
#canvas.place(x=300,y=300)

#canvas = tk.Canvas(root, width=100, height=175, bg="red")   #??+ card 4
#canvas.place(x=425,y=300)

#canvas = tk.Canvas(root, width=100, height=175, bg="red")   ##??+ card 5
#canvas.place(x=550,y=300)
horzPlayerLocation = 100
for i in range(2):
    canvas = tk.Canvas(root, width=100, height=175, bg="white")   #player 2 card 1, 2 blue bg
    canvas.place(x=horzPlayerLocation, y=550) 
    horzPlayerLocation += 200  

unicodeSuit = [
'\u2660',
'\u2665',
'\u2666',
'\u2663'
]

tkcardRank = [
    "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
]
#tkUnicodeSuitNum = 0 
#for i in range(2):
#    tkUnicodeSuitNum = p2Hand[i]/13
    #tkcodeRankNum = 
# Iterate through all unicode values #here, this is set up for p2. Once figure out p2Hand[], add instead of unicodeSuit
#horzUnicodeSuit = 50
p2NumLocation = 320


p2SuitLocation = 320 #add unicodesuit to player 2
for i in range(2):
    p2Suit = tk.Label(root, text = u'{unicodeSuit[0]}'.format( #0 can be replaced by the specific unicode suit of p2 suit card 1, then at end make sure goes to p2 suit card 2
      unicodeSuit = unicodeSuit), bg= 'blue')
    p2Suit.config(font =("Courier", 75))
    p2Suit.place(x=p2SuitLocation, y=75)
    p2SuitLocation += 200
    

middleSuitLocation = 320 #add unicodesuit to middle cards woorkut messy s b
#for i in unicodeSuit:
#    middleSuit = tk.Label(root, text = u'{unicodeSuit[2]}'.format( #maybe change the unicodeSuit[2] to a unique array for middleSuitLocation like data from 5card/5cards?
#      unicodeSuit = unicodeSuit), bg= 'red')
#    middleSuit.config(font =("Courier", 75))
#    middleSuit.place(x=middleSuitLocation, y=375)
#    middleSuitLocation += 200
    
#p1SuitLocation = 320 works but messy
#for i in unicodeSuit: #add unicodesuit to player 1
#    p1Suit = tk.Label(root, text = u'{unicodeSuit[3]}'.format(
#      unicodeSuit = unicodeSuit), bg= 'white')
 #   p1Suit.config(font =("Courier", 75))
 #   p1Suit.place(x=p1SuitLocation, y=575)
 #   p1SuitLocation += 200

#p1RankLocation = 100 works but messy
#for i in tkcardRank: #add tkcardRank to player 1, kinda wonky but it is kind of there???
#    p1Rank = tk.Label(root, text = {tkcardRank[2]}, bg= 'white')
#    p1Rank.config(font =("Courier", 75))
#    p1Rank.place(x=p1RankLocation, y=575)
#    p1RankLocation += 200

# Iterate through all unicode values
#for unicodeSuit in unicodeSuit:
#    suitSymbol = tk.Label(root, text = u'{unicodeSuit}', bg = 'blue')
#    suitSymbol.place(x=horzUnicodeSuit, y=50)
 #   horzUnicodeSuit += 50
    
#textToAdd = unicode[index]
#for i in unicodeSuit:
 #   tempU = unicodeSuit[i]
#    p2Suit =tk.Label(root, text = tempU,bg='blue')
#    p2Suit.place(x=horzUnicodeSuit, y=50)
 #   horzUnicodeSuit += 50
 
#bet, fold, call, raise, check
                           


#canvas.create_rectangle(210,10,310,210,outline ="black",fill ="white",width =2)
#maybe have the symbols on each card location change based on the assigned card
#incorperate a timer to switch off to opponent
#flip card method??

#add betting



root.mainloop()
