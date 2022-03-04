import org.junit.Test;

import static org.junit.Assert.*;


public class JunitTests {
    private Card card;
    private Card card1;
    private Card card2;
    private Deck deck;
    private Pile pile;

    /**************************************************************
     * This tests the setup of the individual cards. It tests
     * the setSuit(), getSuit(), setRank(), and getRank() setters
     * and getters in the card class.
     **************************************************************/
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
        assertEquals(card.toString(), "b");

        card.setIsFlipped(false); //flips over the card
        assertEquals(card.toString(), "NINE of DIAMONDS");
    }

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
        assertEquals(deck.toString(), "[b]"); //returns one b since there is only one card
    }

    /****************************************************************************************
     * This tests the setPileType() and getPileType() setters and getters from the
     * Pile class.
     ****************************************************************************************/
    @Test
    public void testPileType() {
        pile = new Pile(PileType.TABLEAU);

        pile.setPileType(PileType.FOUNDATION);
        assertEquals(pile.getPileType(), PileType.FOUNDATION);
    }

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


    }

}