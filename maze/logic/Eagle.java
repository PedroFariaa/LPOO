package maze.logic;

public class Eagle extends Elements{
	
	private boolean alive;
	private Labirinth lab;
	private boolean has_sword=false;
	private boolean travelling=false;
	private int initial_x;
	private int initial_y;
	
	public Eagle(Labirinth lab, Hero heroi){
		this.lab=lab;
		this.set_x(heroi.get_x());
		this.set_y(heroi.get_y());
		this.initial_x=heroi.get_x();
		this.initial_y=heroi.get_y();
		this.alive=true;
		
	}
	
	public void set_alive(boolean alive){
		this.alive=alive;
	}
	
	public boolean get_alive(){
		return this.alive;
	}
	
	public void ShowEagle(Labirinth lab){
		if(this.get_alive()){
			if(this.travelling){
				if(this.has_sword){
					lab.labirinth[this.get_x()][this.get_y()]='E';
				}else{
					lab.labirinth[this.get_x()][this.get_y()]='B';
				}
			}
		}else{
			lab.labirinth[this.get_x()][this.get_y()]=' ';
		}
	}
	
	public void Movement(Sword espada){
		if(this.has_sword){
			this.return_movement();
		}else{
			this.movement(espada);
		}
	}
	
	public void movement(Sword espada){
		if(this.get_alive()==true){
			//mesma linha
			if(this.get_x() == espada.get_x()){
				if(espada.get_x() > this.get_x()){
					this.set_x(this.get_x()+1);
				}else{
					this.set_x(this.get_x()-1);
				}
			}
			
			//mesma coluna
			else if(this.get_y() == espada.get_y()){
				if(espada.get_y() > this.get_y()){
					this.set_y(this.get_y()+1);
				}else{
					this.set_y(this.get_y()-1);
				}
			}
			
			//na diagonal
			else if((this.get_x() > espada.get_x()) && (this.get_y() > espada.get_y())){
				this.set_x(this.get_x()+1);
				this.set_y(this.get_y()+1);
			}else if((this.get_x() > espada.get_x()) && (this.get_y() < espada.get_y())){
				this.set_x(this.get_x()+1);
				this.set_y(this.get_y()-1);
			}else if((this.get_x() < espada.get_x()) && (this.get_y() > espada.get_y())){
				this.set_x(this.get_x()-1);
				this.set_y(this.get_y()+1);
			}else if((this.get_x() < espada.get_x()) && (this.get_y() < espada.get_y())){
				this.set_x(this.get_x()-1);
				this.set_y(this.get_y()-1);
			}
		}
	}

	public void return_movement(){
		if(this.get_alive()==true){
			//mesma linha
			if(this.get_x() == initial_x){
				if(initial_x > this.get_x()){
					this.set_x(this.get_x()+1);
				}else{
					this.set_x(this.get_x()-1);
				}
			}
			
			//mesma coluna
			else if(this.get_y() == initial_y){
				if(initial_y > this.get_y()){
					this.set_y(this.get_y()+1);
				}else{
					this.set_y(this.get_y()-1);
				}
			}
			
			//na diagonal
			else if((this.get_x() > initial_x) && (this.get_y() > initial_y)){
				this.set_x(this.get_x()+1);
				this.set_y(this.get_y()+1);
			}else if((this.get_x() > initial_x) && (this.get_y() < initial_y)){
				this.set_x(this.get_x()+1);
				this.set_y(this.get_y()-1);
			}else if((this.get_x() < initial_x) && (this.get_y() > initial_y)){
				this.set_x(this.get_x()-1);
				this.set_y(this.get_y()+1);
			}else if((this.get_x() < initial_x) && (this.get_y() < initial_y)){
				this.set_x(this.get_x()-1);
				this.set_y(this.get_y()-1);
			}
		}
	}

	
	public boolean getTravelling() {
		return travelling;
	}
	
	public void setTravelling() {
		if((this.get_x() != this.initial_x) || (this.get_y() != this.initial_y)){
			this.travelling=true;
		}
	}
	
}