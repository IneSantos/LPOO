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

	private boolean rightWin(Maze maze)
	{
		return moveRight(maze);
	}


	private boolean leftWin(Maze maze)
	{
		return moveLeft(maze);
	}

	private boolean upWin(Maze maze)
	{
		return moveUp(maze);
	}

	private boolean downWin(Maze maze)
	{
		return moveDown(maze);
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
	

	private boolean frontWin(Maze maze)
	{
		switch(this.orientation)
		{
		case 0:	
			return moveUp(maze);
		case 1:
			return moveRight(maze);
		case 2:
			return moveDown(maze);
		case 3:
			return moveLeft(maze);
		}

		return true;
	}


	public void updateOrientation(Maze maze)
	{

		//Par -> Vertical
		if(this.orientation%2 == 0)
		{
			float right = testRightMove();
			float left = testLeftMove();
			float front = testFrontMove();

			System.out.println(right + "    " + front);

			if(right < left && right < front)
			{
				if(moveRight(maze))
				{
					this.orientation = 1;
					return;
				}
				else if(left < front)
				{
					if(moveLeft(maze))
					{
						this.orientation = 3;
						return;
					}
				}
				else if (!frontWin(maze))
				{
					this.orientation = 3;
					moveLeft(maze);
					return;
				}
			}

			if(left < front && left < right)
			{
				if(moveLeft(maze))
				{
					this.orientation = 3;
					return;
				}
				else if(right < front)
				{
					if(moveRight(maze))
					{
						this.orientation = 1;
						return;
					}
				}
				else if (!frontWin(maze))
				{
					this.orientation = 1;
					moveRight(maze);
					return;
				}
			}

			if(front < left && front < right)
			{
				if(frontWin(maze))
					return;
				else if (right < left)
				{
					if(moveRight(maze))
						this.orientation = 1;
					return;
				}
				else if (moveLeft(maze))
				{
					this.orientation = 3;
					moveLeft(maze);
					return;
				}
				else 
				{
					this.orientation = 1;
					moveRight(maze);
					return;
				}

			}
		}
		//Impar -> Horizontal
		else
		{
			float up = testUpMove();
			float down = testDownMove();
			float front = testFrontMove();

			if(up < down && up < front)
			{
				if(moveUp(maze))
				{
					this.orientation = 0;
					return;
				}
				else if(down < front)
				{
					if(moveDown(maze))
					{
						this.orientation = 2;
						return;
					}
				}
				else if (!frontWin(maze))
				{
					this.orientation = 2;
					moveLeft(maze);
					return;
				}
			}

			if(down < front && down < up)
			{
				if(moveDown(maze))
				{
					this.orientation = 2;
					return;
				}
				else if(up < front)
				{
					if(moveUp(maze))
					{
						this.orientation = 0;
						return;
					}
				}
				else if (!frontWin(maze))
				{
					this.orientation = 0;
					moveUp(maze);
					return;
				}
			}

			if(front < down && front < up)
			{
				if(frontWin(maze))
					return;
				else if (up < down)
				{
					if(moveUp(maze))
						this.orientation = 0;
					return;
				}
				else if (moveDown(maze))
				{
					this.orientation = 2;
					moveDown(maze);
					return;
				}
				else 
				{
					this.orientation = 0;
					moveUp(maze);
					return;
				}

			}
		}
	}

	private float calculateDistance(int x, int y)
	{
		return (float) Math.sqrt(Math.pow((x - this.target.x),2) + Math.pow((y - this.target.y),2));
	}

	private float testFrontMove()
	{
		switch(this.orientation)
		{
		case 0:	
			return testUpMove();
		case 1:
			return testRightMove();
		case 2:
			return testDownMove();
		case 3:
			return testLeftMove();
		}

		return 0;
	}

	private float testDownMove() {
		return calculateDistance(this.position.x, position.y + 1);
	}

	private float testUpMove() {
		return calculateDistance(this.position.x, this.position.y-1);
	}

	private float testLeftMove() {
		return calculateDistance(position.x - 1, this.position.y);
	}

	private float testRightMove() {
		return calculateDistance(position.x + 1, this.position.y);
	}

	public void moveGhost(Maze maze){

		if(!maze.isDecisionPoint(getTilePosition(position.x, position.y)))
		{
			if(this.orientation == 0 && !maze.isWall(getTilePosition(position.x, position.y - 1)))
				moveUp(maze);
			else if(this.orientation == 2 && !maze.isWall(getTilePosition(position.x, position.y + GameEngine.TILE_DIMENSION)))
				moveDown(maze);
			else if(this.orientation == 1 && !maze.isWall(getTilePosition(position.x +/* GameEngine.TILE_DIMENSION*/1, position.y)))
				moveRight(maze);
			else if(this.orientation == 3 && !maze.isWall(getTilePosition(position.x - 1, position.y)))
				moveLeft(maze);
			else
				updateOrientation(maze);
		}
		else if(position.x % GameEngine.TILE_DIMENSION == 0 && position.y % GameEngine.TILE_DIMENSION == 0)
			updateOrientation(maze);
		else
		{
			if(this.orientation == 0)
				moveUp(maze);
			else if(this.orientation == 2)
				moveDown(maze);
			else if(this.orientation == 1)
				moveRight(maze);
			else if(this.orientation == 3)
				moveLeft(maze);
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
