package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.CurrentItems;
import main.StoryScreen;

public class StoryScreenTest {

	//unable to test if the correct variable is collected from the GUI slider so will manually pass value through to test
	// No further tests required from this screen as its a single GUI slider
	/**
	 * Tests that based on days the number of pieces is correct. 
	 */
	@Test
	public void PiecesCalculationTest() {
		StoryScreen Story = new StoryScreen();
		Story.DaySetter(5);
		assertEquals(5, Story.DayGetter()); //tests variable is stored correctly before calculation 
		assertEquals(3, Story.getNumPieces());

		
	}

}
