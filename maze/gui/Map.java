package maze.gui;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Map {

	private Image grass, wall, dragon, sword, hero, heroBack, heroLeft, heroRight, heroFrontSword, heroLeftSword,
	heroRightSword, dragonSleeping, dragonLeft, dragonRight, dragonBack;
	
	public Map() {
		
		//Maze content
		ImageIcon img = new ImageIcon("src/maze/gui/images/wall.png");
		wall = img.getImage();
		img = new ImageIcon("src/maze/gui/images/grass.png");
		grass = img.getImage();
		
		//Hero
		img = new ImageIcon("src/maze/gui/images/hero.png");
		hero = img.getImage();
		img = new ImageIcon("src/maze/gui/images/heroBack.png");
		heroBack = img.getImage();
		img = new ImageIcon("src/maze/gui/images/heroLeft.png");
		heroLeft = img.getImage();
		img = new ImageIcon("src/maze/gui/images/heroRight.png");
		heroRight = img.getImage();
		img = new ImageIcon("src/maze/gui/images/sword.png");
		sword = img.getImage();
		img = new ImageIcon("src/maze/gui/images/heroFrontSword.png");
		heroFrontSword = img.getImage();
		img = new ImageIcon("src/maze/gui/images/heroRightSword.png");
		heroRightSword = img.getImage();
		img = new ImageIcon("src/maze/gui/images/heroLeftSword.png");
		heroLeftSword = img.getImage();
		//Missing eagle 
		
		//Dragon
		img = new ImageIcon("src/maze/gui/images/dragon.png");
		dragon = img.getImage();
		img = new ImageIcon("src/maze/gui/images/dragonSleeping.png");
		dragonSleeping = img.getImage();
		img = new ImageIcon("src/maze/gui/images/dragonLeft.png");
		dragonLeft = img.getImage();
		img = new ImageIcon("src/maze/gui/images/dragonRight.png");
		dragonRight = img.getImage();
		img = new ImageIcon("src/maze/gui/images/dragonBack.png");
		dragonBack = img.getImage();
	}
	
	public Image getGrass() {
		return grass;
	}
	
	public Image getWall() {
		return wall;
	}
	
	public Image getDragon() {
		return dragon;
	}
	
	public Image getSword() {
		return sword;
	}
	
	public Image getHero() {
		return hero;
	}
	
	public Image getHeroBack() {
		return heroBack;
	}
	
	public Image getHeroLeft() {
		return heroLeft;
	}
	
	public Image getHeroRight() {
		return heroRight;
	}
	
	public Image getHeroFrontSword() {
		return heroFrontSword;
	}
	
	public Image getHeroLeftSword() {
		return heroLeftSword;
	}
	
	public Image getHeroRightSword() {
		return heroRightSword;
	}
	
	public Image getDragonSleeping() {
		return dragonSleeping;
	}
	
	public Image getDragonLeft() {
		return dragonLeft;
	}
	
	public Image getDragonRight() {
		return dragonRight;
	}
	
	public Image getDragonBack() {
		return dragonBack;
	}
}
