package maze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Yes_Settings extends JDialog implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Yes_Settings dialog = new Yes_Settings();
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	JButton okButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");

	
	public static Yes_Settings getDialog() {
		return dialog; 
	}



	public Yes_Settings() 
	{
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblDimensoDoLabirinto = new JLabel("Dimens\u00E3o do Labirinto");
			lblDimensoDoLabirinto.setBounds(57, 90, 149, 26);
			contentPanel.add(lblDimensoDoLabirinto);
		}
		{
			JLabel lblAconselhaseQueSeja = new JLabel("aconselha-se que seja maior que 7");
			lblAconselhaseQueSeja.setBackground(Color.BLACK);
			lblAconselhaseQueSeja.setForeground(Color.BLACK);
			lblAconselhaseQueSeja.setBounds(57, 137, 237, 14);
			contentPanel.add(lblAconselhaseQueSeja);
		}
		{
			textField = new JTextField();
			textField.setText("10");
			textField.setColumns(10);
			textField.setBounds(212, 90, 130, 36);
			contentPanel.add(textField);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon("C:/Users/Ines/workspace/workspace java IDE/Jogo do Labirinto/src/maze/gui/settings.jpg"));
			label.setBounds(57, 11, 269, 41);
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
				
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		dialog.setVisible(true);
		
		if(arg0.getSource() == okButton)
		{
			try
			{
				Application.menu.obj.dimensaoLabirinto = Integer.parseInt(this.textField.getText());
			} 
			catch (NumberFormatException e1) 
			{
			}
			StartMazze.generateMaze = 1;
			
			dialog.setVisible(false);
			dialog.dispose();
		}
		else if(arg0.getSource() == cancelButton)
		{
			
			Application.menu.obj.dimensaoLabirinto = 10;
			dialog.setVisible(false);
			dialog.dispose();
			
		}
	}
	
}
