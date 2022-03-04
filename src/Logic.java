import java.util.ArrayList;

/**
 * A Class containing the game logic.
 */
public class Logic {

    /** Represents the piles of cards in play.*/
    private static ArrayList<Pile> tablePiles;

    /** Represents the piles of cards placed in order, starts off empty.*/
    private static ArrayList<Pile> suitPiles;

    /** Represents the pile of drawn cards.*/
    private static Pile drawPile;

    /** Represents the deck of unused cards.*/
    private static Deck deck;

    /**
     * Returns the current table piles.
     *
     * @return An ArrayList of the current table piles
     */
    public static ArrayList<Pile> getTablePiles() {
//        return new ArrayList<>(tablePiles);
        return tablePiles;
    }

    /**
     * Sets the current table piles to the given values.
     *
     * @param pTablePiles an ArrayList of card piles on the table
     */
    public static void setTablePiles(final ArrayList<Pile> pTablePiles) {
//        Logic.tablePiles = new ArrayList<>(pTablePiles);
        tablePiles = pTablePiles;
    }

    /**
     * Returns the current suit piles.
     *
     * @return An ArrayList of the current suit piles
     */
    public static ArrayList<Pile> getSuitPiles() {
//        return new ArrayList<>(suitPiles);
        return suitPiles;
    }

    /**
     * Sets the current suit piles to the given values.
     *
     * @param pSuitPiles an ArrayList of suit card piles
     */
    public static void setSuitPiles(final ArrayList<Pile> pSuitPiles) {
//        Logic.suitPiles = new ArrayList<>(pSuitPiles);
        suitPiles = pSuitPiles;
    }

    /**
     * Returns the current draw pile.
     *
     * @return A Pile of the current drawn cards
     */
    public static Pile getDrawPile() {
//        Pile tempPile = new Pile(drawPile.getPileType());
//        for (int i = 0; i < drawPile.getPileSize(); i++) {
//            tempPile.addCard(drawPile.getCard(i));
//        }
//        return tempPile;
        return drawPile;
    }

    /**
     * Sets the current draw pile to the given values.
     *
     * @param pDrawPile a pile of current drawn cards
     */
    public static void setDrawPile(final Pile pDrawPile) {
//        Pile tempPile = new Pile(pDrawPile.getPileType());
//        for (int i = 0; i < pDrawPile.getPileSize(); i++) {
//            tempPile.addCard(pDrawPile.getCard(i));
//        }
//        Logic.drawPile = tempPile;
        drawPile = pDrawPile;
    }

    /**
     * Returns the current deck pile.
     *
     * @return A Pile of the current undrawn cards
     */
    public static Deck getDeck() {
//        Deck tempDeck = new Deck();
//        for (int i = 0; i < deck.deckSize(); i++) {
//            tempDeck.addCard(deck.getCard(i));
//        }
//        return tempDeck;
        return deck;
    }

    /**
     * Sets the current deck to the given values.
     *
     * @param pDeck a Pile of the current undrawn cards
     */
    public static void setDeck(final Deck pDeck) {
//        Deck tempDeck = new Deck();
//        for (int i = 0; i < pDeck.deckSize(); i++) {
//            tempDeck.addCard(pDeck.getCard(i));
//        }
//        Logic.deck = tempDeck;
        deck = pDeck;
    }

    /** Creates a new game.*/
    public static void newGame() {

        suitPiles = new ArrayList<>();

        tablePiles = new ArrayList<>();

        drawPile = new Pile(PileType.WASTE);

        deck = new Deck();

        deck.shuffle();

        final int numSuitPiles = 4;
        final int numTablePiles = 8;

        for (int i = 0; i < numSuitPiles; i++) { //create suit piles
            Pile p = new Pile(PileType.FOUNDATION);
            suitPiles.add(p);
        }

        for (int i = 1; i < numTablePiles; i++) {    //create table piles
            Pile p = new Pile(PileType.TABLEAU);
            deck.dealToPile(p, i);
            tablePiles.add(p);
        }
    }

    /**
     * Moves one card from the deck to the draw pile.
     */
    public static void drawCard() {
        if (!(deck.isEmpty())) {
            deck.dealToPile(drawPile, 1);
        }
    }

    /**
     * Moves all the cards in the draw pile back into the deck
     * (ONLY IF DECK IS EMPTY).
     */
    public static void shuffleWaste() {
        int numCards = drawPile.getPileSize();
        if (deck.isEmpty()) {
            for (int i = 0; i < numCards; i++) {
                Card c = drawPile.getCard(0);
                drawPile.removeFirstCard();
                deck.addCard(c);
            }
        }
    }
    /**
     * Checks to see if the game has been finished.
     *
     * @return a boolean representing whether the game has ended
     */
    public static boolean gameEnded() {
        if (!(deck.isEmpty())) {
            return false;
        }
        if (!(drawPile.isEmpty())) {
            return false;
        }
        for (Pile tablePile : tablePiles) {
            if (!(tablePile.isEmpty())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the card selected.
     *
     * @param p pile the card is being selected from
     * @param whichCard which card is selected
     * @return the card selected by the user
     */
    public static Card selectCard(final Pile p, final int whichCard) {
        if (p.getPileType() == PileType.FOUNDATION
                || p.getPileType() == PileType.WASTE) {
            return p.getLastCard();
        } else if (p.getPileType() == PileType.TABLEAU) {
            for (int i = 0; i < p.getPileSize(); i++) {
                if (!p.getCard(i).getIsFlipped()) {
                    if (whichCard == 0) {
                        return p.getCard(i);
                    } else if (whichCard == 1) {
                        return p.getLastCard();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Moves the selected pile to the desired location.
     *
     * @param give pile the selected pile is moved from
     * @param take pile the selected pile is moved to
     * @param whichCard which card is selected
     */
    public static void movePile(final Pile give, final Pile take,
                                final int whichCard) {
        Pile tempPile = new Pile(null);
        int index = 0;
        int count = 1;
        Card c = selectCard(give, whichCard);
        if (take.canTake(c)) {
            for (int i = 0; i < give.getPileSize(); i++) {
                if (give.getCard(i) == c) {
                    index = i;
                }
            }
            for (int j = index; j < give.getPileSize(); j++) {
                Card add = give.getCard(j);
                tempPile.addCard(add);
                ++count;
            }
            for (int k = 0; k < tempPile.getPileSize(); k++) {
                take.addCard(tempPile.getCard(k));
            }
            for (int i = 1; i < count; ++i) {
                give.removeLastCard();
            }
            if (!give.isEmpty()) {
                give.getLastCard().setIsFlipped(false);
            }
        }
    }
}
