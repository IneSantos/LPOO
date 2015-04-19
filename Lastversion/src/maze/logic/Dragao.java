package maze.logic;

import java.io.Serializable;

/**  
* Dragao.java - a simple class that represents a Dragon.
*/
@SuppressWarnings("serial")
public class Dragao implements Serializable{

	/**
	 *  The coordinate of this Dragon.
	 * 
	 * @see {@link Coordenada}
	 */
	public Coordenada posicao;
	
	/**
	 *  The number of iterations where the dragon is asleep .
	 * 
	 */
	public int niteracoes;

	/**
	 *  Boolean that indicates if the Dragon is alive or not.
	 * 
	 */
	public boolean vivo;
	
	/**
	 *  Boolean that indicates if the Dragon is asleep or not.
	 * 
	 */
	public boolean dormir;

	/**
	 * Constructs and initializes a Dragon at the specified position (x,y), alive and awake with 0 iterations of sleep.
	 *
	 * @param x
	 *        the X coordinate of the newly constructed Dragon.
	 * @param y
	 *        the Y coordinate of the newly constructed Dragon.
	 *        
	 * @see {@link Coordenada}
	 */
	public Dragao(int x, int y)
	{
		this.posicao = new Coordenada(x, y);
		this.vivo = true;
		this.dormir = false;
		this.niteracoes = 0;
	}
	
	/**
	 *  Test dragon's position according to the hero's position, it only works for one Dragon.
	 *  
	 *  @return dragon's position according to the hero's position.
	 */
	//SO TESTA PARA UM DRAGAO, RETORNA 1,2,3,4 DE ACORDO COM A POSICAO DO HEROI EM RELACAO AO DRAGAO
	public int linhaMiraDragao(Heroi hero)
	{

		if(posicao.x == hero.cor.x && hero.cor.y < posicao.y && Math.abs(posicao.y-hero.cor.y) <= 3 )
			return 1; //cima
		else if(posicao.x == hero.cor.x && hero.cor.y > posicao.y && Math.abs(posicao.y-hero.cor.y)<=3  )
			return 2; // baixo
		else if(posicao.y == hero.cor.y && hero.cor.x < posicao.x  && Math.abs(posicao.x-hero.cor.x)<=3 )
			return 3; //esquerda
		else if(posicao.y == hero.cor.y && hero.cor.x > posicao.x && Math.abs(posicao.x-hero.cor.x)<=3 )
			return 4; //direita
		else			
			return -1;
	}
	
	/**
	 * Returns true if dragon's direction is into a wall.
	 * 
	 * @param temp
	 *           dragon's direction.
	 * @return <code>true</code> if dragon's direction is into a wall.
	 */
	public boolean verificaWall(int temp, Labirinto labirinto)
	{
		for(int i = 0; i <= 3 ; ++i)
		{
			if(temp == 1)
			{
				if(labirinto.labirinto[posicao.y - i][posicao.x] == 'X')
				return true;
			}
			else if(temp == 2)
			{
				if(labirinto.labirinto[posicao.y + i][posicao.x] == 'X')
					return true;
			}
			else if(temp == 3)
			{
				if(labirinto.labirinto[posicao.y][posicao.x-i] == 'X')
					return true;
			}
			else if(temp == 4)
			{
				if(labirinto.labirinto[posicao.y][posicao.x+i] == 'X')
					return true;
			}
		}
		
		return false;
	}

}
