package maze.gui;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Map {

	private Image grass, wall, dragon, sword, hero, heroBack, heroLeft, heroRight, heroFrontSword, heroLeftSword, exit,
	heroRightSword, dragonSleeping, dragonLeft, dragonRight, dragonBack, EagleWall, EagleGrass, EagleWallSword, EagleGrassSword;
	
	/**
	 * Loads all the Images that are going to be used in GUI
	 */
	public Map() {
		
		//Maze content
		ImageIcon img = new ImageIcon("src/maze/gui/images/wall.png");
		wall = img.getImage();
		img = new ImageIcon("src/maze/gui/images/grass.png");
		grass = img.getImage();
		img = new ImageIcon("src/maze/gui/images/exit.jpg");
		exit = img.getImage();
		
		
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
		
		//Eagle 
		img = new ImageIcon("src/maze/gui/images/EagleWall.jpg");
		EagleWall = img.getImage();
		img = new ImageIcon("src/maze/gui/images/EagleWallSword.jpg");
		EagleWallSword = img.getImage();
		img = new ImageIcon("src/maze/gui/images/EagleGrass.jpg");
		EagleGrass = img.getImage();
		img = new ImageIcon("src/maze/gui/images/EagleGrassSword.jpg");
		EagleGrassSword = img.getImage();
		
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
	
	/**
	 * Get Grass' Image
	 * @return
	 * 		Image that represents the grass
	 */
	public Image getGrass() {
		return grass;
	}
	
	/**
	 * Get wall's Image
	 * @return
	 * 		Image that represents the wall
	 */
	public Image getWall() {
		return wall;
	}
	
	/**
	 * Get dragon's Image
	 * @return
	 * 		Image that represents the dragon
	 */
	public Image getDragon() {
		return dragon;
	}
	
	/**
	 * Get sword's Image
	 * @return
	 * 		Image that represents the sword
	 */
	public Image getSword() {
		return sword;
	}
	
	/**
	 * Get hero' Image
	 * @return
	 * 		Image that represents the hero
	 */
	public Image getHero() {
		return hero;
	}
	
	/**
	 * Get hero' Image seen from behind
	 * @return
	 * 		Image that represents the hero seen from behind
	 */
	public Image getHeroBack() {
		return heroBack;
	}
	
	/**
	 * Get hero's Image seen from his left side
	 * @return
	 * 		Image that represents the hero seen from his left side
	 */
	public Image getHeroLeft() {
		return heroLeft;
	}
	
	/**
	 * Get hero's Image seen from his right side
	 * @return
	 * 		Image that represents the hero seen from his right side
	 */
	public Image getHeroRight() {
		return heroRight;
	}
	
	/**
	 * Get hero's Image armed with the sword
	 * @return
	 * 		Image that represents the hero armed with the sword
	 */
	public Image getHeroFrontSword() {
		return heroFrontSword;
	}
	
	/**
	 * Get hero's Image armed with the sword being seen from his left side
	 * @return
	 * 		Image that represents the hero armed with the sword being seen from his left side
	 */
	public Image getHeroLeftSword() {
		return heroLeftSword;
	}
	
	/**
	 * Get hero's Image armed with the sword being seen from his right side
	 * @return
	 * 		Image that represents the hero armed with the sword being seen from his right side
	 */
	public Image getHeroRightSword() {
		return heroRightSword;
	}
	
	/**
	 * Get Sleeping Dragon's Image
	 * @return
	 * 		Image that represents the dragon when sleeping
	 */
	public Image getDragonSleeping() {
		return dragonSleeping;
	}
	
	/**
	 * Get dragon's Image seen from his left side
	 * @return
	 * 		Image that represents the dragon seen from his left side
	 */
	public Image getDragonLeft() {
		return dragonLeft;
	}
	
	/**
	 * Get dragon's Image seen from his right side
	 * @return
	 * 		Image that represents the dragon seen from his right side
	 */
	public Image getDragonRight() {
		return dragonRight;
	}
	
	/**
	 * Get dragon's Image seen from behind
	 * @return
	 * 		Image that represents the dragon seen from behind
	 */
	public Image getDragonBack() {
		return dragonBack;
	}
	
	/**
	 * Get eagle's Image when flying above the grass
	 * @return
	 * 		Image that represents eagle flying above the grass
	 */
	public Image getEagleGrass() {
		return EagleGrass;
	}
	
	/**
	 * Get eagle's Image when flying above the grass carrying the sword
	 * @return
	 * 		Image that represents eagle flying above the grass carrying the sword
	 */
	public Image getEagleGrassSword() {
		return EagleGrassSword;
	}
	
	/**
	 * Get eagle's Image when flying above a wall
	 * @return
	 * 		Image that represents eagle flying above a wall
	 */
	public Image getEagleWall() {
		return EagleWall;
	}
	
	/**
	 * Get eagle's Image when flying above a wall carrying the sword
	 * @return
	 * 		Image that represents eagle flying above a wall carrying the sword
	 */
	public Image getEagleWallSword() {
		return EagleWallSword;
	}
	
	/**
	 * Get exit's Image
	 * @return
	 * 		Image that represents the exit
	 */
	public Image getExit() {
		return exit;
	}
}
