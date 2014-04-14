package maze.logic;

import java.util.ArrayList;

public class Dragon extends Elements{
	private boolean alive;
	private boolean asleep;
	private Labirinth lab;
	
	public Dragon(Labirinth lab){
		this.lab=lab;
		this.randomPos(lab);
		this.alive=true;
		this.asleep=false;
	}

	public void set_alive(boolean alive){
		this.alive=alive;
	}
	
	public boolean get_alive(){
		return this.alive;
	}
	
	public void set_asleep(boolean asleep){
		this.asleep=asleep;
	}
	
	public boolean get_asleep(){
		return this.asleep;
	}
	
	public void ShowDragon(Labirinth lab){
		if(this.alive){
			if(lab.labirinth[this.get_x()][this.get_y()] != 'E' ){
				lab.labirinth[this.get_x()][this.get_y()]='D';
			}else{
				lab.labirinth[this.get_x()][this.get_y()]='F';
			}
		}else{
			lab.labirinth[this.get_x()][this.get_y()]=' ';
		}
	}
	
	public void movement(){
		int move = (int)(Math.random() * 5);
		if(this.get_alive()==true){
			switch(move){
			case 0:
				this.set_asleep(false);
				if(!(lab.labirinth[this.get_x()+1][this.get_y()] == 'X') &&
						!(lab.labirinth[this.get_x()+1][this.get_y()] == 'S') && this.get_x() < 9){
					lab.labirinth[this.get_x()][this.get_y()]=' ';
					this.set_x(this.get_x()+1);
					lab.labirinth[this.get_x()][this.get_y()]='D';
				}
				break;
			case 1:
				this.set_asleep(false);
				if(!(lab.labirinth[this.get_x()-1][this.get_y()] == 'X') &&
						!(lab.labirinth[this.get_x()-1][this.get_y()] == 'S') && this.get_x() > 0){
					lab.labirinth[this.get_x()][this.get_y()]=' ';
					this.set_x(this.get_x()-1);
					lab.labirinth[this.get_x()][this.get_y()]='D';
				}
				break;
			case 2:
				this.set_asleep(false);
				if(!(lab.labirinth[this.get_x()][this.get_y()+1] == 'X') &&
						!(lab.labirinth[this.get_x()][this.get_y()+1] == 'S') && this.get_y() < 9){
					lab.labirinth[this.get_x()][this.get_y()]=' ';
					this.set_y(this.get_y()+1);
					lab.labirinth[this.get_x()][this.get_y()]='D';
				}
				break;
			case 3:
				this.set_asleep(false);
				if(!(lab.labirinth[this.get_x()][this.get_y()-1] == 'X') &&
						!(lab.labirinth[this.get_x()][this.get_y()-1] == 'S') && this.get_y() > 0){
					lab.labirinth[this.get_x()][this.get_y()]=' ';
					this.set_y(this.get_y()-1);
					lab.labirinth[this.get_x()][this.get_y()]='D';
				}
				break;
			case 4:
				this.set_asleep(true);
				if(this.get_asleep()){
					lab.labirinth[this.get_x()][this.get_y()]='Z';
				}
			}
		}
	}
	
	public void killDragon(){
		this.set_alive(false);
		for(int i =0; i< lab.labirinth.length; i++){
			for(int j=0; j<lab.labirinth.length; j++){
				if((lab.labirinth[i][j]=='D') || (lab.labirinth[i][j]=='Z')){
					lab.labirinth[i][j]=' ';
				}
			}
		}
		System.out.println("The Dragon Was Killed!");
	}
	
	void placeDragons(ArrayList<Dragon> d, int numb, Labirinth lab){
		for(int i=0; i<numb; i++){
			Dragon d1 = new Dragon(lab);
			d.add(d1);
		}
	}
}