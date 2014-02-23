public class Mapa{
	static char[][] mapa = new char[10][10];
	static char[] espacos = {' ', 'X', 'S', 'H'};
	static Hero heroi;
	
	public static void drawInitialMap(){
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
	
	public static void drawDragon(){
		mapa[3][1] = 'D';
/*		
 		boolean pos = false;
		do{
			int a = 0 + (int) (Math.random()*10);
			int b = 0 + (int) (Math.random()*10);
			System.out.print("valor de a:" + a + "; valor de b:" + b);
			if( mapa[a][b] == ' '){
				System.out.print("posicao valida");
//				mapa[a][b] = 'D';
				pos = true;
			}
		}while(pos==false);
		*/
	}
	
	public static void drawHero(){
		mapa[1][1] = 'H';
	}
	
	public static void drawSword(){
		
	}
	
	public static void EquipHero(){
		heroi.setArmado(true);
	}
	
	public static void DisplayMap(){
		for(int i=0; i< mapa.length; i++){
			for(int j=0; j<mapa[i].length; j++){
				System.out.print("  "+ mapa[i][j] + " ");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	public static void main(String[] args){		
		
		drawInitialMap();
		drawDragon();
		drawHero();
		
		DisplayMap();
	}
}