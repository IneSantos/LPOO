package pacman.logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Pacman extends Character {
	
	int score;
	int animation;
	int power;
	int lifes;

	public Pacman()
	{
		super();
		score = 0;
		animation = 0;
		power = 0;
		lifes = 3;
	}
	
	public int updateAnimation()
	{
		animation++;
		
		if (animation > 3)
			animation = 0;
		
		return animation;
	}
	
	
	
	

}
