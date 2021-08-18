package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MedicineSelectScreen {
	
	public JFrame frame;
	private Ship playerShip = GameEnvironment.getPlayerShip();
	private ArrayList<CrewMember> crewMembers = playerShip.getCrewMembers();
	private CrewMember currentCrew;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private GameEnvironment Game;
	private Map<String, Integer> inventory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicineSelectScreen window = new MedicineSelectScreen();
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
	public MedicineSelectScreen() {
		initialize();
	}
	
	public MedicineSelectScreen(GameEnvironment tempGame, int cIndex) {
		currentCrew = crewMembers.get(cIndex);
		Game = tempGame;
		inventory = Game.Shop.getInventory();
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
		
		JLabel lblMedicineSelection = new JLabel("Medicine Selection");
		lblMedicineSelection.setForeground(Color.WHITE);
		lblMedicineSelection.setFont(new Font("Dialog", Font.BOLD, 22));
		lblMedicineSelection.setBounds(10, 10, 200, 30);
		frame.getContentPane().add(lblMedicineSelection);
		
		JLabel label = new JLabel("Crew Member: "+currentCrew.name);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 18));
		label.setBounds(270, 10, 360, 30);
		frame.getContentPane().add(label);
		
		final JLabel lblsHealth = new JLabel(currentCrew.name+"'s Health: "+currentCrew.health);
		lblsHealth.setForeground(Color.LIGHT_GRAY);
		lblsHealth.setFont(new Font("Dialog", Font.BOLD, 18));
		lblsHealth.setBounds(390, 50, 240, 30);
		frame.getContentPane().add(lblsHealth);
		
		final JRadioButton radioButton = new JRadioButton("Bandage");
		buttonGroup.add(radioButton);
		radioButton.setForeground(Color.WHITE);
		radioButton.setFont(new Font("Courier New", Font.PLAIN, 16));
		radioButton.setBackground(Color.BLACK);
		radioButton.setBounds(30, 80, 170, 30);
		frame.getContentPane().add(radioButton);
		
		final JRadioButton radioButton_1 = new JRadioButton("Stimpack");
		buttonGroup.add(radioButton_1);
		radioButton_1.setForeground(Color.WHITE);
		radioButton_1.setFont(new Font("Courier New", Font.PLAIN, 16));
		radioButton_1.setBackground(Color.BLACK);
		radioButton_1.setBounds(30, 130, 170, 30);
		frame.getContentPane().add(radioButton_1);
		
		final JRadioButton radioButton_2 = new JRadioButton("Plague Cure");
		buttonGroup.add(radioButton_2);
		radioButton_2.setForeground(Color.WHITE);
		radioButton_2.setFont(new Font("Courier New", Font.PLAIN, 16));
		radioButton_2.setBackground(Color.BLACK);
		radioButton_2.setBounds(30, 180, 170, 30);
		frame.getContentPane().add(radioButton_2);
		
		final JRadioButton radioButton_3 = new JRadioButton("Medicine");
		buttonGroup.add(radioButton_3);
		radioButton_3.setForeground(Color.WHITE);
		radioButton_3.setFont(new Font("Courier New", Font.PLAIN, 16));
		radioButton_3.setBackground(Color.BLACK);
		radioButton_3.setBounds(30, 230, 170, 30);
		frame.getContentPane().add(radioButton_3);
		
		JLabel label_1 = new JLabel("25 Health Points");
		label_1.setForeground(Color.WHITE);
		label_1.setBackground(Color.DARK_GRAY);
		label_1.setBounds(220, 85, 100, 20);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("50 Health Points");
		label_2.setForeground(Color.WHITE);
		label_2.setBackground(Color.DARK_GRAY);
		label_2.setBounds(220, 135, 100, 20);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Cures the space plague");
		label_3.setForeground(Color.WHITE);
		label_3.setBackground(Color.DARK_GRAY);
		label_3.setBounds(220, 185, 150, 20);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("100 Health Points");
		label_4.setForeground(Color.WHITE);
		label_4.setBackground(Color.DARK_GRAY);
		label_4.setBounds(220, 235, 100, 20);
		frame.getContentPane().add(label_4);
		
		JLabel lblMedicineInventory = new JLabel("Medicine Inventory:");
		lblMedicineInventory.setForeground(Color.WHITE);
		lblMedicineInventory.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMedicineInventory.setBounds(400, 180, 160, 30);
		frame.getContentPane().add(lblMedicineInventory);
		
		final JLabel label_5 = new JLabel(Game.Shop.getMedicine());
		label_5.setVerticalAlignment(SwingConstants.TOP);
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Courier New", Font.PLAIN, 16));
		label_5.setBounds(400, 220, 220, 180);
		frame.getContentPane().add(label_5);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameEnvironment.openStatusScreen();
				frame.setVisible(false);
			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(Color.GRAY);
		button.setBounds(530, 450, 100, 30);
		frame.getContentPane().add(button);
		
		JButton btnUseSelectedMedicine = new JButton("Use Selected Medicine");
		btnUseSelectedMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentCrew.health == currentCrew.maxHealth && radioButton_2.isSelected() == false) {
					JOptionPane.showMessageDialog(null, currentCrew.name+"'s health level is at its max!", "Game Message: " + "Health Level Maxed", JOptionPane.INFORMATION_MESSAGE);
				} else {
					
					if (radioButton.isSelected()) {
						if (inventory.get("Bandage") > 0) {
							currentCrew.health = currentCrew.health + 25;
							inventory.replace("Bandage", inventory.get("Bandage")-1);
							label_5.setText(Game.Shop.getMedicine());
						} else {
							JOptionPane.showMessageDialog(null, "You don't have this medical item!", "Game Message: " + "Insufficient Medical Items", JOptionPane.INFORMATION_MESSAGE);
						}
					} else if (radioButton_1.isSelected()) {
						if (inventory.get("Stimpack") > 0) {
							currentCrew.health = currentCrew.health + 50;
							inventory.replace("Stimpack", inventory.get("Stimpack")-1);
							label_5.setText(Game.Shop.getMedicine());
						} else {
							JOptionPane.showMessageDialog(null, "You don't have this medical item!", "Game Message: " + "Insufficient Medical Items", JOptionPane.INFORMATION_MESSAGE);
						}
					} else if (radioButton_2.isSelected()) {
						if (inventory.get("Plague Cure") > 0) {
							currentCrew.hasPlague = false;
							inventory.replace("Plague Cure", inventory.get("Plague Cure")-1);
							label_5.setText(Game.Shop.getMedicine());
						} else {
							JOptionPane.showMessageDialog(null, "You don't have this medical item!", "Game Message: " + "Insufficient Medical Items", JOptionPane.INFORMATION_MESSAGE);
						}
					} else if (radioButton_3.isSelected()) {
						if (inventory.get("Medicine") > 0) {
							currentCrew.health = currentCrew.health + 100;
							inventory.replace("Medicine", inventory.get("Medicine")-1);
							label_5.setText(Game.Shop.getMedicine());
						} else {
							JOptionPane.showMessageDialog(null, "You don't have this medical item!", "Game Message: " + "Insufficient Medical Items", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please select a medical item for "+currentCrew.name+" to use", "Game Message: " + "No Medical Item Selected", JOptionPane.INFORMATION_MESSAGE);
					}
				if (currentCrew.health > currentCrew.maxHealth) {
					currentCrew.health = currentCrew.maxHealth;
				}
				lblsHealth.setText(currentCrew.name+"'s Health: "+currentCrew.health);
				}
				
			}
		});
		btnUseSelectedMedicine.setForeground(Color.WHITE);
		btnUseSelectedMedicine.setBackground(Color.GRAY);
		btnUseSelectedMedicine.setBounds(10, 450, 180, 30);
		frame.getContentPane().add(btnUseSelectedMedicine);
	}
}
