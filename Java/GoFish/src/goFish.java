import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class goFish {

    public static void main(String[] args) throws InterruptedException {
        // Create input object
        Scanner in = new Scanner(System.in);

        // Create gamepaly objects
        Deck set = new Deck();
        set.shuffle();
        Hand userHand = new Hand(set);
        Hand dealerHand = new Hand(set);

        //Prep player hands to be used
        userHand.sortHand();
        dealerHand.sortHand();

        // Clears screen to look good
        clearScr();

        // Driver code for gameplay
        
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

}

class Hand {
    ArrayList<Card> hand;

    Deck set;

    public Hand(Deck set) {
        this.set = set;
        this.hand = new ArrayList<Card>();
        for (int x = 0; x < 26; x++) {
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

    public void makeBook() {
        for (int x = 0; x < this.hand.size(); x++) {
            int value = this.hand.get(x).value;
            int occur = 0;
            for (int y = 0; y < this.hand.size(); y++) {
                if (this.hand.get(y).value == value) {
                    occur++;
                }
                if (occur == 4) {
                    for (int i = 0; i < this.hand.size(); i++) {
                        if (this.hand.get(i).value == value) {
                            this.hand.remove(i);
                        }
                    }
                }
            }
        }
    }

    public boolean checkIfInHand(String askFor) {
        for (int x = 0; x < this.hand.size(); x++) {
            if (this.hand.get(x).idName.equals(askFor)) {
                return true;
            }
        }
        return false;
    }

    public void takeCard(String askFor, Hand takerHand) {
        for (int x = 0; x < this.hand.size(); x++) {
            if (this.hand.get(x).idName.equals(askFor)) {
                Card theCard = new Card(this.hand.get(x).value, this.hand.get(x).idName);
                x = this.removeCard(theCard, takerHand, x);
            }
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