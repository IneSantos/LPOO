package pacman.GUI;

import java.util.Timer;
import java.util.TimerTask;

import pacman.logic.Ghost.Mode;

@SuppressWarnings("serial")
public class GameTimer extends GameEngine
{
	Timer timer = new Timer();
	public GameTimer(){
	 timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
			game.getRedGhost().mode = Mode.SCATTER;
		    game.getOrangeGhost().mode = Mode.SCATTER;
		    game.getPinkGhost().mode = Mode.SCATTER;
		    game.getBlueGhost().mode = Mode.SCATTER;
		  }
		}, 7*1000, 7*1000);
	
	}
	
	
}
