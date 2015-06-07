package pacman.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
