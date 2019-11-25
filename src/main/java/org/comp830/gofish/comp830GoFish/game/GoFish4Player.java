/**
 * 
 */
package org.comp830.gofish.comp830GoFish.game;

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
}
