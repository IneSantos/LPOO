package pacman.logic;

import pacman.GUI.Application;

public class Character {

	Position position;
	int velocity;
	int orientation;
	boolean alive;

	public Character()
	{
		position = new Position(100, 100);
		velocity = 2;
		orientation = 3;
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

	public Position getTilePosition(int width, int height, int tileWidth, int tileHeight) 
	{
		int x = width / tileWidth;
		int y = height / tileHeight;

		return new Position(x, y);
	}

	public boolean moveUp(int tileWidth, int tileHeight, Maze maze)
	{
		Position p1 = getTilePosition(position.x, position.y - velocity, tileWidth, tileHeight);

		if(maze.isWall(p1))
		{
			position.y = (p1.y + 1) * tileHeight;

			return false;
		}
		
		position.y -= velocity;
		return true;
	}

	public boolean moveRight(int tileWidth, int tileHeight, Maze maze)
	{
		Position p1 = getTilePosition(position.x + tileWidth - 1 + velocity , position.y, tileWidth, tileHeight);

		if(p1.x >= maze.maze[0].length)
		{
			if(position.x >= Application.frame.getContentPane().getWidth())
				position.x = -tileWidth;
		}
		else
		{
			if(maze.isWall(p1))
			{
				position.x = (p1.x - 1) * tileWidth;

				return false;
			}
		}

		position.x += velocity;
		return true;
	}

	public boolean moveLeft(int tileWidth, int tileHeight, Maze maze)
	{		
		Position p1 = getTilePosition(position.x - velocity, position.y, tileWidth, tileHeight);

		if(p1.x < 0)
		{
			if(position.x <= -24)
				position.x = Application.frame.getContentPane().getWidth();
		}	
		else
		{

			if(maze.isWall(p1))
			{
				position.x = (p1.x + 1) * tileWidth;

				return false;
			}
		}
		position.x -= velocity;

		return true;
	}

	public boolean moveDown(int tileWidth, int tileHeight, Maze maze)
	{		
		Position p1 = getTilePosition(position.x, position.y + tileHeight - 1 + velocity, tileWidth, tileHeight);
		
		if(maze.isWall(p1))
		{
			position.y = (p1.y - 1) * tileHeight;

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
