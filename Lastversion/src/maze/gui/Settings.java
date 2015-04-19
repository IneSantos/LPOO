package maze.gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import maze.logic.Jogo;

@SuppressWarnings("serial")
public class Settings extends JDialog implements ActionListener
{
	static int old_key;
	private static Settings dialog = new Settings();
	private final JPanel contentPanel = new JPanel();
	private JSpinner spinner = new JSpinner();
	JButton okButton = new JButton("OK"); 
	JButton cancelButton = new JButton("Cancel");

	
	JButton btnUp = new JButton(KeyEvent.getKeyText(Controls.up));
	JButton btnDown = new JButton(KeyEvent.getKeyText(Controls.down));
	JButton btnLeft = new JButton(KeyEvent.getKeyText(Controls.left));
	JButton btnRight = new JButton(KeyEvent.getKeyText(Controls.right));
	
	
	JButton button = new JButton(KeyEvent.getKeyText(Controls.shootUp));
	JButton button_1 = new JButton(KeyEvent.getKeyText(Controls.shootDown));
	JButton button_2 = new JButton(KeyEvent.getKeyText(Controls.shootLeft));
	JButton button_3 = new JButton(KeyEvent.getKeyText(Controls.shootRight));
	
	
	public static Settings getDialog() 
	{
		return dialog;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Settings() 
	{
		ButtonGroup group = new ButtonGroup();
		setBounds(100, 100, 450, 588);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 549);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblDesejaCriarUm = new JLabel("Deseja criar um labirinto?");
		lblDesejaCriarUm.setBounds(56, 100, 149, 26);
		contentPanel.add(lblDesejaCriarUm);
		
		JRadioButton rdbtnYes = new JRadioButton("SIM\r\n", false);
		rdbtnYes.setBounds(237, 102, 55, 23);
		contentPanel.add(rdbtnYes);
		group.add(rdbtnYes);
		rdbtnYes.addActionListener(new Yes_Settings());

		JRadioButton rdbtnNo = new JRadioButton("N\u00C3O\r\n",true);
		rdbtnNo.setBounds(312, 102, 54, 23);
		contentPanel.add(rdbtnNo);
		group.add(rdbtnNo);
		rdbtnNo.addActionListener(new NO_Settings());
		
		
		JLabel lblNewLabel = new JLabel("Movimentos Dragao");
		lblNewLabel.setBounds(55, 152, 120, 26);
		contentPanel.add(lblNewLabel);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Parado", "Aleat\u00F3rio", "Aleat\u00F3rio + Drag\u00E3o adormecer"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(185, 155, 181, 20);
		contentPanel.add(comboBox);
		comboBox.addActionListener(new ComboListener());
		
		JLabel lblNDrages = new JLabel("N\u00BA Drag\u00F5es em jogo");
		lblNDrages.setBounds(55, 189, 120, 26);
		contentPanel.add(lblNDrages);
		
		
		spinner.setModel(new SpinnerNumberModel(0, 0, Jogo.MAX_DRAGONS, 1));
		spinner.setBounds(185, 192, 98, 20);
		contentPanel.add(spinner);
		
		
		JLabel lblNewLabel_1 = new JLabel("Alterar Comandos do jogo:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(55, 238, 196, 31);
		contentPanel.add(lblNewLabel_1);
		
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 503, 435, 35);
		contentPanel.add(buttonPane);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(this);
			
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
				
		btnUp.setFont(new Font("Tahoma", Font.PLAIN, 10));	
		btnUp.setBounds(245, 245, 121, 20);
		contentPanel.add(btnUp);
		btnUp.addActionListener(this);
		btnUp.setText("UP - " + KeyEvent.getKeyText(Controls.up));		
				
		btnDown.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnDown.setBounds(245, 265, 121, 20);
		contentPanel.add(btnDown);
		btnDown.addActionListener(this);
		btnDown.setText("DOWN - " + KeyEvent.getKeyText(Controls.down));
			
		btnLeft.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLeft.addActionListener(this);
		btnLeft.setBounds(245, 285, 121, 20);
		contentPanel.add(btnLeft);
		btnLeft.setText("LEFT - " + KeyEvent.getKeyText(Controls.left));
				
		btnRight.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRight.addActionListener(this);
		btnRight.setText("RIGHT - " + KeyEvent.getKeyText(Controls.right));
		btnRight.setBounds(245, 305, 121, 20);
		contentPanel.add(btnRight);
				
		JLabel lblAlterarComandosDe = new JLabel("Alterar Comandos de disparo:");
		lblAlterarComandosDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlterarComandosDe.setBounds(55, 365, 196, 31);
		contentPanel.add(lblAlterarComandosDe);
				
		button.addActionListener(this);
		button.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button.setText("Shoot UP - " + KeyEvent.getKeyText(Controls.shootUp));
		button.setBounds(245, 369, 121, 20);
		contentPanel.add(button);
				
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1.addActionListener(this);
		button_1.setText("Shoot DOWN - " + KeyEvent.getKeyText(Controls.shootDown));
		button_1.setBounds(245, 390, 121, 20);
		contentPanel.add(button_1);
				
				
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_2.setText("Shoot LEFT - " + KeyEvent.getKeyText(Controls.shootLeft));
		button_2.addActionListener(this);
		button_2.setBounds(245, 411, 120, 20);
		contentPanel.add(button_2);
				
				
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_3.setText("Shoot RIGHT - " + KeyEvent.getKeyText(Controls.shootRight));
		button_3.addActionListener(this);
		button_3.setBounds(245, 432, 121, 20);
		contentPanel.add(button_3);
		cancelButton.addActionListener(this);
			 
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource() == btnUp)
			new ChangeKeys(this, "up", btnUp);
		else if(arg0.getSource() == btnDown)
			new ChangeKeys(this, "down", btnDown);
		else if(arg0.getSource() == btnLeft)
			new ChangeKeys(this, "left", btnLeft);
		else if(arg0.getSource() == btnRight)
			new ChangeKeys(this, "right", btnRight);
		else if(arg0.getSource() == button)
			new ChangeKeys(this, "shootup", button);
		else if(arg0.getSource() == button_1)
			new ChangeKeys(this, "shootdown", button_1);
		else if(arg0.getSource() == button_2)
			new ChangeKeys(this, "shootleft", button_2);
		else if(arg0.getSource() == button_3)
			new ChangeKeys(this, "shootright", button_3);
		else if(arg0.getSource() == okButton)
		{
			Application.menu.obj.numDragoes = (int) this.spinner.getModel().getValue();
			dialog.setVisible(false);
			dialog.dispose();
		}
		else if(arg0.getSource() == cancelButton)
		{
			Application.menu.obj.numDragoes = 1;
			Application.menu.obj.dimensaoLabirinto = 10;
			dialog.setVisible(false);
			dialog.dispose();
		}
	}

	

	
}
