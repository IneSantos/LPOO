package maze.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class ChangeKeys extends JDialog implements KeyListener{

	//up, down, left, right -- shootup, shootdown, shootleft, shootright

	Settings st;
	String key;
	JButton button;
	static int keypressed = -1;

	public ChangeKeys(Settings settings, String key2, JButton but) 
	{
		this.st = settings;
		this.key = key2;	
		this.button = but;
		this.addKeyListener(this);
		this.getContentPane().setLayout(null);
		JLabel carrega = new JLabel("Carrega em qualquer tecla...");
		this.setSize(300, 100);
		carrega.setBounds(40, 20 , 200, 25);
		this.setResizable(false);
		getContentPane().add(carrega);
		this.setModal(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}


	@Override
	public void keyPressed(KeyEvent e) 
	{
		keypressed = e.getKeyCode();
		
		if(!equalToShootKeys(keypressed) && !equalToGameKeys(keypressed))
		{
			if(key.equals("up"))
			{
				Controls.up = keypressed;
				button.setText("UP - " + KeyEvent.getKeyText(Controls.up));
				this.dispose();
			}
			else if(key.equals("down"))
			{
				Controls.down = keypressed;
				button.setText("DOWN - " + KeyEvent.getKeyText(Controls.down));
				this.dispose();
			}
			else if(key.equals("left"))
			{
				Controls.left = keypressed;
				button.setText("LEFT - " + KeyEvent.getKeyText(Controls.left));
				this.dispose();
			}
			else if(key.equals("right"))
			{
				Controls.right = keypressed;
				button.setText("RIGHT - " + KeyEvent.getKeyText(Controls.right));
				this.dispose();
			}
			else if(key.equals("shootup"))
			{
				Controls.shootUp = keypressed;
				button.setText("Shoot UP - " + KeyEvent.getKeyText(Controls.shootUp));
				this.dispose();
			}
			else if(key.equals("shootdown"))
			{
				Controls.shootDown = keypressed;
				button.setText("Shoot DOWN - " + KeyEvent.getKeyText(Controls.shootDown));
				this.dispose();
			}
			else if(key.equals("shootleft"))
			{
				Controls.shootLeft = keypressed;
				button.setText("Shoot LEFT - " + KeyEvent.getKeyText(Controls.shootLeft));
				this.dispose();
			}
			else if(key.equals("shootright"))
			{
				Controls.shootRight = keypressed;
				button.setText("Shoot RIGHT - " + KeyEvent.getKeyText(Controls.shootRight));
				this.dispose();
			}
		}
		else JOptionPane.showMessageDialog(this,
				"Tecla já em uso... escolhe outra... ","ERROR",JOptionPane.WARNING_MESSAGE);

	}

	boolean equalToShootKeys(int k)
	{
		if(k == Controls.shootUp || k == Controls.shootDown ||k == Controls.shootLeft || k == Controls.shootRight)
			return true;
		else return false;
	}

	boolean equalToGameKeys(int k)
	{
		if(k == Controls.up || k == Controls.down ||k == Controls.left || k == Controls.right)
			return true;
		else return false;
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}



}