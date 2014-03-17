package maze.logic;

public class Display{
	
	public void DisplayMap(Labirinth lab){
		for(int i=0; i< lab.labirinth.length; i++){
			for(int j=0; j<lab.labirinth[i].length; j++){
				System.out.print(lab.labirinth[i][j]+" ");
			}
			System.out.println();
		}
	}
	
}