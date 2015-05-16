package pacman.logic;

import java.util.Scanner;

public class Jogo {
	//21x26
	static char labDefault[][] =  {
		{ ' ', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , 'X' , 'X', ' '},
		{ ' ', 'X', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.' , '.' , '.', '.', '.' , '.' , '.' , '.' , 'X', ' '},
		{ ' ', 'X', '.', 'X', 'X', '.', 'X', 'X', 'X', '.', 'X', '.' , 'X' , 'X', 'X', '.' , 'X' , 'X' , '.' , 'X', ' '},
		{ ' ', 'X', 'P', 'X', 'X', '.', 'X', 'X', 'X', '.', 'X', '.' , 'X' , 'X', 'X', '.' , 'X' , 'X' , 'P' , 'X', ' '},
		{ ' ', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' , '.' , '.', '.', '.' , '.' , '.' , '.' , 'X', ' '},
		{ ' ', 'X', '.', 'X', 'X', '.', 'X', '.', 'X', 'X', 'X', 'X' , 'X' , '.', 'X', '.' , 'X' , 'X' , '.' , 'X', ' '},
		{ ' ', 'X', '.', '.', '.', '.', 'X', '.', '.', '.', 'X', '.' , '.' , '.', 'X', '.' , '.' , '.' , '.' , 'X', ' '},
		{ ' ', 'X', 'X', 'X', 'X', '.', 'X', 'X', 'X', ' ', 'X', ' ' , 'X' , 'X', 'X', '.' , 'X' , 'X' , 'X' , 'X', ' '},
		{ ' ', ' ', ' ', ' ', 'X', '.', 'X', ' ', ' ', ' ', ' ', ' ' , ' ' , ' ', 'X', '.' , 'X' , ' ' , ' ' , ' ', ' '},
		{ ' ', ' ', ' ', ' ', 'X', '.', 'X', ' ', 'X', 'X', 'S', 'X' , 'X' , ' ', 'X', '.' , 'X' , ' ' , ' ' , ' ', ' '},
		{ ' ', ' ', ' ', ' ', 'X', '.', 'X', ' ', 'X', ' ', ' ', ' ' , 'X' , ' ', 'X', '.' , 'X' , ' ' , ' ' , ' ', ' '},
		{ 'X', 'X', 'X', 'X', 'X', '.', 'X', ' ', 'X', ' ', ' ', ' ' , 'X' , ' ', 'X', '.' , 'X' , 'X' , 'X' , 'X', 'X'},
		{ ' ', ' ', ' ', ' ', ' ', '.', ' ', ' ', 'X', ' ', ' ', ' ' , 'X' , ' ', ' ', '.' , ' ' , ' ' , ' ' , ' ', ' '},
		{ 'X', 'X', 'X', 'X', 'X', '.', 'X', ' ', 'X', ' ', ' ', ' ' , 'X' , ' ', 'X', '.' , 'X' , 'X' , 'X' , 'X', 'X'},
		{ ' ', ' ', ' ', ' ', 'X', '.', 'X', ' ', 'X', ' ', ' ', ' ' , 'X' , ' ', 'X', '.' , 'X' , ' ' , ' ' , ' ', ' '},
		{ ' ', ' ', ' ', ' ', 'X', '.', 'X', ' ', 'X', 'X', 'X', 'X' , 'X' , ' ', 'X', '.' , 'X' , ' ' , ' ' , ' ', ' '},
		{ ' ', ' ', ' ', ' ', 'X', '.', 'X', ' ', ' ', ' ', ' ', ' ' , ' ' , ' ', 'X', '.' , 'X' , ' ' , ' ' , ' ', ' '},
		{ ' ', 'X', 'X', 'X', 'X', '.', 'X', '.', 'X', 'X', 'X', 'X' , 'X' , '.', 'X', '.' , 'X' , 'X' , 'X' , 'X', ' '},
		{ ' ', 'X', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.' , '.' , '.', '.', '.' , '.' , '.' , '.' , 'X', ' '},
		{ ' ', 'X', '.', 'X', 'X', '.', 'X', 'X', 'X', '.', 'X', '.' , 'X' , 'X', 'X', '.' , 'X' , 'X' , '.' , 'X', ' '},
		{ ' ', 'X', 'P', '.', 'X', '.', '.', '.', '.', '.', '.', '.' , '.' , '.', '.', '.' , 'X' , '.' , 'P' , 'X', ' '},
		{ ' ', 'X', 'X', '.', 'X', '.', 'X', '.', 'X', 'X', 'X', 'X' , 'X' , '.', 'X', '.' , 'X' , '.' , 'X' , 'X', ' '},
		{ ' ', 'X', '.', '.', '.', '.', 'X', '.', '.', '.', 'X', '.' , '.' , '.', 'X', '.' , '.' , '.' , '.' , 'X', ' '},
		{ ' ', 'X', '.', 'X', 'X', 'X', 'X', 'X', 'X', '.', 'X', '.' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , '.' , 'X', ' '},
		{ ' ', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' , '.' , '.', '.', '.' , '.' , '.' , '.' , 'X', ' '},
		{ ' ', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , 'X' , 'X', ' '}};

	static Pacman pac = new Pacman();	


	static RedMonster redmonster = new RedMonster();
	static PinkMonster pinkmonster = new PinkMonster();
	static OrangeMonster orangemonster = new OrangeMonster();
	static BlueMonster bluemonster = new BlueMonster();

/*
	public static void main(String[] args) {

		deslocaPacman();
	}
*/
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


}



