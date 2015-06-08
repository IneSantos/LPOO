package pacman.logic;

import java.util.Random;

import pacman.GUI.GameEngine;
import pacman.logic.Game.Mode;
/**  
* 
* Ghost.java - a simple class that represents a Ghost.
* 
* @see Pacman
* @see RedGhost
* @see BlueGhost
* @see OrangeGhost
* @see PinkGhost
* @see Maze
*/
public class Ghost extends Character {

/**
 * Indicates Ghost state
 */
	int state;
	
/**
 * Position of target Tile
 */
	public Position target;
	/**
	 * Indicates if Ghost is in the house or not
	 */
	public boolean house;
	
/**
 * Constructor of Ghost
 * @see Position
 * @param target position of ghosts target tile
 */
	public Ghost(Position target)
	{
		super();
		this.orientation = 0; //sempre que sai da casa sai com a orientacao para a esquerda
		this.alive = true;
		this.target = target;
	}

	/**
	 * Generates random Orientation
	 * @return orientation
	 */
	private int generateOrientation()
	{
		Random random = new Random();
		int rand = random.nextInt(4);
		return rand;
	}

	/**
	 * Moves Ghost
	 */
	public void moveGhost()
	{
		if(house)
		{
			if(this.orientation == 0)
			{
				if(moveUp() && !Game.maze.isDoor(getTilePosition(position.x, position.y - 1)))
					return;
				else
				{
					this.orientation = 2;
					moveDown();
					return;
				}
			}
			else if(this.orientation == 2)
			{
				if(moveDown())
					return;
				else
				{
					this.orientation = 0;
					moveUp();
					return;
				}
			}
		}

		if(Game.maze.isSpecial(getTilePosition(position.x, position.y)))
		{
			if (!this.alive && position.x % GameEngine.TILE_DIMENSION == 0 && position.y % GameEngine.TILE_DIMENSION == 0)
			{
				this.orientation = 2;
				moveDown();
				return;
			}
		}

		if(Game.maze.isDoor(getTilePosition(position.x, position.y)))
		{
			if (position.x % GameEngine.TILE_DIMENSION == 0 && position.y % GameEngine.TILE_DIMENSION == 0)
			{
				this.alive = true;
				this.orientation = 0;
				this.velocity = 2;
				moveUp();
				return;
			}
		}

		if(!Game.maze.isDecisionPoint(getTilePosition(position.x, position.y)))
		{
			if(this.orientation == 0 && moveUp())
				return;
			else if(this.orientation == 2 && moveDown())
				return;
			else if(this.orientation == 1 && moveRight())
				return;
			else if(this.orientation == 3 && moveLeft())
				return;
			else
				switchMode();

		}
		else if(position.x % GameEngine.TILE_DIMENSION == 0 && position.y % GameEngine.TILE_DIMENSION == 0)
			switchMode();
		else
		{
			if(this.orientation == 0 && moveUp())
				return;
			else if(this.orientation == 3 && moveLeft())
				return;
			else if(this.orientation == 2 && moveDown())
				return ;
			else if(this.orientation == 1 && moveRight())
				return;
			else
				switchMode();
		}
	}

	/**
	 * Switches Ghost mode
	 */
	public void switchMode()
	{
		if(this.alive)
		{
			if(Game.ghostMode == Mode.CHASE)
				updateOrientation(Game.pacman.position);
			else if (Game.ghostMode == Mode.SCATTER)
				updateOrientation(target);
			else if(Game.ghostMode == Mode.FRIGHTENED)
				updateOrientation(null);
		}
		else updateOrientation(new Position(12*GameEngine.TILE_DIMENSION, 15*GameEngine.TILE_DIMENSION));

	}

	/**
	 * Updates ghost in Frightened mode 
	 */
	private void updateFrightened() 
	{
		int new_orientation = generateOrientation();

		if(new_orientation == 0)
			if(moveUp())
				this.orientation = 0;
			else {
				moveDown();
				this.orientation = 2;
			}
		else if(new_orientation == 2)
			if(moveDown())
				this.orientation = 2;
			else {
				moveUp();
				this.orientation = 0;
			}
		else if(new_orientation == 1)
			if(moveRight())
				this.orientation = 1;
			else {
				moveLeft();
				this.orientation = 3;
			}
		else if(new_orientation == 3)
			if(moveLeft())
				this.orientation = 3;
			else {
				moveRight();
				this.orientation = 1;
			}
	}

	/**
	 * Updates orientation
	 * @param target position of target tile
	 */
	public void updateOrientation(Position target)
	{
		if(target == null)
			updateFrightened();
		else{

			if(this.orientation == 0) //UP
				updateOrientationFromUpMovement(target);
			else if(this.orientation == 1) //RIGHT
				updateOrientationFromRightMovement(target);
			else if(this.orientation == 2) //DOWN
				updateOrientationFromDownMovement(target);
			else if(this.orientation == 3) //LEFT
				updateOrientationFromLeftMovement(target);
		}
	}

	/**
	 * Updates Orientation when the ghost is coming from the left
	 * @param target position of target tile
	 */
	private void updateOrientationFromLeftMovement(Position target) 
	{
		float down = testDownMove(target);
		float up = testUpMove(target);
		float left = testLeftMove(target);


		if(up < left)
		{
			if(up < down)
			{
				if(moveUp())
					this.orientation = 0;
				else if (down < left)
				{
					if(moveDown())
						this.orientation = 2;
					else moveLeft();
				}
				else if(moveLeft())
					return;
				else 
				{
					moveDown();
					this.orientation = 2;
				}
			}
			else if(moveDown())
				this.orientation = 2;
			else if(moveUp())
				this.orientation = 0;
			else moveLeft();
		}
		else if(down < left)
		{
			if(moveDown())
				this.orientation = 2;
			else if(moveLeft())
				return;
			else 
			{
				moveUp();
				this.orientation = 0;
			}
		}
		else
		{
			if (moveLeft())
				return;
			else if(down < up)
			{
				if (moveDown())
					this.orientation = 2;
				else
				{
					moveUp();
					this.orientation = 0;
				}
			}
			else if(moveUp())
				this.orientation = 0;
			else 
			{
				moveDown();
				this.orientation = 2;
			}
		}
	}

	/**
	 * Updates Orientation when the ghost is coming from down
	 * @param target position of target tile
	 */
	private void updateOrientationFromDownMovement(Position target)	
	{
		float left = testLeftMove(target);
		float right = testRightMove(target);
		float down = testDownMove(target);

		if(right < down)
		{
			if(right < left)
			{
				if(moveRight())
					this.orientation = 1;
				else if (left < down)
				{
					if(moveLeft())
						this.orientation = 3;
					else moveDown();
				}
				else if(moveDown())
					return;
				else
				{
					moveLeft();
					this.orientation = 3;
				}
			}
			else if(moveLeft())
				this.orientation = 3;
			else if(moveRight())
				this.orientation = 1;
			else moveDown();
		}
		else if(left < down)
		{
			if(moveLeft())
				this.orientation = 3;
			else if(moveDown())
				return;
			else 
			{
				moveRight();
				this.orientation = 1;
			}
		}
		else
		{
			if (moveDown())
				return;
			else if(left < right)
			{
				if (moveLeft())
					this.orientation = 3;
				else
				{
					moveRight();
					this.orientation = 1;
				}
			}
			else if(moveRight())
				this.orientation = 1;
			else 
			{
				moveLeft();
				this.orientation = 3;
			}
		}
	}

	/**
	 * Updates Orientation when the ghost is coming from the right
	 * @param target position of target tile
	 */
	private void updateOrientationFromRightMovement(Position target) 	
	{
		float down = testDownMove(target);
		float up = testUpMove(target);
		float right = testRightMove(target);

		if(up < right)
		{
			if(up < down)
			{
				if(moveUp())
					this.orientation = 0;
				else if (down < right)
				{
					if(moveDown())
						this.orientation = 2;
					else moveRight();
				}
				else if(moveRight())
					return;
				else 
				{
					moveDown();
					this.orientation = 2;
				}
			}
			else if(moveDown())
				this.orientation = 2;
			else if(moveUp())
				this.orientation = 0;
			else moveRight();
		}
		else if(down < right)
		{
			if(moveDown())
				this.orientation = 2;
			else if(moveRight())
				return;
			else 
			{
				moveUp();
				this.orientation = 0;
			}
		}
		else
		{
			if (moveRight())
				return;
			else if(down < up)
			{
				if (moveDown())
					this.orientation = 2;
				else
				{
					moveUp();
					this.orientation = 0;
				}
			}
			else if(moveUp())
				this.orientation = 0;
			else 
			{
				moveDown();
				this.orientation = 2;
			}
		}
	}

	/**
	 * Updates Orientation when the ghost is coming from up
	 * @param target position of target tile
	 */
	private void updateOrientationFromUpMovement(Position target) 
	{
		float left = testLeftMove(target);
		float right = testRightMove(target);
		float up = testUpMove(target);

		if(right < up)
		{
			if(right < left)
			{
				if(moveRight())
					this.orientation = 1;
				else if (left < up)
				{
					if(moveLeft())
						this.orientation = 3;
					else moveUp();
				}
				else if(moveUp())
					return;
				else
				{
					moveLeft();
					this.orientation = 3;
				}
			}
			else if(moveLeft())
				this.orientation = 3;
			else if(moveRight())
				this.orientation = 1;
			else moveUp();
		}
		else if(left < up)
		{
			if(moveLeft())
				this.orientation = 3;
			else if(moveUp())
				return;
			else 
			{
				moveRight();
				this.orientation = 1;
			}
		}
		else
		{
			if (moveUp())
				return;
			else if(left < right)
			{
				if (moveLeft())
					this.orientation = 3;
				else
				{
					moveRight();
					this.orientation = 1;
				}
			}
			else if(moveRight())
				this.orientation = 1;
			else 
			{
				moveLeft();
				this.orientation = 3;
			}
		}
	}

	/**
	 * Calculates distance between two tiles
	 * @param x x coordinate of object
	 * @param y y coordinate of object
	 * @param target position of target tile
	 * @return Distance between them 
	 */
	protected float calculateDistance(int x, int y, Position target)
	{
		return (float) Math.sqrt(Math.pow((x - target.x),2) + Math.pow((y - target.y),2));
	}

	/**
	 * Tests to see if distance if he goes down
	 * @param target position of target tile
	 * @return distance 
	 */
	private float testDownMove(Position target) {
		return calculateDistance(this.position.x, position.y + 1, target);
	}

	/**
	 * Tests to see if distance if he goes up
	 * @param target position of target tile
	 * @return distance 
	 */
	private float testUpMove(Position target) {
		return calculateDistance(this.position.x, this.position.y-1, target);
	}

	/**
	 * Tests to see if distance if he goes to the left
	 * @param target position of target tile
	 * @return distance 
	 */
	private float testLeftMove(Position target) {
		return calculateDistance(position.x - 1, this.position.y, target);
	}

	/**
	 * Tests to see if distance if he goes to the right
	 * @param target position of target tile
	 * @return distance 
	 */
	private float testRightMove(Position target) {
		return calculateDistance(position.x + 1, this.position.y, target);
	}




}
