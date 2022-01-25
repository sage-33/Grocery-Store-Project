package simulator.grocery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import config.Groceries;

public class TestGroceries {

	@Test (timeout = 100)
	public void testMilk() {
		GroceryInterface milk = Groceries.getMilk();
		assertEquals("Milk", milk.getName());
		assertEquals(3.29, milk.getPrice(), 0.0);
		assertEquals(1.25, milk.getCost(), 0.0);
		assertEquals(0.1, milk.getHandlingRating(), 0);
	}
	
	@Test (timeout = 100)
	public void testEggs() {
		GroceryInterface eggs = Groceries.getEggs();
		assertEquals("Eggs", eggs.getName());
		assertEquals(2.29, eggs.getPrice(), 0.0);
		assertEquals(0.25, eggs.getCost(), 0.0);
		assertEquals(0.8, eggs.getHandlingRating(), 0);
	}
	
	@Test (timeout = 100)
	public void testColdPocket() {
		GroceryInterface coldPocket = Groceries.getColdPocket();
		assertEquals("Cold Pocket", coldPocket.getName());
		assertEquals(0.49, coldPocket.getPrice(), 0.0);
		assertEquals(0.02, coldPocket.getCost(), 0.0);
		assertEquals(0.13, coldPocket.getHandlingRating(), 0);
	}
	
	@Test (timeout = 100)
	public void testChips() {
		GroceryInterface chips = Groceries.getChips();
		assertEquals("Chips", chips.getName());
		assertEquals(3.19, chips.getPrice(), 0.0);
		assertEquals(0.50, chips.getCost(), 0.0);
		assertEquals(0.4, chips.getHandlingRating(), 0);
	}

	@Test (timeout = 100)
	public void testBeef() {
		GroceryInterface wheresTheBeef = Groceries.getBeef();
		assertEquals("Beef", wheresTheBeef.getName());
		assertEquals(3.39, wheresTheBeef.getPrice(), 0.0);
		assertEquals(1.14, wheresTheBeef.getCost(), 0.0);
		assertEquals(0.75, wheresTheBeef.getHandlingRating(), 0);
	}
	
	@Test (timeout = 100)
	public void testApple() {
		GroceryInterface apple = Groceries.getApple();
		assertEquals("Apple", apple.getName());
		assertEquals(0.69, apple.getPrice(), 0.0);
		assertEquals(0.17, apple.getCost(), 0.0);
		assertEquals(0.25, apple.getHandlingRating(), 0);
	}

}
