package maze.logic;

import java.io.Serializable;

import maze.logic.Coordenada;

/**  
* Dardo.java - a simple class that represents an Arrow 
*/
@SuppressWarnings("serial")
public class Dardo implements Serializable
{
	/**
	 *  The coordinate of this Arrow.
	 * 
	 * @see {@link Coordenada}
	 */
	public Coordenada posicao;

	
	/**
	 * Constructs and initializes an Arrow at the specified position (x,y).
	 *
	 * @param x
	 *        the X coordinate of the newly constructed Arrow.
	 * @param y
	 *        the Y coordinate of the newly constructed Arrow.
	 *        
	 * @see {@link Coordenada}
	 */
	public Dardo(int x, int y)
	{
		this.posicao =  new Coordenada(x,y);
	}



	
}
