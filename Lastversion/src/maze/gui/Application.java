package maze.gui;

import javax.swing.JFrame;

public class Application {

	public static JFrame frmLabirinto;
	public static MainMenu menu;
	
	public static void main(String[] args) 
	{
	
		new Tiles();
					
		frmLabirinto = new JFrame();
		menu = new MainMenu();
		frmLabirinto.setVisible(true);
			
	}
	
}
