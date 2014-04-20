package maze.test;

import java.util.Vector;

import maze.logic.*;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 * Tests simple features of the game
 * @author Pedro Faria 
 * @author Guilherme Routar
 * @version 1.0
 */
public class hero_tests {
	Labyrinth lab = new Labyrinth();
	Hero heroi = new Hero(lab);
	Sword espada = new Sword(lab);
	Dragon d = new Dragon(lab);
	Eagle bird = new Eagle(lab, heroi);
	Vector<Dragon> drag = new Vector<Dragon> ();
	Elements exit = new Elements();
	Game g = new Game(lab, espada, heroi, exit, drag);

	@Test
	public void test_MoveToFreeCell(){
		heroi.set_x(3);
		heroi.set_y(1);
		espada.set_x(1);
		espada.set_y(8);
		d.set_x(8);
		d.set_y(8);
				
		heroi.movement("s", bird);
		heroi.movement("s", bird);
		assertEquals(5,heroi.get_x());
		assertEquals(1,heroi.get_y());
		
		heroi.movement("d", bird);
		heroi.movement("d", bird);
		heroi.movement("d", bird);
		assertEquals(5,heroi.get_x());
		assertEquals(4,heroi.get_y());
		
		heroi.movement("w", bird);
		assertEquals(4,heroi.get_x());
		assertEquals(4,heroi.get_y());
		
		heroi.movement("s", bird);
		heroi.movement("a", bird);
		heroi.movement("a", bird);
		assertEquals(5,heroi.get_x());
		assertEquals(2,heroi.get_y());
	}
	
	
	@Test
	public void test_MoveAgainstWall(){
		heroi.set_x(5);
		heroi.set_y(6);
		espada.set_x(1);
		espada.set_y(8);
		d.set_x(8);
		d.set_y(8);
				
		heroi.movement("d", bird);
		assertEquals(5,heroi.get_x());
		assertEquals(6,heroi.get_y());
		
		heroi.movement("a", bird);
		heroi.movement("w", bird);
		assertEquals(5,heroi.get_x());
		assertEquals(5,heroi.get_y());
		
		heroi.movement("s", bird);
		assertEquals(5,heroi.get_x());
		assertEquals(5,heroi.get_y());
		
		heroi.movement("a", bird);
		heroi.movement("w", bird);
		heroi.movement("a", bird);
		assertEquals(4,heroi.get_x());
		assertEquals(4,heroi.get_y());
	}
	
	@Test
	public void test_EquipSword(){
		heroi.set_x(1);
		heroi.set_y(1);
		espada.set_x(1);
		espada.set_y(3);
		d.set_x(8);
		d.set_y(8);
		
		heroi.movement("d", bird);
		heroi.movement("d", bird);
		g.CheckPositions();
		heroi.movement("d", bird);		
		
		assertEquals(true, heroi.getArmado());
		assertEquals(true, espada.get_equipped());
	}
	
	@Test
	public void test_GettingKilled(){
		heroi.set_x(1);
		heroi.set_y(1);
		espada.set_x(8);
		espada.set_y(8);
		g.InitializeDragons(1);
		drag.get(0).set_x(1);
		drag.get(0).set_y(3);
		
		heroi.setArmado(false);
		heroi.movement("d", bird);
		drag.get(0).set_x(1);
		drag.get(0).set_y(3);
		drag.get(0).wake();
		g.CheckPositions();
		heroi.movement("d", bird);
		
		assertEquals(false, heroi.get_alive());
	}
	
	@Test
	public void test_KillDragon(){
		heroi.set_x(1);
		heroi.set_y(1);
		espada.set_x(8);
		espada.set_y(8);
		g.InitializeDragons(1);
		drag.get(0).set_x(1);
		drag.get(0).set_y(3);
		
		espada.set_equipped(true);
		heroi.setArmado(true);
		assertEquals(1, drag.size());
		drag.get(0).wake();
		assertEquals(false, drag.get(0).get_sleeping());
		drag.get(0).set_x(1);
		drag.get(0).set_y(1);
		g.CheckPositions();
		
		assertEquals(true, heroi.get_alive());
		assertEquals(false, drag.get(0).get_alive());
	}
	
	@Test
	public void test_WinGame(){
		heroi.set_x(1);
		heroi.set_y(7);
		espada.set_x(2);
		espada.set_y(8);
		g.InitializeDragons(1);
		drag.get(0).set_x(8);
		drag.get(0).set_y(1);
		
		heroi.movement("d", bird);
		heroi.movement("s", bird);
		g.CheckPositions();
		
		assertEquals(true, heroi.getArmado());
		
		heroi.movement("s", bird);
		drag.get(0).set_x(3);
		drag.get(0).set_y(8);
		g.CheckPositions();
		
		assertEquals(false, drag.get(0).get_alive());
		
		heroi.movement("s", bird);
		heroi.movement("d", bird);
		
		assertEquals(true, g.EndGame());
	}
	
	@Test
	public void CantExit(){
		heroi.set_x(4);
		heroi.set_y(8);
		
		assertEquals(false, heroi.getArmado());
		
		heroi.movement("d", bird);
		
		assertEquals(false, g.EndGame());
	}
	
}