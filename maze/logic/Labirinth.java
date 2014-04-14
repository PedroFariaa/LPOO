package maze.logic;

import java.util.Random;
import java.util.Stack;

public class Labirinth{

	char[][]labirinth;
	int dim;

	public Labirinth(){
		this.labirinth=this.Random_maze(15);
	}

	public char[][] Default_maze(){
		dim = 10;
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

	public char[][] Random_maze(int size){
		dim=size;
		this.labirinth = new char[size][size];
		boolean[][] visitedCells = new boolean[size-2][size-2];


		// filling labyrinth with X
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i % 2 != 0 && j % 2 != 0)
					labirinth[i][j] = ' ';
				else
					labirinth[i][j] = 'X';
			}
		}

		// generating exit
		int exitX, exitY;
		int direction= (int)(Math.random() * 2); //0->horizontal    1->vertical
		int exit1= (int)(Math.random() * 2);
		int exit2= (int)(Math.random() * size+1);
		if(direction==0){
			if(exit1==0){
				labirinth[0][exit2]='S';
				labirinth[1][exit2]=' ';
				exitX = 0;
				exitY = exit2;
			}else{
				labirinth[size-1][exit2]='S';
				labirinth[size-2][exit2]=' ';
				exitX = size-1;
				exitY = exit2;
			}
		}
		else{
			if(exit1==0){
				labirinth[exit2][0]='S';
				labirinth[exit2][1]=' ';
				exitX = exit2;
				exitY = 0;
			}else{
				labirinth[exit2][size-1]='S';
				labirinth[exit2][size-2]=' ';
				exitX = size-1;
				exitY = 0;
			}
		}

		// preparing matrix for maze generation
		for (int i = 0; i < visitedCells.length; i++)
			for (int j = 0; j < visitedCells[i].length; j++)
				visitedCells[i][j] = false;


		// preparing data for maze generation
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

		// running maze generation algorithm
		while (!pathHistory.empty()) {
			// if all neighbors of guideCell have been visited
			if ((guideCell.get_x()+1 >= (size-1)/2 || visitedCells[guideCell.get_y()][guideCell.get_x()+1] == true)
					&& (guideCell.get_x()-1 < 0 || visitedCells[guideCell.get_y()][guideCell.get_x()-1] == true) &&
					(guideCell.get_y()+1 >= (size-1)/2 || visitedCells[guideCell.get_y()+1][guideCell.get_x()] == true)
					&& (guideCell.get_y()-1 < 0 || visitedCells[guideCell.get_y()-1][guideCell.get_x()] == true)) {

				// pop cell from history
				pathHistory.pop();

				// if path history is empty, terminate algorithm
				if (pathHistory.empty())
					break;
				else
					guideCell = pathHistory.peek();
			}

			// generating a new path
			Random r = new Random();
			switch (r.nextInt(4)) {  // see this after the cases
			// up
			case 0:
				if (guideCell.get_y()-1 >= 0
				&& visitedCells[guideCell.get_y()-1][guideCell.get_x()] == false) {
					// updating maze
					labirinth[guideCell.get_y()*2][guideCell.get_x()*2+1] = ' ';

					// updating guideCell position
					guideCell.set_y(guideCell.get_y()-1);
					pathHistory.push(new Cell(guideCell.get_x(), guideCell.get_y()));

					// marking cell as visited
					visitedCells[guideCell.get_y()][guideCell.get_x()] = true;
				}
				break;
				// right
			case 1:
				if (guideCell.get_x()+1 < (size-1)/2 && visitedCells[guideCell.get_y()][guideCell.get_x()+1] == false){
					// updating maze
					labirinth[guideCell.get_y()*2+1][(guideCell.get_x()+1)*2] = ' ';

					// updating guideCell position
					guideCell.set_x(guideCell.get_x()+1);
					pathHistory.push(new Cell(guideCell.get_x(), guideCell.get_y()));

					// marking cell as visited
					visitedCells[guideCell.get_y()][guideCell.get_x()] = true;
				}
				break;
				// down
			case 2:
				if (guideCell.get_y()+1 < (size-1)/2 && visitedCells[guideCell.get_y()+1][guideCell.get_x()] == false){
					// updating maze
					labirinth[(guideCell.get_y()+1)*2][guideCell.get_x()*2+1] = ' ';

					// updating guideCell position
					guideCell.set_y(guideCell.get_y()+1);
					pathHistory.push(new Cell(guideCell.get_x(), guideCell.get_y()));

					// marking cell as visited
					visitedCells[guideCell.get_y()][guideCell.get_x()] = true;
				}
				break;
				// left
			case 3:
				if (guideCell.get_x()-1 >= 0 && visitedCells[guideCell.get_y()][guideCell.get_x()-1] == false){
					// updating maze
					labirinth[guideCell.get_y()*2+1][guideCell.get_x()*2] = ' ';

					// updating guideCell position
					guideCell.set_x(guideCell.get_x()-1);
					pathHistory.push(new Cell(guideCell.get_x(), guideCell.get_y()));

					// marking cell as visited
					visitedCells[guideCell.get_y()][guideCell.get_x()] = true;
				}
				break;
			}
		}

		return labirinth;
	}


}