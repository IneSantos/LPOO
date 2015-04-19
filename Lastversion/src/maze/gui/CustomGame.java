package maze.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import maze.logic.Coordenada;
import maze.logic.Dardo;
import maze.logic.Dragao;
import maze.logic.Heroi;
import maze.logic.Labirinto;


public class CustomGame extends JPanel implements MouseListener, KeyListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int element;


	public CustomGame()
	{
		this.addMouseListener(this);
		this.addKeyListener(this);
		setFocusTraversalKeysEnabled(false);	

		
		Application.menu.obj.dimensaoLabirinto = 10;
		Application.menu.obj.labirinto = new Labirinto();
		element = 0;
		setMazeToWall();

		new Heroi(); 
		Application.menu.obj.hero.cor = new Coordenada(-1, -1);

		Application.menu.obj.dragoes = new ArrayList<Dragao>();
		Application.menu.obj.dardos = new ArrayList<Dardo>();

		Application.menu.obj.sword.posicao = new Coordenada(-1, -1);
		Application.menu.obj.sword.recolhida = false;

		Application.menu.obj.shield.posicao = new Coordenada(-1, -1);
		Application.menu.obj.shield.recolhida = false;

		Application.menu.obj.labirinto.Saida = new Coordenada(-1, -1);		
	}


	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); 			

		for (int i = 0; i < Application.menu.obj.labirinto.labirinto.length; i++) 
		{
			for (int j = 0; j < Application.menu.obj.labirinto.labirinto.length; j++) 
			{
				int x = j * Tiles.TILE_DIMENSION;
				int y = i * Tiles.TILE_DIMENSION;
				BufferedImage terrainTile = null;

				int drake = Application.menu.obj.verificaDragao(j, i);
				int dartCheck = Application.menu.obj.verificaDardo(j, i);

				if(j == Application.menu.obj.hero.cor.x && i == Application.menu.obj.hero.cor.y)
					terrainTile = Tiles.hero;
				else if (drake != -1)
				{
					if(Application.menu.obj.dragoes.get(drake).vivo && Application.menu.obj.dragoes.get(drake).dormir)
						terrainTile = Tiles.dragon_sleeping;
					else if (Application.menu.obj.dragoes.get(drake).vivo && !Application.menu.obj.dragoes.get(drake).dormir)
						terrainTile = Tiles.dragon;
					else terrainTile = Tiles.grass;
				}
				else if (j == Application.menu.obj.sword.posicao.x && i == Application.menu.obj.sword.posicao.y && !Application.menu.obj.sword.recolhida)
					terrainTile = Tiles.sword;
				else if (j == Application.menu.obj.shield.posicao.x && i == Application.menu.obj.shield.posicao.y && !Application.menu.obj.shield.recolhida)
					terrainTile = Tiles.shield;
				else if (dartCheck != -1)
					terrainTile = Tiles.dart;
				else if(Application.menu.obj.labirinto.labirinto[i][j] == ' ' || Application.menu.obj.labirinto.Saida.equals(new Coordenada(j,i)))
					terrainTile = Tiles.grass;
				else terrainTile = Tiles.wall;


				g.drawImage(terrainTile, x, y, Application.frmLabirinto);				

			}
		}
	}


	private void setMazeToWall() 
	{
		Application.menu.obj.labirinto.labirinto = new char[Application.menu.obj.dimensaoLabirinto][Application.menu.obj.dimensaoLabirinto];
		
		for(int h = 0; h < Application.menu.obj.labirinto.labirinto.length; ++h)
			for(int w = 0; w < Application.menu.obj.labirinto.labirinto.length; ++w)
				Application.menu.obj.labirinto.labirinto[h][w] = 'X';
	}


	private void setNewTile(int x, int y) 
	{
		int drakeCheck = Application.menu.obj.verificaDragao(x, y);
		int dartCheck = Application.menu.obj.verificaDardo(x, y);
		
		Coordenada nula = new Coordenada(-1, -1);

		if(drakeCheck != -1)
			Application.menu.obj.dragoes.remove(drakeCheck);
		else if (dartCheck != -1)
			Application.menu.obj.dardos.remove(dartCheck);
		else if(Application.menu.obj.hero.cor.equals(new Coordenada(x,y)))
		{
			if(x == Application.menu.obj.hero.cor.x && y == Application.menu.obj.hero.cor.y)
				Application.menu.obj.hero.cor = nula;
		}
		else if(Application.menu.obj.sword.posicao.equals(new Coordenada(x,y)))
		{
			if(x == Application.menu.obj.sword.posicao.x && y == Application.menu.obj.sword.posicao.y)
				Application.menu.obj.sword.posicao = nula;
		}
		else if(Application.menu.obj.shield.posicao.equals(new Coordenada(x,y)))
		{
			if(x == Application.menu.obj.shield.posicao.x && y == Application.menu.obj.shield.posicao.y)
				Application.menu.obj.shield.posicao = nula;
		}



		if(element == 0)
			Application.menu.obj.labirinto.labirinto[y][x] = ' ';
		else if(element == 1)
			Application.menu.obj.labirinto.labirinto[y][x] = 'X';
		else if(element == 2)
		{
			Application.menu.obj.dragoes.add(new Dragao(x, y));
			Application.menu.obj.labirinto.labirinto[y][x] = ' ';
		}
		else if (element == 3)
		{
			Application.menu.obj.dardos.add(new Dardo(x, y));
			Application.menu.obj.labirinto.labirinto[y][x] = ' ';
		}
		else if (element == 4)
		{
			Application.menu.obj.sword.posicao = new Coordenada(x, y);
			Application.menu.obj.labirinto.labirinto[y][x] = ' ';
		}
		else if (element == 5)
		{
			Application.menu.obj.shield.posicao = new Coordenada(x, y);
			Application.menu.obj.labirinto.labirinto[y][x] = ' ';
		}
		else if (element == 6)
		{
			Application.menu.obj.hero.cor = new Coordenada(x, y);
			Application.menu.obj.labirinto.labirinto[y][x] = ' ';
		}
	}


	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		int x = arg0.getX() / Tiles.TILE_DIMENSION;
		int y = arg0.getY() / Tiles.TILE_DIMENSION;

		if(!(x == 0 || x == Application.menu.obj.labirinto.labirinto.length - 1 || y == 0 || y == Application.menu.obj.labirinto.labirinto.length - 1))
			setNewTile(x, y);
		else if(element == 7 && !((x == 0 && y == 0) || (x == Application.menu.obj.labirinto.labirinto.length - 1 && y == 0) || (x == 0 && y == Application.menu.obj.labirinto.labirinto.length - 1) || (x == Application.menu.obj.labirinto.labirinto.length - 1 && y == Application.menu.obj.labirinto.labirinto.length - 1)))
			{
				if(Application.menu.obj.labirinto.Saida.x != -1 && Application.menu.obj.labirinto.Saida.y != -1)
					Application.menu.obj.labirinto.labirinto[Application.menu.obj.labirinto.Saida.y][Application.menu.obj.labirinto.Saida.x] = 'X';
					
				Application.menu.obj.labirinto.Saida = new Coordenada(x, y);
			}
		

		requestFocus();
		repaint();

	}


	@Override
	public void mouseEntered(MouseEvent arg0) {}


	@Override
	public void mouseExited(MouseEvent arg0) {}


	@Override
	public void mousePressed(MouseEvent arg0) {}


	@Override
	public void mouseReleased(MouseEvent arg0) {}


	@Override
	public void keyPressed(KeyEvent e) 
	{ 
		
		if(e.getKeyCode() == KeyEvent.VK_ADD || e.getKeyCode() == KeyEvent.VK_PLUS)
		{
			Application.menu.obj.dimensaoLabirinto++;
			element = 0;
			setPreferredSize(new Dimension(Tiles.TILE_DIMENSION * Application.menu.obj.dimensaoLabirinto, Tiles.TILE_DIMENSION * Application.menu.obj.dimensaoLabirinto));

			
			Application.menu.obj.hero.cor = new Coordenada(-1, -1);

			Application.menu.obj.sword.posicao = new Coordenada(-1, -1);

			Application.menu.obj.shield.posicao = new Coordenada(-1, -1);

			Application.menu.obj.labirinto.Saida = new Coordenada(-1, -1);	
			
			Application.menu.obj.dragoes.clear();
			Application.menu.obj.dardos.clear();
			
			setMazeToWall();
			
			Application.frmLabirinto.pack();
						
			e.setKeyCode(0);
			
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SUBTRACT || e.getKeyCode() == KeyEvent.VK_MINUS)
		{
			Application.menu.obj.dimensaoLabirinto--;
			element = 0;
			setPreferredSize(new Dimension(Tiles.TILE_DIMENSION * Application.menu.obj.dimensaoLabirinto, Tiles.TILE_DIMENSION * Application.menu.obj.dimensaoLabirinto));

			
			Application.menu.obj.hero.cor = new Coordenada(-1, -1);

			Application.menu.obj.sword.posicao = new Coordenada(-1, -1);

			Application.menu.obj.shield.posicao = new Coordenada(-1, -1);

			Application.menu.obj.labirinto.Saida = new Coordenada(-1, -1);	
			
			Application.menu.obj.dragoes.clear();
			Application.menu.obj.dardos.clear();
			
			setMazeToWall();
			
			Application.frmLabirinto.pack();
						
			e.setKeyCode(0);
			
			repaint();
		}
		
		System.out.println("passou");
	}


	@Override
	public void keyReleased(KeyEvent e) {}


	public void keyTyped(KeyEvent e) {}
}
