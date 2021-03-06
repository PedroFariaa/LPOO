package maze.logic;

import java.util.Vector;
/**
 * Display.java - Shows the Game to the player
 * @author Pedro Faria e Guilherme Routar
 * @version 1.0
 */
public class Display{

	/**
	 * 
	 * Display the Game with all the Elements drawn on it
	 * 
	 * @param lab
	 * 			labyrinth
	 * @param h
	 * 			hero
	 * @param drag
	 * 			vector of Dragons
	 * @param s
	 * 			sword
	 * @param e
	 * 			eagle
	 */
	public void DisplayMap(Labyrinth lab, Hero h, Vector<Dragon> drag, Sword s, Eagle e){
		DrawAllElements(lab, h, drag, s, e);

		for(int i=0; i< lab.getLabyrinth().length; i++){
			for(int j=0; j<lab.getLabyrinth()[i].length; j++){
				System.out.print(lab.getLabyrinth()[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * 
	 * Places the Elements on the Labyrinth
	 * 
	 * @param lab
	 * 			labyrinth
	 * @param h
	 * 			hero
	 * @param drag
	 * 			vector of dragons
	 * @param s
	 * 			sword
	 * @param e
	 * 			eagle
	 */
	private void DrawAllElements(Labyrinth lab, Hero h, Vector<Dragon> drag, Sword s, Eagle e) {

		//draws the sword
		if(!s.get_equipped()){
			lab.getLabyrinth()[s.get_x()][s.get_y()] = 'E';
		}

		//draws the Hero
		if(h.get_alive()){
			if(h.getArmado()){
				lab.getLabyrinth()[h.get_x()][h.get_y()] = 'A';
			}else{
				lab.getLabyrinth()[h.get_x()][h.get_y()] = 'H';
			}
		}

		//draws the dragon(s)
		for(int i=0; i<drag.size(); i++){
			if(drag.get(i).get_alive()){
				if(!drag.get(i).get_sleeping()){
					lab.getLabyrinth()[drag.get(i).get_x()][drag.get(i).get_y()] = 'D';
				}else{
					lab.getLabyrinth()[drag.get(i).get_x()][drag.get(i).get_y()] = 'Z';
				}
			}
			if(drag.get(i).get_alive() && !s.get_equipped()){
				if(drag.get(i).get_x()==s.get_x() && drag.get(i).get_y()==s.get_y()){
					lab.getLabyrinth()[drag.get(i).get_x()][drag.get(i).get_y()] = 'F';
				}
			}else if(drag.get(i).get_alive() && !s.get_equipped()){
				lab.getLabyrinth()[s.get_x()][s.get_y()] = 'E';
			}else if(h.get_x()==s.get_x() && h.get_y()==s.get_y()){
				lab.getLabyrinth()[s.get_x()][s.get_y()] = 'A';
			}else{
				lab.getLabyrinth()[s.get_x()][s.get_y()] = ' ';
			}
		}

		//draws the eagle
		if(e.get_alive() && e.getTraveling() && e.getHas_sword()==false && (e.get_x() != h.get_x() || h.get_y() != h.get_y())){
			lab.getLabyrinth()[e.get_x()][e.get_y()] = 'B';
		}else if(e.get_alive() && e.getTraveling() && e.getHas_sword()){
			lab.getLabyrinth()[e.get_x()][e.get_y()] = 'E';
		}else if(h.getArmado()){
			if(h.get_x()==s.get_x() && h.get_y()==s.get_y()){
				lab.getLabyrinth()[s.get_x()][s.get_y()] = 'A';
			}else{
				lab.getLabyrinth()[s.get_x()][s.get_y()] = ' ';
			}
		}

	}	
}