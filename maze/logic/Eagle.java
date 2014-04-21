package maze.logic;
/**
 * Eagle Class
 * 
 * @author Pedro Faria
 * @author Guilherme Routar
 *
 * @see Elements
 */
public class Eagle extends Elements{

	private boolean alive;
	private Labyrinth lab;
	private boolean has_sword=false;
	private boolean traveling=false;
	private int initialX;
	private int initialY;
	private char previous_cell;

	/**
	 * Constructs and initializes eagle
	 * 
	 * @param lab
	 * 			labyrinth where the Eagle is going to be placed
	 * @param heroi
	 * 			Hero that tames the Eagle
	 */
	public Eagle(Labyrinth lab, Hero heroi){
		this.lab=lab;
		this.set_x(heroi.get_x());
		this.set_y(heroi.get_y());
		heroi.get_x();
		heroi.get_y();
		this.alive=true;

	}

	/**
	 * Set Eagle's parameter alive
	 * 
	 * @param alive
	 * 			new Eagle's life condition
	 */
	public void set_alive(boolean alive){
		this.alive=alive;
	}

	/**
	 * Get Eagle's parameter alive
	 * 
	 * @return
	 * 		Eagle's life condition
	 */
	public boolean get_alive(){
		return this.alive;
	}

	/**
	 * Shows the Eagle in the Labyrinth
	 * 
	 * @param lab
	 * 			Labyrinth where the Eagle will be shown
	 */
	public void ShowEagle(Labyrinth lab){
		if(this.get_alive()){
			if(this.traveling){
				if(this.has_sword){
					lab.getLabyrinth()[this.get_x()][this.get_y()]='E';
				}else{
					lab.getLabyrinth()[this.get_x()][this.get_y()]='B';
				}
			}
		}else{
			lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
		}
	}

	/**
	 * Allow the Eagle's movement
	 * 
	 * @param espada
	 * 			Sword that the Eagle will carry
	 * @param heroi
	 * 			Hero that tames the eagle
	 */
	public void Movement(Sword espada, Hero heroi){
		if(this.has_sword){
			this.return_movement(espada, heroi);
		}else{
			this.going_movement(espada);
		}
	}

	/**
	 * Eagle's movement towards the sword position
	 * 
	 * @param espada
	 * 			Sword which the Eagle is flying towards to
	 */
	public void going_movement(Sword espada){
		if(this.get_alive()==true){
			//mesma linha
			if(this.get_x() == espada.get_x()){
				if(espada.get_y() > this.get_y()){
					// analisar metodo
					lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
					setPrevious_cell(lab.getLabyrinth()[this.get_x()][this.get_y()+1]);
					this.set_y(this.get_y()+1);
				}else{
					lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
					setPrevious_cell(lab.getLabyrinth()[this.get_x()][this.get_y()-1]);
					this.set_y(this.get_y()-1);
				}
			}

			//mesma coluna
			else if(this.get_y() == espada.get_y()){
				if(espada.get_x() > this.get_x()){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
					setPrevious_cell(lab.getLabyrinth()[this.get_x()+1][this.get_y()]);
					this.set_x(this.get_x()+1);
				}else{
					lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
					setPrevious_cell(lab.getLabyrinth()[this.get_x()-1][this.get_y()]);
					this.set_x(this.get_x()-1);
				}
			}

			//na diagonal
			else if((this.get_x() > espada.get_x()) && (this.get_y() > espada.get_y())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
				setPrevious_cell(lab.getLabyrinth()[this.get_x()-1][this.get_y()-1]);
				this.set_x(this.get_x()-1);
				this.set_y(this.get_y()-1);
			}else if((this.get_x() > espada.get_x()) && (this.get_y() < espada.get_y())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
				setPrevious_cell(lab.getLabyrinth()[this.get_x()-1][this.get_y()+1]);
				this.set_x(this.get_x()-1);
				this.set_y(this.get_y()+1);
			}else if((this.get_x() < espada.get_x()) && (this.get_y() > espada.get_y())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
				setPrevious_cell(lab.getLabyrinth()[this.get_x()+1][this.get_y()-1]);
				this.set_x(this.get_x()+1);
				this.set_y(this.get_y()-1);
			}else if((this.get_x() < espada.get_x()) && (this.get_y() < espada.get_y())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
				setPrevious_cell(lab.getLabyrinth()[this.get_x()+1][this.get_y()+1]);
				this.set_x(this.get_x()+1);
				this.set_y(this.get_y()+1);
			}
		}
	}

	/**
	 * Eagle's movement towards the it has been sent
	 * 
	 * @param espada
	 * 			Sword that is being carried by the Eagle
	 * @param heroi
	 * 			Hero that tames the Eagle
	 */
	public void return_movement(Sword espada, Hero heroi){
		if(this.get_alive()==true && !heroi.getArmado()){
			if(this.get_x() == this.getInitialX() && this.get_y()==this.getInitialY()){
//				this.EndsMovement(espada);
			}
			//mesma linha
			else if(this.get_x() == this.getInitialX()){
				if(this.getInitialY() > this.get_y()){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
					setPrevious_cell(lab.getLabyrinth()[this.get_x()][this.get_y()+1]);
					this.set_y(this.get_y()+1);
					espada.set_y(this.get_y());
				}else{
					lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
					setPrevious_cell(lab.getLabyrinth()[this.get_x()][this.get_y()-1]);
					this.set_y(this.get_y()-1);
					espada.set_y(this.get_y());
				}
			}

			//mesma coluna
			else if(this.get_y() == this.getInitialY()){
				if(this.getInitialX() > this.get_x()){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
					setPrevious_cell(lab.getLabyrinth()[this.get_x()+1][this.get_y()]);
					this.set_x(this.get_x()+1);
					espada.set_x(this.get_x());
				}else{
					lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
					setPrevious_cell(lab.getLabyrinth()[this.get_x()-1][this.get_y()]);
					this.set_x(this.get_x()-1);
					espada.set_x(this.get_x());
				}
			}

			//na diagonal
			else if((this.get_x() > this.getInitialX()) && (this.get_y() > this.getInitialY())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
				setPrevious_cell(lab.getLabyrinth()[this.get_x()-1][this.get_y()-1]);
				this.set_x(this.get_x()-1);
				this.set_y(this.get_y()-1);
				espada.set_x(this.get_x());
				espada.set_y(this.get_y());
			}else if((this.get_x() > this.getInitialX()) && (this.get_y() < this.getInitialY())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
				setPrevious_cell(lab.getLabyrinth()[this.get_x()-1][this.get_y()+1]);
				this.set_x(this.get_x()-1);
				this.set_y(this.get_y()+1);
				espada.set_x(this.get_x());
				espada.set_y(this.get_y());
			}else if((this.get_x() < this.getInitialX()) && (this.get_y() > this.getInitialY())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
				setPrevious_cell(lab.getLabyrinth()[this.get_x()+1][this.get_y()-1]);
				this.set_x(this.get_x()+1);
				this.set_y(this.get_y()-1);
				espada.set_x(this.get_x());
				espada.set_y(this.get_y());
			}else if((this.get_x() < this.getInitialX()) && (this.get_y() < this.getInitialY())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=getPrevious_cell();
				setPrevious_cell(lab.getLabyrinth()[this.get_x()+1][this.get_y()+1]);
				this.set_x(this.get_x()+1);
				this.set_y(this.get_y()+1);
				espada.set_x(this.get_x());
				espada.set_y(this.get_y());
			}
		}
	}

	/**
	 * Ends Eagle's movement, placing the sword in the ground
	 * 
	 * @param espada
	 * 			Sword that is going to be placed in the ground
	 */
	public void EndsMovement(Sword espada) {
		espada.set_x(this.get_x());
		espada.set_y(this.get_y());
		espada.set_equipped(false);
		this.alive=false;
	}

	/**
	 * Kill Eagle
	 * 
	 * @param espada
	 * 		Sword that will be dropped in the ground if the eagle was carrying one
	 */
	public void KillsEagle(Sword espada) {
		if(this.getHas_sword()){
			espada.set_x(this.get_x());
			espada.set_y(this.get_y());
			espada.set_equipped(false);
			lab.getLabyrinth()[this.get_x()][this.get_y()]='E';
		}
		this.setTraveling(false);
		lab.getLabyrinth()[this.get_x()][this.get_y()]=this.getPrevious_cell();
		this.alive=false;
	}

	/**
	 * Get Eagle's parameter traveling
	 * 
	 * @return
	 * 		Eagle's Traveling condition
	 */
	public boolean getTraveling() {
		return traveling;
	}

	/**
	 * Set Eagle's parameter traveling
	 * 
	 * @param trav
	 * 		new Eagle's Traveling condition
	 */
	public void setTraveling(boolean trav) {
		this.traveling=trav;
	}

	/**
	 * Sends Eagle into flight
	 */
	public void send_eagle() {
		this.setInitialX(this.get_x());
		this.setInitialY(this.get_y());
	}

	/**
	 * Get Eagle's parameter Has_sword
	 * 
	 * @return
	 * 		Eagle's carrying situation
	 */
	public boolean getHas_sword(){
		return this.has_sword;
	}

	/**
	 * Get Eagle's parameter InitialX
	 * 
	 * @return
	 * 		X Position from where the Eagle was sent
	 */
	public int getInitialX() {
		return initialX;
	}

	/**
	 * Set Eagle's parameter InitialX
	 * 
	 * @param initialX
	 * 		new X Position from where the Eagle was sent
	 */
	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}

	/**
	 * Get Eagle's parameter InitialY
	 * 
	 * @return
	 * 		Y Position from where the Eagle was sent
	 */
	public int getInitialY() {
		return initialY;
	}

	/**
	 * Set Eagle's parameter InitialY
	 * 
	 * @param initialY
	 * 		new Y Position from where the Eagle was sent
	 */
	public void setInitialY(int initialY) {
		this.initialY = initialY;
	}

	/**
	 * Eagle's parameter Has_sword
	 * 
	 * @param b
	 * 		new Eagle's carrying situation
	 */
	public void setHas_Sword(boolean b) {
		this.has_sword=b;
	}

	public char getPrevious_cell() {
		return previous_cell;
	}

	public void setPrevious_cell(char previous_cell) {
		this.previous_cell = previous_cell;
	}

}
