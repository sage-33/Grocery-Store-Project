package simulator.world;

import java.util.LinkedList;
import java.util.List;

import config.Groceries;
import simulator.bigbrother.BigBrother;
import simulator.grocery.GroceryInterface;
import simulator.shopper.Shopper;
/**
 * Represents a SimpleStore that only processes two shoppers.
 * @author jcollard, jddevaug
 *
 */
public class SimpleStoreWorld extends World {

  @Override
  public final void tick() {
    // At time step 1000, create a shopper buying milk, beef, and cold pockets
    if (BigBrother.getBigBrother().getTime() == 1000) {
      List<GroceryInterface> groceries = new LinkedList<GroceryInterface>();
      groceries.add(Groceries.getMilk());
      groceries.add(Groceries.getBeef());
      groceries.add(Groceries.getColdPocket());
      new Shopper(groceries);
    }

    // At time step 5000, create a shopper buying 30 beefs
    if (BigBrother.getBigBrother().getTime() == 5000) {
      List<GroceryInterface> groceries = new LinkedList<GroceryInterface>();
      for (int i = 0; i < 30; i++) {
        groceries.add(Groceries.getBeef());
      }
      new Shopper(groceries);
    }
  }

}
