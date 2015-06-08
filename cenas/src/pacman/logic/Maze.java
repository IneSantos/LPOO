package pacman.logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**  
* 
* Maze.java - a simple class that represents the maze.
* 
*/
public class Maze {
	
	
	/**
	 * maze representation.
	 * 
	 */
	public ArrayList<char[]> maze;

	/**
	 * number of pills on the maze
	 */
	int pills = 0;
	
	
/**
 * Maze's Constructor of level level.
 * 
 * @param level level of maze to construct
 */
	public Maze(int level)
	{ 
		loadMaze(level);

		for(int i = 0; i < maze.size(); ++i)
			for (int j = 0; j < maze.get(0).length; ++j)
				if(maze.get(i)[j] == 'P' ||maze.get(i)[j] == '.' || maze.get(i)[j] == 'D')
					this.pills++;
	}

	/**
	 * Loads the correspondent maze for each level
	 * 
	 * @param level level of maze to load
	 */
	private void loadMaze(int level) 
	{
		maze = new ArrayList<char[]>();

		Scanner scan = new Scanner(getClass().getResourceAsStream("/resources/mazes/maze" + level + ".txt"));
		while (scan.hasNextLine())
			maze.add(scan.nextLine().toCharArray());

		scan.close();

		correctPowerUps();

	}

	/**
	 * Generates Power ups according to what is established in settings
	 */
	private void correctPowerUps()
	{
		int p = 0; 
		for(int i = 0 ; i < maze.size(); ++i)
			for(int j = 0; j < maze.get(0).length; ++j)
				if(maze.get(i)[j] == 'P')
				{
					if(p >= Game.powerUps)
						maze.get(i)[j] = '.';
					else p++;
				}

		while(p < Game.powerUps)
		{
			Random random = new Random();
			int x = random.nextInt(maze.get(0).length);
			int y = random.nextInt(maze.size());

			if(maze.get(y)[x] == '.')
				{
					maze.get(y)[x] = 'P';
					p++;
				}
		}


	}
	
	/**
	 * Checks if a given position is a wall
	 * 
	 * @param p position position to test
	 * @return <code>true</code> if the position is a wall.
	 */
	public boolean isWall(Position p)
	{
		if(p.x >= 0 && p.x < Game.mazeWidth)
			if(maze.get(p.y)[p.x] == 'X')
				return true;
		return false;
	}

	/**
	 * Checks if a given position is a point
	 * 
	 * @param p position to test
	 * @return <code>true</code> if the position is a point.
	 */
	public boolean isPoint(Position p)
	{
		if(p.x >= 0 && p.x < Game.mazeWidth)
			if(maze.get(p.y)[p.x] == '.' || maze.get(p.y)[p.x] == 'D')
				return true;
		return false;
	}

	/**
	 * Checks if a given position is a power up
	 * 
	 * @param p position to test
	 * @return <code>true</code> if the position is a power up.
	 */
	public boolean isPowerPoint(Position p)
	{
		if(p.x > 0 && p.x < Game.mazeWidth)
			if(maze.get(p.y)[p.x] == 'P')
				return true;
		return false;
	}

	/**
	 * Checks if a given position is the door to the ghost house
	 * 
	 * @param p position to test
	 * @return <code>true</code> if the position is the door to the ghost house.
	 */
	public boolean isDoor(Position p)
	{
		if(p.x > 0 && p.x < Game.mazeWidth)
			if(maze.get(p.y)[p.x] == 'S')
				return true;
		return false;
	}
	
	/**
	 * Checks if a given position is a decision point
	 * 
	 * @param p position to test
	 * @return <code>true</code> if the position is a decision point.
	 */
	public boolean isDecisionPoint(Position p)
	{
		if(p.x > 0 && p.x < Game.mazeWidth)
			if(maze.get(p.y)[p.x] == 'D' || maze.get(p.y)[p.x] == 'd')
				return true;

		return false;
	}

	/**
	 * Checks if a given position is a special point
	 *  
	 * @param p position to test
	 * @return <code>true</code> if the position is a special point.
	 */
	public boolean isSpecial(Position p)
	{
		if(p.x >= 0 && p.x < Game.mazeWidth)
			if(maze.get(p.y)[p.x] == 's')
				return true;

		return false;
	}

	/**
	 * Checks if a given position is a fruit point
	 * 
	 * @param p position to test
	 * @return <code>true</code> if the position is a fruit point.
	 */
	public boolean isFruit(Position p)
	{
		if(p.x >= 0 && p.x < Game.mazeWidth)
			if(maze.get(p.y)[p.x] == 'F')
				return true;

		return false;
	}

	/**
	 * Returns the number of pills in the maze
	 * 
	 * @return number of pills in the maze
	 */
	public int getPills() {
		return pills;
	}
}
