package pacman.logic;

import java.awt.event.KeyEvent;

import pacman.GUI.GameEngine;


public class Pacman extends Character {
	
	int score;
	int animation;
	int power;
	public int lifes;

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
	
	
	public void updateMovement(int inputKey, Maze maze) 
	{	
		//Portal á direita no labirinto
		if(position.x + GameEngine.TILE_DIMENSION >= maze.maze[0].length * GameEngine.TILE_DIMENSION && orientation == 1)
		{
			if(position.x == maze.maze[0].length * GameEngine.TILE_DIMENSION)
				position.x = -GameEngine.TILE_DIMENSION;
		}
		//Portal á esquerda no labirinto
		else if(position.x <= 0 && orientation == 3)
		{
			if(position.x == -GameEngine.TILE_DIMENSION)
				position.x = maze.maze[0].length * GameEngine.TILE_DIMENSION;
		}
		else if(position.x % GameEngine.TILE_DIMENSION == 0 && position.y % GameEngine.TILE_DIMENSION == 0 && inputKey != 0)
		{
			if(inputKey == KeyEvent.VK_UP && !maze.isWall(getTilePosition(position.x, position.y - 1)))
			{
				setOrientation(0);
				if(maze.isPowerPoint(position))
					this.power = 1;
			}
			else if(inputKey == KeyEvent.VK_DOWN && !maze.isWall(getTilePosition(position.x, position.y + GameEngine.TILE_DIMENSION)) && !maze.isDoor(getTilePosition(position.x, position.y + GameEngine.TILE_DIMENSION)))
			{
				setOrientation(2);
				if(maze.isPowerPoint(position))
					this.power = 1;
			}
			else if(inputKey == KeyEvent.VK_RIGHT && !maze.isWall(getTilePosition(position.x + GameEngine.TILE_DIMENSION, position.y)))
			{
				setOrientation(1);
				if(maze.isPowerPoint(position))
					this.power = 1;
			}
			else if(inputKey == KeyEvent.VK_LEFT && !maze.isWall(getTilePosition(position.x - 1, position.y))){
				setOrientation(3);
				if(maze.isPowerPoint(position))
					this.power = 1;
			}
				
			
			inputKey = 0;
		}
		
		if(orientation == 0)
			moveUp(maze);
		else if (orientation == 1)
			moveRight(maze);
		else if(orientation == 2)
			moveDown(maze);
		else if(orientation == 3)
			moveLeft(maze);
		
		 //TODO // eoiw
		if(position.x/GameEngine.TILE_DIMENSION >= 0 && position.x/GameEngine.TILE_DIMENSION < maze.maze[0].length)
			if(maze.isPoint(new Position(position.x / GameEngine.TILE_DIMENSION, position.y / GameEngine.TILE_DIMENSION)) || maze.isPowerPoint(new Position(position.x / GameEngine.TILE_DIMENSION, position.y / GameEngine.TILE_DIMENSION)))
				maze.maze[position.y / GameEngine.TILE_DIMENSION][position.x / GameEngine.TILE_DIMENSION] = ' ';
	}
	
	public int getAnimation()
	{
		return animation;	
	}
	
	public int getPower(){
		return power;
	}

}
