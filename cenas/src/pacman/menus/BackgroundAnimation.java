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
public class BackgroundAnimation extends JPanel implements ActionListener, KeyListener, MouseListener{


	final int MILISSECONDS_TO_REFRESH = 10;
	Timer  timer;
	int refresh = 0;
	int width;
	int height;
	int animation;
	int pacx = 0;
	int ghostx = -60;
	int velx = 20;
	int y = 300;
	boolean passou = false;

	public BackgroundAnimation(int width, int height){

		Application.frame.getContentPane().removeAll();
		timer = new Timer(MILISSECONDS_TO_REFRESH, this);
		this.setPreferredSize(new Dimension(width, height));
		Application.frame.getContentPane().add(this, null);

		this.width = width;
		this.height = height;
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		requestFocus();

		Application.frame.pack();
		Application.frame.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g)
	{

		g.drawImage(Application.images.background1, 0, 0, this.width, this.height, null, null);

		if(refresh%5 == 0)
			g.drawImage(Application.images.background2, 30, 250, 329, 50, null, null);

			if( pacx*24 + 24   <= this.width*24 && (ghostx - 30*3)*24 + 24  <= this.width*24 && !passou){
				passou = false;
				g.drawImage(Application.images.sprites.getSubimage(animation * 24, 1*24, 24, 24), 
						pacx,y, 30, 30, null,  null);

				g.drawImage(Application.images.sprites.getSubimage(animation *24 + 4 * 24, 1*24, 24, 24), 
						ghostx, y, 30, 30, null, null);

				g.drawImage(Application.images.sprites.getSubimage(animation *24 + 6 * 24, 1*24, 24, 24), 
						ghostx-30, y, 30, 30, null, null);

				g.drawImage(Application.images.sprites.getSubimage(animation *24 + 8 * 24, 1*24, 24, 24), 
						ghostx-30*2, y, 30, 30, null, null);

				g.drawImage(Application.images.sprites.getSubimage(animation *24 + 10 * 24, 1*24, 24, 24), 
						ghostx-30*3, y, 30, 30, null, null);
			}
			else{
				
				passou = true;
				System.out.println("Pacman x :" + pacx + " Ghost 1 :" + ghostx + "passou " + passou);

				g.drawImage(Application.images.sprites.getSubimage(animation *24 + 4 * 24, 3*24, 24, 24), 
						ghostx, y, 30, 30, null, null);

				g.drawImage(Application.images.sprites.getSubimage(animation *24 + 6 * 24, 3*24, 24, 24), 
						ghostx-30, y, 30, 30, null, null);

				g.drawImage(Application.images.sprites.getSubimage(animation *24 + 8 * 24, 3*24, 24, 24), 
						ghostx-30*2, y, 30, 30, null, null);

				g.drawImage(Application.images.sprites.getSubimage(animation *24 + 10 * 24, 3*24, 24, 24), 
						ghostx-30*3, y, 30, 30, null, null);

				g.drawImage(Application.images.sprites.getSubimage(animation * 24, 3*24, 24, 24), 
						pacx,y, 30, 30, null,  null);

			}
		
		timer.start();

	}

	public void updateMovement()
	{
		if(pacx + 24   == this.width){
			pacx = this.width*60;
		}
		if((ghostx - 30) +24*3  == this.width){
			ghostx = this.width;
		}
		if(!passou) {
			pacx += velx;
			ghostx += velx;
		}
		else{
			pacx -= velx;
			ghostx -= velx;
		}

		
		
	}

	public int updateAnimation()
	{
		animation++;

		if (animation > 3)
			animation = 0;

		return animation;
	}

	public int updateAnimationGhost()
	{
		animation++;

		if (animation > 1)
			animation = 0;

		return animation;
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

		if(refresh++ > 4)
			refresh = 0;

		if(refresh%2 == 0)
			updateAnimation();
		updateAnimationGhost();

		updateMovement();

		repaint();


	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse Clicked: ("+e.getX()+", "+e.getY() +")");
		if(e.getX() >= 53 && e.getX() <= 343){
			if(e.getY() >= 263 && e.getY() <= 280){
				new GameEngine();
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
