public class Dragon{
	public int linha=1;
	public int coluna=1;
	public boolean vivo;
	
	public int getLinha(){
		return linha;
	}	
	public int getColuna(){
		return coluna;
	}
	public boolean getVivo(){
		return vivo;
	}
	
	public void setLinha(int nline){
		this.linha=nline;
	}
	public void setColuna(int ncol){
		this.coluna=ncol;
	}
	public void setVivo(boolean vivo){
		this.vivo=vivo;
	}
	
	public void HeroMovement(){
		char jogada='0';
		switch(jogada){
		case '0':
			this.setLinha(this.getLinha()+1);
			break;
		case '1':
			this.setLinha(this.getLinha()-1);
			break;
		case '2':
			this.setColuna(this.getLinha()+1);
			break;
		case '3':
			this.setColuna(this.getLinha()-1);
			break;
		}
	}
	
}