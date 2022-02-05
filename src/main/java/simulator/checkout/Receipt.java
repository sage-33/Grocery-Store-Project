package simulator.checkout;

import java.util.List;

import simulator.grocery.GroceryInterface;

/**
 * Constructs a Receipt with the total price of each grocery item and applied discount
 * 
 * @author sagesilberman
 *
 */
public class Receipt extends AbstractReceipt {

    public Receipt(List<GroceryInterface> groceries, double discount) {
        super(groceries, discount);
    }

    @Override
    public double getSubtotal() {
        double subtotal = 0;
        for (GroceryInterface g : super.getGroceries()) {
            subtotal += g.getPrice();
        }
        return subtotal;
    }

    @Override
    public double getSaleValue() {
        return getSubtotal() - (getSubtotal() * getDiscount());
    }

}
