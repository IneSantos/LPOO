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
		velocity = 7;
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
	
	public boolean setOrientation(int o, int tileWidth, int tileHeight, Maze maze)
	{
		if(o == orientation)
			return false;
		
		Position p1 = null;
		Position p2 = null;
		
		if(o == 0)
		{
			p1 = getTilePosition(position.x - tileHeight, position.y - tileHeight, tileWidth, tileHeight);
			p2 = getTilePosition(position.x + tileWidth - tileHeight - 1, position.y + tileWidth - tileHeight - 1, tileWidth, tileHeight);		
		}
		else if(o == 1)
		{
			p1 = getTilePosition(position.x + tileWidth, position.y + tileWidth, tileWidth, tileHeight);
			p2 = getTilePosition(position.x + tileWidth + tileWidth - 1, position.y + tileHeight + tileWidth - 1, tileWidth, tileHeight);
		}
		else if(o == 2)
		{
			p1 = getTilePosition(position.x + tileHeight, position.y + tileHeight, tileWidth, tileHeight);
			p2 = getTilePosition(position.x + tileWidth + tileHeight - 1, position.y + tileHeight + tileWidth - 1, tileWidth, tileHeight);
		}
		else if(o == 3)
		{
			p1 = getTilePosition(position.x, position.y, tileWidth, tileHeight);
			p2 = getTilePosition(position.x + tileWidth - 1, position.y + tileHeight - 1, tileWidth, tileHeight);
		}
		
		if(!(maze.isWall(p1) && maze.isWall(p2)))
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
	
	public boolean moveUp(int width, int height, Maze maze)
	{
		Position p = getTilePosition(width, height);
		
		p.y--;
		
		if(maze.isWall(p))
			return false;
		
		position.y -= velocity;
		return true;
	}
	
	public boolean moveDown(int width, int height, Maze maze)
	{
		Position p = getTilePosition(width, height);
		
		p.y++;
		
		if(maze.isWall(p))
			return false;
		
		position.y += velocity;
		return true;
	}
	
	public boolean moveLeft(int width, int height, Maze maze)
	{		
		Position p = getTilePosition(width, height);
		
		p.x--;
		
		if(maze.isWall(p))
			return false;
		
		if(position.x <= -24)
			position.x = Application.frame.getContentPane().getWidth();

		position.x -= velocity;
		
		return true;
	}
	
	public boolean moveRight(int width, int height, Maze maze)
	{		
		Position p = getTilePosition(width, height);
		
		p.x++;
		
		if(maze.isWall(p))
			return false;
		
		if(position.x >= Application.frame.getContentPane().getWidth())
			position.x = -width;

		position.x += velocity;
		
		return true;
	}
	
	public void setPosition(Position p)
	{
		this.position = p;
	}
}
