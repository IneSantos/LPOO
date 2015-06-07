package pacman.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import pacman.menus.MainMenu;
import resources.images.Images;
import resources.sounds.Sounds;

public class Application
{
	public static Dimension screenSize;
	public static JFrame frame;
	public static Images images = new Images();
	public static Sounds sounds = new Sounds();
	
	public static void main(String[] args) 
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame = new JFrame("PacMano, m'putz");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setResizable(false);
		
		new MainMenu();
		return;
	}
}
