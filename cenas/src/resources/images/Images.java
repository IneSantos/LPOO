package resources.images;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

	public BufferedImage sprites;
	public BufferedImage background;		
	public BufferedImage pointTile;
	public BufferedImage powerPointTile;
	public BufferedImage backgroundTile;
	public BufferedImage wallTile1;
	public BufferedImage wallTile2;
	public BufferedImage wallTile3;
	public BufferedImage gif;
	public BufferedImage deathAnimation;
	public BufferedImage startScreen;
	public BufferedImage game_over1;
	public BufferedImage game_over2;
	public BufferedImage mouse;
	public BufferedImage numbers;
	public BufferedImage letters;
	public BufferedImage fruits;
	public BufferedImage main_menuImage;
	public BufferedImage background1;
	public BufferedImage background2;
	public BufferedImage scoresAni;
	public BufferedImage scores;
	public BufferedImage winbackground;
	public BufferedImage win2;
	


	public Images()
	{
		System.out.println("Images");

		try 
		{
			sprites = ImageIO.read(this.getClass().getResource("/resources/images/pacmanSprites.png"));
			background = ImageIO.read(this.getClass().getResource("/resources/images/background.jpg"));
			pointTile = ImageIO.read(this.getClass().getResource("/resources/images/point.png"));
			powerPointTile = ImageIO.read(this.getClass().getResource("/resources/images/powerPoint.png"));
			backgroundTile = ImageIO.read(this.getClass().getResource("/resources/images/blank.png"));
			wallTile1 = ImageIO.read(this.getClass().getResource("/resources/images/tile1.png"));
			wallTile2 = ImageIO.read(this.getClass().getResource("/resources/images/tile2.png"));
			wallTile3 = ImageIO.read(this.getClass().getResource("/resources/images/tile3.png"));
			gif = ImageIO.read(this.getClass().getResource("/resources/images/pacman.gif"));
			deathAnimation = ImageIO.read(this.getClass().getResource("/resources/images/deathAnimation.png"));
			startScreen = ImageIO.read(this.getClass().getResource("/resources/images/startScreen.png"));
			game_over1 = ImageIO.read(this.getClass().getResource("/resources/images/game_over_1.png"));
			game_over2 = ImageIO.read(this.getClass().getResource("/resources/images/game_over_2.png"));
			mouse = ImageIO.read(this.getClass().getResource("/resources/images/mouse.png"));
			numbers = ImageIO.read(this.getClass().getResource("/resources/images/numbers.png"));
			letters = ImageIO.read(this.getClass().getResource("/resources/images/letters.png"));
			fruits = ImageIO.read(this.getClass().getResource("/resources/images/fruits.png"));
			main_menuImage = ImageIO.read(this.getClass().getResource("/resources/images/main_menu.jpg"));
			background1 = ImageIO.read(this.getClass().getResource("/resources/images/1.png"));
			background2 = ImageIO.read(this.getClass().getResource("/resources/images/2.png"));
			scoresAni = ImageIO.read(this.getClass().getResource("/resources/images/scores_ani.png"));
			scores = ImageIO.read(this.getClass().getResource("/resources/images/socre_sem_ani.png"));
			winbackground = ImageIO.read(this.getClass().getResource("/resources/images/win1.jpg"));
			win2 = ImageIO.read(this.getClass().getResource("/resources/images/win2.jpg"));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
