package pacman.logic;

public class PinkMonster {

	public Coordenada posicao;
	public boolean adormecido;
	public boolean comido;
	public char letra;
	
	public PinkMonster(){
		posicao = new Coordenada(10,12);
		adormecido = false;
		comido = false;
		letra = 'R';
	}
	
}
