package maze.logic;

import java.io.Serializable;
import java.util.Random;

/**  
* Espada.java - a simple class that represents the Sword.
*/
@SuppressWarnings("serial")
public class Espada implements Serializable
{
	/**
	 *  The coordinate of the Sword.
	 * 
	 * @see {@link Coordenada}
	 */
	public Coordenada posicao;
	
	/**
	 *  Boolean that indicates if the Sword was collected or not.
	 * 
	 */
	public boolean recolhida = false;
	
	/**
	 * Constructs and initializes the Sword at the specified coordinate and sets recolhida as false.
	 * 
	 * @param x
	 *            the X coordinate of the Sword
	 * @param y
	 *            the Y coordinate of the Sword
	 * @see {@link Coordenada}
	 */	
	public Espada(int x, int y)
	{
		posicao = new Coordenada(x, y);
		recolhida = false;
	}
	
	public Espada() {}

	/**
	 * Sets Sword's position in the maze.
	 * 
	 */
	public void deploySword(Jogo jogo)
	{
		recolhida = false;
		
		Random randomGen = new Random ();
		boolean definido = false;
		

		while(!definido)
		{
			int x = randomGen.nextInt(jogo.dimensaoLabirinto - 2) + 1;
			int y = randomGen.nextInt(jogo.dimensaoLabirinto - 2) + 1;

			if((x != jogo.hero.cor.x || y != jogo.hero.cor.y) && jogo.labirinto.espacoLivreHeroi(y, x))
			{
				posicao = new Coordenada(x, y);
				definido = true;
			}
		}
	}
}
