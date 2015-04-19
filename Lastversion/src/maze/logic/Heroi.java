package maze.logic;

import java.io.Serializable;
import java.util.Random;


@SuppressWarnings("serial")
public class Heroi implements Serializable {

	/**
	 *  The coordinate of the Hero.
	 * 
	 * @see {@link Coordenada}
	 */
	public Coordenada cor;
	
	/**
	 *  Boolean that indicates if the hero is armed or not.
	 * 
	 */
	public boolean armado;
	
	/**
	 *  Boolean that indicates if the hero is protected or not.
	 * 
	 */
	public boolean protegido;
	
	/**
	 *  Boolean that indicates if the hero is alive or not.
	 * 
	 */
	public boolean vivo;
	
	/**
	 *  The number of arrows.
	 * 
	 */
	public int numDardos;

	/**
	 * Constructs and initializes the hero with 0 arrows, unarmed, alive and unprotected.
	 * 
	 */	
	public Heroi()
	{
		numDardos = 0;
		armado = false;
		vivo = true;
		protegido = false;
	}

	/**
	 * Sets hero's position in the maze.
	 * 
	 */
	public void deployHero(Jogo jogo)
	{
		new Heroi();
		
		Random randomGen = new Random ();
		boolean definido = false;

		if (jogo.labirinto.Saida.x == 0)
		{
			while(!definido)
			{
				int y = randomGen.nextInt(jogo.dimensaoLabirinto-2) + 1;

				if(jogo.labirinto.labirinto[y][jogo.dimensaoLabirinto - 2] == ' ')
				{
					definido = true;
					cor = new Coordenada(jogo.dimensaoLabirinto - 2, y);
				}
			}
		}
		
		if (jogo.labirinto.Saida.x == jogo.dimensaoLabirinto - 1)
		{
			while(!definido)
			{
				int y = randomGen.nextInt(jogo.dimensaoLabirinto-2) + 1;

				if(jogo.labirinto.labirinto[y][1] == ' ')
				{
					definido = true;
					cor = new Coordenada(1, y);
				}
			}
		}
		
		if (jogo.labirinto.Saida.y == 0)
		{
			while(!definido)
			{
				int x = randomGen.nextInt(jogo.dimensaoLabirinto-2) + 1;

				if(jogo.labirinto.labirinto[jogo.dimensaoLabirinto - 2][x] == ' ')
				{
					definido = true;
					cor = new Coordenada(x, jogo.dimensaoLabirinto - 2);
				}
			}
		}
		
		if (jogo.labirinto.Saida.y == jogo.dimensaoLabirinto - 1)
		{
			while(!definido)
			{
				int x = randomGen.nextInt(jogo.dimensaoLabirinto-2) + 1;

				if(jogo.labirinto.labirinto[1][x] == ' ')
				{
					definido = true;
					cor = new Coordenada(x, 1);
				}
			}
		}
	}

	/**
	 * Fire arrow.
	 */
	public void disparaDardo(char dir, Jogo jogo)
	{
		if(numDardos == 0)
			return;
		
		if(dir == 'w')
		{
			for(int i = 0; i < jogo.dragoes.size(); ++i)
			{
				if(jogo.dragoes.get(i).posicao.x == cor.x && jogo.dragoes.get(i).posicao.y < cor.y && jogo.labirinto.caminhoLivre(jogo.dragoes.get(i).posicao, cor))
				{
					jogo.dragoes.get(i).vivo = false;
					break;
				}	
			}
		}
		else if(dir == 's')
		{	
			for(int i = 0; i < jogo.dragoes.size(); ++i)
			{
				if(jogo.dragoes.get(i).posicao.x == cor.x && jogo.dragoes.get(i).posicao.y > cor.y && jogo.labirinto.caminhoLivre(jogo.dragoes.get(i).posicao, cor))
				{
					jogo.dragoes.get(i).vivo = false;
					break;
				}			
			}
		} 
		else if(dir == 'a')
		{
			for(int i = 0; i < jogo.dragoes.size(); ++i)
			{
				if(jogo.dragoes.get(i).posicao.y == cor.y && jogo.dragoes.get(i).posicao.x < cor.x && jogo.labirinto.caminhoLivre(jogo.dragoes.get(i).posicao, cor))
				{
					jogo.dragoes.get(i).vivo = false;
					break;
				}
			}
		} 
		else if(dir == 'd')
		{	
			for(int i = 0; i < jogo.dragoes.size(); ++i)
			{
				if(jogo.dragoes.get(i).posicao.y == cor.y && jogo.dragoes.get(i).posicao.x > cor.x && jogo.labirinto.caminhoLivre(jogo.dragoes.get(i).posicao, cor))
				{
					jogo.dragoes.get(i).vivo = false;
					break;
				}			
			}
		}

		numDardos--;
	}
	
}
