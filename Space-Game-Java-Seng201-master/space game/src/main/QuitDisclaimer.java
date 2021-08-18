package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuitDisclaimer {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuitDisclaimer window = new QuitDisclaimer();
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
	public QuitDisclaimer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblQuitNowAnd = new JLabel("Quit now and erase all progress?");
		lblQuitNowAnd.setForeground(Color.WHITE);
		lblQuitNowAnd.setFont(new Font("Courier 10 Pitch", Font.BOLD, 20));
		lblQuitNowAnd.setBounds(30, 60, 400, 30);
		frame.getContentPane().add(lblQuitNowAnd);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuit.setForeground(Color.WHITE);
		btnQuit.setBackground(Color.GRAY);
		btnQuit.setBounds(20, 160, 180, 60);
		frame.getContentPane().add(btnQuit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameEnvironment.openMainScreen();
				frame.setVisible(false);
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(Color.GRAY);
		btnCancel.setBounds(240, 160, 180, 60);
		frame.getContentPane().add(btnCancel);
	}

}
