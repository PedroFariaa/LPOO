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
	private Labyrinth lab = new Labyrinth();
	private Vector<Dragon> drag = new Vector<Dragon>();
	Sword espada = new Sword(getLab());
	private Hero heroi = new Hero(getLab());
	Eagle bird = new Eagle(getLab(), getHeroi());
	Display dis = new Display();
	Elements exit = new Elements();
	boolean FoundExit=false;
	
	/**
	 * Constructs a Game
	 */
	public Game(){
		espada.ShowSword(getLab());
		bird.ShowEagle(getLab());
		getHeroi().ShowHero(getLab());
		for(int i=0; i<getDrag().size(); i++){
			getDrag().get(i).ShowDragon(getLab(), espada);
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
		this.setLab(lab);
		this.espada=espada;
		this.setHeroi(heroi);
		this.exit=exit;
		this.dis = new Display();
		this.setDrag(drag);
	}
	/**
	 * Checks if the Hero can leave the dungeon
	 * 
	 * @return
	 * 		hero's condition to leave the dungeon
	 */
	public boolean EndGame(){
		boolean end=false;
		for(int i=0; i<getDrag().size(); i++){
			if(getDrag().get(i).get_alive()==false){
				end=true;
				break;
			}else{
				if(!getHeroi().get_alive()){
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
		for(int i=0; i<getDrag().size(); i++){
			if(getDrag().get(i).get_sleeping()){
				if(getHeroi().getArmado() && getHeroi().get_x()==getDrag().get(i).get_x() && getHeroi().get_y()==getDrag().get(i).get_y()){
					getDrag().get(i).killDragon();
				}
			}
			else{
				if(getHeroi().getArmado() && getHeroi().get_x()==getDrag().get(i).get_x() && getHeroi().get_y()==getDrag().get(i).get_y()){
					getDrag().get(i).killDragon();
				}else if(!getHeroi().getArmado()){
					if((getHeroi().get_x()+1==getDrag().get(i).get_x() && getHeroi().get_y()==getDrag().get(i).get_y()) || (getHeroi().get_x()-1==getDrag().get(i).get_x() && getHeroi().get_y()==getDrag().get(i).get_y()) || (getHeroi().get_x()==getDrag().get(i).get_x() && getHeroi().get_y()+1==getDrag().get(i).get_y()) || (getHeroi().get_x()==getDrag().get(i).get_x() && getHeroi().get_y()==getDrag().get(i).get_y()-1) || (getHeroi().get_x()==getDrag().get(i).get_x() && getHeroi().get_y()==getDrag().get(i).get_y())){
						getHeroi().set_alive(false);
						System.out.println("You are Dead!");
					}
				}
			}
		}

		//verifica espada
		if(!getHeroi().getArmado()){
			if(getHeroi().get_x()==espada.get_x() && getHeroi().get_y()==espada.get_y()){
				getHeroi().setArmado();
				espada.set_equipped(true);
				bird.KillsEagle(espada);
			}
		}

		//verifica passaro
		if(bird.getTraveling() && bird.getHas_sword()){
			if(bird.get_x()==getHeroi().get_x() && bird.get_y()==getHeroi().get_y()){
				espada.set_equipped(true);
				bird.setHas_Sword(true);
				getHeroi().setArmado(true);
			}
		}

		if(!getHeroi().getArmado()){
			if(bird.get_x()==espada.get_x() && bird.get_y()==espada.get_y()){
				espada.set_equipped(true);
				bird.setHas_Sword(true);
				for(int a=0; a<getLab().dim-1; a++){
					for(int b=0; b<getLab().dim-1; b++){
						if(getLab().getLabyrinth()[a][b]=='E'){
							getLab().getLabyrinth()[a][b]=' ';
						}
					}
				}
				if(!espada.get_equipped() && bird.get_alive() && !getHeroi().getArmado()){
					getLab().getLabyrinth()[bird.get_x()][bird.get_y()]='E';
				}
			}
		}
		if(bird.get_alive()){
			for(int i=0; i<getDrag().size(); i++){
				if(!getDrag().get(i).get_sleeping() && getDrag().get(i).get_x()==bird.get_x() && getDrag().get(i).get_y()==bird.get_y()){
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
			dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, bird);
			String userInput;
			Scanner scan = new Scanner (System.in);
			userInput = scan.nextLine();
			for(int i=0; i<getDrag().size(); i++){
				if(sleepingDragon==true){
					getDrag().get(i).movement_sleep();
					CheckPositions();
				}else{
					getDrag().get(i).movement();
					CheckPositions();
				}
			}
			getHeroi().movement(userInput, bird);
			dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, bird);
			CheckPositions();
			if(bird.getTraveling()){
				bird.Movement(espada, getHeroi());
			}
			dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, bird);
			CheckPositions();
		}
		while(FoundExit == false){
			dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, bird);
			String userInput;
			Scanner scan = new Scanner (System.in);
			userInput = scan.nextLine();
			for(int i=0; i<getDrag().size(); i++){
				if(sleepingDragon==true){
					getDrag().get(i).movement_sleep();
					dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, bird);
				}else{
					getDrag().get(i).movement();
					dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, bird);
				}
			}
			getHeroi().movement(userInput, bird);
			dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, bird);
			CheckPositions();
			CheckExit();
		}

	}

	/**
	 * Verifies if the Hero is already at the exit Cell
	 */
	private void CheckExit() {
		for(int i=0; i < getLab().getLabyrinth().length; i++){
			for(int j=0; j < getLab().getLabyrinth().length; j++){
				if(getLab().getLabyrinth()[i][j] == 'S'){
					exit.set_x(i);
					exit.set_y(j);
					break;
				}
			}
		}
		if(getLab().getLabyrinth()[exit.get_x()][exit.get_y()]=='A'){
			getLab().getLabyrinth()[getHeroi().get_x()][getHeroi().get_y()] = ' ';
			getLab().getLabyrinth()[exit.get_x()][exit.get_y()] = 'A';
			dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, bird);
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
			getDrag().add(new Dragon(getLab()));
		}
	}

	public Labyrinth getLab() {
		return lab;
	}

	public void setLab(Labyrinth lab) {
		this.lab = lab;
	}

	public Hero getHeroi() {
		return heroi;
	}

	public void setHeroi(Hero heroi) {
		this.heroi = heroi;
	}
	
	public Game Get_game(){
		return this;
	}

	public Vector<Dragon> getDrag() {
		return drag;
	}

	public void setDrag(Vector<Dragon> drag) {
		this.drag = drag;
	}

}

