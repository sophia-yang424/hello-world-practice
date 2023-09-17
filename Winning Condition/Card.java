public class Card {
   private  String number;
    private String suite;
    private int cardId;

    public Card (String number, String suite, int cardId){
        this.number = number;
        this.suite = suite;
        this.cardId = cardId;
    }

    public String getNumber() {
        return number;
    }

    public String getSuite() {
        return suite;
    }

    public int getCardId() {
        return cardId;
    }
}
