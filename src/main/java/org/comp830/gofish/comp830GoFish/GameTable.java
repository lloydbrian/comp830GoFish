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
 * 
 * @todo empty game masters deck logic
 * @todo pop card timing
 */
public class GameTable {

	private Game currentGame = null;
	private Deck usedDeck = null;
	
	public GameTable(Game game) {
		this.currentGame = game;
		this.usedDeck = new Deck("Deck with Used Cards");
		usedDeck.emptyTheDeck();
	}
	
	private boolean hasWinner(ArrayList<Player> players) {
		for (Player player : players) {
			Deck dP = player.getDeck();
			if (dP.empty()) {
				System.out.println("GAME HAS A WINNER: " + player.toString());
				return true;
			}
		}
		
		return false;
	}
	
	private Deck randomRemove(ArrayList<Card> matchedCards, Deck gpDeck) {
		Deck deck = gpDeck;
		Card removedCard;
		int ranCardIndex = ThreadLocalRandom.current().nextInt(0, matchedCards.size());
		
		if(!matchedCards.isEmpty() && !gpDeck.empty()) {
			// remove card from the matchedcards list
			removedCard = matchedCards.remove(ranCardIndex);
			
			// remove card from the cards on hand deck of the player
			deck.popCard(removedCard);
			usedDeck.addCard(removedCard);
			System.out.println("USED Cards are: " + usedDeck.toString());
		}
		return deck;
	}
	
	public void playGame(GameMaster gm, ArrayList<Player> players, Scanner reader) {


		// check game win
		if(!hasWinner (players) && !gm.getDeck().empty()) {
			System.out.println("Remaining Cards on Game Master's Deck: " + gm.getDeck().toString());
			System.out.println(gm.getDeck().size());

			// card to play and match with all players
			// revisit
			Card gmCard = gm.getDeck().popCard();
			System.out.println("Game Master's card: " + gmCard);
			
			for (int j = 0; j < players.size(); j++) {
				GamePlayer gP = (GamePlayer) players.get(j);
				int playerNum = j +1;
				
				if(gP.isPlayerMe()) {
					System.out.println("Remaining cards in YOUR hand: " + gP.getDeck().toString());
					System.out.println("These are YOUR cards that matched the game master's card");
					ArrayList<Card> matchedCards = ((GoFish) currentGame).getGoFishMatches(gmCard, gP.getDeck());
					
					for(int x =0; x < matchedCards.size(); x++) {
						System.out.println(x + " : " +matchedCards.get(x));
					}
					
					
					if(!matchedCards.isEmpty()) {
						int choice = -1;
						System.out.println("Current cards in YOUR hand: " + gP.getDeck().toString());
						while (choice < 0 || choice > matchedCards.size()) {
							System.out.println("Choose which matching card to drop.");
							choice = reader.nextInt();					

							if(choice >= 0 && choice < matchedCards.size()) {
								Card playedCard = matchedCards.remove(choice);
								usedDeck.addCard(playedCard);
								
								gP.getDeck().popCard(playedCard);
								// pick a new card from deck for new matching
								if(!gm.getDeck().empty()) {
									gmCard = gm.getDeck().popCard();
									System.out.println("Game Master's card: " + gmCard);
								}
								
								System.out.println("USED Cards are: " + usedDeck.toString());
							} 
						}
											
					} else {
						// pop card from GM deck and add to player deck
						System.out.println("Current cards from YOUR hand: " + gP.getDeck().toString());
						if(!gm.getDeck().empty()) {
							System.out.println("No matched cards. New card added to you from game master's deck.");
							gP.getDeck().addCard(gm.getDeck().popCard());
						}
						System.out.println("Current cards plus new from YOUR hand: " + gP.getDeck().toString());
					}
					
				} else {
					System.out.println("Remaining cards on hand of Player # " + playerNum + " " + gP.getDeck().toString());
					System.out.println("Matched Cards of Player # " + playerNum);
					ArrayList<Card> matchedCards = ((GoFish) currentGame).getGoFishMatches(gmCard, gP.getDeck());

					for(int x =0; x < matchedCards.size(); x++) {
						System.out.println(x + " : " +matchedCards.get(x));
					}

					if(!matchedCards.isEmpty()) {
						System.out.println("Running random matched card.");
						randomRemove(matchedCards, gP.getDeck());

						// pick a new card from deck for new matching
						System.out.println("Remaining cards on hand after random removal of Player # " + playerNum + " " + gP.getDeck().toString());
						if(!gm.getDeck().empty()) {
							gmCard = gm.getDeck().popCard();
							System.out.println("Game Master's card: " + gmCard);
						}

						
					} else {
						// pop card from GM deck and add to player deck
						System.out.println("Current cards from player " + playerNum + gP.getDeck().toString());
						if(!gm.getDeck().empty()) {
							System.out.println("No matched cards. New card added to player's deck from game master's deck.");
							gP.getDeck().addCard(gm.getDeck().popCard());
							
						}
						System.out.println("Current cards plus new from player " + playerNum + gP.getDeck().toString());
					}

				}
			}
			
		} else {
			System.out.println("LOOKS LIKE A DRAW. EXITING THE GAME. THANKS FOR PLAYING.");
			System.exit(0);
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
		
		gameDeck.shuffle();
		gMaster.setDeck(gameDeck);
		
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
						
		while(!gTable.hasWinner(players)) {
		//while(!gMaster.getDeck().empty()) {
			// check logic if there is a winner than basing it on size of the remaining gm deck
			gTable.playGame(gMaster, players, reader);
		}
		
	}

}
