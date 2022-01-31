
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
		
	}
}
