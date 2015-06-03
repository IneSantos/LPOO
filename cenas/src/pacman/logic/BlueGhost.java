package pacman.logic;

import pacman.GUI.GameEngine;

	public class BlueGhost extends Ghost {
		
		int animation;

		public BlueGhost() {
			super(new Position(28*GameEngine.TILE_DIMENSION,36*GameEngine.TILE_DIMENSION));
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

