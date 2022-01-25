package simulator.grocery;

/**
 * A {@link GroceryInterface} describes an item that is available.
 * for sale in a {@link GroceryStore}
 * @author jcollard, jddevaug
 *
 */
public interface GroceryInterface {

  /**
   * Returns the name of this {@link GroceryInterface}.
   * @return the name of this {@link GroceryInterface}
   */
  String getName();

  /**
   * Returns the price of this {@link GroceryInterface}. This is
   * the amount that a {@link Shopper} must pay to purchase
   * the item. This must be a value greater than or equal to 0.
   * @return the price of this {@link GroceryInterface}
   */
  double getPrice();

  /**
   * Returns the cost of this {@link GroceryInterface}. This is the
   * amount that a {@link GroceryStore} pays a vendor for the item.
   * This must be a value greater than 0.
   * @return the cost of this {@link GroceryInterface}
   */
  double getCost();

  /**
   * Returns a number between [0, 1] that describes the difficulty
   * to handle this {@link GroceryInterface} where 0 is not difficult at all
   * and 1 is extremely difficult.
   * @return the handling rating associated with this {@link GroceryInterface}
   */
  double getHandlingRating();

}
