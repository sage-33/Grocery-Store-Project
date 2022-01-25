package simulator.checkout;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import simulator.bigbrother.BigBrother;
import simulator.bigbrother.BigBrotherIsWatchingYouException;
import simulator.grocery.GroceryInterface;
import simulator.shopper.Shopper;

/**
 * <p>
 * A {@link AbstractRegister} is used to process {@link Shopper}s. A
 * {@link AbstractRegister} has a running cost associated with it. Each time a
 * {@link Shopper} is processed, a {@link AbstractRegister} will produce a
 * {@link Transaction}. The running cost of a register is associated with the
 * skill level of the employee working at the register. That means, the faster
 * the employee is able to process a transaction, the higher the running cost
 * will be.
 * </p>
 * <p>
 * To implement a {@link AbstractRegister} one must implement the
 * {@link AbstractRegister#createTransaction(Shopper)} method.
 * </p>
 *
 * @author jcollard, jddevaug
 *
 */
public abstract class AbstractRegister {

  private final List<Transaction> transactions = new LinkedList<Transaction>();
  private int transactionEndTime;
  private double runningCost = 0;
  private boolean running;
  /**
   * Constructs an {@link Abstract} and
   * connects it to {@link BigBrother}.
   */
  protected AbstractRegister() {
    // Big Brother knows about all Registers
    BigBrother.getBigBrother().registerRegister(this);
    running = false;
  }

  /**
   * Processes a {@link Shopper} producing a {@link Transaction}. In addition
   * to processing the {@link Shopper} the running cost is increased relative
   * to the speed and difficulty of the groceries purchased by the
   * {@link Shopper}
   *
   * @param s
   *            the shopper to process
   * @return a {@link Transaction} for this customer
   */
  public final Transaction processShopper(final Shopper s) {
    if (!running) {
      throw new BigBrotherIsWatchingYouException(
          "You cannot process a shopper on a register that is turned off.");
    }
    if (s == null) {
      throw new NullPointerException();
    }

    if (isBusy()) {
      throw new BigBrotherIsWatchingYouException(
          "This register is already being used.");
    }

    Transaction t = createTransaction(s);
    double difficulty = 0.0;

    for (GroceryInterface g : t.getReceipt().getGroceries()) {
      difficulty += g.getHandlingRating();
    }

    // The faster the processing is completed, the more expensive
    // it is to run the register
    runningCost += (difficulty * 5000)
        / (t.getTimeSteps() * t.getTimeSteps());
    transactionEndTime = BigBrother.getBigBrother().getTime()
        + t.getTimeSteps();
    transactions.add(t);
    s.completeTransaction(t);
    return t;
  }

  /**
   * Returns the total running cost of this {@link AbstractRegister}.
   *
   * @return the total running cost of this {@link AbstractRegister}
   */
  public final double getRunningCost() {
    return runningCost;
  }

  /**
   * Every tick increases the cost of running this {@link AbstractRegister}.
   * This is called by {@link BigBrother} every time step.
   */
  public final void tick() {
    if (running) {
      runningCost += 0.15;
    }
  }

  /**
   * Returns a {@link List} containing all transactions processed by this
   * {@link AbstractRegister}. The returned list is an immutable view.
   *
   * @return a {@link List} containing all transactions processed by this
   *         {@link AbstractRegister}.
   */
  public final List<Transaction> getTransactions() {
    return Collections.unmodifiableList(transactions);
  }

  /**
   * Returns {@code true} if this {@link AbstractRegister} is busy and
   * {@code false} otherwise.
   *
   * @return {@code true} if this {@link AbstractRegister} is busy and
   *         {@code false} otherwise.
   */
  public final boolean isBusy() {
    int time = BigBrother.getBigBrother().getTime();
    return time < transactionEndTime;
  }

  /**
   * Turns this {@link AbstractRegister} on. There is a starting cost of 10.
   */
  public final void turnOn() {
    if (running) {
      return;
    }
    runningCost += 10;
    running = true;
  }

  /**
   * Turns this {@link AbstractRegister} off. {@link BigBrother} will not
   * allow you to turn this {@link AbstractRegister} off if the register is
   * busy.
   */
  public final void turnOff() {
    if (isBusy()) {
      throw new BigBrotherIsWatchingYouException(
          "This register was busy when you attempted to turn it off.");
    }
    running = false;
  }

  /**
   * This method creates a {@link Transaction} for the given {@link Shopper}.
   *
   * @param s
   *            the shopper being processed
   * @return the {@link Transaction} for the given {@link Shopper}
   */
  protected abstract Transaction createTransaction(Shopper s);

}
