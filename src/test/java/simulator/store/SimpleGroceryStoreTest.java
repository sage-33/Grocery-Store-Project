package simulator.store;

import static org.junit.Assert.*;

import org.junit.Test;

import simulator.bigbrother.BigBrother;
import simulator.checkout.CheckoutLineInterface;
import simulator.world.SimpleStoreWorld;
import config.Configuration;
import config.Groceries;

public class SimpleGroceryStoreTest {

	@Test(timeout = 5000)
	public void testSimpleStoreWorld() {
		
		AbstractGroceryStore store = Configuration.getSimpleStore();
		
		assertNotNull("The lines returned should not be null.",
				store.getLines());

		assertEquals("The number of lines should be 2.", 2, store.getLines()
				.size());

		CheckoutLineInterface line0 = store.getLines().get(0);
		CheckoutLineInterface line1 = store.getLines().get(1);

		boolean oneIsExpressLine = line0.getClass() == Configuration
				.getExpressLine().getClass()
				|| line1.getClass() == Configuration.getExpressLine()
						.getClass();

		boolean oneIsNormalLine = line0.getClass() == Configuration
				.getNormalLine().getClass()
				|| line1.getClass() == Configuration.getNormalLine().getClass();

		assertTrue(
				"One line should be an express line and the other should be a normal line.",
				oneIsExpressLine && oneIsNormalLine);
		
		// This world creates a shopper at time step 1000 who buys
		// milk, beef, and a cold pocket.
		// Then at time step 5000, creates another shopper who buys
		// 30 beefs
		new SimpleStoreWorld();
		// Runs the simulation
		while (!BigBrother.getBigBrother().tick());

		assertEquals("Should have seen 2 shoppers", 2, store.getNumberOfShoppers());
		
		double totalCost = 2 * 0.15 * 43200; // Running cost of 2 registers

		// Cost to checkout customer 1
		totalCost += (5000 * (Groceries.getBeef().getHandlingRating() + Groceries.getMilk().getHandlingRating() + Groceries.getColdPocket().getHandlingRating()))/(144);

		// Cost to checkout customer 2
		totalCost += (Groceries.getBeef().getHandlingRating()*30*5000) / (30 * 4 * 30 * 4);

		// Cost of all items sold
		totalCost += Groceries.getBeef().getCost()*31 + Groceries.getMilk().getCost() + Groceries.getColdPocket().getCost();
		// Cost to turn on registers
		totalCost += 20;
		
		assertEquals("Check total cost.", totalCost, store.getTotalCost(), 0.0001);
		
		// Total sale value for all items sold
		double totalSales = Groceries.getBeef().getPrice()*31 + Groceries.getMilk().getPrice() + Groceries.getColdPocket().getPrice();
		
		assertEquals(totalSales, store.getTotalSales(), 0);
		
	}

}
