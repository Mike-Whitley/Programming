package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.GameEnvironment;

public class GameEnvironmentTest {

	
	@Test
	public void gamePiecesTest() {
		//assertEquals(0, GameEnvironment.getCurrentPiecesCollected()); //initilize game as having collected 0 pieces 
		// when funcionality for adding a game piece is added test.
		assertEquals(6, GameEnvironment.getNumPieces());
		GameEnvironment.setNumPieces(2); //tests value changing
		assertEquals(2, GameEnvironment.getNumPieces()); // confirmed change of value
	}
	
	
	@Test
	public void checkDays() {
		
		assertEquals(10, GameEnvironment.getNumSimulationDays()); //makes sure num of days start at null
		GameEnvironment.reduceDay();
		assertEquals(9, GameEnvironment.getNumSimulationDays()); 
		GameEnvironment.setNumSimulationDays(6);
		assertEquals(6, GameEnvironment.getNumSimulationDays()); 
		
//		assertEquals(0, GameEnvironment.getNumDaysPassed()); //test initilize days at 0
//		GameEnvironment.setNumDaysPassed(5); 
//		assertEquals(5, GameEnvironment.getNumDaysPassed()); //test for changing number of days passed.
//		
		
		
	}
	
	@Test
	public void shipNameTest() {
		
		//assertEquals(null, GameEnvironment.getNumDays()); //makes sure num of days start at null
		// when funcionality for adding a game piece is added test.
		GameEnvironment.setShipName("Falcon");
		assertEquals("Falcon", GameEnvironment.getShipName()); 

	}
	
	@Test
	public void gamePiecesCollectedTest() {

		assertEquals(0, GameEnvironment.getCurrentPiecesCollected());
		GameEnvironment.addPiece();
		assertEquals(1, GameEnvironment.getCurrentPiecesCollected());
	}

	
	@Test
	public void daysPastInGameTest() {

		assertEquals(0, GameEnvironment.getNumDaysPassed());
		GameEnvironment.setNumDaysPassed(5);
		assertEquals(5, GameEnvironment.getNumDaysPassed());
	}

	


}
