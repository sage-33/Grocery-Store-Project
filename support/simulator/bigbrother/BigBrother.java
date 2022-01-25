package simulator.bigbrother;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import simulator.checkout.AbstractRegister;
import simulator.checkout.Transaction;
import simulator.shopper.Shopper;
import simulator.store.AbstractGroceryStore;
import simulator.world.World;

/**
 * {@link BigBrother} is a fictional character in George Orwell's novel Nineteen
 * Eighty-Four. He is the enigmatic dictator of Oceania, a totalitarian state
 * taken to its utmost logical consequence – where the ruling Party wields total
 * power for its own sake over the inhabitants.
 *
 * @author jcollard, jddevaug
 *
 */
public final class BigBrother {

  private int time = 0;

  private final Set<AbstractGroceryStore> stores;

  private final Set<AbstractRegister> registers;

  private final Set<Shopper> shoppers;

  private final Map<AbstractGroceryStore, Set<Transaction>> transMap;
  /**
   * Registers an {@link Shopper} with {@link BigBrother}.
   *
   * @param s
   *            the {@link Shopper} to register
   */
  public void registerShopper(final Shopper s) {
    if (s == null) {
      throw new NullPointerException();
    }
    shoppers.add(s);
    
  }

  /**
   * Registers an {@link AbstractRegister} with {@link BigBrother}.
   *
   * @param register
   *            the {@link AbstractRegister} to register
   */
  public void registerRegister(final AbstractRegister register) {
    if (register == null) {
      throw new NullPointerException();
    }
    registers.add(register);
  }

  /**
   * Registers an {@link AbstractGroceryStore} with {@link BigBrother}.
   *
   * @param store
   *            the {@link AbstractGroceryStore} to register
   */
  public void registerStore(final AbstractGroceryStore store) {
    if (store == null) {
      throw new NullPointerException();
    }
    if (stores.contains(store)) {
      throw new IllegalArgumentException("Each store should be registered exactly once.");
    }
    stores.add(store);
    transMap.put(store, new HashSet<Transaction>());
  }

  /**
   * Returns the current time.
   *
   * @return the current time
   */
  public int getTime() {
    return time;
  }

  /**
   * Causes the universe to advance a single time step. The world ticks, all
   * registers tick, all shoppers tick. If possible, shoppers select a store
   *
   * @return {@code true} if the simulation is over and {@code false}
   *         otherwise.
   */
  public boolean tick() {
    if (time >= 43200) {
      return true;
    }

    time++;

    World.getWorld().tick();

    List<AbstractGroceryStore> possibleStores = new LinkedList<AbstractGroceryStore>(
        stores);

    for (AbstractRegister r : registers) {
      r.tick();
    }

    for (Shopper s : shoppers) {
      s.tick(transMap);
      if (!s.isInStore()) {
        s.goShopping(possibleStores);
      }
    }

    for (AbstractGroceryStore store : stores) {
      store.tick();
    }

    return false;
  }

  /**
   * Returns the {@link BigBrother} object which watches what you're doing.
   *
   * @return the {@link BigBrother} object which watches what you're doing.
   */
  public static BigBrother getBigBrother() {
    return BIG_BROTHER_INSTANCE;
  }

  private static final BigBrother BIG_BROTHER_INSTANCE = new BigBrother();
  /**
   * Constructs a BigBrother instance.
   */
  private BigBrother() {
    if (BIG_BROTHER_INSTANCE != null) {
      throw new BigBrotherIsWatchingYouException("Nice Try!");
    }
    registers = new HashSet<AbstractRegister>();
    stores = new HashSet<AbstractGroceryStore>();
    shoppers = new HashSet<Shopper>();
    transMap = new HashMap<AbstractGroceryStore, Set<Transaction>>();
  }

}
