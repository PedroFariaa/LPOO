import java.util.Random;

public class Mapa{
	char[][] mapa = new char[10][10];
	char[] espacos = {' ', 'X', 'S', 'H'};
	private Hero heroi;
	private Dragon dragon;
	
	public void drawInitialMap(){
		//constroi o contorno à volta do mapa
		for(int i=0; i<mapa.length; i++){
			mapa[0][i] = espacos[1];
			mapa[i][0] = espacos[1];
			mapa[9][i] = espacos[1];
			mapa[i][9] = espacos[1];
		}
		
		mapa[2][2] = espacos[1];mapa[2][3] = espacos[1];mapa[2][5] = espacos[1];mapa[2][7] = espacos[1];
		mapa[3][2] = espacos[1];mapa[3][3] = espacos[1];mapa[3][5] = espacos[1];mapa[3][7] = espacos[1];
		mapa[4][2] = espacos[1];mapa[4][3] = espacos[1];mapa[4][5] = espacos[1];mapa[4][7] = espacos[1];
		mapa[6][2] = espacos[1];mapa[6][3] = espacos[1];mapa[6][5] = espacos[1];mapa[6][7] = espacos[1];
		mapa[7][2] = espacos[1];mapa[7][3] = espacos[1];mapa[7][5] = espacos[1];mapa[7][7] = espacos[1];
		mapa[5][7] = espacos[1];mapa[7][3] = espacos[1];mapa[8][2] = espacos[1];mapa[8][3] = espacos[1];
		mapa[4][9] = espacos[2]; //saida
	}
	
	public void drawDragon(){
		//mapa[3][1] = 'D';
		
 		boolean pos = false;
 		while(!pos){
			Random rand = new Random(47);
			int a, b;
			a = rand.nextInt(10);
			b = rand.nextInt(10);
			if( mapa[dragon.getLinha()][dragon.getColuna()] == ' '){
				mapa[dragon.getLinha()][dragon.getColuna()] = 'D';
				pos = true;
			}
		}	
	}
	
	public void drawHero(){
		if(heroi.getArmado() == false){
			mapa[1][1] = 'H';
		}
		else mapa[1][1] = 'A';
	}
	
	public void drawSword(){
		if(heroi.getArmado() == false){
			//mostra espada
		}
	}
	
	public void EquipHero(){
		heroi.setArmado(true);
	}
	
	public void DisplayMap(){
		for(int i=0; i< mapa.length; i++){
			for(int j=0; j<mapa[i].length; j++){
				System.out.print("  "+ mapa[i][j]);
			}
			System.out.println();
			System.out.println();
		}
	}
	
	public static void main(String[] args){		
		
		do{
			drawInitialMap();
			drawDragon();
			drawHero();
			
			DisplayMap();
		}while();
	}
}