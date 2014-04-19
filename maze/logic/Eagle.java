package maze.logic;

public class Eagle extends Elements{

	private boolean alive;
	private Labyrinth lab;
	private boolean has_sword=false;
	private boolean travelling=false;
	private int initialX;
	private int initialY;
	private char previous_cell;

	public Eagle(Labyrinth lab, Hero heroi){
		this.lab=lab;
		this.set_x(heroi.get_x());
		this.set_y(heroi.get_y());
		heroi.get_x();
		heroi.get_y();
		this.alive=true;

	}

	public void set_alive(boolean alive){
		this.alive=alive;
	}

	public boolean get_alive(){
		return this.alive;
	}

	public void ShowEagle(Labyrinth lab){
		if(this.get_alive()){
			if(this.travelling){
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

	public void Movement(Sword espada, Hero heroi){
		if(this.has_sword){
			this.return_movement(espada, heroi);
		}else{
			this.going_movement(espada);
		}
	}

	public void going_movement(Sword espada){
		if(this.get_alive()==true){
			//mesma linha
			if(this.get_x() == espada.get_x()){
				if(espada.get_y() > this.get_y()){
					// analisar metodo
					lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
					previous_cell=lab.getLabyrinth()[this.get_x()][this.get_y()+1];
					this.set_y(this.get_y()+1);
				}else{
					lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
					previous_cell=lab.getLabyrinth()[this.get_x()][this.get_y()-1];
					this.set_y(this.get_y()-1);
				}
			}

			//mesma coluna
			else if(this.get_y() == espada.get_y()){
				if(espada.get_x() > this.get_x()){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
					previous_cell=lab.getLabyrinth()[this.get_x()+1][this.get_y()];
					this.set_x(this.get_x()+1);
				}else{
					lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
					previous_cell=lab.getLabyrinth()[this.get_x()-1][this.get_y()];
					this.set_x(this.get_x()-1);
				}
			}

			//na diagonal
			else if((this.get_x() > espada.get_x()) && (this.get_y() > espada.get_y())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
				previous_cell=lab.getLabyrinth()[this.get_x()-1][this.get_y()-1];
				this.set_x(this.get_x()-1);
				this.set_y(this.get_y()-1);
			}else if((this.get_x() > espada.get_x()) && (this.get_y() < espada.get_y())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
				previous_cell=lab.getLabyrinth()[this.get_x()-1][this.get_y()+1];
				this.set_x(this.get_x()-1);
				this.set_y(this.get_y()+1);
			}else if((this.get_x() < espada.get_x()) && (this.get_y() > espada.get_y())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
				previous_cell=lab.getLabyrinth()[this.get_x()+1][this.get_y()-1];
				this.set_x(this.get_x()+1);
				this.set_y(this.get_y()-1);
			}else if((this.get_x() < espada.get_x()) && (this.get_y() < espada.get_y())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
				previous_cell=lab.getLabyrinth()[this.get_x()+1][this.get_y()+1];
				this.set_x(this.get_x()+1);
				this.set_y(this.get_y()+1);
			}
		}
	}



	public void return_movement(Sword espada, Hero heroi){
		if(this.get_alive()==true && !heroi.getArmado()){
			if(this.get_x() == this.getInitialX() && this.get_y()==this.getInitialY()){
//				this.EndsMovement(espada);
			}
			//mesma linha
			else if(this.get_x() == this.getInitialX()){
				if(this.getInitialY() > this.get_y()){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
					previous_cell=lab.getLabyrinth()[this.get_x()][this.get_y()+1];
					this.set_y(this.get_y()+1);
					espada.set_y(this.get_y());
				}else{
					lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
					previous_cell=lab.getLabyrinth()[this.get_x()][this.get_y()-1];
					this.set_y(this.get_y()-1);
					espada.set_y(this.get_y());
				}
			}

			//mesma coluna
			else if(this.get_y() == this.getInitialY()){
				if(this.getInitialX() > this.get_x()){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
					previous_cell=lab.getLabyrinth()[this.get_x()+1][this.get_y()];
					this.set_x(this.get_x()+1);
					espada.set_x(this.get_x());
				}else{
					lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
					previous_cell=lab.getLabyrinth()[this.get_x()-1][this.get_y()];
					this.set_x(this.get_x()-1);
					espada.set_x(this.get_x());
				}
			}

			//na diagonal
			else if((this.get_x() > this.getInitialX()) && (this.get_y() > this.getInitialY())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
				previous_cell=lab.getLabyrinth()[this.get_x()-1][this.get_y()-1];
				this.set_x(this.get_x()-1);
				this.set_y(this.get_y()-1);
				espada.set_x(this.get_x());
				espada.set_y(this.get_y());
			}else if((this.get_x() > this.getInitialX()) && (this.get_y() < this.getInitialY())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
				previous_cell=lab.getLabyrinth()[this.get_x()-1][this.get_y()+1];
				this.set_x(this.get_x()-1);
				this.set_y(this.get_y()+1);
				espada.set_x(this.get_x());
				espada.set_y(this.get_y());
			}else if((this.get_x() < this.getInitialX()) && (this.get_y() > this.getInitialY())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
				previous_cell=lab.getLabyrinth()[this.get_x()+1][this.get_y()-1];
				this.set_x(this.get_x()+1);
				this.set_y(this.get_y()-1);
				espada.set_x(this.get_x());
				espada.set_y(this.get_y());
			}else if((this.get_x() < this.getInitialX()) && (this.get_y() < this.getInitialY())){
				lab.getLabyrinth()[this.get_x()][this.get_y()]=previous_cell;
				previous_cell=lab.getLabyrinth()[this.get_x()+1][this.get_y()+1];
				this.set_x(this.get_x()+1);
				this.set_y(this.get_y()+1);
				espada.set_x(this.get_x());
				espada.set_y(this.get_y());
			}
		}
	}

	public void EndsMovement(Sword espada) {
		espada.set_x(this.get_x());
		espada.set_y(this.get_y());
		espada.set_equiped(false);
		this.alive=false;
	}

	public void KillsEagle(Sword espada) {
		if(this.getHas_sword()){
			espada.set_x(this.get_x());
			espada.set_y(this.get_y());
			espada.set_equiped(false);
			lab.getLabyrinth()[this.get_x()][this.get_y()]='E';
		}
		this.setTravelling(false);
		lab.getLabyrinth()[this.get_x()][this.get_y()]=this.previous_cell;
		this.alive=false;
	}

	public boolean getTravelling() {
		return travelling;
	}

	public void setTravelling(boolean trav) {
		this.travelling=trav;
	}

	public void send_eagle() {
		this.setInitialX(this.get_x());
		this.setInitialY(this.get_y());
	}

	public boolean getHas_sword(){
		return this.has_sword;
	}

	public int getInitialX() {
		return initialX;
	}

	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}

	public int getInitialY() {
		return initialY;
	}

	public void setInitialY(int initialY) {
		this.initialY = initialY;
	}

	public void setHas_Sword(boolean b) {
		this.has_sword=b;
	}

}
