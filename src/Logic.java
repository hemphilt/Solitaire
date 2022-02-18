
public class Logic {
    private static Pile[] tablePiles, suitsPiles;
    private static Pile suitPile, suitPile2, suitPile3, suitPile4;
    private static Pile playPile, playPile2, playPile3, playPile4, playPile5, playPile6, playPile7;
    private static Pile discardPile;
    private static Deck deck;


    public static void initialize(){
        newGame();
    }

    public static void newGame(){
        suitPile = new Pile(true);
        suitPile2 = new Pile(true);
        suitPile3 = new Pile(true);
        suitPile4 = new Pile(true);

        playPile = new Pile(false);
        playPile2 = new Pile(false);
        playPile3 = new Pile(false);
        playPile4 = new Pile(false);
        playPile5 = new Pile(false);
        playPile6 = new Pile(false);
        playPile7 = new Pile(false);

        discardPile = new Pile(false);

        deck = new Deck();

        deck.shuffle();

        deck.dealToPile(playPile, 1);
        deck.dealToPile(playPile2, 2);
        deck.dealToPile(playPile3, 3);
        deck.dealToPile(playPile4, 4);
        deck.dealToPile(playPile5, 5);
        deck.dealToPile(playPile6, 6);
        deck.dealToPile(playPile7, 7);

        tablePiles = new Pile[] {playPile, playPile2, playPile3, playPile4, playPile5, playPile6, playPile7};
        suitsPiles = new Pile[] {suitPile, suitPile2, suitPile3, suitPile4};

    }

    public static void drawCard(){
        if (!(deck.isEmpty())){
            deck.dealToPile(discardPile, 1);
        }
    }

    public static void shuffleWaste(){
        if (deck.isEmpty()){
            for (int i = 0; i < discardPile.size(); i++){
                Card c = discardPile.getTopCard();
                discardPile.removeCard();
                deck.addCard(c);
            }
        }
    }

    public static boolean gameEnded(){
        if(!(deck.isEmpty())){
            return false;
        }
        if (!(discardPile.isEmpty())){
            return false;
        }
        for (int i = 0; i < tablePiles.length; i++){
            if (!(tablePiles[i].isEmpty())){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        initialize();

        System.out.println(deck.toString());
        playPile.moveCard(suitPile);

//        System.out.println(playPile);
//        System.out.println(playPile2);
//        System.out.println(playPile3);
//        System.out.println(playPile4);
//        System.out.println(playPile5);
//        System.out.println(playPile6);
//        System.out.println(playPile7);
//        System.out.println();
//        System.out.println(suitPile);
//        System.out.println(suitPile2);
//        System.out.println(suitPile3);
//        System.out.println(suitPile4);
//        System.out.println();
//        System.out.println(discardPile);

//        drawCard();
//        drawCard();

        playPile7.removeCard();
        System.out.println(playPile7);

        playPile7.moveCard(suitPile2);

        System.out.println(playPile);
        System.out.println(playPile2);
        System.out.println(playPile3);
        System.out.println(playPile4);
        System.out.println(playPile5);
        System.out.println(playPile6);
        System.out.println(playPile7);
        System.out.println();
        System.out.println(suitPile);
        System.out.println(suitPile2);
        System.out.println(suitPile3);
        System.out.println(suitPile4);
        System.out.println();
        System.out.println(discardPile);


        deck.dealToPile(discardPile, deck.size());
        System.out.println(deck.toString());
        System.out.println(discardPile);

        shuffleWaste();

        System.out.println();
        System.out.println(deck.toString());
        System.out.println(discardPile);

        System.out.println(gameEnded());
    }


}
