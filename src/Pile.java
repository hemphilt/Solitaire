import java.util.Stack;

public class Pile {
    private Stack<Card> pile;
    private boolean suitPile;
    private Deck deck;

    public Pile(boolean isSuitPile){
        this.pile = new Stack<Card>();
        this.suitPile = isSuitPile;
    }

    /**
     * This method returns the top card of the pile
     * @return the top card of the pile. If the pile is empty, it returns null.
     */
    public Card getTopCard(){
        if (this.pile.isEmpty()){
            return null;
        }
        else {
            return this.pile.peek();
        }
    }

    public void moveCard(Pile take){
        if (!(this.pile.isEmpty())){
            if (take.canTake(this.pile.lastElement())){
                take.addCard(this.pile.lastElement());
                this.pile.pop();
                if (!(this.pile.isEmpty())){
                    this.pile.get(pile.size()-1).isFlipped(false);
                }
            }
        }
    }

    /**
     * Checks if the pile is a Suit Pile.
     * @return boolean suitPile
     */
    public boolean isSuitPile(){
        return this.suitPile;
    }

    /**
     * Changes the pile to a suit pile
     * @param isSetPile boolean if you want to make the current pile a Suit Pile
     */
    public void setSuitPile(boolean isSetPile){
        this.suitPile = isSetPile;
    }

    /**
     * Adds a specific card to the pile
     * @param c the card that you want to add to the pile
     */
    public void addCard(Card c) {
        this.pile.push(c);
    }

    /**
     * This removes the last card in the pile
     * @return the last card of the pile to remove
     */
    public Stack<Card> removeCard(){
        this.pile.pop();
        this.pile.lastElement().isFlipped(false);
        return this.pile;
    }


    /**
     * Checks if the pile can take a certain card
     * @param c the card that you are trying to add to the pile
     * @return true or false depending on the card and if the pile is a suit pile or not
     */
    public boolean canTake(Card c) {
        if (isSuitPile()) {
            if (isEmpty() && c.getRank() == 1){
                return true;
            }
            else if (!(isEmpty()) && this.pile.peek().getSuit() == c.getSuit() && this.pile.peek().getRank() == c.getRank() - 1) {
                return true;
            }
            else{
                return false;
            }
        }
        else if (!isSuitPile()){
            if (isEmpty() && c.getRank() == 13){
                return true;
            }
            else if (!(isEmpty()) && this.pile.peek().getSuit().isRed != c.getSuit().isRed && this.pile.peek().getRank() == c.getRank() + 1){
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * This returns the current size of the pile
     * @return size of the pile
     */
    public int size(){
        return pile.size();
    }

    /**
     * This returns a boolean to see if the pile is empty
     * @return true if the pile is empty, false if the pile is not
     */
    public boolean isEmpty(){
        return this.pile.isEmpty();
    }

    /**
     * This method prints the cards in the pile to a string
     * @return pile.toString()
     */
    public String toString(){
        return this.pile.toString();
    }


    public static void main(String[] args) {
        Pile pile = new Pile(false);
        Deck deck = new Deck();

        Card c = deck.getCard(27);
        System.out.println("C: " + c);
        pile.addCard(c);

        System.out.println("initial pile: " + pile);
        System.out.println("pile after deal 1: " + pile);
        System.out.println("Card ontop of deck: " + deck.getCard(0));
        if (pile.canTake(deck.getCard(0))){
            System.out.println("Yes we can!");
            deck.dealToPile(pile, 1);
        }
        else{
            System.out.println("NAH FAM");
        }
        System.out.println("Pile after can take: " + pile);


    }
}
