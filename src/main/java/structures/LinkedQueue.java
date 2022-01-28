package structures;

/**
 * A {@link LinkedQueue} is a queue that is implemented using a Linked queue structure to allow for
 * unbounded size meet the big-O time complexities specified in the interface.
 *
 * @param <T>
 *            the elements stored in the stack
 * 
 * @author sagesilberman
 */
public class LinkedQueue<T> implements QueueInterface<T> {
    private int count; // indicates number of elements stored
    private LinearNode<T> front, rear; // indicates the front and read nodes of the queue

    public LinkedQueue() {
        count = 0;
        front = rear = null;

    }

    @Override
    /**
     * Removes the element at the front of this queue and returns a reference to it. Throws an
     * EmptyCollectionException if the queue is empty.
     *
     * @return the element at the front of this queue
     * @throws IllegalStateException
     *             if an empty collection exception occurs
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("queue");
        }
        T result = front.getElement();
        front = front.getNext();
        count--;

        if (isEmpty()) {
            rear = null;
        }

        return result;
    }

    /**
     * Returns the front most element of this queue.
     * 
     * @return the front most element of this queue.
     * @throws IllegalStateException
     *             if the queue is empty
     * 
     */
    @Override
    public T peek() {
        if (isEmpty())
            throw new IllegalStateException("queue");

        return front.getElement();
    }

    /**
     * Adds the specified element to the rear of this queue.
     *
     * @param element
     *            the element to be added to the rear of this queue
     * @throws NullPointerException
     *             if the element is empty
     */
    @Override
    public QueueInterface<T> enqueue(T elem) {
        LinearNode<T> node = new LinearNode<T>(elem);
        if (elem == null) {
            throw new NullPointerException("queue");
        }
        if (isEmpty()) {
            front = node;
        } else
            rear.setNext(node);

        rear = node;
        count++;

        // return the queue itself
        return this;
    }

    /**
     * 
     * Returns {@code true} if the queue contains no elements and {@code false} otherwise.
     * 
     * @return {@code true} if the queue contains no elements and {@code false} otherwise.
     * 
     */
    @Override
    public boolean isEmpty() {
        if (count == 0) {
            return true;

        } else {
            return false;

        }
    }

    /**
     * Returns the number of elements in this queue.
     * 
     * @return the number of elements in this queue.
     */
    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        // use string builder because it's faster and more memory efficient
        StringBuilder result = new StringBuilder();
        result.append("[");
        // start at the front of the list
        LinearNode<T> copy = this.front; // this line will work once you copy in your LinearNode
                                         // class

        // REALLY IMPORTANT FOR STRUCTRES LIKE THESE!!!
        while (copy != null) {
            result.append(copy.getElement().toString());
            if (copy.getNext() != null) {
                result.append(", ");
            }
            // move to the next node
            copy = copy.getNext();
        }

        return result.append("]").toString();
    }

}
