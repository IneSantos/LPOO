package pacman.logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Maze {

	public ArrayList<char[]> maze;

	int pills = 0;

	public Maze(int level)
	{ 
		loadMaze(level);

		for(int i = 0; i < maze.size(); ++i)
			for (int j = 0; j < maze.get(0).length; ++j)
				if(maze.get(i)[j] == 'P' ||maze.get(i)[j] == '.' || maze.get(i)[j] == 'D')
					this.pills++;
	}

	private void loadMaze(int level) 
	{
		maze = new ArrayList<char[]>();

		Scanner scan = new Scanner(getClass().getResourceAsStream("/resources/mazes/maze" + level + ".txt"));
		while (scan.hasNextLine())
			maze.add(scan.nextLine().toCharArray());

		scan.close();

		correctPowerUps();

	}

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

	public boolean isWall(Position p)
	{
		if(p.x >= 0 && p.x < Game.mazeWidth)
			if(maze.get(p.y)[p.x] == 'X')
				return true;
		return false;
	}

	public boolean isPoint(Position p)
	{
		if(p.x >= 0 && p.x < Game.mazeWidth)
			if(maze.get(p.y)[p.x] == '.' || maze.get(p.y)[p.x] == 'D')
				return true;
		return false;
	}

	public boolean isPowerPoint(Position p)
	{
		if(p.x > 0 && p.x < Game.mazeWidth)
			if(maze.get(p.y)[p.x] == 'P')
				return true;
		return false;
	}

	public boolean isDoor(Position p)
	{
		if(p.x > 0 && p.x < Game.mazeWidth)
			if(maze.get(p.y)[p.x] == 'S')
				return true;
		return false;
	}

	public boolean isDecisionPoint(Position p)
	{
		if(p.x > 0 && p.x < Game.mazeWidth)
			if(maze.get(p.y)[p.x] == 'D' || maze.get(p.y)[p.x] == 'd')
				return true;

		return false;
	}

	public boolean isSpecial(Position p)
	{
		if(p.x >= 0 && p.x < Game.mazeWidth)
			if(maze.get(p.y)[p.x] == 's')
				return true;

		return false;
	}

	public boolean isFruit(Position p)
	{
		if(p.x >= 0 && p.x < Game.mazeWidth)
			if(maze.get(p.y)[p.x] == 'F')
				return true;

		return false;
	}

	public int getPills() {
		return pills;
	}
}
