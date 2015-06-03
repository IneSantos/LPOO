package pacman.GUI;

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
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
