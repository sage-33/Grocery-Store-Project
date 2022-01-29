package simulator.checkout;

import simulator.shopper.Shopper;
import structures.QueueInterface;

public class ExpressLine extends NormalLine {

    public boolean canEnterLine(Shopper shopper) {
        if (shopper == null) {
            throw new NullPointerException();
        } else if (shopper.getShoppingList().size() > 15) {
            return false;
        } else {
            return true;
        }
    }
}
