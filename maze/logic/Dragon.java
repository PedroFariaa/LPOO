package maze.logic;
/**
 * Dragon Class
 * 
 * @author Pedro Faria
 * @author Guilherme Routar
 *
 * @see Elements
 */
public class Dragon extends Elements{
	public boolean alive;
	private boolean sleeping;
	private Labyrinth lab;

	/**
	 * Constructs and initializes one Dragon
	 * 
	 * @param lab
	 * 			labyrinth where the dragon is going to be placed
	 */
	public Dragon(Labyrinth lab){
		this.lab=lab;
		this.randomPos(lab);
		this.alive=true;
		this.sleeping=false;
	}
	
	/**
	 * Set Dragon's parameter alive
	 * 
	 * @param alive
	 * 			new Dragon's life condition
	 */
	public void set_alive(boolean alive){
		this.alive=alive;
	}

	/**
	 * Get Dragon's parameter alive
	 * 
	 * @return
	 * 		Dragon's life condition
	 */
	public boolean get_alive(){
		return this.alive;
	}

	/**
	 * Set Dragon's parameter sleeping
	 * 
	 * @param alive
	 * 			new Dragon's sleeping condition
	 */
	public void set_sleeping(boolean sleeping){
		this.sleeping=sleeping;
	}
	
	/**
	 * Get Dragon's parameter sleeping
	 * 
	 * @return
	 * 		Dragon's sleeping condition
	 */
	public boolean get_sleeping(){
		return this.sleeping;
	}
	
	/**
	 * Shows the Dragon in the Labyrinth
	 * 
	 * @param lab
	 * 			Labyrinth where the Dragon will be shown
	 * @param espada
	 * 			Sword in the Same labyrinth
	 */
	public void ShowDragon(Labyrinth lab, Sword espada){
		if(this.alive){
			if(this.get_x()==espada.get_x() && this.get_y()==espada.get_y()){
				lab.getLabyrinth()[this.get_x()][this.get_y()]='F';
			}else if(!this.get_sleeping()){
				lab.getLabyrinth()[this.get_x()][this.get_y()]='D';
			}else{
				lab.getLabyrinth()[this.get_x()][this.get_y()]='Z';
			}
		}else{
			lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
		}
	}
	
	/**
	 * Moves the dragon allowing it to sleep
	 */
	public void movement_sleep(){
		int move = (int)(Math.random() * 5);
		if(this.get_alive()==true){
			switch(move){
			case 0:
				this.set_sleeping(false);
				if(!(lab.getLabyrinth()[this.get_x()+1][this.get_y()] == 'X') &&
						!(lab.getLabyrinth()[this.get_x()+1][this.get_y()] == 'S') && this.get_x() < lab.dim-1){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_x(this.get_x()+1);
					lab.getLabyrinth()[this.get_x()][this.get_y()]='D';
				}
				break;
			case 1:
				this.set_sleeping(false);
				if(!(lab.getLabyrinth()[this.get_x()-1][this.get_y()] == 'X') &&
						!(lab.getLabyrinth()[this.get_x()-1][this.get_y()] == 'S') && this.get_x() > 0){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_x(this.get_x()-1);
					lab.getLabyrinth()[this.get_x()][this.get_y()]='D';
				}
				break;
			case 2:
				this.set_sleeping(false);
				if(!(lab.getLabyrinth()[this.get_x()][this.get_y()+1] == 'X') &&
						!(lab.getLabyrinth()[this.get_x()][this.get_y()+1] == 'S') && this.get_y() < lab.dim-1){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_y(this.get_y()+1);
					lab.getLabyrinth()[this.get_x()][this.get_y()]='D';
				}
				break;
			case 3:
				this.set_sleeping(false);
				if(!(lab.getLabyrinth()[this.get_x()][this.get_y()-1] == 'X') && !(lab.getLabyrinth()[this.get_x()][this.get_y()-1] == 'S') && this.get_y() > 0){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_y(this.get_y()-1);
					lab.getLabyrinth()[this.get_x()][this.get_y()]='D';
				}
				break;
			case 4:
				this.set_sleeping(true);
				if(this.get_sleeping()){
					lab.getLabyrinth()[this.get_x()][this.get_y()]='Z';
				}
			}
		}else{
			lab.getLabyrinth()[this.get_x()][this.get_y()] = ' ';
		}
	}

	/**
	 * Moves the dragon without allowing it to sleep
	 */
	public void movement(){
		int move = (int)(Math.random() * 4);
		if(this.get_alive()==true){
			switch(move){
			case 0:
				if(!(lab.getLabyrinth()[this.get_x()+1][this.get_y()] == 'X') && !(lab.getLabyrinth()[this.get_x()+1][this.get_y()] == 'S') && this.get_x() < lab.dim-1){
					this.set_sleeping(false);
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_x(this.get_x()+1);
					lab.getLabyrinth()[this.get_x()][this.get_y()]='D';
				}
				break;
			case 1:
				if(!(lab.getLabyrinth()[this.get_x()-1][this.get_y()] == 'X') && !(lab.getLabyrinth()[this.get_x()-1][this.get_y()] == 'S') && this.get_x() > 0){
					this.set_sleeping(false);
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_x(this.get_x()-1);
					lab.getLabyrinth()[this.get_x()][this.get_y()]='D';
				}
				break;
			case 2:
				if(!(lab.getLabyrinth()[this.get_x()][this.get_y()+1] == 'X') && !(lab.getLabyrinth()[this.get_x()][this.get_y()+1] == 'S') && this.get_y() < lab.dim-1){
					this.set_sleeping(false);
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_y(this.get_y()+1);
					lab.getLabyrinth()[this.get_x()][this.get_y()]='D';
				}
				break;
			case 3:
				this.set_sleeping(false);
				if(!(lab.getLabyrinth()[this.get_x()][this.get_y()-1] == 'X') && !(lab.getLabyrinth()[this.get_x()][this.get_y()-1] == 'S') && this.get_y() > 0){
					this.set_sleeping(false);
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_y(this.get_y()-1);
					lab.getLabyrinth()[this.get_x()][this.get_y()]='D';
				}
				break;
			}
		}else{
			lab.getLabyrinth()[this.get_x()][this.get_y()] = ' ';
		}
	}
	
	/**
	 * Kill the Dragon
	 */
	public void killDragon(){
		if(this.get_alive()==true){
			this.set_alive(false);
			System.out.println("The Dragon Was Killed!");
		}
	}
	
	/**
	 * Awakes the Dragon
	 */
	public void wake() {
		this.sleeping=false;
	}
}