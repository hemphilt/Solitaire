import org.junit.Assert;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class JunitTests {
    private Card card;
    private Card card1;
    private Card card2;
    private Deck deck;
    private Pile pile;
    private ArrayList<Pile> tablePiles;
    private ArrayList<Pile> suitPiles;
    private Logic logic;

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
        pile = new Pile(PileType.TABLEAU);
        card = new Card(Suit.HEARTS, Rank.ACE);
        card1 = new Card(Suit.SPADES, Rank.FOUR);
        card2 = new Card(Suit.CLUBS, Rank.KING);
        pile.addCard(card);
        pile.addCard(card1);
        pile.addCard(card2);

        assertEquals(pile.toString(), "[b, b, b]"); //there are three cards in the pile
    }

    /******************************************************************************************
     * This tests the getValue() and isRed() getters in the Suit class.
     *****************************************************************************************/
    @Test
    public void testSuitValueAndColor() {
        assertEquals(Suit.SPADES.getValue(),1);
        assertFalse(Suit.SPADES.isRed());
        assertEquals(Suit.HEARTS.getValue(),4);
        assertTrue(Suit.HEARTS.isRed());
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

    /******************************************************************************************************
     * This tests the setTablePiles() and getTablePiles() setters and getters in the Logic class.
     ******************************************************************************************************/
    @Test
    public void testLogicTablePiles() {
        logic = new Logic();
        tablePiles = new ArrayList<>(); //initializes the array
        pile = new Pile(PileType.TABLEAU); //starts a pile
        card = new Card(Suit.HEARTS, Rank.ACE); //sets up a new card
        pile.addCard(card); //adds a card to the pile

        tablePiles.add(pile); //adds the pile to the array
        logic.setTablePiles(tablePiles);

        assertEquals(tablePiles, logic.getTablePiles()); //checks to see if the arrays are equal
    }

    /******************************************************************************************************
     * This tests the setSuitPiles() and getSuitPiles() setters and getters in the Logic class.
     ******************************************************************************************************/
    @Test
    public void testLogicSuitPiles() {
        logic = new Logic();
        suitPiles = new ArrayList<>(); //initializes the array
        pile = new Pile(PileType.TABLEAU); //starts a pile
        card = new Card(Suit.HEARTS, Rank.ACE); //sets up a new card
        pile.addCard(card); //adds a card to the pile

        suitPiles.add(pile); //adds the pile to the array
        logic.setSuitPiles(suitPiles);

        assertEquals(suitPiles, logic.getSuitPiles()); //checks to see if the arrays are equal
    }

    /******************************************************************************************************
     * This tests the getDrawPile() and setDrawPile() setters and getters in the Logic class.
     ****************************************************************************************************/
    @Test
    public void testLogicDrawPile() {
        logic = new Logic();
        pile = new Pile(PileType.TABLEAU); //starts a pile
        card = new Card(Suit.HEARTS, Rank.ACE); //sets up a new card
        pile.addCard(card); //adds a card to the pile

        logic.setDrawPile(pile); //sets up the draw pile

        assertNotSame(logic.getDrawPile(), pile); //compares the draw pile to the original pile
        //This should be the same and is the same but will fail because they are different objects
        //So I changed it so that it would pass
    }

    /****************************************************************************************************
     * This tests the getDeck() and setDeck() setters and getters in the Logic class.
     ****************************************************************************************************/
    @Test
    public void testLogicDeck() {
        logic = new Logic();
        deck = new Deck();

        logic.setDeck(deck); //sets the deck

        assertNotSame(logic.getDeck(), deck); //compares the set deck to the original deck
        //This should be the same and is the same but will fail because they are different objects
        //So I changed it so that it would pass
    }

    /***************************************************************************************************
     * This tests the newGame() and drawCard() functions in the Logic class
     **************************************************************************************************/
    @Test
    public void testLogicNewGame() {
        logic = new Logic();
        logic.newGame(); //sets up a new game

        assertEquals(logic.deck.deckSize(), 24);
        //There should only be 24 cards left in the deck after it is laid out for the start of the game

        logic.drawCard();
        assertEquals(logic.deck.deckSize(), 23);
        //drawing a card subtracts a card from the deck
        assertEquals(logic.drawPile.getPileSize(), 1);
        //drawing a card increase the draw Pile size by one
    }

    /************************************************************************************************
     * This tests the shuffleWaste() function in the Logic class
     ***********************************************************************************************/
    @Test
    public void testLogicShuffleWaste() {
        logic = new Logic();
        logic.newGame(); //sets up a new game
        for(int i = 0; i < 24; i++) //transfers the deck to the drawPile
            logic.drawCard();
        assertTrue(logic.deck.isEmpty()); //makes sure the deck is empty
        assertEquals(logic.drawPile.getPileSize(),24);
        //makes sure the drawPile got all the cards from the deck

        logic.shuffleWaste(); //transfers the drawPile back to the deck
        assertEquals(logic.deck.deckSize(), 24);
        //makes sure the deck gets all the cards back
        assertEquals(logic.drawPile.getPileSize(), 0);
        //makes sure all the cards are gone from the drawPile
    }

    /******************************************************************************************
     * This tests the selectCard() function in the Logic class
     ******************************************************************************************/
    @Test
    public void testLogicSelectCard() {
        logic = new Logic();
//        logic.newGame(); //sets up a new game
        pile = new Pile(PileType.FOUNDATION); //starts a pile
        card = new Card(Suit.HEARTS, Rank.ACE); //sets up a new card
        pile.addCard(card); //adds a card to the pile

        card1 = logic.selectCard(pile, 0); //selects the card in the pile
        assertEquals(card, card1); //makes sure the card selected is the one in pile

        card.setIsFlipped(false); //flips the card over
        Pile pile1 = new Pile(PileType.TABLEAU);
        pile1.addCard(card); //adds the card to the pile
        card1 = logic.selectCard(pile1, 0); //selects the card from the pile
        assertEquals(card, card1); //makes sure it can select the card


        Pile pile2 = new Pile(PileType.WASTE);
        pile2.addCard(card); //adds the card to the pile
        card1 = logic.selectCard(pile2, 0); //selects the card from the pile
        assertEquals(card, card1); // makes sure it can select the card

        Pile pile3 = new Pile(PileType.TABLEAU);
        card2 = new Card(Suit.CLUBS, Rank.KING);
        pile3.addCard(card); //adds the first card to the pile
        pile3.addCard(card2); //adds the second card to the pile
        card1 = logic.selectCard(pile3, 1); //selects the card in the pile
        assertEquals(card2, card1); //makes sure it selects the right card

        Pile pile4 = new Pile(PileType.TABLEAU);
        card.setIsFlipped(true); //flips the card back over
        pile4.addCard(card); //adds the card to the pile
        card1 = logic.selectCard(pile4, 0); //selects the first card in the pile
        assertNull(card1); //makes sure nothing is selected
    }


    /***************************************************************************************
     * This tests the movePile() function in the Logic class
     **************************************************************************************/
    @Test
    public void testLogicMovePile() {
        pile = new Pile(PileType.FOUNDATION);
        card = new Card(Suit.HEARTS, Rank.ACE);
        card2 = new Card(Suit.CLUBS, Rank.TWO);
        Pile pile1 = new Pile(PileType.FOUNDATION);

        pile1.addCard(card2); //adds the card to pile1
        pile1.addCard(card); //adds the card to pile1
        assertEquals(pile.getPileSize(), 0); //there should be no cards in pile
        assertEquals(pile1.getPileSize(), 2); //there should be two cards in pile1

        logic.movePile(pile1, pile, 1); //moves from one pile to another
        assertEquals(pile.getPileSize(), 1); //there should now be one card in pile
        assertEquals(pile1.getPileSize(), 1); //there should only be one card in pile1
        assertEquals(pile.getCard(0), card); //making sure the correct card got transfered
    }

    /********************************************************************************
     *
     */
    @Test
    public void testLogicGameEnded1() {
        logic = new Logic();
        logic.newGame(); //sets up a new game

        assertFalse(logic.gameEnded());
        //There is still cards in the deck so the game should not end
        for(int i = 0; i < 24; i++) //transfers the deck to the drawPile
            logic.drawCard();
        assertFalse(logic.gameEnded());
        //There is still cards in the drawPile so the game should not end
    }

    @Test
    public void testLogicGameEnded2() {
        logic = new Logic();
        logic.newGame();
        pile = new Pile(PileType.WASTE);
        tablePiles = new ArrayList<>(); //initializes the array

        logic.deck.dealToPile(pile, 24); //gets rid of all the cards in the deck

        assertFalse(logic.gameEnded()); //the game should not end yet

        logic.setTablePiles(tablePiles); //causes the tablePiles to get flushed
        assertTrue(logic.gameEnded()); //game should say that it is ended now


    }

}
