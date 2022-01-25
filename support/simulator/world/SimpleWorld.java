package simulator.world;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import config.Groceries;
import simulator.bigbrother.BigBrother;
import simulator.grocery.GroceryInterface;
import simulator.shopper.Shopper;

/**
 * A {@link SimpleWorld} generates {@link Shopper}s at a specified rate. Each shopper
 * will have a randomized shopping list.
 * @author jcollard, jddevaug
 *
 */
public class SimpleWorld extends World {

  private final Random random = new Random(0);
  private final int shopperRate;

  /**
   * Creates the {@link SimpleWorld} with the specified rate for {@link Shopper} generation.
   * @param shopperRate the rate at which shoppers are generated
   */
  public SimpleWorld(int shopperRate) {
    if (shopperRate < 1) {
      throw new IllegalArgumentException("Shopper rate must be greater than 0.");
    }
    this.shopperRate = shopperRate;
  }

  /**
   * At each tick, if the current time is divisible by the rate of shoppers, a new
   * shopper is created with a randomized grocery list.
   */
  @Override
  public void tick() {
    if (BigBrother.getBigBrother().getTime() % shopperRate == 0) {
      List<GroceryInterface> groceries = getgroceries();
      new Shopper(groceries);
    }

  }

  /**
   * Creates a grocery list with at least one item.
   * @return
   *          a {@link List} of {@link GroceryInterface} items
   */
  private List<GroceryInterface> getgroceries() {
    List<GroceryInterface> groceries = new LinkedList<GroceryInterface>();
    do {
      groceries.add(nextGrocery());
    } while (moreGroceries(groceries));
    return groceries;
  }
  /**
   * Decides whether to produce another grocery.
   * As the size of the groceries grows, it becomes less and less
   * likely to produce another grocery
   * @param groceries
   *                  a {@link List} of groceries randomly generated
   * @return
   *        <code>true</code> if you are going to produce another grocery;
   *        <code>false</code> otherwise
   */
  private boolean moreGroceries(final List<GroceryInterface> groceries) {
    if (groceries.size() * random.nextDouble() * random.nextDouble() > 8) {
      return false;
    }
    return true;
  }
  /**
   * Produces a random grocery.
   * @return
   *          a random grocery
   */
  private GroceryInterface nextGrocery() {
    switch (random.nextInt(6)) {
      case 0: return Groceries.getApple();
      case 1: return Groceries.getBeef();
      case 2: return Groceries.getChips();
      case 3: return Groceries.getColdPocket();
      case 4: return Groceries.getEggs();
      case 5: return Groceries.getMilk();
      default: throw new IllegalStateException("Should not ever get here");
    }
  }

}
