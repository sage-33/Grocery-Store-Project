package simulator.checkout;

import simulator.shopper.Shopper;

/**
 * Constructs an ExpressLine for if a shopper has 15 items or less.
 * 
 * @author sagesilberman
 *
 */
public class ExpressLine extends NormalLine {

    @Override
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
