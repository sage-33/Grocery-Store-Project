package simulator.world;

import simulator.bigbrother.BigBrotherIsWatchingYouException;

/**
 * A {@link World} determines how {@link Shopper}s are generated in a
 * simulation.
 *
 * @author jcollard, jddevaug
 *
 */
public abstract class World {

  private static World instance;

  /**
   * Returns the {@link World} object if it exists. If no {@link World} has
   * been constructed an {@link IllegalStateException} will be thrown
   *
   * @return the {@link World}.
   */
  public static World getWorld() {
    if (instance == null) {
      throw new IllegalStateException("No world has been constructed.");
    }
    return instance;
  }

  /**
   * Only a Single {@link World} object can exist. If more than one is
   * constructed, {@link BigBrother} will not be happy.
   */
  protected World() {
    if (instance != null) {
      throw new BigBrotherIsWatchingYouException(
          "Multiple Worlds constructed.");
    }
    instance = this;
  }

  /**
   * Ticks the {@link World} by one time step. {@link BigBrother} will call
   * this method.
   */
  public abstract void tick();

}
