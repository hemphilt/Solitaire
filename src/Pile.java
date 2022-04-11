import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * This class represents the different piles of the game that the user can interact with.
 */
public class Pile extends JPanel {
    /** Represents the pile of cards.*/
    private final ArrayList<Card> pile;

    /** Represents the type of pile.*/
    private PileType type;

    /**
     * A constructor for the pile of cards.
     *
     * @param pType The type of pile
     */
    public Pile(final PileType pType) {
        this.pile = new ArrayList<>();
        this.type = pType;

        setOpaque(false);
    }

    /**
     * Sets the pile type of the pile.
     *
     * @param pType The type of pile desired
     */
    public final void setPileType(final PileType pType) {
        this.type = pType;
    }

    /**
     * Returns the pile type of the pile.
     *
     * @return The type of pile
     */
    public final PileType getPileType() {
        return type;
    }

    /**
     * Adds a card to the pile.
     *
     * @param c the card that you want to add to the pile
     */
    public void addCard(final Card c) {
        pile.add(c);
    }

    /**
     * Removes a card to the pile and returns it.
     *
     * @param i the index of the card to be removed
     * @return the card removed
     */
    public Card removeCard(final int i) {
        if (!pile.isEmpty()) {
            return this.pile.remove(i);
        }
        return null;
    }

    /**
     * Removes the first card from the pile.
     */
    public void removeFirstCard() {
        if (!this.pile.isEmpty()) {
            this.pile.remove(0);
            if (!this.pile.isEmpty()) {
                pile.get(pile.size() - 1).setIsFlipped(false);
            }
        }
    }
    /**
     * Removes the last card from the pile and returns it.
     *
     * @return the card removed
     */
    public final Card removeLastCard() {
        if (this.pile.size() != 0) {
            return this.pile.remove(pile.size() - 1);
        }
        return null;
    }

    /**
     * Returns the card at the given index.
     *
     * @param i the index of card to be removed
     * @return The card at the given index
     */
    public Card getCard(final int i) {
        return this.pile.get(i);
    }

    /**
     * Checks if the pile can take a certain card.
     *
     * @param c the card that you are trying to add
     * @return true if the pile can take the card, or false if not
     */
    public boolean canTake(final Card c) {
        if (c != null) {
            if (getPileType() == PileType.FOUNDATION) {
                if (isEmpty() && c.getRank() == Rank.ACE.getValue()) {
                    return true;
                } else if (!isEmpty() && pile.get(pile.size() - 1).getSuit()
                        == c.getSuit() && pile.get(pile.size() - 1).getRank()
                        == c.getRank() - 1) {
                    return true;
                }
            }
            if (getPileType() == PileType.WASTE) {
                return false;
            }
            else if (getPileType() == PileType.TABLEAU){
                final int kingRank = 13;
                if (isEmpty() && c.getRank() == kingRank) {
                    return true;
                } else {
                    return !isEmpty()
                            && pile.get(pile.size() - 1).getSuit().isRed()
                            != c.getSuit().isRed()
                            && pile.get(pile.size() - 1).getRank()
                            == c.getRank() + 1;
                }
            }
        }
        return false;
    }

    /**
     * This returns the last card of the pile.
     *
     * @return The last card in the pile
     */
    public Card getLastCard() {
        if (!this.pile.isEmpty()) {
            return this.pile.get(pile.size() - 1);
        }
        return null;
    }

    /**
     * Checks if the pile is empty.
     *
     * @return true if the pile has no cards, false if not
     */
    public boolean isEmpty() {
        return this.pile.isEmpty();
    }

    /**
     * Returns the size of the pile.
     *
     * @return int the number of cards in the pile
     */
    public int getPileSize() {
        return this.pile.size();
    }

    /**
     * Returns the pile to a string.
     *
     * @return All the cards in the pile to a string
     */
    public String toString() {
        return this.pile.toString();
    }

    /**
     * Creates the component using the given graphics.
     *
     * @param graphics the desired Graphics to be used
     */
    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(Color.WHITE);

        final int y2 = 96;
        g2d.drawLine(0, 0, 0, y2);
        g2d.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, y2);

        final int x1 = 36;
        final int x2 = 36;
        final int y22 = 60;
        final int r = 255;
        final int g = 255;
        final int b = 255;
        final int a = 160;
        g2d.setPaint(new GradientPaint(x1, 0,
                new Color(r, g, b, a), x2, y22,
                new Color(0, 0, 0, 0)));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        int y = 0;
        final int width = 100;
        final int height = 130;
        final int increment = 25;

        if (!this.isEmpty()) {
            for (Card c : this.pile) {
                if (!c.getIsFlipped()) {
                    g2d.drawImage(c.getCardImage(), 0, y, width, height, this);
                } else {
                    g2d.drawImage(c.getBackImage(), 0, y, width, height, this);
                }
                if (this.getPileType() == PileType.TABLEAU) {
                    y += increment;
                }
            }
        }
    }
}