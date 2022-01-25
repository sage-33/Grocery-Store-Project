package simulator.shopper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import simulator.bigbrother.BigBrotherIsWatchingYouException;
import simulator.store.AbstractGroceryStore;

/**
 * {@link MyFacePagePlus} is the hot new social networking site created by Tom
 * Zuckerberg! It is used for pretty much everything ({@link BigBrother}
 * mandates this).
 *
 * @author jcollard, jddevaug
 *
 */
public final class MyFacePagePlus {

  private final Random rand = new Random(42);

  private final Map<AbstractGroceryStore, Double> ratings;

  private int votes;

  /**
   * Returns the current rating for the specified {@link AbstractGroceryStore}
   * . The value is a rating in the range [0.10, 1.0]. All
   * {@link AbstractGroceryStore}s start with the highest possible rating.
   *
   * @param store
   *            the store to look up
   * @return the current rating for the specified {@link AbstractGroceryStore}
   *         .
   */
  public double getRating(final AbstractGroceryStore store) {
    if (store == null) {
      throw new NullPointerException();
    }
    Double rating = ratings.get(store);
    if (rating == null) {
      rating = 1.0;
      ratings.put(store, rating);
    }
    return rating;
  }

  /**
   * Increases the specified {@link AbstractGroceryStore}s rating! The new
   * rating is the old rating multiplied by 1.05. {@link BigBrother} monitors
   * the upvote system and ensures that there is exactly 1 vote per
   * {@link Shopper} so you may want to reconsider calling this method
   * repeatedly on your own store.
   *
   * @param store
   *            the store to upvote
   */
  public void upvote(final AbstractGroceryStore store) {
    double rating = Math.min(1.0, getRating(store) * 1.05);
    ratings.put(store, rating);
    this.votes++;
  }

  /**
   * Decreases the specified {@link AbstractGroceryStore}'s rating. The new
   * rating is the old rating multiplied by 0.75. {@link BigBrother} monitors
   * the downvote system and ensures that there is exactly 1 vote per
   * {@link Shopper} so you may want to reconsider calling this method
   * repeatedly on a rival store.
   *
   * @param store
   *            the store to downvote
   */
  public void downvote(final AbstractGroceryStore store) {
    double rating = Math.max(0.10, getRating(store) * 0.75);
    ratings.put(store, rating);
    this.votes++;
  }

  /**
   * Given a {@link List} of {@link AbstractGroceryStore}s, selects one based
   * purely on their ratings. The higher a stores rating, the more likely it
   * is to be chosen.
   *
   * @param stores
   *            the stores to choose from
   * @return a {@link AbstractGroceryStore}
   */
  public AbstractGroceryStore selectStore(final List<AbstractGroceryStore> stores) {
    if (stores == null) {
      throw new NullPointerException();
    }
    if (stores.size() < 1) {
      throw new IllegalArgumentException();
    }

    int storeIndex = rand.nextInt(stores.size());
    AbstractGroceryStore store = stores.get(storeIndex);
    double chance = rand.nextDouble();
    double rating = getRating(store);
    if (chance <= rating) {
      return store;
    }
    return selectStore(stores);
  }

  /**
   * Returns the total number of up and down votes.
   * @return the total number of up and down votes.
   */
  public int getTotalVotes() {
    return votes;
  }

  /**
   * Returns the {@link MyFacePagePlus} social networking site.
   *
   * @return the {@link MyFacePagePlus} social networking site.
   */
  public static MyFacePagePlus getSocialNetwork() {
    return MY_FACE_PAGE_PLUS_INSTANCE;
  }

  private static final MyFacePagePlus MY_FACE_PAGE_PLUS_INSTANCE = new MyFacePagePlus();
  /**
   * Constructs a MyFacePagePlus instance.
   */
  private MyFacePagePlus() {
    if (MY_FACE_PAGE_PLUS_INSTANCE != null) {
      throw new BigBrotherIsWatchingYouException(
          "There can be only one social network!");
    }
    this.ratings = new HashMap<AbstractGroceryStore, Double>();
  }

}
