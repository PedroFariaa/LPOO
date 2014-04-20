package maze.logic;
/**
 * 
 * Represents one cell of the Labyrinth. This was used
 * to generate the Random Labyrinth
 * 
 * @author Pedro Faria
 * @author Guilherme Routar
 *
 */
public class Cell extends Elements{
	/**
	 * Constructs and initializes the cell at (0,0) location
	 */
	public Cell(){
		this.set_x(0);
		this.set_y(0);
	}
	/**
	 * Constructs and initializes the cell at (x,y) location
	 * 
	 * @param x
	 * 			Position X of the Cell
	 * @param y
	 *  		Position Y of the Cell
	 */
	public Cell(int x, int y){
		this.set_x(x);
		this.set_y(y);
	}
}
