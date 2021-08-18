package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class MainScreen {
	
	private Ship playerShip = GameEnvironment.getPlayerShip();
	private ArrayList<CrewMember> crewMembers = playerShip.getCrewMembers();
	private GameEnvironment Game = GameEnvironment.Game;
	
	
	private int totalPieces = GameEnvironment.getNumPieces();
	private int currentPlanetPieces = 1 - Game.pieceFound;
	private int crewWithActions = 0;
	
	Random rand = new Random();
	
	public JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
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
	public MainScreen() {
		initialize();
	}
	
	/**
	 * Kills the first living crew member in the crewMembers ArrayList
	 * @param reason is the reason that the crew member was killed, used to tell the user how they lost the game if they lose too many crew members
	 */
	public void killCrew(String reason) {
		
		int crewKilled = 0;
		
		if (crewMembers.get(0).isAlive == true) {
			crewMembers.get(0).isAlive = false;
			crewKilled = 1;
			
		} else if (crewKilled == 0 && crewMembers.get(1).isAlive == true) {
			crewMembers.get(1).isAlive = false;
			crewKilled = 1;
			
		}  else if (crewKilled == 0 && crewMembers.get(2).isAlive == true) {
			crewMembers.get(2).isAlive = false;
			crewKilled = 1;
			
		}  else {
			crewMembers.get(3).isAlive = false;
			crewKilled = 1;
			
		}
		
		checkIfDead(reason);
		
	}
	
	/**
	 * 
	 * @param number
	 * Takes an int input of 1, 2 or 3 the amount of members that will be given the plague 
	 * Based on how many members are given checks all the players that are alive gives a certain amount the space plague 
	 * or if not many crew gives them all space plague
	 * space plague reduces the heros effected health by 10 per day.
	 */
	public void givePlague(int number) {
		int crewSize = crewMembers.size();
		int crewCount = 0;
		int numInfected = 0;
		
		while (crewCount < crewSize) {
			if (crewMembers.get(crewCount).isAlive == true) {
				crewCount += 1;
			}
		}
		
		if (crewCount >= number) { //More crew than people who are to be infected
			while (numInfected < number) {
				crewMembers.get(numInfected).hasPlague = true;
				numInfected += 1;
			}
		} else { //All crew must be infected
			while (numInfected < crewSize) {
				crewMembers.get(numInfected).hasPlague = true;
				numInfected += 1;
			}
		}
	}
	/**
<<<<<<< HEAD
	 * checks if the player is dead if they player count goes below 2 the lose screen is displayed.
	 * @param reason
	 * Takes the reason global param which holds the reason why the player has lost e.g. hunger death
=======
	 * Checks if the crew is dead. If the crew count goes below 2 the lose screen is displayed.
	 * @param Takes the reason global parameter which holds the reason why the player has lost e.g. hunger death
>>>>>>> branch 'master' of https://github.com/Mike389727/Seng201-project.git
	 */
	public void checkIfDead(String reason) {
		ArrayList<CrewMember> crewMembers = playerShip.getCrewMembers();
		int crewSize = crewMembers.size();
		int crewCount = 0;
		
		while (crewCount < crewSize) {
			if (crewMembers.get(crewCount).isAlive == true) {
				crewCount += 1;
			}
		}
		
		if (crewCount < 2) {
			GameEnvironment.openLoseScreen(reason);
		}
		
	}
	
	/**
	 * 
	 * @param damage
	 * takes parameter int of the amount of damage the ship will take in damage 
	 * @param reason
	 * takes the reason string as imput as to the reason the player loses this will be the input of ship dying.
	 */
	
	public void damageShip(int damage, String reason) {
		if (playerShip.shipShield < 0) {
			playerShip.shipShield = 0;
		}
		
		int shieldMult = (100-playerShip.shipShield)/100;
		playerShip.shipHealth -= (damage*shieldMult);
		
		if (playerShip.shipHealth <= 0) {
			GameEnvironment.openLoseScreen(reason);
		}
		
	}
	/**
	 * 
	 * @param credits
	 * Takes input amount you want to increase the players credits by, this is an int value. 
	 */
	public void addCredits(int credits) {
		playerShip.playerCredits += credits;
	}

	
	/**
	 * 
	 * @param scrap
	 * takes "large" or any other value string 
	 * if large is input increases credits between 151 and 200 otherwise its between 51 or 50
	 */
	
	public void giveScrap(String scrap) {
		
		int creditLimitUpper;
		int creditLimitLower;
		
		if (scrap == "Large") {
			creditLimitUpper = 151;
			creditLimitLower = 200;
		} else {
			creditLimitUpper = 51;
			creditLimitLower = 50;
		}
		
		int foundCredits = rand.nextInt(creditLimitUpper)+creditLimitLower;
		playerShip.changeCredits(foundCredits);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 660, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnStatusPanel = new JButton("Status Panel");
		btnStatusPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameEnvironment.openStatusScreen();
				frame.setVisible(false);
			}
		});
		btnStatusPanel.setForeground(Color.WHITE);
		btnStatusPanel.setBackground(Color.GRAY);
		btnStatusPanel.setBounds(27, 430, 140, 50);
		frame.getContentPane().add(btnStatusPanel);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.BLACK);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Event Log", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setBounds(27, 130, 600, 240);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		final JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 20, 580, 210);
		panel.add(textPane);
		textPane.setBackground(Color.BLACK);
		textPane.setForeground(Color.WHITE);
		textPane.setText(" ");
		
		JButton btnQuitGame = new JButton("Quit Game");
		btnQuitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameEnvironment.openQuitScreen();
				frame.setVisible(false);
			}
		});
		btnQuitGame.setForeground(Color.WHITE);
		btnQuitGame.setBackground(Color.GRAY);
		btnQuitGame.setBounds(488, 430, 140, 50);
		frame.getContentPane().add(btnQuitGame);
		
		final JLabel lblDayX = new JLabel("Day: "+Game.numDaysPassed);
		lblDayX.setFont(new Font("Courier 10 Pitch", Font.BOLD, 20));
		lblDayX.setForeground(Color.WHITE);
		lblDayX.setBounds(560, 15, 80, 30);
		frame.getContentPane().add(lblDayX);
		
		final JLabel lblPiecesFound = new JLabel("Pieces Found: "+Game.currentPiecesCollected);
		lblPiecesFound.setForeground(Color.WHITE);
		lblPiecesFound.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPiecesFound.setBounds(20, 15, 233, 30);
		frame.getContentPane().add(lblPiecesFound);
		
		final JLabel lblPiecesRemainingX = new JLabel("Pieces Remaining: "+(totalPieces-Game.currentPiecesCollected));
		lblPiecesRemainingX.setForeground(Color.WHITE);
		lblPiecesRemainingX.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPiecesRemainingX.setBounds(20, 50, 343, 30);
		frame.getContentPane().add(lblPiecesRemainingX);
		
		final JLabel lblPiecesOnThis = new JLabel("Pieces On This Planet: "+currentPlanetPieces);
		lblPiecesOnThis.setForeground(Color.WHITE);
		lblPiecesOnThis.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPiecesOnThis.setBounds(20, 90, 310, 30);
		frame.getContentPane().add(lblPiecesOnThis);
		
		final JLabel lblCredits = new JLabel("Credits: "+playerShip.playerCredits);
		lblCredits.setForeground(Color.WHITE);
		lblCredits.setFont(new Font("Dialog", Font.BOLD, 20));
		lblCredits.setBounds(300, 15, 160, 30);
		frame.getContentPane().add(lblCredits);
		
		JButton btnNewPlanet = new JButton("Find A New Planet");
		btnNewPlanet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for (int i = 0; i < crewMembers.size(); i++) {
					if (crewMembers.get(i).actionsRemaining > 0) {
						crewWithActions++;
						
					}
				}
				
				if (crewWithActions >= 2) {
					
					GameEnvironment.openNextPlanetScreen();
					
					if (Game.numDaysPassed == GameEnvironment.getNumSimulationDays()) {
						GameEnvironment.openLoseScreen("Time");
						frame.setVisible(false);
					}
					
					int randInt1 = rand.nextInt(6);
					
					
					if (randInt1 == 0) { //Alien Pirates
						String stolenItem = Game.Shop.stealItemFromShip();
						if (stolenItem == ("NoItem")) {
							textPane.setText("Your ship is ambushed by alien pirates. They try to steal an item but there was nothing to steal!");
						}else {
							textPane.setText("Your ship is ambushed by alien pirates they have stolen " + stolenItem);
							Game.Shop.remove(stolenItem);

						}
					
						
						
					} else if (randInt1 == 1) { //Space Plague
						
						int randInt2 = rand.nextInt(100);
						
						if (randInt2 < 70) {
							//1 crew gets plague
							givePlague(1);
							textPane.setText("One of your crew members has contracted the space plague. Without the cure their health will continually decrease");
							
						} else if (randInt2 >= 70 && randInt2 < 90) {
							//2 crew get plague
							givePlague(2);
							textPane.setText("Two of your crew members have contracted the space plague. Without the cure their health will continually decrease");
							
						} else {
							//3 crew get plague
							givePlague(3);
							textPane.setText("Three of your crew members have contracted the space plague. Without the cure their health will continually decrease");
							
						}
						
					} else if (randInt1 == 2) { //Asteroid Belt
						if (playerShip.shipShield < 100) {
							
							int randInt2 = rand.nextInt(100);
							
							if (randInt2 < 70) {
								//25 damage
								damageShip(25, "Asteroids");
								textPane.setText("You arrive near an asteroid belt and your ship takes light damage from a rogue asteroid. Current ship health is "+playerShip.shipHealth+"%");
							} else if (randInt2 >= 70 && randInt2 < 90) {
								//50 damage
								damageShip(50, "Asteroids");
								textPane.setText("You arrive near an asteroid belt and your ship takes moderate damage from a rogue asteroid. Current ship health is "+playerShip.shipHealth+"%");
							} else {
								//75 damage
								damageShip(75, "Asteroids");
								textPane.setText("You arrive near an asteroid belt and your ship takes heavy damage from a rogue asteroid. Current ship health is "+playerShip.shipHealth+"%");
							}
		
				
						} else {
							playerShip.shipShield -= 25;
							textPane.setText("You arrive near an asteroid belt and your shield manages to protect your ship from damage. Your shield level is now "+playerShip.shipShield+"%");

						}

						
					} else if (randInt1 == 3) { //Abandoned Ship
						
						int randInt2 = rand.nextInt(100);
						if (randInt2 < 20) {
							//Scrap reward
							giveScrap("Small");
							lblCredits.setText("Credits: "+playerShip.playerCredits);
							textPane.setText("Your crew found an abandoned ship with a small amount of scrap inside.");
							//													ADD TEXT BOX MESSAGE HERE
						} else if (randInt2 >= 20 && randInt2 < 40) {
							//Part found
							if (Game.pieceFound == 0) {
								Game.addPiece();
								Game.pieceFound = 1;
								currentPlanetPieces = 0;
								lblPiecesOnThis.setText("Pieces On This Planet: "+currentPlanetPieces);
								lblPiecesFound.setText("Pieces Found: "+Game.currentPiecesCollected);
								lblPiecesRemainingX.setText("Pieces Remaining: "+(totalPieces-Game.currentPiecesCollected));
								textPane.setText("You found an abandoned ship with a warp drive part inside.");
							} else {
								textPane.setText("You found an abandoned ship with a badly damaged warp drive part inside. It is too old to be of any use to your crew.");
							}
						} else if (randInt2 >= 40 && randInt2 < 60) {
							//Medical cabinet
							textPane.setText("You found an abandoned ship with a" + Game.Shop.getRandomMedicalItem());

						} else if (randInt2 >= 60 && randInt2 < 80) {
							//Ship explodes
							damageShip(75, "Explosion");
							textPane.setText("You found an abandoned ship with a warp drive part inside.");
							
						} else if (randInt2 >= 80 && randInt2 < 98) {
							//Dormant alien kills crew
							killCrew("Dormant");
							textPane.setText("You found a hypersleep pod with an alien hybernating inside. Awakened by your crews intrusion, the alien kills a member of your crew before you can escape back to your ship.");
							
						} else {
							//Bioweapon
							GameEnvironment.openLoseScreen("Bioweapon");
						}
						
					} else { //Ancient Alien Cache
						
						int randInt2 = rand.nextInt(100);
						
						if (randInt2 < 50) {
							//Large scrap reward
							giveScrap("Large");
							lblCredits.setText("Credits: "+playerShip.playerCredits);
							textPane.setText("You stumbled across an Ancient Alien Supply Cache and found a large amount of scrap inside.");
							
						} else if (randInt2 >= 50 && randInt2 < 80) {
							//Small scrap reward
							giveScrap("Small");
							lblCredits.setText("Credits: "+playerShip.playerCredits);
							textPane.setText("You stumbled across an Ancient Alien Supply Cache and found a small amount of scrap inside.");
							
						} else {
							//Part found
							if (Game.pieceFound == 0) {
								Game.addPiece();
								Game.pieceFound = 1;
								currentPlanetPieces = 0;
								lblPiecesOnThis.setText("Pieces On This Planet: "+currentPlanetPieces);
								lblPiecesFound.setText("Pieces Found: "+Game.currentPiecesCollected);
								lblPiecesRemainingX.setText("Pieces Remaining: "+(totalPieces-Game.currentPiecesCollected));
								textPane.setText("You stumbled across an Ancient Alien Supply Cache and found a warp drive part inside.");
								
							} else {
								textPane.setText("You stumbled across an Ancient Alien Supply Cache and found a badly damaged warp drive part inside. It is too old to be of any use to your crew.");
								
							}
						}
						if (Game.currentPiecesCollected == totalPieces) {
							GameEnvironment.openWinScreen();
							frame.setVisible(false);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "You need 2 crew members with an action remaining to fly the ship.", "Game Message: " + "No Actions", JOptionPane.INFORMATION_MESSAGE);					
				}
				
				
				
			}
		});
		btnNewPlanet.setForeground(Color.WHITE);
		btnNewPlanet.setBackground(Color.GRAY);
		btnNewPlanet.setBounds(256, 430, 173, 50);
		frame.getContentPane().add(btnNewPlanet);
		
		if (Game.currentPiecesCollected == totalPieces) {
			GameEnvironment.openWinScreen();
			frame.setVisible(false);
		}
		
	}
}
