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
	final int MILISSECONDS_TO_REFRESH = 45;
	Timer  timer;
	
	int inputKey = 0;
	
	Game game = new Game();
	
	
	public GameEngine()
	{
		timer = new Timer(MILISSECONDS_TO_REFRESH, this);
		timer.start();
		
		this.setPreferredSize(new Dimension(game.getMaze().maze[0].length*25, game.getMaze().maze.length*25));
		Application.frame.getContentPane().add(this, BorderLayout.CENTER);

		game.getPacman().setPosition(new Position(13*25, 17*25));
		
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
		
		Application.frame.pack();
		Application.frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		game.getPacman().updateAnimation();
				
		game.getPacman().updateMovement(Application.frame.getContentPane().getWidth(), Application.frame.getContentPane().getHeight(), game.getMaze());
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		int mazeWidth = game.getMaze().maze[0].length;
		int mazeHeight = game.getMaze().maze.length;
		
		int tileWidth = Application.frame.getContentPane().getWidth() / mazeWidth;
		int tileHeight = Application.frame.getContentPane().getHeight() / mazeHeight;
		
		for(int h = 0; h < mazeHeight; h++)
			for(int w = 0; w < mazeWidth; w++)
				if(game.getMaze().isWall(new Position(w, h)))
					g.drawImage(Application.images.wallTile, tileWidth*w, tileHeight*h, tileWidth, tileHeight, null, null);
				else if(game.getMaze().isPoint(new Position(w, h)))
					g.drawImage(Application.images.pointTile, tileWidth*w, tileHeight*h, tileWidth, tileHeight, null, null);
				else if(game.getMaze().isPowerPoint(new Position(w, h)))
					g.drawImage(Application.images.powerPointTile, tileWidth*w, tileHeight*h, tileWidth, tileHeight, null, null);
				else g.drawImage(Application.images.backgroundTile, tileWidth*w, tileHeight*h, tileWidth, tileHeight, null, null);
				
		g.drawImage(Application.images.sprites.getSubimage(game.getPacman().getAnimation() * 24, 
					game.getPacman().getOrientation() * 24, 24, 24), game.getPacman().getX(), game.getPacman().getY(), null);
		
		Application.frame.getContentPane().setPreferredSize(new Dimension(tileWidth*mazeWidth, tileHeight*mazeHeight));
		Application.frame.pack();
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int mazeWidth = game.getMaze().maze[0].length;
		int mazeHeight = game.getMaze().maze.length;
		
		int tileWidth = Application.frame.getContentPane().getWidth() / mazeWidth;
		int tileHeight = Application.frame.getContentPane().getHeight() / mazeHeight;
		
		Position p = game.getPacman().getTilePosition(tileWidth, tileHeight);
		
		if(e.getKeyCode() == KeyEvent.VK_UP && game.getPacman().moveUp(tileWidth, tileHeight, game.getMaze()))
			game.getPacman().setOrientation(0);
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT && !game.getMaze().isWall(game.getPacman().getTilePosition(tileHeight, tileHeight)))
			game.getPacman().setOrientation(1);
		else if(e.getKeyCode() == KeyEvent.VK_DOWN && !game.getMaze().isWall(game.getPacman().getTilePosition(tileHeight, tileHeight)))
			game.getPacman().setOrientation(2);
		else if(e.getKeyCode() == KeyEvent.VK_LEFT && !game.getMaze().isWall(game.getPacman().getTilePosition(tileHeight, tileHeight)))
			game.getPacman().setOrientation(3);
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	

}
