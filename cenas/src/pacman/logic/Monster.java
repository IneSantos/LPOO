package pacman.logic;

public class Monster {

	public Coordenada posicao;
	public boolean adormecido;
	public char letra;
	
	public Monster(){
		posicao = new Coordenada(10,8);
		adormecido = false;
		letra = 'M';
	}
	
	
}
