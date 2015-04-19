package maze.test;

import static org.junit.Assert.*;
import maze.logic.*;

import org.junit.Test;

public class MazeGenerationTesting {

	Jogo jogo;
	
	@Test
	public void generateMaze() 
	{
		jogo = new Jogo(true);
		
		jogo.dimensaoLabirinto = 15;
		jogo.labirinto = new Labirinto();
		jogo.labirinto.criaLabirinto(jogo.dimensaoLabirinto);
		
		assertEquals(jogo.labirinto.labirinto.length, jogo.dimensaoLabirinto);
		
		assertEquals(jogo.labirinto.celulasVisitadas.length, (jogo.dimensaoLabirinto - 1)/2);
		
		for(int h = 0; h < jogo.labirinto.celulasVisitadas.length; ++h)
			for(int w = 0; w < jogo.labirinto.celulasVisitadas.length; ++w)
				assertEquals(jogo.labirinto.celulasVisitadas[h][w], '+');
		
		
		jogo.dimensaoLabirinto = 14;
		jogo.labirinto.criaLabirinto(jogo.dimensaoLabirinto);
		
		assertEquals(jogo.labirinto.labirinto.length, jogo.dimensaoLabirinto);
		
		assertEquals(jogo.labirinto.celulasVisitadas.length, (jogo.dimensaoLabirinto - 1)/2);
		
		for(int h = 0; h < jogo.labirinto.celulasVisitadas.length; ++h)
			for(int w = 0; w < jogo.labirinto.celulasVisitadas.length; ++w)
				assertEquals(jogo.labirinto.celulasVisitadas[h][w], '+');
		
	}
	
	@Test
	public void testFreeSpaceDragon()
	{
		jogo = new Jogo(true);
		
		jogo.dimensaoLabirinto = 13;
		jogo.labirinto = new Labirinto();
		jogo.labirinto.criaLabirinto(13);
		
		assertFalse(jogo.labirinto.espacoLivreDragao(jogo.labirinto.Saida.y, jogo.labirinto.Saida.x));
		
		jogo.labirinto.labirinto[3][3] = ' ';
		assertTrue(jogo.labirinto.espacoLivreDragao(3,3));

		jogo.labirinto.labirinto[3][3] = 'X';
		assertFalse(jogo.labirinto.espacoLivreDragao(3,3));
	}

	
	@Test
	public void testFreePath()
	{
		jogo = new Jogo(true);
		
		jogo.dimensaoLabirinto = 13;
		jogo.labirinto = new Labirinto();
		jogo.labirinto.criaLabirinto(13);
		
		jogo.labirinto.labirinto[3][1] = ' ';
		jogo.labirinto.labirinto[3][2] = ' ';
		jogo.labirinto.labirinto[3][3] = ' ';
		assertTrue(jogo.labirinto.caminhoLivre(new Coordenada(1, 3), new Coordenada(3, 3)));
		assertTrue(jogo.labirinto.caminhoLivre(new Coordenada(3,3), new Coordenada(1, 3)));
		
		jogo.labirinto.labirinto[3][2] = 'X';
		assertFalse(jogo.labirinto.caminhoLivre(new Coordenada(1,3), new Coordenada(3, 3)));
		assertFalse(jogo.labirinto.caminhoLivre(new Coordenada(3,3), new Coordenada(1, 3)));
		
		
		jogo.labirinto.labirinto[1][1] = ' ';
		jogo.labirinto.labirinto[2][1] = ' ';
		jogo.labirinto.labirinto[3][1] = ' ';
		assertTrue(jogo.labirinto.caminhoLivre(new Coordenada(1,1), new Coordenada(1, 3)));
		assertTrue(jogo.labirinto.caminhoLivre(new Coordenada(1,3), new Coordenada(1, 1)));
		
		jogo.labirinto.labirinto[2][1] = 'X';
		assertFalse(jogo.labirinto.caminhoLivre(new Coordenada(1,1), new Coordenada(1, 3)));
		assertFalse(jogo.labirinto.caminhoLivre(new Coordenada(1,3), new Coordenada(1, 1)));
		
		assertTrue(jogo.labirinto.caminhoLivre(new Coordenada(1,1), new Coordenada(1, 1)));
	}
}
