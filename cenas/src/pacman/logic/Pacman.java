package pacman.logic;


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

	public void updateMovement(int Pwidth, int Pheight, Maze maze) 
	{
		int width = Pwidth/maze.maze[0].length;
		int height = Pheight/maze.maze.length;
		
		if(orientation == 0 && position.y >= 0)
			moveUp(width, height, maze);
		else if (orientation == 1)
		{
			if(position.x >= width)
				position.x = -24;
	
			position.x += velocity;
		}
		else if(orientation == 2 && position.y <= height - 24)
			position.y += velocity;
		else if(orientation == 3)
		{
			if(position.x <= -24)
				position.x = width;
	
			position.x -= velocity;
		}
	}
	
	public int getAnimation()
	{
		return animation;	
	}


	
	
	

}
