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
		
		public void switchMode()
		{
			Position new_target =  new Position(Game.redGhost.position.x + 2*(Game.pacman.position.x - Game.redGhost.position.x), Game.redGhost.position.y + 2*(Game.pacman.position.y - Game.redGhost.position.y));
			
			if(new_target.x < 0)
				new_target.x = 0;
			else if (new_target.x > Game.mazeWidth * GameEngine.TILE_DIMENSION)
				new_target.x = Game.mazeWidth * GameEngine.TILE_DIMENSION;
			
			if(new_target.y < 0)
				new_target.y = 0;
			else if (new_target.y > Game.mazeWidth * GameEngine.TILE_DIMENSION)
				new_target.y = Game.mazeWidth * GameEngine.TILE_DIMENSION;
			
			if(mode == Mode.CHASE)
				updateOrientation(Game.pacman.position);
			else if (mode == Mode.SCATTER)
				updateOrientation(this.target);
		}
		
	}

