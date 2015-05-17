package pacman.GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Application
{
	public static JFrame frame;
	static Images images = new Images();
	
	public static void main(String[] args) 
	{
		frame = new JFrame("PacMano, m'putz");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		new GameEngine();
		return;
	}
}
