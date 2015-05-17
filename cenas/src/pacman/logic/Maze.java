package pacman.logic;

public class Maze {

	public char maze[][] =  {
		{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , 'X' , 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
		{ 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' , '.' , 'X', 'X', '.' , '.' , '.' , '.' , '.', '.', '.', '.', '.', '.', '.', '.', 'X'},
		{ 'X', '.', 'X', 'X', 'X', 'X', '.', 'X', 'X', 'X', 'X', 'X' , '.' , 'X', 'X', '.' , 'X' , 'X' , 'X' , 'X', 'X', '.', 'X', 'X', 'X', 'X', '.', 'X'},
		{ 'X', 'P', 'X', ' ', ' ', 'X', '.', 'X', ' ', ' ', ' ', 'X' , '.' , 'X', 'X', '.' , 'X' , ' ' , ' ' , ' ', 'X', '.', 'X', ' ', ' ', 'X', 'P', 'X'},
		{ 'X', '.', 'X', 'X', 'X', 'X', '.', 'X', 'X', 'X', 'X', 'X' , '.' , 'X', 'X', '.' , 'X' , 'X' , 'X' , 'X', 'X', '.', 'X', 'X', 'X', 'X', '.', 'X'},
		{ 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' , '.' , '.', '.', '.' , '.' , '.' , '.' , '.', '.', '.', '.', '.', '.', '.', '.', 'X'},
		{ 'X', '.', 'X', 'X', 'X', 'X', '.', 'X', 'X', '.', 'X', 'X' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , '.' , 'X', 'X', '.', 'X', 'X', 'X', 'X', '.', 'X'},
		{ 'X', '.', 'X', 'X', 'X', 'X', '.', 'X', 'X', '.', 'X', 'X' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , '.' , 'X', 'X', '.', 'X', 'X', 'X', 'X', '.', 'X'},
		{ 'X', '.', '.', '.', '.', '.', '.', 'X', 'X', '.', '.', '.' , '.' , 'X', 'X', '.' , '.' , '.' , '.' , 'X', 'X', '.', '.', '.', '.', '.', '.', 'X'},
		{ 'X', 'X', 'X', 'X', 'X', 'X', '.', 'X', 'X', 'X', 'X', 'X' , ' ' , 'X', 'X', ' ' , 'X' , 'X' , 'X' , 'X', 'X', '.', 'X', 'X', 'X', 'X', 'X', 'X'},
		{ ' ', ' ', ' ', ' ', ' ', 'X', '.', 'X', 'X', 'X', 'X', 'X' , ' ' , 'X', 'X', ' ' , 'X' , 'X' , 'X' , 'X', 'X', '.', 'X', ' ', ' ', ' ', ' ', ' '},
		{ ' ', ' ', ' ', ' ', ' ', 'X', '.', 'X', 'X', ' ', ' ', ' ' , ' ' , ' ', ' ', ' ' , ' ' , ' ' , ' ' , 'X', 'X', '.', 'X', ' ', ' ', ' ', ' ', ' '},
		{ ' ', ' ', ' ', ' ', ' ', 'X', '.', 'X', 'X', ' ', 'X', 'X' , 'X' , 'S', 'S', 'X' , 'X' , 'X' , ' ' , 'X', 'X', '.', 'X', ' ', ' ', ' ', ' ', ' '},
		{ 'X', 'X', 'X', 'X', 'X', 'X', '.', 'X', 'X', ' ', 'X', ' ' , ' ' , ' ', ' ', ' ' , ' ' , 'X' , ' ' , 'X', 'X', '.', 'X', 'X', 'X', 'X', 'X', 'X'},
		{ ' ', ' ', ' ', ' ', ' ', ' ', '.', ' ', ' ', ' ', 'X', ' ' , ' ' , ' ', ' ', ' ' , ' ' , 'X' , ' ' , ' ', ' ', '.', ' ', ' ', ' ', ' ', ' ', ' '},
		{ 'X', 'X', 'X', 'X', 'X', 'X', '.', 'X', 'X', ' ', 'X', ' ' , ' ' , ' ', ' ', ' ' , ' ' , 'X' , ' ' , 'X', 'X', '.', 'X', 'X', 'X', 'X', 'X', 'X'},
		{ ' ', ' ', ' ', ' ', ' ', 'X', '.', 'X', 'X', ' ', 'X', 'X' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , ' ' , 'X', 'X', '.', 'X', ' ', ' ', ' ', ' ', ' '},
		{ ' ', ' ', ' ', ' ', ' ', 'X', '.', 'X', 'X', ' ', ' ', ' ' , ' ' , ' ', ' ', ' ' , ' ' , ' ' , ' ' , 'X', 'X', '.', 'X', ' ', ' ', ' ', ' ', ' '},
		{ ' ', ' ', ' ', ' ', ' ', 'X', '.', 'X', 'X', ' ', 'X', 'X' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , ' ' , 'X', 'X', '.', 'X', ' ', ' ', ' ', ' ', ' '},
		{ 'X', 'X', 'X', 'X', 'X', 'X', '.', 'X', 'X', ' ', 'X', 'X' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , ' ' , 'X', 'X', '.', 'X', 'X', 'X', 'X', 'X', 'X'},
		{ 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' , '.' , 'X', 'X', '.' , '.' , '.' , '.' , '.', '.', '.', '.', '.', '.', '.', '.', 'X'},
		{ 'X', '.', 'X', 'X', 'X', 'X', '.', 'X', 'X', 'X', 'X', 'X' , '.' , 'X', 'X', '.' , 'X' , 'X' , 'X' , 'X', 'X', '.', 'X', 'X', 'X', 'X', '.', 'X'},
		{ 'X', '.', 'X', 'X', 'X', 'X', '.', 'X', 'X', 'X', 'X', 'X' , '.' , 'X', 'X', '.' , 'X' , 'X' , 'X' , 'X', 'X', '.', 'X', 'X', 'X', 'X', '.', 'X'},
		{ 'X', 'P', '.', '.', 'X', 'X', '.', '.', '.', '.', '.', '.' , '.' , '.', '.', '.' , '.' , '.' , '.' , '.', '.', '.', 'X', 'X', '.', '.', 'P', 'X'},
		{ 'X', 'X', 'X', '.', 'X', 'X', '.', 'X', 'X', '.', 'X', 'X' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , '.' , 'X', 'X', '.', 'X', 'X', '.', 'X', 'X', 'X'},
		{ 'X', 'X', 'X', '.', 'X', 'X', '.', 'X', 'X', '.', 'X', 'X' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , '.' , 'X', 'X', '.', 'X', 'X', '.', 'X', 'X', 'X'},
		{ 'X', '.', '.', '.', '.', '.', '.', 'X', 'X', '.', '.', '.' , '.' , 'X', 'X', '.' , '.' , '.' , '.' , 'X', 'X', '.', '.', '.', '.', '.', '.', 'X'},
		{ 'X', '.', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' , '.' , 'X', 'X', '.' , 'X' , 'X' , 'X' , 'X', 'X', 'X', 'X', 'X', 'X', 'X', '.', 'X'},
		{ 'X', '.', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' , '.' , 'X', 'X', '.' , 'X' , 'X' , 'X' , 'X', 'X', 'X', 'X', 'X', 'X', 'X', '.', 'X'},
		{ 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' , '.' , '.', '.', '.' , '.' , '.' , '.' , '.', '.', '.', '.', '.', '.', '.', '.', 'X'},
		{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' , 'X' , 'X', 'X', 'X' , 'X' , 'X' , 'X' , 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}};

	
	public boolean isWall(Position p)
	{
		if(maze[p.y][p.x] == 'X')
			return true;
		return false;
	}
	
	public boolean isPoint(Position p)
	{
		if(maze[p.y][p.x] == '.')
			return true;
		return false;
	}
	
	public boolean isPowerPoint(Position p)
	{
		if(maze[p.y][p.x] == 'P')
			return true;
		return false;
	}
	
	public boolean isDoor(Position p)
	{
		if(maze[p.y][p.x] == 'S')
			return true;
		return false;
	}
	
}
