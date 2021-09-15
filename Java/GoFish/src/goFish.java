import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
        boolean playerTurn = true;
        
        while (userBooks + aiBooks < 13) {
            userHand.displayHand();
            if (userHand.size() < 7) {
                int amt = 7 - userHand.size();
                for (int x = 0; x < amt; x++) {
                    userHand.drawCard();
                }
            }
            if (dealerHand.size() < 7) {
                int amt = 7 - dealerHand.size();
                for (int x = 0; x < amt; x++) {
                    dealerHand.drawCard();
                }
            }
            clearScr();
            userHand.sortHand();
            dealerHand.sortHand();
            if (playerTurn == true) {
                System.out.println("Your Hand:\n");
                userHand.displayHand();
                System.out.println();
                System.out.println("Your books:\n" + userBooks + "\nComputer's books\n" + aiBooks);
                dealerHand.displayHand();
                System.out.println("\n");
                System.out.println("What card would you like to take?\n");
                String askFor = in.nextLine();
                if (userHand.checkIfInHand(askFor) == true) {
                    int tempSize = userHand.size();
                    if (dealerHand.takeCard(askFor, userHand) == true) {
                        if (userHand.size() > tempSize) {
                            System.out.println("You took " + (userHand.size() - tempSize) + " " + askFor + "(s) from the computer.");
                        }
                        int tempBooks = userBooks;
                        userBooks += userHand.makeBook(askFor);
                        if (userBooks > tempBooks) {
                            System.out.println("You made " + (userBooks - tempBooks) + " book(s). You now have " + userBooks + " book(s).");
                        }
                        playerTurn = true;
                        for (int x = 0; x < userHand.size(); x++) {
                            userHand.otherRemove(userHand.getCard(x), askFor, x);
                        }
                        Thread.sleep(1500);
                    }
                    else {
                        System.out.println("The computer did not have any " + askFor + "s in hand. You drew a card");
                        userHand.drawCard();
                        userBooks += userHand.makeBook(userHand.getCard(userHand.size()).idName);
                    
                        //playerTurn = false;
                        Thread.sleep(1500);
                    }
                }
                else {
                    System.out.println("You do not have a " + askFor + " in your hand. Unfortunately, I don't know a good way to handle this yet so I guess you just lose for being a dumbass.");
                    break;
                }
            }
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

    public int removeCard(Card card, Hand turnPerson, int x) {
        this.hand.remove(x);
        turnPerson.addCard(card);
        return x -= 1;
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

    public int makeBook(String askFor) {
        int booksMade = 0;
        int occur = 0;
        for (int x = 0; x < this.hand.size(); x++) {
            if (this.hand.get(x).idName.equals(askFor)) {
                occur++;
            }
        }
        if (occur >= 4 ) {
            booksMade++;
        }
        return booksMade;
    }

    public boolean checkIfInHand(String askFor) {
        for (int x = 0; x < this.hand.size(); x++) {
            if (this.hand.get(x).idName.equals(askFor)) {
                return true;
            }
        }
        return false;
    }

    public boolean takeCard(String askFor, Hand takerHand) {
        boolean didTake = false;
        for (int x = 0; x < this.hand.size(); x++) {
            if (this.hand.get(x).idName.equals(askFor)) {
                Card theCard = new Card(this.hand.get(x).value, this.hand.get(x).idName);
                x = this.removeCard(theCard, takerHand, x);
                didTake = true;
            }
        }
        if (didTake) {
            return true;
        }
        return false;
    }

    public void otherRemove(Card theCard, String askFor, int index) {
        if (theCard.idName.equals(askFor)) {
            this.hand.remove(index);
        }
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