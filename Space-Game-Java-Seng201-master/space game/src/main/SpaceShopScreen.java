package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class SpaceShopScreen {

	public JFrame frame;
	private GameEnvironment Game;
	private Ship playerShip = GameEnvironment.getPlayerShip();
	private int price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpaceShopScreen window = new SpaceShopScreen(new GameEnvironment());
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
	public SpaceShopScreen() {
		initialize();
	}
	
	public SpaceShopScreen(GameEnvironment Game) {
		this.Game = Game; //this is needed to create local variable
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
		
		final JLabel lblItems = new JLabel(Game.Shop.toString());
		lblItems.setForeground(Color.WHITE);
		lblItems.setBounds(320, 100, 240, 240);
		lblItems.setVerticalAlignment(SwingConstants.TOP);
		frame.getContentPane().add(lblItems);
		

		final JLabel lblMoneyXCredits = new JLabel("Money: "+playerShip.getCredits());
		lblMoneyXCredits.setForeground(Color.WHITE);
		lblMoneyXCredits.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		lblMoneyXCredits.setBounds(320, 10, 200, 20);
		frame.getContentPane().add(lblMoneyXCredits);
		
		JLabel lblCurrentItems_1 = new JLabel("Current Items:");
		lblCurrentItems_1.setForeground(Color.WHITE);
		lblCurrentItems_1.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		lblCurrentItems_1.setBounds(320, 60, 200, 20);
		frame.getContentPane().add(lblCurrentItems_1);
		
		JButton btnCloseShop = new JButton("Close Shop");
		btnCloseShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				GameEnvironment.openStatusScreen();
				frame.setVisible(false);
			}
		});
		btnCloseShop.setBackground(Color.GRAY);
		btnCloseShop.setForeground(Color.WHITE);
		btnCloseShop.setBounds(520, 450, 120, 30);
		frame.getContentPane().add(btnCloseShop);
		
		
		JLabel lblItemsForSale = new JLabel("Items for sale:");
		lblItemsForSale.setForeground(Color.WHITE);
		lblItemsForSale.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		lblItemsForSale.setBounds(30, 10, 200, 20);
		frame.getContentPane().add(lblItemsForSale);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Healing Items", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel_1.setBounds(10, 50, 295, 175);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnStemPack = new JButton("Stimpack: 30 credits");
		btnStemPack.setToolTipText("Restores 50 points of Health");
		btnStemPack.setBounds(6, 56, 280, 30);
		panel_1.add(btnStemPack);
		btnStemPack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price = 30;
				if (playerShip.getCredits() < price) {
					
				} else {
					playerShip.changeCredits(-1*price);
					Game.Shop.update("Stimpack");
					lblMoneyXCredits.setText("Money: " + playerShip.getCredits());
				}
				lblItems.setText(Game.Shop.toString());
			}
		});
		btnStemPack.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnStemPack.setForeground(Color.WHITE);
		btnStemPack.setBackground(Color.GRAY);
		
		JButton btnMedicine = new JButton("Medicine: 60 credits");
		btnMedicine.setToolTipText("Restores 100 points of Health");
		btnMedicine.setBounds(6, 136, 280, 30);
		panel_1.add(btnMedicine);
		btnMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price = 60;
				if (playerShip.getCredits() < price) {
					
				} else {
					playerShip.changeCredits(-1*price);
					Game.Shop.update("Medicine");
					lblMoneyXCredits.setText("Money: " + playerShip.getCredits());
				}
				lblItems.setText(Game.Shop.toString());
			}
		});
		btnMedicine.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnMedicine.setForeground(Color.WHITE);
		btnMedicine.setBackground(Color.GRAY);
		
		JButton btnPlagueCure = new JButton("Plague Cure: 30 credits");
		btnPlagueCure.setToolTipText("Cures the Space Plague");
		btnPlagueCure.setBounds(6, 96, 280, 30);
		panel_1.add(btnPlagueCure);
		btnPlagueCure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price = 30;
				if (playerShip.getCredits() < price) {
					
				} else {
					playerShip.changeCredits(-1*price);
					Game.Shop.update("Plague Cure");
					lblMoneyXCredits.setText("Money: " + playerShip.getCredits());
				}
				lblItems.setText(Game.Shop.toString());
			}
		});
		btnPlagueCure.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnPlagueCure.setForeground(Color.WHITE);
		btnPlagueCure.setBackground(Color.GRAY);
		
		JButton btnBandage = new JButton("Bandage: 10 credits");
		btnBandage.setToolTipText("Restores 25 points of Health");
		btnBandage.setBounds(6, 16, 280, 30);
		panel_1.add(btnBandage);
		btnBandage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price = 10;
				if (playerShip.getCredits() < price) {
					
				} else {
					playerShip.changeCredits(-1*price);
					Game.Shop.update("Bandage");
					lblMoneyXCredits.setText("Money: " + playerShip.getCredits());
				}
				lblItems.setText(Game.Shop.toString());
				
			}
		});
		btnBandage.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnBandage.setForeground(Color.WHITE);
		btnBandage.setBackground(Color.GRAY);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.BLACK);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Food Items", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setBounds(10, 230, 295, 255);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnCookie = new JButton("Cookie: 10 credits");
		btnCookie.setToolTipText("Restores 10 points of Hunger");
		btnCookie.setBounds(6, 16, 280, 30);
		panel.add(btnCookie);
		btnCookie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price = 10;
				if (playerShip.getCredits() < price) {
					
				} else {
					playerShip.changeCredits(-1*price);
					Game.Shop.update("Cookie");
					lblMoneyXCredits.setText("Money: " + playerShip.getCredits());
				}
				lblItems.setText(Game.Shop.toString());
			}
		});
		btnCookie.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnCookie.setForeground(Color.WHITE);
		btnCookie.setBackground(Color.GRAY);
		
		JButton btnSpaceSnack = new JButton("Space Snack: 20 credits");
		btnSpaceSnack.setToolTipText("Restores 20 points of Hunger");
		btnSpaceSnack.setBounds(6, 56, 280, 30);
		panel.add(btnSpaceSnack);
		btnSpaceSnack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price = 20;
				if (playerShip.getCredits() < price) {
					
				} else {
					playerShip.changeCredits(-1*price);
					Game.Shop.update("Space Snack");
					lblMoneyXCredits.setText("Money: " + playerShip.getCredits());
				}
				lblItems.setText(Game.Shop.toString());
			}
		});
		btnSpaceSnack.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnSpaceSnack.setForeground(Color.WHITE);
		btnSpaceSnack.setBackground(Color.GRAY);
		
		JButton btnSandwich = new JButton("Sandwich: 30 credits");
		btnSandwich.setToolTipText("Restores 30 points of Hunger");
		btnSandwich.setBounds(6, 96, 280, 30);
		panel.add(btnSandwich);
		btnSandwich.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				price = 30;
				if (playerShip.getCredits() < price) {
					
				} else {
					playerShip.changeCredits(-1*price);
					Game.Shop.update("Sandwich");
					lblMoneyXCredits.setText("Money: " + playerShip.getCredits());
				}
				lblItems.setText(Game.Shop.toString());
			}
		});
		btnSandwich.setForeground(Color.WHITE);
		btnSandwich.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnSandwich.setBackground(Color.GRAY);
		
		JButton btnSpaceLunch = new JButton("Space Lunch: 50 credits");
		btnSpaceLunch.setToolTipText("Restores 50 points of Hunger");
		btnSpaceLunch.setBounds(6, 136, 280, 30);
		panel.add(btnSpaceLunch);
		btnSpaceLunch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				price = 50;
				if (playerShip.getCredits() < price) {
					
				} else {
					playerShip.changeCredits(-1*price);
					Game.Shop.update("Space Lunch");
					lblMoneyXCredits.setText("Money: " + playerShip.getCredits());
				}
				lblItems.setText(Game.Shop.toString());

			}
		});
		btnSpaceLunch.setForeground(Color.WHITE);
		btnSpaceLunch.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnSpaceLunch.setBackground(Color.GRAY);
		
		JButton btnLargeDinner = new JButton("Large Dinner: 75 credits");
		btnLargeDinner.setToolTipText("Restores 75 points of Hunger");
		btnLargeDinner.setBounds(6, 176, 280, 30);
		panel.add(btnLargeDinner);
		btnLargeDinner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				price = 75;
				if (playerShip.getCredits() < price) {
					
				} else {
					playerShip.changeCredits(-1*price);
					Game.Shop.update("Large Dinner");
					lblMoneyXCredits.setText("Money: " + playerShip.getCredits());
				}
				lblItems.setText(Game.Shop.toString());

			}
			
		});
		btnLargeDinner.setForeground(Color.WHITE);
		btnLargeDinner.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnLargeDinner.setBackground(Color.GRAY);
		
		JButton btnPortableFeast = new JButton("Portable Feast: 100 credits");
		btnPortableFeast.setToolTipText("Restores 100 points of Hunger");
		btnPortableFeast.setBounds(6, 216, 280, 30);
		panel.add(btnPortableFeast);
		btnPortableFeast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				price = 100;
				if (playerShip.getCredits() < price) {
					
				} else {
					playerShip.changeCredits(-1*price);
					Game.Shop.update("Portable Feast");
					lblMoneyXCredits.setText("Money: " + playerShip.getCredits());
				}
				lblItems.setText(Game.Shop.toString());

			}
		});
		btnPortableFeast.setForeground(Color.WHITE);
		btnPortableFeast.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnPortableFeast.setBackground(Color.GRAY);
		

	

	}
}
