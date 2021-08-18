package main;


import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class TeamSelection {
	
	public CrewMember currentPick = new CrewMember("Human");
	public JFrame frame;
	private GameEnvironment Game;
	private JTextField heroNameInsert;
	private JTextField textField;
	private Ship playerShip = GameEnvironment.getPlayerShip();
	public int currentClass = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamSelection window = new TeamSelection(new GameEnvironment());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param Game 
	 * 
	 */
	public TeamSelection(GameEnvironment Game) {
		this.Game = Game;
		initialize();
		
	
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 1000, 800);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JPanel HeroinfoPanel = new JPanel();
		HeroinfoPanel.setForeground(Color.WHITE);
		HeroinfoPanel.setBackground(Color.GRAY);
		HeroinfoPanel.setBounds(500, 100, 440, 340);
		frame.getContentPane().add(HeroinfoPanel);
		HeroinfoPanel.setLayout(null);
		
		
		final JLabel HeroDisplayPicture = new JLabel("*");
		HeroDisplayPicture.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/humanicon.png")));
		HeroDisplayPicture.setForeground(Color.PINK);
		HeroDisplayPicture.setBackground(Color.PINK);
		HeroDisplayPicture.setBounds(10, 11, 129, 126);
		HeroinfoPanel.add(HeroDisplayPicture);
		
		final JLabel HeroDiscription = new JLabel("<html>Race: Human<br/> health: 100<br/>  Ability Description: Humans are better at finding credits than other aliens<br/>  stamina: 100<br/><html>");
		HeroDiscription.setVerticalAlignment(SwingConstants.TOP);
		HeroDiscription.setBounds(151, 8, 277, 320);
		HeroinfoPanel.add(HeroDiscription);
		
		
		final Utilities utils = new Utilities();
		
		final JButton HumanButton = new JButton("New button");
		HumanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroDisplayPicture.setIcon( utils.getImage("humanicon"));
				currentClass = 0;
				CrewMember c = makeObjects();
				HeroDiscription.setText(c.toString());
			}
		});
		//HumanButton.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/humanicon.png")));
		HumanButton.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/humanicon.png")));
		HumanButton.setBounds(33, 95, 130, 165);
		frame.getContentPane().add(HumanButton);
		
		final JButton MantisButton = new JButton("New button");
		MantisButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HeroDisplayPicture.setIcon( utils.getImage("mantisicon"));
				currentClass = 1;
				CrewMember c = makeObjects();
				HeroDiscription.setText(c.toString());
			}
		});
		MantisButton.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/mantisicon.png")));
		MantisButton.setBounds(174, 95, 130, 165);
		frame.getContentPane().add(MantisButton);
		
		final JButton RobotButton = new JButton("New button");
		RobotButton.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/roboticon.png")));
		RobotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroDisplayPicture.setIcon( utils.getImage("roboticon"));
				currentClass = 2;
				CrewMember c = makeObjects();
				HeroDiscription.setText(c.toString());
			}
		});
		RobotButton.setBounds(316, 95, 130, 165);
		frame.getContentPane().add(RobotButton);
		
		JButton RockmanButton = new JButton();
		RockmanButton.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/rockicon.png")));
		RockmanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroDisplayPicture.setIcon( utils.getImage("rockicon"));
				currentClass = 3;
				CrewMember c = makeObjects();
				HeroDiscription.setText(c.toString());
			}
		});
		RockmanButton.setBounds(33, 271, 130, 165);
		frame.getContentPane().add(RockmanButton);
		
		final JButton SlugButton = new JButton();
		SlugButton.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/slugicon.png")));
		SlugButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroDisplayPicture.setIcon( utils.getImage("slugicon"));
				currentClass = 4;
				CrewMember c = makeObjects();
				HeroDiscription.setText(c.toString());
			}
		});
		SlugButton.setBounds(174, 271, 130, 165);
		frame.getContentPane().add(SlugButton);

		final JButton EnergyManButton = new JButton();
		EnergyManButton.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/energyicon.png")));
		EnergyManButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroDisplayPicture.setIcon( utils.getImage("energyicon"));
				currentClass = 5;
				CrewMember c = makeObjects();
				HeroDiscription.setText(c.toString());
			}
		});
		EnergyManButton.setBounds(316, 271, 130, 165);
		frame.getContentPane().add(EnergyManButton);

	
		JLabel lblPickUpTo = new JLabel("Pick up to four unique heroes!");
		lblPickUpTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPickUpTo.setForeground(Color.WHITE);
		lblPickUpTo.setBackground(Color.BLACK);
		lblPickUpTo.setFont(new Font("Courier 10 Pitch", Font.PLAIN, 44));
		lblPickUpTo.setBounds(66, 27, 831, 54);
		frame.getContentPane().add(lblPickUpTo);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(90, 522, 792, 165);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		final JLabel heroOne = new JLabel("");
		heroOne.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/noheroicon.png")));
		heroOne.setBounds(12, 0, 175, 165);
		heroOne.setForeground(Color.PINK);
		heroOne.setBackground(Color.PINK);
		panel.add(heroOne);
		
		final JLabel heroTwo = new JLabel("");
		heroTwo.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/noheroicon.png")));
		heroTwo.setForeground(Color.PINK);
		heroTwo.setBackground(Color.PINK);
		heroTwo.setBounds(199, 0, 175, 165);
		panel.add(heroTwo);
		
		final JLabel heroThree = new JLabel("");
		heroThree.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/noheroicon.png")));
		heroThree.setForeground(Color.PINK);
		heroThree.setBackground(Color.PINK);
		heroThree.setBounds(398, 0, 175, 165);
		panel.add(heroThree);
		
		final JLabel heroFour = new JLabel("");
		heroFour.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/noheroicon.png")));
		heroFour.setForeground(Color.PINK);
		heroFour.setBackground(Color.PINK);
		heroFour.setBounds(594, 0, 175, 165);
		panel.add(heroFour);
		
		

		
		JButton AddToTeam = new JButton("Add to team");
		AddToTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				
				if (GameEnvironment.getTeam().crewLength() > 3) {
					JOptionPane.showMessageDialog(null, "Your team already has four heroes, click to continue or reset your hero list to choose again!", "Game Message: " + "Too many heroes picked!", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (heroNameInsert.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Your hero needs a name - in the text box provided enter a name for your hero", "Game Message: " + "The Hero with no name", JOptionPane.INFORMATION_MESSAGE);
					} else {
						currentPick.setName(heroNameInsert.getText());
						playerShip.getCrewMembers().add(currentPick);
						GameEnvironment.getTeam().addCrewMember(currentPick); //adds the currently selected crew member to the team
						GameEnvironment.getTeam().addName("Current Team " + heroNameInsert.getText()); 
						
						CrewMember c = makeObjects();
						HeroDiscription.setText(c.toString());
						
					}
				}
				if (GameEnvironment.getTeam().crewLength() == 1) {
//					heroOne.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/" + GameEnvironment.getTeam().returnIndexValue(0) +".png")));
					heroOne.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/" + currentPick.iconName +".png")));

				} 
				if(GameEnvironment.getTeam().crewLength() == 2){
					heroTwo.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/" + currentPick.iconName +".png")));	
				}
				if(GameEnvironment.getTeam().crewLength() == 3){
					heroThree.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/" + currentPick.iconName +".png")));	
				}
				if(GameEnvironment.getTeam().crewLength() == 4){
					heroFour.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/" + currentPick.iconName +".png")));	
				}
				
			}
		});
		AddToTeam.setForeground(Color.WHITE);
		AddToTeam.setBackground(Color.GRAY);
		AddToTeam.setBounds(500, 450, 440, 60);
		frame.getContentPane().add(AddToTeam);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameEnvironment.setShipName(textField.getText());
				if (GameEnvironment.getTeam().crewLength() > 1 && textField.getText().isEmpty() != true) {
					GameEnvironment.openStatusScreen();
					frame.setVisible(false);
				} else if (GameEnvironment.getTeam().crewLength() < 2) {
					JOptionPane.showMessageDialog(null, "Your team must have at least 2 heroes", "Game Messagse: " + "Too few heroes picked!", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Your ship must have a name", "Game Message: " + "No Ship Name!", JOptionPane.INFORMATION_MESSAGE);
				}
					
			}
		});
		btnContinue.setForeground(Color.WHITE);
		btnContinue.setBackground(Color.GRAY);
		btnContinue.setBounds(697, 699, 279, 57);
		frame.getContentPane().add(btnContinue);
		
		JButton resetTeam = new JButton("Reset Team");
		resetTeam.setBackground(Color.GRAY);
		resetTeam.setForeground(Color.WHITE);
		resetTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				playerShip.clearCrewMembers();
				heroOne.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/noheroicon.png")));
				heroTwo.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/noheroicon.png")));
				heroThree.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/noheroicon.png")));
				heroFour.setIcon(new ImageIcon(TeamSelection.class.getResource("/Images/noheroicon.png")));
				 GameEnvironment.getTeam().clearCrewMembers();
				 GameEnvironment.getTeam().clearTeamNames();
			}
		});
		resetTeam.setBounds(33, 704, 155, 52);
		frame.getContentPane().add(resetTeam);
		
		heroNameInsert = new JTextField();
		heroNameInsert.setBounds(200, 460, 280, 35);
		frame.getContentPane().add(heroNameInsert);
		heroNameInsert.setColumns(10);
		
		JLabel lblHerosName = new JLabel("Input Hero's Name:");
		lblHerosName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHerosName.setForeground(Color.WHITE);
		lblHerosName.setBounds(10, 450, 178, 52);
		frame.getContentPane().add(lblHerosName);
		
		textField = new JTextField();
		textField.setBounds(400, 710, 280, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblInputShipssName = new JLabel("Input Ships's Name:");
		lblInputShipssName.setForeground(Color.WHITE);
		lblInputShipssName.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblInputShipssName.setBounds(210, 699, 178, 52);
		frame.getContentPane().add(lblInputShipssName);
	
	}
	
	/**
	 * 
	 * @return returns the object depends on currentClass number for the alien races of human, mantis, robot, rock, slug, greenman
	 */
	
	public CrewMember makeObjects() {
		if (currentClass == 0) {
			CrewMember c = new CrewMember("Human");
			c.abilityChooser(AlienRaces.HUMAN);
			currentPick = c;
		}
		
		if (currentClass == 1) {
			CrewMember c = new CrewMember("Mantis");
			c.abilityChooser(AlienRaces.MANTIS);
			currentPick = c;
		}
		if (currentClass == 2) {
			CrewMember c = new CrewMember("roboticon");
			c.abilityChooser(AlienRaces.ROBOT);
			currentPick = c;
		}
		if (currentClass == 3) {
			CrewMember c = new CrewMember("Rock");
			c.abilityChooser(AlienRaces.ROCK);
			currentPick = c;
		}
		if (currentClass == 4) {
			CrewMember c = new CrewMember("Slug");
			c.abilityChooser(AlienRaces.SLUG);
			currentPick = c;
		}
		if (currentClass == 5) {
			CrewMember c = new CrewMember("Energy");
			c.abilityChooser(AlienRaces.GREENMAN);
			currentPick = c;
		}
		
		
		
		return currentPick;

	}
	
	
	
	
}
