package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    
    int currentPlayerID = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayerID) + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayerID]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(players.get(currentPlayerID) + " is getting out of the penalty box");
				places[currentPlayerID] = places[currentPlayerID] + roll;
				if (places[currentPlayerID] > 11) places[currentPlayerID] = places[currentPlayerID] - 12;
				
				System.out.println(players.get(currentPlayerID)
						+ "'s new location is " 
						+ places[currentPlayerID]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(players.get(currentPlayerID) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayerID] = places[currentPlayerID] + roll;
			if (places[currentPlayerID] > 11) places[currentPlayerID] = places[currentPlayerID] - 12;
			
			System.out.println(players.get(currentPlayerID)
					+ "'s new location is " 
					+ places[currentPlayerID]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			System.out.println(rockQuestions.removeFirst());		
	}
	
	
	private String currentCategory() {
		if (places[currentPlayerID] == 0) return "Pop";
		if (places[currentPlayerID] == 4) return "Pop";
		if (places[currentPlayerID] == 8) return "Pop";
		if (places[currentPlayerID] == 1) return "Science";
		if (places[currentPlayerID] == 5) return "Science";
		if (places[currentPlayerID] == 9) return "Science";
		if (places[currentPlayerID] == 2) return "Sports";
		if (places[currentPlayerID] == 6) return "Sports";
		if (places[currentPlayerID] == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayerID]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayerID]++;
				System.out.println(players.get(currentPlayerID)
						+ " now has "
						+ purses[currentPlayerID]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayerID++;
				if (currentPlayerID == players.size()) currentPlayerID = 0;
				
				return winner;
			} else {
				currentPlayerID++;
				if (currentPlayerID == players.size()) currentPlayerID = 0;
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayerID]++;
			System.out.println(players.get(currentPlayerID)
					+ " now has "
					+ purses[currentPlayerID]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayerID++;
			if (currentPlayerID == players.size()) currentPlayerID = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayerID)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayerID] = true;
		
		currentPlayerID++;
		if (currentPlayerID == players.size()) currentPlayerID = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayerID] == 6);
	}
}
