package maze.logic;

public class Sword extends Elements{
	private boolean equiped;	
	
	public Sword(Labyrinth lab){
		this.randomPos(lab);
		this.equiped=false;
	}
	
	public boolean get_equiped(){
		return this.equiped;
	}
	
	public void ShowSword(Labyrinth lab){
		if(this.equiped == true){
			lab.getLabyrinth()[this.get_x()][this.get_y()]=' ';
		}else{
			lab.getLabyrinth()[this.get_x()][this.get_y()]='E';
		}
	}
	
	public void set_equiped(boolean equiped){
		this.equiped=equiped;
	}
}