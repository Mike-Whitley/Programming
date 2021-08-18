package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StoryScreen {
	
	private int numSimulationDays;
	private GameEnvironment Game = GameEnvironment.Game;
	
	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoryScreen window = new StoryScreen();
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
	public StoryScreen() {
		initialize();
	}
	
	public StoryScreen(int numDays) {
		numSimulationDays = numDays;		
		initialize();
	}
	
	//created for testing purposes to avoid slider GUI variable
	public void DaySetter(int numDays) {
		numSimulationDays = numDays;		
	}
	//created to return number of days for GUI testing
	public int DayGetter() {
		return numSimulationDays;	
	}
	
	public int getNumPieces() {
		int numPieces = 2*numSimulationDays/3;
		Game.setNumPieces(numPieces);
		return numPieces;
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
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameEnvironment.openTeamSelection();
				frame.setVisible(false);
			}
		});
		btnContinue.setBackground(Color.GRAY);
		btnContinue.setForeground(Color.WHITE);
		btnContinue.setBounds(210, 340, 240, 120);
		frame.getContentPane().add(btnContinue);
		
		JTextPane txtpnYouAndYour = new JTextPane();
		txtpnYouAndYour.setForeground(Color.WHITE);
		txtpnYouAndYour.setBackground(Color.BLACK);
		txtpnYouAndYour.setFont(new Font("Courier 10 Pitch", Font.PLAIN, 16));
		txtpnYouAndYour.setText("You and your crew were travelling through space when your warp drive was hit by a stray asteroid. The radioactive material inside is deteriorating but your scanners indicate that you can find the required parts to rebuild the warp drive in a nearby galaxy. You will need to find "+this.getNumPieces()+" warp drive fragments in order to repair it and get home but you only have "+numSimulationDays+" days before the warp drive deteriorates beyond repair and leaves you stranded.");
		txtpnYouAndYour.setBounds(40, 40, 560, 200);
		frame.getContentPane().add(txtpnYouAndYour);
	}
}
