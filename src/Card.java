import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class represents the cards that are used during the game.
 */
public class Card extends JPanel {
    /** Represents the rank of the card.*/
    private Rank rank;

    /** Represents the suit of the card.*/
    private Suit suit;

    /** Whether the card is face up.*/
    private boolean isFlipped;

    /** Represents the face of the card.*/
    private BufferedImage cardImage;

    /** Represents the back of the card.*/
    private BufferedImage backImage;

    /**
     * Constructor for the Card object.
     * @param pRank rank of the card
     * @param pSuit suit of the card
     */
    public Card(final Suit pSuit, final Rank pRank) {
        this.suit = pSuit;
        this.rank = pRank;
        isFlipped = true;

        try {
            File image = new File(System.getProperty("user.dir")
                    + "\\src\\CardImages\\" + rank + " " + suit + ".png");
            cardImage = ImageIO.read(image);

            image = new File(System.getProperty("user.dir")
                    + "\\src\\CardImages\\BackRed.png");
            backImage = ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int cardWidth = 90;
        final int cardHeight = 120;
        setPreferredSize(new Dimension(cardWidth, cardHeight));
        setOpaque(false);
    }

    /**
     * Returns the rank (value) of the card.
     * @return rank
     */
    public int getRank() {
        return rank.getValue();
    }

    /**
     * Returns the suit of the card.
     * @return suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Sets the rank (value) of the card.
     * @param pRank RANK...
     */
    public void setRank(final Rank pRank) {
        this.rank = pRank;
    }

    /**
     * Sets the suit of the card.
     * @param pSuit SUIT...
     */
    public void setSuit(final Suit pSuit) {
        this.suit = pSuit;
    }

    /**
     * Sets the image of the card.
     * @param image the new image that you want to set the card to
     */
    public void setCardImage(final BufferedImage image) {
        cardImage = image;
    }

    /**
     * Returns the card image.
     * @return cardImage
     */
    public BufferedImage getCardImage() {
        return cardImage;
    }
    /**
     * Returns the file location of the card image.
     * @return string
     */
    public String getCardURL() {
        return "/CardImages/" + this.rank + " " + this.suit + ".png";
    }

    /**
     * Sets the image for the back of the card.
     * @param pImage the new image that you want to set the back of the card to
     */
    public void setBackImage(final BufferedImage pImage) {
        backImage = pImage;
    }

    /**
     * Returns the back image of the card.
     * @return the current backImage of the card
     */
    public BufferedImage getBackImage() {
        BufferedImage temp = backImage;
        return temp;
    }

    /**
     * Sets the value of isFlipped.
     * @param pIsFlipped true if you want the card to be flipped, false if not
     */
    public void setIsFlipped(final boolean pIsFlipped) {
        this.isFlipped = pIsFlipped;
    }

    /**
     * Gets the boolean isFlipped to see if a card is reversed.
     * @return isFlipped true if the card is flipped, or false if
     * the card is not
     */
    public boolean getIsFlipped() {
        return isFlipped;
    }

    /**
     * Returns the card name to a String.
     * @return "[rank] of [suit]"
     */
    public String toString() {
        if (!(isFlipped)) {
            return rank + " of " + suit;
        } else {
            return "b";
        }
    }

}
