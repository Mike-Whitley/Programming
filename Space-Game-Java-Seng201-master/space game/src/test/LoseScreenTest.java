package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.GameEnvironment;
import main.LoseScreen;

public class LoseScreenTest {

	@Test
	public void lostReasonTest() {
		LoseScreen lostnoreason = new LoseScreen("");
		assertEquals("", lostnoreason.getText()); //default no reason given.
		
		LoseScreen lostTime = new LoseScreen("Time");
		assertEquals("Your crew ran out of time and the warp drive is now damaged beyond repair.", lostTime.getText()); // hunger death
		
		LoseScreen loststarvation = new LoseScreen("Hunger");
		assertEquals("Your crew died of starvation.", loststarvation.getText()); // hunger death
		
		LoseScreen lostBioWeapon = new LoseScreen("Bioweapon");
		assertEquals("Your entire crew was wiped out by an alien bioweapon.", lostBioWeapon.getText()); //bio death

		LoseScreen lostPlague = new LoseScreen("Plague");
		assertEquals("Your crew died of the Space Plague.", lostPlague.getText()); //plague death

		LoseScreen lostAsteroids = new LoseScreen("Asteroids");
		assertEquals("Your ship was destroyed by asteroids.", lostAsteroids.getText()); //astroid death

		LoseScreen lostPirates = new LoseScreen("Pirates");
		assertEquals("Your crew was killed by space pirates.", lostPirates.getText()); //pirate death
		
		LoseScreen lostExplosion = new LoseScreen("Explosion");
		assertEquals("Your ship was destroyed by a nearby explosion.", lostExplosion.getText()); 
		
		LoseScreen lostDormant = new LoseScreen("Dormant");
		assertEquals("Your crew was killed by an angrily awakened alien.", lostDormant.getText()); 
		
		
	}

}
