package pacman.logic;

import pacman.GUI.GameEngine;
/**  
* 
* Character.java - a simple class that represents a Character.
* 
* @see Pacman
* @see RedGhost
* @see BlueGhost
* @see OrangeGhost
* @see PinkGhost
* @see Maze
*/
public class Character {

	/**
	 * Position of Character
	 */
	Position position;
	/**
	 * velocity of Character
	 */
	int velocity;
	/**
	 * orientation of Character
	 */
	int orientation;
	/**
	 * boolean that indicates if Character is alive
	 */
	boolean alive;

	/**
	 * Constructor of Character
	 * sets his position at 100,100, velocity and orientation to two and boolean to true 
	 */
	public Character()
	{
		position = new Position(100, 100);
		velocity = 2;
		orientation = 2;
		alive = true;
	}
	
	/**
	 * Get Alive boolean
	 * @return <code>true</code> if character is alive.
	 */
	public boolean getAlive(){
		return alive;
	}

	/**
	 * Get orientation
	 * @return orientation
	 */
	public int getOrientation()
	{
		return orientation;
	}

	/**
	 * Get X coordinate
	 * @return X coordinate
	 */
	public int getX()
	{
		return position.x;
	}

	/**
	 * Get Y coordinate
	 * @return Y coordinate
	 */
	public int getY()
	{
		return position.y;
	}

	/**
	 * Set orientation 
	 * @param o new orientation
	 */
	public void setOrientation(int o)
	{
		orientation = o;
	}

	/**
	 * Gets Position of tile 
	 * @param width width of maze
	 * @param height height of maze 
	 * @return position of tile 
	 */
	public Position getTilePosition(int width, int height) 
	{
		int x = width / GameEngine.TILE_DIMENSION;
		int y = height / GameEngine.TILE_DIMENSION;

		return new Position(x, y);
	}

	/**
	 * Moves position up if possible
	 * @return <code>true</code> if is possible to move up.
	 */
	public boolean moveUp()
	{
		Position p1 = getTilePosition(position.x, position.y - velocity);

		if(Game.maze.isWall(p1))
		{
			position.y = (p1.y + 1) * GameEngine.TILE_DIMENSION;

			return false;
		}
		
		position.y -= velocity;
		return true;
	}

	/**
	 * Moves position right if possible
	 * @return <code>true</code> if is possible to move right.
	 */
	public boolean moveRight()
	{
		Position p1 = getTilePosition(position.x + GameEngine.TILE_DIMENSION - 1 + velocity , position.y);

		if(p1.x >= Game.maze.maze.get(0).length)
		{
			if(position.x >= Game.mazeWidth * GameEngine.TILE_DIMENSION)
				position.x = -GameEngine.TILE_DIMENSION;
		}
		else
		{
			if(Game.maze.isWall(p1))
			{
				position.x = (p1.x - 1) * GameEngine.TILE_DIMENSION;

				return false;
			}
		}

		position.x += velocity;
		return true;
	}

	/**
	 * Moves position left if possible
	 * @return <code>true</code> if is possible to move left.
	 */
	public boolean moveLeft()
	{		
		Position p1 = getTilePosition(position.x - velocity, position.y);

		if(p1.x < 0)
		{
			if(position.x <= -24)
				position.x = Game.mazeWidth * GameEngine.TILE_DIMENSION;
		}	
		else
		{

			if(Game.maze.isWall(p1))
			{
				position.x = (p1.x + 1) * GameEngine.TILE_DIMENSION;

				return false;
			}
		}
		position.x -= velocity;

		return true;
	}

	/**
	 * Moves position down if possible
	 * @return <code>true</code> if is possible to move down.
	 */
	public boolean moveDown()
	{		
		Position p1 = getTilePosition(position.x, position.y + GameEngine.TILE_DIMENSION - 1 + velocity);
		
		if(Game.maze.isWall(p1))
		{
			position.y = (p1.y - 1) * GameEngine.TILE_DIMENSION;

			return false;
		}
		
		position.y += velocity;

		return true;
	}

	/**
	 * Sets position to new position passed as argument
	 * @param p new position
	 */
	public void setPosition(Position p)
	{
		this.position = p;
	}
	
	/**
	 * Sets velocity to new velocity passed as argument
	 * @param vel new velocity
	 */
	public void setVelocity(int vel)
	{
		this.velocity = vel;
	}

}
