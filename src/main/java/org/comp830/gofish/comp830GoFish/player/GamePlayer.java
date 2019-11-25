/**
 * 
 */
package org.comp830.gofish.comp830GoFish.player;

import org.comp830.gofish.comp830GoFish.card.Deck;

/**
 * @author 
 * Valerie Therrien 2019
 * Lloyd Dagoc 2019
 * Surya Kranthi 2019
 */
public class GamePlayer implements Player {

	private static final String PLAYER_TYPE = "GamePlayer";
	private String gamePlayed = "";
	private String playerStatus = "";
	
	private Deck myDeck;
	
	@Override
	public String currentPlayedGame() {
		return gamePlayed;
	}

	@Override
	public void setGame(String game) {
		gamePlayed = game;
	}

	/**
	 * @return the playerType
	 */
	public static String getPlayerType() {
		return PLAYER_TYPE;
	}

	@Override
	public String getPlayerStatus() {
		return playerStatus;
	}

	@Override
	public void setPlayerStatus(String status) {
		playerStatus = status;
	}

	@Override
	public void setDeck(Deck d) {
		myDeck = d;
	}

	@Override
	public Deck getDeck() {
		return myDeck;
	}

}
