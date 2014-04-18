package maze.logic;

public class Dragon extends Elements{
	private boolean alive;
	private boolean sleeping;
	private Labyrinth lab;

	public Dragon(Labyrinth lab){
		this.lab=lab;
		this.randomPos(lab);
		this.alive=true;
		this.sleeping=false;
	}

	public void set_alive(boolean alive){
		this.alive=alive;
	}

	public boolean get_alive(){
		return this.alive;
	}

	public void set_sleeping(boolean sleeping){
		this.sleeping=sleeping;
	}

	public boolean get_sleeping(){
		return this.sleeping;
	}
	
	public void ShowDragon(Labyrinth lab, Sword espada){
		if(this.alive){
			if(this.get_x()==espada.get_x() && this.get_y()==espada.get_y()){
				lab.getLabyrinth()[this.get_x()][this.get_y()]='F';
			}else if(this.get_sleeping()){
				lab.getLabyrinth()[this.get_x()][this.get_y()]='Z';
			}else{
				lab.getLabyrinth()[this.get_x()][this.get_y()]='D';
			}
		}else{
			lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
		}
	}

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

	public void movement(){
		int move = (int)(Math.random() * 4);
		if(this.get_alive()==true){
			switch(move){
			case 0:
				if(!(lab.getLabyrinth()[this.get_x()+1][this.get_y()] == 'X') && !(lab.getLabyrinth()[this.get_x()+1][this.get_y()] == 'S') && this.get_x() < lab.dim-1){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_x(this.get_x()+1);
					lab.getLabyrinth()[this.get_x()][this.get_y()]='D';
				}
				break;
			case 1:
				if(!(lab.getLabyrinth()[this.get_x()-1][this.get_y()] == 'X') && !(lab.getLabyrinth()[this.get_x()-1][this.get_y()] == 'S') && this.get_x() > 0){
					lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
					this.set_x(this.get_x()-1);
					lab.getLabyrinth()[this.get_x()][this.get_y()]='D';
				}
				break;
			case 2:
				if(!(lab.getLabyrinth()[this.get_x()][this.get_y()+1] == 'X') && !(lab.getLabyrinth()[this.get_x()][this.get_y()+1] == 'S') && this.get_y() < lab.dim-1){
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
			}
		}else{
			lab.getLabyrinth()[this.get_x()][this.get_y()] = ' ';
		}
	}
	
	public void killDragon(){
		if(this.get_alive()==true){
			this.set_alive(false);
			System.out.println("The Dragon Was Killed!");
		}
	}

	public void wake() {
		this.sleeping=false;
	}
}