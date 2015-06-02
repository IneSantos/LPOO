package pacman.logic;

import pacman.GUI.Application;
import pacman.GUI.GameEngine;

public class Character {

	Position position;
	int velocity;
	int orientation;
	boolean alive;

	public Character()
	{
		position = new Position(100, 100);
		velocity = 2;
		orientation = 2;
		alive = true;
	}

	public int getOrientation()
	{
		return orientation;
	}

	public int getX()
	{
		return position.x;
	}

	public int getY()
	{
		return position.y;
	}

	public boolean setOrientation(int o)
	{
		if(o == orientation)
			return false;

		orientation = o;
		return true;
	}

	public Position getTilePosition(int width, int height) 
	{
		int x = width / GameEngine.TILE_DIMENSION;
		int y = height / GameEngine.TILE_DIMENSION;

		return new Position(x, y);
	}

	public boolean moveUp(Maze maze)
	{
		Position p1 = getTilePosition(position.x, position.y - velocity);

		if(maze.isWall(p1))
		{
			position.y = (p1.y + 1) * GameEngine.TILE_DIMENSION;

			return false;
		}
		
		position.y -= velocity;
		return true;
	}

	public boolean moveRight(Maze maze)
	{
		Position p1 = getTilePosition(position.x + GameEngine.TILE_DIMENSION - 1 + velocity , position.y);

		if(p1.x >= maze.maze[0].length)
		{
			if(position.x >= Application.frame.getContentPane().getWidth())
				position.x = -GameEngine.TILE_DIMENSION;
		}
		else
		{
			if(maze.isWall(p1))
			{
				position.x = (p1.x - 1) * GameEngine.TILE_DIMENSION;

				return false;
			}
		}

		position.x += velocity;
		return true;
	}

	public boolean moveLeft(Maze maze)
	{		
		Position p1 = getTilePosition(position.x - velocity, position.y);

		if(p1.x < 0)
		{
			if(position.x <= -24)
				position.x = Application.frame.getContentPane().getWidth();
		}	
		else
		{

			if(maze.isWall(p1))
			{
				position.x = (p1.x + 1) * GameEngine.TILE_DIMENSION;

				return false;
			}
		}
		position.x -= velocity;

		return true;
	}

	public boolean moveDown(Maze maze)
	{		
		Position p1 = getTilePosition(position.x, position.y + GameEngine.TILE_DIMENSION - 1 + velocity);
		
		if(maze.isWall(p1))
		{
			position.y = (p1.y - 1) * GameEngine.TILE_DIMENSION;

			return false;
		}
		
		position.y += velocity;

		return true;
	}

	public void setPosition(Position p)
	{
		this.position = p;
	}
}
