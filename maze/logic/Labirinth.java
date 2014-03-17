package maze.logic;

public class Labirinth{
	
	char[][]labirinth = new char[10][10];
	
	public Labirinth(){
		//constroi o contorno à volta do labirinth
		for(int a=0; a<labirinth.length; a++){
			for(int b=0; b<labirinth.length; b++){
				labirinth[a][b]= ' ';
			}
		}
		
		for(int i=0; i<labirinth.length; i++){
			labirinth[0][i] = 'X';
			labirinth[i][0] = 'X';
			labirinth[9][i] = 'X';
			labirinth[i][9] = 'X';
		}
		
		labirinth[2][2] = 'X';labirinth[2][3] = 'X';labirinth[2][5] = 'X';labirinth[2][7] = 'X';
		labirinth[3][2] = 'X';labirinth[3][3] = 'X';labirinth[3][5] = 'X';labirinth[3][7] = 'X';
		labirinth[4][2] = 'X';labirinth[4][3] = 'X';labirinth[4][5] = 'X';labirinth[4][7] = 'X';
		labirinth[6][2] = 'X';labirinth[6][3] = 'X';labirinth[6][5] = 'X';labirinth[6][7] = 'X';
		labirinth[7][2] = 'X';labirinth[7][3] = 'X';labirinth[7][5] = 'X';labirinth[7][7] = 'X';
		labirinth[5][7] = 'X';labirinth[7][3] = 'X';labirinth[8][2] = 'X';labirinth[8][3] = 'X';
		labirinth[4][9] = 'S'; //saida
	}
}