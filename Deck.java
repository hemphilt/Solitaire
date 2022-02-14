
import java.util.Random;

public class Deck {

    Card[] Deck = new Card[52];
    String color, suit;
    int value;

    public Deck() {

        for (int i = 1; i <= 4; i++) {
            for (int value = 1; value <= 13; value++) {
                if (i == 1) {
                    suit = "Club";
                    color = "Black";
                }

                if (i == 2) {
                    suit = "Diamond";
                    color = "Red";
                }

                if (i == 3) {
                    suit = "Heart";
                    color = "Red";
                }

                if (i == 4) {
                    suit = "Spade";
                    color = "Black";
                }

                Deck[i] = new Card(color, suit, value);
            }
            System.out.println();
        }
    }

    public void Shuffle() {
        //randomly generates numbers to create each card for the different suits
        //can use random.nextInt(13) + 1 to create each new card

    }

    public void StartLayout() {
        //sets up the layout for the start of the game, where the cards should go after they are shuffled

    }

    public void movement() {
        //determines if the movement the player is trying to make is allowed or not
    }

    public void undo() {
        //stores what movement was last done
    }

    public void scoreCounter() {
        //need to decide what determines points and add it up as we see fit
    }

    public void winCondition() {
        //we don't need to do this its only if we want a separate function from movement
    }
    
    public void gameOverCondition() {
        //again could make it separate or put it in movement
    }
    
    public void optionsMenu() {
        //can either put this here or in GUI if we want
        //says what options the people want to start or even during the game
    }
    
    public void tutorialScreen() {
        //also could be put in GUI too
    }
    
    
}
