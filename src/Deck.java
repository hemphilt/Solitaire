import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Deck extends JPanel {
    ArrayList<Card> deckOfCards;
    BufferedImage img;

    public Deck() {
        int i = 0;
        this.deckOfCards = new ArrayList<Card>(52);
        for (Suit s: Suit.values()){
            for (Rank r: Rank.values()){
                Card c = new Card(s, r);
                deckOfCards.add(i, c);
                i++;
            }
        }
        img = null;
        try{
            img = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\CardImages\\BackRed.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        setOpaque(false);
    }

    public BufferedImage getImg(){
        return img;
    }

    /**
     * This method returns the current number of cards in the deck
     * @return deckOfCards.size() the current size of the deck
     */
    public int deckSize(){
        return this.deckOfCards.size();
    }

    /**
     * This method returns true or false if the deck of cards is empty or not
     * @return true if deck is empty, false if the deck is not
     */
    public boolean isEmpty(){
        return this.deckOfCards.isEmpty();
    }

    /**
     * This method grabs a specific card in the deck based on the integer
     * @param i the integer/index of the card
     * @return card at int i of the deck
     */
    public Card getCard(int i){
        if (deckOfCards.isEmpty()){
            return null;
        }
        else if (i > deckOfCards.size()){
            return null;
        }
        else {
            return deckOfCards.get(i);
        }
    }

    /**
     * This method adds the Card c to the deck
     * @param c the card that you want to add to the deck
     */
    public void addCard(Card c){
        c.setIsFlipped(true);
        deckOfCards.add(c);
    }

    /**
     * This method shuffles and randomizes the cards in the deck
     */
    public void shuffle(){
        Collections.shuffle(deckOfCards);
    }

    /**
     * This method puts numCards from the deck to a pile
     * @param pile the pile that you want to deal to
     * @param numCards the number of cards that you want to deal
     */
    public void dealToPile(Pile pile, int numCards){
        if (deckOfCards.size() > 0 && numCards > 0){
            for (int i = 0; i < numCards; i++){
                Card c = deckOfCards.get(0);
                if (i == numCards-1){
                    c.setIsFlipped(false);
                }
                pile.addCard(c);
                deckOfCards.remove(0);
            }
        }
    }

    /**
     * This method returns the cards in the deck to a string
     * @return deckOfCards.toString(). All the cards in the deck to a string
     */
    public String toString(){
        return deckOfCards.toString();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.WHITE);
        g2d.drawRect(0,0, 170, this.getHeight());

        if (!isEmpty()){
            g.drawImage(img, 0, 0, 100, 130, this);
        }
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        Pile pile = new Pile(PileType.FOUNDATION);

        System.out.println(deck.size());



//        deck.dealToPile(pile, 1);
//        deck.dealToPile(pile2, 2);
//        deck.dealToPile(pile3, 3);
//        deck.dealToPile(pile4, 4);
//        deck.dealToPile(pile5, 5);
//        deck.dealToPile(pile6, 6);
//        deck.dealToPile(pile7, 7);

        System.out.println("Deck: " + deck.getCard(0));
        deck.dealToPile(pile, 12);
        Card c = deck.getCard(1);
        System.out.println("C" + c);
//        System.out.println("Pile: " + pile.getTopCard());
        System.out.println(pile.canTake(deck.getCard(1)));

//        System.out.println(pile.getTopCard());



        System.out.println("deck: " + deck.getCard(0) + " " + deck.size());
//        System.out.println("card: " + pileTest.get(0) + " " + pileTest.size());
    }

}
