package maze.test;

import static org.junit.Assert.*;
import maze.logic.*;

import org.junit.Test;

public class testGameplay {

	Jogo jogo;
	
	@Test
	public void testVerificaDragao() 
	{
		jogo = new Jogo(true);
		
		jogo.standardLabirinto();
		
		assertEquals(jogo.verificaDragao(9,  9), -1);
		assertEquals(jogo.verificaDragao(1, 3), 0);
	}

	@Test
	public void testVerificaDardo() 
	{
		jogo = new Jogo(true);
		
		
		jogo.standardLabirinto();
		
		
		jogo.dardos.add(new Dardo(2, 1));
		
		assertEquals(jogo.verificaDardo(9,  9), -1);
		assertEquals(jogo.verificaDardo(2, 1), 0);
	}
	
	
}
