package DeckOfCards;

public abstract class Card {
	protected int value;
	protected Suit s;
	protected boolean isAvaliable;
	public Card(int v, Suit s) {
		this.value = v;
		this.s = s;
		this.isAvaliable = true;
	}
	
	public abstract int value();
	
	public void setUnavaliable() {
		this.isAvaliable = false;
	}
	
	public void setAvaliable() {
		this.isAvaliable = true;
	}
	
	public boolean isAvaliable() {
		return this.isAvaliable;
	}
	
}
