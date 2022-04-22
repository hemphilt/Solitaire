/**
 * Enumerator for the different ranks of the cards.
 */
public enum Rank {
    /** Represents the value of an Ace card.*/
    ACE(1),

    /** Represents the value of a two card.*/
    TWO(2),

    /** Represents the value of a three card.*/
    THREE(3),

    /** Represents the value of a four card.*/
    FOUR(4),

    /** Represents the value of a five card.*/
    FIVE(5),

    /** Represents the value of a six card.*/
    SIX(6),

    /** Represents the value of a seven card.*/
    SEVEN(7),

    /** Represents the value of an eight card.*/
    EIGHT(8),

    /** Represents the value of a nine card.*/
    NINE(9),

    /** Represents the value of a ten card.*/
    TEN(10),

    /** Represents the value of a Jack card.*/
    JACK(11),

    /** Represents the value of a Queen card.*/
    QUEEN(12),

    /** Represents the value of a King card.*/
    KING(13);

    /** Represents the value of the desired card.*/
    private final int value;

    /**
     * Returns the value of the card.
     *
     * @return the numerical value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     *  Sets the rank of the card to the given value.
     *
     * @param pValue the numerical value of the card
     */
    Rank(final int pValue) {
        this.value = pValue;
    }

}
