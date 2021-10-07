import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
//TODO: fix bug with AI taking the same card
//TODO: fix timings of text and appearance of the game

public class goFish {

    public static void main(String[] args) throws InterruptedException {

        clearScr();

        Scanner in = new Scanner(System.in);

        Deck set = new Deck();
        set.shuffle();
        
        Hand userHand = new Hand(set);
        Hand dealerHand = new Hand(set);
        
        int userBooks = 0;
        int aiBooks = 0;
        int totalBooks = userBooks + aiBooks;
        
        boolean playerTurn = true;
        
        clearScr();

        userHand.sortHand();
        dealerHand.sortHand();

        System.out.println("Your hand:");
        System.out.print("---------------\n");
        userHand.displayHand();
        System.out.println();
        System.out.println();
        System.out.println("Dealer's hand:");
        System.out.print("---------------\n");
        dealerHand.displayHand();
        System.out.println("\n\nYour books: " + userBooks + "\nDealer's books: " + aiBooks);
        
        while (totalBooks < 13) {
            String randChoice = dealerHand.getCard((int) Math.random() * dealerHand.size()).idName;
            if (playerTurn) {
                System.out.println();
                String askFor = in.nextLine();
                if (dealerHand.checkIfInHand(askFor) == true) {
                    int amtTaken = 0;
                    for (int y = 0; y < dealerHand.size(); y++) {
                        if (dealerHand.getCard(y).idName.equals(askFor)) {
                            Card theCard = new Card(dealerHand.getCard(y).value, dealerHand.getCard(y).idName);
                            userHand.addCard(theCard);
                            dealerHand.removeCard(y);
                            y--;
                            amtTaken++;
                        }
                    }
                    System.out.println("You took " + amtTaken + " " + askFor + "(s) from the computer!");
                    Thread.sleep(2000);
                    clearScr();
                    int occur = 0;
                    for (int x = 0; x < userHand.size(); x++) {
                        if (userHand.getCard(x).idName.equals(askFor)) {
                            occur++;
                        }
                        if (occur == 4) {
                            userBooks++;
                            System.out.println("You have a set of " + askFor + "s. You now have " + userBooks + " books.");
                            Thread.sleep(2000);
                            clearScr();
                            for (int i = 0; i < userHand.size(); i++) {
                                if (userHand.getCard(i).idName.equals(askFor)) {
                                    userHand.removeCard(i);
                                    i--;
                                }
                            }
                        }
                    }
                }
                else {
                    clearScr();
                    System.out.println("The computer did not have any " + askFor + "s. Here's a free pity draw.");
                    userHand.drawCard();
                    System.out.println("You drew a " + userHand.getCard(userHand.size() - 1).idName + ".");
                    Thread.sleep(2000);
                    clearScr();
                    int occur = 0;
                    for (int x = 0; x < userHand.size(); x++) {
                        if (userHand.getCard(x).idName.equals(askFor)) {
                            occur++;
                        }
                        if (occur == 4) {
                            userBooks++;
                            System.out.println("You have a set of " + askFor + "s. You now have " + userBooks + " books.");
                            Thread.sleep(2000);
                            clearScr();
                            for (int i = 0; i < userHand.size(); i++) {
                                if (userHand.getCard(i).idName.equals(askFor)) {
                                    userHand.removeCard(i);
                                    i--;
                                }
                            }
                        }
                    }
                    playerTurn = false;
                }
            }
            else {
                clearScr();
                System.out.println("The dealer asked for " + randChoice + ".");
                Thread.sleep(3500);
                if (userHand.checkIfInHand(randChoice) == true) {
                    int amtTaken = 0;
                    for (int y = 0; y < userHand.size(); y++) {
                        if (userHand.getCard(y).idName.equals(randChoice)) {
                            Card theCard = new Card(userHand.getCard(y).value, userHand.getCard(y).idName);
                            dealerHand.addCard(theCard);
                            userHand.removeCard(y);
                            y--;
                            amtTaken++;
                        }
                    }
                    System.out.println("The dealer took " + amtTaken + " " + randChoice + "(s) from you!");
                    Thread.sleep(3500);
                    clearScr();
                    int occur = 0;
                    for (int x = 0; x < dealerHand.size(); x++) {
                        if (dealerHand.getCard(x).idName.equals(randChoice)) {
                            occur++;
                        }
                        if (occur == 4) {
                            aiBooks++;
                            System.out.println("The dealer has a set of " + randChoice + "s. They now have " + aiBooks + " books.");
                            Thread.sleep(3500);
                            clearScr();
                            for (int i = 0; i < dealerHand.size(); i++) {
                                if (dealerHand.getCard(i).idName.equals(randChoice)) {
                                    dealerHand.removeCard(i);
                                    i--;
                                }
                            }
                        }
                    }
                }
                else {
                    clearScr();
                    System.out.println("You did not have any " + randChoice + "s. The dealer gets a free pity draw.");
                    dealerHand.drawCard();
                    Thread.sleep(3500);
                    System.out.println("They drew a " + dealerHand.getCard(dealerHand.size() - 1).idName + ".");
                    Thread.sleep(3500);
                    clearScr();
                    int occur = 0;
                    for (int x = 0; x < dealerHand.size(); x++) {
                        if (dealerHand.getCard(x).idName.equals(randChoice)) {
                            occur++;
                        }
                        if (occur == 4) {
                            aiBooks++;
                            System.out.println("The dealer has a set of " + randChoice + "s. They now have " + aiBooks + " books.");
                            Thread.sleep(3500);
                            clearScr();
                            for (int i = 0; i < dealerHand.size(); i++) {
                                if (dealerHand.getCard(i).idName.equals(randChoice)) {
                                    dealerHand.removeCard(i);
                                    i--;
                                }
                            }
                        }
                    }
                    playerTurn = true;
                }
            }

        userHand.sortHand();
        dealerHand.sortHand();

        System.out.println("Your hand:");
        System.out.print("---------------\n");
        userHand.displayHand();
        System.out.println();
        System.out.println();
        System.out.println("Dealer's hand:");
        System.out.print("---------------\n");
        dealerHand.displayHand();
        System.out.println("\n\nYour books: " + userBooks + "\nDealer's books: " + aiBooks);
        
    }
    in.close();
}

    
    public static void clearScr() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
}


class Deck {
    ArrayList<Card> deck;
    String nameLists[] = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
    String idName;
    Deck() {

        this.deck = new ArrayList<Card>();
        for (int amt = 0; amt < 4; amt++) {
			for (int count = 2; count < 15; count++) {
				if (count == 11) {
                    idName = "jack";
					deck.add(new Card(11, idName));
				}
				else if (count == 12) {
                    idName = "queen";
					deck.add(new Card(12, idName));
				}
				else if (count == 13) {
                    idName = "king";
					deck.add(new Card(13, idName));
				}
				else if (count == 14) {
                    idName = "ace";
					deck.add(new Card(14, idName));
				}
				else {
                    idName = nameLists[count - 1];
					deck.add(new Card(count, idName));
				}
			}
		}
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card dealTop() {
		Card card = this.deck.get(0);
		this.deck.remove(0);
		return card;
	}
    public int size() {
        return this.deck.size();
    }
}

class Hand {
    ArrayList<Card> hand;

    Deck set;

    public Hand(Deck set) {
        this.set = set;
        this.hand = new ArrayList<Card>();
        for (int x = 0; x < 15; x++) {
            this.drawCard();
        }
    }

    public void drawCard() {
        this.hand.add(set.dealTop());
    }

    public void sortHand() {
        int n = this.hand.size();
        for (int i = 0; i < n-1; i++) {   
            for (int j = 0; j < n-i-1; j++) {
                if (this.hand.get(j).value > this.hand.get(j + 1).value) {
                    Card temp = this.hand.get(j);
                    Card temp2 = this.hand.get(j + 1);
                    this.hand.set(j, temp2);
                    this.hand.set(j + 1, temp);
                }
            }
        }
    }
    public void displayHand() {
        for (int x = 0; x < this.hand.size(); x++) {
            System.out.print(this.hand.get(x).idName + " | ");
        }
    }

    public void removeCard(int x) {
        this.hand.remove(x);
    }

    public void addCard(Card card) {
        this.hand.add(new Card(card.value, card.idName));
    }

    public int size() {
        return this.hand.size();
    }

    public Card getCard(int x) {
        return this.hand.get(x);
    }

    public boolean checkIfInHand(String askFor) {
        for (int x = 0; x < this.hand.size(); x++) {
            if (this.hand.get(x).idName.equals(askFor)) {
                return true;
            }
        }
        return false;
    }

}

class Card {
    int value;
    String idName;
    Card(int value, String idName) {
        this.value = value;
        this.idName = idName;
    }

}