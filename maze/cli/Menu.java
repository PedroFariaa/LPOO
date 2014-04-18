package maze.cli;
import java.util.Scanner;

import maze.logic.Game;

public class Menu {

	public void Number_dragons(Game g){
		int n_dragons;
		System.out.println("choose the number of dragons you expect to find");
		Scanner scan2 = new Scanner (System.in);
		n_dragons = scan2.nextInt();
		g.InitializeDragons(n_dragons);
	}

	public boolean SleepingDragon(Game g){
		String s; boolean res;
		System.out.println("Choose Y if you want your dragon to sleep or N if you don't");
		Scanner scan2 = new Scanner (System.in);
		s = scan2.next();
		if(s == "N" || s == "n"){
			res = false;
		}else{
			res = true;
		}
		return res;
	}

	public static void main(String[] argc){
		Menu m = new Menu();
		Game g1 = new Game();
		m.Number_dragons(g1);
		boolean sleep=m.SleepingDragon(g1);
		g1.PlayGame(sleep);
	}

}


