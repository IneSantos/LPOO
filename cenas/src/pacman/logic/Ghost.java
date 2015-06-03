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
		this.mode = Mode.CHASE;
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

	private boolean moveFront(Maze maze)
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


	public void updateOrientation()
	{
		if(mode == Mode.CHASE)
			updateOrientationChase(Game.maze, Game.pacman.position);
		else if (mode == Mode.SCATTER)
			updateOrientationScatter(Game.maze, this.target);
	}

	public void updateOrientationChase(Maze maze, Position target)
	{
		System.out.println("chase");
		//OrientationPar -> Vertical
		if(this.orientation%2 == 0)
		{
			float right = testRightMove(target);
			float left = testLeftMove(target);
			float front = testFrontMove(target);

			if(right < left && right < front) //Caminho a direita mais proximo de target
			{
				if(moveRight(maze)) //Se puder mover para a direita, altera orientacao
				{
					this.orientation = 1;
					return;
				}
				// Se nao puder mover para a direita
				else if(left < front)  //Se a distancia pela esquerda for menor do que continuar em frente
				{
					if(moveLeft(maze))	//Se puder mover para a esquerda altera orientacao
					{
						this.orientation = 3;
						return;
					}
				}
				else if (!moveFront(maze)) // Se mover em frente for a melhor opcao, verifica se pode mover ou nao. caso nao posse,mve para a esquerda
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
				else if (!moveFront(maze))
				{
					this.orientation = 1;
					moveRight(maze);
					return;
				}
			}

			if(front < left && front < right)
			{
				if(moveFront(maze))
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
		//OrientationImpar -> Horizontal
		else
		{
			float up = testUpMove(target);
			float down = testDownMove(target);
			float front = testFrontMove(target);

			System.out.println("mouco");

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
				else if (!moveFront(maze))
				{
					this.orientation = 2;
					moveDown(maze);
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
				else if (!moveFront(maze))
				{
					this.orientation = 0;
					moveUp(maze);
					return;
				}
			}

			if(front < down && front < up)
			{
				if(moveFront(maze))
					return;
				else if (up < down)
				{
					if(moveUp(maze))
					{
						this.orientation = 0;
						return;
					}
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

	public void updateOrientationScatter(Maze maze, Position target)
	{

		//Par -> Vertical
		if(this.orientation%2 == 0)
		{
			float right = testRightMove(target);
			float left = testLeftMove(target);
			float front = testFrontMove(target);

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
				else if (!moveFront(maze))
				{
					this.orientation = 3;
					moveLeft(maze);
					return;
				}
			}

			else if(left < front && left < right)
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
				else if (!moveFront(maze))
				{
					this.orientation = 1;
					moveRight(maze);
					return;
				}
			}

			else if(front < left && front < right)
			{
				if(moveFront(maze))
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
			return;
		}
		//Impar -> Horizontal
		else
		{
			float up = testUpMove(target);
			float down = testDownMove(target);
			float front = testFrontMove(target);

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
				else if (!moveFront(maze))
				{
					this.orientation = 2;
					moveLeft(maze);
					return;
				}
			}

			else if(down < front && down < up)
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
				else if (!moveFront(maze))
				{
					this.orientation = 0;
					moveUp(maze);
					return;
				}
			}

			else if(front < down && front < up)
			{
				if(moveFront(maze))
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
			return;
		}
	}



	private float calculateDistance(int x, int y, Position target)
	{
		return (float) Math.sqrt(Math.pow((x - target.x),2) + Math.pow((y - target.y),2));
	}

	private float testFrontMove(Position target)
	{
		switch(this.orientation)
		{
		case 0:	
			return testUpMove(target);
		case 1:
			return testRightMove(target);
		case 2:
			return testDownMove(target);
		case 3:
			return testLeftMove(target);
		}

		return 0;
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

	public void moveGhost(Maze maze){

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
				updateOrientation();
		}
		else if(position.x % GameEngine.TILE_DIMENSION == 0 && position.y % GameEngine.TILE_DIMENSION == 0)
			updateOrientation();
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
