package pacman.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pacman.GUI.GameEngine;
import pacman.logic.*;

public class GameTest {

	@Test
	public void test() {
		GameEngine.game = new Game();
		
		assertEquals(GameEngine.game.getLevel(), 1);
		assertEquals(new Position(GameEngine.game.getRedGhost().getX(),GameEngine.game.getRedGhost().getY()), new Position(13*GameEngine.TILE_DIMENSION, 11*GameEngine.TILE_DIMENSION));
		assertEquals(new Position(GameEngine.game.getPinkGhost().getX(),GameEngine.game.getPinkGhost().getY()), new Position(13*GameEngine.TILE_DIMENSION, 15*GameEngine.TILE_DIMENSION));
		assertEquals(new Position(GameEngine.game.getOrangeGhost().getX(),GameEngine.game.getOrangeGhost().getY()), new Position(14*GameEngine.TILE_DIMENSION, 13*GameEngine.TILE_DIMENSION));
		assertEquals(new Position(GameEngine.game.getBlueGhost().getX(),GameEngine.game.getBlueGhost().getY()), new Position(13*GameEngine.TILE_DIMENSION, 13*GameEngine.TILE_DIMENSION));
		assertEquals(new Position(GameEngine.game.getPacman().getX(), GameEngine.game.getPacman().getY()), new Position(13*GameEngine.TILE_DIMENSION, 17*GameEngine.TILE_DIMENSION));
		
		GameEngine.game.nextLevel();
		GameEngine.game.initLevel(true);
		assertEquals(GameEngine.game.getLevel(), 2);
		assertEquals(new Position(GameEngine.game.getRedGhost().getX(),GameEngine.game.getRedGhost().getY()), new Position(13*GameEngine.TILE_DIMENSION, 13*GameEngine.TILE_DIMENSION));
		assertEquals(new Position(GameEngine.game.getPinkGhost().getX(),GameEngine.game.getPinkGhost().getY()), new Position(13*GameEngine.TILE_DIMENSION, 17*GameEngine.TILE_DIMENSION));
		assertEquals(new Position(GameEngine.game.getOrangeGhost().getX(),GameEngine.game.getOrangeGhost().getY()), new Position(14*GameEngine.TILE_DIMENSION, 15*GameEngine.TILE_DIMENSION));
		assertEquals(new Position(GameEngine.game.getBlueGhost().getX(),GameEngine.game.getBlueGhost().getY()), new Position(13*GameEngine.TILE_DIMENSION, 15*GameEngine.TILE_DIMENSION));
		assertEquals(new Position(GameEngine.game.getPacman().getX(), GameEngine.game.getPacman().getY()), new Position(13*GameEngine.TILE_DIMENSION, 19*GameEngine.TILE_DIMENSION));
		
		GameEngine.game.nextLevel();
		GameEngine.game.initLevel(true);
		assertEquals(GameEngine.game.getLevel(), 3);
		assertEquals(new Position(GameEngine.game.getRedGhost().getX(),GameEngine.game.getRedGhost().getY()), new Position(13*GameEngine.TILE_DIMENSION, 11*GameEngine.TILE_DIMENSION));
		assertEquals(new Position(GameEngine.game.getPinkGhost().getX(),GameEngine.game.getPinkGhost().getY()), new Position(13*GameEngine.TILE_DIMENSION, 15*GameEngine.TILE_DIMENSION));
		assertEquals(new Position(GameEngine.game.getOrangeGhost().getX(),GameEngine.game.getOrangeGhost().getY()), new Position(14*GameEngine.TILE_DIMENSION, 13*GameEngine.TILE_DIMENSION));
		assertEquals(new Position(GameEngine.game.getBlueGhost().getX(),GameEngine.game.getBlueGhost().getY()), new Position(13*GameEngine.TILE_DIMENSION, 13*GameEngine.TILE_DIMENSION));
		assertEquals(new Position(GameEngine.game.getPacman().getX(), GameEngine.game.getPacman().getY()), new Position(13*GameEngine.TILE_DIMENSION, 17*GameEngine.TILE_DIMENSION));
	
		
		GameEngine.game.getPacman().setPosition(new Position(20, 20));
		GameEngine.game.getRedGhost().setPosition(new Position(20, 20));
		GameEngine.game.getPinkGhost().setPosition(new Position(20, 20));
		GameEngine.game.getBlueGhost().setPosition(new Position(20, 20));
		GameEngine.game.getOrangeGhost().setPosition(new Position(20, 28));
		assertTrue(GameEngine.game.checkCharacterColision(true));
		assertFalse(GameEngine.game.getPacman().getAlive());
		
		GameEngine.game.initLevel(false);
		Game.maze.maze.get(1)[1] = 'P';
		GameEngine.game.getPacman().setPosition(new Position(20, 26));
		GameEngine.game.getPacman().setOrientation(0);
		GameEngine.game.getRedGhost().setPosition(new Position(20, 26));
		GameEngine.game.getPinkGhost().setPosition(new Position(20, 26));
		GameEngine.game.getBlueGhost().setPosition(new Position(20, 26));
		GameEngine.game.getOrangeGhost().setPosition(new Position(20, 26));
		GameEngine.game.updateElements(Game.mazeWidth * 20);
		assertFalse(GameEngine.game.getRedGhost().getAlive());
		assertFalse(GameEngine.game.getPinkGhost().getAlive());
		assertFalse(GameEngine.game.getBlueGhost().getAlive());
		assertFalse(GameEngine.game.getOrangeGhost().getAlive());
		
	
		GameEngine.game.generateFruit();
		assertEquals(Game.generatedFruits, 1);
		
		GameEngine.game.initLevel(false);
		Game.collected_pills = Game.maze.getPills()/2;
		GameEngine.game.updateElements(Game.mazeWidth * 20);
		assertFalse(GameEngine.game.getBlueGhost().house);
		assertFalse(GameEngine.game.getOrangeGhost().house);
		
		
		
	}

}
