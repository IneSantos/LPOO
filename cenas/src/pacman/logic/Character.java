package pacman.logic;

public class Character {
	
	Position position;
	int velocity;
	int orientation;
	boolean alive;
	
	public Character()
	{
		position = new Position(100, 100);
		velocity = 7;
		orientation = 0;
		alive = true;
	}
	

}
