package simulator.checkout;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import simulator.grocery.GroceryInterface;

/**
 * A {@link AbstractReceipt} is a list of purchased {@link GroceryInterface} items,
 * a sub-total for those items, a discount for the purchase, and a sale value.
 *
 * @author jcollard, jddevaug
 *
 */
public abstract class AbstractReceipt {

  private final List<GroceryInterface> groceries;
  private final double discount;

  /**
   * Creates a {@link AbstractReceipt} with the specified grocery list, and
   * discount. The discount given to a {@link Shopper} is a value in the range
   * [0, 1] where 0 is no discount and 1 is a 100% discount.
   *
   * @param groceries
   *            the {@link List} of {@link GroceryInterface} items purchased
   * @param discount
   *            any discount given to the {@link Shopper}
   * @throws NullPointerException
   *             if {@code groceries} is {@code null}
   * @throws IllegalArgumentException
   *             if discount is less than 0 or greater than 1.
   */
  protected AbstractReceipt(final List<GroceryInterface> groceries, double discount) {
    if (groceries == null) {
      throw new NullPointerException("Grocery List must be non-null.");
    }
    if (discount < 0 || discount > 1) {
      throw new IllegalArgumentException(
          "Discount must be in the range [0, 1].");
    }
    this.groceries = Collections.unmodifiableList(new LinkedList<GroceryInterface>(
        groceries));
    this.discount = discount;
  }

  /**
   * Returns an immutable {@link List} of {@link GroceryInterface} items on this
   * {@link AbstractReceipt}. Any attempt to modify the list will result in an
   * exception being thrown.
   *
   * @return an immutable {@link List} of {@link GroceryInterface} items on this
   *         {@link AbstractReceipt}.
   */
  public final List<GroceryInterface> getGroceries() {
    return groceries;
  }

  /**
   * Returns the discount given to a {@link Shopper}. This is a value in the
   * range [0, 1] where 0 is no discount and 1 is a 100% discount.
   *
   * @return the discount given to the {@link Shopper}.
   */
  public final double getDiscount() {
    return discount;
  }

  /**
   * Returns the sub-total of this {@link AbstractReceipt}. That is, the sum
   * of the price of all of the {@link GroceryInterface} items associated with it.
   *
   * @return Returns the sub-total of this {@link AbstractReceipt}.
   */
  public abstract double getSubtotal();

  /**
   * Returns the final sale value of this {@link AbstractReceipt}. That is,
   * the amount that a {@link Shopper} paid after any discount.
   *
   * @return the final sale value of this {@link AbstractReceipt}.
   */
  public abstract double getSaleValue();

}
