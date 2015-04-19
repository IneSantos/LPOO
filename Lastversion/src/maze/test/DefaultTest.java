package maze.test;

import static org.junit.Assert.assertEquals;
import maze.logic.*;

import org.junit.Test;

public class DefaultTest {


	Jogo jogo;
	
	
	@Test
	public void testDefaultLabirinto()
	{
		jogo = new Jogo(true);
		
		//maze testing
		jogo.standardLabirinto();
		
		char[][] labirintoTest = {
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
		
		assertEquals(jogo.dimensaoLabirinto, jogo.labirinto.labirinto.length);
		assertEquals(jogo.labirinto.Saida, new Coordenada(9, 5));
		
		for(int y = 0; y < jogo.dimensaoLabirinto; y++)
			for(int x = 0; x < jogo.dimensaoLabirinto; x++)
				assertEquals(jogo.labirinto.labirinto[y][x], labirintoTest[y][x]);
				
		//hero testing
		assertEquals(jogo.hero.protegido, true);
		assertEquals(jogo.hero.armado, false);
		assertEquals(jogo.hero.vivo, true);
		assertEquals(jogo.hero.numDardos, 0);
		assertEquals(jogo.hero.cor, new Coordenada(1, 1));
		
		//sword testing
		assertEquals(jogo.sword.recolhida, false);
		assertEquals(jogo.sword.posicao, new Coordenada(1, 8));
		
		//shield testing
		assertEquals(jogo.shield.recolhida, true);
		assertEquals(jogo.shield.posicao, new Coordenada(0,0));
		
		//dragon testing
		assertEquals(jogo.dragoes.size(), 1);
		assertEquals(jogo.dragoes.get(0).vivo, true);
		assertEquals(jogo.dragoes.get(0).posicao, new Coordenada(1, 3));
		assertEquals(jogo.dragoes.get(0).dormir, false);
		assertEquals(jogo.dragoes.get(0).niteracoes, 0);
		
		//darts testing
		assertEquals(jogo.dardos.size(), 0);
	}
	
	@Test
	public void testHeroCollision() 
	{
		jogo = new Jogo(true);
		
		jogo.standardLabirinto();
		
		jogo.verificaMovimentoHeroi('w');
		assertEquals(jogo.hero.cor, new Coordenada(1, 1));
		jogo.verificaMovimentoHeroi('a');
		assertEquals(jogo.hero.cor, new Coordenada(1, 1));
		jogo.verificaMovimentoHeroi('d');
		jogo.verificaMovimentoHeroi('d');
		assertEquals(jogo.hero.cor, new Coordenada(3, 1));
		jogo.verificaMovimentoHeroi('d');
		jogo.verificaMovimentoHeroi('s');
		assertEquals(jogo.hero.cor, new Coordenada(4, 2));
		jogo.verificaMovimentoHeroi('d');
		assertEquals(jogo.hero.cor, new Coordenada(4, 2));
		jogo.verificaMovimentoHeroi('w');
		assertEquals(jogo.hero.cor, new Coordenada(4, 1));
		jogo.verificaMovimentoHeroi('a');
		assertEquals(jogo.hero.cor, new Coordenada(3, 1));
		jogo.verificaMovimentoHeroi('s');
		assertEquals(jogo.hero.cor, new Coordenada(3, 1));

	}
	
	@Test
	public void testHeroDead()
	{
		jogo = new Jogo(true);
		
		jogo.standardLabirinto();
		
		jogo.verificaMovimentoHeroi('s');
		assertEquals(jogo.hero.cor, new Coordenada(1, 2));
		assertEquals(jogo.hero.vivo, false);
		assertEquals(jogo.dragoes.get(0).vivo, true);
	}
	
	@Test
	public void testHeroKill()
	{
		jogo = new Jogo(true);
		
		jogo.standardLabirinto();
		jogo.hero.armado = true;
		
		jogo.verificaMovimentoHeroi('s');
		assertEquals(jogo.hero.cor, new Coordenada(1, 2));
		assertEquals(jogo.hero.vivo, true);
		assertEquals(jogo.dragoes.get(0).vivo, false);
	}
	
	@Test 
	public void testCatchSword()
	{
		jogo = new Jogo(true);
		
		jogo.standardLabirinto();
		jogo.dragoes.get(0).vivo = false;
		
		jogo.verificaMovimentoHeroi('s');
		jogo.verificaMovimentoHeroi('s');
		jogo.verificaMovimentoHeroi('s');
		jogo.verificaMovimentoHeroi('s');
		jogo.verificaMovimentoHeroi('s');
		jogo.verificaMovimentoHeroi('s');
		jogo.verificaMovimentoHeroi('s');
		assertEquals(jogo.hero.armado, true);
		assertEquals(jogo.shield.recolhida, true);
	}
	
	@Test
	public void testDefeat()
	{
		jogo = new Jogo(true);
		
		jogo.standardLabirinto();
		jogo.labirinto.Saida = new Coordenada(1, 0);
		assertEquals(jogo.termina, false);

		jogo.verificaMovimentoHeroi('w');
		assertEquals(jogo.hero.cor.equals(new Coordenada(1,1)), true);
		assertEquals(jogo.termina, false);
		
		jogo.hero.armado = true;
		jogo.verificaMovimentoHeroi('w');
		assertEquals(jogo.hero.cor.equals(new Coordenada(1,1)), true);
		assertEquals(jogo.termina, false);
	}
	
	@Test
	public void testVictory()
	{
		jogo = new Jogo(true);
		
		jogo.standardLabirinto();
		jogo.labirinto.Saida = new Coordenada(1, 0);
		jogo.dragoes.get(0).vivo = false;
		jogo.hero.armado = true;
		
		jogo.verificaMovimentoHeroi('w');
		assertEquals(jogo.hero.cor.equals(new Coordenada(1,0)), true);
		assertEquals(jogo.termina, true);
	}
}
