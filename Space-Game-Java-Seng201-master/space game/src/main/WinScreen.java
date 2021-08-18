package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinScreen {
	
	public JFrame frame;
	private Ship playerShip = GameEnvironment.getPlayerShip();
	private ArrayList<CrewMember> crewMembers = playerShip.getCrewMembers();
	private GameEnvironment Game = GameEnvironment.Game;
	
	private int finalScore = playerShip.playerCredits*10+Game.getNumPieces()*100;
	private int crewSize = crewMembers.size();
	private int crewCount;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinScreen window = new WinScreen();
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
	public WinScreen() {
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
		
		JLabel lblYouWin = new JLabel("You Win!");
		lblYouWin.setForeground(Color.WHITE);
		lblYouWin.setFont(new Font("Dialog", Font.BOLD, 24));
		lblYouWin.setBounds(20, 20, 140, 50);
		frame.getContentPane().add(lblYouWin);
		
		JLabel label = new JLabel("Your Score: "+finalScore);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setBounds(400, 20, 200, 50);
		frame.getContentPane().add(label);
		
		
		for (int i = 0; i < crewSize; i++) {
			if (crewMembers.get(i).isAlive == true) {
				crewCount++;
			}
		}
		
		JLabel label_2 = new JLabel("Crew Remaining: "+crewCount);
		label_2.setVerticalAlignment(SwingConstants.TOP);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Dialog", Font.BOLD, 16));
		label_2.setBounds(400, 120, 220, 240);
		frame.getContentPane().add(label_2);
		
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
		
		JTextPane txtpnCongratulationsYouFound = new JTextPane();
		txtpnCongratulationsYouFound.setText("Congratulations! You found all warp drive pieces and successfully made your way home.");
		txtpnCongratulationsYouFound.setForeground(Color.WHITE);
		txtpnCongratulationsYouFound.setFont(new Font("Dialog", Font.BOLD, 16));
		txtpnCongratulationsYouFound.setBackground(Color.BLACK);
		txtpnCongratulationsYouFound.setBounds(10, 185, 360, 260);
		frame.getContentPane().add(txtpnCongratulationsYouFound);
	}

}
