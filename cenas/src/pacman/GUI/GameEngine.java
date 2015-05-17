package pacman.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import pacman.logic.Game;

@SuppressWarnings("serial")
public class GameEngine extends JPanel implements ActionListener, KeyListener
{
	final int MILISSECONDS_TO_REFRESH = 45;
	Timer  timer;
	
	Game game = new Game();
	
	
	
	
	public GameEngine()
	{
		timer = new Timer(MILISSECONDS_TO_REFRESH, this);
		timer.start();
		
		this.setPreferredSize(new Dimension(500, 500));
		Application.frame.getContentPane().add(this, BorderLayout.CENTER);
	
		
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
				
		if(orientation == 0 && y >= 0)
			y -= vel;
		else if (orientation == 1)
		{
			if(x >= this.getWidth())
				x = -24;
	
				x += vel;
		}
		else if(orientation == 2 && y <= this.getHeight() - 24)
			y += vel;
		else if(orientation == 3)
		{
			if(x <= -24)
				x = this.getWidth();
	
			x -= vel;
		}
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(background, 0, 0, null);
		g.drawImage(sprites.getSubimage(animation * 24, orientation * 24, 24, 24), x, y, null);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_UP)
			orientation = 0;
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			orientation = 1;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			orientation = 2;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			orientation = 3;
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	

}