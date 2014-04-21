package maze.logic;

/**
 * Elements Class - Places into a Labyrinth. Elements will make a game possible.
 * Elements examples: Dragons, eagle, sword, hero and Cell.
 * 
 * @author Pedro Faria
 * @author Guilherme Routar
 * 
 * @see Dragon
 * @see Hero
 * @see Sword
 * @see Eagle
 * @see Cell
 */
public class Elements{
	private int x_pos;
	private int y_pos;

	/**
	 * Set Element's X Position
	 * 
	 * @param y
	 * 		new Element's X Position
	 */
	public void set_x(int x){
		this.x_pos=x;
	}

	/**
	 * Set Element's Y Position
	 * 
	 * @param y
	 * 		new Element's Y Position
	 */
	public void set_y(int y){
		this.y_pos=y;
	}
	
	/**
	 * Get Element's X position
	 * 
	 * @return
	 * 		Element's X position
	 */
	public int get_x(){
		return this.x_pos;
	}

	/**
	 * Get Element's Y position
	 * 
	 * @return
	 * 		Element's Y position
	 */
	public int get_y(){
		return this.y_pos;
	}
	
	/**
	 * Places a element randomly into a labyrinth
	 * 
	 * @param lab
	 * 			labyrinth where the Element is going to be placed
	 */
	public void randomPos(Labyrinth lab){
		while( (lab.getLabyrinth()[this.get_x()][this.get_y()] == 'X') || (lab.getLabyrinth()[this.get_x()][this.get_y()] == 'S') || (lab.getLabyrinth()[this.get_x()][this.get_y()] == 'D')){
			int a = (int)(Math.random() * lab.getDim());
			int b = (int)(Math.random() * lab.getDim());
			this.set_x(a);
			this.set_y(b);
		}	
	}

	
	@Override
	public boolean equals(Object obj) {
		boolean res = false;
		if ((this == obj) && (obj != null) && (this.getClass() == obj.getClass()))
			return true;

		Elements other = (Elements) obj;
		if (this.x_pos != other.get_x() || this.y_pos != other.get_y())
			res = false;

		return res;
	}
}