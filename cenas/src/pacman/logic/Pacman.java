package pacman.logic;

import java.awt.event.KeyEvent;

import pacman.GUI.GameEngine;
import pacman.logic.Game.Mode;


public class Pacman extends Character {

	final int POINT_SCORE = 10;
	final int POWER_POINT_SCORE = 50;
	final int FRUIT_SCORE = 150;
	/**
	 * Score of pac-man 
	 */
	int score;
	/**
	 * animation associated with sprite
	 */
	int animation;
	/**
	 * Time of power time
	 */
	int power_timer;
	/**
	 * number of lifes
	 */
	int lifes;
	/**
	 * number of fruits
	 */
	int fruits;
	
/**
 * Constructor of Pac-man 
 */
	public Pacman()
	{
		super();
		score = 0;
		animation = 0;
		power_timer = 0;
		lifes = 3;
		fruits = 0;
	}

	/**
	 * updates animation
	 * @return animation
	 */
	public int updateAnimation()
	{
		animation++;

		if (animation > 3)
			animation = 0;

		return animation;
	}

	/**
	 * updates movement acording to input key
	 * @param inputKey input key
	 */
	public void updateMovement(int inputKey) 
	{	
		//Portal á direita no labirinto
		if(position.x + GameEngine.TILE_DIMENSION >= Game.mazeWidth * GameEngine.TILE_DIMENSION && orientation == 1)
		{
			if(position.x == Game.maze.maze.get(0).length * GameEngine.TILE_DIMENSION)
				position.x = -GameEngine.TILE_DIMENSION;
		}
		//Portal á esquerda no labirinto
		else if(position.x <= 0 && orientation == 3)
		{
			if(position.x == -GameEngine.TILE_DIMENSION)
				position.x = Game.maze.maze.get(0).length * GameEngine.TILE_DIMENSION;
		} 
		else if(position.x % GameEngine.TILE_DIMENSION == 0 && position.y % GameEngine.TILE_DIMENSION == 0 && inputKey != 0)
		{
			if(inputKey == KeyEvent.VK_UP && !Game.maze.isWall(getTilePosition(position.x, position.y - 1)))
				setOrientation(0);
			else if(inputKey == KeyEvent.VK_DOWN && !Game.maze.isWall(getTilePosition(position.x, position.y + GameEngine.TILE_DIMENSION)) && !Game.maze.isDoor(getTilePosition(position.x, position.y + GameEngine.TILE_DIMENSION)))
				setOrientation(2);
			else if(inputKey == KeyEvent.VK_RIGHT && !Game.maze.isWall(getTilePosition(position.x + GameEngine.TILE_DIMENSION, position.y)))
				setOrientation(1);
			else if(inputKey == KeyEvent.VK_LEFT && !Game.maze.isWall(getTilePosition(position.x - 1, position.y)))
				setOrientation(3);

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

		Position tile = getTilePosition(Game.pacman.position.x + GameEngine.TILE_DIMENSION/2, Game.pacman.position.y + GameEngine.TILE_DIMENSION/2);

		if(Game.maze.isPoint(tile))
		{
			if(Game.maze.isDecisionPoint(tile))
				Game.maze.maze.get(tile.y)[tile.x] = 'd';
			else Game.maze.maze.get(tile.y)[tile.x] = ' ';

			this.score += POINT_SCORE;
			Game.collected_pills++;
		}
		else if(Game.maze.isPowerPoint(tile))
		{
			Game.maze.maze.get(tile.y)[tile.x] = ' ';

			this.power_timer = 12;
			this.score += POWER_POINT_SCORE;
			Game.collected_pills++;
			Game.ghostMode = Mode.FRIGHTENED;
		}
		else if(Game.maze.isFruit(tile))
		{
			Game.maze.maze.get(tile.y)[tile.x] = ' ';

			this.score += POWER_POINT_SCORE;
			Game.collected_pills++;
			
			
		}


	}
/**
 * Returns animation
 * @return animation
 */
	public int getAnimation()
	{
		return animation;	
	}

	/**
	 * Returns power time
	 * @return power time
	 */
	public int getPower(){
		return power_timer;
	}
	
	/**
	 *  Decreases power time 
	 */
	public void decPower(){
		power_timer--;
	}
	/**
	 * returns score
	 * @return score
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * returns number of lifes 
	 * @return number of lifes
	 */
	public int getLifes()
	{
		return lifes;
	}

	/**
	 * Sets boolean alive
	 * @param b new boolean alive
	 */
	public void setAlive(boolean b) {
		this.alive = b;
		
	}

	/**
	 * return fruits
	 * @return fruits
	 */ 
	public int getFruits() {
		return fruits;
	}

	/**
	 * gives bonus life
	 */
	public void bonusLife() 
	{
		if(this.lifes < 3)
			this.lifes++;	
	}
	
}
