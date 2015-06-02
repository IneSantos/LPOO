package pacman.logic;

public class RedGhost extends Ghost {

	int animation;

	public RedGhost() {
		super(new Position(33*20,0));
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
