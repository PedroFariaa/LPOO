package maze.logic;

/**
 * Hero Class
 * 
 * @author Pedro Faria
 * @author Guilherme Routar
 * 
 * @see Elements
 */
public class Hero extends Elements{
	private boolean alive;
	private Labyrinth lab;
	private boolean armado=false;

	/**
	 * Constructs and initializes Hero
	 * 
	 * @param lab
	 * 			labyrinth where the Hero is going to be placed
	 */
	public Hero(Labyrinth lab){
		this.lab=lab;
		this.randomPos(lab);
		this.alive=true;
	}

	/**
	 * Set Hero's parameter alive
	 * 
	 * @param alive
	 * 			new Hero's life condition
	 */
	public void set_alive(boolean alive){
		this.alive=alive;
	}

	/**
	 * Get Hero's parameter alive
	 * 
	 * @return
	 * 		Hero's life condition
	 */
	public boolean get_alive(){
		return this.alive;
	}

	/**
	 * Shows the Hero in the Labyrinth
	 * 
	 * @param lab
	 * 			Labyrinth where the Hero will be shown
	 */
	public void ShowHero(Labyrinth lab){
		if(this.get_alive()){
			if(this.armado){
				lab.getLabyrinth()[this.get_x()][this.get_y()]='A';
			}else{
				lab.getLabyrinth()[this.get_x()][this.get_y()]='H';
			}
		}else{
			lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
		}

	}

	/**
	 * Allow the player to Move the Hero and send the Eagle
	 */
	public void movement(String input, Eagle bird){
		if(this.get_alive()==true){
			switch(input){
			case "s":
			case "S":
				if(!(lab.getLabyrinth()[this.get_x()+1][this.get_y()] == 'X') && this.get_x() < lab.dim-1){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_x(this.get_x()+1);
					if(!bird.getTraveling()){
						bird.set_x(this.get_x());
					}
					lab.getLabyrinth()[this.get_x()][this.get_y()]='H';
					if(this.getArmado()==true){
						lab.getLabyrinth()[this.get_x()][this.get_y()]='A';
					}
				}
				break;
			case "w":
			case "W":
				if(!(lab.getLabyrinth()[this.get_x()-1][this.get_y()] == 'X') && this.get_x() > 0){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_x(this.get_x()-1);
					if(!bird.getTraveling()){
						bird.set_x(this.get_x());
					}
					lab.getLabyrinth()[this.get_x()][this.get_y()]='H';
					if(this.getArmado()==true){
						lab.getLabyrinth()[this.get_x()][this.get_y()]='A';
					}
				}
				break;
			case "a":
			case "A":
				if(!(lab.getLabyrinth()[this.get_x()][this.get_y()-1] == 'X') && this.get_y() > 0){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_y(this.get_y()-1);
					if(!bird.getTraveling()){
						bird.set_y(this.get_y());
					}
					lab.getLabyrinth()[this.get_x()][this.get_y()]='H';
					if(this.getArmado()==true){
						lab.getLabyrinth()[this.get_x()][this.get_y()]='A';
					}
				}
				break;
			case "d":
			case "D":
				if(!(lab.getLabyrinth()[this.get_x()][this.get_y()+1] == 'X') && this.get_y() < lab.dim-1){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_y(this.get_y()+1);
					if(!bird.getTraveling()){
						bird.set_y(this.get_y());
					}
					lab.getLabyrinth()[this.get_x()][this.get_y()]='H';
					if(this.getArmado()==true){
						lab.getLabyrinth()[this.get_x()][this.get_y()]='A';
					}
				}
				break;
			case "e":
			case "E":
				if(this.armado == false){
					bird.send_eagle();
					bird.setTraveling(true);
					break;
				}
			}
		}
	}

	/**
	 * Get Hero's parameter Armado
	 * 
	 * @return
	 * 		Hero's sword possession
	 */
	public boolean getArmado() {
		return armado;
	}

	/**
	 * Set Hero's parameter Armado
	 * 
	 */
	public void setArmado() {
		this.armado=true;
	}

	/**
	 * Set Hero's parameter Armado
	 * 
	 * @param alive
	 * 			new Hero's sword possession
	 */
	public void setArmado(boolean b) {
		this.armado=b;
	}

	/**
	 * Arm Hero with a Sword
	 * 
	 * @param s
	 * 		Sword equipped by the Hero
	 */
	public void EquipHero(Sword s){
		if(this.get_x()==s.get_x() && this.get_y()==s.get_y()){
			this.setArmado();
			s.get_equipped();
		}
	}


}