import java.io.*;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Logic class that manipulates the cards.
 */
public class Logic {

    /**
     * Represents the piles of cards in play.
     */
    private static ArrayList<Pile> tablePiles;

    /**
     * Represents the piles of cards placed in order, starts off empty.
     */
    private static ArrayList<Pile> suitPiles;

    /**
     * Represents the pile of drawn cards.
     */
    public static Pile drawPile;

    /**
     * Represents the deck of unused cards.
     */
    public static Deck deck;

    /**
     * Returns the current table piles.
     *
     * @return An ArrayList of the current table piles
     */
    public static ArrayList<Pile> getTablePiles() {
        ArrayList<Pile> tempPiles = new ArrayList<>();
        for (Pile p : tablePiles) {
            tempPiles.add(p);
        }
        return tempPiles;
    }

    /**
     * Sets the current table piles to the given values.
     *
     * @param pTablePiles an ArrayList of card piles on the table
     */
    public static void setTablePiles(final ArrayList<Pile> pTablePiles) {
        ArrayList<Pile> tempPiles = new ArrayList<>();
        for (Pile p : pTablePiles) {
            tempPiles.add(p);
        }
        Logic.tablePiles = tempPiles;
    }

    /**
     * Returns the current suit piles.
     *
     * @return An ArrayList of the current suit piles
     */
    public static ArrayList<Pile> getSuitPiles() {
        ArrayList<Pile> tempPiles = new ArrayList<>();
        for (Pile p : suitPiles) {
            tempPiles.add(p);
        }
        return tempPiles;
    }

    /**
     * Sets the current suit piles to the given values.
     *
     * @param pSuitPiles an ArrayList of suit card piles
     */
    public static void setSuitPiles(final ArrayList<Pile> pSuitPiles) {
        ArrayList<Pile> tempPiles = new ArrayList<>();
        for (Pile p : pSuitPiles) {
            tempPiles.add(p);
        }
        Logic.suitPiles = tempPiles;
    }

    /**
     * Returns the current draw pile.
     *
     * @return A Pile of the current drawn cards
     */
    public static Pile getDrawPile() {
        return drawPile;
    }

    /**
     * Sets the current draw pile to the given values.
     *
     * @param pDrawPile a pile of current drawn cards
     */
    public static void setDrawPile(final Pile pDrawPile) {
        Pile tempPile = new Pile(pDrawPile.getPileType());
        for (int i = 0; i < pDrawPile.getPileSize(); i++) {
            tempPile.addCard(pDrawPile.getCard(i));
        }
        Logic.drawPile = tempPile;
    }

    /**
     * Returns the current deck pile.
     *
     * @return A Pile of the current undrawn cards
     */
    public static Deck getDeck() {
        return deck;
    }

    /**
     * Sets the current deck to the given values.
     *
     * @param pDeck a Pile of the current undrawn cards
     */
    public static void setDeck(final Deck pDeck) {
        Deck tempDeck = new Deck();
        for (int i = 0; i < pDeck.deckSize(); i++) {
            tempDeck.addCard(pDeck.getCard(i));
        }
        Logic.deck = tempDeck;
    }

    /**
     * Creates a new game.
     */
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
     * Method for the instructions on how to play the game.
     *
     * @return the string holding the instructions.
     */
    public static String howToPlay() {
        return "The four aces form the foundations. As it becomes available, each ace must be played to a row above the piles. " +
                "\nCards in the appropriate suit are then played on the aces in sequence - the two, then the three, and so on - as they become available.\n" +
                "\n" +
                "Any movable card may be placed on a card next-higher in rank if it is of opposite color. " +
                "\nExample: A black five may be played on a red six. If more than one card is face up on a tableau pile, " +
                "\nall such cards must be moved as a unit.\n" +
                "\n" +
                "When there is no face-up card left on a pile, the top face-down card is turned up and becomes available.\n" +
                "\n" +
                "Only a king may fill an open space in the layout. The player turns up cards from the " +
                "\ntop of the stock in groups of three, and the top card of the three may be used for " +
                "\nbuilding on the piles, if possible, played on a foundation. If a card is used in this manner, the card below it becomes available for play. " +
                "\nIf the up-card cannot be used, the one, two, or three cards of the group are placed face up on the waste pile, and the next group of three cards is turned up.";
    }

    /**
     * Method to return the controls of the game.
     *
     * @return controls. The instruction on how to control the game.
     */
    public static String controls() {
        return "First, click on the card/pile that you want to move." +
                "\nIf you want to move the entire pile, use the LEFT CLICK button to select it." +
                "\nIf the card that you want to move is the last card in the pile, use the RIGHT CLICK button to select it." +
                "\nThen, click on the pile that you want to move it to.\n" +
                "\nNOTE: If the card/piles are not moving, try clicking on the same card twice to reset the selections.";
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
     * Checks to see if the game has been finished. It checks if all the cards in
     * the deck and discard/draw are empty. Then counts the number of shown cards in the remaining piles.
     *
     * @return a boolean representing whether the game has ended
     */
    public static boolean gameEnded() {
        int count = 0;
        if (!(deck.isEmpty())){
            return false;
        }
        if (!(drawPile.isEmpty())){
            return false;
        }
        for (Pile p: tablePiles){
            for (int i = 0; i < p.getPileSize(); i++){
                if (!(p.getCard(i).getIsFlipped())){
                    count += 1;
                }
            }
        }
        for (Pile p: suitPiles){
            for (int i = 0; i < p.getPileSize(); i++){
                if (!(p.getCard(i).getIsFlipped())){
                    count+=1;
                }
            }
        }

        return count ==52;
    }


    /**
     * Returns the card selected.
     *
     * @param p         pile the card is being selected from
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
     * @param give      pile the selected pile is moved from
     * @param take      pile the selected pile is moved to
     * @param whichCard which card is selected
     */
    public static int movePile(final Pile give, final Pile take, final int whichCard) {
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
            return 1;
        }
        return 0;
    }

    public static void load() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("filename.txt"));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null){
            content.append(line);
            content.append(System.lineSeparator());
        }
        System.out.println(content);
        reader.close();

        String readCards = content.toString();


        String[] read = readCards.split(",");

//        for (int i = 0; i < read.length; i++){
//            System.out.println(read[i]);
//        }

        System.out.println(read);

        System.out.println(readCards.length());
    }

    public static void save() throws IOException {
        String str = "";
        BufferedWriter writer = new BufferedWriter(new FileWriter("filename.txt"));

        for (Pile p: suitPiles){
            str += p.toString() + "\n";
        }
        for (Pile p: tablePiles){
            str += p.toString() + "\n";
        }
        str += drawPile.toString() + "\n";
        str += deck.toString();

        writer.write(str);
        writer.close();
    }

}
