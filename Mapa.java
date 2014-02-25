import java.util.Random;
import java.util.Scanner;

public class Mapa{
	char[][]mapa = new char[10][10];
	char[] espacos = {' ', 'X', 'S', 'H'};
	private Hero heroi;
	private Dragon dragon;
	
	public Mapa(){
		heroi=new Hero();
		dragon=new Dragon();
	}
	
	public void drawInitialMap(){
		//constroi o contorno à volta do mapa
		for(int a=0; a<mapa.length; a++){
			for(int b=0; b<mapa.length; b++){
				mapa[a][b]= ' ';
			}
		}
		
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
		boolean pos = false;
		while(pos == false){
			int a = (int)(Math.random() * 10);
			int b = (int)(Math.random() * 10);
			dragon.setLinha(a);
			dragon.setColuna(b);
			if((mapa[dragon.getLinha()][dragon.getColuna()] != 'X') && (mapa[dragon.getLinha()][dragon.getColuna()] != 'H') && (mapa[dragon.getLinha()][dragon.getColuna()] != 'A')){
				mapa[dragon.getLinha()][dragon.getColuna()] = 'D';
				pos=true;
			}
		}	
	}
	
	public void drawHero(){
		boolean pos = false;
		while(pos == false){
			int a = (int)(Math.random() * 10);
			int b = (int)(Math.random() * 10);
			heroi.setLinha(a);
			heroi.setColuna(b);
			if((mapa[a][b] != 'X') && (mapa[a][b] != 'H') && (mapa[a][b] != 'A')){	
				if(heroi.getArmado() == false){
					mapa[heroi.getLinha()][heroi.getColuna()] = 'H';
				}
				else mapa[heroi.getLinha()][heroi.getColuna()] = 'A';
				pos=true;
			}
		}
		
	}
	
	public void drawSword(){
		boolean pos = false;
		while(pos == false){
			int a = (int)(Math.random() * 10);
			int b = (int)(Math.random() * 10);
			if((mapa[a][b] != 'X') && (mapa[a][b] != 'H') && (mapa[a][b] != 'A') && (mapa[a][b] != 'D')){
				mapa[a][b] = 'E';
				pos=true;
			}
		}
	}
	
	public void EquipHero(){
		for(int i=0; i< mapa.length; i++){
			for(int j=0; j<mapa.length; j++){
				if(mapa[i][j]=='E'){
					if(heroi.getLinha()==i && heroi.getColuna()==j){
						heroi.setArmado(true);
						mapa[i][j]=' ';
					}	
				}
			}
		}
	}
	
	public void killDragon(){
		dragon.setVivo(false);
		for(int i =0; i< mapa.length; i++){
			for(int j=0; j<mapa.length; j++){
				if((mapa[i+1][j]=='D') || (mapa[i-1][j]=='D') || (mapa[i][j+1]=='D') || (mapa[i][j-1]=='D')){
					mapa[i][j]=' ';
				}
			}
		}
	}
	
	public boolean EndGame(){
		boolean end=false;
		if(heroi.getArmado()==true && dragon.getVivo()==false){
			for(int i=0; i<mapa.length; i++){
				for(int j=0; j<mapa.length; j++){
					if(mapa[i][j]=='S'){
						if((i==heroi.getLinha()) && j==heroi.getColuna()){
								end=true;
						}
					}
				}
			}
		}
		return end;
	}
	
	public void CheckPositions(){
		//verifica dragao
		int drc = dragon.getColuna();
		int drl = dragon.getLinha();
		if((heroi.getLinha()==drl+1 && heroi.getColuna()==drc) ||
				(heroi.getLinha()==dragon.getLinha()-1 && heroi.getColuna()==dragon.getColuna()) ||
				(heroi.getLinha()==dragon.getLinha() && heroi.getColuna()==dragon.getColuna()+1) ||
				(heroi.getLinha()==dragon.getLinha() && heroi.getColuna()==dragon.getColuna()-1)){
			if(heroi.getArmado()){
				killDragon();
			}
			else heroi.setAlive(false);
		}
		/*
		//verifica espada
		if(!heroi.getArmado()){
			for(int i=0; i<mapa.length; i++){
				for(int j=0; j<mapa.length; j++){
					if((heroi.getLinha()+1==i && heroi.getColuna()==j) ||
						(heroi.getLinha()-1==i && heroi.getColuna()==j) ||
						(heroi.getLinha()==i && heroi.getColuna()==j+1) ||
						(heroi.getLinha()==i && heroi.getColuna()==j-1)){
						if(!heroi.getArmado()){
							heroi.setArmado(true);
							for(int ii=0; i<mapa.length; ii++){
								for(int jj=0; j<mapa.length; jj++){
									mapa[ii][jj]= ' ';
								}
							}
						}
					}
				}
			}	
		}
		*/
	}
	
	public void DisplayMap(){
		for(int i=0; i< mapa.length; i++){
			for(int j=0; j<mapa[i].length; j++){
				System.out.print(mapa[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public void HeroMovement(String input){
		int hl=heroi.getLinha();
		int hc=heroi.getColuna();
		switch(input){
		case "s":
		case "S":
			if(!(mapa[heroi.getLinha()+1][heroi.getColuna()] == 'X')){
				mapa[heroi.getLinha()][heroi.getColuna()]=' ';
				heroi.setLinha(hl+1);
				mapa[heroi.getLinha()][heroi.getColuna()]='H';
				if(heroi.getArmado()==true){
					mapa[heroi.getLinha()][heroi.getColuna()]='A';
				}
			}
			break;
		case "w":
		case "W":
			if(!(mapa[heroi.getLinha()-1][heroi.getColuna()] == 'X')){
				mapa[heroi.getLinha()][heroi.getColuna()]=' ';
				heroi.setLinha(hl-1);
				mapa[heroi.getLinha()][heroi.getColuna()]='H';
				if(heroi.getArmado()==true){
					mapa[heroi.getLinha()][heroi.getColuna()]='A';
				}
			}
			break;
		case "a":
		case "A":
			if(!(mapa[heroi.getLinha()][heroi.getColuna()-1] == 'X')){
				mapa[heroi.getLinha()][heroi.getColuna()]=' ';
				heroi.setColuna(hc-1);
				mapa[heroi.getLinha()][heroi.getColuna()]='H';
				if(heroi.getArmado()==true){
					mapa[heroi.getLinha()][heroi.getColuna()]='A';
				}
			}
			break;
		case "d":
		case "D":
			if(!(mapa[heroi.getLinha()][heroi.getColuna()+1] == 'X')){
				mapa[heroi.getLinha()][heroi.getColuna()]=' ';
				heroi.setColuna(hc+1);
				mapa[heroi.getLinha()][heroi.getColuna()]='H';
				if(heroi.getArmado()==true){
					mapa[heroi.getLinha()][heroi.getColuna()]='A';
				}
			}
			break;
		}
	}
	

	public void DragonMovement(){
		int drl=dragon.getLinha();
		int drc=dragon.getColuna();
		int move = (int)(Math.random() * 4);
		switch(move){
		case 0:
			if(!(mapa[dragon.getLinha()+1][dragon.getColuna()] == 'X')){
				mapa[dragon.getLinha()][dragon.getColuna()]=' ';
				dragon.setLinha(drl+1);
				mapa[dragon.getLinha()][dragon.getColuna()]='D';
				
			}
			break;
		case 1:
			if(!(mapa[dragon.getLinha()-1][dragon.getColuna()] == 'X')){
				mapa[dragon.getLinha()][dragon.getColuna()]=' ';
				dragon.setLinha(drl-1);
				mapa[dragon.getLinha()][dragon.getColuna()]='D';
			}
			break;
		case 2:
			if(!(mapa[dragon.getLinha()][dragon.getColuna()+1] == 'X')){
				mapa[dragon.getLinha()][dragon.getColuna()]=' ';
				dragon.setLinha(drc+1);
				mapa[dragon.getLinha()][dragon.getColuna()]='D';
			}
			break;
		case 3:
			if(!(mapa[dragon.getLinha()][dragon.getColuna()-1] == 'X')){
				mapa[dragon.getLinha()][dragon.getColuna()]=' ';
				dragon.setLinha(drc-1);
				mapa[dragon.getLinha()][dragon.getColuna()]='D';
			}
			break;
		}
	}
	
	public static void main(String[] args){
		Mapa map = new Mapa();
		map.drawInitialMap();
		map.drawDragon();
		map.drawHero();
		map.drawSword();		
		do{	
			map.DisplayMap();
			
			String userInput;
			Scanner scan = new Scanner (System.in);
			userInput = scan.nextLine();  
			map.HeroMovement(userInput);
			map.EquipHero();
			map.DragonMovement();
//			map.CheckPositions();
						
		}while((!map.EndGame()) && (map.heroi.getAlive()));
	
	}
}