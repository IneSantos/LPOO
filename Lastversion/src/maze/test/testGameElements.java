package maze.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import maze.logic.*;

import org.junit.Test;

public class testGameElements {

	Jogo jogo;
	
	@Test
	public void testDeployHero()
	{
		jogo = new Jogo(true);
		jogo.hero = new Heroi();
		
		jogo.dimensaoLabirinto = 9;
		jogo.labirinto = new Labirinto();
		jogo.labirinto.criaLabirinto(9);
		
		jogo.labirinto.Saida = new Coordenada(0, 2);
		jogo.hero.deployHero(jogo);
		assertTrue(jogo.hero.cor.x == jogo.dimensaoLabirinto - 2);
		assertEquals(jogo.labirinto.labirinto[jogo.hero.cor.y][jogo.hero.cor.x], ' ');
		
		jogo.labirinto.Saida = new Coordenada(jogo.dimensaoLabirinto-1, 2);
		jogo.hero.deployHero(jogo);
		assertTrue(jogo.hero.cor.x == 1);
		assertEquals(jogo.labirinto.labirinto[jogo.hero.cor.y][jogo.hero.cor.x], ' ');
		
		jogo.labirinto.Saida = new Coordenada(2, 0);
		jogo.hero.deployHero(jogo);
		assertTrue(jogo.hero.cor.y == jogo.dimensaoLabirinto - 2);
		assertEquals(jogo.labirinto.labirinto[jogo.hero.cor.y][jogo.hero.cor.x], ' ');
		
		jogo.labirinto.Saida = new Coordenada(2, jogo.dimensaoLabirinto - 1);
		jogo.hero.deployHero(jogo);
		assertTrue(jogo.hero.cor.y == 1);
		assertEquals(jogo.labirinto.labirinto[jogo.hero.cor.y][jogo.hero.cor.x], ' ');
	}
	
	@Test
	public void testShootDart()
	{
		jogo = new Jogo(true);
		
		jogo.standardLabirinto();
		jogo.hero.numDardos = 4;
		
		jogo.hero.disparaDardo('s', jogo);
		assertTrue(jogo.dragoes.get(0).vivo == false);
		assertEquals(jogo.hero.numDardos, 3);
		
		jogo.hero.cor = new Coordenada(4,5);
		jogo.dragoes.add(new Dragao(4,2));
		jogo.hero.disparaDardo('w', jogo);
		assertTrue(jogo.dragoes.get(1).vivo == false);
		assertEquals(jogo.hero.numDardos, 2);
		
		jogo.dragoes.add(new Dragao(1,5));
		jogo.hero.disparaDardo('a', jogo);
		assertTrue(jogo.dragoes.get(2).vivo == false);
		assertEquals(jogo.hero.numDardos, 1);
		
		jogo.dragoes.add(new Dragao(7,5));
		jogo.hero.disparaDardo('d',jogo);
		assertTrue(jogo.dragoes.get(3).vivo == false);
		assertEquals(jogo.hero.numDardos, 0);
		
	}
	
	@Test
	public void testDeploySword() 
	{
		jogo = new Jogo(true);
		
		jogo.dimensaoLabirinto = 9;
		jogo.labirinto = new Labirinto();
		jogo.labirinto.criaLabirinto(9);
		
		jogo.hero = new Heroi();
		jogo.hero.deployHero(jogo);
		jogo.sword = new Espada();
		jogo.sword.deploySword(jogo);
		
		assertNotNull(jogo.sword.posicao);
		
		assertFalse(jogo.sword.posicao.equals(jogo.hero.cor));
		assertTrue(jogo.labirinto.espacoLivreHeroi(jogo.sword.posicao.y, jogo.sword.posicao.x));
	}

	@Test
	public void testDeployShield()
	{
		jogo = new Jogo(true);
		
		jogo.dimensaoLabirinto = 9;
		jogo.labirinto = new Labirinto();
		jogo.labirinto.criaLabirinto(9);
		jogo.hero = new Heroi();
		jogo.hero.deployHero(jogo);
		jogo.sword = new Espada();
		jogo.sword.deploySword(jogo);
		jogo.labirinto.labirinto[2][2] = ' ';
		jogo.labirinto.labirinto[3][3] = ' ';
		jogo.dardos = new ArrayList<Dardo>();
		jogo.dardos.add(new Dardo(2,2));
		jogo.dardos.add(new Dardo(3,3));
		
		jogo.shield = new Escudo();
		jogo.shield.deployShield(jogo);
		assertNotNull(jogo.shield.posicao);
	}
	
	@Test
	public void testDragonSight()
	{
		jogo = new Jogo(true);
		
		jogo.standardLabirinto();
		jogo.hero.cor = new Coordenada(4,5);
		
		jogo.dragoes.add(new Dragao(4, 3));
		assertFalse(jogo.dragoes.get(1).linhaMiraDragao(jogo.hero) == -1);

		jogo.dragoes.add(new Dragao(4, 7));
		assertFalse(jogo.dragoes.get(2).linhaMiraDragao(jogo.hero) == -1);

		jogo.dragoes.add(new Dragao(2, 5));
		assertFalse(jogo.dragoes.get(3).linhaMiraDragao(jogo.hero) == -1);

		jogo.dragoes.add(new Dragao(6, 5));
		assertFalse(jogo.dragoes.get(4).linhaMiraDragao(jogo.hero) == -1);

		jogo.dragoes.add(new Dragao(1, 1));
		assertTrue(jogo.dragoes.get(5).linhaMiraDragao(jogo.hero) == -1);
	}
	
	@Test
	public void testDragonWall()
	{
		jogo = new Jogo(true);
		
		jogo.standardLabirinto();
		
		jogo.hero.cor = new Coordenada(5,8);
		jogo.dragoes.get(0).posicao = new Coordenada(5,5);
		assertTrue(jogo.dragoes.get(0).verificaWall(1, jogo.labirinto));
		
		jogo.hero.cor = new Coordenada(5,5);
		jogo.dragoes.get(0).posicao = new Coordenada(5,8);
		assertTrue(jogo.dragoes.get(0).verificaWall(2, jogo.labirinto));
		
		jogo.hero.cor = new Coordenada(4,7);
		jogo.dragoes.get(0).posicao = new Coordenada(6,7);
		assertTrue(jogo.dragoes.get(0).verificaWall(3, jogo.labirinto));
		
		jogo.hero.cor = new Coordenada(6,7);
		jogo.dragoes.get(0).posicao = new Coordenada(4,7);
		assertTrue(jogo.dragoes.get(0).verificaWall(4, jogo.labirinto));
		
		jogo.hero.cor = new Coordenada(6,7);
		jogo.dragoes.get(0).posicao = new Coordenada(6,5);
		assertFalse(jogo.dragoes.get(0).verificaWall(2, jogo.labirinto));
	}
	
	@Test
	public void testDeployDragons()
	{
		jogo = new Jogo(true);
		jogo.dimensaoLabirinto = 10;
		jogo.numDragoes = 3;
		jogo.generateLabirinto();
		
		assertEquals(jogo.dragoes.size(), jogo.numDragoes);

		assertTrue(jogo.dragoes.get(0).vivo);
		assertTrue(jogo.dragoes.get(1).vivo);
		assertTrue(jogo.dragoes.get(2).vivo);
		
		
	}
	
	@Test
	public void testCatchShield()
	{
		jogo = new Jogo(true);
		jogo.dimensaoLabirinto = 10;
		
		jogo.standardLabirinto();
		jogo.hero.protegido = false;
		jogo.shield = new Escudo(2, 1);
		
		assertFalse(jogo.shield.recolhida);
		assertFalse(jogo.hero.protegido);
		jogo.moveHeroi('d');
		assertTrue(jogo.shield.recolhida);
		assertTrue(jogo.hero.protegido);
		
	}
	
	@Test
	public void testCatchDart()
	{
		jogo = new Jogo(true);
		jogo.dimensaoLabirinto = 10;
		
		jogo.standardLabirinto();
		
		jogo.dardos.add(new Dardo(2, 1));
		
		assertEquals(jogo.hero.numDardos, 0);
		jogo.moveHeroi('d');
		assertEquals(jogo.hero.numDardos, 1);
		assertTrue(jogo.dardos.size() == 0);
		
	}
	
}
