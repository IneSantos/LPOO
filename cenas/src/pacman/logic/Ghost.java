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

	public void moveGhost(Maze maze)
	{
		if(!maze.isDecisionPoint(getTilePosition(position.x, position.y)))
		{
			if(this.orientation == 0 && moveUp())
				return;
			else if(this.orientation == 2 && moveDown())
				return;
			else if(this.orientation == 1 && moveRight())
				return;
			else if(this.orientation == 3 && moveLeft())
				return;
			else
				switchMode();

		}
		else if(position.x % GameEngine.TILE_DIMENSION == 0 && position.y % GameEngine.TILE_DIMENSION == 0)
			switchMode();
		else
		{
			if(this.orientation == 0 && moveUp())
				return;
			else if(this.orientation == 3 && moveLeft())
				return;
			else if(this.orientation == 2 && moveDown())
				return ;
			else if(this.orientation == 1 && moveRight())
				return;
			else
				switchMode();
		}

	}

	public void switchMode()
	{
		if(mode == Mode.CHASE)
			updateOrientation(Game.pacman.position);
		else if (mode == Mode.SCATTER)
			updateOrientation(this.target);
		else if(mode == Mode.FRIGHTENED){
			//updateFrightened(new Position(13*GameEngine.TILE_DIMENSION,16*GameEngine.TILE_DIMENSION));
			//updateFrightened(new Position(14*GameEngine.TILE_DIMENSION,14*GameEngine.TILE_DIMENSION));
		}
	}

	private void updateFrightened(Position position) {
		/*
		 * Mudar de direcao o movimento!
		 */
		
	}

	public void updateOrientation(Position target)
	{
		if(this.orientation == 0) //UP
			updateOrientationFromUpMovement(target);
		else if(this.orientation == 1) //RIGHT
			updateOrientationFromRightMovement(target);
		else if(this.orientation == 2) //DOWN
			updateOrientationFromDownMovement(target);
		else if(this.orientation == 3) //LEFT
			updateOrientationFromLeftMovement(target);
	}

	private void updateOrientationFromLeftMovement(Position target) 
	{
		float down = testDownMove(target);
		float up = testUpMove(target);
		float left = testLeftMove(target);


		if(up < left)
		{
			if(up < down)
			{
				if(moveUp())
					this.orientation = 0;
				else if (down < left)
				{
					if(moveDown())
						this.orientation = 2;
					else moveLeft();
				}
				else if(moveLeft())
					return;
				else 
				{
					moveDown();
					this.orientation = 2;
				}
			}
			else if(moveDown())
				this.orientation = 2;
			else if(moveUp())
				this.orientation = 0;
			else moveLeft();
		}
		else if(down < left)
		{
			if(moveDown())
				this.orientation = 2;
			else if(moveLeft())
				return;
			else 
			{
				moveUp();
				this.orientation = 0;
			}
		}
		else
		{
			if (moveLeft())
				return;
			else if(down < up)
			{
				if (moveDown())
					this.orientation = 2;
				else
				{
					moveUp();
					this.orientation = 0;
				}
			}
			else if(moveUp())
				this.orientation = 0;
			else 
			{
				moveDown();
				this.orientation = 2;
			}
		}
	}

	private void updateOrientationFromDownMovement(Position target)	
	{
		float left = testLeftMove(target);
		float right = testRightMove(target);
		float down = testDownMove(target);

		if(right < down)
		{
			if(right < left)
			{
				if(moveRight())
					this.orientation = 1;
				else if (left < down)
				{
					if(moveLeft())
						this.orientation = 3;
					else moveDown();
				}
				else if(moveDown())
					return;
				else
				{
					moveLeft();
					this.orientation = 3;
				}
			}
			else if(moveLeft())
				this.orientation = 3;
			else if(moveRight())
				this.orientation = 1;
			else moveDown();
		}
		else if(left < down)
		{
			if(moveLeft())
				this.orientation = 3;
			else if(moveDown())
				return;
			else 
			{
				moveRight();
				this.orientation = 1;
			}
		}
		else
		{
			if (moveDown())
				return;
			else if(left < right)
			{
				if (moveLeft())
					this.orientation = 3;
				else
				{
					moveRight();
					this.orientation = 1;
				}
			}
			else if(moveRight())
				this.orientation = 1;
			else 
			{
				moveLeft();
				this.orientation = 3;
			}
		}
	}

	private void updateOrientationFromRightMovement(Position target) 	
	{
		float down = testDownMove(target);
		float up = testUpMove(target);
		float right = testRightMove(target);

		if(up < right)
		{
			if(up < down)
			{
				if(moveUp())
					this.orientation = 0;
				else if (down < right)
				{
					if(moveDown())
						this.orientation = 2;
					else moveRight();
				}
				else if(moveRight())
					return;
				else 
				{
					moveDown();
					this.orientation = 2;
				}
			}
			else if(moveDown())
				this.orientation = 2;
			else if(moveUp())
				this.orientation = 0;
			else moveRight();
		}
		else if(down < right)
		{
			if(moveDown())
				this.orientation = 2;
			else if(moveRight())
				return;
			else 
			{
				moveUp();
				this.orientation = 0;
			}
		}
		else
		{
			if (moveRight())
				return;
			else if(down < up)
			{
				if (moveDown())
					this.orientation = 2;
				else
				{
					moveUp();
					this.orientation = 0;
				}
			}
			else if(moveUp())
				this.orientation = 0;
			else 
			{
				moveDown();
				this.orientation = 2;
			}
		}
	}

	private void updateOrientationFromUpMovement(Position target) 
	{
		float left = testLeftMove(target);
		float right = testRightMove(target);
		float up = testUpMove(target);

		if(right < up)
		{
			if(right < left)
			{
				if(moveRight())
					this.orientation = 1;
				else if (left < up)
				{
					if(moveLeft())
						this.orientation = 3;
					else moveUp();
				}
				else if(moveUp())
					return;
				else
				{
					moveLeft();
					this.orientation = 3;
				}
			}
			else if(moveLeft())
				this.orientation = 3;
			else if(moveRight())
				this.orientation = 1;
			else moveUp();
		}
		else if(left < up)
		{
			if(moveLeft())
				this.orientation = 3;
			else if(moveUp())
				return;
			else 
			{
				moveRight();
				this.orientation = 1;
			}
		}
		else
		{
			if (moveUp())
				return;
			else if(left < right)
			{
				if (moveLeft())
					this.orientation = 3;
				else
				{
					moveRight();
					this.orientation = 1;
				}
			}
			else if(moveRight())
				this.orientation = 1;
			else 
			{
				moveLeft();
				this.orientation = 3;
			}
		}
	}

	private float calculateDistance(int x, int y, Position target)
	{
		return (float) Math.sqrt(Math.pow((x - target.x),2) + Math.pow((y - target.y),2));
	}

	private float testDownMove(Position target) {
		return calculateDistance(this.position.x, position.y + 1, target);
	}

	private float testUpMove(Position target) {
		return calculateDistance(this.position.x, this.position.y-1, target);
	}

	private float testLeftMove(Position target) {
		return calculateDistance(position.x - 1, this.position.y, target);
	}

	private float testRightMove(Position target) {
		return calculateDistance(position.x + 1, this.position.y, target);
	}

	private void moveFromGhostHouse(Maze maze){
		/*
		 * Sai no inicio do jogo...
		 */
	}


	//TODO: ainda falta fazer esta função!
	private void moveToGhostHouse(Maze maze){
		/*
		 * So vai para la quando morre!!! 
		 */
	}
	
	
	

}
