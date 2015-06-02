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
import pacman.logic.Maze;
import pacman.logic.Position;

@SuppressWarnings("serial")
public class GameEngine extends JPanel implements ActionListener, KeyListener
{
	final int MILISSECONDS_TO_REFRESH = 20;
	public static final int TILE_DIMENSION = 20;
	Timer  timer;
	int refresh = 0;
	
	int inputKey = 0;

	
	Game game = new Game();
		
	public GameEngine()
	{
		timer = new Timer(MILISSECONDS_TO_REFRESH, this);
		timer.start();
		
		this.setPreferredSize(new Dimension(game.getMaze().maze[0].length*TILE_DIMENSION, game.getMaze().maze.length*TILE_DIMENSION));
		Application.frame.getContentPane().add(this, BorderLayout.CENTER);

		game.getPacman().setPosition(new Position(13*TILE_DIMENSION, (17+3)*TILE_DIMENSION));
		game.getRedGhost().setPosition(new Position(13*TILE_DIMENSION, 14*TILE_DIMENSION));
		
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
		
		Application.frame.pack();
		Application.frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(refresh++ == 0){
			game.getPacman().updateAnimation();
			game.getRedGhost().updateAnimation();
		}
		else refresh = 0;
				
		game.getPacman().updateMovement(inputKey, game.getMaze());
		game.getRedGhost().moveGhost(game.getMaze());
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		int mazeWidth = game.getMaze().maze[0].length;
		int mazeHeight = game.getMaze().maze.length;
		
		for(int h = 0; h < mazeHeight; h++)
			for(int w = 0; w < mazeWidth; w++)
				if(game.getMaze().isWall(new Position(w, h)))
					g.drawImage(Application.images.wallTile, TILE_DIMENSION*w, TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);
				else if(game.getMaze().isPoint(new Position(w, h)))
					g.drawImage(Application.images.pointTile, TILE_DIMENSION*w, TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);
				else if(game.getMaze().isPowerPoint(new Position(w, h)))
					g.drawImage(Application.images.powerPointTile, TILE_DIMENSION*w, TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);
				else g.drawImage(Application.images.backgroundTile, TILE_DIMENSION*w, TILE_DIMENSION*h, TILE_DIMENSION, TILE_DIMENSION, null, null);
		
				
		g.drawImage(Application.images.sprites.getSubimage(game.getPacman().getAnimation() * 24, game.getPacman().getOrientation() * 24, 24, 24), 
					game.getPacman().getX(), game.getPacman().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);
		
		
		g.drawImage(Application.images.sprites.getSubimage(game.getRedGhost().getAnimation() * 24 +24*4, game.getRedGhost().getOrientation() * 24, 24, 24), 
				game.getRedGhost().getX(), game.getRedGhost().getY(), TILE_DIMENSION, TILE_DIMENSION, null, null);
		

		System.out.println(game.getRedGhost().getOrientation());
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_UP )
			inputKey = KeyEvent.VK_UP;
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			inputKey = KeyEvent.VK_RIGHT;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			inputKey = KeyEvent.VK_DOWN;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			inputKey = KeyEvent.VK_LEFT;
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	public void resetInputKey()
	{
		inputKey = 0;
	}

}
