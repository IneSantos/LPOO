package pacman.logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Pacman implements Serializable {

	public Coordenada posicao;
	public boolean vivo;
	public char man;
	public boolean cresceu;
	public int pontos;


	public Pacman(){
		posicao = new Coordenada(10,16);
		vivo = true;
		man = 'Q';
		pontos = 0;
	}


	public void movPacman(char c){
		if(c=='w'){
			posicao.y--;
		}
		if(c=='s'){
			posicao.y++;
		}
		if(c=='a'){
			posicao.x--;
		}
		if(c=='d'){
			posicao.x++;
		}
	}
	
	
	
	
	
	

}
