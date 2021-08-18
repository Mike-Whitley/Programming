package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class NextPlanetScreen {
	
	public JFrame frame;
	private GameEnvironment Game = GameEnvironment.Game;
	private Ship playerShip = GameEnvironment.getPlayerShip();
	private ArrayList<CrewMember> crewMembers = playerShip.getCrewMembers();
	private int crewLength = crewMembers.size();
	private int chosePilot = 0;
	private int pilot = 0;
	private int copilot = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NextPlanetScreen window = new NextPlanetScreen();
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
	public NextPlanetScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 660, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JLabel lblChoosePilots = new JLabel("Ship Pilot:");
		lblChoosePilots.setForeground(Color.WHITE);
		lblChoosePilots.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		lblChoosePilots.setBounds(18, 70, 300, 20);
		frame.getContentPane().add(lblChoosePilots);
		
		final JLabel lblShipCopilot = new JLabel("Ship Co-Pilot:");
		lblShipCopilot.setForeground(Color.WHITE);
		lblShipCopilot.setFont(new Font("Dialog", Font.BOLD, 18));
		lblShipCopilot.setBounds(336, 70, 300, 20);
		frame.getContentPane().add(lblShipCopilot);
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(120, 70, 170, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel(" ");
		label.setForeground(Color.WHITE);
		label.setBounds(450, 70, 170, 20);
		frame.getContentPane().add(label);
		
		JLabel lblChooseYourPilots = new JLabel("Choose Your Pilots");
		lblChooseYourPilots.setForeground(Color.WHITE);
		lblChooseYourPilots.setFont(new Font("Dialog", Font.BOLD, 22));
		lblChooseYourPilots.setBounds(10, 10, 200, 30);
		frame.getContentPane().add(lblChooseYourPilots);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chosePilot == 2) {
					if (crewMembers.get(pilot).actionsRemaining > 0 && crewMembers.get(copilot).actionsRemaining > 0) {
						Game.pieceFound = 0;
					crewMembers.get(pilot).useAction();
					crewMembers.get(copilot).useAction();
					frame.setVisible(false);
					
					} else {
						JOptionPane.showMessageDialog(null, "Both pilots need to have action remaining.", "Game Message: " + "No actions", JOptionPane.INFORMATION_MESSAGE);
						frame.setVisible(false);
						GameEnvironment.openNextPlanetScreen();
					}
				} else if (chosePilot == 1){
					JOptionPane.showMessageDialog(null, "Please select a CoPilot.", "Game Message: " + "No pilots", JOptionPane.INFORMATION_MESSAGE);					
				} else {
					JOptionPane.showMessageDialog(null, "Please select two Pilots for the ship.", "Game Message: " + "No pilots", JOptionPane.INFORMATION_MESSAGE);					
				}
			}
		});
		btnContinue.setForeground(Color.WHITE);
		btnContinue.setBackground(Color.GRAY);
		btnContinue.setBounds(490, 430, 140, 50);
		frame.getContentPane().add(btnContinue);
		
		final JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pilot Selection", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setBounds(169, 129, 272, 320);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnCrew = new JButton("Crew1");
		btnCrew.setBounds(14, 73, 120, 40);
		panel.add(btnCrew);
		btnCrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chosePilot == 0) {
					lblChoosePilots.setText("Ship Pilot:"+crewMembers.get(0).name);
					pilot = 0;
					chosePilot++;
				} else if (chosePilot == 1 && pilot != 0){
					lblShipCopilot.setText("Ship Co-Pilot:"+crewMembers.get(0).name);
					copilot = 0;
					chosePilot++;
					panel.setVisible(false);
				}
			}
		});
		btnCrew.setBackground(Color.GRAY);
		btnCrew.setForeground(Color.WHITE);
		
		JButton btnCrew_1 = new JButton("Crew2");
		btnCrew_1.setBounds(14, 134, 120, 40);
		panel.add(btnCrew_1);
		btnCrew_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chosePilot == 0) {
					lblChoosePilots.setText("Ship Pilot:"+crewMembers.get(1).name);
					pilot = 1;
					chosePilot++;
				} else if (chosePilot == 1 && pilot != 1){
					lblShipCopilot.setText("Ship Co-Pilot:"+crewMembers.get(1).name);
					copilot = 1;
					chosePilot++;
					panel.setVisible(false);
				}
			}
		});
		btnCrew_1.setBackground(Color.GRAY);
		btnCrew_1.setForeground(Color.WHITE);
		
		JButton btnCrew_2 = new JButton("Crew3");
		btnCrew_2.setBounds(14, 195, 120, 40);
		panel.add(btnCrew_2);
		btnCrew_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chosePilot == 0) {
					lblChoosePilots.setText("Ship Pilot:"+crewMembers.get(2).name);
					pilot = 2;
					chosePilot++;
				} else if (chosePilot == 1 && pilot != 2){
					lblShipCopilot.setText("Ship Co-Pilot:"+crewMembers.get(2).name);
					copilot = 2;
					chosePilot++;
					panel.setVisible(false);
				}
			}
		});
		btnCrew_2.setForeground(Color.WHITE);
		btnCrew_2.setBackground(Color.GRAY);
		
		JButton btnCrew_3 = new JButton("Crew4");
		btnCrew_3.setBounds(14, 256, 120, 40);
		panel.add(btnCrew_3);
		btnCrew_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chosePilot == 0) {
					lblChoosePilots.setText("Ship Pilot:"+crewMembers.get(3).name);
					pilot = 3;
					chosePilot++;
				} else if (chosePilot == 1 && pilot != 3){
					lblShipCopilot.setText("Ship Co-Pilot:"+crewMembers.get(3).name);
					copilot = 3;
					chosePilot++;
					panel.setVisible(false);
				}
			}
		});
		btnCrew_3.setForeground(Color.WHITE);
		btnCrew_3.setBackground(Color.GRAY);
		
		JLabel lblChooseYourShip = new JLabel("Choose Your Ship Pilot:");
		lblChooseYourShip.setBounds(14, 21, 244, 29);
		panel.add(lblChooseYourShip);
		lblChooseYourShip.setForeground(Color.WHITE);
		lblChooseYourShip.setFont(new Font("Dialog", Font.BOLD, 22));
		
		JLabel label_1 = new JLabel("Actions: "+crewMembers.get(0).actionsRemaining);
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(138, 86, 120, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Actions: "+crewMembers.get(1).actionsRemaining);
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(138, 147, 120, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(138, 208, 120, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setForeground(Color.WHITE);
		label_4.setBounds(138, 269, 120, 14);
		panel.add(label_4);
		
		if (crewLength == 2) {
			btnCrew.setText(crewMembers.get(0).name);
			btnCrew_1.setText(crewMembers.get(1).name);
			btnCrew_2.setVisible(false);
			label_3.setVisible(false);
			btnCrew_3.setVisible(false);
			label_4.setVisible(false);
		} else if (crewLength == 3) {
			btnCrew.setText(crewMembers.get(0).name);
			btnCrew_1.setText(crewMembers.get(1).name);
			btnCrew_2.setText(crewMembers.get(2).name);
			label_3.setText("Actions: "+crewMembers.get(2).actionsRemaining);
			btnCrew_3.setVisible(false);
			label_4.setVisible(false);
		} else if (crewLength == 4) {
			btnCrew.setText(crewMembers.get(0).name);
			btnCrew_1.setText(crewMembers.get(1).name);
			btnCrew_2.setText(crewMembers.get(2).name);
			label_3.setText("Actions: "+crewMembers.get(2).actionsRemaining);
			btnCrew_3.setText(crewMembers.get(3).name);
			label_4.setText("Actions: "+crewMembers.get(3).actionsRemaining);
		}
		
	}
}
