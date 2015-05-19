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
		velocity = 4;
		orientation = 0;
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
		Position p2 = getTilePosition(position.x + tileWidth - 1, position.y - velocity, tileWidth, tileHeight);

		boolean b1 = maze.isWall(p1);
		boolean b2 = maze.isWall(p2);

		if(b1 && b2)
		{
			position.y = (p1.y + 1) * tileHeight;

			return false;
		}
		else if(b1)
		{
			if(p2.x * tileWidth - position.x <= tileWidth/4)
				position.x = p2.x * tileWidth;

			return false;
		}
		else if(b2)
		{
			if(position.x - p1.x * tileWidth <= tileWidth/4)
				position.x = p1.x * tileWidth;

			return false;
		}

		position.y -= velocity;
		return true;
	}

	public boolean moveRight(int tileWidth, int tileHeight, Maze maze)
	{
		Position p1 = getTilePosition(position.x + tileWidth - 1 + velocity , position.y, tileWidth, tileHeight);
		Position p2 = getTilePosition(position.x + tileWidth - 1 + velocity , position.y + tileHeight - 1, tileWidth, tileHeight);

		if(p1.x >= maze.maze[0].length)
		{
			if(position.x >= Application.frame.getContentPane().getWidth())
				position.x = -tileWidth;
		}
		else
		{
			boolean b1 = maze.isWall(p1);
			boolean b2 = maze.isWall(p2);

			if(b1 && b2)
			{
				position.x = (p1.x - 1) * tileWidth;

				return false;
			}
			else if(b1)
			{
				if(p2.y * tileHeight - position.y <= tileHeight/4)
					position.y = p2.y * tileHeight; 

				return false;
			}
			else if(b2)
			{
				if(position.y - p1.y * tileHeight <= tileHeight/4)
					position.y = p1.y * tileHeight;

				return false;
			}
		}

		position.x += velocity;
		return true;
	}

	public boolean moveLeft(int tileWidth, int tileHeight, Maze maze)
	{		
		Position p1 = getTilePosition(position.x - velocity, position.y, tileWidth, tileHeight);
		Position p2 = getTilePosition(position.x - velocity, position.y + tileHeight - 1, tileWidth, tileHeight);


		if(p1.x < 0)
		{
			if(position.x <= -24)
				position.x = Application.frame.getContentPane().getWidth();
		}	
		else
		{ 
			boolean b1 = maze.isWall(p1);
			boolean b2 = maze.isWall(p2);

			if(b1 && b2)
			{
				position.x = (p1.x + 1) * tileWidth;

				return false;
			}
			else if(b1)
			{
				if(p2.y * tileHeight - position.y <= tileHeight/4)
					position.y = p2.y * tileHeight; 

				return false;
			}
			else if(b2)
			{
				if(position.y - p1.y * tileHeight <= tileHeight/4)
					position.y = p1.y * tileHeight;

				return false;
			}

		}
		position.x -= velocity;

		return true;
	}

	public boolean moveDown(int tileWidth, int tileHeight, Maze maze)
	{		
		Position p1 = getTilePosition(position.x, position.y + tileHeight - 1 + velocity, tileWidth, tileHeight);
		Position p2 = getTilePosition(position.x + tileWidth - 1, position.y + tileHeight - 1 + velocity, tileWidth, tileHeight);	

		boolean b1 = maze.isWall(p1);
		boolean b2 = maze.isWall(p2);

		if(b1 && b2)
		{
			position.y = (p1.y - 1) * tileHeight;

			return false;
		}
		else if(b1)
		{
			if(p2.x * tileWidth - position.x <= tileWidth/4)
				position.x = p2.x * tileWidth;

			return false;
		}
		else if(b2)
		{
			if(position.x - p1.x * tileWidth <= tileWidth/4)
				position.x = p1.x * tileWidth;

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
