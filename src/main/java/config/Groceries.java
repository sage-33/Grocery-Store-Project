package config;

import simulator.grocery.Grocery;
import simulator.grocery.GroceryInterface;

/**
 * This file contains your implementation of some {@link GroceryInterface} items that must be
 * available in your {@link GroceryStore}.
 * 
 * @author jcollard, jddevaug, sagesilberman
 */
public final class Groceries {

    /**
     * Returns your implementation of Milk.
     * 
     * <pre>
     * getName() returns "Milk"
     * getPrice() returns 3.29
     * getCost() returns 1.25
     * getHandlingRating() returns 0.1
     * </pre>
     * 
     * @return your implementation of Milk
     */
    public static GroceryInterface getMilk() {
        return new Grocery("Milk", 3.29, 1.25, 0.1);
    }

    /**
     * Returns your implementation of Eggs.
     * 
     * <pre>
     * getName() returns "Eggs"
     * getPrice() returns 2.29
     * getCost() returns .25
     * getHandlingRating() returns 0.8
     * </pre>
     * 
     * @return your implementation of Eggs
     */
    public static GroceryInterface getEggs() {
        return new Grocery("Eggs", 2.29, .25, 0.8);
    }

    /**
     * Returns your implementation of a Cold Pocket.
     * 
     * <pre>
     * getName() returns "Cold Pocket"
     * getPrice() returns 0.49
     * getCost() returns 0.02
     * getHandlingRating() returns 0.13
     * </pre>
     * 
     * @return your implementation of Cold Pocket
     */
    public static GroceryInterface getColdPocket() {
        return new Grocery("Cold Pocket", 0.49, 0.02, 0.13);
    }

    /**
     * Returns your implementation of Chips.
     * 
     * <pre>
     * getName() returns "Chips"
     * getPrice() returns 3.19
     * getCost() returns 0.50
     * getHandlingRating() returns 0.4
     * </pre>
     * 
     * @return your implementation of Chips
     */
    public static GroceryInterface getChips() {
        return new Grocery("Chips", 3.19, 0.50, 0.4);
    }

    /**
     * Returns your implementation of Beef.
     * 
     * <pre>
     * getName() returns "Beef"
     * getPrice() returns 3.39
     * getCost() returns 1.14
     * getHandlingRating() returns 0.75
     * </pre>
     * 
     * @return your implementation of Beef
     */
    public static GroceryInterface getBeef() {
        return new Grocery("Beef", 3.39, 1.14, 0.75);
    }

    /**
     * Returns your implementation of Apple.
     * 
     * <pre>
     * getName() returns "Apple"
     * getPrice() returns 0.69
     * getCost() returns 0.17
     * getHandlingRating() returns 0.25
     * </pre>
     * 
     * @return your implementation of Apple
     */
    public static GroceryInterface getApple() {
        return new Grocery("Apple", 0.69, 0.17, 0.25);
    }

}
