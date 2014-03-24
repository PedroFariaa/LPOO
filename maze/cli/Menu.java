package maze.cli;
import java.util.Scanner;

import maze.logic.Game;

public class Menu {
	public static void main(String[] argc){
		String userInput;
		Scanner scan = new Scanner (System.in);
		userInput = scan.nextLine();
		switch(userInput){
		case "1":
			// Play Game
			Game g = new Game();
		/*	int n_dragons;
			Scanner scan2 = new Scanner (System.in);
			n_dragons = scan2.nextInt();
			g.CreatesDragons(n_dragons);*/
			g.PlayGame();
			break;
		case "2":
			//
			break;
		}
		
		
	}
}


