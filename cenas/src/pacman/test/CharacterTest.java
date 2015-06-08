package pacman.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pacman.logic.*;
import pacman.logic.Character;

public class CharacterTest {

	@Test
	public void testCharacter() {
		
		Character character = new Character();
		
		assertTrue(character.getAlive());
		assertTrue(character.getOrientation() == 2);
		assertEquals(character.getX(), 100);
		assertEquals(character.getY(), 100);
	}

	@Test
	public void setTest()
	{
		Character character = new Character();
		
		character.setOrientation(0);
		assertEquals(character.getOrientation(), 0);
		
		character.setPosition(new Position(0,0));
		assertEquals(new Position(character.getX(), character.getY()), new Position (0,0));
	}
	
	@Test
	public void moveTest()
	{
		Game game = new Game();
		game.initLevel(true);
		
		Character character = new Character();
		character.setPosition(new Position(20, 20));
		
		assertFalse(character.moveUp()); 
		assertTrue(character.moveDown());
		character.setPosition(new Position(20, 40));
		assertFalse(character.moveRight());
		
		character.setPosition(new Position(20, 22));
		assertTrue(character.moveUp());
		
		assertFalse(character.moveLeft());
		assertTrue(character.moveRight());
		
		character.setPosition(new Position(40, 20));
		assertFalse(character.moveDown());
		assertTrue(character.moveLeft());
	}
}
