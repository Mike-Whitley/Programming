
/**
 * This class contains the screen the player uses to eat food
 */
package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

public class FoodSelectScreen {

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
					FoodSelectScreen window = new FoodSelectScreen();
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
	
	public FoodSelectScreen() {
		initialize();
	}
	
	public FoodSelectScreen(GameEnvironment tempGame, int cIndex) {
		currentCrew = crewMembers.get(cIndex);
		Game = tempGame;
		inventory = Game.Shop.getInventory();
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 660, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JLabel lblHunger = new JLabel(currentCrew.name+"'s Hunger: "+currentCrew.hunger);
		lblHunger.setForeground(Color.LIGHT_GRAY);
		lblHunger.setFont(new Font("Dialog", Font.BOLD, 18));
		lblHunger.setBounds(390, 50, 240, 30);
		frame.getContentPane().add(lblHunger);
		
		JLabel lblHungerPoints_5 = new JLabel("20 Hunger Points");
		lblHungerPoints_5.setBackground(Color.DARK_GRAY);
		lblHungerPoints_5.setForeground(Color.WHITE);
		lblHungerPoints_5.setBounds(220, 133, 100, 20);
		frame.getContentPane().add(lblHungerPoints_5);
		
		JLabel lblHungerPoints_2 = new JLabel("75 Hunger Points");
		lblHungerPoints_2.setBackground(Color.DARK_GRAY);
		lblHungerPoints_2.setForeground(Color.WHITE);
		lblHungerPoints_2.setBounds(220, 283, 100, 20);
		frame.getContentPane().add(lblHungerPoints_2);
		
		JLabel lblHungerPoints_3 = new JLabel("50 Hunger Points");
		lblHungerPoints_3.setBackground(Color.DARK_GRAY);
		lblHungerPoints_3.setForeground(Color.WHITE);
		lblHungerPoints_3.setBounds(220, 233, 100, 20);
		frame.getContentPane().add(lblHungerPoints_3);
		
		JLabel lblHungerPoints_1 = new JLabel("100 Hunger Points");
		lblHungerPoints_1.setBackground(Color.DARK_GRAY);
		lblHungerPoints_1.setForeground(Color.WHITE);
		lblHungerPoints_1.setBounds(220, 333, 100, 20);
		frame.getContentPane().add(lblHungerPoints_1);
		
		JLabel lblHungerPoints_4 = new JLabel("30 Hunger Points");
		lblHungerPoints_4.setBackground(Color.DARK_GRAY);
		lblHungerPoints_4.setForeground(Color.WHITE);
		lblHungerPoints_4.setBounds(220, 183, 100, 20);
		frame.getContentPane().add(lblHungerPoints_4);
		
		JLabel lblHungerPoints = new JLabel("10 Hunger Points");
		lblHungerPoints.setBackground(Color.DARK_GRAY);
		lblHungerPoints.setForeground(Color.WHITE);
		lblHungerPoints.setBounds(220, 80, 100, 20);
		frame.getContentPane().add(lblHungerPoints);
		
		JLabel lblFoodSelect = new JLabel("Food Selection");
		lblFoodSelect.setForeground(Color.WHITE);
		lblFoodSelect.setFont(new Font("Dialog", Font.BOLD, 22));
		lblFoodSelect.setBounds(10, 10, 180, 30);
		frame.getContentPane().add(lblFoodSelect);
		
		JLabel lblCrewMember = new JLabel("Crew Member: "+currentCrew.name);
		lblCrewMember.setForeground(Color.WHITE);
		lblCrewMember.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCrewMember.setBounds(270, 10, 360, 30);
		frame.getContentPane().add(lblCrewMember);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameEnvironment.openStatusScreen();
				frame.setVisible(false);
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(Color.GRAY);
		btnBack.setBounds(530, 450, 100, 30);
		frame.getContentPane().add(btnBack);
		
		final JRadioButton btn1 = new JRadioButton("Cookie");
		buttonGroup.add(btn1);
		btn1.setFont(new Font("Courier New", Font.PLAIN, 16));
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		btn1.setBounds(30, 80, 170, 30);
		frame.getContentPane().add(btn1);
		
		final JRadioButton btn2 = new JRadioButton("Space Snack");
		buttonGroup.add(btn2);
		btn2.setFont(new Font("Courier New", Font.PLAIN, 16));
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.BLACK);
		btn2.setBounds(30, 130, 170, 30);
		frame.getContentPane().add(btn2);
		
		final JRadioButton btn3 = new JRadioButton("Sandwich");
		buttonGroup.add(btn3);
		btn3.setFont(new Font("Courier New", Font.PLAIN, 16));
		btn3.setForeground(Color.WHITE);
		btn3.setBackground(Color.BLACK);
		btn3.setBounds(30, 180, 170, 30);
		frame.getContentPane().add(btn3);
		
		final JRadioButton btn4 = new JRadioButton("Space Lunch");
		buttonGroup.add(btn4);
		btn4.setFont(new Font("Courier New", Font.PLAIN, 16));
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.BLACK);
		btn4.setBounds(30, 230, 170, 30);
		frame.getContentPane().add(btn4);
		
		final JRadioButton btn5 = new JRadioButton("Large Dinner");
		buttonGroup.add(btn5);
		btn5.setForeground(Color.WHITE);
		btn5.setFont(new Font("Courier New", Font.PLAIN, 16));
		btn5.setBackground(Color.BLACK);
		btn5.setBounds(30, 280, 170, 30);
		frame.getContentPane().add(btn5);
		
		final JRadioButton btn6 = new JRadioButton("Portable Feast");
		buttonGroup.add(btn6);
		btn6.setFont(new Font("Courier New", Font.PLAIN, 16));
		btn6.setForeground(Color.WHITE);
		btn6.setBackground(Color.BLACK);
		btn6.setBounds(30, 330, 170, 30);
		frame.getContentPane().add(btn6);
		
		final JLabel lblInv = new JLabel(Game.Shop.getFood());
		lblInv.setVerticalAlignment(SwingConstants.TOP);
		lblInv.setFont(new Font("Courier New", Font.PLAIN, 16));
		lblInv.setForeground(Color.WHITE);
		lblInv.setBounds(400, 220, 220, 180);
		frame.getContentPane().add(lblInv);
		
		/**
		 * Determines if they player health is below 100 the player can "eat" one one of the food items they have and increases their health level by the item 
		 * eaten amount.
		 * Checks that the player has enough of the item to be used and updates all variables associated with the change of this value.
		 */
		
		JButton btnEatSelectedFood = new JButton("Eat Selected Food");
		btnEatSelectedFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentCrew.hunger == 100) {
					JOptionPane.showMessageDialog(null, currentCrew.name+"'s hunger level is at its max!", "Game Message: " + "Hunger Level Maxed", JOptionPane.INFORMATION_MESSAGE);
				} else {
					
					if (btn1.isSelected()) {
						if (inventory.get("Cookie") > 0) {
							currentCrew.hunger = currentCrew.hunger + 10;
							inventory.replace("Cookie", inventory.get("Cookie")-1);
							lblInv.setText(Game.Shop.getFood());
						} else {
							JOptionPane.showMessageDialog(null, "You don't have this food item!", "Game Message: " + "Insufficient Food Items", JOptionPane.INFORMATION_MESSAGE);
						}
					} else if (btn2.isSelected()) {
						if (inventory.get("Space Snack") > 0) {
							currentCrew.hunger = currentCrew.hunger + 20;
							inventory.replace("Space Snack", inventory.get("Space Snack")-1);
							lblInv.setText(Game.Shop.getFood());
						} else {
							JOptionPane.showMessageDialog(null, "You don't have this food item!", "Game Message: " + "Insufficient Food Items", JOptionPane.INFORMATION_MESSAGE);
						}
					} else if (btn3.isSelected()) {
						if (inventory.get("Sandwich") > 0) {
							currentCrew.hunger = currentCrew.hunger + 30;
							inventory.replace("Sandwich", inventory.get("Sandwich")-1);
							lblInv.setText(Game.Shop.getFood());
						} else {
							JOptionPane.showMessageDialog(null, "You don't have this food item!", "Game Message: " + "Insufficient Food Items", JOptionPane.INFORMATION_MESSAGE);
						}
					} else if (btn4.isSelected()) {
						if (inventory.get("Space Lunch") > 0) {
							currentCrew.hunger = currentCrew.hunger + 50;
							inventory.replace("Space Lunch", inventory.get("Space Lunch")-1);
							lblInv.setText(Game.Shop.getFood());
						} else {
							JOptionPane.showMessageDialog(null, "You don't have this food item!", "Game Message: " + "Insufficient Food Items", JOptionPane.INFORMATION_MESSAGE);
						}
					} else if (btn5.isSelected()) {
						if (inventory.get("Large Dinner") > 0) {
							currentCrew.hunger = currentCrew.hunger + 75;
							inventory.replace("Large Dinner", inventory.get("Large Dinner")-1);
							lblInv.setText(Game.Shop.getFood());
						} else {
							JOptionPane.showMessageDialog(null, "You don't have this food item!", "Game Message: " + "Insufficient Food Items", JOptionPane.INFORMATION_MESSAGE);
						}
					} else if (btn6.isSelected()) {
						if (inventory.get("Portable Feast") > 0) {
							currentCrew.hunger = currentCrew.hunger + 100;
							inventory.replace("Portable Feast", inventory.get("Portable Feast")-1);
							lblInv.setText(Game.Shop.getFood());
						} else {
							JOptionPane.showMessageDialog(null, "You don't have this food item!", "Game Message: " + "Insufficient Food Items", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please select a food item for "+currentCrew.name+" to eat", "Game Message: " + "No Food Item Selected", JOptionPane.INFORMATION_MESSAGE);
					}
				if (currentCrew.hunger > 100) {
					currentCrew.hunger = 100;
				}
				lblHunger.setText(currentCrew.name+"'s Hunger: "+currentCrew.hunger);
				}
				
			}
		});
		btnEatSelectedFood.setForeground(Color.WHITE);
		btnEatSelectedFood.setBackground(Color.GRAY);
		btnEatSelectedFood.setBounds(10, 450, 140, 30);
		frame.getContentPane().add(btnEatSelectedFood);
		
		JLabel lblFoodInventory = new JLabel("Food Inventory:");
		lblFoodInventory.setForeground(Color.WHITE);
		lblFoodInventory.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFoodInventory.setBounds(400, 180, 140, 30);
		frame.getContentPane().add(lblFoodInventory);
	}
}
