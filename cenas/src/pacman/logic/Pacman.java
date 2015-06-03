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
	
	
	public void updateMovement(int inputKey) 
	{	
		//Portal á direita no labirinto
		if(position.x + GameEngine.TILE_DIMENSION >= Game.mazeWidth * GameEngine.TILE_DIMENSION && orientation == 1)
		{
			if(position.x == Game.maze.maze[0].length * GameEngine.TILE_DIMENSION)
				position.x = -GameEngine.TILE_DIMENSION;
		}
		//Portal á esquerda no labirinto
		else if(position.x <= 0 && orientation == 3)
		{
			if(position.x == -GameEngine.TILE_DIMENSION)
				position.x = Game.maze.maze[0].length * GameEngine.TILE_DIMENSION;
		}
		else if(position.x % GameEngine.TILE_DIMENSION == 0 && position.y % GameEngine.TILE_DIMENSION == 0 && inputKey != 0)
		{
			if(inputKey == KeyEvent.VK_UP && !Game.maze.isWall(getTilePosition(position.x, position.y - 1)))
			{
				setOrientation(0);
				if(Game.maze.isPowerPoint(position))
					this.power = 1;
			}
			else if(inputKey == KeyEvent.VK_DOWN && !Game.maze.isWall(getTilePosition(position.x, position.y + GameEngine.TILE_DIMENSION)) && !Game.maze.isDoor(getTilePosition(position.x, position.y + GameEngine.TILE_DIMENSION)))
			{
				setOrientation(2);
				if(Game.maze.isPowerPoint(position))
					this.power = 1;
			}
			else if(inputKey == KeyEvent.VK_RIGHT && !Game.maze.isWall(getTilePosition(position.x + GameEngine.TILE_DIMENSION, position.y)))
			{
				setOrientation(1);
				if(Game.maze.isPowerPoint(position))
					this.power = 1;
			}
			else if(inputKey == KeyEvent.VK_LEFT && !Game.maze.isWall(getTilePosition(position.x - 1, position.y))){
				setOrientation(3);
				if(Game.maze.isPowerPoint(position))
					this.power = 1;
			}
				
			
			inputKey = 0;
		}
		
		if(orientation == 0)
			moveUp();
		else if (orientation == 1)
			moveRight();
		else if(orientation == 2)
			moveDown();
		else if(orientation == 3)
			moveLeft();
		
		 //TODO // eoiw
		if(position.x/GameEngine.TILE_DIMENSION >= 0 && position.x/GameEngine.TILE_DIMENSION < Game.mazeWidth)
			if(Game.maze.isPoint(new Position(position.x / GameEngine.TILE_DIMENSION, position.y / GameEngine.TILE_DIMENSION)) || Game.maze.isPowerPoint(new Position(position.x / GameEngine.TILE_DIMENSION, position.y / GameEngine.TILE_DIMENSION)))
				Game.maze.maze[position.y / GameEngine.TILE_DIMENSION][position.x / GameEngine.TILE_DIMENSION] = ' ';
	}
	
	public int getAnimation()
	{
		return animation;	
	}
	
	public int getPower(){
		return power;
	}

}
