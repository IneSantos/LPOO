package maze.logic;

import java.io.Serializable;
import java.util.Random;

import maze.logic.Coordenada;

/**  
* Escudo.java - a simple class that represents a shield.
*/
@SuppressWarnings("serial")
public class Escudo implements Serializable{

	/**
	 *  The coordinate of the shield.
	 * 
	 * @see {@link Coordenada}
	 */
	public Coordenada posicao;
	
	/**
	 *  Boolean that indicates if the shield was collected or not.
	 * 
	 */
	public boolean recolhida;
	
	/**
	 * Constructs and initializes the shield at the specified coordinate and sets recolhida as false.
	 * 
	 * @param x
	 *            the X coordinate of the shield
	 * @param y
	 *            the Y coordinate of the shield
	 * @see {@link Coordenada}
	 */	
	public Escudo(int x, int y)
	{
		posicao =  new Coordenada(x,y);
		recolhida = false;
	}
	

	public Escudo() {}


	/**
	 * Sets shield's position in the maze.
	 * 
	 */
	public void deployShield(Jogo jogo)
	{
		Random randomGen = new Random ();

		recolhida = false;
		boolean definido = false;

		while(!definido)
		{
			int x = randomGen.nextInt(jogo.dimensaoLabirinto - 2) + 1;
			int y = randomGen.nextInt(jogo.dimensaoLabirinto - 2) + 1;

			if(!(jogo.hero.cor.equals(new Coordenada(x, y))) && jogo.labirinto.espacoLivreHeroi(y, x) && (x != jogo.sword.posicao.x || y != jogo.sword.posicao.y) && jogo.verificaDardo(x, y) == -1)
			{
				posicao = new Coordenada(x,y);
				definido = true;
			}
		}
	}
}
