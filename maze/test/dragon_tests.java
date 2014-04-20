package maze.test;

import static org.junit.Assert.*;

import java.util.Vector;

import maze.logic.Dragon;
import maze.logic.Eagle;
import maze.logic.Elements;
import maze.logic.Game;
import maze.logic.Hero;
import maze.logic.Labyrinth;
import maze.logic.Sword;

import org.junit.Test;
/**
 * Tests complex features of the game related with multiple dragons
 * @author Pedro Faria 
 * @author Guilherme Routar
 * @version 1.0
 */
public class dragon_tests {

	Labyrinth lab = new Labyrinth();
	Hero heroi = new Hero(lab);
	Sword espada = new Sword(lab);
	Dragon d = new Dragon(lab);
	Eagle bird = new Eagle(lab, heroi);
	Vector<Dragon> drag = new Vector<Dragon> ();
	Elements exit = new Elements();
	Game g = new Game(lab, espada, heroi, exit, drag);

	@Test
	public void test_several_dragons(){
		g.InitializeDragons(4);

		assertEquals(4, drag.size());
	}

	@Test
	public void test_dragon_sleeps(){
		g.InitializeDragons(3);
		drag.get(0).set_x(8);
		drag.get(0).set_y(6);
		drag.get(1).set_x(8);
		drag.get(1).set_y(8);
		drag.get(2).set_x(1);
		drag.get(2).set_y(8);

		while(!drag.get(0).get_sleeping() || !drag.get(1).get_sleeping() || !drag.get(2).get_sleeping()){
			for(int i=0; i<3; i++){
				drag.get(i).movement_sleep();
			}
		}
		
		assertEquals(true, ( drag.get(0).get_sleeping()==true || drag.get(1).get_sleeping()==true || drag.get(2).get_sleeping()==true ));
	}
	
	@Test
	public void test_dragon_doesnt_sleep(){
		g.InitializeDragons(3);
		drag.get(0).set_x(8);
		drag.get(0).set_y(6);
		drag.get(1).set_x(8);
		drag.get(1).set_y(8);
		drag.get(2).set_x(1);
		drag.get(2).set_y(8);
		boolean test = true;

		for(int i=0; i<3; i++){
			for(int j=0; j<100; j++){
				drag.get(i).movement();
				if(drag.get(i).get_sleeping()){
					test=false;
					break;
				}
			}
		}
		assertEquals(true, test);
	}

	@Test
	public void test_AwakenDragon_Kills(){
		g.InitializeDragons(2);
		
		heroi.set_x(1);
		heroi.set_y(7);
		drag.get(0).set_x(1);
		drag.get(0).set_y(6);
		drag.get(1).set_x(8);
		drag.get(1).set_y(1);
		
		heroi.setArmado(false);
		drag.get(0).set_sleeping(false);
		drag.get(1).set_sleeping(false);
		
		heroi.set_x(drag.get(0).get_x());
		heroi.set_y(drag.get(0).get_y());
		g.CheckPositions();

		assertEquals(false, heroi.get_alive());
	}
	
	@Test
	public void test_SleepingDragon_doesntKills(){
		g.InitializeDragons(2);
		
		heroi.set_x(1);
		heroi.set_y(7);
		drag.get(0).set_x(1);
		drag.get(0).set_y(6);
		drag.get(1).set_x(8);
		drag.get(1).set_y(1);
		
		heroi.setArmado(false);
		drag.get(0).set_sleeping(true);
		drag.get(1).set_sleeping(true);
		
		heroi.set_x(drag.get(0).get_x());
		heroi.set_y(drag.get(0).get_y());
		g.CheckPositions();

		assertEquals(true, heroi.get_alive());
	}
	
	@Test
	public void test_sleepingAndAwakenDragons_die(){
		g.InitializeDragons(2);
		
		heroi.set_x(1);
		heroi.set_y(7);
		drag.get(0).set_x(1);
		drag.get(0).set_y(6);
		drag.get(1).set_x(8);
		drag.get(1).set_y(1);
		
		heroi.setArmado();
		drag.get(0).set_sleeping(true);
		drag.get(1).set_sleeping(false);
		
		heroi.set_x(drag.get(0).get_x());
		heroi.set_y(drag.get(0).get_y());
		g.CheckPositions();
		
		heroi.set_x(drag.get(1).get_x());
		heroi.set_y(drag.get(1).get_y());
		g.CheckPositions();

		assertEquals(false, (drag.get(0).get_alive() && drag.get(1).get_alive()));
		assertEquals(true, heroi.get_alive());
	}
}
