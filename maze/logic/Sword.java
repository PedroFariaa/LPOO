package maze.logic;

public class Sword extends Elements{
	private boolean equiped;	
	
	public Sword(Labirinth lab){
		this.randomPos(lab);
		this.equiped=false;
	}
	
	public boolean get_equiped(){
		return this.equiped;
	}
	
	public void ShowSword(Labirinth lab){
		if(this.equiped == true){
			lab.labirinth[this.get_x()][this.get_y()]=' ';
		}else{
			lab.labirinth[this.get_x()][this.get_y()]='E';
		}
	}
	
	public void set_equiped(boolean equiped){
		this.equiped=equiped;
	}
}