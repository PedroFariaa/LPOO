package maze.logic;

import java.util.Scanner;
import java.util.Vector;

public class Game{

	
	Labirinth lab = new Labirinth();
	private Vector<Dragon> drag = new Vector<Dragon>();
	Sword espada = new Sword(lab);
	Hero heroi = new Hero(lab);
	Eagle bird = new Eagle(lab, heroi);
	Display dis = new Display();
	Elements exit = new Elements();
	boolean FoundExit=false;
	
	

	public boolean EndGame(){
		boolean end=false;
		/*
		if(drag.get_alive()==false){
			end=true;
		}else{
			if(!heroi.get_alive()){
				end=true;
			}
		}
		return end;
		 */
		for(int i=0; i<drag.size(); i++){
			if(drag.get(i).get_alive()==false){
				end=true;
				break;
			}else{
				if(!heroi.get_alive()){
					end=true;
					break;
				}
			}
		}
		return end;
	}

	public void CheckPositions(){  // ALTERAR FUNÇAO PARA FUNCIONAR COM DRAGONS MULTIPLOS
		//verifica dragao
		for(int i=0; i<drag.size(); i++){
			if(!drag.get(i).get_asleep()){
				if((lab.labirinth[heroi.get_x()+1][heroi.get_y()] == lab.labirinth[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.labirinth[heroi.get_x()-1][heroi.get_y()] == lab.labirinth[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.labirinth[heroi.get_x()][heroi.get_y()+1] == lab.labirinth[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.labirinth[heroi.get_x()][heroi.get_y()-1] == lab.labirinth[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.labirinth[heroi.get_x()][heroi.get_y()] == lab.labirinth[drag.get(i).get_x()][drag.get(i).get_y()])){
					if(heroi.getArmado()){
						drag.get(i).killDragon();
					}else{
						heroi.set_alive(false);
						System.out.println("You are Dead !");
					}
				}
			}else{
				if((lab.labirinth[heroi.get_x()+1][heroi.get_y()] == lab.labirinth[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.labirinth[heroi.get_x()-1][heroi.get_y()] == lab.labirinth[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.labirinth[heroi.get_x()][heroi.get_y()+1] == lab.labirinth[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.labirinth[heroi.get_x()][heroi.get_y()-1] == lab.labirinth[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.labirinth[heroi.get_x()][heroi.get_y()] == lab.labirinth[drag.get(i).get_x()][drag.get(i).get_y()])){
					if(heroi.getArmado()){
						drag.get(i).killDragon();
					}
				}
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
		for(int i=0; i<drag.size(); i++){
			drag.get(i).ShowDragon(lab);
		}
	}


	public void PlayGame(){
		while(EndGame() == false){
			dis.DisplayMap(lab, heroi, drag, espada, bird);
			String userInput;
			Scanner scan = new Scanner (System.in);
			userInput = scan.nextLine();
			for(int i=0; i<drag.size(); i++){
				drag.get(i).movement();
			}
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
			for(int i=0; i<drag.size(); i++){
				drag.get(i).movement();
			}
			CheckPositions();
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
	
	public void InitializeDragons(int n){
		for(int i=0; i<n; i++){
			drag.add(new Dragon(lab));
		}
	}
	

}

