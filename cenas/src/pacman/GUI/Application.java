package pacman.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFrame;

import pacman.menus.MainMenu;
import resources.MyCompare;
import resources.images.Images;
import resources.sounds.Sounds;

public class Application
{
	public static Dimension screenSize;
	public static JFrame frame;
	public static Images images = new Images();
	public static Sounds sounds = new Sounds();
	public static ArrayList<Integer> scores = new ArrayList<Integer>();

	public static void main(String[] args) 
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame = new JFrame("PacMano, m'putz");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setResizable(false);

		new Application();

		new MainMenu();
		return;
	}

	public Application()
	{
		loadScores();
	}

	public void loadScores()
	{
		Scanner scan;
		scan = new Scanner(getClass().getResourceAsStream("/resources/scores.txt"));

		while (scan.hasNextLine())
			scores.add(scan.nextInt());

		scan.close();

		scores.sort(new MyCompare());

		for(int i = 0; i < scores.size(); i++)
			System.out.println(scores.get(i));

	}




	public static void setNewScore(Integer score)
	{
		scores.add(score);
		Collections.sort(scores, new MyCompare());
	}


}


