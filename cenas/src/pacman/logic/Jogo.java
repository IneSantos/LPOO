package pacman.logic;

import java.util.Scanner;

public class Jogo {
	//21x26
	static char labDefault[][] =  {
		{ ' ', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , 'X' , 'X', ' '},
		{ ' ', 'X', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'X', 'p' , 'p' , 'p', 'p', 'p' , 'p' , 'p' , 'p' , 'X', ' '},
		{ ' ', 'X', 'P', 'X', 'X', 'p', 'X', 'X', 'X', 'p', 'X', 'p' , 'X' , 'X', 'X', 'p' , 'X' , 'X' , 'p' , 'X', ' '},
		{ ' ', 'X', 'p', 'X', 'X', 'p', 'X', 'X', 'X', 'p', 'X', 'p' , 'X' , 'X', 'X', 'p' , 'X' , 'X' , 'p' , 'X', ' '},
		{ ' ', 'X', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' , 'p' , 'p', 'p', 'p' , 'p' , 'p' , 'p' , 'X', ' '},
		{ ' ', 'X', 'p', 'X', 'X', 'p', 'X', 'p', 'X', 'X', 'X', 'X' , 'X' , 'p', 'X', 'p' , 'X' , 'X' , 'p' , 'X', ' '},
		{ ' ', 'X', 'p', 'p', 'p', 'p', 'X', 'p', 'p', 'p', 'X', 'p' , 'p' , 'p', 'X', 'p' , 'p' , 'p' , 'p' , 'X', ' '},
		{ ' ', 'X', 'X', 'X', 'X', 'p', 'X', 'X', 'X', ' ', 'X', ' ' , 'X' , 'X', 'X', 'p' , 'X' , 'X' , 'X' , 'X', ' '},
		{ ' ', ' ', ' ', ' ', 'X', 'p', 'X', ' ', ' ', ' ', ' ', ' ' , ' ' , ' ', 'X', 'p' , 'X' , ' ' , ' ' , ' ', ' '},
		{ ' ', ' ', ' ', ' ', 'X', 'p', 'X', ' ', 'X', 'X', 'S', 'X' , 'X' , ' ', 'X', 'p' , 'X' , ' ' , ' ' , ' ', ' '},
		{ ' ', ' ', ' ', ' ', 'X', 'p', 'X', ' ', 'X', ' ', ' ', ' ' , 'X' , ' ', 'X', 'p' , 'X' , ' ' , ' ' , ' ', ' '},
		{ 'X', 'X', 'X', 'X', 'X', 'p', 'X', ' ', 'X', ' ', ' ', ' ' , 'X' , ' ', 'X', 'p' , 'X' , 'X' , 'X' , 'X', 'X'},
		{ ' ', ' ', ' ', ' ', ' ', 'p', ' ', ' ', 'X', ' ', ' ', ' ' , 'X' , ' ', ' ', 'p' , ' ' , ' ' , ' ' , ' ', ' '},
		{ 'X', 'X', 'X', 'X', 'X', 'p', 'X', ' ', 'X', ' ', ' ', ' ' , 'X' , ' ', 'X', 'p' , 'X' , 'X' , 'X' , 'X', 'X'},
		{ ' ', ' ', ' ', ' ', 'X', 'p', 'X', ' ', 'X', ' ', ' ', ' ' , 'X' , ' ', 'X', 'p' , 'X' , ' ' , ' ' , ' ', ' '},
		{ ' ', ' ', ' ', ' ', 'X', 'p', 'X', ' ', 'X', 'X', 'X', 'X' , 'X' , ' ', 'X', 'p' , 'X' , ' ' , ' ' , ' ', ' '},
		{ ' ', ' ', ' ', ' ', 'X', 'p', 'X', ' ', ' ', ' ', ' ', ' ' , ' ' , ' ', 'X', 'p' , 'X' , ' ' , ' ' , ' ', ' '},
		{ ' ', 'X', 'X', 'X', 'X', 'p', 'X', 'p', 'X', 'X', 'X', 'X' , 'X' , 'p', 'X', 'p' , 'X' , 'X' , 'X' , 'X', ' '},
		{ ' ', 'X', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'X', 'p' , 'p' , 'p', 'p', 'p' , 'p' , 'p' , 'p' , 'X', ' '},
		{ ' ', 'X', 'p', 'X', 'X', 'p', 'X', 'X', 'X', 'p', 'X', 'p' , 'X' , 'X', 'X', 'p' , 'X' , 'X' , 'p' , 'X', ' '},
		{ ' ', 'X', 'P', 'p', 'X', 'p', 'p', 'p', 'p', 'p', 'p', 'p' , 'p' , 'p', 'p', 'p' , 'X' , 'p' , 'P' , 'X', ' '},
		{ ' ', 'X', 'X', 'p', 'X', 'p', 'X', 'p', 'X', 'X', 'X', 'X' , 'X' , 'p', 'X', 'p' , 'X' , 'p' , 'X' , 'X', ' '},
		{ ' ', 'X', 'p', 'p', 'p', 'p', 'X', 'p', 'p', 'p', 'X', 'p' , 'p' , 'p', 'X', 'p' , 'p' , 'p' , 'p' , 'X', ' '},
		{ ' ', 'X', 'p', 'X', 'X', 'X', 'X', 'X', 'X', 'p', 'X', 'p' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , 'p' , 'X', ' '},
		{ ' ', 'X', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' , 'p' , 'p', 'p', 'p' , 'p' , 'p' , 'p' , 'X', ' '},
		{ ' ', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , 'X' , 'X', ' '}};

	static Pacman pac = new Pacman();	
	static RedMonster monster = new RedMonster();


	public static void main(String[] args) {

		deslocaPacman();
	}

	public static void deslocaPacman(){

		while(pac.vivo){
			for(int i=0; i<26; ++i){
				for(int j=0; j<21 ; ++j){
					if(i == pac.posicao.y && j ==pac.posicao.x){
						System.out.print(pac.man);
						System.out.print(' ');
					}
					if(i == monster.posicao.y && j == monster.posicao.x){
						System.out.print(monster.letra);
						System.out.print(' ');
					}else{
						System.out.print(labDefault[i][j]);
						//System.out.print(' ');
					}

					System.out.print(' ');
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
		if(tecla == 'w'){
			if(verificaParede(pac.posicao.y-1, pac.posicao.x)){
				pac.movPacman(tecla);
			}
		}
		if(tecla == 's'){
			if(verificaParede(pac.posicao.y+1, pac.posicao.x)){
				pac.movPacman(tecla);
			}
		}
		if(tecla == 'a'){
			if(pac.posicao.y == 10 && pac.posicao.x == 0){
				System.out.print("passou");
				pac.posicao.x = 20;
			}			
			if(verificaParede(pac.posicao.y, pac.posicao.x-1)){
				pac.movPacman(tecla);
			}			
		}
		if(tecla == 'd'){
			if(verificaParede(pac.posicao.y, pac.posicao.x+1)){
				pac.movPacman(tecla);
			}
		}
	}

	public static boolean verificaParede(int width, int hight){

		if(labDefault[width][hight] == 'P'){
			pac.cresceu = true;
		}
		
		if(labDefault[width][hight] == 'p'){
			labDefault[width][hight] = ' ';
			pac.pontos += 3;
		}
		

		if(width == monster.posicao.y && hight == monster.posicao.x){
			pac.vivo = false;
		}

		return labDefault[width][hight]	!= 'X';	 
	}


}



