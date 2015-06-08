package pacman.logic;

import pacman.GUI.GameEngine;
import pacman.logic.Game.Mode;

/**  
* 
* OrangeGhost.java - a simple class that represents a OrangeGhost.
* 
* @see Ghost
*/
public class OrangeGhost extends Ghost {

	/**
	 * animation related to sprite
	 */
	int animation;

	/**
	 * Constructor of Orange ghost, set his position, animation and house.
	 */
	public OrangeGhost() {
		super(new Position(0,36*GameEngine.TILE_DIMENSION));
		animation = 0;
		house = true;
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

	/**
	 * Switches Orange ghost mode
	 * @param frameWidth width of frame
	 */
	public void switchMode(int frameWidth)
	{
		if(alive)
		{
			if(Game.ghostMode == Mode.CHASE)
			{
				Position target = Game.pacman.position;

				if(calculateDistance(position.x, position.y, target)%GameEngine.TILE_DIMENSION >= 8)
					target = this.target;

				updateOrientation(target);
			}
			else if (Game.ghostMode == Mode.SCATTER)
				updateOrientation(target);
			else if(Game.ghostMode == Mode.FRIGHTENED)
				updateOrientation(null);
		}
		else updateOrientation(new Position(12*GameEngine.TILE_DIMENSION, 15*GameEngine.TILE_DIMENSION));
	}

} 

