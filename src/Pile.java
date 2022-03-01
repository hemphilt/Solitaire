import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Pile extends JPanel{

    private ArrayList<Card> pile;
    private PileType type;

    int offSet = 15;

    public Pile(PileType type){
        this.pile = new ArrayList<>();
        this.type = type;

        setOpaque(false);
    }

    public void setPileType(PileType type){
        this.type = type;
    }

    public PileType getPileType(){
        return type;
    }


    /**
     * Add a card to the pile
     * @param c the card that you want to add to the pile
     */
    public void addCard(Card c){
        pile.add(c);
    }

    /**
     * Removes the last card from the pile
     */
    public void removeCard(){
        if (!this.pile.isEmpty()){
            this.pile.remove(0);
            if (!this.pile.isEmpty()){
                pile.get(pile.size()-1).isFlipped(false);
            }
        }
    }

    public Card getCard(int i){
        return this.pile.get(i);
    }

    /**
     * This method checks if the pile can take a certain card
     * @param c the card that you are trying to add
     * @return true if the pile can take the card, or false if not
     */
    public boolean canTake(Card c){
        if (getPileType() == PileType.FOUNDATION) {
            if (isEmpty() && c.getRank() == 1) {
                return true;
            } else if (!isEmpty() && pile.get(pile.size() - 1).getSuit() == c.getSuit() && pile.get(pile.size() - 1).getRank() == c.getRank()) {
                return true;
            }
        }
        else {
            if (isEmpty() && c.getRank() == 13){
                return true;
            }
            else if (!isEmpty() && pile.get(pile.size()-1).getSuit().isRed != c.getSuit().isRed && pile.get(pile.size()-1).getRank() == c.getRank() +1){
                return true;
            }
        }
        return false;
    }

    public void movePile(Card c){
        if (canTake(c)){

        }
    }

    /**
     * This returns the last card of the pile
     */
    public Card getLastCard(){
        return this.pile.get(pile.size()-1);
    }

    /**
     * This method checks if the pile is empty
     * @return true if the pile has no cards, false if not
     */
    public boolean isEmpty(){
        return this.pile.isEmpty();
    }

    /**
     * This method returns the size of the pile
     * @return int the number of cards in the pile
     */
    public int getPileSize() {
        return this.pile.size();
    }

    /**
     * This method returns the pile to a string
     * @return all of the cards in the pile to a string
     */
    public String toString(){
        return this.pile.toString();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.drawLine(0,0,0,96);
        g2d.drawLine(this.getWidth() -1, 0, this.getWidth() -1, 96);


        g2d.setPaint(new GradientPaint(36, 0, new Color(255, 255, 255, 160), 36, 60, new Color(0,0,0,0)));
        g2d.fillRect(0,0, this.getWidth(), this.getHeight());

        int cardYPos = 0;
        if(this.isEmpty()){
        }
        else{
            for (Card c: this.pile){
                if (!c.getIsFlipped()){
                    g.drawImage(c.getCardImage(), 0, cardYPos, 100, 130, this);
                }
                else{
                    g.drawImage(c.getBackImage(), 0, cardYPos, 100, 130, this);
                }
                if (this.getPileType() == PileType.TABLEAU) {
                    cardYPos += 25;
                }
            }
        }
    }

    public static void main(String[] args) {
        Pile p = new Pile(PileType.TABLEAU);
        Deck deck = new Deck();
        deck.dealToPile(p, 2);
        System.out.println(p.getPileType() == PileType.TABLEAU);
        System.out.println(p.getPileType());
        System.out.println(p.getPileSize());
    }

}
