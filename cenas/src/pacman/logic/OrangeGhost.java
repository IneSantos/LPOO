package pacman.logic;

import pacman.GUI.GameEngine;
import pacman.logic.Game.Mode;

public class OrangeGhost extends Ghost {

	int animation;

	public OrangeGhost() {
		super(new Position(0,36*GameEngine.TILE_DIMENSION));
		animation = 0;
		house = true;
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
		if(alive)
		{
			if(Game.ghostMode == Mode.CHASE)
			{
				Position target = Game.pacman.position;

				if(calculateDistance(position.x, position.y, target)%GameEngine.TILE_DIMENSION >= 8)
					target = this.target;

				updateOrientation(target);
			}
			else if (Game.ghostMode == Mode.SCATTER)
				updateOrientation(this.target);
			else if(Game.ghostMode == Mode.FRIGHTENED)
				updateOrientation(null);
		}
		else updateOrientation(new Position(12*GameEngine.TILE_DIMENSION, 15*GameEngine.TILE_DIMENSION));
	}

}

