package pacman.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import pacman.GUI.Application;

@SuppressWarnings("serial")
public class ScoresListener extends JPanel implements ActionListener, KeyListener, MouseListener{

	final int MILISSECONDS_TO_REFRESH = 50;
	Timer  timer;
	int refresh = 0;
	int press = 1;
	
	
	public ScoresListener(){

		Application.frame.getContentPane().removeAll();
		timer = new Timer(MILISSECONDS_TO_REFRESH, this);

		Application.frame.setBounds(100, 100, 410, 500);
		Application.frame.getContentPane().add(this, null);

		Application.frame.setBackground(Color.BLACK);
		this.setLayout(null);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.repaint();
		setFocusable(true);
		requestFocus();
		timer.start();
		Application.frame.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g)
	{

		Color[] cor = {Color.RED, Color.ORANGE, Color.CYAN, Color.PINK};

		g.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		g.setColor(Color.CYAN);
		g.drawString("HIGH SCORES", Application.frame.getWidth()/2 - 100, 80);

		g.setFont(new Font("Cooper Black", Font.PLAIN, 14));
		g.setColor(Color.YELLOW);
		g.drawString("RANK", 100, 130);

		g.setFont(new Font("Cooper Black", Font.PLAIN, 14));
		g.setColor(Color.YELLOW);
		g.drawString("SCORE", 250 , 130);


		if(refresh%3 == 0)
		{
			for(int i = 1; i <= 5; ++i)
			{
				Random c = new Random();
				int color = c.nextInt(4);

				if(i == 1){

					g.setFont(new Font("Cooper Black", Font.PLAIN, 14));
					g.setColor(cor[color]);
					g.drawString( i + "ST", 100, 140 + i*30);					

				}else if(i == 2){

					g.setFont(new Font("Cooper Black", Font.PLAIN, 14));
					g.setColor(cor[color]);
					g.drawString(i + "ND", 100, 140 + i*30);

				}else if(i == 3){

					g.setFont(new Font("Cooper Black", Font.PLAIN, 14));
					g.setColor(cor[color]);
					g.drawString(i + "RD", 100, 140 + i*30);

				}else{
					g.setFont(new Font("Cooper Black", Font.PLAIN, 14));
					g.setColor(cor[color]);
					g.drawString(i + "TH", 100, 140 + i*30);
				}

				g.setFont(new Font("Cooper Black", Font.PLAIN, 14));
				g.setColor(cor[color]);
				g.drawString(Integer.toString(Application.scores.get(i-1)), 250, 140 + i*30);
		
			}
		}


		if(press == 1)
		{
			if(refresh%18 == 0)
			{
				g.drawImage(Application.images.background, 30, Application.frame.getHeight() - 140, 341, 64, null, null); 
				press = 0;
			}
		}
		else
		{
			if(refresh%6 == 0)
			{
				g.drawImage(Application.images.scoresAni, 30, Application.frame.getHeight() - 140, 341, 64, null, null); 
				press = 1;
			}
		}



	}

	@Override
	public void keyPressed(KeyEvent arg0) {

		if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
		{
			timer.stop();

			new MainMenu();
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

		refresh++;

		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("ola");
		if(e.getX() >= 30 && e.getX() <= 371){

			if(e.getY() >= Application.frame.getHeight() - 140 && e.getY() <=  Application.frame.getHeight() - 76)
			{
				new MainMenu();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}
