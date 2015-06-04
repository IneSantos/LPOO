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
		
		public void switchMode()
		{
			Position target = Game.pacman.position;
			
			if(calculateDistance(position.x, position.y, target)%GameEngine.TILE_DIMENSION >= 8)
				target = this.target;
				
			if(mode == Mode.CHASE)
				updateOrientation(Game.pacman.position);
			else if (mode == Mode.SCATTER)
				updateOrientation(this.target);
		}
		
	}

