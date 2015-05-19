package pacman.logic;

import pacman.GUI.Application;


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

	public void updateMovement(int tileWidth, int tileHeight, Maze maze) 
	{		
		if(orientation == 0 && position.y >= 0)
			moveUp(tileWidth, tileHeight, maze);
		else if (orientation == 1)
			moveRight(tileWidth, tileHeight, maze);
		else if(orientation == 2 && position.y <= Application.frame.getContentPane().getHeight() - 24)
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
