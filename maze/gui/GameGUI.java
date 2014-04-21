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
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import maze.cli.Menu;
import maze.logic.Dragon;
import maze.logic.Eagle;
import maze.logic.Game;
import maze.logic.Hero;
import maze.logic.Labyrinth;

public class GameGUI extends JPanel implements ActionListener{

	private Timer timer;
	private Labyrinth M;
	private char[][] Board;
	private Hero H;
	private Vector<Dragon> D;
	private Eagle Ea;
	private int keyCode;
	private JFrame jf;
	private Game A;

	GameGUI GB = this;

	/**
	 * Create the panel.
	 */
	public GameGUI(boolean sleep2, int nDragons) {
		Menu Me = new Menu();
		Game G = new Game(nDragons);

		//G.InitializeDragons(nDragons);
		boolean sleep = Me.SleepingDragon(G);

		A = G;
		M = A.getLab();
		H = A.getHeroi();
		D = A.getDrag();
		Ea = A.getEagle();
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

		requestFocus(true);
		setFocusable(true);
		super.paint(Gr);
		Map P = new Map();
		int altura=this.getHeight()/A.getLab().getDim();
		int largura=this.getWidth()/A.getLab().getDim();
		
		for (int i = 0; i < Board.length; i++) {
			for (int j = 0; j < Board.length; j++) {
				char boardSymbol =  M.getLabyrinth()[i][j];
				if (boardSymbol == 'X')
					Gr.drawImage(P.getWall(), j * largura, i * altura, largura, altura, Color.black, null);
				else if (boardSymbol == 'E')
					Gr.drawImage(P.getSword(), j * largura, i * altura, largura, altura, Color.black, null);
				else if (boardSymbol == 'D' || boardSymbol == 'F') //Not tested
				{
					Gr.drawImage(P.getDragon(), j * largura, i * altura, largura, altura, Color.black, null);
					/*
					for (int k = 0; k < D.size(); k++) {

						if (i == D.get(k).get_x() && j == D.get(k).get_y()) { //Determinates which dragon is and
																			  //draws according to the direction taken by it	
							String direction = D.get(i).getDirectionTaken();
							if (direction == "up") Gr.drawImage(P.getDragonBack(), j * 64, i * 64, null);
							else if (direction == "left") Gr.drawImage(P.getDragonLeft(), j * 64, i * 64, null);
							else if (direction == "right") Gr.drawImage(P.getDragonRight(), j * 64, i * 64, null);
							else Gr.drawImage(P.getDragon(), j * 64, i * 64, null);
						}
					}*/
				}
				else if (boardSymbol == 'Z')
					Gr.drawImage(P.getDragonSleeping(), j * largura, i * altura, largura, altura, Color.black, null);
				else if (boardSymbol == 'H') 
				{
					if (keyCode == KeyEvent.VK_W) Gr.drawImage(P.getHeroBack(), j * largura, i * altura, largura, altura, Color.black, null);
					else if (keyCode == KeyEvent.VK_A) Gr.drawImage(P.getHeroLeft(), j * largura, i * altura, largura, altura, Color.black, null);
					else if (keyCode == KeyEvent.VK_D) Gr.drawImage(P.getHeroRight(), j * largura, i * altura, largura, altura, Color.black, null);
					else Gr.drawImage(P.getHero(), j * largura, i * altura, largura, altura, Color.black, null);
				}
				else if (boardSymbol == 'A') 
				{
					if (keyCode == KeyEvent.VK_W) Gr.drawImage(P.getHeroBack(), j * largura, i * altura, largura, altura, Color.black, null);
					else if (keyCode == KeyEvent.VK_A) Gr.drawImage(P.getHeroLeftSword(), j * largura, i * altura, largura, altura, Color.black, null);
					else if (keyCode == KeyEvent.VK_D) Gr.drawImage(P.getHeroRightSword(), j * largura, i * altura, largura, altura, Color.black, null);
					else Gr.drawImage(P.getHeroFrontSword(), j * largura, i * altura, largura, altura, Color.black, null);
				}
				else Gr.drawImage(P.getGrass(), j * largura, i * altura, largura, altura, Color.black, null);
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

		public void GameGUIOptions() {

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
			A.PlayGame(direction);
		}

		public void keyPressed(KeyEvent E) {

			int keyCode = E.getKeyCode();

			if (keyCode == KeyEvent.VK_W) {
				handleGame("w");
			}
			if (keyCode == KeyEvent.VK_A) {
				handleGame("a");
			}
			if (keyCode == KeyEvent.VK_S) {
				handleGame("s");
			}
			if (keyCode == KeyEvent.VK_D) {
				handleGame("d");
			}
			if (keyCode == KeyEvent.VK_E) {
				handleGame("e");
			}
			if (keyCode == KeyEvent.VK_ESCAPE) {
				GameGUIOptions();
			}
			repaint();
		}
	}
}

