package pacman.menus;


import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.Font;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

import pacman.GUI.Application;


public class SettingsListener implements ActionListener {

	public JPanel panel = new JPanel();
	
	
	public SettingsListener(){
	
		
		Application.frame.getContentPane().removeAll();

		Application.frame.setBounds(100, 100, 476, 500);
		
		Application.frame.getContentPane().add(panel);

		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LEVEL:");
		lblNewLabel.setBackground(new Color(255, 255, 102));
		lblNewLabel.setFont(new Font("Cooper Black", Font.PLAIN, 14));
		lblNewLabel.setForeground(new Color(255, 255, 102));
		lblNewLabel.setBounds(25, 90, 76, 32);
		panel.add(lblNewLabel);
		
		JRadioButton level1 = new JRadioButton("LEVEL 1");
		level1.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		level1.setSelected(true);
		level1.setForeground(new Color(255, 255, 102));
		level1.setBackground(new Color(0, 0, 0));
		level1.setBounds(120, 96, 92, 23);
		panel.add(level1);
		
		JRadioButton level2 = new JRadioButton("LEVEL 2");
		level2.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		level2.setForeground(new Color(255, 255, 102));
		level2.setBackground(new Color(0, 0, 0));
		level2.setBounds(214, 96, 102, 23);
		panel.add(level2);
		
		JRadioButton level3 = new JRadioButton("LEVEL 3");
		level3.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		level3.setForeground(new Color(255, 255, 102));
		level3.setBackground(new Color(0, 0, 0));
		level3.setBounds(318, 96, 113, 23);
		panel.add(level3);
		
		JLabel lblGhostsToFight = new JLabel("GHOSTS IN GAME:");
		lblGhostsToFight.setFont(new Font("Cooper Black", Font.PLAIN, 14));
		lblGhostsToFight.setForeground(new Color(255, 255, 102));
		lblGhostsToFight.setBounds(25, 238, 136, 32);
		panel.add(lblGhostsToFight);
		
		JSpinner spinner = new JSpinner();
		spinner.setBackground(new Color(255, 255, 102));
		spinner.setForeground(new Color(0, 0, 0));
		spinner.setModel(new SpinnerNumberModel(4, 4, 12, 1));
		spinner.setBounds(257, 245, 141, 20);
		panel.add(spinner);
		
		JLabel lblNewLabel_2 = new JLabel("NUMBER OF POWER UP:");
		lblNewLabel_2.setFont(new Font("Cooper Black", Font.PLAIN, 14));
		lblNewLabel_2.setForeground(new Color(255, 255, 102));
		lblNewLabel_2.setBounds(25, 317, 202, 32);
		panel.add(lblNewLabel_2);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		spinner_1.setBounds(277, 324, 141, 20);
		panel.add(spinner_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(SettingsListener.class.getResource("/images/Set_menu.jpg")));
		lblNewLabel_3.setBounds(130, 11, 202, 42);
		panel.add(lblNewLabel_3);
		
		JLabel gender = new JLabel("GENDER : ");
		gender.setFont(new Font("Cooper Black", Font.PLAIN, 14));
		gender.setForeground(new Color(255, 255, 102));
		gender.setBounds(25, 161, 102, 32);
		panel.add(gender);
		
		JRadioButton pacman = new JRadioButton("PACMAN");
		pacman.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		pacman.setSelected(true);
		pacman.setForeground(new Color(255, 255, 102));
		pacman.setBackground(new Color(0, 0, 0));
		pacman.setBounds(305, 167, 113, 23);
		panel.add(pacman);
		
		
		JRadioButton pacman1 = new JRadioButton("Ms.PACMAN");
		pacman1.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		pacman1.setSelected(false);
		pacman1.setForeground(new Color(255, 255, 102));
		pacman1.setBackground(new Color(0, 0, 0));
		pacman1.setBounds(160, 167, 113, 23);
		panel.add(pacman1);
		
		
		
		//Application.frame.pack();
		Application.frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
			
		
	}
}
