package maze.logic;

import java.util.Scanner;

public class Game{

	Labirinth lab = new Labirinth();
	Dragon drag = new Dragon(lab);
	Sword espada = new Sword(lab);
	Hero heroi = new Hero(lab);
	Eagle bird = new Eagle(lab, heroi);
	Display dis = new Display();
	Elements exit = new Elements();
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
			if(heroi.get_x()==espada.get_x() && heroi.get_y()==espada.get_y()){
				heroi.setArmado();
				espada.set_equiped(true);
			}
		}
		
		//verifica passaro
		
	}
	
	public Game(){
		espada.ShowSword(lab);
		bird.ShowEagle(lab);
		heroi.ShowHero(lab);
		drag.ShowDragon(lab);
	}
	
	
	public void PlayGame(){
		while(EndGame() == false){
			dis.DisplayMap(lab, heroi, drag, espada, bird);
			String userInput;
			Scanner scan = new Scanner (System.in);
			userInput = scan.nextLine();
			drag.movement();
			CheckPositions();
			heroi.movement(userInput, bird);
			CheckPositions();
			if(bird.getTravelling()==true){
				bird.Movement(espada);
				bird.ShowEagle(lab);
			}
			//CheckPositions_eagle();
		}
		while(FoundExit == false){
			dis.DisplayMap(lab, heroi, drag, espada, bird);
			String userInput;
			Scanner scan = new Scanner (System.in);
			userInput = scan.nextLine();
			heroi.movement(userInput, bird);
			CheckExit();
		}
		
	}
	

	private void CheckExit() {
		for(int i=0; i < lab.labirinth.length; i++){
			for(int j=0; j < lab.labirinth.length; j++){
				if(lab.labirinth[i][j] == 'S'){
					exit.set_x(i);
					exit.set_y(j);
					break;
				}
			}
		}
		if(lab.labirinth[exit.get_x()][exit.get_y()]=='A'){
			lab.labirinth[heroi.get_x()][heroi.get_y()] = ' ';
			lab.labirinth[exit.get_x()][exit.get_y()] = 'A';
			dis.DisplayMap(lab, heroi, drag, espada, bird);
			System.out.println("You are at least free!");
			FoundExit = true;
		}
	}	
	
}

	