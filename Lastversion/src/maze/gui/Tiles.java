package maze.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tiles {
	
	public static int TILE_DIMENSION = 40;

	public static BufferedImage wall;
	public static BufferedImage grass;
	public static BufferedImage hero;
	public static BufferedImage dragon;
	public static BufferedImage dragon_sleeping;
	public static BufferedImage sword;
	public static BufferedImage dart;
	public static BufferedImage shield;
	
	public Tiles()
	{
		try 
		{
			wall = ImageIO.read(this.getClass().getResource("/images/wall.png"));
			grass = ImageIO.read(this.getClass().getResource("/images/grass.png"));
			hero = ImageIO.read(this.getClass().getResource("/images/hero.png"));
			dragon = ImageIO.read(this.getClass().getResource("/images/dragon.png"));
			dragon_sleeping = ImageIO.read(this.getClass().getResource("/images/dragon_sleeping.png"));
			sword = ImageIO.read(this.getClass().getResource("/images/sword.png"));
			dart = ImageIO.read(this.getClass().getResource("/images/dart.png"));
			shield = ImageIO.read(this.getClass().getResource("/images/shield.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}
