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

@SuppressWarnings("serial")
public class WinAnimation extends JPanel implements ActionListener, KeyListener, MouseListener{


	final int MILISSECONDS_TO_REFRESH = 100;
	Timer  timer;
	int refresh = 0;
	int width;
	int height;
	int animation;
	int x1 = 0;
	int x2 = width;
	int velx = 20;
	int y = 300;
	boolean passou = false;

	public WinAnimation(){

		Application.frame.getContentPane().removeAll();
		timer = new Timer(MILISSECONDS_TO_REFRESH, this);
		Application.frame.getContentPane().setPreferredSize(new Dimension(400, 500));
		Application.frame.getContentPane().add(this, null);

		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		requestFocus();
		
		timer.start();
		Application.frame.pack();
		Application.frame.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g)
	{

		g.drawImage(Application.images.winbackground, 0, 0, 400, 500, null, null);

		if(refresh%5 == 0)
			g.drawImage(Application.images.win2, 40, 200, 300, 50, null, null);

			

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			new MainMenu();
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

		repaint();


	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse Clicked: ("+e.getX()+", "+e.getY() +")");
		if(e.getX() >= 53 && e.getX() <= 343){
			if(e.getY() >= 263 && e.getY() <= 280){
				new MainMenu();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
