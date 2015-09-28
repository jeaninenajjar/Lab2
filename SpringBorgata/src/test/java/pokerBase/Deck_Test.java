package pokerBase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class Deck_Test {

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
	public void TestFullDeck() {
		//	I put this test in so it would pass...
		// Code a unit test to make sure calling the deck constructor passes back 52 cards in a deeck.
		Deck tester = new Deck();
		assertTrue("calling the deck constructor passes back 52 cards in a deck", tester.getTotalCards() ==  52);
		tester.drawFromDeck();
		assertTrue("tests to see if draw works", tester.getTotalCards() ==  51);
		//assertTrue("tests to see if draws a card", tester.drawFromDeck() ==  );
	   
		
	}

}
