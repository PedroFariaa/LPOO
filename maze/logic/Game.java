package maze.logic;

import java.util.Scanner;

public class Game{

	Labirinth lab = new Labirinth();
	Sword espada = new Sword(lab);
	Hero heroi = new Hero(lab);
	Dragon drag = new Dragon(lab);
	Eagle bird = new Eagle(lab, heroi);
	Display dis = new Display();
	boolean FoundExit=false;
	
	
	public boolean EndGame(){
		boolean end=false;
		if(drag.get_alive()==false){
				end=true;
		}else{
			if(!heroi.get_alive()){
				end=true;
			}
		}
		return end;
	}
	
	public void CheckPositions(){
		//verifica dragao
		if((lab.labirinth[heroi.get_x()+1][heroi.get_y()] == 'D') || (lab.labirinth[heroi.get_x()-1][heroi.get_y()] == 'D') || (lab.labirinth[heroi.get_x()][heroi.get_y()+1] == 'D') || (lab.labirinth[heroi.get_x()][heroi.get_y()-1] == 'D') || (lab.labirinth[heroi.get_x()][heroi.get_y()] == 'D')){
			if(heroi.getArmado()){
				drag.killDragon();
			}else{
				heroi.set_alive(false);
				System.out.println("You are Dead !");
			}
		}
		if((lab.labirinth[heroi.get_x()+1][heroi.get_y()] == 'Z') || (lab.labirinth[heroi.get_x()-1][heroi.get_y()] == 'Z') || (lab.labirinth[heroi.get_x()][heroi.get_y()+1] == 'Z') || (lab.labirinth[heroi.get_x()][heroi.get_y()-1] == 'Z') || (lab.labirinth[heroi.get_x()][heroi.get_y()] == 'Z')){
			if(heroi.getArmado()){
				drag.killDragon();
			}
		}
		
		
		//verifica espada
		if(!heroi.getArmado()){
			if(((lab.labirinth[heroi.get_x()+1][heroi.get_y()] == 'E')) || (lab.labirinth[heroi.get_x()-1][heroi.get_y()] == 'E') || (lab.labirinth[heroi.get_x()][heroi.get_y()+1] == 'E') || (lab.labirinth[heroi.get_x()][heroi.get_y()-1] == 'E')){
				heroi.setArmado();
				espada.set_equiped(true);
			}
		}
		
		//verifica passaro
		
		//verifica se chegou à espada
	}
	
	public static void main(String[] argc){
		Game jogo = new Game();
		jogo.espada.ShowSword(jogo.lab);
		jogo.heroi.ShowHero(jogo.lab);
		jogo.drag.ShowDragon(jogo.lab);
		while(jogo.EndGame() == false){
			jogo.dis.DisplayMap(jogo.lab);
			String userInput;
			Scanner scan = new Scanner (System.in);
			userInput = scan.nextLine();  
			jogo.drag.movement();
			jogo.CheckPositions();
			jogo.heroi.movement(userInput);
			jogo.CheckPositions();
		}
		while(jogo.FoundExit == false){
			jogo.dis.DisplayMap(jogo.lab);
			String userInput;
			Scanner scan = new Scanner (System.in);
			userInput = scan.nextLine();
			jogo.heroi.movement(userInput);
			jogo.CheckExit();
		}
	}

	private void CheckExit() {
		int X_Exit=1, Y_Exit=1;
		for(int i=0; i < lab.labirinth.length; i++){
			for(int j=0; j < lab.labirinth.length; j++){
				if(lab.labirinth[i][j] == 'S'){
					X_Exit=i;
					Y_Exit=j;
					break;
				}
			}
		}
		if(lab.labirinth[X_Exit][Y_Exit]=='A'){
			FoundExit = true;
		}
	}

	
}

	