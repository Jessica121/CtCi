package DeckOfCards;
import java.util.*;
public class Deck <T extends Card>{
	List<T> cards;
	int numOfHands;
	int cardIndex;
	
	public Deck(int num) {
		this.numOfHands = num;
		this.cardIndex = 0;
	}
	
	public void initCards(List<T> card) {
		this.cards = card;
	}
	
	public T[] dealHands() {
		T[] hands = (T[]) new Card[numOfHands];
		for(int i = 0; i < hands.length; i++) {
			if(remainingCards() > 0) {
				hands[i] = cards.get(cardIndex++); // deal card
				hands[i].setUnavaliable();
			} else {
				System.out.println("ERROR - Out of cards");
				break;
			}
		}
		return hands;
	}
	
	public int remainingCards() {
		return cards.size() - cardIndex;
	}
	
	/*
	 * take the index from i to end and swap the card with i and that index. 
	 * to this till the last card
	 * i = 0 .. len - 1,,,.A random index should be from i to len - 1, inclusive.
	 * and use set to set the index and the card, save current card first.
	 * set current index the card in the random pos, and the other way around.
	 * generate the index from i to len - 1. 0 1 2 3 4  nextInt(end - start + 1) + i
	 * */
	public void shuffle() {
		Random r = new Random();
		for(int i = 0; i < cards.size(); i++) {
			int rand = r.nextInt(cards.size() - i) + i;
			T cur = cards.get(i);
			cards.set(i, cards.get(rand));
			cards.set(rand, cur);
		}
	}
}
