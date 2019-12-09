/**
 * 
 */
package org.comp830.gofish.comp830GoFish;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.comp830.gofish.comp830GoFish.card.Card;
import org.comp830.gofish.comp830GoFish.card.Deck;
import org.comp830.gofish.comp830GoFish.game.Game;
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

	private Game currentGame = null;
	
	public GameTable(Game game) {
		this.currentGame = game;
	}
	
	public void playGame(GameMaster gm, ArrayList<Player> players, Scanner reader) {

		// card to play and match with all players
		Card gmCard = gm.getDeck().popCard();
		System.out.println("Game Master's card: " + gmCard);
		
		for (int j = 0; j < players.size(); j++) {
			GamePlayer gP = (GamePlayer) players.get(j);
			
			if(gP.isPlayerMe()) {
				System.out.println("These are YOUR cards that matched the game master's card");
				ArrayList<Card> matchedCards = ((GoFish) currentGame).getGoFishMatches(gmCard, gP.getDeck());
				
				for(int x =0; x < matchedCards.size(); x++) {
					System.out.println(x + " : " +matchedCards.get(x));
				}
				
				
				if(matchedCards.size() > 0) {
					int choice = -1;
					System.out.println("Current cards in YOUR hand: " + gP.getDeck().toString());
					while (choice < 0 || choice > matchedCards.size()) {
						System.out.println("Not an exact matching card, choose the right matching card to drop.");
						choice = reader.nextInt();					
					}
					
					gP.getDeck().popCard((Card) matchedCards.get(choice));
					System.out.println("Remaining cards in YOUR hand: " + gP.getDeck().toString());
					
				} else {
					System.out.println("No matched cards.");
				}
				
			} else {
				System.out.println("Matched Cards of Player #: " + j);
				ArrayList<Card> matchedCards = ((GoFish) currentGame).getGoFishMatches(gmCard, gP.getDeck());

				for(int x =0; x < matchedCards.size(); x++) {
					System.out.println(x + " : " +matchedCards.get(x));
				}

				if(matchedCards.size() > 0) {
					//gP.getDeck().popCard((Card) matchedCards.get(choice));
					System.out.println("Remaining cards on hand of Player #: " + gP.getDeck().toString());
					
				} else {
					System.out.println("No matched cards.");
				}

			}
		}
	}
	
	public void matchTest (GameMaster gm, Player player) {
		Card gmCard = gm.getDeck().popCard();
		Deck playerDeck = player.getDeck();

		ArrayList<Card> matchedCards = ((GoFish) currentGame).getGoFishMatches(gmCard, playerDeck);
		System.out.println("Game Master Card: " + gmCard.toString());
		System.out.println(matchedCards);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		GameTable gTable;
		GoFish goFishGame;

		ArrayList<Player> players = new ArrayList<>();
		GameMaster gMaster = new GameMaster();
		Deck gameDeck = new Deck("Undealt Deck");
		Deck  usedDeck = new Deck("Deck with Used Cards");
		
		gameDeck.shuffle();
		gMaster.setDeck(gameDeck);
		
		usedDeck.emptyTheDeck();
		
		System.out.println("Welcome to Go Fish! \n" );

		Scanner reader = new Scanner(System.in);
		System.out.println("Enter 2 for 2-Player Game or 4 for 4-Player Game");
		int n = reader.nextInt();
		if(n == 4) {
			goFishGame = new GoFish4Player(); 
			gTable = new GameTable(goFishGame);

		} else {
			goFishGame = new GoFish2Player();
			gTable = new GameTable(goFishGame);
		}

		
		System.out.println("GoFish Game has " + n + " players.");
		
		int playerChosen = 1;
		do {
			System.out.println("Choose your player.");
			System.out.println("Cards will be dealt after your choice.");
			playerChosen = reader.nextInt();
		} while (playerChosen > n || playerChosen <= 0);


		for (int i = 1; i <= n; i++) {
			GamePlayer player = new GamePlayer();
			Deck cardsAtHand = new Deck("Player Deck");
			cardsAtHand.emptyTheDeck();
			
			if(playerChosen == i) {
				System.out.println("Player: YOU");
				player.setPlayerMe(true);
			} else {
				System.out.println("Player: " + i);				
			}
			
			for(int j = 0; j < goFishGame.getCardsToPlay(); j++) {
				Card cardDealt = gameDeck.popCard(j);
				System.out.println("Card #" + j + ": " + cardDealt);
				cardsAtHand.addCard(cardDealt);
			}
			
			player.setDeck(cardsAtHand);			
			players.add(player);
		
		}
		
		System.out.println("Remaining Cards on Game Master's Deck: " + gameDeck.toString());
		System.out.println(gameDeck.size());
		
		//gTable.matchTest(gMaster, players.get(playerChosen-1));
		gTable.playGame(gMaster, players, reader);
	}

}
