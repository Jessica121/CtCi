package DeckOfCards;

public enum Suit {
	CLUB(0), 
	DIAMOND(1),
	HEART(2),
	SPADE(3);
	
	private int val;
	private Suit(int v) {
		this.val = v;
	}
	
	public int getVal() {
		return this.val;
	}
	
	public Suit getSuitFromVal(int v) {
		switch(v) {
		case 0: return CLUB;
		case 1: return DIAMOND;
		case 2: return HEART;
		case 3: return SPADE;
		default: return null;
		}
	}
}
