package simulator.checkout;

import simulator.bigbrother.BigBrother;
import simulator.shopper.Shopper;

/**
 * A {@link Transaction} contains a receipt for a shopper and the amount of time
 * it took to complete a transaction.
 *
 * @author jcollard, jddevaug
 *
 */
public final class Transaction {

  private final AbstractReceipt receipt;
  private final Shopper shopper;
  private final int timesteps;
  private final int startTime;

  /**
   * Creates a {@link Transaction} with the specified {@link AbstractReceipt}, {@link Shopper},
   * that takes the specified number of time steps.
   * The number of time steps to complete a transaction
   * must be at least 1.
   * @param receipt the receipt associated with this {@link Transaction}
   * @param shopper the shopper associated with this {@link Transaction}
   * @param timesteps the number of time steps to complete this transaction.
   * @throws NullPointerException if {@code receipt} of {@code shopper} are {@code null}
   * @throws IllegalArgumentException if {@code timesteps} is less than 1
   */
  public Transaction(final AbstractReceipt receipt, final Shopper shopper, int timesteps) {
    if (receipt == null || shopper == null) {
      throw new NullPointerException(
          "Receipt and Shopper must be non-null");
    }
    if (timesteps < 1) {
      throw new IllegalArgumentException(
          "Cannot perform transaction in less than 1 time step.");
    }
    this.receipt = receipt;
    this.shopper = shopper;
    this.startTime = BigBrother.getBigBrother().getTime();
    this.timesteps = timesteps;
  }

  /**
   * Returns the {@link AbstractReceipt} for this {@link Transaction}.
   *
   * @return the {@link AbstractReceipt} for this {@link Transaction}
   */
  public AbstractReceipt getReceipt() {
    return receipt;
  }

  /**
   * Returns the {@link Shopper} associated with this {@link Transaction}.
   *
   * @return the {@link Shopper} associated with this {@link Transaction}
   */
  public Shopper getShopper() {
    return shopper;
  }

  /**
   * Returns the number of time steps required to complete this transaction.
   * This number will be greater than or equal to 1.
   *
   * @return the number of time steps required to complete this transaction.
   */
  public int getTimeSteps() {
    return timesteps;
  }

  /**
   * Returns the time when this {@link Transaction} was created.
   *
   * @return the time when this {@link Transaction} was created.
   */
  public int getStartTime() {
    return startTime;
  }

}
