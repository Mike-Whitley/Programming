package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.AlienRaces;
import main.CrewMember;

public class CrewMemberTest {
	
	
	public CrewMember crewHuman;
	public CrewMember crewRobot;
	public CrewMember crewSlug;
	public CrewMember crewMantis;
	public CrewMember crewgreenman;
	public CrewMember crewRock;
	

	@Before
	public void initilizeCrewObjects() {
		crewHuman = new CrewMember("Human");
		crewHuman.abilityChooser(AlienRaces.HUMAN);
		
		crewRobot = new CrewMember("Robot");
		crewRobot.abilityChooser(AlienRaces.ROBOT);
		
		crewSlug = new CrewMember("Slug");
		crewSlug.abilityChooser(AlienRaces.SLUG);
		
		crewMantis = new CrewMember("Mantis");
		crewMantis.abilityChooser(AlienRaces.MANTIS);
		
		crewgreenman = new CrewMember("Greenman");
		crewgreenman.abilityChooser(AlienRaces.GREENMAN);
		
		crewRock = new CrewMember("Rock");
		crewRock.abilityChooser(AlienRaces.ROCK);


		
		
		
	}
	
	@Test
	public void humanCreationTest() {
		String Initialcreation = "<html>Race: Human<br/>Health: 100<br/>Ability Description: Humans are better at "
				+ "finding valuable scrap than other aliens<br/>Hunger: 100<br/>Tiredness: 100<br/>Actions "
				+ "Remaining: 2<br/>Space Plague: false<br/><html>" + 
				"";
		assertEquals(Initialcreation, crewHuman.toString());

	 }

	
	@Test
	public void robotCreationTest() {
		String Initialcreation = "<html>Race: Robot<br/>Health: 80<br/>Ability Description: Robots repair ships "
				+ "at double the speed of other aliens but deal half as much damage<br/>Hunger: 100<br/>Tiredness: "
				+ "100<br/>Actions Remaining: 2<br/>Space Plague: false<br/><html>" + 
				"";
		assertEquals(Initialcreation, crewRobot.toString());

	}
		
		@Test
		public void slugCreationTest() {
			String Initialcreation = "<html>Race: Slug<br/>Health: 80<br/>Ability Description: Slugs are better at "
					+ "finding ship components than other aliens<br/>Hunger: 100<br/>Tiredness: 100<br/>Actions "
					+ "Remaining: 2<br/>Space Plague: false<br/><html>" + 
					"";
			assertEquals(Initialcreation, crewSlug.toString());

		 }
	
		
		
		@Test
		public void mantisCreationTest() {
			String Initialcreation = "<html>Race: Mantis<br/>Health: 100<br/>Ability Description: Mantis deal 50% "
					+ "more damage than other aliens but repair at half speed<br/>Hunger: 100<br/>Tiredness: "
					+ "100<br/>Actions Remaining: 2<br/>Space Plague: false<br/><html>" + 
					"";
			assertEquals(Initialcreation, crewMantis.toString());

		 }
		
		
		@Test
		public void greenmanCreationTest() {
			String Initialcreation = "<html>Race: Greenman<br/>Health: 70<br/>Ability Description: Greenmen boost "
					+ "the efficiency of ship systems by 50%<br/>Hunger: 100<br/>Tiredness: 100<br/>Actions"
					+ " Remaining: 2<br/>Space Plague: false<br/><html>" + 
					"";
			assertEquals(Initialcreation, crewgreenman.toString());

		 }
	
		@Test
		public void rockCreationTest() {
			String Initialcreation = "<html>Race: Rock<br/>Health: 150<br/>Ability Description: Rock are immune "
					+ "to fire but repair at half speed<br/>Hunger: 100<br/>Tiredness: 100<br/>Actions Remaining:"
					+ " 2<br/>Space Plague: false<br/><html>" + 
					"";
				assertEquals(Initialcreation, crewRock.toString());

		 }
		
		@Test
		public void healthTest() {
			assertEquals(70, crewgreenman.getHealth()); // Tests initial health
			crewgreenman.reduceHealth(50);
			assertEquals(20, crewgreenman.getHealth());

		}
		
		@Test
		public void hungerTest() {
			assertEquals(100, crewgreenman.getHunger()); // Tests initial hunger	
			crewgreenman.reduceHunger(25); //reduces hunger testing
			assertEquals(75, crewgreenman.getHunger());

		}
		
		

		@Test
		public void actionsTest() {

			assertEquals(2, crewgreenman.getActions()); // Testing creation when 2 actions
			crewgreenman.useAction();  // tests reducing action points by 1
			assertEquals(1, crewgreenman.getActions());
			crewgreenman.useAction();
			assertEquals(0, crewgreenman.getActions());
			crewgreenman.useAction();
			assertEquals((-1), crewgreenman.getActions());
			crewgreenman.resetActions(); // testing actions reset when called
			assertEquals((2), crewgreenman.getActions());

		}
		
		
		@Test
		public void setNameTest() {
			crewRobot.setName("Jake");
			assertEquals("Jake", crewRobot.name);

		}
		
		
		@Test
		public void aliveTest() {
			assertEquals(true, crewRobot.ifAlive());

		}
		
		
		
		
		
		
		
		
	
		
	
}
