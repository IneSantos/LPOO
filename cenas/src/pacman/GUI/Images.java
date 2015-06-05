package pacman.GUI;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	
	public BufferedImage sprites;
	public BufferedImage background;		
	public BufferedImage pointTile;
	public BufferedImage powerPointTile;
	public BufferedImage backgroundTile;
	public BufferedImage wallTile;
	public BufferedImage gif;
	public BufferedImage deathAnimation;
	public BufferedImage startScreen;
	public BufferedImage game_over1;
	public BufferedImage game_over2;
	public BufferedImage mouse;

	public Images()
	{
		try 
		{
			sprites = ImageIO.read(this.getClass().getResource("/images/pacmanSprites.png"));
			background = ImageIO.read(this.getClass().getResource("/images/background.jpg"));
			pointTile = ImageIO.read(this.getClass().getResource("/images/point.png"));
			powerPointTile = ImageIO.read(this.getClass().getResource("/images/powerPoint.png"));
			backgroundTile = ImageIO.read(this.getClass().getResource("/images/blank.png"));
			wallTile = ImageIO.read(this.getClass().getResource("/images/wall.PNG"));
			gif = ImageIO.read(this.getClass().getResource("/images/pacman.gif"));
			deathAnimation = ImageIO.read(this.getClass().getResource("/images/deathAnimation.png"));
			startScreen = ImageIO.read(this.getClass().getResource("/images/startScreen.png"));
			game_over1 = ImageIO.read(this.getClass().getResource("/images/game_over_1.png"));
			game_over2 = ImageIO.read(this.getClass().getResource("/images/game_over_2.png"));
			mouse = ImageIO.read(this.getClass().getResource("/images/mouse.png"));
			
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
