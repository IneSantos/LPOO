package resources.sounds;

import java.net.URL;

import pacman.GUI.MediaPlayer;

public class Sounds {
	
	public URL chomp;
	public URL beginning;
	public URL death;
	public URL power;
	public URL intermission;
	public URL ghost;
	public URL fruit;
	
	public Sounds()
	{
		System.out.println("Sounds");
		
		beginning = MediaPlayer.class.getResource("/resources/sounds/beginning.wav");
		
		chomp = MediaPlayer.class.getResource("/resources/sounds/chomp.wav");

		death = MediaPlayer.class.getResource("/resources/sounds/death.wav");

		power = MediaPlayer.class.getResource("/resources/sounds/power.wav");

		intermission = MediaPlayer.class.getResource("/resources/sounds/intermission.wav");

		ghost = MediaPlayer.class.getResource("/resources/sounds/ghost.wav");
		
		fruit = MediaPlayer.class.getResource("/resources/sounds/fruit.wav");
	}
}
