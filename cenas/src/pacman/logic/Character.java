package pacman.logic;

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
	
	public boolean setOrientation(int o)
	{
		if(o == orientation)
			return false;
		
		orientation = o;
		return true;
	}
	
	public Position getTilePosition(int width, int height) 
	{
		int x = position.x / width;
		int y = position.y / height;
				
		return new Position(x, y);
	}
	
	public boolean moveUp(int width, int height, Maze maze)
	{
		Position p = getTilePosition(width, height - 1);
		
		if(maze.isWall(p))
			return false;
		
		position.y -= velocity;
		return true;
	}
	
	public void setPosition(Position p)
	{
		this.position = p;
	}
}
