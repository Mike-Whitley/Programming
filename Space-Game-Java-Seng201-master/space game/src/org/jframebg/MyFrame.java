package org.jframebg;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.border.EmptyBorder;  
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class MyFrame extends JFrame{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setVisible(true); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel() {;
			public void paintComponent(Graphics g) {  
				Image img = Toolkit.getDefaultToolkit().getImage(
						MyFrame.class.getResource("shutterstock_295846730.jpg"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));  
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

	}

}
