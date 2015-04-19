package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NO_Settings implements ActionListener {

	public NO_Settings()
	{

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{

		StartMazze.generateMaze = 0;
		Application.menu.obj.dimensaoLabirinto = 10;
	}

}
