package pacman.logic;

public class RedMonster {

	public Coordenada posicao;
	public boolean adormecido;
	public boolean comido;
	public char letra;
	
	public RedMonster(){
		posicao = new Coordenada(10,8);
		adormecido = false;
		comido = false;
		letra = 'V';
	}
	
	
}
