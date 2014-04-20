package maze.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface extends JFrame{

	private JFrame menu; //For the main menu
	private JFrame gameBoard; //For the maze itself
	
	public Interface() {
		
		gameBoard = this;
		menu = new JFrame();
		menu.setTitle("Dragon Slayer");
		 
		try {
			menu.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\Guilherme\\Desktop\\images\\dragonWallpaper.jpg")))));
			} 
		catch (IOException E) {
			System.out.println("Error loading the background.");
			}
		 
		 menu.setSize(646,669);
		 menu.setResizable(false);
		 menu.setLocationRelativeTo(null);
		 menu.setVisible(true);
		 menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 mainMenuButtons();

	}
	
	public void mazeInterface() {
		
		this.setTitle("Dragon Slayer");
		this.add(new GameBoard());
		this.setSize(646,669);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void mainMenuButtons() {
		
		JButton newGame = new JButton();
		JButton customGame = new JButton();
		JButton createGame = new JButton();
		JButton loadGame = new JButton();
		JButton instructions = new JButton();
		
		//new game
		newGame.setText("New Game");
		newGame.setSize(100,35);
		newGame.setBounds(420, 150 , 120, 35);
		newGame.setBackground(Color.darkGray);
		newGame.setForeground(Color.white);
		newGame.setVisible(true); 
		menu.add(newGame);
		
		//custom game
		customGame.setText("Custom Game");
		customGame.setSize(100,35);
		customGame.setBounds(420, 200 , 120, 35);
		customGame.setBackground(Color.darkGray);
		customGame.setForeground(Color.white);
		customGame.setVisible(true);
		menu.add(customGame);
		
		//create game
		createGame.setText("Create Game");
		createGame.setSize(100,35);
		createGame.setBounds(420, 250 , 120, 35);
		createGame.setBackground(Color.darkGray);
		createGame.setForeground(Color.white);
		createGame.setVisible(true);
		menu.add(createGame);
		
		//load game
		loadGame.setText("Load Game"); 
		loadGame.setSize(100,35);
		loadGame.setBounds(420, 300 , 120, 35); 
		loadGame.setBackground(Color.darkGray);
		loadGame.setForeground(Color.white);
		loadGame.setVisible(true);
		menu.add(loadGame);
		
		//Instructions
		instructions.setText("Instructions");
		instructions.setSize(100,35); 
		instructions.setBounds(420, 350 , 120, 35); 
		instructions.setBackground(Color.darkGray);
		instructions.setForeground(Color.white); 
		instructions.setVisible(true);
		menu.add(instructions); 
		
		
		newGame.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent E) {
				menu.dispose();
				mazeInterface();
			}
		});
	
		customGameMenu(customGame);
		createGameMenu(createGame);
		loadGameMenu(loadGame);
		instructionsMenu(instructions);
	}

	public void customGameMenu(JButton customGame) {
		
		customGame.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				//Dialog menu
				JDialog dial = new JDialog();
				dial.setSize(300,400);
				dial.setResizable(false);
				dial.getContentPane().setBackground(Color.darkGray);
				dial.setVisible(true);
				dial.setLocationRelativeTo(null);
				
				//Dimensions (Width/height)
				JTextField dimensionsText = new JTextField();
				dimensionsText.setBounds(200, 70, 50, 25);
				dimensionsText.setVisible(true);
				dial.add(dimensionsText);
				
				JPanel J = new JPanel();
				J.setBounds(30, 70, 150, 25);
				J.setVisible(true);
				J.setBackground(Color.darkGray);
				
				JLabel L = new JLabel();
				L.setSize(150,25);
				L.setVisible(true);
				L.setText("  Dimension (Width/Height)");
				L.setForeground(Color.WHITE);
				
				J.add(L);
				dial.add(J);
				
				//# of dragons
				JTextField dragonsText = new JTextField();
				dragonsText.setBounds(200, 150, 50, 25);
				dragonsText.setVisible(true);
				dial.add(dragonsText);
				
				JPanel J2 = new JPanel();
				J2.setBounds(30, 150, 150, 25);
				J2.setVisible(true);
				J2.setBackground(Color.darkGray);
				
				JLabel L2 = new JLabel();
				L2.setSize(150,25);
				L2.setVisible(true);
				L2.setText("  Number of dragons");
				L2.setForeground(Color.WHITE);
				
				J2.add(L2);
				dial.add(J2);
				
				//Dragon behavior
				JPanel J3 = new JPanel();
				J3.setBounds(30, 230, 150, 25);
				J3.setVisible(true);
				J3.setBackground(Color.darkGray);
				
				JLabel L3 = new JLabel();
				L3.setSize(150,25);
				L3.setVisible(true);
				L3.setText("  Dragon Behavior");
				L3.setForeground(Color.WHITE);
				
				J3.add(L3);
				dial.add(J3);
				
				//Start button
				JButton startGame = new JButton();
				
				startGame.setText("Start Game");
				startGame.setBounds(90, 320, 120, 30);
				startGame.setVisible(true);
				dial.add(startGame);
				
				startGame.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("To be developed.");
					}
				});
			}
		});
	}
	
	public void createGameMenu(JButton createGame) { 
		
		createGame.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("To be developed.");
			}
		});
	}
	
	public void loadGameMenu(JButton loadGame) { 
		
		loadGame.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("To be developed.");
			}
		}); 
	}
	
	public void instructionsMenu(JButton instructions) { 
		
		instructions.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("To be developed.");
			}
		});
	}
}
