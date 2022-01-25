package simulator.checkout;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import simulator.grocery.GroceryInterface;
import simulator.shopper.Shopper;
import config.Configuration;
import config.Groceries;

public class SimpleRegisterTest {

	@Test (timeout = 100)
	public void testCreateTransaction1() {
		AbstractRegister register = Configuration.getSimpleRegister();
		
		List<GroceryInterface> groceries = new LinkedList<GroceryInterface>();
		groceries.add(Groceries.getApple());
		Shopper joe = new Shopper(groceries);
		
		Transaction t = register.createTransaction(joe);
		
		assertNotNull("The returned transaction should not be null.",t);
		assertEquals("The grocery lists should be equal.", groceries, t.getReceipt().getGroceries());
		assertEquals("The shopper should match.", joe, t.getShopper());
		assertEquals("The number of time steps should be 4.", 4, t.getTimeSteps());
	}
	
	@Test (timeout = 100)
	public void testCreateTransaction2() {
		AbstractRegister register = Configuration.getSimpleRegister();
		
		List<GroceryInterface> groceries = new LinkedList<GroceryInterface>();
		groceries.add(Groceries.getApple());
		groceries.add(Groceries.getBeef());
		
		Shopper joe = new Shopper(groceries);
		
		Transaction t = register.createTransaction(joe);
		
		assertNotNull("The returned transaction should not be null.",t);
		assertEquals("The grocery lists should be equal.", groceries, t.getReceipt().getGroceries());
		assertEquals("The shopper should match.", joe, t.getShopper());
		assertEquals("The number of time steps should be 8.", 8, t.getTimeSteps());
	}
	

}
