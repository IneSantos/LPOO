package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class ComboListener implements ActionListener{

	public ComboListener(){
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
		int temp = (int)cb.getSelectedIndex();
		if(temp == 0)
			Application.menu.obj.dragonMoves = 0;
		else if (temp == 1)
			Application.menu.obj.dragonMoves = 5;
		else if (temp == 2) 
			Application.menu.obj.dragonMoves = 6;

	}
}
