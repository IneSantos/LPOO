package maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PlayListener  implements ActionListener {

	public static JPanel panel;
	public static JPanel options;
	
	int cenas; 

	public static void startGame()
	{
		Application.frmLabirinto.getContentPane().removeAll();
		Application.frmLabirinto.getContentPane().setLayout(new BorderLayout());
		Application.frmLabirinto.setResizable(true);

		JOptionPane.showMessageDialog(Application.frmLabirinto, "Prepare yourself! The adventure is about to begin! ;) ");
		JOptionPane.showMessageDialog(Application.frmLabirinto, "OK?");
		
		panel = new StartMazze();
		panel.setPreferredSize(new Dimension(500, 500));
		Application.frmLabirinto.getContentPane().add(panel, BorderLayout.CENTER);
		panel.requestFocus();

		options = new OptionPanel(panel);
		options.setPreferredSize(new Dimension(150, 500));
		Application.frmLabirinto.getContentPane().add(options, BorderLayout.EAST);

		

		

		
		Application.frmLabirinto.pack();
		Application.frmLabirinto.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		startGame();
	}
}
