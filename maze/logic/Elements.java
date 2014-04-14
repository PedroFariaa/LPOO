package maze.logic;

public class Elements{
	private int x_pos;
	private int y_pos;

	public void set_x(int x){
		this.x_pos=x;
	}

	public void set_y(int y){
		this.y_pos=y;
	}

	public int get_x(){
		return this.x_pos;
	}

	public int get_y(){
		return this.y_pos;
	}

	public void randomPos(Labirinth lab){
		while( (lab.labirinth[this.get_x()][this.get_y()] == 'X') || (lab.labirinth[this.get_x()][this.get_y()] == 'S') || (lab.labirinth[this.get_x()][this.get_y()] == 'D')){
			int a = (int)(Math.random() * 10);
			int b = (int)(Math.random() * 10);
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