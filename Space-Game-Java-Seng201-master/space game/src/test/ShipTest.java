package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;

import org.junit.Test;

import main.AlienRaces;
import main.CrewMember;

import main.Ship;

public class ShipTest {

	
	public CrewMember crewHuman;
	public CrewMember crewRobot;
	public CrewMember crewSlug;
	public CrewMember crewMantis;
	public CrewMember crewgreenman;
	public CrewMember crewRock;
	public ArrayList<CrewMember> crewMembers = new ArrayList<CrewMember>();
	public Ship playerShip = new Ship();
	
	
	
	/**
	 * Initilize a crew object for each individual possible crew member and add them to an array list
	 */
	
	@Before
	public void initilizeCrewObjects() {
		
		crewHuman = new CrewMember("Human");
		crewHuman.abilityChooser(AlienRaces.HUMAN);
		playerShip.addCrewMember(crewHuman);
		
		crewRobot = new CrewMember("Robot");
		crewRobot.abilityChooser(AlienRaces.ROBOT);
		playerShip.addCrewMember(crewRobot);
		
		crewSlug = new CrewMember("Slug");
		crewSlug.abilityChooser(AlienRaces.SLUG);
		playerShip.addCrewMember(crewSlug);
		
		crewMantis = new CrewMember("Mantis");
		crewMantis.abilityChooser(AlienRaces.MANTIS);
		playerShip.addCrewMember(crewMantis);
		
		crewgreenman = new CrewMember("Greenman");
		crewgreenman.abilityChooser(AlienRaces.GREENMAN);
		playerShip.addCrewMember(crewgreenman);
		
		crewRock = new CrewMember("Rock");
		crewRock.abilityChooser(AlienRaces.ROCK);
		playerShip.addCrewMember(crewRock);

	}
	
	
	
	/**
	 * 
	 */
	
	@Test
	public void hungerTest() {
		
		assertEquals(false, playerShip.checkIfDeadFromHunger()); // if this is true we can determine that the list is clear
		// change health to zero and and re test. 
	
	}

	
	@Test
	public void shipcrewTest() {
		String humanTest = "<html>Race: Human<br/>Health: 100<br/>Ability Description: Humans are better at finding "
				+ "valuable scrap than other aliens<br/>Hunger: 100<br/>Tiredness: "
				+ "100<br/>Actions Remaining: 2<br/>Space Plague: false<br/><html>" + 
				"";
		assertEquals(playerShip.returnIndexValue(0), humanTest);
		playerShip.clearCrewMembers();

		assertEquals(0, playerShip.crewLength()); // if this is true we can determine that the list is clear
//		playerShip.update("");
//

	
	}


	@Test
	public void checkCreditTest() {
		
		assertEquals(200, playerShip.getCredits());
		playerShip.changeCredits(200);
		assertEquals(400, playerShip.getCredits());
	}

	
	@Test
	public void shieldTest() {
		playerShip.setShieldLevel(50);
		assertEquals(50, playerShip.getShieldLevel());
		
	}
	
	@Test
	public void nameTest() {
		playerShip.addName("jake");;
		assertEquals(true, playerShip.returnCrewNames("jake"));
		
		playerShip.clearTeamNames();
		assertEquals(false, playerShip.returnCrewNames("jake"));
		
	}
	
	
	@Test
	public void shipHealthTest() {
		assertEquals(100, playerShip.getShipHealth());
		playerShip.updateShipHealth(50);
		assertEquals(150, playerShip.getShipHealth());
	
	}
	
	
	
	
	
	
	
	

}

