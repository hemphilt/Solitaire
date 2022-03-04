/**
 * Enumerator for the different suits of the cards.
 */
public enum Suit {
    /** Represents the value of the Spades suit.*/
    SPADES(1, false),

    /** Represents the value of the Clubs suit.*/
    CLUBS(2, false),

    /** Represents the value of the Diamonds suit.*/
    DIAMONDS(3, true),

    /** Represents the value of the Hearts suit.*/
    HEARTS(4, true);


    /** Represents the value of the cards suit.*/
    private final int value;

    /** Represents whether the card is red.*/
    private final boolean isRed;

    /**
     * Returns the suit of the card.
     *
     * @return the numerical value of the suit of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns whether the card is red or black.
     *
     * @return True if the card is red, false if it is black
     */
    public boolean isRed() {
        return isRed;
    }
    Suit(final int pValue, final boolean pIsRed) {
        this.value = pValue;
        this.isRed = pIsRed;
    }

}
