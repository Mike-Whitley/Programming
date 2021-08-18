/**
 * @author mkw60
 * This class is used to create the Hashmap holding all items in the game
 *  along with most changes associated with the Hashmap.
 */

package main;

import java.util.*;

public class CurrentItems {
	private static Map<String, Integer> itemStorage = new HashMap<String, Integer>();
	
	
	
	/**
	 * default constructor when the Class is called it fill the hash map with Bandage,Stimpack,Plague Cure,Medicine,Cookie,Space Snack,Sandwich,Space Lunch,Large Dinner,Portable Feast
	 * these all have a default value of zero
	 */
	public CurrentItems(){
		
		itemStorage.put("Bandage", 0);
		itemStorage.put("Stimpack", 0);
		itemStorage.put("Plague Cure", 0);
		itemStorage.put("Medicine", 0);
		itemStorage.put("Cookie", 0);
		itemStorage.put("Space Snack", 0);
		itemStorage.put("Sandwich", 0);
		itemStorage.put("Space Lunch", 0);
		itemStorage.put("Large Dinner", 0);
		itemStorage.put("Portable Feast", 0);
	
	//return itemStorage.get(Items);
	
	}
	
	/**
	 * increases item in hashmap by 1
	 * @param Item
	 * Takes the String of the item that you want to increase the value by in the CurrentItems hashmap
	 */
	public void update(String Item) {
		itemStorage.replace(Item, itemStorage.get(Item) + 1);
	}
	
	
	/**
	 * decreases item in hashmap by 1
	 * @param Item
	 * Takes the String of the item that you want to decrease the value by in the CurrentItems hashmap
	 */
	public void remove(String Item) {
		itemStorage.replace(Item, itemStorage.get(Item) - 1);
	}
	
	
	/**
	 * 
	 * @return returns the name String of the highest value item the crew currently has as items are in order of price or if all are 0 returns NoItem
	 *
	 */
	public String stealItemFromShip() {
		String item = "NoItem";
		
		for ( String key : itemStorage.keySet() ) {     //steals the most expensive item the player has
		    if(itemStorage.get(key) >= 1) {
		    	item = key;
		    }
		}

		return item;
	}
	
	/**
	 * Random generates a number between 1 and 6 that corresponds to one of the food item. It updates the value of that item in the hash map by one
	 * and returns the string of that item 
	 * @return String of the item that has been updated
	 */
	public String getRandomFoodItem() {
		
		Random rand = new Random();
		int randomNum = rand.nextInt(6);
		String ItemFound;
		
		if (randomNum == 0) {
			update("Cookie");
			ItemFound = "Cookie";
			
		} else if (randomNum == 1) {
			update("Space Snack");
			ItemFound = "Space Snack";
			
		} else if (randomNum == 2) {
			update("Sandwich");
			ItemFound = "Sandwich";
			
		} else if (randomNum == 3) {
			update("Space Lunch");
			ItemFound = "Space Lunch";
			
		} else if (randomNum == 4) {
			update("Large Dinner");
			ItemFound = "Large Dinner";
			
		} else {
			update("Portable Feast");
			ItemFound = "Portable Feast";
		}	
		return ItemFound;
	}

	/**
	 * Random generates a number between 1 and 4 that corresponds to one of the Medical item. It updates the value of that item in the hash map by one
	 * and returns the string of that item 
	 * @return String of the item that has been updated
	 */
	
public String getRandomMedicalItem() { 
		
		Random rand = new Random();
		int randomNum = rand.nextInt(4);
		String ItemFound;
		
		if (randomNum == 0) {
			update("Bandage");
			ItemFound =  "Bandage";
			
		} else if (randomNum == 1) {
			update("Stimpack");
			ItemFound = "Stimpack";
			
		} else if (randomNum == 2) {
			update("Plague Cure");
			ItemFound =  "Plague Cure";
			
		} else {
			update("Medicine");
			ItemFound = "Medicine";
		}
		return ItemFound;
	}
	
	/**
	 * 
	 * @param Items
	 * takes the String of Item request from the hashmap e.g. "Bandage", 
	 * @return Returns the value of that item 
	 */
	
	public Integer ItemGetter(String Items) {
		return itemStorage.get(Items);
	}
	
	
	/**
	 * 
	 * @return returns the object of the hashmap itemStorage calling the toString function
	 */
	
	public Map<String, Integer> getInventory() {
		return itemStorage;
		
	}
	
	/**
	 * 
	 * @return returns a formatted string of all the food items and their values held in itemStorage hashmap
	 * in order of cookie, space snack, sandwich, space lunch, large dinner, portable Feast
	 */
	public String getFood() {
		return String.format("<html>Cookie: %s<br/>Space Snack: %s<br/>Sandwich: %s<br/>Space Lunch: %s<br/>Large Dinner: %s<br/>Portable Feast: %s </html>",itemStorage.get("Cookie"),itemStorage.get("Space Snack"),itemStorage.get("Sandwich"),itemStorage.get("Space Lunch"),itemStorage.get("Large Dinner"),itemStorage.get("Portable Feast"));
	}
	
	
	/**
	 * 
	 * @return returns a formatted string of all the medical items and their values held in the hashmap
	 * in order of Bandages, Stimpack, Plague Cure, Medicine
	 */
	public String getMedicine() {
		return String.format("<html>Bandages: %s<br/>Stimpack: %s<br/>Plague Cure: %s<br/>Medicine: %s </html>",itemStorage.get("Bandage"),itemStorage.get("Stimpack"),itemStorage.get("Plague Cure"),itemStorage.get("Medicine"));
	}
	
	
	/**
	 * @return Returns all items and associated variables held in the itemStorage Hashmap
	 */
	public String toString() {
		return String.format("<html>Bandages: %s<br/>Stimpack: %s<br/>Plague Cure: %s<br/>Medicine: %s<br/>Cookie: %s<br/>Space Snack: %s<br/>Sandwich: %s<br/>Space Lunch: %s<br/> "
				+ "LargeDinner: %s<br/> PortableFeast: %s<br/>  <html>",itemStorage.get("Bandage"),itemStorage.get("Stimpack"),itemStorage.get("Plague Cure"),itemStorage.get("Medicine"),
				itemStorage.get("Cookie"),itemStorage.get("Space Snack"),itemStorage.get("Sandwich"),itemStorage.get("Space Lunch"),itemStorage.get("Large Dinner"),itemStorage.get("Portable Feast"));
		
	}
	

}
