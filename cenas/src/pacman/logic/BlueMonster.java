package pacman.logic;

public class BlueMonster {

	public Coordenada posicao;
	public boolean adormecido;
	public boolean comido;
	public char letra;
	
	public BlueMonster(){
		posicao = new Coordenada(9,12);
		adormecido = false;
		comido = false;
		letra = 'A';
	}
	
	
}
