package pacman.logic;

import java.util.Random;

import pacman.GUI.GameEngine;

public class Ghost extends Character {

	public enum Mode {
		CHASE , SCATTER , FRIGHTENED
	}

	int state;
	public Mode mode;
	public Position target;

	public Ghost(Position target){
		this.orientation = 3; //sempre que sai da casa sai com a orientacao para a esquerda
		this.mode = Mode.SCATTER;
		this.velocity = 2;
		this.alive = true;
		this.target = target;
	}

	private int generateOri(){
		Random random = new Random();
		int rand = random.nextInt(4);
		return rand;
	}
	
	private boolean tieWin(Maze maze)
	{
		// UP > LEFT > DOWN
		
		if(!maze.isWall(getTilePosition(position.x, position.y - 1))){
			return moveUp(maze);
		}
		if(!maze.isWall(getTilePosition(position.x - 1, position.y))){
			return moveLeft(maze);
		}
		if(!maze.isWall(getTilePosition(position.x, position.y + GameEngine.TILE_DIMENSION)))
		{
			return moveDown(maze);
		}
		return false;
	}
	
	public void switchMode()
	{
		if(mode == Mode.CHASE)
			updateOrientation(Game.maze, Game.pacman.position);
		else if (mode == Mode.SCATTER)
			updateOrientation(Game.maze, this.target);
	}

	public void updateOrientation(Maze maze, Position target)
	{
		if(this.orientation == 0) //UP
		{
			float right = testRightMove(target);
			float left = testLeftMove(target);
			float up = testUpMove(target);
			
			if(right < up && right < left)
			{
				if(moveRight(maze))
				{
					this.orientation = 1;
					return;
				}
				else if(left < up)
				{
					if(moveLeft(maze))
					{
						this.orientation = 3;
						return;
					}
				}
				else moveUp(maze);
			}
			else if(left < right && left < up)
			{
				if(moveLeft(maze))
				{
					this.orientation = 3;
					return;
				}
				else if(right < up)
				{
					if(moveRight(maze))
					{
						this.orientation = 1;
						return;
					}
				}
				else moveUp(maze);
			}
			else if (up <  left && up < right)
			{
				if(moveUp(maze))
					return;
				else if (left < right)
				{
					if(moveLeft(maze))
					{
						this.orientation = 3;
						return;
					}
				}
				else moveRight(maze);
			}
		}
		else if(this.orientation == 2) //DOWN
		{
			float right = testRightMove(target);
			float left = testLeftMove(target);
			float down = testDownMove(target);
			
			if(right < down && right < left)
			{
				if(moveRight(maze))
				{
					this.orientation = 1;
					return;
				}
				else if(left < down)
				{
					if(moveLeft(maze))
					{
						this.orientation = 3;
						return;
					}
				}
				else moveDown(maze);
			}
			else if(left < right && left < down)
			{
				if(moveLeft(maze))
				{
					this.orientation = 3;
					return;
				}
				else if(right < down)
				{
					if(moveRight(maze))
					{
						this.orientation = 1;
						return;
					}
				}
				else moveDown(maze);
			}
			else if (down <  left && down < right)
			{
				if(moveDown(maze))
					return;
				else if (left < right)
				{
					if(moveLeft(maze))
					{
						this.orientation = 3;
						return;
					}
				}
				else moveRight(maze);
			}
		}
		else if(this.orientation == 1) //RIGHT
		{
			float right = testRightMove(target);
			float down = testDownMove(target);
			float up = testUpMove(target);
			
			if(down < up && down < right)
			{
				if(moveDown(maze))
				{
					this.orientation = 2;
					return;
				}
				else if(up < right)
				{
					if(moveUp(maze))
					{
						this.orientation = 0;
						return;
					}
				}
				else moveRight(maze);
			}
			else if(up < right && up < down)
			{
				if(moveUp(maze))
				{
					this.orientation = 0;
					return;
				}
				else if(right < down)
				{
					if(moveRight(maze))
					{
						this.orientation = 2;
						return;
					}
				}
				else moveRight(maze);
			}
			else if (right <  up && right < down)
			{
				if(moveRight(maze))
					return;
				else if (down < up)
				{
					if(moveDown(maze))
					{
						this.orientation = 2;
						return;
					}
				}
				else moveUp(maze);
			}
		}
		else if(this.orientation == 3) //LEFT
		{
			float left = testLeftMove(target);
			float down = testDownMove(target);
			float up = testUpMove(target);
			
			if(down < up && down < left)
			{
				if(moveDown(maze))
				{
					this.orientation = 2;
					return;
				}
				else if(up < left)
				{
					if(moveUp(maze))
					{
						this.orientation = 0;
						return;
					}
				}
				else moveLeft(maze);
			}
			else if(up < left && up < down)
			{
				if(moveUp(maze))
				{
					this.orientation = 0;
					return;
				}
				else if(left < down)
				{
					if(moveRight(maze))
					{
						this.orientation = 2;
						return;
					}
				}
				else moveLeft(maze);
			}
			else if (left <  up && left < down)
			{
				if(moveLeft(maze))
					return;
				else if (down < up)
				{
					if(moveDown(maze))
					{
						this.orientation = 2;
						return;
					}
				}
				else moveUp(maze);
			}
		}
		
		if(moveUp(maze) && this.orientation != 2)
		{
			this.orientation = 0;
			return;
		}
		else if(moveLeft(maze) && this.orientation != 1)
		{
			this.orientation = 3;
			return;
		}
		else if(this.orientation != 0)
		{
			moveDown(maze);
			this.orientation = 2;
			return;
		}
		
	}
	
	private int calculateDistance(int x, int y, Position target)
	{
		return (int) Math.sqrt(Math.pow((x - target.x),2) + Math.pow((y - target.y),2));
	}

	private int testDownMove(Position target) {
		return calculateDistance(this.position.x, position.y + 1, target);
	}

	private int testUpMove(Position target) {
		return calculateDistance(this.position.x, this.position.y-1, target);
	}

	private int testLeftMove(Position target) {
		return calculateDistance(position.x - 1, this.position.y, target);
	}

	private int testRightMove(Position target) {
		return calculateDistance(position.x + 1, this.position.y, target);
	}

	public void moveGhost(Maze maze)
	{
		if(!maze.isDecisionPoint(getTilePosition(position.x, position.y)))
		{
			if(this.orientation == 0 && moveUp(maze))
				return;
			else if(this.orientation == 2 && moveDown(maze))
				return;
			else if(this.orientation == 1 && moveRight(maze))
				return;
			else if(this.orientation == 3 && moveLeft(maze))
				return;
			else
				switchMode();
		}
		else if(position.x % GameEngine.TILE_DIMENSION == 0 && position.y % GameEngine.TILE_DIMENSION == 0)
		{
			System.out.println("dsnvosdnvsdnv");
			switchMode();
		}
		else
		{
			if(this.orientation == 0 && moveUp(maze))
				return;
			else if(this.orientation == 3 && moveLeft(maze))
				return;
			else if(this.orientation == 2 && moveDown(maze))
				return ;
			else if(this.orientation == 1 && moveRight(maze))
				return;
			else
				switchMode();
		}

	}
	
	//TODO: ainda falta fazer esta função!
	private void moveToGhostHouse(Maze maze){


		boolean valid = false;

		while(!valid){
			int rand = generateOri(); 
			if(position.x % GameEngine.TILE_DIMENSION == 0 && position.y % GameEngine.TILE_DIMENSION == 0 && rand != 0)
			{
				if(rand == 0 && !maze.isWall(getTilePosition(position.x, position.y - 1)))
				{
					setOrientation(0);
					valid = true;
				}
				else if(rand == 2 && !maze.isWall(getTilePosition(position.x, position.y + GameEngine.TILE_DIMENSION)) && !this.alive)
				{
					setOrientation(2);
					valid = true;
				}
				else if(rand == 1 && !maze.isWall(getTilePosition(position.x + GameEngine.TILE_DIMENSION, position.y)))
				{
					setOrientation(1);
					valid = true;
				}
				else if(rand == 3 && !maze.isWall(getTilePosition(position.x - 1, position.y)))
				{
					setOrientation(3);
					valid = true;
				}
			}
		}

		if(orientation == 0)
			moveUp(maze);
		else if (orientation == 1)
			moveRight(maze);
		else if(orientation == 2)
			moveDown(maze);
		else if(orientation == 3)
			moveLeft(maze);		

	}

}
