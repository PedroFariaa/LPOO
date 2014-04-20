package maze.logic;
/**
 * Sword Class
 * 
 * @author Pedro Faria
 * @author Guilherme Routar
 * 
 * @see Elements
 */
public class Sword extends Elements{
	private boolean equipped;
	
	/**
	 * Constructs and initializes Sword
	 * 
	 * @param lab
	 * 			labyrinth where the sword is going to be placed
	 */
	public Sword(Labyrinth lab){
		this.randomPos(lab);
		this.equipped=false;
	}
	
	/**
	 * Get Sword's parameter equipped
	 * 
	 * @return
	 * 		Sword's parameter equipped
	 */
	public boolean get_equipped(){
		return this.equipped;
	}
	
	/**
	 * Shows the Sword in the Labyrinth
	 * 
	 * @param lab
	 * 			Labyrinth where the Sword will be shown
	 */
	public void ShowSword(Labyrinth lab){
		if(this.equipped == true){
			lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
		}else{
			lab.getLabyrinth()[this.get_x()][this.get_y()]='E';
		}
	}
	
	/**
	 * Set Sword's parameter equipped
	 * 
	 * @param alive
	 * 			new Sword's parameter equipped
	 */
	public void set_equipped(boolean equiped){
		this.equipped=equiped;
	}
}