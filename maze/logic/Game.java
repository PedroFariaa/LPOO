package maze.logic;

import java.util.Scanner;
import java.util.Vector;
/**
 * 
 * Game.java - Related the labyrinth with all the Elements and allowing to play
 * 
 * @author Pedro Faria
 * @author Guilherme Routar
 *
 *@see Labyrinth
 *@see Elements
 */
public class Game{
	Labyrinth lab = new Labyrinth();
	private Vector<Dragon> drag = new Vector<Dragon>();
	Sword espada = new Sword(lab);
	Hero heroi = new Hero(lab);
	Eagle bird = new Eagle(lab, heroi);
	Display dis = new Display();
	Elements exit = new Elements();
	boolean FoundExit=false;
	
	/**
	 * Constructs a Game
	 */
	public Game(){
		espada.ShowSword(lab);
		bird.ShowEagle(lab);
		heroi.ShowHero(lab);
		for(int i=0; i<drag.size(); i++){
			drag.get(i).ShowDragon(lab, espada);
		}
	}
	
	/**
	 * Constructs and initializes a Game with defined parameters
	 * 
	 * @param lab
	 * 			labyrinth
	 * @param espada
	 * 			sword
	 * @param heroi
	 * 			hero
	 * @param exit
	 * 			exit cell
	 * @param drag
	 * 			vector of Dragons
	 */
	public Game(Labyrinth lab, Sword espada, Hero heroi, Elements exit, Vector<Dragon> drag){
		this.lab=lab;
		this.espada=espada;
		this.heroi=heroi;
		this.exit=exit;
		this.dis = new Display();
		this.drag = drag;
	}
	/**
	 * Checks if the Hero can leave the dungeon
	 * 
	 * @return
	 * 		hero's condition to leave the dungeon
	 */
	public boolean EndGame(){
		boolean end=false;
		for(int i=0; i<drag.size(); i++){
			if(drag.get(i).get_alive()==false){
				end=true;
				break;
			}else{
				if(!heroi.get_alive()){
					end=true;
					break;
				}
			}
		}
		return end;
	}
	
	/**
	 * Updates the Game
	 */
	public void CheckPositions(){
		//verifica dragao
		for(int i=0; i<drag.size(); i++){
			if(drag.get(i).get_sleeping()){
				if(heroi.getArmado() && heroi.get_x()==drag.get(i).get_x() && heroi.get_y()==drag.get(i).get_y()){
					drag.get(i).killDragon();
				}
			}
			else{
				if(heroi.getArmado() && heroi.get_x()==drag.get(i).get_x() && heroi.get_y()==drag.get(i).get_y()){
					drag.get(i).killDragon();
				}else if(!heroi.getArmado()){
					if((heroi.get_x()+1==drag.get(i).get_x() && heroi.get_y()==drag.get(i).get_y()) || (heroi.get_x()-1==drag.get(i).get_x() && heroi.get_y()==drag.get(i).get_y()) || (heroi.get_x()==drag.get(i).get_x() && heroi.get_y()+1==drag.get(i).get_y()) || (heroi.get_x()==drag.get(i).get_x() && heroi.get_y()==drag.get(i).get_y()-1) || (heroi.get_x()==drag.get(i).get_x() && heroi.get_y()==drag.get(i).get_y())){
						heroi.set_alive(false);
						System.out.println("You are Dead!");
					}
				}
			}
		}

		//verifica espada
		if(!heroi.getArmado()){
			if(heroi.get_x()==espada.get_x() && heroi.get_y()==espada.get_y()){
				heroi.setArmado();
				espada.set_equipped(true);
				bird.KillsEagle(espada);
			}
		}

		//verifica passaro
		if(bird.getTraveling() && bird.getHas_sword()){
			if(bird.get_x()==heroi.get_x() && bird.get_y()==heroi.get_y()){
				espada.set_equipped(true);
				bird.setHas_Sword(true);
				heroi.setArmado(true);
			}
		}

		if(!heroi.getArmado()){
			if(bird.get_x()==espada.get_x() && bird.get_y()==espada.get_y()){
				espada.set_equipped(true);
				bird.setHas_Sword(true);
				for(int a=0; a<lab.dim-1; a++){
					for(int b=0; b<lab.dim-1; b++){
						if(lab.getLabyrinth()[a][b]=='E'){
							lab.getLabyrinth()[a][b]=' ';
						}
					}
				}
				if(!espada.get_equipped() && bird.get_alive() && !heroi.getArmado()){
					lab.getLabyrinth()[bird.get_x()][bird.get_y()]='E';
				}
			}
		}
		if(bird.get_alive()){
			for(int i=0; i<drag.size(); i++){
				if(!drag.get(i).get_sleeping() && drag.get(i).get_x()==bird.get_x() && drag.get(i).get_y()==bird.get_y()){
					bird.KillsEagle(espada);
					if(bird.getTraveling()){
						System.out.println("The Eagle has been killed by a Dragon");
					}
				}
			}
		}

	}

	/**
	 * Allow the Player to Play the Game
	 * 
	 * @param sleepingDragon
	 * 			Dragon behavior
	 */
	public void PlayGame(boolean sleepingDragon){
		while(EndGame() == false){
			dis.DisplayMap(lab, heroi, drag, espada, bird);
			String userInput;
			Scanner scan = new Scanner (System.in);
			userInput = scan.nextLine();
			for(int i=0; i<drag.size(); i++){
				if(sleepingDragon==true){
					drag.get(i).movement_sleep();
					CheckPositions();
				}else{
					drag.get(i).movement();
					CheckPositions();
				}
			}
			heroi.movement(userInput, bird);
			dis.DisplayMap(lab, heroi, drag, espada, bird);
			CheckPositions();
			if(bird.getTraveling()){
				bird.Movement(espada, heroi);
			}
			dis.DisplayMap(lab, heroi, drag, espada, bird);
			CheckPositions();
		}
		while(FoundExit == false){
			dis.DisplayMap(lab, heroi, drag, espada, bird);
			String userInput;
			Scanner scan = new Scanner (System.in);
			userInput = scan.nextLine();
			for(int i=0; i<drag.size(); i++){
				if(sleepingDragon==true){
					drag.get(i).movement_sleep();
					dis.DisplayMap(lab, heroi, drag, espada, bird);
				}else{
					drag.get(i).movement();
					dis.DisplayMap(lab, heroi, drag, espada, bird);
				}
			}
			heroi.movement(userInput, bird);
			dis.DisplayMap(lab, heroi, drag, espada, bird);
			CheckPositions();
			CheckExit();
		}

	}

	/**
	 * Verifies if the Hero is already at the exit Cell
	 */
	private void CheckExit() {
		for(int i=0; i < lab.getLabyrinth().length; i++){
			for(int j=0; j < lab.getLabyrinth().length; j++){
				if(lab.getLabyrinth()[i][j] == 'S'){
					exit.set_x(i);
					exit.set_y(j);
					break;
				}
			}
		}
		if(lab.getLabyrinth()[exit.get_x()][exit.get_y()]=='A'){
			lab.getLabyrinth()[heroi.get_x()][heroi.get_y()] = ' ';
			lab.getLabyrinth()[exit.get_x()][exit.get_y()] = 'A';
			dis.DisplayMap(lab, heroi, drag, espada, bird);
			System.out.println("You are free at least!");
			FoundExit = true;
		}
	}

	/**
	 * Creates as many dragons as the Player wants to play with
	 * @param n
	 * 			Number of Dragons
	 */
	public void InitializeDragons(int n){
		for(int i=0; i<n; i++){
			drag.add(new Dragon(lab));
		}
	}


}

