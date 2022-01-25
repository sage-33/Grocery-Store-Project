package simulator.checkout;

import simulator.shopper.Shopper;
import structures.QueueInterface;

/**
 * A {@link CheckoutLineInterface} is a {@link QueueInterface} of
 * {@link Shopper}.
 *
 * @author jcollard, jddevaug
 *
 */
public interface CheckoutLineInterface extends QueueInterface<Shopper> {

  /**
   * Returns {@code true} if this specified {@link Shopper} can enter this
   * line and {@code false} otherwise.
   *
   * @param shopper
   *            the shopper to check
   * @return {@code true} if this specified {@link Shopper} can enter this
   *         line and {@code false} otherwise.
   * @throws NullPointerException if {@code shopper} is {@code null}
   */
  boolean canEnterLine(Shopper shopper);

  /**
   * Adds {@code shopper} to the end of this {@link CheckoutLineInterace}.
   * This operation should execute in constant time. That is, it should be
   * executed in O(1). For convenience, this method returns the modified
   * {@link QueueInterface}
   *
   * @param shopper
   *            the shopper to be added
   * @return the modified {@link QueueInterface}
   * @throws NullPointerException
   *             if {@code shopper} is {@code null}
   * @throws IllegalArgumentException
   *             if {@code shopper} cannot enter the line as specified by
   *             {@link CheckoutLineInterface#canEnterLine(Shopper)}
   */
  QueueInterface<Shopper> enqueue(Shopper shopper);

}
