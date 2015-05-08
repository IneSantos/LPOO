package pacman.logic;

public class OrangeMonster {

	public Coordenada posicao;
	public boolean adormecido;
	public char letra;
	
	public OrangeMonster(){
		posicao = new Coordenada(10,8);
		adormecido = false;
		letra = 'L';
	}
	
}
