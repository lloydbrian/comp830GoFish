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
public interface GoFish extends Game {
	static final String GAME_NAME = "Go Fish";
	static final int STARTING_COUNT_OF_CARDS = 52;
	boolean withJoker = false;
	
	Game goFishGameDecided = null;
	
	public int getCardsToPlay();
	public ArrayList getGoFishMatches(Card fromGameMaster, Deck playerCardsOnHand);
	
}
