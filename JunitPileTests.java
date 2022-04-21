import org.junit.Assert;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JunitPileTests {
    private Pile pile;
    private Card card;
    private Card card1;
    private Card card2;


    /*******************************************************************************************
     * This tests the addCard(), removeCard(), removeFirstCard(), removeLastCard(), isEmpty(),
     * getPileSize(), and getLastCard() functions from the Pile class.
     ********************************************************************************************/
    @Test
    public void testPileRemoveCards() {
        pile = new Pile(PileType.FOUNDATION);
        card = new Card(Suit.HEARTS, Rank.ACE);
        card1 = new Card(Suit.SPADES, Rank.FOUR);
        card2 = new Card(Suit.CLUBS, Rank.KING);

        assertTrue(pile.isEmpty());
        assertNull(pile.removeCard(1)); //empty pile so it should return null

        pile.addCard(card);
        pile.addCard(card1);
        pile.addCard(card2);
        assertEquals(pile.getCard(0), card); //pile count starts at zero
        assertFalse(pile.isEmpty()); //pile is not empty anymore so it should be false
        assertEquals(pile.getPileSize(), 3); //should have 3 cards in the pile

        pile.removeFirstCard(); //should remove the first card of the pile
        assertEquals(pile.getCard(0), card1); //the second card should now be the first card
        assertEquals(pile.getPileSize(), 2); //should only have 2 cards in the pile now

        assertEquals(pile.getLastCard(), card2); //the last card should be card2
        pile.removeLastCard(); //removes the last card in the pile
        assertEquals(pile.getLastCard(), card1); //the last card should now be card1
        assertEquals(pile.getPileSize(), 1); //should only have 1 card in the pile now

        pile.removeCard(0); //should remove the first card
        assertTrue(pile.isEmpty()); //should show the pile as empty
        assertNull(pile.removeLastCard()); //this function should return null
        assertNull(pile.getLastCard()); //this function should return null
    }

    /***************************************************************************************
     * This tests the toString() function in the pile class.
     ***************************************************************************************/
    @Test
    public void testPileToString() {
        pile = new Pile(PileType.TABLEAU);
        card = new Card(Suit.HEARTS, Rank.ACE);
        card1 = new Card(Suit.SPADES, Rank.FOUR);
        card2 = new Card(Suit.CLUBS, Rank.KING);
        pile.addCard(card);
        pile.addCard(card1);
        pile.addCard(card2);

        assertEquals(pile.toString(), "[ACE of HEARTS, FOUR of SPADES, KING of CLUBS]");
        //there are three cards in the pile
    }

    /***************************************************************************************************
     * This tests the canTake() function in the Pile class.
     ************************************************************************************************/
    @Test
    public void testPileCanTake() {
        pile = new Pile(PileType.FOUNDATION);
        card = new Card(Suit.HEARTS, Rank.ACE);
        card1 = new Card(Suit.SPADES, Rank.FOUR);
        card2 = new Card(Suit.CLUBS, Rank.KING);
        Card card3 = new Card(Suit.HEARTS, Rank.TWO);
        Card card4 = new Card(Suit.CLUBS, Rank.TWO);
        Pile pile1 = new Pile(PileType.TABLEAU);
        Pile pile2 = new Pile(PileType.WASTE);

        assertFalse(pile.canTake(card1)); //The start of the end piles cannot begin with a four
        assertFalse(pile.canTake(card2)); //The start of the end piles cannot begin with a king
        assertTrue(pile.canTake(card)); //The start of the end piles must begin with an ace
        pile.addCard(card); //adds the first card to the foundation pile
        assertFalse(pile.canTake(card2)); //the second card cannot be a king
        assertFalse(pile.canTake(card4)); //the second card on the same pile cannot be a different suit
        assertTrue(pile.canTake(card3)); //the second card must be a two of the same suit

        assertFalse(pile2.canTake(card)); //the waste pile does not take any card

        assertFalse(pile1.canTake(card)); //the tableau pile only takes kings as the first card
        assertTrue(pile1.canTake(card2)); //the tableau pile only takes kings as the first card
        pile1.addCard(card4); //adds a black two to a pile
        assertTrue(pile1.canTake(card)); //the next card can only be a red ace
        assertFalse(pile1.canTake(card1)); //the next card added to the pile must be a red ace
    }

}
