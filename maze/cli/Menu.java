package maze.cli;
import java.util.Scanner;

import maze.logic.Dragon;
import maze.logic.Game;

public class Menu {

	public void Mostra_menu(){
		System.out.println("1 - Play Default Mode with one dragon");
		System.out.println("1 - Play Regular Mode");
		System.out.println("1 - Play Random Mode with one dragon");
		System.out.println("1 - Play Regular Mode");
	}

	public static void main(String[] argc){
		/*	Menu m = new Menu();
		m.Mostra_menu();		
		String userInput;
		Scanner scan = new Scanner (System.in);
		userInput = scan.nextLine();
		switch(userInput){
		case "1":
			// Play Game
			Game g = new Game();
		/*	int n_dragons;
		  	System.out.println("Chose the number of dragons you expect to find");
			Scanner scan2 = new Scanner (System.in);
			n_dragons = scan2.nextInt();
			g.CreatesDragons(n_dragons);*/
		/*
			g.PlayGame();
			break;
		case "2":
			//
			break;
		}*/
		Game g = new Game();
		int n_dragons;
		System.out.println("Chose the number of dragons you expect to find");
		Scanner scan2 = new Scanner (System.in);
		n_dragons = scan2.nextInt();
		g.InitializeDragons(n_dragons);
		g.PlayGame();
	}

}


