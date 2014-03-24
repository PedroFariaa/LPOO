package maze.logic;

public class Display{
	
	public void DisplayMap(Labirinth lab, Hero h, Dragon d, Sword s){
		DrawAllElements(lab, h, d, s);
		
		for(int i=0; i< lab.labirinth.length; i++){
			for(int j=0; j<lab.labirinth[i].length; j++){
				System.out.print(lab.labirinth[i][j]+" ");
			}
			System.out.println();
		}
	}

	private void DrawAllElements(Labirinth lab, Hero h, Dragon d, Sword s) {
		if(h.get_alive()){
			if(h.getArmado()){
				lab.labirinth[h.get_x()][h.get_y()] = 'A';
			}else{
				lab.labirinth[h.get_x()][h.get_y()] = 'H';
			}
		}
		if(d.get_alive() && !s.get_equiped()){
			if(d.get_x()==s.get_x() && d.get_y()==s.get_y()){
				lab.labirinth[d.get_x()][d.get_y()] = 'F';
			}
		}else if(d.get_alive()){
			
		}
		
	}
	
}