package pacman.GUI;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.Timer;

import pacman.logic.Game;
import pacman.logic.Position;
import pacman.menus.GameOverAnimation;
import pacman.menus.MainMenu;
import pacman.menus.WinAnimation;
import resources.sounds.MediaPlayer;

@SuppressWarnings("serial")
public class GameEngine extends JPanel implements ActionListener, KeyListener, MouseListener
{
	final int MILISSECONDS_TO_REFRESH = 20;
	public static final int TILE_DIMENSION = 20;
	public static final int TOP_HUD_HEIGHT = 60;
	public static final int BOTTOM_HUD_HEIGHT = 40;
	static final int SPRITE_DIMENSION = 24;
	public static int refresh = 0;
	public static int inputKey = 0;
	Timer  timer;
	boolean sound = true;
	MediaPlayer beginning;
	MediaPlayer chomp;
	MediaPlayer power;
	MediaPlayer death;
	MediaPlayer ghost;
	MediaPlayer intermission;
	MediaPlayer fruit;

	private int deathAnimation = 0;
	int startAnimation = 7;

	public static Game game;;

	public GameEngine()
	{
		Application.frame.getContentPane().removeAll();
		Application.frame.getContentPane().revalidate();
		game = new Game();
		timer = new Timer(MILISSECONDS_TO_REFRESH, this);
		beginning = new MediaPlayer(Application.sounds.beginning);
		chomp = new MediaPlayer(Application.sounds.chomp);
		power = new MediaPlayer(Application.sounds.power);
		death = new MediaPlayer(Application.sounds.death);
		ghost = new MediaPlayer(Application.sounds.ghost);
		intermission = new MediaPlayer(Application.sounds.intermission);
		fruit = new MediaPlayer(Application.sounds.fruit);

		Application.frame.getContentPane().add(this, BorderLayout.CENTER);
		Application.frame.getContentPane().setPreferredSize(new Dimension(Game.maze.maze.get(0).length*TILE_DIMENSION, Game.maze.maze.size()*TILE_DIMENSION + TOP_HUD_HEIGHT + BOTTOM_HUD_HEIGHT));
		Application.frame.pack();

		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		requestFocus();

		repaint();
		startAnimation--;

		beginning.open();
		if(sound)
		{
			beginning.clip.start();
			chomp.open();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{ 
		refresh++;

		if(sound)
		{
			updateSound();
			
			if(!chomp.clip.isRunning())
				chomp.clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else
		{
			if(chomp.clip.isRunning())
				chomp.clip.stop();
			
			if(intermission.clip.isRunning())
				intermission.clip.close();
				
		}

		game.updateElements(Application.frame.getContentPane().getWidth());


		if(Game.collected_pills == Game.maze.getPills() || (!Game.pacman.getAlive() && this.deathAnimation == 11))
		{
			timer.stop();
			deathAnimation = 0;
			startAnimation = 7;

			if(Game.collected_pills == Game.maze.getPills())
			{
				if(game.getLevel() == 3)
				{
					Application.setNewScore(game.getPacman().getScore());

					try { this.finalize();}
					catch (Throwable e1) {}

					new WinAnimation();
					if(sound) 
						chomp.clip.close();
				}
				else
				{
					game.nextLevel();
					Game.pacman.bonusLife();
					startAnimation = 7;
					game.initLevel(true);

					this.setPreferredSize(new Dimension(Game.maze.maze.get(0).length*TILE_DIMENSION, Game.maze.maze.size()*TILE_DIMENSION + TOP_HUD_HEIGHT + BOTTOM_HUD_HEIGHT));
					Application.frame.pack();
					beginning.open();
					if(sound)
					{
						beginning.clip.start();
						chomp.clip.stop();
					}
				}

			}
			else if(Game.pacman.getLifes() == 0)
			{
				Application.setNewScore(game.getPacman().getScore());
				try { this.finalize();}
				catch (Throwable e1) {}

				new GameOverAnimation();

				chomp.clip.close();
			}
			else
			{
				game.initLevel(false);
				repaint();
				refresh = 0;

				if(!beginning.clip.isOpen())
					beginning.open();

				if(sound)
				{
					beginning.clip.start();
					chomp.clip.stop();
				}
			}
		}

		repaint();

		if(startAnimation > 1)
			startAnimation--;

	}

	private void updateSound() 
	{
		
		if(death.clip.isOpen() && !death.clip.isRunning())
			death.clip.close();

		if(beginning.clip.isOpen() && !beginning.clip.isRunning())
			beginning.clip.close();

		if(intermission.clip.isOpen() && !intermission.clip.isRunning())
			intermission.clip.close();

		if(power.clip.isOpen() && !power.clip.isRunning())
		{
			power.clip.close();
			intermission.open();
			intermission.clip.start();
		}

		if(ghost.clip.isOpen() && !ghost.clip.isRunning())
			ghost.clip.close();

		if(fruit.clip.isOpen() && !fruit.clip.isRunning())
			fruit.clip.close();

		if(!game.getPacman().getAlive() && !death.clip.isRunning())
		{
			if(beginning.clip.isRunning())
				beginning.clip.close();

			if(chomp.clip.isRunning())
				chomp.clip.stop();

			death.open();
			death.clip.start();
		}

		if(game.getPacman().getPower() == 12 && !power.clip.isOpen())
		{
			power.open();
			power.clip.start();
		}

		if(game.checkCharacterColision(false) && !ghost.clip.isOpen())
		{
			ghost.open();
			ghost.clip.start();
		}

		if(Game.maze.isFruit(game.getPacman().getTilePosition(game.getPacman().getX(), game.getPacman().getY())) && !fruit.clip.isOpen())
		{
			fruit.open();
			fruit.clip.start();
		}

	}



	@Override
	public void paintComponent(Graphics g)
	{

		for(int h = 0; h < Game.mazeHeight; h++)
			for(int w = 0; w < Game.mazeWidth; w++)
				if(Game.maze.isWall(new Position(w, h)))
				{
					if(game.getLevel() == 1)
						g.drawImage(Application.images.wallTile1, TILE_DIMENSION*w, TOP_HUD_HEIGHT+TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);
					else if(game.getLevel() == 2)
						g.drawImage(Application.images.wallTile2, TILE_DIMENSION*w, TOP_HUD_HEIGHT+TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);
					else
						g.drawImage(Application.images.wallTile3, TILE_DIMENSION*w, TOP_HUD_HEIGHT+TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);
				}
				else if(Game.maze.isPoint(new Position(w, h)))
					g.drawImage(Application.images.pointTile, TILE_DIMENSION*w, TOP_HUD_HEIGHT+TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);
				else if(Game.maze.isPowerPoint(new Position(w, h)))
					g.drawImage(Application.images.powerPointTile, TILE_DIMENSION*w, TOP_HUD_HEIGHT+TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);
				else if(Game.maze.isFruit(new Position(w, h)))
					g.drawImage(Application.images.fruits.getSubimage(0, 0, 50, 50), TILE_DIMENSION*w, TOP_HUD_HEIGHT+TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);
				else g.drawImage(Application.images.backgroundTile, TILE_DIMENSION*w, TOP_HUD_HEIGHT+TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);


		paintPacman(g);

		paintGhosts(g);

		paintScores(g);

		paintBottomHUD(g);



		if(startAnimation > 1)
		{
			if(startAnimation >= 6)
				g.drawImage(Application.images.startScreen, 0, 0, Application.frame.getContentPane().getWidth(), Application.frame.getContentPane().getHeight(), null);
			else {
				g.drawImage(Application.images.startScreen, Application.frame.getContentPane().getWidth()/(startAnimation*2), 
						Application.frame.getContentPane().getHeight()/(startAnimation*2), 
						Application.frame.getContentPane().getWidth() - Application.frame.getContentPane().getWidth()/(startAnimation), 
						Application.frame.getContentPane().getHeight() - Application.frame.getContentPane().getHeight()/(startAnimation), null);
			}
		}
	}

	private void paintBottomHUD(Graphics g) 
	{
		g.drawImage(Application.images.background, 0,  60 + Game.mazeHeight*TILE_DIMENSION, Application.frame.getContentPane().getWidth(), 40, null);

		for(int i = 0; i < Game.pacman.getLifes(); ++i)
			g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 20 + 35 * i, 60 + Game.mazeHeight * TILE_DIMENSION + 10, 25, 25, null);

		if(Game.pacman.getFruits() >= 10)
			g.drawImage(Application.images.numbers.getSubimage(30*((Game.pacman.getFruits()/10)%10), 0, 30, 30), Game.mazeWidth*TILE_DIMENSION - 25*4, 60 + Game.mazeHeight * TILE_DIMENSION + 10, 20, 20, null);

		g.drawImage(Application.images.numbers.getSubimage(30*((Game.pacman.getFruits()/10)%10), 0, 30, 30), Game.mazeWidth*TILE_DIMENSION - 25*3, 60 + Game.mazeHeight * TILE_DIMENSION + 10, 20, 20, null);
		g.drawImage(Application.images.letters.getSubimage(23*50, 0, 50, 50), Game.mazeWidth*TILE_DIMENSION - 25*2, 60 + Game.mazeHeight * TILE_DIMENSION + 15, 15, 15, null);
		g.drawImage(Application.images.fruits.getSubimage(0, 0, 50, 50), Game.mazeWidth*TILE_DIMENSION - 25, 60 + Game.mazeHeight * TILE_DIMENSION + 10, 20, 20, null);

	} 

	private void paintGhosts(Graphics g) 
	{
		if(Game.redFlag)
			if(game.getRedGhost().getAlive())
			{
				if(Game.pacman.getPower() == 0)
					g.drawImage(Application.images.sprites.getSubimage(game.getRedGhost().getAnimation() * SPRITE_DIMENSION +SPRITE_DIMENSION*4, game.getRedGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
							game.getRedGhost().getX() - 5, game.getRedGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);
				else if(Game.pacman.getPower() > 5 || Game.pacman.getPower() % 2 == 0)
					g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*13, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
							game.getRedGhost().getX() - 5, game.getRedGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);
				else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*15, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
						game.getRedGhost().getX() - 5, game.getRedGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);

			}
			else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*12, game.getRedGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
					game.getRedGhost().getX() - 5, game.getRedGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);


		if(Game.pinkFlag)
			if(game.getPinkGhost().getAlive())
			{
				if(Game.pacman.getPower() == 0)
					g.drawImage(Application.images.sprites.getSubimage(game.getPinkGhost().getAnimation() * SPRITE_DIMENSION +SPRITE_DIMENSION*8, game.getPinkGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
							game.getPinkGhost().getX() - 5, game.getPinkGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);
				else if(Game.pacman.getPower() > 5 || Game.pacman.getPower() % 2 == 0)
					g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*13, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
							game.getPinkGhost().getX() - 5, game.getPinkGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);
				else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*15, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
						game.getPinkGhost().getX() - 5, game.getPinkGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);

			}
			else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*12, game.getPinkGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
					game.getPinkGhost().getX() - 5, game.getPinkGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);

		if(Game.orangeFlag)
			if(game.getOrangeGhost().getAlive())
			{
				if(Game.pacman.getPower() == 0)
					g.drawImage(Application.images.sprites.getSubimage(game.getOrangeGhost().getAnimation() * SPRITE_DIMENSION +SPRITE_DIMENSION*10, game.getOrangeGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
							game.getOrangeGhost().getX() - 5, game.getOrangeGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);
				else if(Game.pacman.getPower() > 5 || Game.pacman.getPower() % 2 == 0)
					g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*13, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
							game.getOrangeGhost().getX() - 5, game.getOrangeGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);
				else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*15, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
						game.getOrangeGhost().getX() - 5, game.getOrangeGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);

			}
			else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*12, game.getOrangeGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
					game.getOrangeGhost().getX() - 5, game.getOrangeGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);

		if(Game.blueFlag)
			if(game.getBlueGhost().getAlive())
			{
				if(Game.pacman.getPower() == 0)
					g.drawImage(Application.images.sprites.getSubimage(game.getBlueGhost().getAnimation() * SPRITE_DIMENSION +SPRITE_DIMENSION*6, game.getBlueGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
							game.getBlueGhost().getX() - 5, game.getBlueGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);
				else if(Game.pacman.getPower() > 5 || Game.pacman.getPower() % 2 == 0)
					g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*13, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
							game.getBlueGhost().getX() - 5, game.getBlueGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);
				else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*15, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
						game.getBlueGhost().getX() - 5, game.getBlueGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);
			}
			else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*12, game.getBlueGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
					game.getBlueGhost().getX() - 5, game.getBlueGhost().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);


	}

	private void paintPacman(Graphics g)
	{

		if(Game.pacman.getAlive())
		{
			if(Game.male)
			g.drawImage(Application.images.sprites.getSubimage(game.getPacman().getAnimation() * SPRITE_DIMENSION, game.getPacman().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
					game.getPacman().getX() - 5, game.getPacman().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);
			else 
			{
				int o = game.getPacman().getOrientation();
				if(o == 0)
					o = 2;
				else if (o == 2)
					o = 3;
				else if(o == 3) 
					o = 0;
				
				int a = game.getPacman().getAnimation();
				if(a == 3)
					a = 1;
				
				g.drawImage(Application.images.mspacman.getSubimage(a * 46, o * 45, 46, 45), 
						game.getPacman().getX() - 5, game.getPacman().getY() + TOP_HUD_HEIGHT - 5, TILE_DIMENSION + 10, TILE_DIMENSION + 10, null, null);
			}
		}
		else 
		{
			g.drawImage(Application.images.deathAnimation.getSubimage(this.deathAnimation*50, 0, 50, 57), 
					game.getPacman().getX(), game.getPacman().getY() + TOP_HUD_HEIGHT, TILE_DIMENSION, TILE_DIMENSION, null, null);

			if(refresh % 4 == 0)
				deathAnimation++;
		}
	}	

	private void paintScores(Graphics g) 
	{
		g.drawImage(Application.images.background, 0,  0, Application.frame.getContentPane().getWidth(), 60, null);

		g.drawImage(Application.images.letters.getSubimage(7*50, 0, 50, 50), 25, 10, 20, 20, null); //h
		g.drawImage(Application.images.letters.getSubimage(8*50, 0, 50, 50), 25*2, 10, 20, 20, null); //i
		g.drawImage(Application.images.letters.getSubimage(6*50, 0, 50, 50), 25*3, 10, 20, 20, null); //g
		g.drawImage(Application.images.letters.getSubimage(7*50, 0, 50, 50), 25*4, 10, 20, 20, null); //h

		g.drawImage(Application.images.letters.getSubimage(18*50, 0, 50, 50), 25*6, 10, 20, 20, null); //s
		g.drawImage(Application.images.letters.getSubimage(2*50, 0, 50, 50), 25*7, 10, 20, 20, null);  //c
		g.drawImage(Application.images.letters.getSubimage(14*50, 0, 50, 50), 25*8, 10, 20, 20, null); //o
		g.drawImage(Application.images.letters.getSubimage(17*50, 0, 50, 50), 25*9, 10, 20, 20, null); //r
		g.drawImage(Application.images.letters.getSubimage(4*50, 0, 50, 50), 25*10, 10, 20, 20, null);  //e

		if(Application.scores.get(0) >= 1000000)
			g.drawImage(Application.images.numbers.getSubimage(30*(Application.scores.get(0)/1000000)%10, 0, 30, 30), 25, 35, 20, 20, null);

		if(Application.scores.get(0) >= 100000)
			g.drawImage(Application.images.numbers.getSubimage(30*(Application.scores.get(0)/100000)%10, 0, 30, 30), 25*2, 35, 20, 20, null);

		if(Application.scores.get(0) >= 10000)
			g.drawImage(Application.images.numbers.getSubimage(30*((Application.scores.get(0)/10000)%10), 0, 30, 30), 25*3, 35, 20, 20, null);

		if(Application.scores.get(0) >= 1000)
			g.drawImage(Application.images.numbers.getSubimage(30*((Application.scores.get(0)/1000)%10), 0, 30, 30), 25*4, 35, 20, 20, null);

		if(Application.scores.get(0) >= 100)
			g.drawImage(Application.images.numbers.getSubimage(30*((Application.scores.get(0)/100)%10), 0, 30, 30), 25*5, 35, 20, 20, null);

		if(Application.scores.get(0) >= 10)
			g.drawImage(Application.images.numbers.getSubimage(30*((Application.scores.get(0)/10)%10), 0, 30, 30), 25*6, 35, 20, 20, null);

		if(Application.scores.get(0) >= 1)
			g.drawImage(Application.images.numbers.getSubimage(30*(Application.scores.get(0)%10), 0, 30, 30), 25*7, 35, 20, 20, null);	


		if(sound)
			g.drawImage(Application.images.soundON, 25*13, 10, 30, 30, null); 
		else
			g.drawImage(Application.images.soundOFF, 25*13, 10, 30, 30, null); 
		
		
		
		g.drawImage(Application.images.letters.getSubimage(18*50, 0, 50, 50), Game.mazeWidth*TILE_DIMENSION - 25*5, 10, 20, 20, null); //s
		g.drawImage(Application.images.letters.getSubimage(2*50, 0, 50, 50), Game.mazeWidth*TILE_DIMENSION - 25*4, 10, 20, 20, null);  //c
		g.drawImage(Application.images.letters.getSubimage(14*50, 0, 50, 50), Game.mazeWidth*TILE_DIMENSION - 25*3, 10, 20, 20, null); //o
		g.drawImage(Application.images.letters.getSubimage(17*50, 0, 50, 50), Game.mazeWidth*TILE_DIMENSION - 25*2, 10, 20, 20, null); //r
		g.drawImage(Application.images.letters.getSubimage(4*50, 0, 50, 50), Game.mazeWidth*TILE_DIMENSION - 25, 10, 20, 20, null);  //e

		if(Game.pacman.getScore() >= 1000000)
			g.drawImage(Application.images.numbers.getSubimage(30*(Game.pacman.getScore()/1000000)%10, 0, 30, 30), Game.mazeWidth*TILE_DIMENSION - 25*7, 35, 20, 20, null);

		if(Game.pacman.getScore() >= 100000)
			g.drawImage(Application.images.numbers.getSubimage(30*(Game.pacman.getScore()/100000)%10, 0, 30, 30), Game.mazeWidth*TILE_DIMENSION - 25*6, 35, 20, 20, null);

		if(Game.pacman.getScore() >= 10000)
			g.drawImage(Application.images.numbers.getSubimage(30*((Game.pacman.getScore()/10000)%10), 0, 30, 30), Game.mazeWidth*TILE_DIMENSION - 25*5, 35, 20, 20, null);

		if(Game.pacman.getScore() >= 1000)
			g.drawImage(Application.images.numbers.getSubimage(30*((Game.pacman.getScore()/1000)%10), 0, 30, 30), Game.mazeWidth*TILE_DIMENSION - 25*4, 35, 20, 20, null);

		if(Game.pacman.getScore() >= 100)
			g.drawImage(Application.images.numbers.getSubimage(30*((Game.pacman.getScore()/100)%10), 0, 30, 30), Game.mazeWidth*TILE_DIMENSION - 25*3, 35, 20, 20, null);

		if(Game.pacman.getScore() >= 10)
			g.drawImage(Application.images.numbers.getSubimage(30*((Game.pacman.getScore()/10)%10), 0, 30, 30), Game.mazeWidth*TILE_DIMENSION - 25*2, 35, 20, 20, null);

		if(Game.pacman.getScore() >= 1)
			g.drawImage(Application.images.numbers.getSubimage(30*(Game.pacman.getScore()%10), 0, 30, 30), Game.mazeWidth*TILE_DIMENSION - 25, 35, 20, 20, null);	


	}


	@Override
	public void keyPressed(KeyEvent e)
	{

		if(e.getKeyCode() == KeyEvent.VK_UP )
			inputKey = KeyEvent.VK_UP;
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			inputKey = KeyEvent.VK_RIGHT;

			if(!timer.isRunning())
			{
				timer.start();
				chomp.clip.loop(Clip.LOOP_CONTINUOUSLY);

				if(intermission.clip.isOpen())
					intermission.clip.start();
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			inputKey = KeyEvent.VK_DOWN;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			inputKey = KeyEvent.VK_LEFT;
			if(!timer.isRunning())
			{
				timer.start();
				chomp.clip.loop(Clip.LOOP_CONTINUOUSLY);

				if(intermission.clip.isOpen())
					intermission.clip.start();
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			if(timer.isRunning())
			{				
				timer.stop();
				repaint();
				startAnimation = 6;

				if(chomp.clip.isRunning())
					chomp.clip.stop();

				if(intermission.clip.isRunning())
					intermission.clip.stop();
			}
			else 
			{
				try { this.finalize();}
				catch (Throwable e1) {}

				if(beginning.clip.isRunning())
					beginning.clip.close();

				if(intermission.clip.isRunning())
					intermission.clip.close();

				new MainMenu();
			}

		}
		else if (e.getKeyCode() == KeyEvent.VK_M)
		{
			if(sound)
				sound = false;
			else sound = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getX() >= 25*13 && e.getX() <= 25*13+30)
		{
			if(e.getY() >= 10 && e.getY() <= 40)
				if(sound)
					sound = false;
				else sound = true;
		}
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		

		java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
		Cursor a = toolkit.createCustomCursor(Application.images.mouse , new Point(this.getX(),this.getY()), "img");
		Application.frame.setCursor (a);
	}


	@Override
	public void mouseExited(MouseEvent arg0) {}


	@Override
	public void mousePressed(MouseEvent arg0) {}


	@Override
	public void mouseReleased(MouseEvent arg0) {}


}
