package pokerBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;

public class Hand_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestHands() {

		/**
		 * a straight flush including ace, king, queen, jack, and ten all in the
		 * same suit,
		 */
		Deck royalflush = new Deck();

		ArrayList<Card> royal = royalflush.getCards();

		royal.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.ACE, 14));
		royal.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.KING, 13));
		royal.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.QUEEN, 12));
		royal.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.JACK, 11));
		royal.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.TEN, 10));

		Hand royal1 = new Hand();

		Hand royalscorehand = Hand.EvalHand(royal);

		Assert.assertTrue(eHandStrength.RoyalFlush.getHandStrength() == royalscorehand.getHandStrength());

		// straight flush

		Deck straightflush = new Deck();

		ArrayList<Card> straightf = straightflush.getCards();

		straightf.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.FOUR, 4));
		straightf.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.FIVE, 5));
		straightf.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.SIX, 6));
		straightf.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.SEVEN, 7));
		straightf.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.EIGHT, 8));

		Hand straightflush1 = new Hand();

		Hand straightflushscorehand = Hand.EvalHand(straightf);

		Assert.assertTrue(eHandStrength.StraightFlush.getHandStrength() == straightflushscorehand.getHandStrength());

		// four of a kind

		Deck foak = new Deck();

		ArrayList<Card> fourofakind = foak.getCards();

		fourofakind.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.ACE, 14));
		fourofakind.add(new Card(pokerEnums.eSuit.CLUBS, pokerEnums.eRank.ACE, 14));
		fourofakind.add(new Card(pokerEnums.eSuit.SPADES, pokerEnums.eRank.ACE, 14));
		fourofakind.add(new Card(pokerEnums.eSuit.DIAMONDS, pokerEnums.eRank.ACE, 14));
		fourofakind.add(new Card(pokerEnums.eSuit.DIAMONDS, pokerEnums.eRank.FOUR, 4));

		Hand foak1 = new Hand();

		Hand fourofakindscorehand = Hand.EvalHand(fourofakind);

		Assert.assertTrue(eHandStrength.FourOfAKind.getHandStrength() == fourofakindscorehand.getHandStrength());

		// fullhouse

		Deck full = new Deck();

		ArrayList<Card> fullhouse = full.getCards();

		fullhouse.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.EIGHT, 8));
		fullhouse.add(new Card(pokerEnums.eSuit.DIAMONDS, pokerEnums.eRank.EIGHT, 8));
		fullhouse.add(new Card(pokerEnums.eSuit.CLUBS, pokerEnums.eRank.EIGHT, 8));
		fullhouse.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.KING, 13));
		fullhouse.add(new Card(pokerEnums.eSuit.SPADES, pokerEnums.eRank.KING, 13));

		Hand full1 = new Hand();

		Hand fullhousescorehand = Hand.EvalHand(fullhouse);

		Assert.assertTrue(eHandStrength.FullHouse.getHandStrength() == fullhousescorehand.getHandStrength());

		// flush

		Deck flush = new Deck();

		ArrayList<Card> flush1 = flush.getCards();

		flush1.add(new Card(pokerEnums.eSuit.CLUBS, pokerEnums.eRank.TEN, 10));
		flush1.add(new Card(pokerEnums.eSuit.CLUBS, pokerEnums.eRank.FOUR, 4));
		flush1.add(new Card(pokerEnums.eSuit.CLUBS, pokerEnums.eRank.QUEEN, 13));
		flush1.add(new Card(pokerEnums.eSuit.CLUBS, pokerEnums.eRank.SEVEN, 7));
		flush1.add(new Card(pokerEnums.eSuit.CLUBS, pokerEnums.eRank.TWO, 2));

		Hand flush2 = new Hand();

		Hand flushscorehand = Hand.EvalHand(fullhouse);

		Assert.assertTrue(eHandStrength.Flush.getHandStrength() == flushscorehand.getHandStrength());

		// straight

		Deck straight = new Deck();

		ArrayList<Card> straight1 = straight.getCards();

		straight1.add(new Card(pokerEnums.eSuit.CLUBS, pokerEnums.eRank.SEVEN, 10));
		straight1.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.EIGHT, 4));
		straight1.add(new Card(pokerEnums.eSuit.DIAMONDS, pokerEnums.eRank.NINE, 13));
		straight1.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.TEN, 7));
		straight1.add(new Card(pokerEnums.eSuit.SPADES, pokerEnums.eRank.JACK, 2));

		Hand straight2 = new Hand();

		Hand straightscorehand = Hand.EvalHand(straight1);

		Assert.assertTrue(eHandStrength.Straight.getHandStrength() == straightscorehand.getHandStrength());

		// three of a kind

		Deck three = new Deck();

		ArrayList<Card> threeofakind = three.getCards();

		threeofakind.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.QUEEN, 10));
		threeofakind.add(new Card(pokerEnums.eSuit.CLUBS, pokerEnums.eRank.QUEEN, 4));
		threeofakind.add(new Card(pokerEnums.eSuit.DIAMONDS, pokerEnums.eRank.QUEEN, 13));
		threeofakind.add(new Card(pokerEnums.eSuit.SPADES, pokerEnums.eRank.FIVE, 7));
		threeofakind.add(new Card(pokerEnums.eSuit.DIAMONDS, pokerEnums.eRank.ACE, 2));

		Hand threeof = new Hand();

		Hand threeofakindscorehand = Hand.EvalHand(threeofakind);

		Assert.assertTrue(eHandStrength.ThreeOfAKind.getHandStrength() == threeofakindscorehand.getHandStrength());

		// two pair

		Deck two = new Deck();

		ArrayList<Card> twopair = two.getCards();

		twopair.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.THREE, 10));
		twopair.add(new Card(pokerEnums.eSuit.DIAMONDS, pokerEnums.eRank.THREE, 4));
		twopair.add(new Card(pokerEnums.eSuit.CLUBS, pokerEnums.eRank.SIX, 13));
		twopair.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.SIX, 7));
		twopair.add(new Card(pokerEnums.eSuit.SPADES, pokerEnums.eRank.KING, 2));

		Hand two1 = new Hand();

		Hand twopairscorehand = Hand.EvalHand(twopair);

		Assert.assertTrue(eHandStrength.TwoPair.getHandStrength() == twopairscorehand.getHandStrength());

		// ONE pair

		Deck one = new Deck();

		ArrayList<Card> onepair = one.getCards();

		onepair.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.THREE, 10));
		onepair.add(new Card(pokerEnums.eSuit.DIAMONDS, pokerEnums.eRank.THREE, 4));
		onepair.add(new Card(pokerEnums.eSuit.CLUBS, pokerEnums.eRank.FIVE, 13));
		onepair.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.SIX, 7));
		onepair.add(new Card(pokerEnums.eSuit.SPADES, pokerEnums.eRank.KING, 2));

		Hand ONE1 = new Hand();

		Hand onepairscorehand = Hand.EvalHand(onepair);

		Assert.assertTrue(eHandStrength.Pair.getHandStrength() == onepairscorehand.getHandStrength());

		// no pair

		Deck no = new Deck();

		ArrayList<Card> nopair = no.getCards();

		nopair.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.THREE, 10));
		nopair.add(new Card(pokerEnums.eSuit.DIAMONDS, pokerEnums.eRank.SEVEN, 4));
		nopair.add(new Card(pokerEnums.eSuit.CLUBS, pokerEnums.eRank.FIVE, 13));
		nopair.add(new Card(pokerEnums.eSuit.HEARTS, pokerEnums.eRank.SIX, 7));
		nopair.add(new Card(pokerEnums.eSuit.SPADES, pokerEnums.eRank.KING, 2));

		Hand NO1 = new Hand();

		Hand nopairscorehand = Hand.EvalHand(nopair);

		Assert.assertTrue(eHandStrength.HighCard.getHandStrength() == nopairscorehand.getHandStrength());

	}

}
