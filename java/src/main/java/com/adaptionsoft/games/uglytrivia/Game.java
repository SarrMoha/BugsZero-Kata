package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {

	//TODO : Add comments that explain the purpose of each section of the code

	//Create Constant
	private static final String CATEGORY_POP = "Pop";
	private static final String CATEGORY_SCIENCE = "Science";
	private static final String CATEGORY_SPORT = "Sports";
	private static final String CATEGORY_ROCK = "Rock";


	//Use generics types
	private ArrayList<Player> players = new ArrayList<>();
	private LinkedList<String> popQuestions = new LinkedList<>();
	private LinkedList<String> scienceQuestions = new LinkedList<>();
	private LinkedList<String> sportsQuestions = new LinkedList<>();
	private LinkedList<String> rockQuestions = new LinkedList<>();
	private Player currentPlayer;
	private boolean isGettingOutOfPenaltyBox;

	public Game() {
		for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast("Science Question " + i);
			sportsQuestions.addLast("Sports Question " + i);
			rockQuestions.addLast("Rock Question " + i);
		}
	}

	/*howManyPlayers method is useless */

	public boolean isPlayable() {
		return players.size() >= 2;
	}


	public void addPlayer(Player _player) {

		getPlayers().add(_player);

		System.out.println(_player.getName() + " was added");
		System.out.println("They is now " + players.size() + " players");
	}


	/*Divide the roll method into several small methods*/
	public void roll(int roll) {

		System.out.println(currentPlayer.getName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (currentPlayer.isInPenaltyBox()) {

			if (roll % 2 != 0) {
				System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
				movePlayer(currentPlayer, roll);
				askQuestion(currentPlayer);
			} else {
				System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
			}

		} else {

			movePlayer(currentPlayer, roll);
			askQuestion(currentPlayer);
		}

	}


	private void movePlayer(Player _player, int _roll) {

		_player.setPlace(_player.getPlace() + _roll);

		if (_player.getPlace() > 11)
			_player.setPlace(_player.getPlace() - 12);

		System.out.println(_player.getName()
				+ "'s new location is "
				+ _player.getPlace());
	}

	//Refactoring askQuestions to manage all cases
	public void askQuestion(Player _player) {
		System.out.println("The category for " + _player.getName() + " is " + currentCategory(_player.getPlace()));
		switch (currentCategory(_player.getPlace())) {
			case CATEGORY_POP:
				System.out.println(popQuestions.removeFirst());
				break;
			case CATEGORY_SCIENCE:
				System.out.println(scienceQuestions.removeFirst());
				break;
			case CATEGORY_SPORT:
				System.out.println(sportsQuestions.removeFirst());
				break;
			case CATEGORY_ROCK:
				System.out.println(rockQuestions.removeFirst());
				break;
		}
	}

	//Refactoring wasCorrectlyAnswered
	public void wasCorrectlyAnswered(boolean correct) {
		if (currentPlayer.isInPenaltyBox()) {
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				currentPlayer = nextPlayer(currentPlayer);
				currentPlayer.setPurse(currentPlayer.getPurse() + 1);
				System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getPurse() + " Gold Coins.");
			} else {
				currentPlayer = nextPlayer(currentPlayer);
			}
		} else {
			System.out.println("Answer was correct!!!!");
			currentPlayer.setPurse(currentPlayer.getPurse() + 1);
			System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getPurse() + " Gold Coins.");
			currentPlayer = nextPlayer(currentPlayer);
		}
	}

	public boolean wrongAnswer(boolean correct) {
		System.out.println("Question was incorrectly answered");
		System.out.println(currentPlayer.getName() + " was sent to the penalty box");
		currentPlayer.setInPenaltyBox(true);
		currentPlayer = nextPlayer(currentPlayer);
		return true;
	}

	private String currentCategory(int _place) {
		switch (_place % 4) {
			case 0 : return CATEGORY_POP;
			case 1 : return "Science";
			case 2 : return "Sports";
			default : return "Rock";
		}
	}

	public Player isAnyWinner() {

		Player result = null;

		for (Player p : players) {
			if (p.getPurse() == 6) {
				result = p;
				break;
			}
		}

		return result;
	}

	private Player nextPlayer(Player _player) {

		if (players.indexOf(_player) + 1 != players.size())
			return players.get(players.indexOf(_player) + 1);
		else
			return players.get(0);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

}
