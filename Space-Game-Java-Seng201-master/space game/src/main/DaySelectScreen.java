/**
 * This Class contains a Sliders which the player is able to choose the number of days the want to player for.
 */
package main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DaySelectScreen {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DaySelectScreen window = new DaySelectScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * 
	 */
	public DaySelectScreen() {
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
		
		JLabel lblNumberOfDays = new JLabel("Number of Days");
		lblNumberOfDays.setForeground(Color.WHITE);
		lblNumberOfDays.setFont(new Font("Courier 10 Pitch", Font.PLAIN, 16));
		lblNumberOfDays.setBounds(40, 140, 140, 30);
		frame.getContentPane().add(lblNumberOfDays);
		
		final JSlider slider = new JSlider();
		slider.setForeground(Color.WHITE);
		slider.setBounds(180, 140, 420, 50);
		slider.setMinorTickSpacing(1);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBackground(Color.BLACK);
		slider.setMajorTickSpacing(1);
		slider.setMaximum(10);
		slider.setMinimum(3);
		frame.getContentPane().add(slider);
		
		JLabel lblGameLength = new JLabel("Game Length");
		lblGameLength.setBackground(Color.BLACK);
		lblGameLength.setForeground(Color.WHITE);
		lblGameLength.setFont(new Font("Courier 10 Pitch", Font.BOLD, 24));
		lblGameLength.setBounds(240, 20, 180, 40);
		frame.getContentPane().add(lblGameLength);
		
		
		
	/**
	 * ButtonContinue takes the current slider number of Days and passes it to a variable to store
	 * opens the story screen then closes this screen.
	 */
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameEnvironment.setNumSimulationDays(slider.getValue());
				GameEnvironment.openStoryScreen();
				frame.setVisible(false);
			}
		});
		btnContinue.setForeground(Color.WHITE);
		btnContinue.setBackground(Color.GRAY);
		btnContinue.setBounds(240, 260, 200, 60);
		frame.getContentPane().add(btnContinue);

	}
}
