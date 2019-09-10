
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		Game aGame = new Game();
		
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");
		
		Random rand = new Random(Integer.parseInt(args[0]));
	
		do {
			
			aGame.moveBasedOnDieRoll(rand.nextInt(5) + 1);
			
			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.handleIncorrectAnswer();
			} else {
				notAWinner = aGame.handleCorrectAnswer();
			}
			
			
			
		} while (notAWinner);
		
	}
}
