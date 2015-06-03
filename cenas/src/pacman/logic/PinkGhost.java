package pacman.logic;

import pacman.GUI.GameEngine;

	public class PinkGhost extends Ghost {
		
		int animation;

		public PinkGhost() {
			super(new Position(0,33));
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

