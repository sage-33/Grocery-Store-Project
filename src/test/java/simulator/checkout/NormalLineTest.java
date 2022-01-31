package simulator.checkout;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import config.Configuration;
import config.Groceries;
import simulator.grocery.GroceryInterface;
import simulator.shopper.Shopper;

public class NormalLineTest {

    private Shopper bob;
    private Shopper pete;
    private Shopper mete;

    @Before
    public void setUp() throws Exception {
        LinkedList<GroceryInterface> shortList = new LinkedList<GroceryInterface>();
        shortList.add(Groceries.getApple());
        shortList.add(Groceries.getBeef());

        bob = new Shopper(shortList);

        LinkedList<GroceryInterface> whereIsTheBeef = new LinkedList<GroceryInterface>();
        for (int i = 0; i < 15; i++)
            whereIsTheBeef.add(Groceries.getBeef());

        pete = new Shopper(whereIsTheBeef);

        LinkedList<GroceryInterface> tooMuchBeef = new LinkedList<GroceryInterface>();
        for (int i = 0; i < 100; i++)
            tooMuchBeef.add(Groceries.getBeef());

        mete = new Shopper(tooMuchBeef);
    }

    @Test(timeout = 100, expected = NullPointerException.class)
    public void testNPE1() {
        Configuration.getNormalLine().enqueue(null);
    }

    @Test(timeout = 100, expected = NullPointerException.class)
    public void testNPE2() {
        Configuration.getNormalLine().canEnterLine(null);
    }

    // logic test 1
    @Test(timeout = 100)
    public void test2() {
        CheckoutLineInterface col = Configuration.getNormalLine();
        assertTrue(col.canEnterLine(pete));
        col.enqueue(pete);
        assertTrue(col.canEnterLine(mete));
        col.enqueue(mete);
        assertTrue(col.canEnterLine(bob));
        col.enqueue(bob);

        assertEquals(pete, col.dequeue());
        assertEquals(mete, col.dequeue());
        assertEquals(bob, col.dequeue());
    }

    // logic test 2
    @Test(timeout = 100)
    public void test3() {
        CheckoutLineInterface col = Configuration.getNormalLine();
        assertTrue(col.canEnterLine(mete));
        col.enqueue(mete);
        assertTrue(col.canEnterLine(bob));
        col.enqueue(bob);
        assertTrue(col.canEnterLine(pete));
        col.enqueue(pete);

        assertEquals(mete, col.dequeue());
        assertEquals(bob, col.dequeue());
        assertEquals(pete, col.dequeue());
    }

    @Test(timeout = 100)
    public void test() {
        CheckoutLineInterface col = Configuration.getNormalLine();
        assertTrue(col.canEnterLine(bob));
        col.enqueue(bob);
        assertTrue(col.canEnterLine(pete));
        col.enqueue(pete);

        assertEquals(bob, col.dequeue());
        assertEquals(pete, col.dequeue());
    }

    // exception test
    @Test(timeout = 100, expected = NullPointerException.class)
    public void testNPE3() {
        Configuration.getNormalLine().canEnterLine(mete);
        Configuration.getNormalLine().canEnterLine(pete);
        Configuration.getNormalLine().canEnterLine(null);

        Configuration.getNormalLine().canEnterLine(bob);
    }
}
