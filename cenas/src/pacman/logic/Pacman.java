package pacman.logic;

import java.awt.event.KeyEvent;


public class Pacman extends Character {
	
	int score;
	int animation;
	int power;
	int lifes;

	public Pacman()
	{
		super();
		score = 0;
		animation = 0;
		power = 0;
		lifes = 3;
	}
	
	public int updateAnimation()
	{
		animation++;
		
		if (animation > 3)
			animation = 0;
		
		return animation;
	}

	public void updateMovement(int inputKey, int tileWidth, int tileHeight, Maze maze) 
	{	
		if(position.x + tileWidth >= maze.maze[0].length * tileWidth && orientation == 1)
		{
			if(position.x == maze.maze[0].length * tileWidth)
				position.x = -tileWidth;
		}
		else if(position.x <= 0 && orientation == 3)
		{
			if(position.x == -tileWidth)
				position.x = maze.maze[0].length * tileWidth;
		}
		else if(position.x % tileWidth == 0 && position.y % tileHeight == 0 && inputKey != 0)
		{
			if(inputKey == KeyEvent.VK_UP && !maze.isWall(getTilePosition(position.x, position.y - 1, tileWidth, tileHeight)))
				setOrientation(0);
			else if(inputKey == KeyEvent.VK_DOWN && !maze.isWall(getTilePosition(position.x, position.y + tileHeight, tileWidth, tileHeight)))
				setOrientation(2);
			else if(inputKey == KeyEvent.VK_RIGHT && !maze.isWall(getTilePosition(position.x + tileWidth, position.y, tileWidth, tileHeight)))
				setOrientation(1);
			else if(inputKey == KeyEvent.VK_LEFT && !maze.isWall(getTilePosition(position.x - 1, position.y, tileWidth, tileHeight)))
				setOrientation(3);
			
			inputKey = 0;
		}
		
		if(orientation == 0)
			moveUp(tileWidth, tileHeight, maze);
		else if (orientation == 1)
			moveRight(tileWidth, tileHeight, maze);
		else if(orientation == 2)
			moveDown(tileWidth, tileHeight, maze);
		else if(orientation == 3)
			moveLeft(tileWidth, tileHeight, maze);
		
		 //TODO
		if(position.x/tileWidth >= 0 && position.x/tileWidth < maze.maze[0].length)
			if(maze.isPoint(new Position(position.x / tileWidth, position.y / tileHeight)) || maze.isPowerPoint(new Position(position.x / tileWidth, position.y / tileHeight)))
				maze.maze[position.y / tileHeight][position.x / tileWidth] = ' ';
	}
	
	public int getAnimation()
	{
		return animation;	
	}

}
