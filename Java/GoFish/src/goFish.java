import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

public class goFish {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deck set = new Deck();
        set.shuffle();

        Hand userHand = new Hand(set);
        Hand dealerHand = new Hand(set);

        System.out.println("\n");
        userHand.displayHand();
        System.out.println("\n");
        dealerHand.displayHand();
        System.out.println("\n");

        String askFor = in.nextLine();
        for (int x = 0; x < dealerHand.size(); x++) {
            System.out.println(askFor + " " + dealerHand.getCard(x).idName);
            System.out.println(dealerHand.getCard(x).idName.equals(askFor));
            if (dealerHand.getCard(x).idName.equals(askFor)) {
                System.out.println("should work!");
                Card theCard = new Card(dealerHand.getCard(x).value, dealerHand.getCard(x).suit, dealerHand.getCard(x).name, dealerHand.getCard(x).idName);
                x = dealerHand.removeCard(theCard, userHand, x);
            }
        }
        System.out.println("\n");
        userHand.displayHand();
        System.out.println("\n");
        dealerHand.displayHand();
        System.out.println("\n");
        in.close();
    }

}


class Deck {
    ArrayList<Card> deck;
    String suits[] = {"spades", "hearts", "diamonds", "clubs"};
    String idName;
    Deck() {

        this.deck = new ArrayList<Card>();
        for (int amt = 0; amt < 4; amt++) {
			for (int count = 2; count < 15; count++) {
				if (count == 11) {
                    idName = "jack";
					deck.add(new Card(10, suits[amt], "Jack of " + suits[amt], idName));
				}
				else if (count == 12) {
                    idName = "queen";
					deck.add(new Card(10, suits[amt], "Queen of " + suits[amt], idName));
				}
				else if (count == 13) {
                    idName = "king";
					deck.add(new Card(10, suits[amt], "King of " + suits[amt], idName));
				}
				else if (count == 14) {
                    idName = "ace";
					deck.add(new Card(11, suits[amt], "Ace of " + suits[amt], idName));
				}
				else {
                    idName = Integer.toString(count);
					deck.add(new Card(count, suits[amt], count + " of " + suits[amt], idName));
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
        for (int x = 0; x < 7; x++) {
            this.drawCard();
        }
    }

    public void drawCard() {
        this.hand.add(set.dealTop());
    }

    public void displayHand() {
        for (int x = 0; x < this.hand.size(); x++) {
			System.out.print(this.hand.get(x).name + " | ");
		}
    }

    public int removeCard(Card card, Hand turnPerson, int x) {
        this.hand.remove(x);
        turnPerson.addCard(card);
        return x -= 1;
    }

    public void addCard(Card card) {
        this.hand.add(new Card(card.value, card.suit, card.name, card.idName));
    }

    public int size() {
        return this.hand.size();
    }

    public Card getCard(int x) {
        return this.hand.get(x);
    }
}

class Card {
    int value;
    String suit;
    String name;
    String idName;
    Card(int value, String suit, String name, String idName) {
        this.value = value;
        this.suit = suit;
        this.name = name;
        this.idName = idName;
    }

}