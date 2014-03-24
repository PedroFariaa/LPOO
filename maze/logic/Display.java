package maze.logic;

public class Display{
	
	public void DisplayMap(Labirinth lab, Hero h, Dragon drag, Sword s, Eagle e){
		DrawAllElements(lab, h, drag, s, e);
		
		for(int i=0; i< lab.labirinth.length; i++){
			for(int j=0; j<lab.labirinth[i].length; j++){
				System.out.print(lab.labirinth[i][j]+" ");
			}
			System.out.println();
		}
	}

	private void DrawAllElements(Labirinth lab, Hero h, Dragon drag, Sword s, Eagle e) {
		if(h.get_alive()){
			if(h.getArmado()){
				lab.labirinth[h.get_x()][h.get_y()] = 'A';
			}else{
				lab.labirinth[h.get_x()][h.get_y()] = 'H';
			}
		}
		if(drag.get_alive() && !s.get_equiped()){
			if(drag.get_x()==s.get_x() && drag.get_y()==s.get_y()){
				lab.labirinth[drag.get_x()][drag.get_y()] = 'F';
			}
		}else if(drag.get_alive()){
			
		}else if(!s.get_equiped()){
			lab.labirinth[s.get_x()][s.get_y()] = 'E';
		}
		if(e.get_alive() && e.getTravelling() && e.get_has_sword()==false){
			lab.labirinth[e.get_x()][e.get_y()] = 'B';
		}
	}	
}