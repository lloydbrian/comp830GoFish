/**
 * 
 */
package org.comp830.gofish.comp830GoFish.game;

import java.util.ArrayList;

import org.comp830.gofish.comp830GoFish.card.Card;
import org.comp830.gofish.comp830GoFish.card.Deck;

/**
 * @author 
 * Lloyd Dagoc 2019
 * Surya Kranthi 2019
 * Valerie Therrien 2019
 *
 */
public class GoFish4Player implements GoFish {

	static final int CARDS_DISTRO = 5;

	@Override
	public String getName() {
		return GAME_NAME;
	}

	@Override
	public int getCardsToPlay() {
		return CARDS_DISTRO;
	}

	@Override
	public ArrayList getGoFishMatches(Card fromGameMaster, Deck playerCardsOnHand) {
		ArrayList<Card> matchCards = new ArrayList<Card>(); 
		
		for (int i = 0; i < playerCardsOnHand.getDeckOfCards().size(); i++) {
			Card playerCard = playerCardsOnHand.getCard(i);
			
			if(fromGameMaster.equals(playerCard)) {
				matchCards.add(playerCard);
			}
		}
		
		return matchCards;
	}

}
