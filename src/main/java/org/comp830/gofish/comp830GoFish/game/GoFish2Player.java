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
public class GoFish2Player implements GoFish {

	static final int CARDS_DISTRO = 7;
	private Card playedCard = null;

	@Override
	public String getName() {
		return GAME_NAME;
	}

	@Override
	public int getCardsToPlay() {
		return CARDS_DISTRO;
	}

	public void setCardFromGameMaster(Card fromGameMaster) {
		this.playedCard = fromGameMaster;
	}
	
	public Card getCardFromMaster() {
		return playedCard;
	}
	
	@Override
	public ArrayList getGoFishMatches(Card fromGameMaster, Deck playerCardsOnHand) {
		playedCard = fromGameMaster;
		
		ArrayList<Card> matchCards = new ArrayList<Card>(); 
		
		if (fromGameMaster == null) {
			return matchCards;
		}
		
		for (int i = 0; i < playerCardsOnHand.getDeckOfCards().size(); i++) {
			Card playerCard = playerCardsOnHand.getCard(i);
			
			if(fromGameMaster.equals(playerCard)) {
				matchCards.add(playerCard);
			}
		}
		
		return matchCards;
	}

}
