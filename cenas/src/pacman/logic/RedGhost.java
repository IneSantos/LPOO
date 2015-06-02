package pacman.logic;

import pacman.GUI.GameEngine;

public class RedGhost extends Ghost {

	int animation;

	public RedGhost() {
		super(new Position(0*GameEngine.TILE_DIMENSION,0));
		animation = 0;
	}

	public int updateAnimation()
	{
		animation++;

		if (animation > 1)
			animation = 0;

		return animation;
	}

	public int getAnimation()
	{
		return animation;	
	}


}
