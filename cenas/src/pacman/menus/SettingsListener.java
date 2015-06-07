package pacman.menus;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import pacman.GUI.Application;
import pacman.logic.Game;


public class SettingsListener extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JPanel panel = new JPanel();
	JButton btnCancel = new JButton("CANCEL");
	JButton btnOk = new JButton("OK");
	JLabel SETTINGS = new JLabel("SETTINGS");
	JLabel GHOSTS = new JLabel("GHOSTS IN GAME:");
	JLabel POWERS = new JLabel("NUMBER OF POWER UP:");
	JLabel GENDER = new JLabel("GENDER : ");
	JSpinner numPowerUps = new JSpinner();
	JRadioButton male = new JRadioButton("PACMAN");
	JRadioButton female = new JRadioButton("Ms.PACMAN");
	JCheckBox red = new JCheckBox("RED");
	JCheckBox pink = new JCheckBox("PINK");
	JCheckBox blue = new JCheckBox("BLUE");
	JCheckBox orange = new JCheckBox("ORANGE");
	
	public SettingsListener(){

		ButtonGroup group1 = new ButtonGroup();

		Application.frame.getContentPane().removeAll();
		Application.frame.setBounds(100, 100, 476, 500);
		Application.frame.getContentPane().add(panel);

		panel.setBackground(Color.BLACK);
		panel.setLayout(null);


		SETTINGS.setBackground(new Color(255, 255, 102));
		SETTINGS.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		SETTINGS.setForeground(new Color(255, 255, 102));
		SETTINGS.setBounds(125, 50, 300, 80);
		panel.add(SETTINGS);

		
		GHOSTS.setFont(new Font("Cooper Black", Font.PLAIN, 14));
		GHOSTS.setForeground(new Color(255, 255, 102));
		GHOSTS.setBounds(25, 254, 136, 42);
		panel.add(GHOSTS);


		POWERS.setFont(new Font("Cooper Black", Font.PLAIN, 14));
		POWERS.setForeground(new Color(255, 255, 102));
		POWERS.setBounds(25, 344, 202, 42);
		panel.add(POWERS);


		numPowerUps.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		numPowerUps.setBounds(283, 355, 136, 23);
		panel.add(numPowerUps);



		GENDER.setFont(new Font("Cooper Black", Font.PLAIN, 14));
		GENDER.setForeground(new Color(255, 255, 102));
		GENDER.setBounds(25, 186, 102, 42);
		panel.add(GENDER);

		
		male.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		male.setSelected(true);
		male.setForeground(new Color(255, 255, 102));
		male.setBackground(new Color(0, 0, 0));
		male.setBounds(306, 197, 113, 23);
		group1.add(male);
		panel.add(male);



		female.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		female.setSelected(false);
		female.setForeground(new Color(255, 255, 102));
		female.setBackground(new Color(0, 0, 0));
		female.setBounds(151, 192, 122, 32);
		group1.add(female);
		panel.add(female);


		red.setSelected(Game.redFlag);
		red.setBackground(Color.BLACK);
		red.setForeground(Color.YELLOW);
		red.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		red.setBounds(202, 265, 56, 23);
		panel.add(red);


		
		pink.setSelected(Game.pinkFlag);
		pink.setForeground(Color.YELLOW);
		pink.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		pink.setBackground(Color.BLACK);
		pink.setBounds(337, 265, 56, 23);
		panel.add(pink);


		blue.setSelected(Game.blueFlag);
		blue.setForeground(Color.YELLOW);
		blue.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		blue.setBackground(Color.BLACK);
		blue.setBounds(202, 305, 71, 23);
		panel.add(blue);

	
		orange.setSelected(Game.orangeFlag);
		orange.setForeground(Color.YELLOW);
		orange.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		orange.setBackground(Color.BLACK);
		orange.setBounds(341, 305, 102, 23);
		panel.add(orange);


		btnOk.setBackground(Color.LIGHT_GRAY);
		btnOk.setForeground(Color.YELLOW);
		btnOk.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		btnOk.setBounds(/*122*/Application.frame.getWidth()-300, /*400*/Application.frame.getHeight() -90, 85, 25);
		btnOk.addActionListener(this);
		panel.add(btnOk);


		btnCancel.setForeground(Color.YELLOW);
		btnCancel.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		btnCancel.setBackground(Color.LIGHT_GRAY);
		btnCancel.setBounds(Application.frame.getWidth()-200, Application.frame.getHeight() -90, 85, 25);
		btnCancel.addActionListener(this);
		panel.add(btnCancel);

		
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{

		if(e.getSource() == btnOk){
			
			Game.redFlag = red.isSelected();
			Game.pinkFlag = pink.isSelected();
			Game.blueFlag = blue.isSelected();
			Game.orangeFlag = orange.isSelected();
			Game.male = male.isSelected();
			Game.powerUps = (Integer)numPowerUps.getValue();
			
			new MainMenu();
		}
		else if(e.getSource() == btnCancel)
			new MainMenu();
	}
}
