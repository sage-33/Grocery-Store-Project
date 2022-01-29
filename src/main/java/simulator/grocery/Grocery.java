package simulator.grocery;

public class Grocery implements GroceryInterface {

    // set up some attributes
    private String name;
    private double price;
    private double cost;
    private double handlingRating;

    // constructor - initialize attributes
    public Grocery(String n, double p, double c, double hr) {
        name = n;
        price = p;
        cost = c;
        handlingRating = hr;
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
