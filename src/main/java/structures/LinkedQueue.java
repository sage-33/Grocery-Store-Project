package structures;

/**
 * A {@link LinkedQueue} is a queue that is implemented using a Linked queue structure to allow for
 * unbounded size meet the big-O time complexities specified in the interface.
 *
 * @param <T>
 *            the elements stored in the queue
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

    @Override
    public T peek() {
        if (isEmpty())
            throw new IllegalStateException("queue");

        return front.getElement();
    }

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

    @Override
    public boolean isEmpty() {
        if (count == 0) {
            return true;

        } else {
            return false;

        }
    }

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
