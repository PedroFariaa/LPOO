package maze.cli;
import java.util.Scanner;

import maze.logic.Game;

/**
 * Menu.java - Allows the user to configure game in command line interface
 * @author Pedro Faria 
 * @author Guilherme Routar
 */
public class Menu {

	Scanner scan = new Scanner(System.in);
	boolean random = false;
	
	/**
	 * Asks the Player with how many dragons the want to play with
	 * 
	 * @param g
	 * 			the game itself
	 */
	public void Number_dragons(Game g){
		int n_dragons;
		System.out.println("choose the number of dragons you expect to find");
		n_dragons = scan.nextInt();
		g.InitializeDragons(n_dragons);
	}
	
	/**
	 * Asks the Player if he/she wants to play against sleeping dragons or not
	 * 
	 * @param g
	 * 			the game itself
	 * 
	 * @return the Dragon behavior
	 */
	public boolean SleepingDragon(Game g){
		String s; boolean res;
		System.out.println("Choose Y if you want your dragon to sleep or N if you don't");
		s = scan.next();
		if(s.equals("N") || (s.equals("n"))){
			res = false;
		}else{
			res = true;
		}
		return res;
	}


	/**
	 * Main function
	 * 
	 * @param argc
	 */
	public static void main(String[] argc){
		Menu m = new Menu();
		Game g1 = new Game(2);
		m.Number_dragons(g1);
		boolean sleep = m.SleepingDragon(g1);
		g1.PlayGame(sleep);
	}

}


