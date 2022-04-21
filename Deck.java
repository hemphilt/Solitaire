import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class holds the Cards class and represents the playable deck.
 */
public class Deck extends JPanel {
    /** Represents the deck of cards.*/
    private final ArrayList<Card> deckOfCards;

    /** Represents the image of the card.*/
    private BufferedImage img;

    /** Constructor for the Deck object.*/
    public Deck() {
        int i = 0;
        final int numCards = 52;
        this.deckOfCards = new ArrayList<>(numCards);
        for (Suit s: Suit.values()) {
            for (Rank r: Rank.values()) {
                Card c = new Card(s, r);
                deckOfCards.add(i, c);
                i++;
            }
        }
        img = null;
        try {
           URL url = getClass().getResource("BackRed.png");
           if (url != null) {
               img = ImageIO.read(url);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        setOpaque(false);
    }
    /**
     * Returns the current image of the card.
     *
     * @return the image of the card
     */
    public final BufferedImage getImg() {
       return img;
    }

    /**
     * Returns the current number of cards in the deck.
     *
     * @return the current size of the deck
     */
    public int deckSize() {
        return this.deckOfCards.size();
    }

    /**
     * Returns true or false if the deck of cards is empty or not.
     *
     * @return true if deck is empty, false if the deck is not
     */
    public boolean isEmpty() {
        return this.deckOfCards.isEmpty();
    }

    /**
     * Returns a specific card in the deck based on the integer.
     *
     * @param i the integer/index of the card
     * @return card at int i of the deck
     */
    public Card getCard(final int i) {
        if (deckOfCards.isEmpty()) {
            return null;
        } else if (i > deckOfCards.size()) {
            return null;
        } else {
            return deckOfCards.get(i);
        }
    }

    /**
     * Adds the Card c to the deck.
     *
     * @param c the card that you want to add to the deck
     */
    public void addCard(final Card c) {
        c.setIsFlipped(true);
        deckOfCards.add(c);
    }

    /**
     * Set the image of the deck (back of the cards).
     * @param img the image that you want to set the deck to
     */
    public void setDeckImage(final BufferedImage img){
        this.img = img;
    }

    /**
     * Shuffles and randomizes the cards in the deck.
     */
    public void shuffle() {
        Collections.shuffle(deckOfCards);
    }

    /**
     * Puts a given number of cards from the deck to a pile.
     *
     * @param pile the pile that you want to deal to
     * @param numCards the number of cards that you want to deal
     */
    public void dealToPile(final Pile pile, final int numCards) {
        if (deckOfCards.size() > 0 && numCards > 0) {
            for (int i = 0; i < numCards; i++) {
                Card c = deckOfCards.get(0);
                if (i == numCards - 1) {
                    c.setIsFlipped(false);
                }
                pile.addCard(c);
                deckOfCards.remove(0);
            }
        }
    }

    /**
     * Returns the cards in the deck to a string.
     *
     * @return deckOfCards.toString(). All the cards in the deck to a string
     */
    public String toString() {
        return deckOfCards.toString();
    }

    @Override
    protected final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        final int strokeWidth = 5;
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.setColor(Color.WHITE);
        final int rectWidth = 170;
        g2d.drawRect(0, 0, rectWidth, this.getHeight());

        if (!isEmpty()) {
            final int imgWidth = 100;
            final int imgHeight = 130;
            g.drawImage(img, 0, 0, imgWidth, imgHeight, this);
        }
    }

}
