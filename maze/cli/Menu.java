package maze.cli;
import java.util.Scanner;

import maze.logic.Game;

public class Menu {

	Scanner scan = new Scanner(System.in);
	boolean random = false;
	
	private void Number_dragons(Game g){
		int n_dragons;
		System.out.println("choose the number of dragons you expect to find");
		n_dragons = scan.nextInt();
		g.InitializeDragons(n_dragons);
	}

	private boolean SleepingDragon(Game g){
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

//	private boolean RandomLab(boolean rand){
//		String Slab;
//		System.out.println("Do you want to play in a Random Labyrinth ?");
//		Slab = scan.next();
//		if (Slab.equals("y") || Slab.equals("Y")){
//			rand = true;
//		}
//		return rand;
//	}
//	
//	private int LabSize(){
//		System.out.println("Tamanho do labirinto (tem de ser ímpar): ");
//		int size = scan.nextInt();
//		return size;
//	}

	public static void main(String[] argc){
//		boolean rand = false;
//		int s=11;
		Menu m = new Menu();
//		m.RandomLab(rand);
//		if(rand==true){
//			s = m.LabSize();
//		}
		Game g1 = new Game();
		m.Number_dragons(g1);
		boolean sleep = m.SleepingDragon(g1);
		g1.PlayGame(sleep);
	}

}


