package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class RestartGame implements ActionListener 
{
	private JPanel game;
		
	public RestartGame(JPanel game) 
	{
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		new AskForRestart(game);
		
	}

}
