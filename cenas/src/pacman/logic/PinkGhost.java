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
		
		public void switchMode()
		{
			Position new_target = this.target;
			
			if(this.orientation == 0)
				new_target = new Position(Game.pacman.position.x, Game.pacman.position.y - 4*GameEngine.TILE_DIMENSION);
			else if (this.orientation == 1)
				new_target = new Position(Game.pacman.position.x + 4*GameEngine.TILE_DIMENSION, Game.pacman.position.y);
			else if (this.orientation == 2)
				new_target = new Position(Game.pacman.position.x, Game.pacman.position.y + 4*GameEngine.TILE_DIMENSION);
			else new_target = new Position(Game.pacman.position.x + 4*GameEngine.TILE_DIMENSION, Game.pacman.position.y);

			
			if(mode == Mode.CHASE)
				updateOrientation(new_target);
			else if (mode == Mode.SCATTER)
				updateOrientation(this.target);
		}
		
	}

