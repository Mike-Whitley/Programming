/**
 * @author mkw60
 * this class is a utility class that helps with caching images
 * 
 */
package main;

import javax.swing.ImageIcon;

public class Utilities {
	public Utilities(){
		
	}
	/**
	 * 
	 * @param imageName
	 * @return returns the cached image in memory as icon 
	 * This Method is a utility method to set all images within the game if used outside of a button.
	 */
	
	public ImageIcon getImage(String imageName) {
		ImageIcon icon = null;
		//return new ImageIcon(TeamSelection.class.getResource("/Images/energyicon.png"));
		icon = new ImageIcon(TeamSelection.class.getResource("/Images/"+ imageName + ".png"));
		return icon;
	}
}
