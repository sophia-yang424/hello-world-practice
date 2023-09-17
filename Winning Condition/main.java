import java.util.ArrayList;

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

        System.out.println();
        int a = winCondition(test.getSharedCard(), test.getPlayer1().getPlayerPersonalHand());
        int b = winCondition(test.getSharedCard(), test.getPlayer2().getPlayerPersonalHand());
        decisionStatement(a,b);
    }

    public static void decisionStatement(int a, int b) {
        String decision1 = "";
        String decision2 = "";
            if(a == 0){
                decision1 += "High Card";
            } else if(a == 1) {
                decision1 += "Pair";
            } else if(a == 2) {
                decision1 += "Two Pair";
            } else if(a == 3) {
                decision1 += "Triple";
            } else if(a == 4) {
                decision1 += "Straight";
            } else if(a == 5) {
                decision1 += "Flush";
            } else if(a == 6) {
                decision1 += "Full House";
            } else if(a == 7) {
                decision1 += "Four of a Kind";
            } else if(a == 1) {
                decision1 += "STRAIGHT FLUSH!!!";
            }

        if(b == 0){
            decision2 += "High Card";
        } else if(b == 1) {
            decision2 += "Pair";
        } else if(b == 2) {
            decision2 += "Two Pair";
        } else if(b == 3) {
            decision2 += "Triple";
        } else if(b == 4) {
            decision2 += "Straight";
        } else if(b == 5) {
            decision2 += "Flush";
        } else if(b == 6) {
            decision2 += "Full House";
        } else if(b == 7) {
            decision2 += "Four of a Kind";
        } else if(b == 1) {
            decision2 += "STRAIGHT FLUSH!!!";
        }

        System.out.println("Player 1 has a " + decision1);
        System.out.println("Player 2 has a " + decision2);
        if(a>b){
            System.out.println("Player 1 Wins!");
        } else if (a<b){
            System.out.println("Player 2 Wins!");
        } else {
            System.out.println("Draw... For Now");
        }
    }

    // 5 card, p1 hand, p2 hand  use + to combine arrays
    public static int winCondition(ArrayList<Card> shared, ArrayList<Card> personal){
        Card[] player1 = new Card[7];
        for(int i=0; i<5; i++){
            player1[i] = shared.get(i);
        }
       player1[5] = personal.get(0);
       player1[6] = personal.get(1);

        boolean[] player1WinCondition = {false, false, false, false, false, false, false, false, false};
        int[] suitTrackerPlayer1 = {0, 0, 0, 0};
        int finalPlayer1HandInNumber = 0;

        // 1. 10~A, 2. 1~5, 3. 9~k, etc....
       /* for (int i = 0; i < 2; i++) { //Filling the array with the final hand of player 1
            player1[i] = test.getPlayer1().getPlayerPersonalHand().get(i);
        }
        for (int i = 2; i < 7; i++) {
            player1[i] = test.getSharedCard().get(i - 2);
        }
        for (int i = 0; i < 2; i++) { //Filling the array with the final hand of player 2
            player1[i] = test.getPlayer1().getPlayerPersonalHand().get(i);
        }
        for (int i = 2; i < 7; i++) {
            player1[i] = test.getSharedCard().get(i - 2);
        }
*/
        //************************//Determining the final hand of Player 1//*********************//
        for (int i = 0; i < 7; i++) { //gathering data of suits for player 1
            if (player1[i].getSuite().equals("Spade")) {
                suitTrackerPlayer1[0]++;
            } else if (player1[i].getSuite().equals("Diamonds")) {
                suitTrackerPlayer1[1]++;
            } else if (player1[i].getSuite().equals("Heart")) {
                suitTrackerPlayer1[2]++;
            } else if (player1[i].getSuite().equals("Club")) {
                suitTrackerPlayer1[3]++;
            }
        }

        //Creating an array that notes how much of each number appears
        int[] numApperanceRecorderPlayer1 = new int[13];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 13; j++) {
                if (Integer.parseInt(player1[i].getNumber()) == j+1) {
                    numApperanceRecorderPlayer1[j]++;
                }
            }
        }


        ArrayList<Card> p1Order = new ArrayList<>();
        //sorting cards of player 1 from lowest to highest based on their number
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < i; j++) {
                if (Integer.parseInt(player1[i].getNumber()) < Integer.parseInt(player1[j].getNumber())) {
                    p1Order.add(j, player1[i]);
                } else {
                    p1Order.add(player1[i]);
                }
            }
        }

        //Removing duplicate from ordered card List
        ArrayList<Card> p1OrderWithoutDuplicate = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            boolean noDuplicate = true;
            for (int j = 0; j < i; j++) {
                if (Integer.parseInt(player1[i].getNumber()) == Integer.parseInt(player1[j].getNumber())) {
                    noDuplicate = false;
                }
            }
            if (noDuplicate) {
                p1OrderWithoutDuplicate.add(player1[i]);
            }
        }

        // boolean variables to check straight
        boolean[] straightChecker = {false, false, false, false, false};

        boolean straightRoyalFlush1 = true;
        String suitForStraight = "";
        //Checking for TopStraight
        for (int i = 0; i < p1OrderWithoutDuplicate.size(); i++) {
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 1) {
                straightChecker[0] = true;
                if(i == 0){
                   suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                   if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                       straightRoyalFlush1 = false;
                   }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 13) {
                straightChecker[1] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush1 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 12) {
                straightChecker[2] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush1 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 11) {
                straightChecker[3] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush1 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 10) {
                straightChecker[4] = true;if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush1 = false;
                    }
                }
            }
        }

        boolean temp = true;
        for (int i = 0; i < straightChecker.length; i++) {
            if (straightChecker[i] == false) {
                temp = false;
            }
        }
        if (temp) {
            player1WinCondition[4] = true;
        }

        for (int i = 0; i < straightChecker.length; i++) {
            straightChecker[i] = false;
        }

        //Checking for 1~5
        boolean straightRoyalFlush2 = true;
        for (int i = 0; i < p1OrderWithoutDuplicate.size(); i++) {
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 1) {
                straightChecker[0] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush2 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 2) {
                straightChecker[1] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush2 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 3) {
                straightChecker[2] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush2 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 4) {
                straightChecker[3] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush2 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 5) {
                straightChecker[4] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush2 = false;
                    }
                }
            }
        }

        temp = true;
        for (int i = 0; i < straightChecker.length; i++) {
            if (straightChecker[i] == false) {
                temp = false;
            }
        }
        if (temp) {
            player1WinCondition[4] = true;
        }

        for (int i = 0; i < straightChecker.length; i++) {
            straightChecker[i] = false;
        }
        //Checking for 9~K
        boolean straightRoyalFlush3 = true;
        for (int i = 0; i < p1OrderWithoutDuplicate.size(); i++) {
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 13) {
                straightChecker[0] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush3 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 12) {
                straightChecker[1] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush3 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 11) {
                straightChecker[2] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush3 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 10) {
                straightChecker[3] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush3 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 9) {
                straightChecker[4] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush3 = false;
                    }
                }
            }
        }

        temp = true;
        for (int i = 0; i < straightChecker.length; i++) {
            if (straightChecker[i] == false) {
                temp = false;
            }
        }
        if (temp) {
            player1WinCondition[4] = true;
        }

        for (int i = 0; i < straightChecker.length; i++) {
            straightChecker[i] = false;
        }

        //checking for 8~Q
        boolean straightRoyalFlush4 = true;
        for (int i = 0; i < p1OrderWithoutDuplicate.size(); i++) {
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 12) {
                straightChecker[0] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush4 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 11) {
                straightChecker[1] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush4 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 10) {
                straightChecker[2] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush4 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 9) {
                straightChecker[3] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush4 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 8) {
                straightChecker[4] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush4 = false;
                    }
                }
            }
        }

        temp = true;
        for (int i = 0; i < straightChecker.length; i++) {
            if (straightChecker[i] == false) {
                temp = false;
            }
        }
        if (temp) {
            player1WinCondition[4] = true;
        }

        for (int i = 0; i < straightChecker.length; i++) {
            straightChecker[i] = false;
        }

        //Checking for 7~J
        boolean straightRoyalFlush5 = true;
        for (int i = 0; i < p1OrderWithoutDuplicate.size(); i++) {
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 11) {
                straightChecker[0] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush5 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 10) {
                straightChecker[1] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush5 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 9) {
                straightChecker[2] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush5 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 8) {
                straightChecker[3] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush5 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 7) {
                straightChecker[4] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush5 = false;
                    }
                }
            }
        }

        temp = true;
        for (int i = 0; i < straightChecker.length; i++) {
            if (straightChecker[i] == false) {
                temp = false;
            }
        }
        if (temp) {
            player1WinCondition[4] = true;
        }

        for (int i = 0; i < straightChecker.length; i++) {
            straightChecker[i] = false;
        }

        //Checking for 6~10
        boolean straightRoyalFlush6 = true;
        for (int i = 0; i < p1OrderWithoutDuplicate.size(); i++) {
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 10) {
                straightChecker[0] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush6 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 9) {
                straightChecker[1] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush6 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 8) {
                straightChecker[2] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush6 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 7) {
                straightChecker[3] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush6 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 6) {
                straightChecker[4] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush6 = false;
                    }
                }
            }
        }

        temp = true;
        for (int i = 0; i < straightChecker.length; i++) {
            if (straightChecker[i] == false) {
                temp = false;
            }
        }
        if (temp) {
            player1WinCondition[4] = true;
        }

        for (int i = 0; i < straightChecker.length; i++) {
            straightChecker[i] = false;
        }

        //Checking for 5~9
        boolean straightRoyalFlush7 = true;
        for (int i = 0; i < p1OrderWithoutDuplicate.size(); i++) {
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 9) {
                straightChecker[0] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush7 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 8) {
                straightChecker[1] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush7 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 7) {
                straightChecker[2] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush7 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 6) {
                straightChecker[3] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush7 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 5) {
                straightChecker[4] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush7 = false;
                    }
                }
            }
        }

        temp = true;
        for (int i = 0; i < straightChecker.length; i++) {
            if (straightChecker[i] == false) {
                temp = false;
            }
        }
        if (temp) {
            player1WinCondition[4] = true;
        }

        for (int i = 0; i < straightChecker.length; i++) {
            straightChecker[i] = false;
        }

        //Check for 4~8
        boolean straightRoyalFlush8 = true;
        for (int i = 0; i < p1OrderWithoutDuplicate.size(); i++) {
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 4) {
                straightChecker[0] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush8 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 5) {
                straightChecker[1] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush8 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 6) {
                straightChecker[2] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush8 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 7) {
                straightChecker[3] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush8 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 8) {
                straightChecker[4] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush8 = false;
                    }
                }
            }
        }

        temp = true;
        for (int i = 0; i < straightChecker.length; i++) {
            if (straightChecker[i] == false) {
                temp = false;
            }
        }
        if (temp) {
            player1WinCondition[4] = true;
        }

        for (int i = 0; i < straightChecker.length; i++) {
            straightChecker[i] = false;
        }

        //Check for 3~7
        boolean straightRoyalFlush9 = true;
        for (int i = 0; i < p1OrderWithoutDuplicate.size(); i++) {
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 7) {
                straightChecker[0] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush9 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 6) {
                straightChecker[1] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush9 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 5) {
                straightChecker[2] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush9 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 4) {
                straightChecker[3] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush9 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 3) {
                straightChecker[4] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush9 = false;
                    }
                }
            }
        }

        temp = true;
        for (int i = 0; i < straightChecker.length; i++) {
            if (straightChecker[i] == false) {
                temp = false;
            }
        }
        if (temp) {
            player1WinCondition[4] = true;
        }

        for (int i = 0; i < straightChecker.length; i++) {
            straightChecker[i] = false;
        }
        //Check for 2~6
        boolean straightRoyalFlush10 = true;
        for (int i = 0; i < p1OrderWithoutDuplicate.size(); i++) {
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 2) {
                straightChecker[0] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush10 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 3) {
                straightChecker[1] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush10 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 4) {
                straightChecker[2] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush10 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 5) {
                straightChecker[3] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush10 = false;
                    }
                }
            }
            if (Integer.parseInt(p1OrderWithoutDuplicate.get(i).getNumber()) == 6) {
                straightChecker[4] = true;
                if(i == 0){
                    suitForStraight += p1OrderWithoutDuplicate.get(i).getSuite();
                } else {
                    if(!p1OrderWithoutDuplicate.get(i).getSuite().equals(suitForStraight)){
                        straightRoyalFlush10 = false;
                    }
                }
            }
        }

        temp = true;
        for (int i = 0; i < straightChecker.length; i++) {
            if (straightChecker[i] == false) {
                temp = false;
            }
        }
        if (temp) {
            player1WinCondition[4] = true;
        }

        for (int i = 0; i < straightChecker.length; i++) {
            straightChecker[i] = false;
        }

        //--------------------------------------------//
        //Checking for Flush and straight flush


        for (int i = 0; i < suitTrackerPlayer1.length; i++) {
            if (suitTrackerPlayer1[i] >= 5 && player1WinCondition[4] && (straightRoyalFlush1 || straightRoyalFlush2 ||
                    straightRoyalFlush3 || straightRoyalFlush4 || straightRoyalFlush5 || straightRoyalFlush5 ||
                    straightRoyalFlush6 || straightRoyalFlush7 || straightRoyalFlush8 || straightRoyalFlush9 ||
                    straightRoyalFlush10)) {
                player1WinCondition[8] = true;
            } else if (suitTrackerPlayer1[i] >= 5) {
                player1WinCondition[5] = true;
            }
        }

        // Creating helpful arrays for rest of the test cases
        int numPairsPlayer1 = 0;
        int numTriplesPlayer1 = 0;


        for (int i = 0; i < 13; i++) {
            if (numApperanceRecorderPlayer1[i] == 4) { //Checking for four of a kind
                player1WinCondition[7] = true;
                break;
            } else if (numApperanceRecorderPlayer1[i] == 3) {
                numTriplesPlayer1++;
            } else if (numApperanceRecorderPlayer1[i] == 2) {
                numPairsPlayer1++;
            }
        }

        //Checking for a full house
        if (numTriplesPlayer1 >= 1 && numTriplesPlayer1 + numPairsPlayer1 >= 2) {
            player1WinCondition[6] = true;
        }

        //Checking for a triple
        if (numTriplesPlayer1 >= 1) {
            player1WinCondition[3] = true;
        }

        //Checking for a twoPair
        if (numPairsPlayer1 >= 2) {
            player1WinCondition[2] = true;
        }

        //Checking for a pair
        if (numPairsPlayer1 >= 1) {
            player1WinCondition[1] = true;
        }

        //Checking for a pair
        player1WinCondition[0] = true;

        for (int i = player1WinCondition.length - 1; i >= 0; i--) {
            if (player1WinCondition[i]) {
                finalPlayer1HandInNumber = i;
                break;
            }
        }
        return finalPlayer1HandInNumber;
    }
}
