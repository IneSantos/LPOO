package pacman.logic;

import java.util.Random;

import pacman.GUI.GameEngine;

/**  
* 
* Game.java - a simple class that represents the Game.
* 
* @see Pacman
* @see RedGhost
* @see BlueGhost
* @see OrangeGhost
* @see PinkGhost
* @see Maze
*/
public class Game {

	/**
	 * Represents a {@link Ghost} behavior
	 * 
	 * @author Ines
	 *
	 */
	public enum Mode {
		CHASE , SCATTER , FRIGHTENED;
	}

	public static boolean redFlag = true;
	public static boolean pinkFlag = true;
	public static boolean blueFlag = true;
	public static boolean orangeFlag = true;
	public static boolean male = true;
	public static int powerUps = 4;

	public static Pacman pacman;	
	static RedGhost redGhost;
	static PinkGhost pinkGhost;
	static OrangeGhost orangeGhost;
	static BlueGhost blueGhost;
	public static Maze maze;


	public static int mazeWidth;
	public static int mazeHeight;

	int level = 1;
	public static int collected_pills = 0;
	public static int generatedFruits = 0;

	public static Mode ghostMode;

	/**
	 * Game Constructor 
	 */
	public Game()
	{
		pacman = new Pacman();
		initLevel(true);
	}

	/**
	 * 
	 * Initializes the maze according with the boolean received, if true, which mean the player has won creates a new maze according to new level else creats the old maze
	 * @param newMaze boolean that represents if player has won or not
	 */
	public void initLevel(boolean newMaze)
	{
		if(newMaze)
		{
			maze = new Maze(level);
			mazeWidth = maze.maze.get(0).length;
			mazeHeight = maze.maze.size();
		}

		if(redFlag)
		{
			redGhost = new RedGhost();

			if(this.level == 1 || this.level == 3)
				redGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 11*GameEngine.TILE_DIMENSION));
			else if (this.level == 2)
				redGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 13*GameEngine.TILE_DIMENSION));
		}

		if(pinkFlag)
		{
			pinkGhost = new PinkGhost();

			if(this.level == 1 || this.level == 3)
				pinkGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 15*GameEngine.TILE_DIMENSION));
			else if(this.level == 2)
				pinkGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 17*GameEngine.TILE_DIMENSION));
		}

		if(orangeFlag)
		{
			orangeGhost = new OrangeGhost();

			if(this.level == 1 || this.level == 3)
				orangeGhost.setPosition(new Position(14*GameEngine.TILE_DIMENSION, 13*GameEngine.TILE_DIMENSION));
			else if(this.level == 2)
				orangeGhost.setPosition(new Position(14*GameEngine.TILE_DIMENSION, 15*GameEngine.TILE_DIMENSION));
		}

		if(blueFlag)
		{
			blueGhost = new BlueGhost();
			if(this.level == 1 || this.level == 3)
				blueGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 13*GameEngine.TILE_DIMENSION));
			else if (this.level == 2)
				blueGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 15*GameEngine.TILE_DIMENSION));

		}


		Game.pacman.setAlive(true);
		Game.pacman.setOrientation(2);
		if(this.level == 1 || this.level == 3)
			pacman.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 17*GameEngine.TILE_DIMENSION));
		else if (this.level == 2)
			pacman.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 19*GameEngine.TILE_DIMENSION));

		ghostMode = Mode.CHASE;
	}
	
	/**
	 * Returns Pacman in game
	 * @return Pacman in game
	 */
	public Pacman getPacman()
	{
		return pacman;
	}

	/**
	 * Returns RedGhost in game
	 * @return RedGhost in game
	 */
	public RedGhost getRedGhost(){
		return redGhost;
	}

	/**
	 * Returns PinkGhost in game
	 * @return PinkGhost in game
	 */
	public PinkGhost getPinkGhost(){
		return pinkGhost;
	}

	/**
	 * Returns OrangeGhost in game
	 * @return OrangeGhost in game
	 */
	public OrangeGhost getOrangeGhost(){
		return orangeGhost;
	}

	/**
	 * Returns BlueGhost in game
	 * @return BlueGhost in game
	 */
	public BlueGhost getBlueGhost(){
		return blueGhost;
	}

	/**
	 * Returns game level
	 * @return game level
	 */
	public int getLevel()
	{
		return level;
	}

	/**
	 * Checks collision between Pac-man and Ghost in game if killPacman is true else checks if a collision has occurred
	 * @param killPacman boolean to know if the function is used to check collisions just between Pac-man and Ghosts 
	 * @return <code>true</code> if a collision has occurred.
	 */
	public boolean checkCharacterColision(boolean killPacman)
	{
		boolean result = false;

		Position pac = new Position(pacman.position.x + 10, pacman.position.y + 10);
		Position r = new Position(0,0);
		Position p = new Position(0,0);
		Position b = new Position(0,0);
		Position o = new Position(0,0);

		if(redFlag)
			r = new Position(redGhost.position.x + 10, redGhost.position.y + 10);

		if(pinkFlag) 
			p = new Position(pinkGhost.position.x + 10, pinkGhost.position.y + 10);

		if(blueFlag)
			b = new Position(blueGhost.position.x + 10, blueGhost.position.y + 10);

		if(orangeFlag)
			o = new Position(orangeGhost.position.x + 10, orangeGhost.position.y + 10);

		if(pacman.power_timer == 0)
		{
			if((pac.equals(r) && redGhost.getAlive()
					|| pac.equals(p) && pinkGhost.getAlive()
					|| pac.equals(b) && blueGhost.getAlive()
					|| pac.equals(o) && orangeGhost.getAlive()))
			{
				if(killPacman)
				{
					pacman.lifes--;
					pacman.alive = false;
				}
				result = true;
			}
		}
		else
		{			
			if(pac.equals(r))
			{
				redGhost.alive = false;
				redGhost.velocity = 4;
				pacman.score += 200;
				result = true;
			}
			if(pac.equals(p))
			{
				pinkGhost.alive = false;
				pinkGhost.velocity = 4;
				pacman.score += 200;
				result = true;
			}
			if(pac.equals(b))
			{
				blueGhost.alive = false;
				blueGhost.velocity = 4;
				pacman.score += 200;
				result = true;
			}
			if(pac.equals(o))
			{
				orangeGhost.alive = false;
				orangeGhost.velocity = 4;
				pacman.score += 200;
				result = true;
			}
		}


		return result;
	}

	/**
	 * Updates to the next level
	 */
	public void nextLevel()
	{
		this.level++;

		this.collected_pills = 0;
	}

	/**
	 * generates fruit
	 */
	public void generateFruit() {

		if(generatedFruits == level)
			return;

		Random random = new Random();

		for(int i = 0; i < 3; ++i)
		{
			int x =  random.nextInt(mazeWidth);
			int y = random.nextInt(mazeHeight);

			if(maze.isPoint(new Position(x, y)))
			{
				maze.maze.get(y)[x] = 'F';
				generatedFruits++;
				break;
			}
		}
	}

	/**
	 * Updates elements in game
	 * @param frameWidth Width of frame
	 */
	public void updateElements(int frameWidth)
	{
		updatePacman(frameWidth);

		if(pacman.getPower() > 0 && GameEngine.refresh %  25 == 0)
		{
			pacman.decPower();

			if(pacman.getPower() == 0)
				ghostMode = Mode.CHASE;
		}

		if(GameEngine.refresh == 50*7 || GameEngine.refresh == 50*34 || GameEngine.refresh == 50*59 || GameEngine.refresh == 50*84)
			ghostMode = Mode.CHASE;
		else if(GameEngine.refresh == 50*27 || GameEngine.refresh == 50*54 || GameEngine.refresh == 50*79)
			ghostMode = Mode.SCATTER;

		if(collected_pills >= maze.getPills()/3)
			generateFruit();

		if(redFlag)
			updateRedGhost();
		if(pinkFlag)
			updatePinkGhost();
		if(orangeFlag)
			updateOrangeGhost();
		if(blueFlag)
			updateBlueGhost();

		if(pacman.getAlive())
			checkCharacterColision(true);
	}

	/**
	 * Updates Pac-man movement and animation
	 * @param frameWidth Wisth of frame
	 */
	public void updatePacman(int frameWidth)
	{
		if(GameEngine.refresh%4 == 0)
			pacman.updateAnimation();

		if(pacman.getAlive())
			pacman.updateMovement(GameEngine.inputKey);
	}

/**
 * Updates Red Ghosts movement
 */
	public void updateRedGhost()
	{
		getRedGhost().moveGhost();
	}

	/**
	 * Updates Pink Ghosts movement
	 */
	public void updatePinkGhost()
	{
		getPinkGhost().moveGhost();
	}

	/**
	 * Updates Blue Ghosts movement
	 */
	public void updateBlueGhost()
	{
		if(collected_pills  >= 30 && getBlueGhost().house)
		{
			getBlueGhost().setOrientation(0);
			getBlueGhost().house = false;
		}

		getBlueGhost().moveGhost();
	}

	/**
	 * Updates Orange Ghosts movement
	 */
	public void updateOrangeGhost()
	{
		if(collected_pills >= maze.getPills()/3 && getOrangeGhost().house)
		{
			getOrangeGhost().setOrientation(0);
			getOrangeGhost().house = false;
		}

		getOrangeGhost().moveGhost();
	}

}



