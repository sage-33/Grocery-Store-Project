package simulator.bigbrother;

/**
 * A {@link BigBrotherIsWatchingYouException} is thrown if it can be determined
 * that your code base violates some requirement of the simulation.
 *
 * @author jcollard, jddevaug
 *
 */
public class BigBrotherIsWatchingYouException extends RuntimeException {

  private static final long serialVersionUID = 3397264524349256866L;
  /**
   * Constructs an {@link Exception} to be thrown.
   */
  public BigBrotherIsWatchingYouException() {
    super();
  }
  /**
   * Constructs an {@link Exception} with the message
   * to be thrown.
   * @param message
   *                the message to be thrown
   */
  public BigBrotherIsWatchingYouException(final String message) {
    super(message);
  }

}
