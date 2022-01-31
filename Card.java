
public class Card {

	String color, suit, value;

	public Card(String color, String suit, int value) {
		this.color = color;
		this.suit = suit;
		switch (value) {
		case 1:
			this.value = "Ace";
			break;
		case 2:
			this.value = "Two";
			break;
		case 3:
			this.value = "Three";
			break;
		case 4:
			this.value = "Four";
			break;
		case 5:
			this.value = "Five";
			break;
		case 6:
			this.value = "Six";
			break;
		case 7:
			this.value = "Seven";
			break;
		case 8:
			this.value = "Eight";
			break;
		case 9:
			this.value = "Nine";
			break;
		case 10:
			this.value = "Ten";
			break;
		case 11:
			this.value = "Jack";
			break;
		case 12:
			this.value = "Queen";
			break;
		case 13:
			this.value = "King";
			break;
		}
		//Temporary to make sure all cards created
		System.out.println(String.format("%s of %ss", this.value, this.suit));
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
