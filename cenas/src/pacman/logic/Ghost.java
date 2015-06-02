package pacman.logic;

import java.awt.event.KeyEvent;
import java.util.Random;

public class Ghost extends Character {

	public enum Mode {
		CHASE , SCATTER , FRIGHTENED
	}

	int state;
	public Mode mode;
	int old_orientation;

	public Ghost(){
		this.orientation = 3; //sempre que sai da casa sai com a orientacao para a esquerda
		this.mode = Mode.CHASE;
		this.velocity = 2;
		this.alive = true;
	}

	public int moveGhost(int new_ori, int tileWidth, int tileHeight){

		old_orientation = this.orientation;

		if(old_orientation%2 == 0){
			//if(testRightMove() < testLeftMove())
			//	MovRight(new_ori, tileWidth, tileHeight);
			//	else MovLeft(new_ori, tileWidth, tileHeight);
		}else{
			//if(testUpMove() < testDownMove())
			//	MovUp(new_ori, tileWidth, tileHeight);
			//else MovDown(new_ori, tileWidth, tileHeight);
		}

		return 0;



	}


	private int testDownMove() {
		return 0;
	}

	private int testUpMove() {
		return 0;
	}


	private int testLeftMove() {
		return 0;
	}

	private int testRightMove() {
		return 0;
	}

	private int generateOri(){
		Random random = new Random();
		int rand;
		rand = random.nextInt(4);
		return rand;
	}

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

				rand = 0;
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
