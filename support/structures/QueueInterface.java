package structures;

/**
 * A {@link QueueInterface} is First In First Out (FIFO) container that supports
 * adding to the end and removing from the front.
 * @author jcollard, jddevaug
 * @param <T>
 *            The type of elements stored in this {@link QueueInterface}
 */
public interface QueueInterface<T> {

  /**
   * Removes the first element from this {@link QueueInterface} and returns
   * it. This operation should execute in constant time. That is, it should be
   * executed in O(1).
   * @return the first element that was removed from this
   *         {@link QueueInterfacE}
   * @throws IllegalStateException
   *             if this {@link QueueInterface} is empty.
   */
  T dequeue();

  /**
   * Returns the first element in this {@link QueueInterface} but does not
   * remove it. This operation should execute in constant time. That is, it
   * should be executed in O(1).
   * @return the first element of this {@link QueueInterface}
   * @throws IllegalStateException
   *             if this {@link QueueInterface} is empty.
   */
  T peek();

  /**
   * Adds {@code elem} to the end of this {@link QueueInterface}. This
   * operation should execute in constant time. That is, it should be executed
   * in O(1). For convenience, this method returns the modified
   * {@link QueueInterface}
   * @param elem
   *            the element to be added
   * @return the modified {@link QueueInterface}
   * @throws NullPointerException
   *             if {@code elem} is {@code null}
   */
  QueueInterface<T> enqueue(T elem);

  /**
   * Returns {@code true} if this {@link QueueInterface} does not contain any
   * elements and {@code false} otherwise.This operation should execute in
   * constant time. That is, it should be executed in O(1).
   * @return {@code true} if this {@link QueueInterface} does not contain any
   *         elements and {@code false} otherwise.
   */
  boolean isEmpty();

  /**
   * Returns the number of elements in this {@link QueueInterface}. This
   * operation should execute in constant time. That is, it should be executed
   * in O(1).
   * @return the number of elements in this {@link QueueInterface}
   */
  int size();

  /**
   * Returns a nicely formatted String representing this
   * {@link QueueInterface}. The returned String starts with an open square
   * bracket '[' and ending with a close square bracket ']'. Each element in
   * the Queue should then be written using its toString() method. The
   * elements are separated by a comma ',' and a space ' '. There should be no
   * space between the open bracket and the first element and no space between
   * the close bracket and the last element.
   * <pre>
   * Examples:
   * An empty Queue : []
   * A queue containing "Hello" : [Hello]
   * A queue containing 1, 2, and 3 : [1, 2, 3]
   * A queue containing "Hello" and "World" : [Hello, World]
   * </pre>
   * @return a nicely formatted String representing this
   *         {@link QueueInterface}.
   */
  String toString();

}
