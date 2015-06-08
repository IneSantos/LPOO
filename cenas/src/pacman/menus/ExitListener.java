package pacman.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import pacman.GUI.Application;

public class ExitListener implements ActionListener {

	public ExitListener()
	{
		try {
			saveScores();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
	}


	public static void saveScores() throws IOException
	{
		File outFile = new File ("src/resources/scores.txt");
		FileWriter fWriter = new FileWriter (outFile);
		PrintWriter pWriter = new PrintWriter (fWriter); 

		for(int i = 0; i < 5; i++)
			pWriter.println(Application.scores.get(i));
			
		
		pWriter.close();
	}



}
