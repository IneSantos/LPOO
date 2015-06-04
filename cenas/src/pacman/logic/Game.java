package pacman.logic;

import pacman.GUI.GameEngine;
import pacman.menus.MainMenu;

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
	
	public static Mode ghostMode = Mode.CHASE;
	
	public Game()
	{
		pacman = new Pacman();
		redGhost = new RedGhost();
		pinkGhost = new PinkGhost();
		orangeGhost = new OrangeGhost();
		blueGhost = new BlueGhost();
		maze = new Maze();
		
		mazeWidth = maze.maze[0].length;
		mazeHeight = maze.maze.length;
		
		pacman.setPosition(new Position(13*GameEngine.TILE_DIMENSION, (17+3)*GameEngine.TILE_DIMENSION));
		redGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 14*GameEngine.TILE_DIMENSION));
		pinkGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 18*GameEngine.TILE_DIMENSION));
		blueGhost.setPosition(new Position(13*GameEngine.TILE_DIMENSION, 18*GameEngine.TILE_DIMENSION));
		orangeGhost.setPosition(new Position(14*GameEngine.TILE_DIMENSION, 18*GameEngine.TILE_DIMENSION));

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
	
	


	/*
	public static void main(String[] args) {

		deslocaPacman();
	}

	public static void deslocaPacman(){

		while(pac.vivo)
		{
			for(int i=0; i<26; ++i)
			{
				for(int j=0; j<21 ; ++j)
				{
					if (i == pac.posicao.y && j == pac.posicao.x) 
					{
						System.out.print(pac.man);
						System.out.print(' ');
					} 
					else if (i == redmonster.posicao.y
							&& j == redmonster.posicao.x) 
					{
						System.out.print(redmonster.letra);
						System.out.print(' ');
					} 
					else if (i == pinkmonster.posicao.y
							&& j == pinkmonster.posicao.x) 
					{
						System.out.print(pinkmonster.letra);
						System.out.print(' ');
					} 
					else if (i == orangemonster.posicao.y
							&& j == orangemonster.posicao.x) 
					{
						System.out.print(orangemonster.letra);
						System.out.print(' ');
					} 
					else if (i == bluemonster.posicao.y
							&& j == bluemonster.posicao.x) 
					{
						System.out.print(bluemonster.letra);
						System.out.print(' ');
					} 
					else
					{
						System.out.print(labDefault[i][j]);
						System.out.print(' ');
					}
				}
				System.out.println();
			}

			char mov;
			Scanner key = new Scanner(System.in);
			System.out.println("Para deslocar o pacman usar as teclas ...");
			mov = key.next().charAt(0);

			movPacman(mov);
		}

		System.out.print("Morreste, com " + pac.pontos + " pontos...");

	}


	public static void movPacman(char tecla){
		if(tecla == 'w' )
		{
			if(verificaParede(pac.posicao.y-1, pac.posicao.x))
			{
				pac.movPacman(tecla);
			}
		}
		if(tecla == 's'){
			if(verificaParede(pac.posicao.y+1, pac.posicao.x)){
				pac.movPacman(tecla);
			}
		}
		if(tecla == 'a'){
			if(pac.posicao.y == 12 && pac.posicao.x == 0){
				System.out.print("passou");
				pac.posicao.x = 19;
			}			
			if(verificaParede(pac.posicao.y, pac.posicao.x-1)){
				pac.movPacman(tecla);
			}			
		}
		if(tecla == 'd'){
			if(pac.posicao.y == 12 && pac.posicao.x == 20){
				System.out.print("passou");
				pac.posicao.x = -1;
			}	

			if(verificaParede(pac.posicao.y, pac.posicao.x+1)){
				pac.movPacman(tecla);
			}
		}
	}

	public static void verificaPacmanMonstro(int width, int hight){

		if (width == redmonster.posicao.y && hight == redmonster.posicao.x) {
			pac.vivo = false;
		} else if (width == orangemonster.posicao.y
				&& hight == orangemonster.posicao.x) {
			pac.vivo = false;
		} else if (width == pinkmonster.posicao.y
				&& hight == pinkmonster.posicao.x) {
			pac.vivo = false;
		} else if (width == bluemonster.posicao.y
				&& hight == bluemonster.posicao.x) {
			pac.vivo = false;
		}
	}
	public static boolean verificaParede(int width, int hight){

		if(labDefault[width][hight] == 'P'){
			pac.cresceu = true;
		}


		if(labDefault[width][hight] == '.'){
			labDefault[width][hight] = ' ';
			pac.pontos += 3;
		}

		verificaPacmanMonstro(width,hight);

		return labDefault[width][hight]	!= 'X';	 
	}
	 */

}



