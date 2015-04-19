package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SettingsListener extends Settings implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public SettingsListener(){
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Settings.getDialog().setVisible(true);
	}

	
}
