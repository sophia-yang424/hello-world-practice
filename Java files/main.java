public class main {
    public static void main(String[] args) {

        Deck test = new Deck();

        test.shuffelDeck();
       /* for(int i=0; i<test.getDeck().length; i++){
            System.out.println(test.getDeck()[i].getNumber());
        }*/

        test.dealPreFlop();
        System.out.print("Player 1's hand: ");
        test.getPlayer1().printPlayerHand();
        System.out.println();
        System.out.print("Player 2's hand: ");
        test.getPlayer2().printPlayerHand();
        System.out.println();

        System.out.println("Public Card: ");
        test.dealFlop();
        test.printPublicCardFlop();
        test.dealTurn();
        test.printPublicCardTurn();
        test.dealTurn();
        test.printPublicCardRiver();


    }
}
