package maze.logic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import maze.cli.Client;

/**  
* 
* Jogo.java - a simple class that represents the Game.
* 
* @see Dardo
* @see Heroi
* @see Escudo
* @see Dragao
* @see Espada
* @see Labirinto
*/
@SuppressWarnings("serial")
public class Jogo implements Serializable
{
	/**
	 * 	The maximum number of dragons in field;
	 */
	public static final int MAX_DRAGONS = 15;
	
	/**
	 *  The labyrinth's dimension.
	 * 
	 */
	public int dimensaoLabirinto;
	
	/**
	 *  The labyrinth's dimension.
	 * 
	 */
	public int dragonMoves;
	
	/**
	 *  the number of dragons in the maze.
	 * 
	 */
	public int numDragoes;

	/**
	 *  Represents the games Labyrinth.
	 * 
	 */
	public Labirinto labirinto;
	
	/**
	 *  Represents the hero.
	 * 
	 */
	public Heroi hero;
	
	/**
	 *  Represents the sword.
	 * 
	 */
	public Espada sword;
	
	/**
	 *  Represents the shield.
	 * 
	 */
	public Escudo shield;
	
	/**
	 *  List of dragon's in game.
	 * 
	 */
	public List<Dragao> dragoes;
	
	/**
	 *  List of arrow's the hero has left.
	 * 
	 */
	public List<Dardo> dardos;

	/**
	 *  Boolean that indicates if the game has ended or not.
	 * 
	 */
	public boolean termina;


	public static void main(String[] args) 
	{
		
		new Jogo(false);
	}


	public Jogo(boolean test)
	{
		if(!test)
		{
			if(Client.mensagemInicial(this))
				generateLabirinto();
			else standardLabirinto();
	
			Client.desenhaLabirinto(this);
			
			char tecla; 
			
			while (!termina)
			{
				tecla = caraterPremido();
	
				
				verificaMovimentoHeroi(tecla);
				moveDragoes();
				
				Client.desenhaLabirinto(this);
	
			}
	
			Client.mensagemFinal(this);
		}
	}
	
	/**
	 * Constructs and initializes the standard maze.
	 * 
	 */	
	public void standardLabirinto()
	{
		termina = false;
		
		dragoes = new ArrayList<Dragao>();
		dardos = new ArrayList<Dardo>();
		
		dimensaoLabirinto = 10;

		char labDefault[][] =  {
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		
		labirinto = new Labirinto();
		labirinto.labirinto =  labDefault;
		
		hero = new Heroi();
		hero.cor = new Coordenada(1,1);
		hero.protegido = true;

		sword = new Espada(1, 8);
		sword.recolhida = false;

		Dragao d = new Dragao(1, 3);
		dragoes.add(d);

		labirinto.Saida = new Coordenada(9, 5);
		
		shield = new Escudo(0, 0);
		shield.recolhida = true;
	}

	/**
	 * Constructs and initializes the maze generated.
	 * 
	 */	
	public void generateLabirinto()
	{
		termina = false;
	
		dragoes = new ArrayList<Dragao>();
		dardos = new ArrayList<Dardo>();
		
		labirinto = new Labirinto();
		labirinto.criaLabirinto(dimensaoLabirinto);

		hero = new Heroi();
		hero.deployHero(this);


		deployDragons();


		deployDarts();


		sword = new Espada();
		sword.deploySword(this);


		shield = new Escudo();
		shield.deployShield(this);
	}
	
	/**
	 * Verifies if there is a Dragon at the specified position (x,y).
	 * 
	 * @param x
	 *        the X coordinate.
	 * @param y
	 *        the Y coordinate.
	 *        
	 * @return the index of the Dragon that has those coordinates 
	 */	
	public int verificaDragao(int x, int y)
	{

		for(int i = 0; i < dragoes.size(); ++i)
		{
			if(dragoes.get(i).posicao.x == x && dragoes.get(i).posicao.y == y && dragoes.get(i).vivo)
				return i;
		}

		return -1;

	}

	/**
	 * Verifies if there is an Arrow at the specified position (x,y).
	 * 
	 * @param x
	 *        the X coordinate.
	 * @param y
	 *        the Y coordinate.
	 *        
	 * @return the index of the arrow that has those coordinates 
	 */	
	public int verificaDardo(int x, int y)
	{

		for(int i=0; i < dardos.size(); ++i)
		{
			if(dardos.get(i).posicao.x == x && dardos.get(i).posicao.y == y)
				return i;
		}

		return -1;
	}

	/**
	 * Sets dragons in the maze.
	 * 
	 */
	public void deployDragons()
	{
		Random randomGen = new Random ();
		boolean definido;
		int temp = numDragoes;

		while (numDragoes > 0)
		{
			definido = false;
			while(!definido)
			{
				int x = randomGen.nextInt(dimensaoLabirinto - 2) + 1;
				int y = randomGen.nextInt(dimensaoLabirinto - 2) + 1;

				if(x != hero.cor.x && y != hero.cor.y && labirinto.espacoLivreHeroi(y, x) && verificaDragao(x,y) == -1)
				{
					dragoes.add(new Dragao(x, y));

					definido = true;
				}
			}

			numDragoes--;
		}

		numDragoes = temp;
	}

	/**
	 * Sets arrows in the maze.
	 * 
	 */
	public void deployDarts()
	{
		Random randomGen = new Random ();
		int numDarts = randomGen.nextInt(3) + numDragoes;
		

		boolean definido;

		while (numDarts > 0)
		{
			definido = false;
			while(!definido)
			{
				int x = randomGen.nextInt(dimensaoLabirinto - 2) + 1;
				int y = randomGen.nextInt(dimensaoLabirinto - 2) + 1;
				
				
				if(!hero.cor.equals(new Coordenada(x, y)) && labirinto.espacoLivreDragao(y, x) && (verificaDragao(x,y) == -1) && (verificaDardo(x,y) == -1))
				{
					dardos.add(new Dardo(x, y));
					numDarts--;

					definido = true;
				}
			}

		}
	}

	/**
	 * Gets char associated with the key pressed.
	 *
	 * @return the <code>char</code> associated to the symbol specified
	 */
	public char caraterPremido() 
	{
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		char x = s.next().charAt(0);

		return x;
	}

	/**
	 * Moves Hero according to key selected.
	 * 
	 * @param tecla
	 *            key selected
	 */
	public void moveHeroi(char tecla)
	{

		if(tecla == 'w')
			hero.cor.y--;
		else if(tecla == 's')
			hero.cor.y++;
		else if(tecla == 'a')
			hero.cor.x --;
		else if(tecla == 'd')
			hero.cor.x ++;	


		int dardinho = verificaDardo(hero.cor.x ,hero.cor.y); 

		if (hero.cor.equals(sword.posicao) && !sword.recolhida)
		{
			hero.armado = true;
			sword.recolhida = true;
		}

		if (hero.cor.equals(shield.posicao) && !shield.recolhida)
		{
			hero.protegido = true;
			shield.recolhida = true;
		}

		if(dardinho != -1)
		{
			hero.numDardos++;
			dardos.remove(dardinho);
		}
		

		verificaHeroiDragao();

		if (hero.cor.equals(labirinto.Saida))
		{
			if (hero.armado && objetivoCompletado())
				 termina = true;
			else
			{
				if(tecla == 'w')
					hero.cor.y++;
				else if(tecla == 's')
					hero.cor.y--;
				else if(tecla == 'a')
					hero.cor.x ++;
				else if(tecla == 'd')
					hero.cor.x --;	
			}
		}

	}

	/**
	 * Checks whether the game has finish or not.
	 * 
	 * @return <code>true</code> if the game has ended.
	 */
	public boolean objetivoCompletado() 
	{
		for(int i = 0; i < dragoes.size(); i++)
			if(dragoes.get(i).vivo)
				return false;
		
		return true;
	}

	/**
	 * Moves all the Dragons in game.
	 * 
	 */
	public void moveDragoes()
	{
		for(int i = 0; i < dragoes.size(); ++i)
		{
			if(dragoes.get(i).vivo && dragonMoves != 0)
				moveDragao(dragoes.get(i));
		}

	}

	/**
	 * Moves a specific Dragon in game.
	 * 
	 * @param dragao	
	 *            dragon to be moved
	 */
	public void moveDragao(Dragao dragao)
	{
		Random randomGen = new Random ();

		if(!dragao.dormir)
		{
			boolean validAction = false;
			
			while(!validAction)
			{
				int orientation = randomGen.nextInt(dragonMoves);
				
				if(orientation == 0 && labirinto.espacoLivreDragao(dragao.posicao.y - 1, dragao.posicao.x) )
				{
					dragao.posicao.y--;
					validAction = true;
				}
				else if(orientation == 1 && labirinto.espacoLivreDragao(dragao.posicao.y + 1, dragao.posicao.x))
				{
					dragao.posicao.y++;
					validAction = true;
				}
				else if(orientation == 2 && labirinto.espacoLivreDragao(dragao.posicao.y, dragao.posicao.x - 1))
				{
					dragao.posicao.x--;
					validAction = true;
				}
				else if(orientation == 3 && labirinto.espacoLivreDragao(dragao.posicao.y, dragao.posicao.x + 1))
				{
					dragao.posicao.x++;
					validAction = true;
				}
				else if (orientation == 4)
				{
					validAction = true;
				}
				else if (orientation == 5)
				{
					dragao.dormir = true;
					dragao.niteracoes = 3;
					validAction = true;
				}
			}
			
			int sight = dragao.linhaMiraDragao(hero);
			int shootingProb = randomGen.nextInt(4);
			
			if(sight != -1 && shootingProb != 0 && !dragao.dormir)
				if(!dragao.verificaWall(sight, labirinto) && !hero.protegido)
				{
					hero.vivo = false;
					termina = true;
				}
		}
		else if(dragao.niteracoes == 0)
			dragao.dormir = false;
		else dragao.niteracoes--;
		

			
		
	}

	/**
	 * Checks hero's positions according to dragon's position.
	 * 
	 */
	public void verificaHeroiDragao()
	{
		for(int i = 0; i < dragoes.size(); ++i)
		{
			int k = dragaoAdjacente(i);

			if(k != -1)
			{
				if(hero.armado)
					dragoes.get(k).vivo = false;
				else if(!dragoes.get(k).dormir && dragoes.get(i).vivo)
				{
					hero.vivo = false;
					termina = true;
				}
			}
		}
	}

	/**
	 * Checks if hero can go to the direction given.
	 * 
	 * @param tecla
	 * 			  direction given
	 */
	public void verificaMovimentoHeroi(char tecla) 
	{

		if (tecla == 'w') 
		{
			if(labirinto.espacoLivreHeroi(hero.cor.y - 1, hero.cor.x ))
				moveHeroi(tecla);
		} 

		if (tecla == 's') 
		{
			if(labirinto.espacoLivreHeroi(hero.cor.y + 1, hero.cor.x ))
				moveHeroi(tecla);
		} 

		if (tecla == 'a') 
		{
			if(labirinto.espacoLivreHeroi(hero.cor.y, hero.cor.x - 1))
				moveHeroi(tecla);
		} 

		if (tecla == 'd') 
		{
			if(labirinto.espacoLivreHeroi(hero.cor.y, hero.cor.x + 1))
				moveHeroi(tecla);
		}

		if (tecla == 'e')
		{
			char direcao = Client.direcaoDardo(this);
			if(direcao == 'w' || direcao == 'a' || direcao == 's' || direcao == 'd' || direcao == 'e')
				hero.disparaDardo(direcao, this);
			
		}
	}

	/**
	 * Checks if hero has an Dragon in a adjacent position.
	 * 
	 * @param i
	 * 			index of the dragon in the dragons list.
	 */
	public int dragaoAdjacente(int i)
	{

		if(dragoes.get(i).posicao.y + 1 == hero.cor.y && dragoes.get(i).posicao.x == hero.cor.x || dragoes.get(i).posicao.y - 1 == hero.cor.y && dragoes.get(i).posicao.x == hero.cor.x )
			return i;
		else if (dragoes.get(i).posicao.y == hero.cor.y && dragoes.get(i).posicao.x + 1 == hero.cor.x || dragoes.get(i).posicao.y == hero.cor.y && dragoes.get(i).posicao.x - 1 == hero.cor.x )
			return i;
		else if (dragoes.get(i).posicao.x == hero.cor.x && dragoes.get(i).posicao.y == hero.cor.y)
			return i;
		else return -1;
	}

	
	
}
