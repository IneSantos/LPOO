package pacman.logic;

import pacman.GUI.GameEngine;
/**  
* 
* RedGhost.java - a simple class that represents a RedGhost.
* 
* @see Ghost
*/
public class RedGhost extends Ghost {

	/**
	 * animation related to sprite
	 */
	int animation;

	/**
	 * Constructor of Red ghost, set his position, animation and house.
	 */
	public RedGhost() {
		super(new Position(33*GameEngine.TILE_DIMENSION,0));
		animation = 0;
		house = false;
	}

	/**
	 * Updates sprite animation
	 * @return new animation
	 */
	public int updateAnimation()
	{
		animation++;

		if (animation > 1)
			animation = 0;

		return animation;
	}

	/**
	 * Returns animation
	 * @return animation
	 */
	public int getAnimation()
	{
		return animation;	
	}

}
