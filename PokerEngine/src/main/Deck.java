package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Deck {
	public static void main(String[] args) {

	/*	Each deck (you could have multiple decks, depending on the game) is an array of cards.
	•	When you instantiate a deck, you should build the array list of cards.  You should also “shuffle” the deck (make the order of cards random).
	•	A method is needed that  will be to “draw” from the deck (return next card… assuming deck is shuffled).
	•	Build a method to denote how many cards are remaining to be drawn.
 
	*/	
	
	
	Integer[] deck1 = new Integer [52];
	
    ArrayList<Integer> deck = new ArrayList<>(Arrays.asList(deck1));

	
    java.util.Collections.shuffle(deck);

	 
	//the method draw
	
	Card c = card();
	c = Deck.get(0);
	Deck.remove(0);
	return c;
	
	
	
	
	
	
	
	
	
	}
}

