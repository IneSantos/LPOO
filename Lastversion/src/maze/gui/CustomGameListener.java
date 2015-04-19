package maze.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class CustomGameListener implements ActionListener {
	
	public static JPanel game;
	public static JPanel tools;
	
	public CustomGameListener()
	{
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		createCustom();
	}
	
	public static void createCustom()
	{
		Application.frmLabirinto.getContentPane().removeAll();
		Application.frmLabirinto.getContentPane().setLayout(new FlowLayout());
		
		game = new CustomGame();
		game.setPreferredSize(new Dimension(Tiles.TILE_DIMENSION * Application.menu.obj.dimensaoLabirinto, Tiles.TILE_DIMENSION * Application.menu.obj.dimensaoLabirinto));
		Application.frmLabirinto.getContentPane().add(game);
		game.requestFocus();
		
		tools = new MazeTools();
		tools.setPreferredSize(new Dimension(300, Tiles.TILE_DIMENSION * Application.menu.obj.dimensaoLabirinto));
		Application.frmLabirinto.getContentPane().add(tools);

		Application.frmLabirinto.pack();
		Application.frmLabirinto.setVisible(true); 
	}

}
