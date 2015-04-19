package maze.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener
{
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyChar() == 'w')
			Application.menu.obj.hero.cor.y--; 
		else if (e.getKeyChar() == 's')
			Application.menu.obj.hero.cor.y++;
		else if (e.getKeyChar() == 'a')
			Application.menu.obj.hero.cor.x--;
		else if (e.getKeyChar() == 'd')
			Application.menu.obj.hero.cor.x++;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
