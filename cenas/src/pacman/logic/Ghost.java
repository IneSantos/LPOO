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
	
	private void rightWin(Maze maze){
		if(!maze.isWall(getTilePosition(position.x+GameEngine.TILE_DIMENSION, position.y))){
			this.orientation = 1;
			moveRight(maze);
		}
		else{
			this.orientation = 3;
			moveLeft(maze);
		}
	}
	
	
	private void leftWin(Maze maze){
		if(!maze.isWall(getTilePosition(position.x-1, position.y))){
			this.orientation = 3;
			moveLeft(maze);
		}
		else{
			this.orientation = 1;
			moveRight(maze);
		}
	}
	
	private void upWin(Maze maze){
		if(!maze.isWall(getTilePosition(position.x, position.y-1))){
			this.orientation = 0;
			moveUp(maze);
		}
		else{
			this.orientation = 2;
			moveDown(maze);
		}
	}
	
	private void downWin(Maze maze){
		if(!maze.isWall(getTilePosition(position.x, position.y+GameEngine.TILE_DIMENSION))){
			this.orientation = 2;
			moveDown(maze);
		}
		else{
			this.orientation = 0;
			moveUp(maze);
		}
	}


	public void updateOrientation(Maze maze){

		//Par -> Vertical
		if(this.orientation%2 == 0){
			if(testRightMove() < testLeftMove()){
				rightWin(maze);
			}
			else{
				leftWin(maze);
			}
		}
		//Impar -> Horizontal
		else{
			if(testUpMove() < testDownMove()){
				upWin(maze);
			}
			else {
				downWin(maze);
			}
		}
	}

	private float calDistance(int x, int y){
		return (float) Math.sqrt(Math.pow((x - this.target.x),2) + Math.pow((y - this.target.y),2));
	}

	private float testDownMove() {
		return calDistance(this.position.x, position.y + GameEngine.TILE_DIMENSION);
	}

	private float testUpMove() {
		return calDistance(this.position.x, this.position.y-1);
	}

	private float testLeftMove() {
		return calDistance(position.x - 1, this.position.y);
	}

	private float testRightMove() {
		return calDistance(position.x + GameEngine.TILE_DIMENSION, this.position.y);
	}

	public void moveGhost(Maze maze){

		if(!maze.isDecisionPoint(getTilePosition(position.x, position.y)))
		{
			System.out.println("Entrou no 1 if");
			if(this.orientation == 0 && !maze.isWall(getTilePosition(position.x, position.y - 1)))
				moveUp(maze);
			else if(this.orientation == 2 && !maze.isWall(getTilePosition(position.x, position.y + GameEngine.TILE_DIMENSION)))
				moveDown(maze);
			else if(this.orientation == 1 && !maze.isWall(getTilePosition(position.x + GameEngine.TILE_DIMENSION, position.y)))
				moveRight(maze);
			else if(this.orientation == 3 && !maze.isWall(getTilePosition(position.x - 1, position.y)))
			{
				moveLeft(maze);
				System.out.println("Entrou posicao em x: " + position.x);
			}
			else { 
				updateOrientation(maze);	
				System.out.println("dentro do else");
			}
			System.out.println("Saiu 1 if");
		}
		else if(position.x % GameEngine.TILE_DIMENSION == 0 && position.y % GameEngine.TILE_DIMENSION == 0)
		{
			updateOrientation(maze);
		}
		else{
			if(this.orientation == 0)
				moveUp(maze);
			else if(this.orientation == 2)
				moveDown(maze);
			else if(this.orientation == 1)
				moveRight(maze);
			else if(this.orientation == 3)
			{
				moveLeft(maze);
				System.out.println("Entrou posicao em x: " + position.x);
			}
		}

	}
	//TODO: ainda falta fazer esta função!
	private void movToGhostHouse(Maze maze){


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
