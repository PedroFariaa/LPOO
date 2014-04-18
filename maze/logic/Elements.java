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

	public void randomPos(Labyrinth lab){
		while( (lab.getLabyrinth()[this.get_x()][this.get_y()] == 'X') || (lab.getLabyrinth()[this.get_x()][this.get_y()] == 'S') || (lab.getLabyrinth()[this.get_x()][this.get_y()] == 'D')){
			int a = (int)(Math.random() * lab.dim);
			int b = (int)(Math.random() * lab.dim);
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