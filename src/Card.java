import sun.awt.image.BufferedImageDevice;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Card {
    private Rank rank;
    private Suit suit;
    private boolean isReversed;
    private BufferedImage cardImage;
    private BufferedImage backImage;

    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
        isReversed = false;

        try {
            File image = new File(System.getProperty("user.dir") + "\\src\\CardImages\\" + rank + " " + suit + ".png");
            cardImage = ImageIO.read(image);

            image = new File(System.getProperty("user.dir") + "\\src\\CardImages\\BackRed.png");
            backImage = ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This returns the rank (value) of the card
     * @return rank
     */
    public Rank getRank(){
        return rank;
    }

    /**
     * This returns the suit of the card
     * @return suit
     */
    public Suit getSuit(){
        return suit;
    }

    /**
     * Set the rank (value)of the card
     * @param rank RANK...
     */
    public void setRank(Rank rank){
        this.rank = rank;
    }

    /**
     * Set the suit of the card
     * @param suit SUIT...
     */
    public void setSuit(Suit suit){
        this.suit = suit;
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
        return cardImage;
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
     * @return backImage
     */
    public BufferedImage getBackImage(){
        return backImage;
    }
    /**
     * Changes the isFlipped boolean to true in order to display the correct side of the card
     */
    public void show(){
        isReversed = false;
    }

    /**
     * Changes the isFlipped boolean to false in order to display the correct side of the card
     */
    public void hide(){
        isReversed = true;
    }

    /**
     * Returns the card name to a String
     * @return "[rank] of [suit]"
     */
    public String toString(){
        return rank + " of " + suit;
    }

    public static void main(String args[]) throws IOException {
        Card card = new Card(Suit.HEARTS, Rank.NINE);
        //card.setCardImage(ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\CardImages\\" + card.getRank() + " OF " + card.getSuit() + ".png")));
        card.setRank(Rank.EIGHT);
        System.out.println(card);

        File image = new File(System.getProperty("user.dir") + "\\src\\CardImages\\" + card.getRank() + " " + card.getSuit() + ".png");
        BufferedImage cardImage = ImageIO.read(image);
        card.setCardImage(cardImage);
        System.out.println(card.getCardImage());

    }

}
