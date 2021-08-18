package main;

import java.util.ArrayList;

public class Ship {
	
	public ArrayList<CrewMember> crewMembers = new ArrayList<CrewMember>();
	private ArrayList<String> crewNames;
	public String shipname;
	public int shipHealth = 100;
	public int playerCredits = 200;
	public int shipShield = 100;
	
	public Ship() {
		crewNames = new ArrayList<String>();
		//System.out.print(crew.ReturnCrew());
	}
	
	/**
	 * determines if the player will lose because the team doesn't have enough players alive.
	 * @return returns boolean true or false
	 */
	public boolean checkIfDeadFromHunger() {
		// loops through every character if any of their health is =< 0
		// if it is display lose screen
		
		boolean dead = false;
		int aliveCount = 0;

		for (CrewMember crew: crewMembers) {
			if(crew.getHunger() > 0 ) {
				aliveCount++;
			} else {
				crew.isAlive = false;
			}
		}
		if (aliveCount < 2) {
			dead = true;
		}
		return dead;
	}
	
	/**
	 * 
	 * @return returns the ships shield level
	 */
	public int getShieldLevel() {
		return shipShield;
	}
	
	/**
	 * 
	 * @param level
	 * Takes the number you want to set the ship level to, takes an int value
	 * 
	 */
	public void setShieldLevel(int level) {
		shipShield = level;
	}
	
	
	/**
	 * 
	 * @return returns the CrewMembers Object
	 */
	public ArrayList<CrewMember> getCrewMembers() {
		return crewMembers;
	}
	
	/**
	 * 
	 * @return returns the players cash / credits they current have. 
	 */
	public int getCredits() {
		return playerCredits;
	}
	

	
	/**
	 * 
	 * @param Value
	 * Takes a value that you want to increase the players credit / cash amount by
	 */
	public void changeCredits(int Value) {
		playerCredits = playerCredits + Value;
	}
	
	
	
	/**
	 * 
	 * @return returns the health of the ship.
	 */
	public int getShipHealth() {
		return shipHealth;
	}
	
	/**
	 * 
	 * @param Value
	 * takes input int of amount you want to increase your sheild health by.
	 * increases the ships health by input amount.
	 */
	public void updateShipHealth(int Value) {
		shipHealth += Value;
	}
	
	public void updateShipShield(int Value) {
		shipShield += Value;
	}
	
	/**
	 * 
	 * @param crew
	 * takes an object of CrewMember and adds it to the CrewMember Array
	 */
	public void addCrewMember(CrewMember crew) {
		crewMembers.add(crew);
	}
	
	/**
	 * Clears all the objects held in the CrewMembers array
	 */
	public void clearCrewMembers() {
		crewMembers.clear();
	}

	/**
	 * 
	 * @param name
	 * takes input string, the name you want to set as your ship name
	 */

	public void shipName(String name) {
		shipname = name;
	}
	
	/**
	 * 
	 * @return returns the name of the players ship
	 */
	public String returnShipName() {
		return shipname;
	}
	
	/**
	 * 
	 * @return returns the ArrayList of crewMembers and all the objects held in this array.
	 */
	public ArrayList<CrewMember> ReturnCrew() {     
		return crewMembers;
	}
	
	/**
	 * 
	 * @param name
	 * takes the input string of a name and adds it to the crewNames Arraylist
	 */
	public void addName(String name) {
		crewNames.add(name);
	}
	
	/**
	 * 
	 * @return returns the length of crewMembers object as to how many objects there are.
	 */
	public int crewLength(){
		return crewMembers.size();
	}
	/**
	 * 
	 * @param index
	 * takes index value of the object you want to be returned 
	 * @return returns the crewMember object at the index given.
	 */

	
	public String returnIndexValue(int index) {
		return crewMembers.get(index).toString();
	}

	/**
	 * Clears the arraylist of the names held in crewNames
	 */
	public void clearTeamNames() {
		crewNames.clear();
	}
	
	/**
	 * 
	 * @param Member
	 *  takes a string of any name
	 * @return returns boolean true or false if the input string is in the crewNames
	 */
	
	public boolean returnCrewNames(String Member) {
		if (crewNames.contains(Member)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns a string containing all the objects held in crewMembers
	 */
	public String toString() {
		String out = "";
		for (int i = 0; i < crewMembers.size(); i++) {
			out += crewMembers.get(i) + " ";;
		}
		//System.out.println(crewMembers.size());
		return out;
	}
	
	public static void main(String[] args) {

	}

	
}
