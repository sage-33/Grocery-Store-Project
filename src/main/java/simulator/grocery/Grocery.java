package simulator.grocery;

public class Grocery implements GroceryInterface {
    
    // set up some attributes
    private String name;
    
    // constructor - initialize attributes
    public Grocery(String n) {
        name = n;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getCost() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getHandlingRating() {
        // TODO Auto-generated method stub
        return 0;
    }

}
