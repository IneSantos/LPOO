package pacman.logic;

import java.awt.event.KeyEvent;
import java.util.Random;

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
	
	private void rightWin(int tileWidth, int tileHeight, Maze maze){
		if(!maze.isWall(getTilePosition(position.x+tileWidth, position.y, tileWidth, tileHeight))){
			this.orientation = 1;
			moveRight(tileWidth, tileHeight,maze);
		}
		else{
			this.orientation = 3;
			moveLeft(tileWidth, tileHeight,maze);
		}
	}
	
	
	private void leftWin(int tileWidth, int tileHeight, Maze maze){
		if(!maze.isWall(getTilePosition(position.x-1, position.y, tileWidth, tileHeight))){
			this.orientation = 3;
			moveLeft(tileWidth, tileHeight,maze);
		}
		else{
			this.orientation = 1;
			moveRight(tileWidth, tileHeight,maze);
		}
	}
	
	private void upWin(int tileWidth, int tileHeight, Maze maze){
		if(!maze.isWall(getTilePosition(position.x, position.y-1, tileWidth, tileHeight))){
			this.orientation = 0;
			moveUp(tileWidth, tileHeight,maze);
		}
		else{
			this.orientation = 2;
			moveDown(tileWidth, tileHeight,maze);
		}
	}
	
	private void downWin(int tileWidth, int tileHeight, Maze maze){
		if(!maze.isWall(getTilePosition(position.x, position.y+tileHeight, tileWidth, tileHeight))){
			this.orientation = 2;
			moveDown(tileWidth, tileHeight,maze);
		}
		else{
			this.orientation = 0;
			moveUp(tileWidth, tileHeight,maze);
		}
	}


	public void updateOrientation(int tileWidth, int tileHeight, Maze maze){

		//Par -> Vertical
		if(this.orientation%2 == 0){
			if(testRightMove(tileWidth) < testLeftMove()){
				rightWin(tileWidth, tileHeight,maze);
			}
			else{
				leftWin(tileWidth, tileHeight, maze);
			}
		}
		//Impar -> Horizontal
		else{
			if(testUpMove() < testDownMove(tileHeight)){
				upWin(tileWidth, tileHeight, maze);
			}
			else {
				downWin(tileWidth, tileHeight, maze);
			}
		}
	}

	private float calDistance(int x, int y){
		return (float) Math.sqrt(Math.pow((x - this.target.x),2) + Math.pow((y - this.target.y),2));
	}

	private float testDownMove(int tileHeight) {
		return calDistance(this.position.x, position.y + tileHeight);
	}

	private float testUpMove() {
		return calDistance(this.position.x, this.position.y-1);
	}

	private float testLeftMove() {
		return calDistance(position.x - 1, this.position.y);
	}

	private float testRightMove(int tileWidth) {
		return calDistance(position.x + tileWidth, this.position.y);
	}

	public void moveGhost(Maze maze, int tileWidth, int tileHeight){

		if(!maze.isDecisionPoint(getTilePosition(position.x, position.y, tileWidth, tileHeight)))
		{
			System.out.println("Entrou no 1 if");
			if(this.orientation == 0 && !maze.isWall(getTilePosition(position.x, position.y - 1, tileWidth, tileHeight)))
				moveUp(tileWidth, tileHeight, maze);
			else if(this.orientation == 2 && !maze.isWall(getTilePosition(position.x, position.y + tileHeight, tileWidth, tileHeight)))
				moveDown(tileWidth, tileHeight, maze);
			else if(this.orientation == 1 && !maze.isWall(getTilePosition(position.x + tileWidth, position.y, tileWidth, tileHeight)))
				moveRight(tileWidth, tileHeight, maze);
			else if(this.orientation == 3 && !maze.isWall(getTilePosition(position.x - 1, position.y, tileWidth, tileHeight)))
			{
				moveLeft(tileWidth, tileHeight, maze);
				System.out.println("Entrou posicao em x: " + position.x);
			}
			else { 
				updateOrientation(tileWidth,tileHeight,maze);	
				System.out.println("dentro do else");
			}
			System.out.println("Saiu 1 if");
		}
		else if(position.x % tileWidth == 0 && position.y % tileHeight == 0)
		{
			updateOrientation(tileWidth, tileHeight, maze);
		}
		else{
			if(this.orientation == 0)
				moveUp(tileWidth, tileHeight, maze);
			else if(this.orientation == 2)
				moveDown(tileWidth, tileHeight, maze);
			else if(this.orientation == 1)
				moveRight(tileWidth, tileHeight, maze);
			else if(this.orientation == 3)
			{
				moveLeft(tileWidth, tileHeight, maze);
				System.out.println("Entrou posicao em x: " + position.x);
			}
		}

	}
	//TODO: ainda falta fazer esta função!
	private void movToGhostHouse(int tileWidth, int tileHeight, Maze maze){


		boolean valid = false;

		while(!valid){
			int rand = generateOri(); 
			if(position.x % tileWidth == 0 && position.y % tileHeight == 0 && rand != 0)
			{
				if(rand == 0 && !maze.isWall(getTilePosition(position.x, position.y - 1, tileWidth, tileHeight))){
					setOrientation(0);
					valid = true;
				}
				else if(rand == 2 && !maze.isWall(getTilePosition(position.x, position.y + tileHeight, tileWidth, tileHeight)) && !this.alive){
					setOrientation(2);
					valid = true;
				}
				else if(rand == 1 && !maze.isWall(getTilePosition(position.x + tileWidth, position.y, tileWidth, tileHeight))){
					setOrientation(1);
					valid = true;
				}
				else if(rand == 3 && !maze.isWall(getTilePosition(position.x - 1, position.y, tileWidth, tileHeight))){
					setOrientation(3);
					valid = true;
				}
			}
		}

		if(orientation == 0)
			moveUp(tileWidth, tileHeight, maze);
		else if (orientation == 1)
			moveRight(tileWidth, tileHeight, maze);
		else if(orientation == 2)
			moveDown(tileWidth, tileHeight, maze);
		else if(orientation == 3)
			moveLeft(tileWidth, tileHeight, maze);		

	}

}
