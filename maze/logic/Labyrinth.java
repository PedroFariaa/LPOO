package maze.logic;

import java.util.Random;
import java.util.Stack;
/**
 * Labyrinth Class
 * 
 * @author Pedro Faria
 * @author Guilherme Routar
 */
public class Labyrinth{

	private char[][]labyrinth;
	private int dim;

	/**
	 * Get Labyrinth's parameter labyrinth
	 * 
	 * @return
	 * 		Labyrinth's parameter labyrinth
	 */
	public char[][] getLabyrinth() {
		return labyrinth;
	}

	/**
	 * Set Labyrinth's parameter labyrinth
	 * 
	 * @param alive
	 * 			new Labyrinth's parameter labyrinth
	 */
	public void setLabyrinth(char[][] labyrinth) {
		this.labyrinth = labyrinth;
	}

	/**
	 * Constructs and initializes Labyrinth
	 */
	public Labyrinth(){
		this.setLabyrinth(this.Random_maze(17));
	}
	/**
	 * Constructs and initializes a Default Labyrinth
	 * 
	 * @return
	 * 		the Default Labyrinth
	 */
	public char[][] Default_maze(){
		setDim(10);
		char[][] labirinth = {
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','S'},
				{'X',' ',' ',' ',' ',' ',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};
		return labirinth;
	}

	/**
	 * Constructs and initializes a Random Labyrinth with a defined size
	 * 
	 * @param size
	 * 			size of the Random Labyrinth
	 * 
	 * @return
	 * 			the Random Labyrinth
	 */
	public char[][] Random_maze(int size){
		setDim(size);
		this.setLabyrinth(new char[size][size]);
		boolean[][] visitedCells = new boolean[size-2][size-2];


		// filling labyrinth with X
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i % 2 != 0 && j % 2 != 0)
					getLabyrinth()[i][j] = ' ';
				else
					getLabyrinth()[i][j] = 'X';
			}
		}

		// generating exit
		int exitX, exitY;
		int direction= (int)(Math.random() * 2); //0->horizontal    1->vertical
		int exit1= (int)(Math.random() * 2);
		int exit2= (int)(Math.random() * size-2);
		if(direction==0){
			if(exit1==0){
				getLabyrinth()[0][exit2+1]='S';
				getLabyrinth()[1][exit2+1]=' ';
				exitX = 0;
				exitY = exit2+1;
			}else{
				getLabyrinth()[size-1][exit2+1]='S';
				getLabyrinth()[size-2][exit2+1]=' ';
				exitX = size-1;
				exitY = exit2+1;
			}
		}
		else{
			if(exit1==0){
				getLabyrinth()[exit2+1][0]='S';
				getLabyrinth()[exit2+1][1]=' ';
				exitX = exit2+1;
				exitY = 0;
			}else{
				getLabyrinth()[exit2+1][size-1]='S';
				getLabyrinth()[exit2+1][size-2]=' ';
				exitX = size-1;
				exitY = 0;
			}
		}

		// prepare labyrinth generation
		for (int i = 0; i < visitedCells.length; i++)
			for (int j = 0; j < visitedCells[i].length; j++)
				visitedCells[i][j] = false;


		// labyrinth generation
		Cell cellNextToExit = new Cell(exitX, exitY);
		if (exitX == 0)
			cellNextToExit.set_x(cellNextToExit.get_x() + 1);
		else if (exitX == size - 1)
			cellNextToExit.set_x(cellNextToExit.get_x() - 1);
		else if (exitY == 0)
			cellNextToExit.set_y(cellNextToExit.get_y() + 1);
		else
			cellNextToExit.set_y(cellNextToExit.get_y() - 1);

		int guideCellX = (cellNextToExit.get_x() - 1) / 2;
		int guideCellY = (cellNextToExit.get_y() - 1) / 2;
		Cell guideCell = new Cell(guideCellX, guideCellY);

		visitedCells[guideCell.get_y()][guideCell.get_x()] = true;
		Stack<Cell> pathHistory = new Stack<Cell>();
		pathHistory.push(new Cell(guideCell.get_x(), guideCell.get_y()));

		// build path
		while (!pathHistory.empty()) {
			// if all adjacent cells of guideCell had been visited
			if ((guideCell.get_x()+1 >= (size-1)/2 || visitedCells[guideCell.get_y()][guideCell.get_x()+1] == true)
					&& (guideCell.get_x()-1 < 0 || visitedCells[guideCell.get_y()][guideCell.get_x()-1] == true) &&
					(guideCell.get_y()+1 >= (size-1)/2 || visitedCells[guideCell.get_y()+1][guideCell.get_x()] == true)
					&& (guideCell.get_y()-1 < 0 || visitedCells[guideCell.get_y()-1][guideCell.get_x()] == true)) {

				pathHistory.pop();

				if (pathHistory.empty())
					break;
				else
					guideCell = pathHistory.peek();
			}

			// generate a new path
			Random r = new Random();
			switch (r.nextInt(4)) {  // see this after the cases
			// w
			case 0:
				if (guideCell.get_y()-1 >= 0
				&& visitedCells[guideCell.get_y()-1][guideCell.get_x()] == false) {
					getLabyrinth()[guideCell.get_y()*2][guideCell.get_x()*2+1] = ' ';

					guideCell.set_y(guideCell.get_y()-1);
					pathHistory.push(new Cell(guideCell.get_x(), guideCell.get_y()));

					visitedCells[guideCell.get_y()][guideCell.get_x()] = true;
				}
				break;
				// d
			case 1:
				if (guideCell.get_x()+1 < (size-1)/2 && visitedCells[guideCell.get_y()][guideCell.get_x()+1] == false){
					getLabyrinth()[guideCell.get_y()*2+1][(guideCell.get_x()+1)*2] = ' ';

					guideCell.set_x(guideCell.get_x()+1);
					pathHistory.push(new Cell(guideCell.get_x(), guideCell.get_y()));

					visitedCells[guideCell.get_y()][guideCell.get_x()] = true;
				}
				break;
				// s
			case 2:
				if (guideCell.get_y()+1 < (size-1)/2 && visitedCells[guideCell.get_y()+1][guideCell.get_x()] == false){
					getLabyrinth()[(guideCell.get_y()+1)*2][guideCell.get_x()*2+1] = ' ';

					guideCell.set_y(guideCell.get_y()+1);
					pathHistory.push(new Cell(guideCell.get_x(), guideCell.get_y()));

					visitedCells[guideCell.get_y()][guideCell.get_x()] = true;
				}
				break;
				// a
			case 3:
				if (guideCell.get_x()-1 >= 0 && visitedCells[guideCell.get_y()][guideCell.get_x()-1] == false){
					getLabyrinth()[guideCell.get_y()*2+1][guideCell.get_x()*2] = ' ';

					guideCell.set_x(guideCell.get_x()-1);
					pathHistory.push(new Cell(guideCell.get_x(), guideCell.get_y()));

					visitedCells[guideCell.get_y()][guideCell.get_x()] = true;
				}
				break;
			}
		}

		return getLabyrinth();
	}

	/**
	 * Get Labyrinth's parameter dim
	 * 
	 * @return
	 * 		Labyrinth's parameter dim
	 */
	public int getDim() {
		return dim;
	}

	/**
	 * Set Labyrinth's parameter dim
	 * 
	 * @param alive
	 * 			new Labyrinth's parameter dim
	 */
	public void setDim(int dim) {
		this.dim = dim;
	}


}