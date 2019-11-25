/**
 * 
 */
package org.comp830.gofish.comp830GoFish;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.comp830.gofish.comp830GoFish.card.Deck;
import org.comp830.gofish.comp830GoFish.game.GoFish;
import org.comp830.gofish.comp830GoFish.game.GoFish2Player;
import org.comp830.gofish.comp830GoFish.game.GoFish4Player;

import org.comp830.gofish.comp830GoFish.player.*;

/**
 * @author 
 * Lloyd Dagoc 2019
 * Surya Kranthi 2019
 * Valerie Therrien 2019
 */
public class GameTable {

	/**
	 * 
	 */
	public GameTable() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GoFish goFishGame;
		ArrayList<Player> players = new ArrayList<>();
		Deck gameDeck = new Deck("Undealt Deck");
		// shuffle the cards before dealing
		gameDeck.shuffle();
		
		System.out.println("Welcome to Go Fish! \n" );

		Scanner reader = new Scanner(System.in);
		System.out.println("Enter 2 for 2-Player Game or 4 for 4-Player Game");
		int n = reader.nextInt();
		System.out.println("You are playing with " + n + " players. Cards will now be distributed to all players.");

		
		if(n == 4) {
			goFishGame = new GoFish4Player(); 
		} else {
			goFishGame = new GoFish2Player();
		}
		

		for (int i = 1; i <= n; i++) {
			Player player = new GamePlayer();
			Deck cardsAtHand = new Deck("Player Deck");
			cardsAtHand.emptyTheDeck();
			
			for(int j = 0; j < goFishGame.getCardsToPlay(); j++) {
				System.out.println(gameDeck.popCard(j));
				cardsAtHand.addCard(gameDeck.popCard(j));
			}
			
			player.setDeck(cardsAtHand);			
			players.add(new GamePlayer());
		
		}
		

		
	}

}
