package test;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import main.CrewMember;
import main.CurrentItems;

public class CurrentItemsTest {

	

	/**
	 * Tests to make sure that the intial hashmap values initilize at zero for all items 
	 */
	public CurrentItems Shop;
	public Map<String, Integer> StartingItemns;
	@Before
	public void ShopDictionaryInitilization() {
		Shop = new CurrentItems();
		StartingItemns = new HashMap<String, Integer>();
		StartingItemns.put("Bandage", 0);
		StartingItemns.put("Stimpack", 0);
		StartingItemns.put("Plague Cure", 0);
		StartingItemns.put("Medicine", 0);
		StartingItemns.put("Cookie", 0);
		StartingItemns.put("Space Snack", 0);
		StartingItemns.put("Sandwich", 0);
		StartingItemns.put("Space Lunch", 0);
		StartingItemns.put("Large Dinner", 0);
		StartingItemns.put("Portable Feast", 0);

		assertEquals(StartingItemns, Shop.getInventory());

	}
	/**
	 * Junit Tests update function correctly to all dictionary items in single use and multiple additions to dictionary and the ToString method returns these values
	 * assertEquals as (expectedValue, actualvalue)
	 */
		
	@Test
	public void UpdateCorrectItem() {
		CurrentItems Shop = new CurrentItems();

		String afterUpdateItems = "<html>Bandages: 2<br/>Stimpack: 1<br/>Plague Cure: 1<br/>Medicine: 2<br/>Cookie: 1<br/>Space Snack: 1<br/>Sandwich:"
				+ " 2<br/>Space Lunch: 1<br/> LargeDinner: 1<br/> PortableFeast: 3<br/>  <html>" + 
				"";
		
		String afterRemoveItems = "<html>Bandages: 1<br/>Stimpack: 1<br/>Plague Cure: 1<br/>Medicine: 2<br/>Cookie: "
				+ "1<br/>Space Snack: 1<br/>Sandwich: 1<br/>Space Lunch: 1<br/> LargeDinner: 1<br/> PortableFeast:"
				+ " 3<br/>  <html>" + 
				"";
		ArrayList<String> TestItems = new ArrayList<String>(Arrays.asList("Bandage", "Stimpack", "Plague Cure", "Medicine","Cookie","Space Snack","Sandwich","Space Lunch","Large Dinner","Portable Feast"));

		// Adds plus one to each item in the hashmap using setter increase value by one. test to make sure only one is being added at a time.
		for (int i = 0; i < 10; i++) {
			Shop.update(TestItems.get(i));
			//System.out.println(TestItems.get(i));
			}
		
		//Checks each item in hashmap equal to one
		for (int i = 0; i < 10; i++) {
			assertEquals(1, (int)Shop.ItemGetter(TestItems.get(i)));
			}

		// adds to the hashmap so as values are different to determine correct tostring return
		Shop.update("Bandage");
		Shop.update("Sandwich");
		Shop.update("Portable Feast");
		Shop.update("Portable Feast");
		Shop.update("Medicine");
		//compares output of tostring method and what it should return.

		assertEquals((afterUpdateItems), Shop.toString());
		Shop.remove("Bandage");
		Shop.remove("Sandwich");
		assertEquals((afterRemoveItems), Shop.toString());
		

	}
		
	
	@Test
	public void foodListTest() {
		String fooditemsreturn = "<html>Cookie: 0<br/>Space Snack: 0<br/>Sandwich: 0<br/>Space Lunch: 0<br/>Large Dinner: 0<br/>Portable Feast: 0 </html>" + 
				"";
		assertEquals((fooditemsreturn), Shop.getFood()); //checks food items are only thing being returned.
	}
	@Test
	public void medicalListTest() {
		String medicalItemsreturned = "<html>Bandages: 0<br/>Stimpack: 0<br/>Plague Cure: 0<br/>Medicine: 0 </html>" + 
				"";
		assertEquals((medicalItemsreturned), Shop.getMedicine()); //checks food items are only thing being returned.
	}
	
	
	@Test
	public void stolenItemTest() {
		String stolenItemoutput = "NoItem";
		
		assertEquals((stolenItemoutput), Shop.stealItemFromShip());
		
		Shop.update("Bandage");
		Shop.update("Sandwich");
		Shop.update("Portable Feast");
		Shop.update("Portable Feast");
		Shop.update("Medicine");

		assertEquals(("Portable Feast"), Shop.stealItemFromShip());  //this checks that an item is being stolen.
		
		
	}


	
	}
	
