package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class NewGameScreen {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGameScreen window = new NewGameScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewGameScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 660, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameEnvironment.openDaySelect();
				frame.setVisible(false);
			}
		});
		
		btnNewGame.setForeground(Color.WHITE);
		btnNewGame.setBackground(Color.GRAY);
		btnNewGame.setToolTipText("Click to begin a new game.");
		btnNewGame.setBounds(98, 200, 180, 100);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuit.setToolTipText("Click to exit the game.");
		btnQuit.setForeground(Color.WHITE);
		btnQuit.setBackground(Color.GRAY);
		btnQuit.setBounds(376, 200, 180, 100);
		frame.getContentPane().add(btnQuit);
	}
}
