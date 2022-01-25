package simulator.shopper;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import simulator.bigbrother.BigBrother;
import simulator.bigbrother.BigBrotherIsWatchingYouException;
import simulator.checkout.CheckoutLineInterface;
import simulator.checkout.Transaction;
import simulator.grocery.GroceryInterface;
import simulator.store.AbstractGroceryStore;

/**
 * A {@link Shopper} contains a list of {@link GroceryInterface} items that they
 * wish to purchase from a store.
 *
 * @author jcollard, jddevaug
 *
 */
public class Shopper {

  private static Random rand = new Random(1984);
  private static int nextID;
  private int waitingTime;
  private final int uniqueID;
  private final List<GroceryInterface> groceries;
  private final Set<Transaction> pastTransactions;
  private boolean inline;
  private boolean irate;
  private boolean notifyBigBrother;
  private AbstractGroceryStore store;

  /**
   * Creates a new {@link Shopper} that has a unique ID that no previously
   * created {@link Shopper} has. The created {@link Shopper}'s shopping list
   * will be a copy of the list specified.
   *
   * @param groceries
   *            the shoppers grocery list
   * @throws NullPointerException
   *             if {@code groceries} is {@code null}
   */
  public Shopper(final List<GroceryInterface> groceries) {
    if (groceries == null) {
      throw new NullPointerException();
    }
    this.uniqueID = nextID++;
    this.groceries = new LinkedList<GroceryInterface>(groceries);
    this.inline = false;
    this.pastTransactions = new HashSet<Transaction>();
    BigBrother.getBigBrother().registerShopper(this);
  }

  /**
   * Returns a uniquely identifying integer for this {@link Shopper}.
   *
   * @return a uniquely identifying integer for this {@link Shopper}
   */
  public final int getUniqueID() {
    return uniqueID;
  }

  /**
   * Returns an immutable {@link List} containing this {@link Shopper}'s
   * grocery items.
   *
   * @return an immutable {@link List} containing this {@link Shopper}'s
   *         grocery items
   */
  public final List<GroceryInterface> getShoppingList() {
    return Collections.unmodifiableList(groceries);
  }

  /**
   * Returns {@code true} if this {@link Shopper} is irate and {@code false}
   * otherwise.
   *
   * @return {@code true} if this {@link Shopper} is irate and {@code false}
   *         otherwise.
   */
  public final boolean isIrate() {
    return irate;
  }

  /**
   * Completes the specified {@link Transaction} with this {@link Shopper}.
   * {@link BigBrother} will stop you if you try to give them a
   * {@link Transaction} that is not theirs or a {@link Transaction} that they
   * already received.
   *
   * A satisfied {@link Shopper} will upvote the {@link AbstractGroceryStore}
   * they visited on the ever growing social networking site
   * {@link MyFacePagePlus}. If the {@link Shopper} was not satisfied, they
   * will downvote the {@link AbstractGroceryStore}.
   *
   * @param t
   *            the {@link Transaction} to complete.
   */
  public void completeTransaction(final Transaction t) {

    if (t == null) {
      throw new NullPointerException();
    }
    if (!t.getShopper().equals(this)) {
      throw new BigBrotherIsWatchingYouException(
          "You must give the shopper a valid transaction.");
    }
    if (pastTransactions.contains(t)) {
      throw new BigBrotherIsWatchingYouException(
          "This is an old transaction.");
    }

    waitingTime += t.getTimeSteps();
    notifyBigBrother = true;
    inline = false;
    rateStore(t);
  }
  /**
   * Rates the store given a {@link Transaction}.
   * @param t
   *          the {@link Transaction} for the {@link Shopper}'s visit
   */
  private void rateStore(final Transaction t) {

    // If the customer left the store, they will give a down vote
    if (irate) {
      MyFacePagePlus.getSocialNetwork().downvote(store);
      return;
    }

    // If the customer gets a 50% discount, they will give an upvote
    if (t.getReceipt().getDiscount() >= 0.50) {
      MyFacePagePlus.getSocialNetwork().upvote(store);
      return;
    }

    // Otherwise, calculate the chance for a downvote
    int numberOfItems = groceries.size();
    double discount = t.getReceipt().getDiscount();

    // Probability of a down vote
    double prob = (waitingTime / (100.0 * numberOfItems)) * (1 - discount);

    // Roll the magic die!
    double chance = rand.nextDouble();

    if (chance < prob) {
      MyFacePagePlus.getSocialNetwork().downvote(store);
      return;
    }

    MyFacePagePlus.getSocialNetwork().upvote(store);

  }

  /**
   * If this {@link Shopper} is in a line, their waiting time increases. If
   * they have been waiting for 300 or more ticks, they will put all of their
   * groceries back and become irate. An irate customer will always downvote
   * the {@link AbstractGroceryStore} they visited.
   * @param transactions
   *                    a {@link Set} of {@link Transaction}s to pass to {@link BigBrother}
   */
  public void tick(final Map<AbstractGroceryStore, Set<Transaction>> transactions) {
    notifyBigBrother(transactions);

    if (inline) {
      waitingTime++;
      if (waitingTime >= 300) {
        groceries.clear();
        irate = true;
      }
    }
  }


  /**
   * Notifies {@link BigBrother} of a new {@link Transaction}.
   * @param transMap
   *                a {@link Map} of {@link AbstractGroceryStore}s
   *                and their {@link Transaction}s
   */
  private void notifyBigBrother(
     final Map<AbstractGroceryStore, Set<Transaction>> transMap) {
    if (notifyBigBrother) {
      notifyBigBrother = false;
      Set<Transaction> trans = transMap.get(store); 
      trans.addAll(pastTransactions);
    }
  }

  /**
   * Returns {@code true} if this {@link Shopper} is in an
   * {@link AbstractGroceryStore} and {@code false} otherwise. This method is
   * used by {@link BigBrother} to assign this {@link Shopper} to a store
   *
   * @return {@code true} if this {@link Shopper} is in a
   *         {@link AbstractGroceryStore} and {@code false} otherwise.
   */
  public boolean isInStore() {
    return store != null;
  }

  /**
   * Returns the total amount of time this {@link Shopper} has been waiting in
   * an {@link AbstractGroceryStore}.
   *
   * @return the total amount of time this {@link Shopper} has been waiting in
   *         an {@link AbstractGroceryStore}
   */
  public final int getWaitingTime() {
    return waitingTime;
  }

  /**
   * Returns the {@link AbstractGroceryStore} this {@link Shopper} visited.
   * @return the {@link AbstractGroceryStore} this {@link Shopper} visited.
   */
  public AbstractGroceryStore getStore() {
    return store;
  }

  /**
   * Given a {@link List} of {@link CheckoutLineInterface}s, this
   * {@link Shopper} will find the shortest line that they can enter. If they
   * entered a line, this method returns {@code true} and {@code false}
   * otherwise.
   *
   * @param lines
   *            the list of lines to try
   * @return {@code true} if this {@link Shopper} entered a line and
   *         {@code false} otherwise.
   */
  public final boolean selectLine(final List<CheckoutLineInterface> lines) {
    // If this shopper is already in a line, don't select a new line
    if (inline) {
      return false;
    }

    // Find the shortest line possible
    CheckoutLineInterface shortest = findShortest(lines);

    // If the shopper was not able to join any lines,
    // return false
    if (shortest == null) {
      return false;
    }

    // Otherwise, this shopper is in the shortest line
    shortest.enqueue(this);
    inline = true;

    return true;
  }

  /**
   * Finds the shortest line that can be entered.
   * @param lines
   *              the list of {@link CheckoutlineInterface}s associated
   *              with the {@link AbstractGrocery} the {@link Shopper}
   *              is in.
   * @return
   *          the {@link CheckoutLineInterface} with the fewest number
   *          of {@link Shopper}s
   */
  private CheckoutLineInterface findShortest(
      final List<CheckoutLineInterface> lines) {
    CheckoutLineInterface shortest = null;
    for (CheckoutLineInterface line : lines) {
      // If the line cannot be entered, check the next line.
      if (!line.canEnterLine(this)) {
        continue;
      }

      // If this is the first line that can be entered, it must be the
      // shortest line
      if (shortest == null) {
        shortest = line;
      }

      // If this line is shorter than the previously found shortest line,
      // it must be the shortest line
      if (shortest.size() > line.size()) {
        shortest = line;
      }
    }
    // Returns the shortest line or null if one could not be found
    return shortest;
  }

  /**
   * Given a {@link List} of {@link AbstractGroceryStore}s, this
   * {@link Shopper} uses the social networking site {@link MyFacePagePlus} to
   * select where they will go shopping. Once a store has been chosen, the
   * {@link Shopper} will try to find a line they can enter.
   *
   * @param stores
   *            a list of {@link AbstractGroceryStore}s to choose from
   * @return {@code true} if the {@link Shopper} entered a line and
   *         {@code false} otherwise.
   */
  public boolean goShopping(final List<AbstractGroceryStore> stores) {
    if (stores == null || stores.size() < 1) {
      throw new IllegalArgumentException();
    }
    if (store != null) {
      return false;
    }

    store = MyFacePagePlus.getSocialNetwork().selectStore(stores);

    return selectLine(store.getLines());
  }

}
