package pacman.logic;

import pacman.GUI.GameEngine;

	public class OrangeGhost extends Ghost {
		
		int animation;

		public OrangeGhost() {
			super(new Position(0,36*GameEngine.TILE_DIMENSION));
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

