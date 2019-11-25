/**
 * 
 */
package org.comp830.gofish.comp830GoFish.player;

import org.comp830.gofish.comp830GoFish.card.Deck;

/**
 * @author 
 * Lloyd Dagoc 2019
 * Surya Kranthi 2019
 * Valerie Therrien 2019
 *
 */
public interface Player {
	
	public String currentPlayedGame();
	public void setGame(String game);
	
	public String getPlayerStatus();
	public void setPlayerStatus(String status);
	
	public void setDeck(Deck d);
	public Deck getDeck();

}
