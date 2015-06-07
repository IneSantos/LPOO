package pacman.menus;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import pacman.GUI.Application;
import pacman.GUI.GameEngine;

@SuppressWarnings("serial")
public class MainMenu extends JPanel implements KeyListener , ActionListener, MouseListener, MouseMotionListener{

	final int MILISSECONDS_TO_REFRESH = 20;
	Timer  timer;
	String[] opcoes = {"Start", "Settings", "Scores" , "Exit"};
	int currOption = 5;
	Color corCurrOption = Color.RED;
	Color corTexto = Color.YELLOW;


	public MainMenu() 
	{
		Application.frame.getContentPane().removeAll();
		Application.frame.setLocation((Application.screenSize.width - 300)/ 2, 100);
		Application.frame.getContentPane().setPreferredSize(new Dimension(300, 400));
		Application.frame.getContentPane().add(this);
		Application.frame.pack();
		addMouseListener(this);
		addKeyListener(this);
		addMouseMotionListener(this);

		timer = new Timer(MILISSECONDS_TO_REFRESH, this);
		timer.start();

		setFocusable(true);
		requestFocus();
	}


	@Override
	public void paintComponent(Graphics g)
	{

		g.drawImage(Application.images.main_menuImage, 0, 0, Application.frame.getWidth(), Application.frame.getHeight(), null, null);

		for(int i = 0; i < opcoes.length ; ++i)
		{
			g.setFont(new Font("Cooper Black", Font.PLAIN, 25));

			if(i == currOption)
			{
				g.setColor(corCurrOption);
				g.drawString("* "+opcoes[i],Application.frame.getWidth()/3 , Application.frame.getHeight()/2 - 35 + i*40);
			}
			else			
			{
				g.setColor(corTexto);
				g.drawString(opcoes[i],Application.frame.getWidth()/3 , Application.frame.getHeight()/2 - 35 + i*40);
			}
		}

	}


	private void selected()
	{
		if(currOption == 0)
			new GameEngine();
		else if(currOption == 1)
			new SettingsListener();
		else if(currOption == 2)
			new ScoresListener();
		else if(currOption == 3)
			new ExitListener();
	}


	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		if(arg0.getKeyCode() == KeyEvent.VK_UP && currOption != 0){
			if(currOption == 5)
				currOption = 3;
			else currOption--;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN && currOption != (opcoes.length - 1))
		{
			if(currOption == 5)
				currOption = 1;
			else currOption++;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
			selected();
		}


	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {


	}


	@Override
	public void actionPerformed(ActionEvent arg0) {

		repaint();
	}


	@Override
	public void mouseClicked(MouseEvent e) 
	{

		if(e.getX() >= 100 && e.getX() <= 200)
		{

			if(e.getY() >= 160 && e.getY() <= 175){
				new GameEngine();
			}
			else if(e.getY() >= 200 && e.getY() <= 215){
				new SettingsListener();
			}
			else if(e.getY() >= 240 && e.getY() <= 260){
				new ScoresListener();
			}
			else if(e.getY() >= 280 && e.getY() <= 300){
				new ExitListener();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
		Cursor a = toolkit.createCustomCursor(Application.images.mouse , new Point(this.getX(),this.getY()), "img");
		Application.frame.setCursor (a);


	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseDragged(MouseEvent arg0) {

	}



	@Override
	public void mouseMoved(MouseEvent e) {
		if(e.getX() >= 100 && e.getX() <= 200)
		{

			if(e.getY() >= 160 && e.getY() <= 175){
				currOption = 0;
			}
			else if(e.getY() >= 200 && e.getY() <= 215){
				currOption = 1;
			}
			else if(e.getY() >= 240 && e.getY() <= 260){
				currOption = 2;
			}
			else if(e.getY() >= 280 && e.getY() <= 300){
				currOption = 3;
			}


		}
		else currOption = 5;
	}
}

