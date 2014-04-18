package maze.logic;

import java.util.Scanner;
import java.util.Vector;

public class Game{


	Labyrinth lab = new Labyrinth();
	private Vector<Dragon> drag = new Vector<Dragon>();
	Sword espada = new Sword(lab);
	Hero heroi = new Hero(lab);
	Eagle bird = new Eagle(lab, heroi);
	Display dis = new Display();
	Elements exit = new Elements();
	boolean FoundExit=false;

	public Game(int n){
		this.lab = new Labyrinth(n);
	}

	public Game(Labyrinth lab, Sword espada, Hero heroi, Elements exit, Vector<Dragon> drag){
		this.lab=lab;
		this.espada=espada;
		this.heroi=heroi;
		this.exit=exit;
		this.dis = new Display();
		this.drag = drag;
	}

	public boolean EndGame(){
		boolean end=false;
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

	public void CheckPositions(){
		//verifica dragao
		for(int i=0; i<drag.size(); i++){
			if(!drag.get(i).get_sleeping()){
				if((lab.getLabyrinth()[heroi.get_x()+1][heroi.get_y()] == lab.getLabyrinth()[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.getLabyrinth()[heroi.get_x()-1][heroi.get_y()] == lab.getLabyrinth()[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.getLabyrinth()[heroi.get_x()][heroi.get_y()+1] == lab.getLabyrinth()[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.getLabyrinth()[heroi.get_x()][heroi.get_y()-1] == lab.getLabyrinth()[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.getLabyrinth()[heroi.get_x()][heroi.get_y()] == lab.getLabyrinth()[drag.get(i).get_x()][drag.get(i).get_y()])){
					if(heroi.getArmado()){
						drag.get(i).killDragon();
					}else{
						heroi.set_alive(false);
						System.out.println("You are Dead !");
					}
				}
			}else{
				if((lab.getLabyrinth()[heroi.get_x()+1][heroi.get_y()] == lab.getLabyrinth()[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.getLabyrinth()[heroi.get_x()-1][heroi.get_y()] == lab.getLabyrinth()[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.getLabyrinth()[heroi.get_x()][heroi.get_y()+1] == lab.getLabyrinth()[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.getLabyrinth()[heroi.get_x()][heroi.get_y()-1] == lab.getLabyrinth()[drag.get(i).get_x()][drag.get(i).get_y()]) || (lab.getLabyrinth()[heroi.get_x()][heroi.get_y()] == lab.getLabyrinth()[drag.get(i).get_x()][drag.get(i).get_y()])){
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
		if(!heroi.getArmado()){
			if(bird.get_x()==espada.get_x() && bird.get_y()==espada.get_y()){
				bird.setTravelling(true);
			}
		}
		if(bird.get_alive()){
			for(int i=0; i<drag.size(); i++){
				if(!drag.get(i).get_sleeping() && drag.get(i).get_x()==bird.get_x() && drag.get(i).get_y()==bird.get_y()){
					bird.KillsEagle(espada);
				}
			}
		}

	}

	public Game(){
		espada.ShowSword(lab);
		bird.ShowEagle(lab);
		heroi.ShowHero(lab);
		for(int i=0; i<drag.size(); i++){
			drag.get(i).ShowDragon(lab, espada);
		}
	}


	public void PlayGame(boolean sleepingDragon){
		while(EndGame() == false){
			dis.DisplayMap(lab, heroi, drag, espada, bird);
			String userInput;
			Scanner scan = new Scanner (System.in);
			userInput = scan.nextLine();
			for(int i=0; i<drag.size(); i++){
				if(sleepingDragon==true){
					drag.get(i).movement_sleep();
					CheckPositions();
				}else{
					drag.get(i).movement();
					CheckPositions();
				}
			}
			CheckPositions();
			heroi.movement(userInput, bird);
			CheckPositions();
			if(bird.getTravelling()){
				bird.Movement(espada);
			}
			CheckPositions();
		}
		while(FoundExit == false){
			dis.DisplayMap(lab, heroi, drag, espada, bird);
			String userInput;
			Scanner scan = new Scanner (System.in);
			userInput = scan.nextLine();
			for(int i=0; i<drag.size(); i++){
				if(sleepingDragon==true){
					drag.get(i).movement_sleep();
					CheckPositions();
				}else{
					drag.get(i).movement();
					CheckPositions();
				}
			}
			CheckPositions();
			heroi.movement(userInput, bird);
			CheckExit();
		}

	}


	private void CheckExit() {
		for(int i=0; i < lab.getLabyrinth().length; i++){
			for(int j=0; j < lab.getLabyrinth().length; j++){
				if(lab.getLabyrinth()[i][j] == 'S'){
					exit.set_x(i);
					exit.set_y(j);
					break;
				}
			}
		}
		if(lab.getLabyrinth()[exit.get_x()][exit.get_y()]=='A'){
			lab.getLabyrinth()[heroi.get_x()][heroi.get_y()] = ' ';
			lab.getLabyrinth()[exit.get_x()][exit.get_y()] = 'A';
			dis.DisplayMap(lab, heroi, drag, espada, bird);
			System.out.println("You are free at least!");
			FoundExit = true;
		}
	}

	public void InitializeDragons(int n){
		for(int i=0; i<n; i++){
			drag.add(new Dragon(lab));
		}
	}


}

