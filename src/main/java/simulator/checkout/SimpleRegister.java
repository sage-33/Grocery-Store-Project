package simulator.checkout;

import simulator.shopper.Shopper;

/**
 * Constructs a SimpleRegister that facilitates creating Transactions for Shoppers who visit a
 * Grocery Store.
 * 
 * @author sagesilberman
 *
 */
public class SimpleRegister extends AbstractRegister {

    @Override
    protected Transaction createTransaction(Shopper s) {
        int timesteps = 0;
        // receipt with all of shoppers groceries (no discounts or sale value)
        Receipt receipt = new Receipt(s.getShoppingList(), 0);
        s.getShoppingList();

        if (s.getShoppingList().size() == 0) {
            timesteps = 1;
        } else {
            timesteps = 4 * s.getShoppingList().size();
        }
        return new Transaction(receipt, s, timesteps);
    }

}
