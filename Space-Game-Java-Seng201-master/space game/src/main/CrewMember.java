/**
 * This Class Is the Main Classes for creating objects for the Crew in the game containing 
 * all variables used by Crew members
 */

package main;

import main.AlienRaces;

public class CrewMember {
	public static int ability;
	public String race;
	public String name;
	public String tempName;
	
	public int maxHealth = 100;
	public int health = 100;
	public String abilityDesc;
	public int actionsRemaining = 2;
	public double repairMult = 1.0;
	public int hunger = 100;
	public int tiredness = 100;
	public String iconName = "humanicon";
	public boolean isAlive = true;
	public boolean hasPlague = false;
	/**
	 * This is default constructor it is the default for testing purposes when no variable is given for alien name. 
	 */
	
	public CrewMember() {
		tempName = "DEFAULT";
		abilityChooser(AlienRaces.HUMAN);
	}
	
	
	/**
	 * This is called when creating any object of CrewMember with the Race as the String 
	 * defaults for the Human race
	 * @param alienName
	 * 
	 */

	public CrewMember(String alienName) {
		tempName = alienName;
		abilityChooser(AlienRaces.HUMAN);
	}
	/**
	 * Takes a String of name and updates the Name variable
	 * @param Name
	 */
	public void setName(String Name) {
		name = Name;
	}
	
	/**
	 * returns the integer Health from the object of CrewMember usually.
	 * @return health
	 */
	public int getHealth() {
		return health;
	}
	
	
	
	/**
	 * Returns the variable of crew object indicating if the player is alive True or False
	 * @return isAlive
	 * Variable returns as boolean
	 */
	public boolean ifAlive() {
		return isAlive;
	}
	
	
	/**
	 * 
	 * Returns the current objects of CrewMember hunger level this is between 0 and 100, starting value of 100
	 * Returns variable as int
	 * @return hunger
	 */
	public int getHunger() {
		return hunger;
	}
	
	/**
	 * Updates Hunger level of the current object input is an int variable 
	 * 
	 * @param value
	 * 
	 */
	public void reduceHunger(int value) {
		hunger -= value;
	}
	
	public void reduceHealth(int value) {
		health -= value;
	}
	
	/**
	 * returns int
	 * @return Current number of actions remaining for current Crew Member object
	 */
	public int getActions() {
		return actionsRemaining;
	}
	
	/**
	 * This method reduces the current object of crewMember actions by 1
	 */
	public void useAction() {
		actionsRemaining = actionsRemaining -1;
	}
	
	/**
	 * This resets the current object of CrewMember actions to the default starting variable of 2
	 * this variable is an int requires no input. 
	 */
	public void resetActions() {
		 actionsRemaining = 2;
	}


	
	/**
	 * 
	 * @param alienRace
	 * returns the alienRace variables to be used in conjunction with creation of CrewMember objects based on the selected alienRace
	 * This takes input of Enum Alien Race from the ALienRaces File. 
	 */
	public void abilityChooser(AlienRaces alienRace) {
		switch (alienRace) {
			case HUMAN:
				ability = 1;
				race = "Human";
				abilityDesc = "Humans are better at finding valuable scrap than other aliens";
				iconName = "humanicon";
				break;
			case ROBOT:
				ability = 2;
				race = "Robot";
				health = 80;
				maxHealth = health;
				abilityDesc = "Robots repair ships at double the speed of other aliens but deal half as much damage";
				repairMult = 2.0;
				iconName = "roboticon";
				break;
			case SLUG:
				ability = 3;
				race = "Slug";
				health = 80;
				maxHealth = health;
				abilityDesc = "Slugs are better at finding ship components than other aliens";
				iconName = "slugicon";
				break;
			case MANTIS:
				ability = 4;
				race = "Mantis";
				abilityDesc = "Mantis deal 50% more damage than other aliens but repair at half speed";
				repairMult = 0.5;
				iconName = "mantisicon";
				break;
			case ROCK:
				ability = 5;
				race = "Rock";
				health = 150;
				maxHealth = health;
				abilityDesc = "Rock are immune to fire but repair at half speed";
				repairMult = 0.5;
				iconName = "rockicon";
				break;
			case GREENMAN:
				ability = 6;
				race = "Greenman";
				health = 70;
				maxHealth = health;
				abilityDesc = "Greenmen boost the efficiency of ship systems by 50%";
				iconName = "energyicon";
				break;
		}
	} 
/**
 * @Return Returns String of race, Health, Ability description, Hunger, Tiredness, Actions remaining for current object. Does not returns all the variables held in the CrewMember Object
 * 
 */
	public String toString() {
		return String.format("<html>Race: %s<br/>Health: %s<br/>Ability Description: %s<br/>Hunger: %s<br/>Tiredness: %s<br/>Actions Remaining: %s<br/>Space Plague: %s<br/><html>",race,health,abilityDesc,hunger,tiredness,actionsRemaining,hasPlague);
	}
	
	
}
