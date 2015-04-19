package maze.gui;

import javax.swing.*;

@SuppressWarnings("serial")
public class AskForRestart extends JOptionPane 
{	
	public AskForRestart(JPanel game)
	{
		int n = JOptionPane.showConfirmDialog(game,
			    "Do you want to start a new game?",
			    "New Game",
			    JOptionPane.YES_NO_OPTION);
		
		if(n == 0)
			((StartMazze)game).restart();
		else game.requestFocus();
	}
	
}
