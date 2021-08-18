package main;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class StatusScreen {
	
	
	private String displayText;
	private GameEnvironment Game;
	private int pieceFound;
	private int totalPieces = GameEnvironment.numPieces;
	private Ship currentTeam = GameEnvironment.getTeam();
	private int crewLength = currentTeam.crewLength();
	private int numPieces = GameEnvironment.numPieces;
	private Ship playerShip = GameEnvironment.getPlayerShip();
	private ArrayList<CrewMember> crewMembers = playerShip.getCrewMembers();
	private int currentSelect = 0;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private Random rand = new Random();
	public JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatusScreen window = new StatusScreen(new GameEnvironment());
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
	public StatusScreen(GameEnvironment Game) {
		this.Game = Game;
		pieceFound = Game.pieceFound;
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
		
		
		final JLabel lblClickOnA = new JLabel("Click on a Crew Member to show their stats");
		lblClickOnA.setVerticalAlignment(SwingConstants.TOP);
		lblClickOnA.setHorizontalAlignment(SwingConstants.LEFT);
		lblClickOnA.setForeground(Color.WHITE);
		lblClickOnA.setBounds(10, 280, 440, 160);
		frame.getContentPane().add(lblClickOnA);
		
		
		final JLabel lblCurrentCredits = new JLabel("Credits: " + playerShip.getCredits());
		lblCurrentCredits.setForeground(Color.WHITE);
		lblCurrentCredits.setBounds(350, 150, 100, 20);
		frame.getContentPane().add(lblCurrentCredits);
		
		
		JLabel lblNewLabel = new JLabel("Ship Name: "+GameEnvironment.getShipName());
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(140, 10, 320, 30);
		frame.getContentPane().add(lblNewLabel);
		
		
		final JLabel lblDaysRemaining = new JLabel("Days remaining:" + GameEnvironment.getNumSimulationDays());
		lblDaysRemaining.setForeground(Color.WHITE);
		lblDaysRemaining.setBounds(330, 458, 140, 14);
		frame.getContentPane().add(lblDaysRemaining);
		
		
		JButton btnNextDay = new JButton("Next Day");
		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < crewLength; i++) {
					crewMembers.get(i).resetActions();
				}
				
				for (int i = 0; i < crewLength; i++) {
					crewMembers.get(i).reduceHunger(10);
					if (crewMembers.get(i).hasPlague == true) {
						crewMembers.get(i).reduceHealth(10);
					}
				}
				
				lblClickOnA.setText(crewMembers.get(currentSelect).toString());
				
				Game.numDaysPassed += 1;
				if (playerShip.checkIfDeadFromHunger()) {
					GameEnvironment.openLoseScreen("Hunger");
					frame.setVisible(false);
				}
				

				GameEnvironment.reduceDay();
				
				lblDaysRemaining.setText("Days remaining:" + GameEnvironment.getNumSimulationDays());
				
				if (GameEnvironment.getNumSimulationDays() == 0 && numPieces != Game.currentPiecesCollected) {
					GameEnvironment.openLoseScreen("Time");
					frame.setVisible(false);
					
				}
				
				for(CrewMember crew: crewMembers) {
					if(crew.ifAlive()){
						
					}
						
				}
		
	}
		});
		btnNextDay.setForeground(Color.WHITE);
		btnNextDay.setBackground(Color.GRAY);
		btnNextDay.setBounds(500, 450, 120, 30);
		frame.getContentPane().add(btnNextDay);
		
		JLabel lblItems = new JLabel("Items:");
		lblItems.setForeground(Color.WHITE);
		lblItems.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblItems.setBounds(480, 14, 120, 20);
		frame.getContentPane().add(lblItems);
		
		JButton btnOpenShop = new JButton("Open Shop");
		btnOpenShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameEnvironment.openShopScreen();
				frame.setVisible(false);
			}
		});
		btnOpenShop.setForeground(Color.WHITE);
		btnOpenShop.setBackground(Color.GRAY);
		btnOpenShop.setBounds(20, 452, 120, 30);
		frame.getContentPane().add(btnOpenShop);
		
	
		final JLabel LblItemslist = new JLabel(Game.Shop.toString());
		LblItemslist.setVerticalAlignment(SwingConstants.TOP);
		LblItemslist.setForeground(Color.WHITE);
		LblItemslist.setBounds(480, 40, 160, 240);
		frame.getContentPane().add(LblItemslist);
		
		final JRadioButton rdbtnCrew_1 = new JRadioButton("Crew1", true);
		lblClickOnA.setText(crewMembers.get(0).toString());
		rdbtnCrew_1.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				currentSelect = 0;
				lblClickOnA.setText(crewMembers.get(0).toString());
			}
		});
		buttonGroup.add(rdbtnCrew_1);
		rdbtnCrew_1.setForeground(Color.WHITE);
		rdbtnCrew_1.setBackground(Color.BLACK);
		rdbtnCrew_1.setBounds(20, 50, 150, 30);
		frame.getContentPane().add(rdbtnCrew_1);
		
		final JRadioButton rdbtnCrew_2 = new JRadioButton("Crew2");
		rdbtnCrew_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentSelect = 1;
				lblClickOnA.setText(crewMembers.get(1).toString());
			}
		});
		buttonGroup.add(rdbtnCrew_2);
		rdbtnCrew_2.setForeground(Color.WHITE);
		rdbtnCrew_2.setBackground(Color.BLACK);
		rdbtnCrew_2.setBounds(180, 50, 150, 30);
		frame.getContentPane().add(rdbtnCrew_2);
		
		final JRadioButton rdbtnCrew_3 = new JRadioButton("Crew3");
		rdbtnCrew_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentSelect = 2;
				lblClickOnA.setText(crewMembers.get(2).toString());
			}
		});
		buttonGroup.add(rdbtnCrew_3);
		rdbtnCrew_3.setForeground(Color.WHITE);
		rdbtnCrew_3.setBackground(Color.BLACK);
		rdbtnCrew_3.setBounds(20, 80, 150, 30);
		frame.getContentPane().add(rdbtnCrew_3);
		
		final JRadioButton rdbtnCrew_4 = new JRadioButton("Crew4");
		rdbtnCrew_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentSelect = 3;
				lblClickOnA.setText(crewMembers.get(3).toString());
			}
		});
		buttonGroup.add(rdbtnCrew_4);
		rdbtnCrew_4.setForeground(Color.WHITE);
		rdbtnCrew_4.setBackground(Color.BLACK);
		rdbtnCrew_4.setBounds(180, 80, 150, 30);
		frame.getContentPane().add(rdbtnCrew_4);
		
		JButton btnSleepToRest = new JButton("Sleep");
		btnSleepToRest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (crewMembers.get(currentSelect).actionsRemaining > 0) {
					if (crewMembers.get(currentSelect).tiredness < 100) {
						crewMembers.get(currentSelect).tiredness = 100;
						crewMembers.get(currentSelect).useAction();
					} else {
						JOptionPane.showMessageDialog(null, "Crew member is not tired enough to sleep", "Game Message: " + "Not Tired Enough", JOptionPane.INFORMATION_MESSAGE);						
					}
				} else {
					JOptionPane.showMessageDialog(null, "Not Enough Action points", "Game Message: " + "Action points are low!", JOptionPane.INFORMATION_MESSAGE);					
				}
			}
		});
		btnSleepToRest.setForeground(Color.WHITE);
		btnSleepToRest.setBackground(Color.GRAY);
		btnSleepToRest.setBounds(10, 200, 160, 30);
		frame.getContentPane().add(btnSleepToRest);
		
		JLabel lblStatusPanel = new JLabel("Status Panel");
		lblStatusPanel.setForeground(Color.WHITE);
		lblStatusPanel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblStatusPanel.setBounds(10, 10, 150, 30);
		frame.getContentPane().add(lblStatusPanel);
		
		
		
		
		JButton btnSearchForScrap = new JButton("Scrap Search");
		btnSearchForScrap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (crewMembers.get(currentSelect).getActions() > 0) {
					
					crewMembers.get(currentSelect).useAction();
					int foundCredits = rand.nextInt(101)+10;
					
					if (crewMembers.get(currentSelect).race == "Human") {
						foundCredits *= 1.5;
						JOptionPane.showMessageDialog(null, "Your Human found: "+foundCredits+" credits worth of scrap and sold it", "Game Message: " + "Scrap sold", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Your crew found: "+foundCredits+" credits worth of scrap and sold it", "Game Message: " + "Scrap sold", JOptionPane.INFORMATION_MESSAGE);
					}
					
					playerShip.changeCredits(foundCredits);
					lblCurrentCredits.setText("Credits: "+playerShip.getCredits());
					
					lblClickOnA.setText(crewMembers.get(currentSelect).toString());
					
				} else {
					JOptionPane.showMessageDialog(null, "Not Enough Action points", "Game Message: " + "Action points are low!", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnSearchForScrap.setForeground(Color.WHITE);
		btnSearchForScrap.setBackground(Color.GRAY);
		btnSearchForScrap.setBounds(10, 160, 160, 30);
		frame.getContentPane().add(btnSearchForScrap);
		
		final JLabel lblShieldHealth = new JLabel("Shield Health: "+playerShip.getShieldLevel());
		lblShieldHealth.setForeground(Color.WHITE);
		lblShieldHealth.setBounds(185, 240, 150, 20);
		frame.getContentPane().add(lblShieldHealth);
		
		JButton btnEatFood = new JButton("Eat Food");
		btnEatFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (crewMembers.get(currentSelect).actionsRemaining > 0) {
					GameEnvironment.openFoodSelectScreen(currentSelect);
					frame.setVisible(false);
					crewMembers.get(currentSelect).useAction();
					
				} else {
					JOptionPane.showMessageDialog(null, "Not Enough Action points", "Game Message: " + "Action points are low!", JOptionPane.INFORMATION_MESSAGE);
				}
								
			}
		});
		btnEatFood.setForeground(Color.WHITE);
		btnEatFood.setBackground(Color.GRAY);
		btnEatFood.setBounds(180, 120, 160, 30);
		frame.getContentPane().add(btnEatFood);
		
		JButton btnUseMedicine = new JButton("Use Medicine");
		btnUseMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (crewMembers.get(currentSelect).actionsRemaining > 0) {
					GameEnvironment.openMedicineSelectScreen(currentSelect);
					frame.setVisible(false);
					crewMembers.get(currentSelect).useAction();
				} else {
					JOptionPane.showMessageDialog(null, "Not Enough Action points", "Game Message: " + "Action points are low!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		btnUseMedicine.setForeground(Color.WHITE);
		btnUseMedicine.setBackground(Color.GRAY);
		btnUseMedicine.setBounds(10, 120, 160, 30);
		frame.getContentPane().add(btnUseMedicine);
		
		JButton btnRepairShields = new JButton("Repair Shields");
		btnRepairShields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (crewMembers.get(currentSelect).actionsRemaining > 0) {
					if (playerShip.shipShield != 100) {
						playerShip.updateShipShield(((int) (20*crewMembers.get(currentSelect).repairMult)));
						crewMembers.get(currentSelect).useAction();
						if (playerShip.shipShield > 100) {
							playerShip.shipShield = 100;
						}
						lblShieldHealth.setText("Shield Health: "+playerShip.getShieldLevel());
					} else {
						JOptionPane.showMessageDialog(null, "Ship shields at at max level.", "Game Message: " + "Max Shields", JOptionPane.INFORMATION_MESSAGE);						
					}
				} else {
					JOptionPane.showMessageDialog(null, "Not Enough Action points", "Game Message: " + "Action points are low!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnRepairShields.setForeground(Color.WHITE);
		btnRepairShields.setBackground(Color.GRAY);
		btnRepairShields.setBounds(180, 200, 160, 30);
		frame.getContentPane().add(btnRepairShields);
		
		JLabel lblShipHealth = new JLabel("Ship Health: "+playerShip.getShipHealth());
		lblShipHealth.setForeground(Color.WHITE);
		lblShipHealth.setBounds(15, 240, 150, 20);
		frame.getContentPane().add(lblShipHealth);
		
		JButton btnMainMenu = new JButton("Travel Screen");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameEnvironment.openMainScreen();
				frame.setVisible(false);
			}
		});
		btnMainMenu.setForeground(Color.WHITE);
		btnMainMenu.setBackground(Color.GRAY);
		btnMainMenu.setBounds(152, 452, 160, 30);
		frame.getContentPane().add(btnMainMenu);
		
		JButton btnSearchPlanet = new JButton("Search Planet");
		btnSearchPlanet.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) { //mike working here
				if (crewMembers.get(currentSelect).getActions() > 0) {
					
					if (Game.pieceFound == 1) {
						JOptionPane.showMessageDialog(null, "No more warp drive pieces on this planet.", "Game Message: " + "No Item Found", JOptionPane.INFORMATION_MESSAGE);
						
					} else {
						lblClickOnA.setText(crewMembers.get(currentSelect).toString());
						Random rand = new Random();
						int randomNum = rand.nextInt(100);
						crewMembers.get(currentSelect).useAction();
						lblClickOnA.setText(crewMembers.get(currentSelect).toString());
						
						if (randomNum < 25) {
							JOptionPane.showMessageDialog(null, "Your crew found a warp drive piece.", "Game Message: " + "Item found", JOptionPane.INFORMATION_MESSAGE);
							Game.pieceFound();
							Game.addPiece();
							
							if (Game.currentPiecesCollected == totalPieces) {
								GameEnvironment.openWinScreen();
								frame.setVisible(false);
							}							
							
						} else if (randomNum >= 25 && randomNum < 50) {
							String foodFound = Game.Shop.getRandomFoodItem();
							LblItemslist.setText(Game.Shop.toString());
							JOptionPane.showMessageDialog(null, "Your crew found a " + foodFound, "Game Message: " + "Item found", JOptionPane.INFORMATION_MESSAGE);
							
							
						} else if (randomNum >= 50 && randomNum > 75) {
							String medItemFound = Game.Shop.getRandomMedicalItem();
							LblItemslist.setText(Game.Shop.toString());
							JOptionPane.showMessageDialog(null, "Your crew found a " + medItemFound, "Game Message: " + "Item found", JOptionPane.INFORMATION_MESSAGE);
							
							
						} else {
							JOptionPane.showMessageDialog(null, "Your crew found nothing.", "Game Message: " + "Item found", JOptionPane.INFORMATION_MESSAGE);
						}
						
						if (Game.currentPiecesCollected == totalPieces) {
							GameEnvironment.openWinScreen();
							frame.setVisible(false);
						}
						
				}
					

					
				} else {
					JOptionPane.showMessageDialog(null, "Not Enough Action points", "Game Message: " + "Action points are low!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
//		
//		else {
//			JOptionPane.showMessageDialog(null, "Not Enough Action points", "Game Message: " + "Action points are low!", JOptionPane.INFORMATION_MESSAGE);
//			}
//		
		
		btnSearchPlanet.setForeground(Color.WHITE);
		btnSearchPlanet.setBackground(Color.GRAY);
		btnSearchPlanet.setBounds(180, 160, 160, 30);
		frame.getContentPane().add(btnSearchPlanet);
		

		if (crewLength == 2) {
			rdbtnCrew_1.setText(crewMembers.get(0).name);
			rdbtnCrew_2.setText(crewMembers.get(1).name);
			rdbtnCrew_4.setVisible(false);
			rdbtnCrew_3.setVisible(false);
		} else if (crewLength == 3) {
			rdbtnCrew_1.setText(crewMembers.get(0).name);
			rdbtnCrew_2.setText(crewMembers.get(1).name);
			rdbtnCrew_3.setText(crewMembers.get(2).name);
			rdbtnCrew_4.setVisible(false);
		} else if (crewLength == 4) {
			rdbtnCrew_1.setText(crewMembers.get(0).name);
			rdbtnCrew_2.setText(crewMembers.get(1).name);
			rdbtnCrew_3.setText(crewMembers.get(2).name);
			rdbtnCrew_4.setText(crewMembers.get(3).name);
		}
		
		if (Game.currentPiecesCollected == totalPieces) {
			GameEnvironment.openWinScreen();
			frame.setVisible(false);
		}
		
				
				
	}
}
