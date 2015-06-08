package pacman.logic;

import pacman.GUI.GameEngine;
import pacman.logic.Game.Mode;
/**  
* 
* BlueGhost.java - a simple class that represents a BlueGhost.
* 
* @see Ghost
*/
public class BlueGhost extends Ghost {

	/**
	 * animation related to sprite
	 */
	int animation;

	/**
	 * Constructor of blue ghost, set his position, animation and house.
	 */
	public BlueGhost() {
		super(new Position(28*GameEngine.TILE_DIMENSION,36*GameEngine.TILE_DIMENSION));
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
	 * Switches Blue ghost mode
	 * @param frameWidth width of frame
	 */
	public void switchMode(int frameWidth)
	{
		if(this.alive)
		{
			if(Game.ghostMode == Mode.CHASE)
			{
				if(Game.redFlag)
				{
					Position p = null;
					if(Game.pacman.orientation == 0)
						p = new Position(Game.pacman.position.x, Game.pacman.position.y - 2);
					else if (Game.pacman.orientation == 1)
						p = new Position(Game.pacman.position.x - 2, Game.pacman.position.y);
					else if (Game.pacman.orientation == 2)
						p = new Position(Game.pacman.position.x, Game.pacman.position.y + 2);
					else
						p = new Position(Game.pacman.position.x + 2, Game.pacman.position.y);

					Position new_target =  new Position(2*p.x - Game.redGhost.position.x, 2*p.y - Game.redGhost.position.y);

					if(new_target.x > Game.mazeWidth * GameEngine.TILE_DIMENSION)
						new_target.x = Game.mazeWidth * GameEngine.TILE_DIMENSION;
					else if (new_target.x > 0)
						new_target.x = 0;

					if(new_target.y > Game.mazeHeight * GameEngine.TILE_DIMENSION)
						new_target.y = Game.mazeHeight * GameEngine.TILE_DIMENSION;
					else if (new_target.y > 0)
						new_target.y = 0;

					updateOrientation(new_target);
				}
				else updateOrientation(Game.pacman.position);
			}
			else if (Game.ghostMode == Mode.SCATTER)
				updateOrientation(target);
			else if(Game.ghostMode == Mode.FRIGHTENED)
				updateOrientation(null);
		} 
		else updateOrientation(new Position(12*GameEngine.TILE_DIMENSION, 15*GameEngine.TILE_DIMENSION));
	}

}

