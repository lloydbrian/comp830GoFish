package org.comp830.gofish.comp830GoFish;

import java.util.Scanner;
import java.util.Random;


/*
Use Case 1 "Game Master chooses number of player options":
System will give an option to the 1st player to choose between a 2-player game or a 4-player game. 
If the player chooses a 2-player game, each player will be given 7 cards whereas if the player chooses a 4-player game, each player will be given 5 cards.
 */

public class GameMaster {


public static void main(String[] args) {
	
	System.out.println("Welcome to Go Fish! \n" );

	Scanner reader = new Scanner(System.in);
	System.out.println("Enter 2 for 2-Player Game or 4 for 4-Player Game");
	int n = reader.nextInt();
	System.out.println("You are playing with " + n + " players. Cards will now be distributed to all players.");
	
	//need a loop to only allow a 2 or a 4				
				
}
public static void distributeCards()
{	
	Deck distributeDeck = new Deck();
	
	int d;
	for (d= 0; d< 5; d++)
	{
		Card deckCards = distributeDeck.drawcard();
		players[d].addFromDeck(deckCards);
}
}
}