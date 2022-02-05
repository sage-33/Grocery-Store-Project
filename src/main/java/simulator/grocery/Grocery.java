package simulator.grocery;

/**
 * Constructs a Grocery item with it's name, price, cost, and handling rating.
 * 
 * @author sagesilberman
 *
 */
public class Grocery implements GroceryInterface {

    // set up attributes
    private String name;
    private double price;
    private double cost;
    private double handlingRating;

    /**
     * Initializes attributes
     * 
     * @param n
     *            the name of the grocery item
     * @param p
     *            the price of the item
     * @param c
     *            what the store pays
     * @param hr
     *            the difficulty to handle the item
     */
    public Grocery(String n, double p, double c, double hr) {
        name = n; // name variable
        price = p; // what the shopper pays
        cost = c; // what the store pays
        handlingRating = hr; // difficulty to handle this
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public double getHandlingRating() {
        return handlingRating;
    }

}
