package pacman.logic;

import java.util.Random;

import pacman.GUI.GameEngine;

public class Game {

	public enum Mode {
		CHASE , SCATTER , FRIGHTENED;
	}

	public static boolean redFlag = true;
	public static boolean pinkFlag = true;
	public static boolean blueFlag = true;
	public static boolean orangeFlag = true;
	public static boolean male = true;
	public static int powerUps = 4;

	public static Pacman pacman;	
	static RedGhost redGhost;
	static PinkGhost pinkGhost;
	static OrangeGhost orangeGhost;
	static BlueGhost blueGhost;
	public static Maze maze;


	public static int mazeWidth;
	public static int mazeHeight;

	int level = 1;
	int collected_pills = 0;
	int generatedFruits = 0;

	public static Mode ghostMode;

	public Game()
	{
		pacman = new Pacman();
		initLevel(true);
	}

	public void initLevel(boolean newMaze)
	{
		if(newMaze)
		{
			maze = new Maze(level);
			mazeWidth = maze.maze.get(0).length;
			mazeHeight = maze.maze.size();
		}
		
		if(redFlag)
		{
			redGhost = new RedGhost();
			
			if(this.level == 1 || this.level == 3)
				redGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 11*GameEngine.TILE_DIMENSION));
			else if (this.level == 2)
				redGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 13*GameEngine.TILE_DIMENSION));
		}

		if(pinkFlag)
		{
			pinkGhost = new PinkGhost();
			
			if(this.level == 1 || this.level == 3)
				pinkGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 15*GameEngine.TILE_DIMENSION));
			else if(this.level == 2)
				pinkGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 17*GameEngine.TILE_DIMENSION));
		}

		if(orangeFlag)
		{
			orangeGhost = new OrangeGhost();
			
			if(this.level == 1 || this.level == 3)
				orangeGhost.setPosition(new Position(14*GameEngine.TILE_DIMENSION, 13*GameEngine.TILE_DIMENSION));
			else if(this.level == 2)
				orangeGhost.setPosition(new Position(14*GameEngine.TILE_DIMENSION, 15*GameEngine.TILE_DIMENSION));
		}

		if(blueFlag)
		{
			blueGhost = new BlueGhost();
			if(this.level == 1 || this.level == 3)
				blueGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 13*GameEngine.TILE_DIMENSION));
			else if (this.level == 2)
				blueGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 15*GameEngine.TILE_DIMENSION));

		}


		Game.pacman.setAlive(true);
		Game.pacman.setOrientation(2);
		if(this.level == 1 || this.level == 3)
			pacman.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 17*GameEngine.TILE_DIMENSION));
		else if (this.level == 2)
			pacman.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 19*GameEngine.TILE_DIMENSION));

		ghostMode = Mode.CHASE;
	}

	public Pacman getPacman()
	{
		return pacman;
	}

	public Maze getMaze()
	{
		return maze;
	}

	public RedGhost getRedGhost(){
		return redGhost;
	}

	public PinkGhost getPinkGhost(){
		return pinkGhost;
	}

	public OrangeGhost getOrangeGhost(){
		return orangeGhost;
	}

	public BlueGhost getBlueGhost(){
		return blueGhost;
	}

	public int getLevel()
	{
		return level;
	}

	public boolean checkCharacterColision(boolean killPacman)
	{
		boolean result = false;
		
		Position pac = new Position(pacman.position.x + 10, pacman.position.y + 10);
		Position r = new Position(0,0);
		Position p = new Position(0,0);
		Position b = new Position(0,0);
		Position o = new Position(0,0);
		
		if(redFlag)
			 r = new Position(redGhost.position.x + 10, redGhost.position.y + 10);
		
		if(pinkFlag)
			p = new Position(pinkGhost.position.x + 10, pinkGhost.position.y + 10);
		
		if(blueFlag)
			b = new Position(blueGhost.position.x + 10, blueGhost.position.y + 10);
		
		if(orangeFlag)
			o = new Position(orangeGhost.position.x + 10, orangeGhost.position.y + 10);

		if(pacman.power_timer == 0)
		{
			if((pac.equals(r) && redGhost.getAlive()
					|| pac.equals(p) && pinkGhost.getAlive()
					|| pac.equals(b) && blueGhost.getAlive()
					|| pac.equals(o) && orangeGhost.getAlive()) && killPacman)
			{
				pacman.lifes--;
				pacman.alive = false;
				result = true;
			}
		}
		else
		{			
			if(pac.equals(r))
			{
				redGhost.alive = false;
				redGhost.velocity = 4;
				pacman.score += 200;
				result = true;
			}
			if(pac.equals(p))
			{
				pinkGhost.alive = false;
				pinkGhost.velocity = 4;
				pacman.score += 200;
				result = true;
			}
			if(pac.equals(b))
			{
				blueGhost.alive = false;
				blueGhost.velocity = 4;
				pacman.score += 200;
				result = true;
			}
			if(pac.equals(o))
			{
				orangeGhost.alive = false;
				orangeGhost.velocity = 4;
				pacman.score += 200;
				result = true;
			}
		}

		return result;
	}

	public void nextLevel()
	{
		this.level++;

		this.collected_pills = 0;
	}
	
	public int getCollectedPills()
	{
		return collected_pills;
	}

	public void generateFruit() {

		if(generatedFruits == level)
			return;

		Random random = new Random();

		for(int i = 0; i < 3; ++i)
		{
			int x =  random.nextInt(mazeWidth);
			int y = random.nextInt(mazeHeight);

			if(maze.isPoint(new Position(x, y)))
			{
				maze.maze.get(y)[x] = 'F';
				generatedFruits++;
				break;
			}
		}
	}

	public void updateElements()
	{
		updatePacman();
		
		if(pacman.getPower() > 0 && GameEngine.refresh %  25 == 0)
		{
			pacman.decPower();

			if(pacman.getPower() == 0)
				ghostMode = Mode.CHASE;
		}
		
		if(GameEngine.refresh == 50*7 || GameEngine.refresh == 50*34 || GameEngine.refresh == 50*59 || GameEngine.refresh == 50*84)
			ghostMode = Mode.CHASE;
		else if(GameEngine.refresh == 50*27 || GameEngine.refresh == 50*54 || GameEngine.refresh == 50*79)
			ghostMode = Mode.SCATTER;
		
		if(getCollectedPills() >= maze.getPills()/3)
			generateFruit();
		
		if(redFlag)
			updateRedGhost();
		if(pinkFlag)
			updatePinkGhost();
		if(orangeFlag)
			updateOrangeGhost();
		if(blueFlag)
			updateBlueGhost();

		if(pacman.getAlive())
			checkCharacterColision(true);
	}
	
	public void updatePacman()
	{
		if(GameEngine.refresh%4 == 0)
			pacman.updateAnimation();
		
		if(pacman.getAlive())
			pacman.updateMovement(GameEngine.inputKey);
	}

	
	public void updateRedGhost()
	{
		getRedGhost().moveGhost();
	}
	
	public void updatePinkGhost()
	{
		getPinkGhost().moveGhost();
	}
	
	public void updateBlueGhost()
	{
		if(getCollectedPills() >= 30 && getBlueGhost().house)
		{
			getBlueGhost().setOrientation(0);
			getBlueGhost().house = false;
		}
		
		getBlueGhost().moveGhost();
	}
	
	public void updateOrangeGhost()
	{
		if(getCollectedPills() >= maze.getPills()/3 && getOrangeGhost().house)
		{
			getOrangeGhost().setOrientation(0);
			getOrangeGhost().house = false;
		}
		
		getOrangeGhost().moveGhost();
	}
	
}



