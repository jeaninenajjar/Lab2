package main;

import pokerEnums.eRank;

public class Card {

	private String suit; // Each card has a suit (heart, spade, club diamond)
	// Each card has a numeric rank (A, 2, 3, 4.. 10, J, Q, K,
						// A)

//ace is 11, jack is 12, king is 13, queen is 14.


	// Constructor
	public Card(String s, rank r) {
		this.suit = s;
		this.rank = r;
	}

	

	// Attributes should be private, without public setter / getters

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	
	
	
	
	
	
	
	//replace public enum rank with @ XMLElement
	  //                              private eRank Rank;
	
	
	
	
	
	
	
}





