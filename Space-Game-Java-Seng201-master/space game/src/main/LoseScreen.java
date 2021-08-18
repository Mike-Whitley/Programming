package main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class LoseScreen {

	public JFrame frame;
	private String reason;
	private Ship playerShip = GameEnvironment.getPlayerShip();
	private ArrayList<CrewMember> crewMembers = playerShip.getCrewMembers();
	
	private int finalScore = playerShip.playerCredits*5;
	private int crewSize = crewMembers.size();
	private int crewCount = 0;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoseScreen window = new LoseScreen();
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
	public LoseScreen() {
		initialize();
	}
	
	public LoseScreen(String tempReason) {
		reason = tempReason;
		initialize();
	}
	
	/**
	 * 
	 * @return returns the String of how the player has died  based on the global reason variable 
	 * the options player lost are, Hunger, Time, Dysentery, Plague, Asteroids, Pirates, Explosion, alien
	 */
	public String getText() {
		if (reason == "Hunger") {
			return ("Your crew died of starvation.");
		} else if (reason == "Time") {
			return ("Your crew ran out of time and the warp drive is now damaged beyond repair.");
		} else if (reason == "Bioweapon") {
			return ("Your entire crew was wiped out by an alien bioweapon.");
		} else if (reason == "Plague") {
			return ("Your crew died of the Space Plague.");
		} else if (reason == "Asteroids") {
			return ("Your ship was destroyed by asteroids.");
		} else if (reason == "Pirates") {
			return ("Your crew was killed by space pirates.");
		} else if (reason == "Explosion") {
			return ("Your ship was destroyed by a nearby explosion.");
		} else if (reason == "Dormant") {
			return ("Your crew was killed by an angrily awakened alien.");
		} else {
			return "";
		}
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
		
		JButton button = new JButton("Quit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setToolTipText("Click to exit the game.");
		button.setForeground(Color.WHITE);
		button.setBackground(Color.GRAY);
		button.setBounds(450, 390, 180, 80);
		frame.getContentPane().add(button);
		
		JLabel lblYouLose = new JLabel("You Lose!");
		lblYouLose.setForeground(Color.WHITE);
		lblYouLose.setFont(new Font("Dialog", Font.BOLD, 24));
		lblYouLose.setBounds(20, 20, 140, 50);
		frame.getContentPane().add(lblYouLose);
		
		JLabel lblYourScore = new JLabel("Your Score: "+finalScore);
		lblYourScore.setForeground(Color.WHITE);
		lblYourScore.setFont(new Font("Dialog", Font.BOLD, 20));
		lblYourScore.setBounds(400, 20, 200, 50);
		frame.getContentPane().add(lblYourScore);
		
		JTextPane txtpnLose = new JTextPane();
		txtpnLose.setForeground(Color.WHITE);
		txtpnLose.setBackground(Color.BLACK);
		txtpnLose.setFont(new Font("Dialog", Font.BOLD, 16));
		txtpnLose.setBounds(10, 185, 360, 260);
		frame.getContentPane().add(txtpnLose);
		
		txtpnLose.setText(this.getText());
		
		for (int i = 0; i < crewSize; i++) {
			if (crewMembers.get(i).isAlive == true) {
				crewCount++;
			}
		}
		
		JLabel lblCrewRemaining = new JLabel("Crew Remaining: "+crewCount);
		lblCrewRemaining.setVerticalAlignment(SwingConstants.TOP);
		lblCrewRemaining.setForeground(Color.WHITE);
		lblCrewRemaining.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCrewRemaining.setBounds(400, 120, 220, 240);
		frame.getContentPane().add(lblCrewRemaining);
		
		
	}
}
