/**
 * 
 */
package org.comp830.gofish.comp830GoFish.card;

import org.comp830.gofish.comp830GoFish.*;

/**
 * @author 
 * Surya Kranthi 2019
 * Valerie Therrien 2019
 * Lloyd Dagoc 2019
 *
 */
public class Deck extends CardCollection implements DeckInt {

	/**
	 * 
	 */
	public Deck(String label) {
		super(label);
		for (int suit = 0; suit <= 3; suit++) {
			for (int rank = 1; rank <= 13; rank++) {
				Card card = new Card(rank, suit);
				addCard(card);
			}
	    }
	}
}
