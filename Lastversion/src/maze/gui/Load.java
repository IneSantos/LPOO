package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import maze.logic.Jogo;

public class Load implements ActionListener {

	String filename;
	JFileChooser fileChooser = new JFileChooser("bin/jogos/save");
	
	public Load(String filename)
	{
		this.filename = filename;    
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{

		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		int returnValue = fileChooser.showOpenDialog(null);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println(selectedFile.getName());
			this.filename = selectedFile.getName();

			
		//File file = new File(filename + ".ser"); 
			File file = selectedFile;
		 ObjectInputStream in = null;

		    try {
		    	in = new ObjectInputStream(new FileInputStream(file));
		    	Application.menu.obj = (Jogo) in.readObject();
		    	in.close();
		    	
		    }
		    catch (IOException except) {
		    	except.printStackTrace();
		         return;
		      }catch(ClassNotFoundException c)
		      {
		        c.printStackTrace();
		      }
		    
			System.out.println("loaded");

			System.out.println(selectedFile.getName());
		PlayListener.panel.requestFocus();
		
	}
	}
	
}
