package maze.logic;

public class Labirinth{
	
	char[][]labirinth;
	
	public Labirinth(){
		this.labirinth=this.Default_maze();
	}
	
	public char[][] Default_maze(){
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
		int size1, size2;
		size1=size; size2=size;
		this.labirinth = new char[size1][size2];
		Elements path = new Elements();
		
		//exterior Wall
		for(int i=0; i<size1-1; i++){
			labirinth[i][0]='X';
			labirinth[i][size-1]='X';
		}
		for(int i=0; i<size1-1; i++){
			labirinth[0][i]='X';
			labirinth[size-1][i]='X';
		}		

		//fills with Walls
		for(int i=1; i<size-2; i++){
			for(int j=1; j<size-2; j++){
				labirinth[i][j]='X';
			}
		}
		
		//places Exit
		int direction= (int)(Math.random() * 2); //0->horizontal    1->vertical
		int exit1= (int)(Math.random() * 2);
		int exit2= (int)(Math.random() * size+1);
		if(direction==0){
			if(exit1==0){
				labirinth[0][exit2]='S';
				labirinth[1][exit2]=' ';
				path.set_x(1);
				path.set_y(exit2);
			}else{
				labirinth[size-1][exit2]='S';
				labirinth[size-2][exit2]=' ';
				path.set_x(size-2);
				path.set_y(exit2);
			}
		}
		else{
			if(exit1==0){
				labirinth[exit2][0]='S';
				labirinth[exit2][1]=' ';
				path.set_x(exit2);
				path.set_y(1);
			}else{
				labirinth[exit2][size-1]='S';
				labirinth[exit2][size-2]=' ';
				path.set_x(exit2);
				path.set_y(size-2);
			}
		}
		
		
		//builds path
	//	while(parede(size)==true){
			int move= (int)(Math.random() * 6);
			for(int i=1; i<size-4; i++){
				for(int j=1; j<size-4; j++){
					if(labirinth[i][j]=='X' && labirinth[i+1][j]=='X' && labirinth[i+2][j]=='X' && labirinth[i][j+1]=='X' && 
							labirinth[i][j+2]=='X' && labirinth[i+1][j+1]=='X' && labirinth[i+2][j+1]=='X' && labirinth[i+1][j+2]=='X' && 
							labirinth[i+2][j+2]=='X'){
						switch(move){
						//horizontal
						case 0:
							labirinth[i+1][j+1]=' ';
							int a=0;
							while(labirinth[i+a][j+1] != ' ' && i+a<size-1){
								labirinth[i+a][j+1]=' ';
								a++;
							}
							break;
						//vertical
						case 1:
							labirinth[i+1][j+1]=' ';
							a=0;
							while(labirinth[i+1][j+a] != ' ' && j+a<size-1){
								labirinth[i+1][j+a]=' ';
								a++;
							}
							break;
						// |_
						case 2:
							labirinth[i+1][j+1]=' ';
							a=0; int b=0;
							while(labirinth[i+a][j+1] != ' ' && i+a<size-1){
								labirinth[i+a][j+1]=' ';
								a++;
							}
							while(labirinth[i+1][j+b] != ' ' && j+b<size-1){
								labirinth[i+1][j+b]=' ';
								b++;
							}
							break;
						// case _|
						case 3:
							labirinth[i+1][j+1]=' ';
							a=0;b=0;
							while(labirinth[i-a][j+1] != ' ' && i-a>1){
								labirinth[i-a][j+1]=' ';
								a++;
							}
							while(labirinth[i+1][j+b] != ' ' && j+b<size-1){
								labirinth[i+1][j+b]=' ';
								b++;
							}
							break;
							// -|
						case 4:
							labirinth[i+1][j+1]=' ';
							a=0;b=0;
							while(labirinth[i-a][j+1] != ' ' && i-a>1){
								labirinth[i-a][j+1]=' ';
								a++;
							}
							while(labirinth[i+1][j-b] != ' ' && j+b>1){
								labirinth[i+1][j-b]=' ';
								b++;
							}
							break;
						case 5:
							labirinth[i+1][j+1]=' ';
							a=0;b=0;
							while(labirinth[i+a][j+1] != ' ' && i+a<size-1){
								labirinth[i+a][j+1]=' ';
								a++;
							}
							while(labirinth[i+1][j-b] != ' ' && j+b>1){
								labirinth[i+1][j-b]=' ';
								b++;
							}
							break;				
						}
					}	
				}
			}
	//	}
		
	//	while(livre(size)==true){
			move= (int)(Math.random() * 4);
			for(int i=1; i<size-3; i++){
				for(int j=1; j<size-3; j++){
					if(labirinth[i][j]==' ' && labirinth[i+1][j]==' ' && labirinth[i][j+1]==' ' && labirinth[i+1][j+1]==' '){
						switch(move){
						case 0:
							labirinth[i][j]='X';
							break;
						case 1:
							labirinth[i+1][j]='X';
							break;
						case 2:
							labirinth[i][j+1]='X';
							break;
						case 3:
							labirinth[i+1][j+1]='X';
							break;
						}
					}	
				}
			}
	//	}
			return labirinth;
	}
/*
	//checks if there is any 3x3 block with wall
	boolean parede(int size){
		boolean res = false;
		for(int i=1; i<size-4; i++){
			for(int j=1; j<size-4; j++){
				if(labirinth[i][j]=='X' && labirinth[i+1][j]=='X' && labirinth[i+2][j]=='X' && labirinth[i][j+1]=='X' && 
						labirinth[i][j+2]=='X' && labirinth[i+1][j+1]=='X' && labirinth[i+2][j+1]=='X' && labirinth[i+1][j+2]=='X' && 
						labirinth[i+2][j+2]=='X'){
					res = true;
					break;
				}	
			}
		}
		return res;
	}
	
	////checks if there is any 2x2 block without anything
	boolean livre(int size){
		boolean res = false;
		for(int i=1; i<size-3; i++){
			for(int j=1; j<size-3; j++){
				if(labirinth[i][j]==' ' && labirinth[i+1][j]==' ' && labirinth[i][j+1]==' ' && labirinth[i+1][j+1]==' '){
					res = true;
					break;
				}	
			}
		}
		return res;
	}*/
	
}