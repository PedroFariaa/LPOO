public class Hero{
	private int linha=1;
	private int coluna=1;
	private boolean armado=false;
	private boolean alive=true;
	

	public int getLinha(){
		return this.linha;
	}	
	public int getColuna(){
		return this.coluna;
	}
	public boolean getArmado(){
		return this.armado;
	}
	public boolean getAlive(){
		return this.alive;
	}
	
	public void setLinha(int nline){
		this.linha=nline;
	}
	public void setColuna(int ncol){
		this.coluna=ncol;
	}
	public void setArmado(boolean arm){
		this.armado=arm;
	}
	public void setAlive(boolean alive){
		this.alive=alive;
	}
	
	public  static void main(String[] args){
		
	}
	
}