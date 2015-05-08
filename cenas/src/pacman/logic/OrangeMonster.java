package pacman.logic;

public class OrangeMonster {

	public Coordenada posicao;
	public boolean adormecido;
	public boolean comido;
	public char letra;
	
	public OrangeMonster(){
		posicao = new Coordenada(11,12);
		adormecido = false;
		comido = false;
		letra = 'L';
	}
	
}
