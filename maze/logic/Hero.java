package maze.logic;

public class Hero extends Elements{
	private boolean alive;
	private Labyrinth lab;
	private boolean armado=false;

	public Hero(Labyrinth lab){
		this.lab=lab;
		this.randomPos(lab);
		this.alive=true;
	}

	public void set_alive(boolean alive){
		this.alive=alive;
	}

	public boolean get_alive(){
		return this.alive;
	}

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

	public void movement(String input, Eagle bird){
		if(this.get_alive()==true){
			switch(input){
			case "s":
			case "S":
				if(!(lab.getLabyrinth()[this.get_x()+1][this.get_y()] == 'X') && this.get_x() < lab.dim-1){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_x(this.get_x()+1);
					if(!bird.getTravelling()){
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
					if(!bird.getTravelling()){
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
					if(!bird.getTravelling()){
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
					if(!bird.getTravelling()){
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
					bird.setTravelling(true);
					break;
				}
			}
		}
	}

	public boolean getArmado() {
		return armado;
	}

	public void setArmado() {
		this.armado=true;
	}

	public void EquipHero(Sword s){
		if(this.get_x()==s.get_x() && this.get_y()==s.get_y()){
			this.setArmado();
			s.get_equiped();
		}
	}

	public void setArmado(boolean b) {
		this.armado=b;
	}


}