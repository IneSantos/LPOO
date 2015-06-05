package pacman.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import pacman.logic.Game;
import pacman.logic.Game.Mode;
import pacman.logic.Position;
import pacman.menus.MainMenu;
import pacman.sound.Sound;

@SuppressWarnings("serial")
public class GameEngine extends JPanel implements ActionListener, KeyListener
{
	final int MILISSECONDS_TO_REFRESH = 20;
	public static final int TILE_DIMENSION = 20;
	static final int SPRITE_DIMENSION = 24;
	Timer  timer;
	Sound sound;
	int refresh = 0;

	int inputKey = 0;
	private int deathAnimation = 0;
	int startAnimation = -1;


	public static Game game;;

	public GameEngine()
	{
		Application.frame.getContentPane().removeAll();
		timer = new Timer(MILISSECONDS_TO_REFRESH, this);

		Application.frame.getContentPane().removeAll();

		game = new Game();
		sound = new Sound();

		this.setPreferredSize(new Dimension(game.getMaze().maze[0].length*TILE_DIMENSION, game.getMaze().maze.length*TILE_DIMENSION));
		Application.frame.getContentPane().add(this, BorderLayout.CENTER);
		System.out.println(Game.pacman.getLifes());

		addKeyListener(this);
		setFocusable(true);
		requestFocus();

		Application.frame.pack();
		Application.frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		refresh++;

		//Actualizacao da animacao
		if(refresh % 2 == 0)
		{
			game.getPacman().updateAnimation();
			game.getRedGhost().updateAnimation();
			game.getPinkGhost().updateAnimation();
			game.getOrangeGhost().updateAnimation();
			game.getBlueGhost().updateAnimation();
		}	

		
		//Verificacao do power up do pacman
		if(Game.pacman.getPower() > 0 && refresh %  25== 0)
		{
			Game.pacman.decPower();

			if(Game.pacman.getPower() == 0)
				Game.ghostMode = Mode.CHASE;
		}

		//Atualizacao do comportamento dos fantasmas
		if(refresh == 50*7 || refresh == 50*34 || refresh == 50*59 || refresh == 50*84)
			Game.ghostMode = Mode.CHASE;
		else if(refresh == 50*27 || refresh == 50*54 || refresh == 50*79)
			Game.ghostMode = Mode.SCATTER;

		//Actualizacao do movimento dos elementos da cena
		if(Game.pacman.getAlive())
			game.getPacman().updateMovement(inputKey);
		/*game.getRedGhost().moveGhost();
		game.getPinkGhost().moveGhost();
		game.getOrangeGhost().moveGhost();
		game.getBlueGhost().moveGhost();*/

		//Verificacao de colisoes entre elementos da cena
		if(Game.pacman.getAlive())
			game.checkCharacterColision();

		//verificacoes de arrnaque de Orange e Blue
		if(game.getCollectedPills() >= 30 && game.getBlueGhost().house)
		{
			game.getBlueGhost().setOrientation(0);
			game.getBlueGhost().house = false;
		}
		if(game.getCollectedPills() >= Game.maze.getPills()/3 && game.getOrangeGhost().house)
		{
			game.getOrangeGhost().setOrientation(0);
			game.getOrangeGhost().house = false;
		}

		//Verificacao de fim do jogo
		if(game.getCollectedPills() == Game.maze.getPills() || (!Game.pacman.getAlive() && this.deathAnimation == 11))
		{
			timer.stop();
			Sound.clip.close();
			sound = new Sound();
			this.deathAnimation = 0;

			if(game.getCollectedPills() == Game.maze.getPills())
			{
				try { this.finalize();}
				catch (Throwable e1) {}
				
				Sound.clip.close();
				new MainMenu();
			}
			else if(Game.pacman.getLifes() == 0)
			{
				try { this.finalize();}
				catch (Throwable e1) {}
				
				Sound.clip.close();
				new MainMenu();
			}
			else
			{
				game.initLevel(1);
				Game.pacman.setAlive(true);
				Game.pacman.setOrientation(2);
				
				this.startAnimation = -1;
				repaint();
				refresh = 0;
			}
			
		}
		
		
		
		repaint();
	}

	@Override
	public void paintComponent(Graphics g)
	{

		for(int h = 0; h < Game.mazeHeight; h++)
			for(int w = 0; w < Game.mazeWidth; w++)
				if(game.getMaze().isWall(new Position(w, h)))
					g.drawImage(Application.images.wallTile, TILE_DIMENSION*w, TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);
				else if(game.getMaze().isPoint(new Position(w, h)))
					g.drawImage(Application.images.pointTile, TILE_DIMENSION*w, TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);
				else if(game.getMaze().isPowerPoint(new Position(w, h)))
					g.drawImage(Application.images.powerPointTile, TILE_DIMENSION*w, TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);
				else g.drawImage(Application.images.backgroundTile, TILE_DIMENSION*w, TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);

		if(Game.pacman.getAlive())
			g.drawImage(Application.images.sprites.getSubimage(game.getPacman().getAnimation() * SPRITE_DIMENSION, game.getPacman().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
					game.getPacman().getX(), game.getPacman().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);
		else 
		{
			g.drawImage(Application.images.deathAnimation.getSubimage(this.deathAnimation*50, 0, 50, 57), 
					game.getPacman().getX(), game.getPacman().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);

			if(refresh % 4 == 0)
				deathAnimation++;
		}


		if(game.getRedGhost().getAlive())
		{
			if(Game.pacman.getPower() == 0)
				g.drawImage(Application.images.sprites.getSubimage(game.getRedGhost().getAnimation() * SPRITE_DIMENSION +SPRITE_DIMENSION*4, game.getRedGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
						game.getRedGhost().getX(), game.getRedGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);
			else if(Game.pacman.getPower() > 5 || Game.pacman.getPower() % 2 == 0)
				g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*13, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
						game.getRedGhost().getX(), game.getRedGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);
			else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*15, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
					game.getRedGhost().getX(), game.getRedGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);

		}
		else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*12, game.getRedGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
				game.getRedGhost().getX(), game.getRedGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);


		if(game.getPinkGhost().getAlive())
		{
			if(Game.pacman.getPower() == 0)
				g.drawImage(Application.images.sprites.getSubimage(game.getPinkGhost().getAnimation() * SPRITE_DIMENSION +SPRITE_DIMENSION*8, game.getPinkGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
						game.getPinkGhost().getX(), game.getPinkGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);
			else if(Game.pacman.getPower() > 5 || Game.pacman.getPower() % 2 == 0)
				g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*13, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
						game.getPinkGhost().getX(), game.getPinkGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);
			else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*15, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
					game.getPinkGhost().getX(), game.getPinkGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);

		}
		else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*12, game.getPinkGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
				game.getPinkGhost().getX(), game.getPinkGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);


		if(game.getOrangeGhost().getAlive())
		{
			if(Game.pacman.getPower() == 0)
				g.drawImage(Application.images.sprites.getSubimage(game.getOrangeGhost().getAnimation() * SPRITE_DIMENSION +SPRITE_DIMENSION*10, game.getOrangeGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
						game.getOrangeGhost().getX(), game.getOrangeGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);
			else if(Game.pacman.getPower() > 5 || Game.pacman.getPower() % 2 == 0)
				g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*13, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
						game.getOrangeGhost().getX(), game.getOrangeGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);
			else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*15, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
					game.getOrangeGhost().getX(), game.getOrangeGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);

		}
		else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*12, game.getOrangeGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
				game.getPinkGhost().getX(), game.getOrangeGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);


		if(game.getBlueGhost().getAlive())
		{
			if(Game.pacman.getPower() == 0)
				g.drawImage(Application.images.sprites.getSubimage(game.getBlueGhost().getAnimation() * SPRITE_DIMENSION +SPRITE_DIMENSION*6, game.getBlueGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
						game.getBlueGhost().getX(), game.getBlueGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);
			else if(Game.pacman.getPower() > 5 || Game.pacman.getPower() % 2 == 0)
				g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*13, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
						game.getBlueGhost().getX(), game.getBlueGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);
			else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*15, 0, SPRITE_DIMENSION, SPRITE_DIMENSION), 
					game.getBlueGhost().getX(), game.getBlueGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);
		}
		else g.drawImage(Application.images.sprites.getSubimage(SPRITE_DIMENSION*12, game.getBlueGhost().getOrientation() * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION), 
				game.getPinkGhost().getX(), game.getBlueGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);

		if(startAnimation <= 5)
		{
			g.drawImage(Application.images.startScreen, startAnimation * Application.frame.getContentPane().getWidth()/10, startAnimation * Application.frame.getContentPane().getHeight()/10, 
					Application.frame.getContentPane().getWidth() - startAnimation * Application.frame.getContentPane().getWidth()/5, 
					Application.frame.getContentPane().getHeight() - startAnimation * Application.frame.getContentPane().getHeight()/5, null);

			startAnimation++;
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(!timer.isRunning())
		{
			Sound.clip.start();
			Sound.clip.loop(10);
			timer.start();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP )
			inputKey = KeyEvent.VK_UP;
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			inputKey = KeyEvent.VK_RIGHT;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			inputKey = KeyEvent.VK_DOWN;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			inputKey = KeyEvent.VK_LEFT;
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			if(timer.isRunning())
			{
				Sound.clip.stop();
				timer.stop();
				repaint();
				startAnimation = 0;
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}


}
