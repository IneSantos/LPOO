package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditionListener implements ActionListener {

	private int ind;
	
	public EditionListener(int i)
	{
		ind = i;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		CustomGame.element = ind;
	}

}
