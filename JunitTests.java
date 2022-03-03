import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;



public class JunitTests {
    private Card card;
    private Deck deck;
    private Pile pile;

    @Test
    public void testCardSetup() {
        card = new Card(Suit.SPADES,Rank.ACE);
        assertEquals(card.getSuit(), Suit.SPADES);
        assertEquals(card.getRank(), 1);

        card.setRank(Rank.KING);
        assertEquals(card.getRank(), 13);

        card.setSuit(Suit.HEARTS);
        assertEquals(card.getSuit(), Suit.HEARTS);
    }

    @Test
    public void testCardFlipping() {
        card = new Card(Suit.SPADES, Rank.ACE);
        assertTrue(card.getIsFlipped());

        card.setIsFlipped(false);
        assertFalse(card.getIsFlipped());
    }

    @Test
    public void testCardToString() {
        card = new Card(Suit.DIAMONDS, Rank.NINE);
        assertEquals(card.toString(), "b");

        card.setIsFlipped(false);
        assertEquals(card.toString(), "NINE of DIAMONDS");
    }

    @Test
    public void testDeckSize() {
        deck = new Deck();
        assertEquals(deck.deckSize(), 52);
    }

    @Test
    public void testDeckisEmpty() {
        deck = new Deck();
        pile = new Pile(PileType.WASTE);
        assertFalse(deck.isEmpty());

        deck.dealToPile(pile, 1);
        assertFalse(deck.isEmpty());

        deck.dealToPile(pile, 51);
        assertTrue(deck.isEmpty());
    }

    @Test
    public void test() {
        //can add a specific card to the deck when it is empty and then test the string

    }
    
}
