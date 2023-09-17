import java.lang.reflect.Array;
import java.util.ArrayList;

public class Deck {
    private Card[] deck;
    private String[] suits = {"Spade", "Diamonds", "Heart", "Club"};
    private String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};

    private Player player1;
    private Player player2;
    private ArrayList<Card> sharedCard = new ArrayList<>();

    private int currentCardNumber = 0; // used to count the location of card in the deck in this game

    public Deck() {
        player1 = new Player();
        player2 = new Player();
        deck = new Card[52];
        int cardCounter = 0;

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                Card card = new Card(numbers[i], suits[j], cardCounter);
                deck[cardCounter] = card;
                cardCounter++;

            }
        }
    }

    public void resetDeck(){
        int cardCounter = 0;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                Card card = new Card(numbers[i], suits[j], cardCounter);
                deck[cardCounter] = card;
                cardCounter++;
            }
        }
        shuffelDeck();
    }

    public void shuffelDeck() {
        int randomNum1 = (int) (Math.random() * 52);
        int randomNum2 = (int) (Math.random() * 52);
        Card tempCard = null;

        for (int i = 0; i < 3333; i++) {
            tempCard = deck[randomNum1];
            deck[randomNum1] = deck[randomNum2];
            deck[randomNum2] = tempCard;
            randomNum1 = (int) (Math.random() * 52);
            randomNum2 = (int) (Math.random() * 52);
        }
    }


    public void printPublicCardFlop(){
        for(int i=0; i<3; i++){
            System.out.print(sharedCard.get(i).getNumber() + " " + sharedCard.get(i).getSuite() + "  ");
        }
    }

    public void printPublicCardTurn(){
        System.out.print(sharedCard.get(3).getNumber() + " " +  sharedCard.get(3).getSuite() + "  ");
    }

    public void printPublicCardRiver(){
        System.out.print(sharedCard.get(4).getNumber() + " " + sharedCard.get(4).getSuite() + "  ");
    }

    public Card[] getDeck() {
        return deck;
    }

    public void dealPreFlop() {
        for (int i = 0; i < 2; i++) {
            player1.setPersonPersonalHand(deck[currentCardNumber]);
            currentCardNumber++;
        }
        for (int i = 0; i < 2; i++) {
            player2.setPersonPersonalHand(deck[currentCardNumber]);
            currentCardNumber++;
        }
    }

    public void dealFlop() {
        for (int i = 0; i < 3; i++) {
            sharedCard.add(deck[currentCardNumber]);
            currentCardNumber++;
        }
    }

    public void dealTurn() {
        sharedCard.add(deck[currentCardNumber]);
        currentCardNumber++;
    }

    public void dealRiver() {
        sharedCard.add(deck[currentCardNumber]);
        currentCardNumber++;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public ArrayList<Card> getSharedCard() {
        return sharedCard;
    }

}
