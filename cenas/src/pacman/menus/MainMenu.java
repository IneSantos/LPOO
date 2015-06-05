package pacman.menus;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import pacman.GUI.Application;
import pacman.GUI.GameEngine;
import pacman.GUI.Images;
import pacman.logic.Position;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

@SuppressWarnings("serial")
public class MainMenu extends JPanel implements KeyListener , ActionListener, MouseListener{

	final int MILISSECONDS_TO_REFRESH = 20;
	Timer  timer;
	String[] opcoes = {"Start", "Settings" , "Exit"};
	int currOption;
	int selected ;
	Color corCurrOption = Color.RED;
	Color corTexto = Color.YELLOW;


	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/


	public MainMenu() {
		//setBounds(232, 5, 462, 435);
			initialize();
	}


	private void initialize() {

		timer = new Timer(MILISSECONDS_TO_REFRESH, this);
		timer.start();

		Application.frame.setVisible(true);
		Application.frame.setBounds(100, 100, 400, 500);
		Application.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Application.frame.getContentPane().add(this);
		this.addMouseListener(this);

		this.repaint();
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(new Images().gif, 0, 0, Application.frame.getWidth(), Application.frame.getHeight(), null, null);
		for(int i = 0; i < opcoes.length ; ++i){
			if(i == currOption){
				g.setColor(corCurrOption);
			}
			else
				g.setColor(corTexto);

			g.setFont(new Font("Cooper Black", Font.PLAIN, 25));
			
			if(i == currOption){
				g.drawString("* "+opcoes[i],Application.frame.getWidth()/2 , Application.frame.getHeight()/2 - 35 + i*35*2);
			}
				else g.drawString(opcoes[i],Application.frame.getWidth()/2 , Application.frame.getHeight()/2 - 35 + i*35*2);
		}

	}


	private void selected(){
		if(selected == 0){
			new GameEngine();
		}
		else 
			if(selected == 1){
				new SettingsListener();
			}
			else if(selected == 2){
				System.exit(0);
			}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_UP && currOption != 0){
			this.currOption--;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN && currOption != (opcoes.length - 1)){
			this.currOption++;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
			this.selected = currOption;
			selected();
		}

	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {

		repaint();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		 System.out.println("Mouse Clicked: ("+e.getX()+", "+e.getY() +")");
		 
		 if(e.getX() >= 200 && e.getX() <= 300){

				if(e.getY() >= 197 && e.getY() <= 215){
					new GameEngine();
				}
				else if(e.getY() >= 265 && e.getY() <= 285){
					new SettingsListener();
				}
				else if(e.getY() >= 337 && e.getY() <= 356){
					//System.exit(0);
					new GameOver();
				}
			}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("entrou");
		
		java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
		Cursor a = toolkit.createCustomCursor(new Images().mouse , new Point(this.getX(),this.getY()), "img");
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
}
