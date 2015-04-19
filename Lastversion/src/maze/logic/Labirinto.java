package maze.logic;
import java.io.Serializable;
import java.util.*;

/**  
* 
* Labirinto.java - a simple class that represents the maze.
* 
* @see Dardo
* @see Heroi
* @see Escudo
* @see Dragao
* @see Espada
* @see Jogo
*/
@SuppressWarnings("serial")
public class Labirinto implements Serializable
{

	/**
	 * The coordinate of the exit.
	 * 
	 * @see {@link Coordenada}
	 */
	public Coordenada Saida;

	/**
	 * Labyrinth's visited cells representation.
	 *
	 */
	public char celulasVisitadas[][];
	
	/**
	 * Labyrinth representation.
	 * 
	 */
	public char labirinto[][];

	
	/**
	 * Constructs a labyrinth.
	 * 
	 * @param dimensao
	 * 				maze's dimension
	 * 
	 */
	public void criaLabirinto(int dimensao)
	{
		if(dimensao % 2 == 1)
			criaLabirintoImpar(dimensao);
		else 
			criaLabirintoPar(dimensao);
	}
	
	/**
	 * Constructs an odd labyrinth.
	 * 
	 * @param dimensao
	 * 				maze's dimension
	 * 
	 */
	public void criaLabirintoImpar(int dimensao)
	{
		int dimensaoCelulasVisitadas = (dimensao-1)/2;
		
		labirinto = new char[dimensao][dimensao];
		celulasVisitadas = new char[dimensaoCelulasVisitadas][dimensaoCelulasVisitadas];

		defaultLabirinto(dimensao);

		defineSaida(dimensao);

		defaultCelulasVisitadas(dimensaoCelulasVisitadas);

		genPath(dimensaoCelulasVisitadas);

	}

	/**
	 * Constructs an even labyrinth.
	 * 
	 * @param dimensao
	 * 				maze's dimension
	 * 
	 */
	public void criaLabirintoPar(int dimensao)
	{
		criaLabirintoImpar(dimensao-1);
		
		char labirintoPar[][] = new char[dimensao][dimensao];
		
		for(int height = 0; height < dimensao-1; height++)
			for (int width = 0; width < dimensao-1; width++)
			{
				if (width == dimensao - 2 && height == dimensao - 2)
				{
					labirintoPar[height][width] = ' ';
					labirintoPar[height+1][width+1] = labirinto[height][width];
				}
				else if (width == dimensao - 2)
				{
					labirintoPar[height][width] = ' ';
					labirintoPar[height][width+1] = labirinto[height][width];
				}
				else if (height == dimensao - 2)
				{
					labirintoPar[height][width] = ' ';
					labirintoPar[height+1][width] = labirinto[height][width];
				}
				else labirintoPar[height][width] = labirinto[height][width];
				
			}
		
		labirintoPar[0][dimensao-2] = 'X';
		labirintoPar[dimensao-2][0] = 'X';
		labirintoPar[dimensao-2][dimensao-1] = 'X';
		labirintoPar[dimensao-1][dimensao-2] = 'X';
		
		labirinto = null;
		labirinto = labirintoPar;
		
		corrigeLabirintoPar(dimensao);
		
	}
	
	/**
	 * Constructs default labyrinth.
	 * 
	 * @param dimensao
	 * 				maze's dimension
	 * 
	 */
	public void defaultLabirinto(int dimensao)
	{		

		for(int i=0; i<dimensao ; ++i)
		{
			for(int j =0; j<dimensao; ++j)
			{
				if(i % 2 == 0 || j % 2 == 0)
					labirinto[i][j]= 'X';
				else labirinto[i][j] = ' ';
			}
		}		

	}

	/**
	 * Constructs default visited cells.
	 * 
	 * @param dimensaoCelulasVisitadas
	 * 								visited cell's dimension
	 * 
	 */
	public void defaultCelulasVisitadas(int dimensaoCelulasVisitadas)
	{
		for(int i = 0; i < dimensaoCelulasVisitadas; i++)
			for (int j = 0; j < dimensaoCelulasVisitadas; j++)
				celulasVisitadas[i][j] = '.';	
	}
	
	/**
	 * Adds a row and a column to the labyrinth in order to meet the criteria.
	 * 
	 * @param dimensao
	 * 				labyrinth's dimension
	 * 
	 */
	public void corrigeLabirintoPar(int dimensao)
	{
		for(int height = 2; height < dimensao-2; height++)
		{
			if(Saida.x != dimensao-1 && Saida.y != height)
			{
				if (espacoLivreHeroi(height-1, dimensao-3) && espacoLivreHeroi(height+1, dimensao-3) && espacoLivreHeroi(height-1, dimensao-2) && espacoLivreHeroi(height+1, dimensao-2))
					labirinto[height][dimensao-2] = 'X';
			}
		}
		
		for(int width = 2; width < dimensao-2; width++)
		{
			if(Saida.x != width && Saida.y != dimensao-1)
			{
				if (espacoLivreHeroi(dimensao-3, width-1) && espacoLivreHeroi(dimensao-3, width+1) && espacoLivreHeroi(dimensao-2, width-1) && espacoLivreHeroi(dimensao-2, width+1))
					labirinto[dimensao-2][width] = 'X';
			}
		} 
		
		labirinto[dimensao-2][dimensao-2] = 'X';
		
		if (Saida.x == dimensao-2)
			Saida.x = dimensao-1;
		else if (Saida.y == dimensao-2)
			Saida.y = dimensao-1;
			
	}
	
	/**
	 * Generates path for the labyrinth.
	 * 
	 * @param dimensaoCelulasVisitadas
	 * 							visited cell's dimension
	 * 
	 */
	public void genPath(int dimensaoCelulasVisitadas)
	{
		Deque<Coordenada> pilha = new ArrayDeque<Coordenada>();
		
		int x = 1, y = 1;
		
		if(Saida.x == 0)
			y = Saida.y;
		else if (Saida.x == dimensaoCelulasVisitadas*2)
		{
			x = Saida.x - 1;
			y = Saida.y;
		}
		else if (Saida.y == 0)
			x = Saida.x;
		else if (Saida.y == dimensaoCelulasVisitadas*2)
		{
			x = Saida.x;
			y = Saida.y - 1;
		}
		
		int coordx = (x-1)/2;
		int coordy = (y-1)/2;
		Coordenada coord = new Coordenada(coordx, coordy);

		celulasVisitadas[coordy][coordx] = '+';

		pilha.push(coord);


		while(pilha.size() > 0)
		{

			coordx = pilha.getFirst().x;
			coordy = pilha.getFirst().y;

			if(!verificaLimites(coordx, coordy, dimensaoCelulasVisitadas))
				pilha.pop();
			else 
			{
				int orientacao = geraOrientacao();
				
				x = coordx * 2 + 1;
				y = coordy * 2 + 1;
				
				if(orientacao == 0 && coordy > 0 )
				{
					if(celulasVisitadas[coordy - 1][coordx] == '.')
					{
						coordy--;
						celulasVisitadas[coordy][coordx] = '+';

						y--;
						labirinto[y][x] = ' ';
						y--;

						Coordenada temp = new Coordenada(coordx, coordy);
						pilha.push(temp);
					}
				}
				else if (orientacao == 1 && coordy < (dimensaoCelulasVisitadas - 1))
				{
					if(celulasVisitadas[coordy + 1][coordx] == '.')
					{
						coordy++;
						celulasVisitadas[coordy][coordx] = '+';

						y++;
						labirinto[y][x] = ' ';
						y++;
						labirinto[y][x] = ' ';

						Coordenada temp = new Coordenada(coordx, coordy);
						pilha.push(temp);
					}
				}
				else if(orientacao == 2 && coordx > 0)
				{
					if(celulasVisitadas[coordy][coordx - 1] == '.')
					{
						coordx--;
						celulasVisitadas[coordy][coordx] = '+';

						x--;
						labirinto[y][x] = ' ';
						x--;
						labirinto[y][x] = ' ';

						Coordenada temp = new Coordenada(coordx, coordy);
						pilha.push(temp);
					}
				}
				else if (orientacao == 3 && coordx < (dimensaoCelulasVisitadas - 1))
				{
					if(celulasVisitadas[coordy][coordx + 1] == '.')
					{
						coordx++;
						celulasVisitadas[coordy][coordx] = '+';

						x++;
						labirinto[y][x] = ' ';
						x++;
						labirinto[y][x] = ' ';

						Coordenada temp = new Coordenada(coordx, coordy);
						pilha.push(temp);
					}
				}
			}
			
		}
		
		addNewPaths();
	}

	/**
	 * Adds paths to the labyrinth.
	 *
	 */
	public void addNewPaths()
	{
		Random randomGen = new Random ();
		int numPaths = 1 + randomGen.nextInt(3);

		while(numPaths > 0)
		{
			int height = 1 + randomGen.nextInt(labirinto.length - 2);
			int width = 1 + randomGen.nextInt(labirinto.length - 2);
			
			if(labirinto[height][width] == 'X')
			{
				if(labirinto[height][width-1] == 'X' && labirinto[height][width+1] == 'X' || labirinto[height-1][width] == 'X' && labirinto[height+1][width] == 'X')
				{
					labirinto[height][width] = ' ';
					numPaths--;
				}
			}
		}
	}
	
	/**
	 * Verifies limits of a specified position (x,y).
	 * 
	 * @param x
	 *        the X coordinate.
	 * @param y
	 *        the Y coordinate.
	 * @param dimensao
	 * 				visited cell's dimension
	 *       
	 * @return <code>true</code> if a limit is found.
	 */
	public boolean verificaLimites(int x, int y, int dimensao)
	{
		if (x == 0 && y == 0)
			return checkDown(x, y) || checkRight(x, y);
		else if (x == 0 && y == dimensao-1)
			return checkUp(x, y) || checkRight(x, y);
		else if (x == dimensao-1 && y == 0)
			return checkDown(x, y) || checkLeft(x, y);
		else if (x == dimensao-1 && y == dimensao-1)
			return checkUp(x, y) || checkLeft(x, y);
		else if (x == 0)
			return checkDown(x, y) || checkUp(x, y) || checkRight(x, y);
		else if (x == dimensao-1)
			return checkDown(x, y) || checkUp(x, y) || checkLeft(x, y);
		else if (y == 0)
			return checkDown(x, y) || checkLeft(x, y) || checkRight(x, y);
		else if (y == dimensao-1)
			return checkUp(x, y) || checkLeft(x, y) || checkRight(x, y);
		else return checkDown(x,y) || checkUp(x, y) || checkLeft(x, y) || checkRight(x, y);

	}

	/**
	 * Checks content of the upper position (x,y).
	 * 
	 * @param x
	 *        the X coordinate.
	 * @param y
	 *        the Y coordinate.
	 *       
	 * @return <code>true</code> if the coordinate hasn't been visit.
	 */
	public boolean checkUp(int x, int y)
	{
		return celulasVisitadas[y-1][x] == '.';
	}
	
	/**
	 * Checks content of the down position (x,y).
	 * 
	 * @param x
	 *        the X coordinate.
	 * @param y
	 *        the Y coordinate.
	 *       
	 * @return <code>true</code> if the coordinate hasn't been visit.
	 */
	public boolean checkDown(int x, int y)
	{
		return celulasVisitadas[y+1][x] == '.';
	}
	
	/**
	 * Checks content of the position (x,y) to the right.
	 * 
	 * @param x
	 *        the X coordinate.
	 * @param y
	 *        the Y coordinate.
	 *       
	 * @return <code>true</code> if the coordinate hasn't been visit.
	 */
	public boolean checkRight(int x, int y)
	{
		return celulasVisitadas[y][x+1] == '.';
	}
	
	/**
	 *  Checks content of the position (x,y) to the left.
	 * 
	 * @param x
	 *        the X coordinate.
	 * @param y
	 *        the Y coordinate.
	 *       
	 * @return <code>true</code> if the coordinate hasn't been visit.
	 */
	public boolean checkLeft(int x, int y)
	{
		return celulasVisitadas[y][x-1] == '.';
	}
	
	/**
	 * Generates random direction.
	 *       
	 * @return direction.
	 */
	public int geraOrientacao()
	{
		Random randomGen = new Random ();
		return randomGen.nextInt(4);
	}

	/**
	 * Sets Exit in the maze.
	 * 
	 * @param dimensao
	 * 				maze's dimension.
	 *       
	 * @return direction.
	 */
	public void defineSaida(int dimensao)
	{
		Random randomGen = new Random ();
		boolean definido = false;

		while(!definido)
		{
			
			int x = randomGen.nextInt(dimensao);
			int y = randomGen.nextInt(dimensao);

			if( x == 0 || x == (dimensao - 1) )
			{
				if(y % 2 == 1)
				{
					Saida = new Coordenada(x, y);
					definido = true;
				}
			}
			if( y == 0 || y == (dimensao - 1))
			{
				if(x % 2 == 1)
				{
					Saida = new Coordenada(x, y);
					definido = true;
				}
			}	
		}
	}

	/**
	 * Verifies if the specified position (x,y) is an open space for the hero.
	 * 
	 * @param width
	 *       	 the X coordinate.
	 * @param height
	 *        	the Y coordinate.
	 *       
	 * @return <code>true</code> if the position (x,y) is an open space.
	 */
	public boolean espacoLivreHeroi(int height, int width)
	{
		return labirinto[height][width] != 'X' || Saida.equals(new Coordenada(width, height));
	}

	/**
	 * Verifies if the specified position (x,y) is an open space for the dragon.
	 * 
	 * @param width
	 *       	 the X coordinate.
	 * @param height
	 *        	the Y coordinate.
	 *       
	 * @return <code>true</code> if the position (x,y) is an open space.
	 */
	public boolean espacoLivreDragao(int height, int width)
	{
		return (labirinto[height][width] == ' ' && !(Saida.equals(new Coordenada(width, height)))); 
	}

	/**
	 * Verifies if from posicao to coordenada is open space, if there is no walls between them. 
	 * @param posicao
	 *       	 	first coordinate.
	 * @param coordenada
	 *       		second coordinate.
	 * @return <code>true</code> if there is no walls between posicao and coordenada.
	 */
	public boolean caminhoLivre(Coordenada posicao, Coordenada coordenada) 
	{
		if(posicao.x == coordenada.x && posicao.y < coordenada.y)
		{
			for(int i = posicao.y; i < coordenada.y; i++)
				if(labirinto[i][posicao.x] == 'X')
					return false;
			
			return true;
		}
		
		if(posicao.x == coordenada.x && posicao.y > coordenada.y)
		{
			for(int i = coordenada.y; i < posicao.y; i++)
				if(labirinto[i][posicao.x] == 'X')
					return false;
			
			return true;
		}
		
		if(posicao.y == coordenada.y && posicao.x < coordenada.x)
		{
			for(int i = posicao.x; i < coordenada.x; i++)
				if(labirinto[posicao.y][i] == 'X')
					return false;
			
			return true;
		}
		
		if(posicao.y == coordenada.y && posicao.x > coordenada.x)
		{
			for(int i = coordenada.x; i < posicao.x; i++)
				if(labirinto[posicao.y][i] == 'X')
					return false;
			
			return true;
		}
		
		return true;
		
	}

}
