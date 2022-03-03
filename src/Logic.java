import java.util.ArrayList;
import java.util.Iterator;

public class Logic {
    static ArrayList<Pile> tablePiles;
    static ArrayList<Pile> suitPiles;
    static Pile drawPile;
    public static Deck deck;
    private int offset = 25;


    public static void initialize() {
        newGame();
    }

    public static void newGame() {

        suitPiles = new ArrayList<>();

        tablePiles = new ArrayList<>();

        drawPile = new Pile(PileType.WASTE);

        deck = new Deck();

        deck.shuffle();


        for (int i = 0; i < 4; i++) { //create suit piles
            Pile p = new Pile(PileType.FOUNDATION);
            suitPiles.add(p);
        }

        for (int i = 1; i < 8; i++) {    //create table piles
            Pile p = new Pile(PileType.TABLEAU);
            deck.dealToPile(p, i);
            tablePiles.add(p);
        }
    }

    /**
     * This method puts one card from the deck and puts it into the draw pile
     */
    public static void drawCard() {
        if (!(deck.isEmpty())) {
            deck.dealToPile(drawPile, 1);
        }
    }

    /**
     * This method puts all the cards in the draw pile back into the deck (ONLY IF DECK IS EMPTY)
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

    public static boolean gameEnded() {
        if (!(deck.isEmpty())) {
            return false;
        }
        if (!(drawPile.isEmpty())) {
            return false;
        }
        for (int i = 0; i < tablePiles.size(); i++) {
            if (!(tablePiles.get(i).isEmpty())) {
                return false;
            }
        }
        return true;
    }

    public static Card selectCard(Pile p, int whichCard) {
        if (p.type == PileType.FOUNDATION || p.type == PileType.WASTE) {
            Card c = p.getLastCard();
            return c;
        } else if (p.type == PileType.TABLEAU) {
            for (int i = 0; i < p.getPileSize(); i++) {
                if (!p.getCard(i).getIsFlipped()) {
                    if (whichCard == 0) {
                        Card c = p.getCard(i);
                        return c;
                    } else if (whichCard == 1) {
                        Card c = p.getLastCard();
                        return c;
                    }
                }
            }
        }
        return null;
    }

    public static void movePile(Pile give, Pile take, int whichCard) {
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
            for (int i = 1; i < count; ++i){
                give.removeLastCard();
            }
            if (!give.isEmpty()) {
                give.getLastCard().setIsFlipped(false);
            }
        }
    }

}
