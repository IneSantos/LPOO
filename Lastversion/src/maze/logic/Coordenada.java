package maze.logic;

import java.io.Serializable;


/**  
* Coordenada.java - a simple class to create a coordinate that represents a location in (x,y) point in space,  specified in
* integer precision.
*/

public class Coordenada implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public boolean equals(Object o) 
	{
		return (o != null) && (o instanceof Coordenada) && ((Coordenada)o).x == x && ((Coordenada)o).y == y;
	}

	/**
	 * The X coordinate of this coord.
	 */
	public int x;
	
	/**
	 * The Y coordinate of this coord.
	 */
	public int y;
	
	
	/**
	 * Constructs and initializes a coord at the specified (x,y) location in the
	 * coordinate space.
	 * 
	 * @param first
	 *            the X coordinate of the newly constructed Coordenada.
	 * @param second
	 *            the Y coordinate of the newly constructed Coordenada.
	 */
	public Coordenada (int first, int second) 
	{ 
		this.x = first;
		this.y = second;
	}


	
}
