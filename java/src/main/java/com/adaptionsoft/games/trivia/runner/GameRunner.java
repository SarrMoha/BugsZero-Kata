package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Player;

public class GameRunner {

	//TODO : Use constants for values such as the number of sides of the die (5) and the threshold for triggering a wrong answer (7).

	//TODO : Add comments that explain the purpose of each section of the code, especially in the playGame method

	public static void main(String[] args) {
		/*Rather than passing the random generator with each method call, we can add a constructor to the Game class that initializes the random generator.*/
		Random rand = new Random();
		playGame(rand);
	}

	public static void playGame(Random rand) {

		/*
		* Avoid using a static variable (notAWinner). We can instead return a value from the playGame method.
		* */

		Game aGame = new Game();
		boolean gameInProgress = true;

		aGame.addPlayer(new Player("Chet"));
		aGame.addPlayer(new Player("Pat"));
		aGame.addPlayer(new Player("Sue"));

		aGame.setCurrentPlayer(aGame.getPlayers().get(0));

		do {
			int roll = rand.nextInt(5) + 1;
			aGame.roll(roll);

			if (rand.nextInt(9) == 7) {
				aGame.wrongAnswer(true);
			} else {
				aGame.wasCorrectlyAnswered(true);
			}

			if (aGame.isAnyWinner() != null) {
				Player winner = aGame.isAnyWinner();
				System.out.println(winner.getName() + " won the game!");
				gameInProgress = false;
			}

		} while (gameInProgress);

	}
}
