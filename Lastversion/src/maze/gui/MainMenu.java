package maze.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.*;

import maze.logic.*;

public class MainMenu extends JPanel{

	private static final long serialVersionUID = 1L;


	private final Panel panel = new Panel();
	
	private final JLabel lblNewLabel = new JLabel("");
	
	private final JButton btnNewGame = new JButton("New Game");
	private final JButton btnCustomGame = new JButton("Create Custom");
	private final JButton btnSettings = new JButton("Settings");
	private final JButton btnExit = new JButton("Exit");

	public Jogo obj;

	
	public MainMenu() 
	{
		obj = new Jogo(true);
		
		obj.labirinto = new Labirinto();
		obj.dimensaoLabirinto = 10;
		obj.hero = new Heroi();
		obj.sword = new Espada();
		obj.shield = new Escudo();
		obj.dragoes = new ArrayList<Dragao>();
		obj.dardos = new ArrayList<Dardo>();
		
		initialize();
	}

	private void initialize()
	{

		
		Application.frmLabirinto.setBackground(Color.BLACK);
		Application.frmLabirinto.setTitle("Labirinto");
		Application.frmLabirinto.setBounds(100, 100, 428, 282);
		Application.frmLabirinto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Application.frmLabirinto.getContentPane().setLayout(null);
		Application.frmLabirinto.setResizable(false);
		
		panel.setLocation(10, 150);
		panel.setSize(150, 83);
	
		Application.frmLabirinto.getContentPane().add(panel);

		panel.setLayout(new GridLayout(0, 1, 0, 1));
		panel.add(btnNewGame);
		panel.add(btnCustomGame);
		panel.add(btnSettings);
		panel.add(btnExit);
		
		btnNewGame.setBounds(154, 100, 150, 23);
		btnNewGame.addActionListener(new PlayListener());
		
		btnCustomGame.setBounds(154, 100, 150, 23);
		btnCustomGame.addActionListener(new CustomGameListener());
		
		btnSettings.setBounds(154, 100, 150, 23);
		btnSettings.addActionListener(new SettingsListener());
		
		btnExit.setBounds(154, 100, 150, 23);
		btnExit.addActionListener(new ExitListener());
		
		JLabel labe = new JLabel();
		labe.setVerticalAlignment(SwingConstants.TOP);
		labe.setBounds(93, 25, 269, 40);
		Application.frmLabirinto.getContentPane().add(labe);
		lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/fundo2.jpg")));
		lblNewLabel.setBounds(0, 0, 4284, 282);
		Application.frmLabirinto.getContentPane().add(lblNewLabel);
				
	}

}