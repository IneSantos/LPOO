package pacman.menus;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import pacman.GUI.Application;
import pacman.GUI.Images;
import pacman.menus.MainMenu;

@SuppressWarnings("serial")
public class GameOver extends JPanel implements MouseListener , ActionListener{

	int ite = 3;

	public GameOver(){
		Application.frame.getContentPane().removeAll();

		Application.frame.setBounds(100, 100, 400, 500);

		Application.frame.getContentPane().add(this);
		this.setLayout(null);

		Application.frame.setVisible(true);
		this.addMouseListener(this);
		this.repaint();
		requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		new MainMenu();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paintComponent(Graphics g)
	{

		g.drawImage(new Images().game_over1, 0, 0, Application.frame.getWidth(), Application.frame.getHeight(), null, null);

		g.drawImage(new Images().game_over2, 0, 0, Application.frame.getWidth(), Application.frame.getHeight(), null, null);

		//	g.drawImage(new Images().game_over1, 0, 0, Application.frame.getWidth(), Application.frame.getHeight(), null, null);


	}


}
