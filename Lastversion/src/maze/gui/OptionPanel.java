package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class OptionPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JButton exit;
	private JButton newGame;
	private JButton save;
	private JButton load;
	public static JLabel lblNewLabel;
	public static JLabel lblProtegido;
	public static JLabel lblArmado;

	public OptionPanel(JPanel game)
	{
		exit = new JButton("EXIT");
		exit.addActionListener(this);

		newGame = new JButton("NEW GAME");
		newGame.addActionListener(new RestartGame(game));

		save = new JButton("SAVE");
		save.addActionListener(new Save("save"));

		load = new JButton("LOAD");
		load.addActionListener(new Load("save"));

		this.add(exit);
		this.add(newGame);
		this.add(save);
		this.add(load);


		lblNewLabel = new JLabel("Dardos: "+ Integer.toString(Application.menu.obj.dardos.size()));
		lblArmado = new JLabel();
		lblProtegido = new JLabel();
		
		
			if(Application.menu.obj.hero.armado)
			{
				lblArmado.setIcon(new ImageIcon(this.getClass().getResource("/images/espadas.jpg")));
				this.repaint();
			}
			if(Application.menu.obj.sword.recolhida)
			{
				lblProtegido.setIcon(new ImageIcon(this.getClass().getResource("/images/shield.jpg")));
				this.repaint();
			}

			add(lblNewLabel);
			add(lblArmado);
			add(lblProtegido);
		}
	

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{

		Application.frmLabirinto.getContentPane().removeAll();
		StartMazze.custom = false;
		new MainMenu();


	}
}
