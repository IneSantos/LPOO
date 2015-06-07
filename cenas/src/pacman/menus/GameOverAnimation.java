package pacman.menus;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import pacman.GUI.Application;
import pacman.GUI.GameEngine;

@SuppressWarnings("serial")
public class GameOverAnimation extends JPanel implements ActionListener, KeyListener, MouseListener{

	final int MILISSECONDS_TO_REFRESH = 20;
	Timer  timer;
	int refresh = 0;
	int animation;
	int pacx = 140;
	int velx = 10;
	int orientation = 1;
	int press=1;

	public GameOverAnimation(){

		Application.frame.getContentPane().removeAll();
		timer = new Timer(MILISSECONDS_TO_REFRESH, this);
		Application.frame.getContentPane().setPreferredSize(new Dimension(400, 500));
		Application.frame.getContentPane().add(this);


		timer.start();
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		requestFocus();

		Application.frame.pack();
	}

	@Override
	public void paintComponent(Graphics g)
	{

		g.drawImage(Application.images.background1, 0, 0, 400, 500, null, null);


		if(press == 1)
			g.drawImage(Application.images.background, 30, 250, 330, 50, null, null);
		else g.drawImage(Application.images.background2, 30, 250, 330, 50, null, null);

		if(refresh%50 == 0)
		{
			if(press == 1)
			{
				if(refresh%10 == 0)
				press = 0;
			}
			else press = 1;
		}



		g.drawImage(Application.images.sprites.getSubimage(animation*24, orientation * 24, 24, 24), 
				pacx, 300,  30, 30, null);

		if(velx > 0)
		{
			g.drawImage(Application.images.sprites.getSubimage(4*24, orientation * 24, 24, 24), 
					pacx - 35, 300,  30, 30, null);

			g.drawImage(Application.images.sprites.getSubimage(6*24, orientation * 24, 24, 24), 
					pacx - 35*2, 300,  30, 30, null);

			g.drawImage(Application.images.sprites.getSubimage(8*24, orientation * 24, 24, 24), 
					pacx - 35*3, 300,  30, 30, null);

			g.drawImage(Application.images.sprites.getSubimage(10*24, orientation * 24, 24, 24), 
					pacx - 35*4, 300,  30, 30, null);
		}
		else 
		{
			g.drawImage(Application.images.sprites.getSubimage(4*24, orientation * 24, 24, 24), 
					pacx + 35, 300,  30, 30, null);

			g.drawImage(Application.images.sprites.getSubimage(6*24, orientation * 24, 24, 24), 
					pacx + 35*2, 300,  30, 30, null);

			g.drawImage(Application.images.sprites.getSubimage(8*24, orientation * 24, 24, 24), 
					pacx + 35*3, 300,  30, 30, null);

			g.drawImage(Application.images.sprites.getSubimage(10*24, orientation * 24, 24, 24), 
					pacx + 35*4, 300,  30, 30, null);
		}
	}

	public void updateMovement()
	{
		if(pacx <= -140 || pacx > 540)
		{
			velx *= -1;
			if(orientation == 1)
				orientation = 3;
			else orientation = 1;
		}

		pacx += velx;

	}

	public void updateAnimation()
	{
		animation++;

		if (animation > 3)
			animation = 0;

	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			new GameEngine();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {


	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		refresh++;

		if(refresh%2 == 0)
			updateAnimation();

		updateMovement();

		repaint();


	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getX() >= 53 && e.getX() <= 343){
			if(e.getY() >= 263 && e.getY() <= 280){
				new MainMenu();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
}
