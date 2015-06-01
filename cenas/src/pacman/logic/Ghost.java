package pacman.logic;

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
			if(testRightMove() < testLeftMove())
				MovRight();
			else MovLeft();
		}else{
			if(testUpMove() < testDownMove())
				MovUp();
			else MovDown();
		}

		this.orientation = new_ori;
		return 0;



	}



}
