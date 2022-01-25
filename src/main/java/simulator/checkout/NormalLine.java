package simulator.checkout;

import simulator.shopper.Shopper;
import structures.LinkedQueue;
import structures.QueueInterface;

public class NormalLine extends LinkedQueue<Shopper> implements CheckoutLineInterface {

    @Override
    public boolean canEnterLine(Shopper shopper) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public QueueInterface<Shopper> enqueue(Shopper shopper) {
        // check to see if they can enter the line if not then throw illegalArgument exceptions
        
        return super.enqueue(shopper);
    }

   
}
