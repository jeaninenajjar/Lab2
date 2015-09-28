package pokerBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;

import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;

public class Hand {
	private UUID playerID;
	@XmlElement
	private ArrayList<Card> CardsInHand;
	private ArrayList<Card> BestCardsInHand;

	@XmlElement
	private int HandStrength;
	@XmlElement
	private int HiHand;
	@XmlElement
	private int LoHand;
	@XmlElement
	private ArrayList<Card> Kicker = new ArrayList<Card>();

	private boolean bScored = false;

	private boolean Flush;
	private boolean Straight;
	private boolean Ace;
	private static Deck dJoker = new Deck();

	public Hand()
	{
		
	}
	public void  AddCardToHand(Card c)
	{
		if (this.CardsInHand == null)
		{
			CardsInHand = new ArrayList<Card>();
		}
		this.CardsInHand.add(c);
	}
	
	public Card  GetCardFromHand(int location)
	{
		return CardsInHand.get(location);
	}
	
	public Hand(Deck d) {
		ArrayList<Card> Import = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			Import.add(d.drawFromDeck());
		}
		CardsInHand = Import;


	}


	public Hand(ArrayList<Card> setCards) {
		this.CardsInHand = setCards;
	}

	public ArrayList<Card> getCards() {
		return CardsInHand;
	}

	public ArrayList<Card> getBestHand() {
		return BestCardsInHand;
	}

	public void setPlayerID(UUID playerID)
	{
		this.playerID = playerID;
	}
	public UUID getPlayerID()
	{
		return playerID;
	}
	public void setBestHand(ArrayList<Card> BestHand) {
		this.BestCardsInHand = BestHand;
	}

	public int getHandStrength() {
		return HandStrength;
	}


	public ArrayList<Card> getKicker() {
		return Kicker;
	}

	public int getHighPairStrength() {
		return HiHand;
	}

	public int getLowPairStrength() {
		return LoHand;
	}

	public boolean getAce() {
		return Ace;
	}

	public static Hand EvalHand(ArrayList<Card> SeededHand) {
		
		Deck d = new Deck();
		Hand h = new Hand(d);
		h.CardsInHand = SeededHand;

		return h;
	}

	public void EvalHand() {
		// Evaluates if the hand is a flush and/or straight then figures out
		// the hand's strength attributes

		// Sort the cards!
		ArrayList<Card> remainingcards = new ArrayList<Card>();
		Collections.sort(CardsInHand, Card.CardRank);

		// Ace Evaluation
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == eRank.ACE) {
			Ace = true;
		}

		// Flush Evaluation
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getSuit()) {
			Flush = true;
		} else {
			Flush = false;
		}

		// five of a Kind

		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FifthCard.getCardNo()).getRank()) {
			remainingcards = null ;
			ScoreHand(eHandStrength.FiveOfAKind,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0, remainingcards);
		}

		// Straight Evaluation
		else if (Ace) {
			// Looks for Ace, King, Queen, Jack, 10
			if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == eRank.KING
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.QUEEN
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo())
							.getRank() == eRank.JACK
					&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN) {
				Straight = true;
				// Looks for Ace, 2, 3, 4, 5
			} else if (CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TWO
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo())
							.getRank() == eRank.THREE
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.FOUR
					&& CardsInHand.get(eCardNo.SecondCard.getCardNo())
							.getRank() == eRank.FIVE) {
				Straight = true;
			} else {
				Straight = false;
			}
			// Looks for straight without Ace
		} else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
				.getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo())
				.getRank().getRank() + 1
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() + 2
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank()
						.getRank() + 3
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank().getRank() + 4) {
			Straight = true;
		} else {
			Straight = false;
		}

		// Evaluate Royal Flush
		if (Straight == true
				&& Flush == true
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN
				&& Ace) {
			
			remainingcards = null;
			ScoreHand(eHandStrength.RoyalFlush, 0, 0, remainingcards);
		}

		// Straight Flush
		else if (Straight == true && Flush == true) {
			remainingcards = null;
			ScoreHand(eHandStrength.StraightFlush,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0, remainingcards);
		}
		// Four of a Kind
		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank()
				== CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank()
				== CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank())
		{  
			remainingcards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			
			
			
			ScoreHand(eHandStrength.FourOfAKind, 
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 
					0, 
					remainingcards);
		}
		else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()
				== CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()
				== CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank())
		{
			
			ScoreHand(eHandStrength.FourOfAKind, 
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(), 
					0, 
					remainingcards);
		}

		// Full House
		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()
			&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank()
				== CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()
			&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()
				== CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank())
				
		{    
			remainingcards = null;
			ScoreHand(eHandStrength.FullHouse, 
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(), 
					remainingcards);
		}
		else if(CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()
			&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()
				== CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()
			&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank()
				== CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank())
				
		{
			remainingcards = null;
			ScoreHand(eHandStrength.FullHouse, 
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(), 
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 
					remainingcards);
		}

		// Flush
		else if (Flush == true)
		{
			
			remainingcards = null;
			ScoreHand(eHandStrength.Flush, 
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 
					0, 
					remainingcards);
		}
		
		// Straight
		else if(Straight == true)
		{
			remainingcards = null ;
			ScoreHand(eHandStrength.Straight,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					0,
					remainingcards);
		}

		// Three of a Kind
		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank()
				== CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank())
		{
			
			remainingcards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingcards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));

			ScoreHand(eHandStrength.ThreeOfAKind,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(),
					remainingcards);
			
	
		}
		

		else if(CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()
				== CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank())
		{
			
			remainingcards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingcards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
			ScoreHand(eHandStrength.ThreeOfAKind,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					remainingcards);	
		}
		
		else if(CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()
				== CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank())
		{
			
			remainingcards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
			remainingcards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			ScoreHand(eHandStrength.ThreeOfAKind,
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					remainingcards);	
		}
		
		// Two Pair
		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()
			&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank())
		{
			
			remainingcards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			
			ScoreHand(eHandStrength.TwoPair,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(),
					remainingcards);
			
		}
		
		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()
			&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank())
		{
			
			remainingcards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
			ScoreHand(eHandStrength.TwoPair,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(),
					remainingcards);
			
		}
		else if(CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()
			&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank())
		{
			remainingcards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			ScoreHand(eHandStrength.TwoPair,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(),
					remainingcards);
			
		}
		

		// Pair
		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank())
		{
			
			remainingcards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
			remainingcards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
			remainingcards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			ScoreHand(eHandStrength.Pair,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					0,
					remainingcards);
		}
		else if(CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank())
		{
			
			remainingcards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingcards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
			remainingcards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			ScoreHand(eHandStrength.Pair,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(),
					0,
					remainingcards);
		}
		else if(CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank())
		{
			
			remainingcards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingcards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
			remainingcards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			ScoreHand(eHandStrength.Pair,
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(),
					0,
					remainingcards);
		}
		else if(CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() 
				== CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank())
		{
			remainingcards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingcards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
			remainingcards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
			ScoreHand(eHandStrength.Pair,
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(),
					0,
					remainingcards);
		}

		// High Card
		//	I'll give you this one :)
		
		
		else {
			
			
			ScoreHand(eHandStrength.HighCard,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
					remainingcards);
		}
	}



	private void ScoreHand(eHandStrength hST, int HiHand, int LoHand, ArrayList<Card> Kicker) {
		this.HandStrength = hST.getHandStrength();
		this.HiHand = HiHand;
		this.LoHand = LoHand;
		this.Kicker = Kicker;
		this.bScored = true;

	}

	/**
	 * Custom sort to figure the best hand in an array of hands
	 */
	public static Comparator<Hand> HandRank = new Comparator<Hand>() {

		public int compare(Hand h1, Hand h2) {

			int result = 0;

			result = h2.getHandStrength() - h1.getHandStrength();

			if (result != 0) {
				return result;
			}

			result = h2.getHighPairStrength() - h1.getHighPairStrength();
			if (result != 0) {
				return result;
			}

			result = h2.getLowPairStrength() - h1.getLowPairStrength();
			if (result != 0) {
				return result;
			}

			result = h2.getKicker() - h1.getKicker();
			if (result != 0) {
				return result;
			}

			return 0;
		}
	};

}