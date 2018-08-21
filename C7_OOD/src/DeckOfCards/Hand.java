package DeckOfCards;
import java.util.*;
public class Hand <T extends Card> {
	List<T> cards;
	public Hand() {
		this.cards = new ArrayList<>();
	};
	
	public void addCard(T c) {
		cards.add(c);
	}
	
	public int score() {
		int s = 0;
		for(T card : cards) {
			s += card.value();
		}
		return s;
	}
}
