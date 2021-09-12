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

        userHand.displayHand();
        System.out.println("\n\n");
        dealerHand.displayHand();
    }

}


class Deck {
    ArrayList<Card> deck;
    String suits[] = {"spades", "hearts", "diamonds", "clubs"};
    Deck() {

        this.deck = new ArrayList<Card>();
        for (int amt = 0; amt < 4; amt++) {
			for (int count = 2; count < 15; count++) {
				if (count == 11) {
					deck.add(new Card(10, suits[amt], "Jack of " + suits[amt]));
				}
				else if (count == 12) {
					deck.add(new Card(10, suits[amt], "Queen of " + suits[amt]));
				}
				else if (count == 13) {
					deck.add(new Card(10, suits[amt], "King of " + suits[amt]));
				}
				else if (count == 14) {
					deck.add(new Card(11, suits[amt], "Ace of " + suits[amt]));
				}
				else {
					deck.add(new Card(count, suits[amt], count + " of " + suits[amt]));
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
}

class Card {
    int value;
    String suit;
    String name;
    Card(int value, String suit, String name) {
        this.value = value;
        this.suit = suit;
        this.name = name;
    }

}