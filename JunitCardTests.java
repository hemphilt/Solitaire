import org.junit.Assert;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class JunitCardTests {
    private Card card;

    /**************************************************************
     * This tests the setup of the individual cards. It tests
     * the setSuit(), getSuit(), setRank(), and getRank() setters
     * and getters in the card class.
     **************************************************************/
    @Test
    public void testCardSetup() {
        card = new Card(Suit.SPADES, Rank.ACE);
        assertEquals(card.getSuit(), Suit.SPADES);
        assertEquals(card.getRank(), 1);

        card.setRank(Rank.KING);
        assertEquals(card.getRank(), 13);

        card.setSuit(Suit.HEARTS);
        assertEquals(card.getSuit(), Suit.HEARTS);
    }

    /***********************************************************************
     * This tests the functions that cause the cards to flip.
     * It sets up a card then tests if it is flipped or not
     * using the setIsFlipped() and getIsFlipped() setters and getters in the
     * card class.
     ************************************************************************/
    @Test
    public void testCardFlipping() {
        card = new Card(Suit.SPADES, Rank.ACE);
        assertTrue(card.getIsFlipped());

        card.setIsFlipped(false);
        assertFalse(card.getIsFlipped());
    }

    /***********************************************************************
     * This tests if the toString() function in the Card class.
     ***********************************************************************/
    @Test
    public void testCardToString() {
        card = new Card(Suit.DIAMONDS, Rank.NINE); //card is automatically on the backside
        assertEquals(card.toString(), "NINE of DIAMONDS");

        card.setIsFlipped(false); //flips over the card
        assertEquals(card.toString(), "NINE of DIAMONDS");
    }
}
