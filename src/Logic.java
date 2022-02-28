import java.util.ArrayList;

public class Logic {
    static ArrayList<Pile> tablePiles;
    static ArrayList<Pile> suitPiles;
    static Pile drawPile;
    public static Deck deck;


    public static void initialize(){
        newGame();
    }

    public static void newGame(){

        suitPiles = new ArrayList<>();

        tablePiles = new ArrayList<>();

        drawPile = new Pile(PileType.WASTE);

        deck = new Deck();

        deck.shuffle();


        for (int i = 0; i < 4; i++){ //create suit piles
            Pile p = new Pile(PileType.FOUNDATION);
            suitPiles.add(p);
        }

        for (int i = 1; i < 8; i++){    //create table piles
            Pile p = new Pile(PileType.TABLEAU);
            deck.dealToPile(p, i);
            tablePiles.add(p);
        }


    }

    /**
     * This method puts one card from the deck and puts it into the draw pile
     */
    public static void drawCard(){
        if (!(deck.isEmpty())){
            deck.dealToPile(drawPile, 1);
        }
    }

    /**
     * This method puts all the cards in the draw pile back into the deck (ONLY IF DECK IS EMPTY)
     */
    public static void shuffleWaste(){
        int numCards = drawPile.getPileSize();
        if (deck.isEmpty()){
            for (int i = 0; i < numCards; i++){
                Card c = drawPile.getLastCard();
                drawPile.removeCard();
                deck.addCard(c);
            }
        }
    }

    public static boolean gameEnded(){
        if(!(deck.isEmpty())){
            return false;
        }
        if (!(drawPile.isEmpty())){
            return false;
        }
        for (int i = 0; i < tablePiles.size(); i++){
            if (!(tablePiles.get(0).isEmpty())){
                return false;
            }
        }
        return true;
    }

    public void clickPile(Pile p) {
        if(!p.isEmpty()) {
            Card c = p.getLastCard();
            if(c.getIsFlipped()) {
                c.isFlipped(false);
            }
        }
    }

    public void turnPile(){
        if (!deck.isEmpty()){
            return;
        }
        while (!drawPile.isEmpty()){
            Card c = drawPile.getLastCard();
            c.isFlipped(true);

            deck.addCard(c);
        }

    }

    public static void main(String[] args) {
        initialize();


        System.out.println(tablePiles.get(2).getPileSize());


        System.out.println(deck);

        System.out.println(deck.size());

        System.out.println("Draw Pile: " + drawPile);

        deck.dealToPile(drawPile, deck.deckSize());

        System.out.println(drawPile);


        System.out.println("drawPile size: " + drawPile.getPileSize());

        System.out.println("deck size: " + deck.size());


        shuffleWaste();

        System.out.println();
        System.out.println(deck);
        System.out.println(drawPile);



        System.out.println(gameEnded());
    }


}
