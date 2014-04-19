package maze.test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import maze.logic.Dragon;
import maze.logic.Eagle;
import maze.logic.Elements;
import maze.logic.Game;
import maze.logic.Hero;
import maze.logic.Labyrinth;
import maze.logic.Sword;

public class eagle_tests {

	Labyrinth lab = new Labyrinth();
	Hero heroi = new Hero(lab);
	Sword espada = new Sword(lab);
	Dragon d = new Dragon(lab);
	Eagle bird = new Eagle(lab, heroi);
	Vector<Dragon> drag = new Vector<Dragon> ();
	Elements exit = new Elements();
	Game g = new Game(lab, espada, heroi, exit, drag);

	@Test
	public void test_ShortestPath(){
		heroi.set_x(1);
		heroi.set_y(1);
		espada.set_x(3);
		espada.set_y(4);
		bird.set_x(heroi.get_x());
		bird.set_y(heroi.get_y());
		heroi.movement("e", bird);
		assertEquals(true, bird.get_alive());
		assertEquals(true, bird.getTravelling());
		int numb_movs=0;
		while(bird.get_x()!=espada.get_x() || bird.get_y()!=espada.get_y()){
			bird.Movement(espada, heroi);
			numb_movs++;
		}
		assertEquals(3, numb_movs);
	}

	@Test
	public void test_MoveSword(){
		heroi.set_x(1);
		heroi.set_y(1);
		heroi.setArmado(false);
		espada.set_x(3);
		espada.set_y(4);
		heroi.movement("e", bird);
		bird.send_eagle();
		bird.set_x(espada.get_x());
		bird.set_y(espada.get_y());
		bird.setHas_Sword(true);
		bird.setInitialX(heroi.get_x());
		bird.setInitialY(heroi.get_y());
		g.CheckPositions();
		
		assertEquals(true, bird.getTravelling());
		assertEquals(true, bird.getHas_sword());

		while(bird.get_x()!=bird.getInitialX() || bird.get_y()!=bird.getInitialY()){
			bird.return_movement(espada, heroi);
			g.CheckPositions();
		}
		
		assertEquals(heroi.get_x(), espada.get_x());
		assertEquals(heroi.get_y(), espada.get_y());
	}
}
