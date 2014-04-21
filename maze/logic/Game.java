package maze.logic;

import java.awt.Menu;
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
	private Eagle bird = new Eagle(getLab(), getHeroi());
	Display dis = new Display();
	Elements exit = new Elements();
	public boolean FoundExit=false;

	/**
	 * Constructs a Game
	 */
	public Game(int nDragons){
		espada.ShowSword(getLab());
		getEagle().ShowEagle(getLab());
		getHeroi().ShowHero(getLab());
		for(int i=0; i<getDrag().size(); i++){
			getDrag().get(i).ShowDragon(getLab(), espada);
		}
		Display dis = new Display();
		//Menu Me = new Menu();
		this.InitializeDragons(nDragons);
		//boolean sleep = Me.SleepingDragon(this);

		System.out.println("this one");
		dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, getEagle());
		System.out.println("this one2a");
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
				getEagle().KillsEagle(espada);
			}
		}

		//verifica passaro
		if(getEagle().getTraveling() && getEagle().getHas_sword()){
			if(getEagle().get_x()==getHeroi().get_x() && getEagle().get_y()==getHeroi().get_y()){
				espada.set_equipped(true);
				getEagle().setHas_Sword(true);
				getHeroi().setArmado(true);
			}
		}

		if(!getHeroi().getArmado()){
			if(getEagle().get_x()==espada.get_x() && getEagle().get_y()==espada.get_y()){
				espada.set_equipped(true);
				getEagle().setHas_Sword(true);
				for(int a=0; a<getLab().getDim()-1; a++){
					for(int b=0; b<getLab().getDim()-1; b++){
						if(getLab().getLabyrinth()[a][b]=='E'){
							getLab().getLabyrinth()[a][b]=' ';
						}
					}
				}
				if(!espada.get_equipped() && getEagle().get_alive() && !getHeroi().getArmado()){
					getLab().getLabyrinth()[getEagle().get_x()][getEagle().get_y()]='E';
				}
			}
		}
		if(getEagle().get_alive()){
			for(int i=0; i<getDrag().size(); i++){
				if(!getDrag().get(i).get_sleeping() && getDrag().get(i).get_x()==getEagle().get_x() && getDrag().get(i).get_y()==getEagle().get_y()){
					getEagle().KillsEagle(espada);
					if(getEagle().getTraveling()){
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
			dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, getEagle());
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
			getHeroi().movement(userInput, getEagle());
			dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, getEagle());
			CheckPositions();
			if(getEagle().getTraveling()){
				getEagle().Movement(espada, getHeroi());
			}
			dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, getEagle());
			CheckPositions();
		}
		while(FoundExit == false){
			dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, getEagle());
			String userInput;
			Scanner scan = new Scanner (System.in);
			userInput = scan.nextLine();
			for(int i=0; i<getDrag().size(); i++){
				if(sleepingDragon==true){
					getDrag().get(i).movement_sleep();
					dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, getEagle());
				}else{
					getDrag().get(i).movement();
					dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, getEagle());
				}
			}
			getHeroi().movement(userInput, getEagle());
			dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, getEagle());
			CheckPositions();
			CheckExit();
		}
	}

	public void PlayGame(String direction){
		for(int i=0; i<getDrag().size(); i++){
			getDrag().get(i).movement_sleep();
			CheckPositions();
		}
		getHeroi().movement(direction, getEagle());
		CheckPositions();
		if(getEagle().getTraveling()){
			getEagle().Movement(espada, getHeroi());
		}
		CheckPositions();
	}

	/**
	 * Verifies if the Hero is already at the exit Cell
	 */
	public void CheckExit() {
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
			dis.DisplayMap(getLab(), getHeroi(), getDrag(), espada, getEagle());
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

	public Eagle getEagle() {
		return bird;
	}

	public void setEagle(Eagle bird) {
		this.bird = bird;
	}

	public Sword getEspada() {
		return this.espada;
	}

	public Display getDisplay() {
		return dis;
	}

}

