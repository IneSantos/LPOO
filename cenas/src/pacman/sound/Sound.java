package pacman.sound;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;

public class Sound {

	public static Mixer mixer;
	public static Clip clip;
	
	public Sound ()
	{
		mixer = AudioSystem.getMixer(AudioSystem.getMixerInfo()[0]);
		
		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
		try{ clip = (Clip)mixer.getLine(dataInfo); } 
		catch(LineUnavailableException lue) { lue.printStackTrace(); }

		try
		{
			URL soundURL = Sound.class.getResource("eminem.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
			clip.open(audioStream);
		}
		catch(LineUnavailableException lue) { lue.printStackTrace(); }
		catch(UnsupportedAudioFileException a) { a.printStackTrace(); }
		catch(IOException e) { e.printStackTrace(); }
	}
	
}
