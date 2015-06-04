package pacman.GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import pacman.menus.MainMenu;

public class Application
{
	public static JFrame frame;
	static Images images = new Images();
	
	public static void main(String[] args) 
	{
		frame = new JFrame("PacMano, m'putz");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setResizable(false);
		
		new MainMenu();
		return;
	}
}
