package simulator.checkout;

import simulator.shopper.Shopper;
import structures.LinkedQueue;
import structures.QueueInterface;

/**
 * Constructs a NormalLine with a shopper if they can enter the line.
 * 
 * @author sagesilberman
 *
 */
public class NormalLine extends LinkedQueue<Shopper> implements CheckoutLineInterface {

    @Override
    public boolean canEnterLine(Shopper shopper) {
        if (shopper == null) {
            throw new NullPointerException();
        }
        return true;

    }

    @Override
    public QueueInterface<Shopper> enqueue(Shopper shopper) {
        // check to see if they can enter the line if not then throw illegalArgument exceptions
        if (!canEnterLine(shopper)) {
            throw new IllegalArgumentException();
        }
        return super.enqueue(shopper);
    }

}
