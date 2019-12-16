/**
 * 
 */
package org.comp830.gofish.comp830GoFish;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.comp830.gofish.comp830GoFish.card.*;

/**
 * @author 
 * Surya Kranthi 2019
 * Lloyd Dagoc 2019
 * Valerie Therrien 2019
 *
 */
public class CardCollection {

	private String label;
	private ArrayList<Card> cards;
	
	public CardCollection() {
		this.label = "New Deck";
		this.cards = new ArrayList<Card>();
	}
	
	public CardCollection(String label) {
		this.label = label;
		this.cards = new ArrayList<Card>();
	}
	
	public String getLabel() {
		return label;
	}
	
	public ArrayList getDeckOfCards () {
		return cards;
	}
	
	public Card getCard(int i) {
		return cards.get(i);
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public Card popCard(Card card) {
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).equalsExact(card)) {
				cards.remove(i);
				return card;
			}
		}
		
		return card;
	}

	public Card popCard(int i) {
		return cards.remove(i);
	}

	public Card popCard() {
		int i = size() - 1;
		return i < 0 ? null : popCard(i); 
		//return popCard(i);
	}

	public int size() {
		return cards.size();
	}
	
	// return true if cards is empty​
	public boolean empty() {
		return cards.size() == 0;
	}

	public void emptyTheDeck() {
		cards.clear();
	}

	
	// create duplicate collection
	public void deal(CardCollection c, int n) {
		for (int i = 0; i < n; i++) {
			Card card = popCard();
			c.addCard(card);
		}
	}

	public void dealAll(CardCollection that) {
		int n = size();
		deal(that, n);
	}
	
	public Card last() {
		int i = size() - 1;
		return cards.get(i);
	}

	public void swapCards(int i, int j) {
		Card temp = cards.get(i);
	    cards.set(i, cards.get(j));
	    cards.set(j, temp);
	  }

	public void shuffle() {
		int ranNumber = ThreadLocalRandom.current().nextInt(1, 100 +1);
		for (int i = size() - 1; i > 0; i--) {
			int j = ThreadLocalRandom.current().nextInt(0, 52);
			swapCards(i, j);
	    }
  	}

	public String toString() {
		return label + ": " + cards.toString();
	}

	public void display() {
	    System.out.println(label + ": ");
	    for (Card card: cards) {
	    	System.out.println(card);
	    }
	    System.out.println();
	}
}
