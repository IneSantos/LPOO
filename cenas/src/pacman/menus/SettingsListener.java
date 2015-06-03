package pacman.menus;


import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

import pacman.GUI.Application;


public class SettingsListener implements ActionListener {

	public JPanel panel = new JPanel();
	private JTextField textField;
	
	
	public SettingsListener(){
	
		
		Application.frame.getContentPane().removeAll();

		Application.frame.setBounds(100, 100, 476, 340);
		
		Application.frame.getContentPane().add(panel);

		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		
		JSlider slider = new JSlider();
		slider.setToolTipText("\r\n");
		slider.setSnapToTicks(true);
		slider.setBackground(new Color(255, 255, 102));
		slider.setForeground(new Color(0, 0, 0));
		slider.setValue(1);
		slider.setPaintLabels(true);
		slider.setMaximum(5);
		slider.setMinimum(1);
		slider.setBounds(147, 72, 247, 26);
		panel.add(slider);
		
		JLabel lblNewLabel = new JLabel("LEVEL:");
		lblNewLabel.setBackground(new Color(255, 255, 102));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(new Color(255, 255, 102));
		lblNewLabel.setBounds(28, 66, 76, 32);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("MAZE DIMENTION:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(new Color(255, 255, 102));
		lblNewLabel_1.setBounds(23, 150, 151, 42);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setBackground(new Color(255, 255, 102));
		textField.setBounds(202, 160, 141, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblGhostsToFight = new JLabel("GHOSTS IN GAME:");
		lblGhostsToFight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGhostsToFight.setForeground(new Color(255, 255, 102));
		lblGhostsToFight.setBounds(24, 203, 136, 42);
		panel.add(lblGhostsToFight);
		
		JSpinner spinner = new JSpinner();
		spinner.setBackground(new Color(255, 255, 102));
		spinner.setForeground(new Color(0, 0, 0));
		spinner.setModel(new SpinnerNumberModel(4, 4, 12, 1));
		spinner.setBounds(202, 216, 141, 20);
		panel.add(spinner);
		
		JLabel lblNewLabel_2 = new JLabel("NUMBER OF POWER UP:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setForeground(new Color(255, 255, 102));
		lblNewLabel_2.setBounds(23, 256, 171, 32);
		panel.add(lblNewLabel_2);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		spinner_1.setBounds(202, 264, 141, 20);
		panel.add(spinner_1);
		
		JLabel lblGenerateMaze = new JLabel("GENERATE MAZE : ");
		lblGenerateMaze.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGenerateMaze.setForeground(new Color(255, 255, 102));
		lblGenerateMaze.setBackground(new Color(255, 255, 153));
		lblGenerateMaze.setBounds(23, 113, 163, 26);
		panel.add(lblGenerateMaze);
		
		JRadioButton rdbtnYes = new JRadioButton("YES");
		rdbtnYes.setBackground(new Color(0, 0, 0));
		rdbtnYes.setForeground(new Color(255, 255, 102));
		rdbtnYes.setBounds(200, 117, 62, 23);
		panel.add(rdbtnYes);
		
		JRadioButton rdbtnNo = new JRadioButton("NO");
		rdbtnNo.setSelected(true);
		rdbtnNo.setForeground(new Color(255, 255, 102));
		rdbtnNo.setBackground(new Color(0, 0, 0));
		rdbtnNo.setBounds(293, 117, 50, 23);
		panel.add(rdbtnNo);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(SettingsListener.class.getResource("/images/Set_menu.jpg")));
		lblNewLabel_3.setBounds(130, 11, 202, 42);
		panel.add(lblNewLabel_3);
		
		//Application.frame.pack();
		Application.frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
			
		
	}
}
