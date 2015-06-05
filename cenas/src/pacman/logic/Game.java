package pacman.logic;

import pacman.GUI.GameEngine;

public class Game {
	//21x26
	//28x36
	
	public enum Mode {
		CHASE , SCATTER , FRIGHTENED
	}

	public static Pacman pacman;	
	static RedGhost redGhost;
	static PinkGhost pinkGhost;
	static OrangeGhost orangeGhost;
	static BlueGhost blueGhost;
	public static Maze maze;
	
	public static int mazeWidth;
	public static int mazeHeight;
	
	int level;
	int ghost_wave = 0;
	int collected_pills = 0;
	
	public static Mode ghostMode;
	
	public Game()
	{
		pacman = new Pacman();
		maze = new Maze();
		
		initLevel(1);
		
		mazeWidth = maze.maze[0].length;
		mazeHeight = maze.maze.length;

	}
	
	public void initLevel(int level)
	{
		redGhost = new RedGhost();
		pinkGhost = new PinkGhost();
		orangeGhost = new OrangeGhost();
		blueGhost = new BlueGhost();
		
		redGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 14*GameEngine.TILE_DIMENSION));
		pinkGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 18*GameEngine.TILE_DIMENSION));
		blueGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 18*GameEngine.TILE_DIMENSION));
		orangeGhost.setPosition(new Position(14*GameEngine.TILE_DIMENSION, 18*GameEngine.TILE_DIMENSION));
		pacman.setPosition(new Position(13*GameEngine.TILE_DIMENSION, (17+3)*GameEngine.TILE_DIMENSION));
		
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

	public void checkCharacterColision()
	{
		if(pacman.power_timer == 0)
		{
			if(pacman.position.equals(redGhost.position) && redGhost.getAlive()
					|| pacman.position.equals(pinkGhost.position) && pinkGhost.getAlive()
					|| pacman.position.equals(blueGhost.position) && blueGhost.getAlive()
					|| pacman.position.equals(orangeGhost.position) && orangeGhost.getAlive())
			{
				pacman.lifes--;
				System.out.println(Game.pacman.getLifes());
				pacman.alive = false;
			}
		}
		if(pacman.power_timer > 0)
		{			
			if(pacman.position.equals(redGhost.position))
				redGhost.alive = false;
			if(pacman.position.equals(pinkGhost.position))
				pinkGhost.alive = false;
			if(pacman.position.equals(blueGhost.position))
				blueGhost.alive = false;
			if(pacman.position.equals(orangeGhost.position))
				orangeGhost.alive = false;
		}

	}

	public int getCollectedPills()
	{
		return collected_pills;
	}
	
	

}



