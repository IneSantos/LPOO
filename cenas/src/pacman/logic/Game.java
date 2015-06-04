package pacman.logic;

import java.util.Scanner;

import pacman.GUI.GameEngine;
import pacman.logic.Ghost.Mode;

public class Game {
	//21x26
	//28x36

	static Pacman pacman = new Pacman();	
	static RedGhost redGhost = new RedGhost();
	static PinkGhost pinkGhost = new PinkGhost();
	static OrangeGhost orangeGhost = new OrangeGhost();
	static BlueGhost blueGhost = new BlueGhost();
	static Maze maze = new Maze();
	public static int mazeWidth = maze.maze[0].length;
	public static int mazeHeight = maze.maze.length;

	int level;


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
	
	public int getLevel(){
		return level;
	}

	public boolean comparePosition(Position p1, Position p2){
		if(p1.x % GameEngine.TILE_DIMENSION == 0 && p1.y % GameEngine.TILE_DIMENSION == 0/* && p2.x % GameEngine.TILE_DIMENSION == 0 && p2.y % GameEngine.TILE_DIMENSION == 0 */)
				return (p1.x == p2.x && p1.y == p2.y);
		return false;
	}

	public void checkLive(){

		if(pacman.lifes > 0 && pacman.power == 0){
			if(comparePosition(pacman.position, redGhost.position) || comparePosition(pacman.position, pinkGhost.position) || comparePosition(pacman.position, blueGhost.position) || comparePosition(pacman.position, orangeGhost.position)){
				pacman.lifes --;
				return;
			}
		}
		if(pacman.lifes == 0)
			pacman.alive = false;
	}


	public void checkColision(){

		if(pacman.power == 1)
		{
			redGhost.mode = Mode.FRIGHTENED;
			pinkGhost.mode = Mode.FRIGHTENED;
			blueGhost.mode = Mode.FRIGHTENED;
			orangeGhost.mode = Mode.FRIGHTENED;
			if(comparePosition(pacman.position, redGhost.position)){
				redGhost.alive = false;
			}
			if(comparePosition(pacman.position, pinkGhost.position)){
				pinkGhost.alive = false;
			}
			if(comparePosition(pacman.position, blueGhost.position)){
				blueGhost.alive = false;
			}
			if(comparePosition(pacman.position, orangeGhost.position)){
				orangeGhost.alive = false;
			}
		}

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



