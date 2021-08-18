package main;

import java.util.ArrayList;

public class GameEnvironment {
	
	//Objects
	public static GameEnvironment Game = new GameEnvironment();
	public static Ship playerShip = new Ship();
	final CurrentItems Shop = new CurrentItems();
	final static Ship CurrentTeam = new Ship(); //just added to call team class
	public static NextPlanetScreen windowWait;
	
	
	//Variables
	private static int numSimulationDays = 10;
	public int pieceFound = 0;
	public static int numPieces = 6;
	public static int numDaysPassed = 0;
	public static int currentPiecesCollected = 0;
	
	ArrayList<String> CrewClasses = new ArrayList<String>();
	
	//Miscellaneous Methods
	/**
	 * 
	 * @return playerShip Object
	 * Not specifically tested but is used through other testing.
	 */
	public static Ship getPlayerShip() {
		return playerShip;
	}
	
	/**
	 * 
	 * @return returns the Current amount of collected ship Pieces across the entire game.
	 */
	public static int getCurrentPiecesCollected() {
		return currentPiecesCollected;
	}
	
	/**
	 * Updates the variable of pieceFound to 1 indicating that the current planets warp drive piece has been found
	 */
	public void pieceFound() {
		pieceFound = 1;
	}
	
	
	/**
	 * Increases the count of current warp drive pieces found over the entire game by one.
	 */
	public static void addPiece() {
		currentPiecesCollected++;
	}
	
	/**
	 * 
	 * @return returns the number of pieces that the player needs to collect in order to win the game.
	 */
	public static int getNumPieces() {
		return numPieces;
	}
	
	
	/**
	 * 
	 * @param numPiece
	 * Takes an int and sets the number of pieces the player is required to collect in the game
	 * using the variable numPieces
	 */
	public static void setNumPieces(int numPiece) {
		numPieces = numPiece;
	}
	
	
	
	
	
	
	/**
	 * 
	 * @return returns the int variable of number of days that have passed in the game.
	 */
	
	public static int getNumDaysPassed() {
		 return numDaysPassed;
	}
	
	/**
	 * Updates the number of days that have passed in the game since initialization 
	 * @param value
	 * Takes a int variable by the amount of days you want to increase the count by
	 */
	public static void setNumDaysPassed(int value) {
		 numDaysPassed = numDaysPassed + value;
	}
	
	
	/**
	 * Decreases the variable numSimulationDays, the current amount of days that the game will go for, taken from the slider at the beginning of game  
	 */
	public static void reduceDay() {
		numSimulationDays = numSimulationDays - 1;
	}
	
	/**
	 * 
	 * @return returns the  CurrentTeam Object
	 * Not specifically tested but is used through other testing.
	 */
	public static Ship getTeam() {
		return CurrentTeam;
	}
	
	
	
	/**
	 * 
	 * @param shipname
	 * Takes a String parameter of the name of the ship.
	 *Updates the ship name held in the Players ship class
	 */
	public static void setShipName(String shipname) {
		playerShip.shipName(shipname);
	}
	
	/**
	 * 
	 * @return returns the the name of the ship held in the PlayerShip Object
	 */
	public static String getShipName() {
		return playerShip.returnShipName();
	}
	

	/**
<<<<<<< HEAD
	 * Sets the number of days for the variable  numSimulationDays for the total number of days the player has opted to play for.
	 * @param numDays
	 * Takes an int the number of days 
=======
	 * Sets the number of days for the variable numSimulationDays for the total number of days the player has opted to play for.
	 * @param Takes an int the number of days 
>>>>>>> branch 'master' of https://github.com/Mike389727/Seng201-project.git
	 */
	public static void setNumSimulationDays(int numDays) {
		numSimulationDays = numDays;
	}
	
	/**
	 * 
	 * @return returns the number of days the player has elected to play for at the start of the game using the slider.
	 */
	public static int getNumSimulationDays() {
		return numSimulationDays;
	}
	

	
	
	//GUI Methods
	public static void openNewGameScreen() {
		NewGameScreen window1 = new NewGameScreen();
		window1.frame.setVisible(true);
	}
	
	public static void openDaySelect() {
		DaySelectScreen window2 = new DaySelectScreen();
		window2.frame.setVisible(true);
	}
	
	public static void openStoryScreen() {
		StoryScreen window3 = new StoryScreen(numSimulationDays);
		window3.frame.setVisible(true);
	}
	
	public static void openTeamSelection() {
		TeamSelection window4 = new TeamSelection(Game);
		window4.frame.setVisible(true);
	}
	
	public static void openStatusScreen() {
		StatusScreen window5 = new StatusScreen(Game);
		window5.frame.setVisible(true);
	}
	
	public static void openShopScreen() {
		SpaceShopScreen window6 = new SpaceShopScreen(Game);
		window6.frame.setVisible(true);
	}
	
	public static void openQuitScreen() {
		QuitDisclaimer window7 = new QuitDisclaimer();
		window7.frame.setVisible(true);
	}
	
	public static void openMainScreen() {
		MainScreen window8 = new MainScreen();
		window8.frame.setVisible(true);
	}
	
	public static void openFoodSelectScreen(int crewIndex) {
		FoodSelectScreen window9 = new FoodSelectScreen(Game, crewIndex);
		window9.frame.setVisible(true);
	}
	
	public static void openMedicineSelectScreen(int crewIndex) {
		MedicineSelectScreen window11 = new MedicineSelectScreen(Game, crewIndex);
		window11.frame.setVisible(true);
	}
	
	public static void openLoseScreen(String reason) {
		LoseScreen window10 = new LoseScreen(reason);
		window10.frame.setVisible(true);
	}
	
	public static void openNextPlanetScreen() {
		NextPlanetScreen window12 = new NextPlanetScreen();
		window12.frame.setVisible(true);
	}
	
	public static void openWinScreen() {
		WinScreen window13 = new WinScreen();
		window13.frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		openNewGameScreen();
	}
	
}

