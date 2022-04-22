import org.junit.Test;
import static org.junit.Assert.*;

public class JunitDeckTests {

    private Deck deck;
    private Pile pile;
    private Card card;
    /*******************************************************************
     * This tests that when a deck is created, it creates all 52 cards.
     * By testing the deckSize() function in the deck class.
     ********************************************************************/
    @Test
    public void testDeckSize() {
        deck = new Deck();
        assertEquals(deck.deckSize(), 52); //checks the deck size is 52
    }

    /***************************************************************************
     * This tests the isEmpty() function in the deck class. It initializes a
     * deck of cards and makes sure the isEmpty() function is reading the amount
     * of cards in the deck properly. It also checks the dealToPile() function.
     ***************************************************************************/
    @Test
    public void testDeckIsEmpty() {
        deck = new Deck();
        pile = new Pile(PileType.WASTE);
        assertFalse(deck.isEmpty());

        deck.dealToPile(pile, 1); //deals one card to the WASTE pile
        assertEquals(deck.deckSize(), 51); //checks the deck size is one less
        assertFalse(deck.isEmpty());

        deck.dealToPile(pile, 51); //deals the rest of cards to the WASTE pile
        assertEquals(deck.deckSize(), 0); //checks the deck size is 0
        assertTrue(deck.isEmpty()); //makes sure the deck is empty
    }

    /*********************************************************************************
     * This tests to make sure the getCard() function from the deck class
     * recognizes when there is not enough cards to get the card the user is asking for.
     **********************************************************************************/
    @Test
    public void testDeckGetCard() {
        deck = new Deck();
        pile = new Pile(PileType.WASTE);

        deck.dealToPile(pile, 49);
        assertNull(deck.getCard(8)); //makes sure that it is null if the card there is not enough cards

        deck.dealToPile(pile, 3);
        assertNull(deck.getCard(3)); //makes sure that it is null if it is empty
    }

    /**************************************************************************************
     * This tests the addCard() and toString() functions of the deck class.
     *************************************************************************************/
    @Test
    public void testDeckAddCardAndToString() {
        deck = new Deck(); //initializes a deck of cards
        pile = new Pile(PileType.WASTE);
        card = new Card(Suit.CLUBS, Rank.FOUR); //initializes a certain card

        deck.dealToPile(pile, 52); //empties the deck
        deck.addCard(card); //adds the specified card
        assertEquals(deck.getCard(0), card); //gets the card from the deck
        assertEquals(deck.toString(), "[FOUR of CLUBS]"); //returns one b since there is only one card
    }

    /******************************************************************************************************
     * This tests the shuffle() function in the Deck class.
     ******************************************************************************************************/
    @Test
    public void testDeckShuffle() {
        deck = new Deck();

        assertEquals(deck.deckSize(), 52); //created a full deck
        deck.shuffle();
        assertEquals(deck.deckSize(), 52); //checks to see if all the cards are still there
    }
}
