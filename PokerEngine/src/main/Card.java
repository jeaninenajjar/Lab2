package main;

public class Card {

	private String suit; // Each card has a suit (heart, spade, club diamond)
	// Each card has a numeric rank (A, 2, 3, 4.. 10, J, Q, K,
						// A)
	
//ace is 11, jack is 12, king is 13, queen is 14.
public enum rank {
   A(11) , J(12), K(13), Q(14), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
   EIGHT(8), NINE(9), TEN(10);
	private int value;
	 
	private rank(int value) {
		this.value = value;
	}
}

private rank rank;

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

	public rank getRank() {
		return rank;
	}

	public void setRank(rank rank) {
		this.rank = rank;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}





