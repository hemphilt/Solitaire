public enum Suit {
    SPADES (1, false),
    CLUBS (2, false),
    DIAMONDS (3, true),
    HEARTS (4, true);

    public int value;
    public boolean isRed;

    private Suit (int value, boolean isRed){
        this.value = value;
        this.isRed = isRed;
    }
}


