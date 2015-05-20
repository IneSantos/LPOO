package pacman.menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainMenu extends JPanel {
  
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	public JPanel panel = new JPanel();
	public JButton NewGame = new JButton();
	public JButton settings = new JButton();
	public JButton scores = new JButton();
	public JButton android = new JButton();
	public JButton exit = new JButton();

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	*/
	public MainMenu() {
		initialize();
	}

	
	private void initialize() {

		
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 481, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBackground(Color.BLACK);

		panel.setBounds(0, 0, 475, 384);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		NewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		NewGame.setText("New Game");
		NewGame.setForeground(Color.BLACK);
		NewGame.setVerticalTextPosition(SwingConstants.CENTER);
		NewGame.setHorizontalTextPosition(SwingConstants.CENTER);
		NewGame.setBounds(168, 137, 104, 31);
		panel.add(NewGame);
		
		settings.setText("Settings");
		settings.setBounds(168, 179, 104, 31);
		panel.add(settings);
		
		//settings.addActionListener(new SettingsListener());
		
		scores.setText("Scores");
		scores.setBounds(168, 221, 104, 31);
		panel.add(scores);

		android.setText("Android");
		android.setBounds(168, 263, 104, 31);
		panel.add(android);
		
		exit.setText("Exit");
		exit.setBounds(168, 305, 104, 31);
		panel.add(exit);
		exit.addActionListener(new ExitListener());
		
		
		
				
		
		
	}
}
