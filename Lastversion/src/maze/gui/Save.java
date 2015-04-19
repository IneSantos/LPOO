package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Save implements ActionListener {

	String filename;

	JFileChooser fileChooser = new JFileChooser("bin/jogos/save");

	public Save(String filename)
	{
		this.filename = filename;

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{

		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		int returnValue = fileChooser.showSaveDialog(null);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println(selectedFile.getName());
			this.filename = selectedFile.getName();

			//File file = new File(filename + ".ser");
			File file = selectedFile;
			ObjectOutputStream os = null;

			try 
			{
				os = new ObjectOutputStream(new FileOutputStream(file));
				os.writeObject(Application.menu.obj);
			}
			catch (IOException except) { }
			finally { if (os != null)
				try {
					os.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}     }

			System.out.println("saved");
			System.out.println(selectedFile.getName());
			PlayListener.panel.requestFocus();

		}
	}
}

