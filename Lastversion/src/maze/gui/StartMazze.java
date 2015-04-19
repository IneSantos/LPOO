package maze.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import maze.logic.Coordenada;
import maze.logic.Dragao;


public class StartMazze extends JPanel implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	private final int MILISSECONDS_TO_REFRESH = 50;
	private final int DRAGONS_REFRESH = 750;
	
	public Timer timer = new Timer(MILISSECONDS_TO_REFRESH, this);
	private int ticks = 0;
	
	public static int generateMaze = 0;
	public static boolean custom = false; 
	
	
	public StartMazze()
	{
		this.addKeyListener(this);
		setFocusTraversalKeysEnabled(false);		
		
		if(!custom)
		{
			if(generateMaze == 1)
				Application.menu.obj.generateLabirinto();
			else if(generateMaze == 0)
				Application.menu.obj.standardLabirinto();
			
		}
						

		timer.start();
	}
	
	
	@Override
    public void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
        
        PlayListener.panel.setPreferredSize(new Dimension(Application.frmLabirinto.getContentPane().getWidth() - 150, Application.frmLabirinto.getContentPane().getHeight()));
        PlayListener.options.setPreferredSize(new Dimension(150, Application.frmLabirinto.getContentPane().getHeight()));
        
        int largura = PlayListener.panel.getWidth() / Application.menu.obj.labirinto.labirinto.length;
        int altura = PlayListener.panel.getHeight() / Application.menu.obj.labirinto.labirinto.length;
        
       
        for (int i = 0; i < Application.menu.obj.labirinto.labirinto.length; i++) 
        {
            for (int j = 0; j < Application.menu.obj.labirinto.labirinto.length; j++) 
            {
                int x = j * largura;
                int y = i * altura;
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
				else if(Application.menu.obj.labirinto.labirinto[i][j] == ' ' || Application.menu.obj.labirinto.Saida.equals(new Coordenada(j, i)))
					terrainTile = Tiles.grass;
				else terrainTile = Tiles.wall;
                
                
                g.drawImage(terrainTile, x, y, largura, altura, null, null);		
            }
        }
    }

	
	public void actionPerformed(ActionEvent a)
	{
		
		if(Application.menu.obj.dragonMoves != 0)
			if(ticks == DRAGONS_REFRESH)
			{
				ticks = 0;
				updateDragons();
			}
			else ticks += MILISSECONDS_TO_REFRESH;

		if(Application.menu.obj.hero.armado)
			OptionPanel.lblArmado.setIcon(new ImageIcon(this.getClass().getResource("/images/espadas.jpg")));
		
		if(Application.menu.obj.shield.recolhida)
			OptionPanel.lblProtegido.setIcon(new ImageIcon(this.getClass().getResource("/images/shield.jpg")));
			
		OptionPanel.lblNewLabel.setText("Dardos: "+ Integer.toString((Application.menu.obj.hero.numDardos)));
		
		repaint();
		
		if(Application.menu.obj.termina)
		{
			Application.menu.obj.termina = false;
			if(!Application.menu.obj.hero.vivo)
				JOptionPane.showMessageDialog(Application.frmLabirinto,new ImageIcon(this.getClass().getResource("/images/morreste_heroi-morto.jpg")));
			else if(Application.menu.obj.hero.vivo)
				JOptionPane.showMessageDialog(Application.frmLabirinto,new ImageIcon(this.getClass().getResource("/images/morreste_heroi-vivo.png")));
			
			Application.frmLabirinto.getContentPane().removeAll();
			Application.frmLabirinto.getContentPane().setLayout(new FlowLayout());
			custom = false;
			
			
			timer.stop();
			
			new MainMenu();
		}
	}
	
	
	private void updateDragons() 
	{
		for(int i = 0; i < Application.menu.obj.dragoes.size(); i++)
		{
			Dragao dragao = Application.menu.obj.dragoes.get(i);
			
			if(dragao.vivo)
				Application.menu.obj.moveDragao(dragao);
			else
				Application.menu.obj.dragoes.remove(i);
		}
	}


	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		
		updateHero(code);
	}
	
	
	public void updateHero(int code)
	{
			
		if(code == Controls.up)
			Application.menu.obj.verificaMovimentoHeroi('w');
		else if(code == Controls.down)
			Application.menu.obj.verificaMovimentoHeroi('s');
		else if(code == Controls.left)
			Application.menu.obj.verificaMovimentoHeroi('a');
		else if(code == Controls.right)
			Application.menu.obj.verificaMovimentoHeroi('d');
		else if(code == Controls.shootUp)
			Application.menu.obj.hero.disparaDardo('w', Application.menu.obj);
		else if(code == Controls.shootDown)
			Application.menu.obj.hero.disparaDardo('s', Application.menu.obj);
		else if(code == Controls.shootLeft)
			Application.menu.obj.hero.disparaDardo('a', Application.menu.obj);
		else if(code == Controls.shootRight)
			Application.menu.obj.hero.disparaDardo('d', Application.menu.obj);
		
		Application.menu.obj.verificaHeroiDragao();
		
		PlayListener.options.repaint();
	
	}


	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	


	public ActionListener restart() 
	{
		if(custom)
			CustomGameListener.createCustom();
		else if(generateMaze == 1)
			Application.menu.obj.generateLabirinto();
		else if(generateMaze == 0)
			Application.menu.obj.standardLabirinto();
					
		requestFocus();
		
		return null;
	}

	
}
