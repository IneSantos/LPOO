package pacman.GUI;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	
	public BufferedImage sprites;
	public BufferedImage background;		

	public Images()
	{
		try 
		{
			sprites = ImageIO.read(this.getClass().getResource("/images/pacmanSprites.png"));
			background = ImageIO.read(this.getClass().getResource("/images/background.jpg"));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
