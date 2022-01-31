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

    // initializes attributes
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
