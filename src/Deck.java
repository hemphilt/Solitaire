import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Deck {
    private Card deckOfCards[];

    public Deck() {
        int i = 0;
        this.deckOfCards = new Card[52];
        for (Suit s: Suit.values()){
            for (Rank r: Rank.values()){
                Card c = new Card(s, r);
                deckOfCards[i] = c;
                i++;
            }
        }
    }

    public void print(){
        for (int i = 0; i < deckOfCards.length; i++){
            System.out.println(deckOfCards[i]);
        }
    }

    public int size(){
        return deckOfCards.length;
    }

    public Card getCard(int i){
        if (i > deckOfCards.length || i < 0){
            return null;
        }
        else {
            return deckOfCards[i];
        }
    }

    public void shuffle(){
        Collections.shuffle(Arrays.asList(deckOfCards));
    }

//    public void addCardImages() throws IOException {
//        for (int i = 0; i < deckOfCards.length; i++){
//            Card c = deckOfCards[i];
//            System.out.println(c.toString());
//            File image = new File(System.getProperty("user.dir") + "\\src\\CardImages\\" + c.getRank() + " " + c.getSuit() + ".png");
//           System.out.println(image);
//            BufferedImage cardImage = ImageIO.read(image);
//            c.setCardImage(cardImage);
//        }
//    }

    public static void main(String[] args) throws IOException {
        Deck deck = new Deck();
//        deck.print();
//        deck.addCardImages();
        System.out.println(deck.getCard(0).getCardImage());
        System.out.println(deck.size());

//        deck.shuffle();
//        deck.shuffle();
//        deck.print();

    }
}
