public class Hero{
	public int linha=1;
	public int coluna=1;
	public boolean armado;
	
	public int getLinha(){
		return linha;
	}	
	public int getColuna(){
		return coluna;
	}
	public boolean getArmado(){
		return armado;
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
	
	public void HeroMovement(){
		char jogada = 'a';
		switch(jogada){
		case 'd':
		case 'D':
			this.setLinha(this.getLinha()+1);
			break;
		case 'a':
		case 'A':
			this.setLinha(this.getLinha()-1);
			break;
		case 'w':
		case 'W':
			this.setColuna(this.getLinha()+1);
			break;
		case 's':
		case 'S':
			this.setColuna(this.getLinha()-1);
			break;
		}
	}
	
}