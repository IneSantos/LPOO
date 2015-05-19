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
import pacman.logic.Position;

@SuppressWarnings("serial")
public class GameEngine extends JPanel implements ActionListener, KeyListener
{
	final int MILISSECONDS_TO_REFRESH = 20;
	Timer  timer;
	int refresh = 0;
	
	int inputKey = 0;
	int tileWidth = 20;
	int tileHeight = 20;
	
	Game game = new Game();
		
	public GameEngine()
	{
		timer = new Timer(MILISSECONDS_TO_REFRESH, this);
		timer.start();
		
		this.setPreferredSize(new Dimension(game.getMaze().maze[0].length*tileWidth, game.getMaze().maze.length*tileHeight));
		Application.frame.getContentPane().add(this, BorderLayout.CENTER);

		game.getPacman().setPosition(new Position(13*tileWidth, 17*tileHeight));
		
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
		
		Application.frame.pack();
		Application.frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(refresh++ == 0)
			game.getPacman().updateAnimation();
		else refresh = 0;
		
		game.getPacman().updateMovement(inputKey, tileWidth, tileHeight, game.getMaze());
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		int mazeWidth = game.getMaze().maze[0].length;
		int mazeHeight = game.getMaze().maze.length;
		
		tileWidth = Application.frame.getContentPane().getWidth() / mazeWidth;
		tileHeight = Application.frame.getContentPane().getHeight() / mazeHeight;
		
		for(int h = 0; h < mazeHeight; h++)
			for(int w = 0; w < mazeWidth; w++)
				if(game.getMaze().isWall(new Position(w, h)))
					g.drawImage(Application.images.wallTile, tileWidth*w, tileHeight*h, tileWidth, tileHeight, null, null);
				else if(game.getMaze().isPoint(new Position(w, h)))
					g.drawImage(Application.images.pointTile, tileWidth*w, tileHeight*h, tileWidth, tileHeight, null, null);
				else if(game.getMaze().isPowerPoint(new Position(w, h)))
					g.drawImage(Application.images.powerPointTile, tileWidth*w, tileHeight*h, tileWidth, tileHeight, null, null);
				else g.drawImage(Application.images.backgroundTile, tileWidth*w, tileHeight*h, tileWidth, tileHeight, null, null);
		
				
		g.drawImage(Application.images.sprites.getSubimage(game.getPacman().getAnimation() * 24, game.getPacman().getOrientation() * 24, 24, 24), 
					game.getPacman().getX(), game.getPacman().getY(), tileWidth, tileHeight, null, null);
		
		
		Application.frame.getContentPane().setPreferredSize(new Dimension(tileWidth*mazeWidth, tileHeight*mazeHeight));
		Application.frame.pack();
		
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
	
	public int getTileWidth()
	{
		return tileWidth;
	}
	
	public int getTileHeight()
	{
		return tileHeight;
	}
	
	public void resetInputKey()
	{
		inputKey = 0;
	}

}
