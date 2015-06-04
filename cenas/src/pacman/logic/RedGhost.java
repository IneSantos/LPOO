package pacman.logic;

import pacman.GUI.GameEngine;

public class RedGhost extends Ghost {

	int animation;

	public RedGhost() {
		super(new Position(33*GameEngine.TILE_DIMENSION,0));
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

	public void switchMode()
	{
		if(mode == Mode.CHASE)
			updateOrientation(Game.pacman.position);
		else if (mode == Mode.SCATTER)
			updateOrientation(this.target);
	}

}
