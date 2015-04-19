package maze.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import maze.logic.*;

public class MazeTools extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton grass;
	private JButton wall;
	private JButton dragon;
	private JButton dart;
	private JButton sword;
	private JButton shield;
	private JButton hero;
	private JButton end;
	private JButton play;
	private JButton exit;
	
	
	
	public MazeTools()
	{
		setLayout(new GridLayout(5, 2));
		
		grass = new JButton();
		grass.setIcon(new ImageIcon(this.getClass().getResource("/images/grass.png"))); 
		grass.addActionListener(new EditionListener(0));
	
		wall = new JButton();
		wall.setIcon(new ImageIcon(this.getClass().getResource("/images/wall.png")));
		wall.addActionListener(new EditionListener(1));
		
		dragon = new JButton();
		dragon.setIcon(new ImageIcon(this.getClass().getResource("/images/dragon.png")));
		dragon.addActionListener(new EditionListener(2));
		
		dart = new JButton();
		dart.setIcon(new ImageIcon(this.getClass().getResource("/images/dart.png")));
		dart.addActionListener(new EditionListener(3));
		
		sword = new JButton();
		sword.setIcon(new ImageIcon(this.getClass().getResource("/images/sword.png")));
		sword.addActionListener(new EditionListener(4));
		
		shield = new JButton();
		shield.setIcon(new ImageIcon(this.getClass().getResource("/images/shield.png")));
		shield.addActionListener(new EditionListener(5));
		
		hero = new JButton();
		hero.setIcon(new ImageIcon(this.getClass().getResource("/images/hero.png")));
		hero.addActionListener(new EditionListener(6));
		
		end = new JButton("end");
		end.addActionListener(new EditionListener(7));
		
		play = new JButton("PLAY");
		play.addActionListener(this);
		
		exit = new JButton("EXIT");
		exit.addActionListener(new OptionPanel(this));
		

		add(play);
		add(exit);
		add(grass);
		add(wall);
		add(dragon);
		add(dart);
		add(sword);
		add(shield);
		add(hero);
		add(end);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		Coordenada nula = new Coordenada(-1, -1);
		
		if(Application.menu.obj.hero.cor.equals(nula))
			JOptionPane.showMessageDialog(null, "Insere um Heroi em campo!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
		else if(Application.menu.obj.dragoes.size() == 0)
		{
			JOptionPane.showMessageDialog(null, "Insere no mínimo um Dragão em campo!", "Warning",
                    JOptionPane.WARNING_MESSAGE);			
		}
		else if (Application.menu.obj.sword.posicao.equals(nula))
		{
			JOptionPane.showMessageDialog(null, "Insere uma Espada em campo!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
		}
		else if (Application.menu.obj.labirinto.Saida.equals(nula))
		{
			JOptionPane.showMessageDialog(null, "Insere uma saida do labirinto!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
		}
		else
		{			
			StartMazze.custom = true;
			PlayListener.startGame();
		}
	}

}
