package maze.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import maze.logic.Dragon;
import maze.logic.Eagle;
import maze.logic.Game;
import maze.logic.Hero;
import maze.logic.Labyrinth;

public class GameBoard extends JPanel implements ActionListener{

	private Timer timer;
	private Labyrinth M;
	private char[][] Board = M.getLabyrinth();
	private Hero H;
	private Vector<Dragon> D;
	private int keyCode;
	private JFrame jf;
	
	GameBoard GB = this;
	
	public GameBoard() {
		
		Game A = new Game();
		M = A.getLab();
		H = A.getHeroi();
		D = A.getDrag();
		
		Board = A.getLab().getLabyrinth();
		addKeyListener(new AListener());
		setFocusable(true);
		timer = new Timer(100, this);
		timer.start();
	}
	
	public static BufferedImage resizeImage(Image image, int width, int height) {
		
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        
        return bufferedImage;
    }
	
	public void paint(Graphics Gr) {
		
		super.paint(Gr);
		Map P = new Map();
		String direction = D.getDirectionMoved();
		
		for (int i = 0; i < Board.length; i++) {
			for (int j = 0; j < Board.length; j++) {
				
				char boardSymbol =  M.getLabyrinth()[i][j];
				
				if (boardSymbol == 'X')
					Gr.drawImage(P.getWall(), j * 64, i * 64, null);
				else if (boardSymbol == 'E')
					Gr.drawImage(P.getSword(), j * 64, i * 64, null);
				else if (boardSymbol == 'D' || boardSymbol == 'F') 
				{
					if (direction == "up") Gr.drawImage(P.getDragonBack(), j * 64, i * 64, null);
					else if (direction == "left") Gr.drawImage(P.getDragonLeft(), j * 64, i * 64, null);
					else if (direction == "right") Gr.drawImage(P.getDragonRight(), j * 64, i * 64, null);
					else Gr.drawImage(P.getDragon(), j * 64, i * 64, null);	
				}
				else if (boardSymbol == 'Z')
					Gr.drawImage(P.getDragonSleeping(), j * 64, i * 64, null);
				else if (boardSymbol == 'H') 
				{
					if (keyCode == KeyEvent.VK_W) Gr.drawImage(P.getHeroBack(), j * 64, i * 64, null);
					else if (keyCode == KeyEvent.VK_A) Gr.drawImage(P.getHeroLeft(), j * 64, i * 64, null);
					else if (keyCode == KeyEvent.VK_D) Gr.drawImage(P.getHeroRight(), j * 64, i * 64, null);
					else Gr.drawImage(P.getHero(), j * 64, i * 64, null);
				}
				else if (boardSymbol == 'A') 
				{
					if (keyCode == KeyEvent.VK_W) Gr.drawImage(P.getHeroBack(), j * 64, i * 64, null);
					else if (keyCode == KeyEvent.VK_A) Gr.drawImage(P.getHeroLeftSword(), j * 64, i * 64, null);
					else if (keyCode == KeyEvent.VK_D) Gr.drawImage(P.getHeroRightSword(), j * 64, i * 64, null);
					else Gr.drawImage(P.getHeroFrontSword(), j * 64, i * 64, null);
				}
				else Gr.drawImage(P.getGrass(), j * 64, i * 64, null);
			}
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
	
	public void setKeyCode(int code) {
		keyCode = code;
	}
	
	
	public class AListener extends KeyAdapter {
		
		public void GameBoardOptions() {
			
			JDialog dial = new JDialog();
			JLabel label = new JLabel();
			dial.setVisible(true);
			dial.setLocationRelativeTo(null);
			dial.setSize(162,150);
			dial.setResizable(false);
			
			label.setVisible(true);
			label.setSize(170,250);
			dial.add(label);
			
			JButton saveGame = new JButton();
			JButton mainMenu = new JButton();
			
			saveGame.setVisible(true);
			saveGame.setText("Save Game");
			saveGame.setBounds(18, 20, 120, 30);
			
			mainMenu.setVisible(true);
			mainMenu.setText("Back to menu");
			mainMenu.setBounds(18, 70, 120, 30);
			
			//TO BE DEVELOPED
			saveGame.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					System.out.println("Yet to be developed.");
				}
			});
			
			//TO BE DEVELOPED
			mainMenu.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					System.out.println("Yet to be developed.");
				}
			});
		
			label.add(saveGame);
			label.add(mainMenu);
		}
		
		public void handleGame(String direction) {
			
			H.movement(M, direction);
			D.movement(M);
			
			for(int i=0; i<D.size(); i++){ // Este ciclo verifica isto para todos os dragoes
				if (H.get_x() == D.get(i).get_x() && H.get_y() == D.get(i).get_y()) {
					if (H.getArmado()) D.get(i).killDragon();
					else H.set_alive(false);
				}
			}
			
			H.handleDragon(M, D);
			
			if (!H.get_alive()) {System.out.println("You're dead.");}
			if (A.EndGame()) System.out.println("Game's over"); //a minha funçao que verifica se o jogo acabou esta na classe Game
		}
		
		public void keyPressed(KeyEvent E) {
			
			int keyCode = E.getKeyCode();
			
			if (keyCode == KeyEvent.VK_W) {
				handleGame("up");
			}
			if (keyCode == KeyEvent.VK_A) {
				handleGame("left");
			}
			if (keyCode == KeyEvent.VK_S) {
				handleGame("down");
			}
			if (keyCode == KeyEvent.VK_D) {
				handleGame("right");
			}
			if (keyCode == KeyEvent.VK_ESCAPE) {
				GameBoardOptions();
			}
			GB.setKeyCode(keyCode);
			repaint();
		}
	}
}
