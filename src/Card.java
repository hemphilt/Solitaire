import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Card extends JPanel {
    private final Rank rank;
    private final Suit suit;
    private boolean isFlipped;
    private BufferedImage cardImage;
    private BufferedImage backImage;

    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
        isFlipped = true;

        try {
            File image = new File(System.getProperty("user.dir") + "\\src\\CardImages\\" + rank + " " + suit + ".png");
            cardImage = ImageIO.read(image);

            image = new File(System.getProperty("user.dir") + "\\src\\CardImages\\BackRed.png");
            backImage = ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(90, 120));
        setOpaque(false);
    }

    /**
     * This returns the rank (value) of the card
     * @return rank
     */
    public int getRank(){
        return rank.value;
    }

    /**
     * This returns the suit of the card
     * @return suit
     */
    public Suit getSuit(){
        return suit;
    }

    /**
     * Set the image of the card
     * @param image the new image that you want to set the card to
     */
    public void setCardImage(BufferedImage image){
        cardImage = image;
    }

    /**
     * Returns the card image
     * @return cardImage
     */
    public BufferedImage getCardImage(){
        if (!(isFlipped)) {
            return cardImage;
        }
        else {
            return backImage;
        }
    }

    /**
     * Sets the image for the back of the card
     * @param image the new image that you want to set the back of the card to
     */
    public void setBackImage(BufferedImage image){
        backImage = image;
    }

    /**
     * Returns the back image of the card
     * @return the current backImage of the card
     */
    public BufferedImage getBackImage(){
        return backImage;
    }

    /**
     * This sets the boolean isFlipped
     * @param isFlipped true if you want the card to be flipped, false if not
     */
    public void setIsFlipped(boolean isFlipped){
        this.isFlipped = isFlipped;
    }

    /**
     * This gets the boolean isFlipped to see if a card is reversed
     * @return isFlipped true if the card is flipped, or false if the card is not
     */
    public boolean getIsFlipped(){
        return isFlipped;
    }

    /**
     * Returns the card name to a String
     * @return "[rank] of [suit]"
     */
    public String toString(){
        if (!(isFlipped)) {
            return rank + " of " + suit;
        }
        else {
            return "b";
        }
    }

}
