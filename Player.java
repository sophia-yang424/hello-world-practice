import java.util.ArrayList;
public class Player {
    private ArrayList<Card> playerPersonalHand = new ArrayList<Card>();
    //private ArrayList<Integer> playerHand = new ArrayList<Integer>();

    //private double money;

    private int numCards;

    public Player(){
    }


    public void setPersonPersonalHand(Card card) {
        playerPersonalHand.add(card);
    }

    public void printPlayerHand() {
        for(int i=0; i<2; i++){
            System.out.print(playerPersonalHand.get(i).getNumber() + " " + playerPersonalHand.get(i).getSuite() + "  ");
        }
    }
}



// Money, Receive two cards, betting,