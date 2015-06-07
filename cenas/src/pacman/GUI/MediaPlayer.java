package pacman.GUI;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;

public class MediaPlayer {

	public Mixer mixer;
	public Clip clip;
	private URL sound;
	
	public MediaPlayer (URL soundURL)
	{
		this.sound = soundURL;
		
		mixer = AudioSystem.getMixer(AudioSystem.getMixerInfo()[0]);
		
		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
		try{ clip = (Clip)mixer.getLine(dataInfo); } 
		catch(LineUnavailableException lue) { lue.printStackTrace(); }

	}
	
	public void open()
	{
		try
		{
			AudioInputStream audio = AudioSystem.getAudioInputStream(sound);
			clip.open(audio);
		}
		catch(LineUnavailableException lue) { lue.printStackTrace(); } 
		catch (IOException e) {e.printStackTrace();} 
		catch (UnsupportedAudioFileException e) {e.printStackTrace();}
		
	}
	
}
