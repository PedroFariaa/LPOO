package maze.logic;

public class Hero extends Elements{
	private boolean alive;
	private Labirinth lab;
	private boolean armado=false;
	
	public Hero(Labirinth lab){
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
	
	public void ShowHero(Labirinth lab){
		if(this.get_alive()){
			if(this.armado){
				lab.labirinth[this.get_x()][this.get_y()]='A';
			}else{
				lab.labirinth[this.get_x()][this.get_y()]='H';
			}
		}else{
			lab.labirinth[this.get_x()][this.get_y()]=' ';
		}
		
	}
	
	public void movement(String input, Eagle bird){
		if(this.get_alive()==true){
			switch(input){
			case "s":
			case "S":
				if(!(lab.labirinth[this.get_x()+1][this.get_y()] == 'X') && this.get_x() < lab.dim-1){
					lab.labirinth[this.get_x()][this.get_y()]=' ';
					this.set_x(this.get_x()+1);
					bird.set_x(this.get_x());
					lab.labirinth[this.get_x()][this.get_y()]='H';
					if(this.getArmado()==true){
						lab.labirinth[this.get_x()][this.get_y()]='A';
					}
				}
				break;
			case "w":
			case "W":
				if(!(lab.labirinth[this.get_x()-1][this.get_y()] == 'X') && this.get_x() > 0){
					lab.labirinth[this.get_x()][this.get_y()]=' ';
					this.set_x(this.get_x()-1);
					bird.set_x(this.get_x());
					lab.labirinth[this.get_x()][this.get_y()]='H';
					if(this.getArmado()==true){
						lab.labirinth[this.get_x()][this.get_y()]='A';
					}
				}
				break;
			case "a":
			case "A":
				if(!(lab.labirinth[this.get_x()][this.get_y()-1] == 'X') && this.get_y() > 0){
					lab.labirinth[this.get_x()][this.get_y()]=' ';
					this.set_y(this.get_y()-1);
					bird.set_y(this.get_y());
					lab.labirinth[this.get_x()][this.get_y()]='H';
					if(this.getArmado()==true){
						lab.labirinth[this.get_x()][this.get_y()]='A';
					}
				}
				break;
			case "d":
			case "D":
				if(!(lab.labirinth[this.get_x()][this.get_y()+1] == 'X') && this.get_y() < lab.dim-1){
					lab.labirinth[this.get_x()][this.get_y()]=' ';
					this.set_y(this.get_y()+1);
					bird.set_y(this.get_y());
					lab.labirinth[this.get_x()][this.get_y()]='H';
					if(this.getArmado()==true){
						lab.labirinth[this.get_x()][this.get_y()]='A';
					}
				}
				break;
			case "e":
			case "E":
				if(this.armado == false){
					bird.send_eagle();
					bird.setTravelling();
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
		/*
		for(int i=0; i< lab.labirinth.length; i++){
			for(int j=0; j<lab.labirinth.length; j++){
				if(lab.labirinth[i][j]=='E'){
					if(this.get_x()==i && this.get_y()==j){
						this.setArmado();
					}	
				}
			}
		}
		*/
		// Outra forma de equipar a espada - ainda por testar
		if(this.get_x()==s.get_x() && this.get_y()==s.get_y()){
			this.setArmado();
			s.get_equiped();
		}
	}
	
	
}