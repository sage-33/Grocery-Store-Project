package simulator.checkout;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import simulator.grocery.GroceryInterface;
import config.Configuration;
import config.Groceries;

public class AbstractReceiptTest {

    private List<GroceryInterface> groceries;

    @Before
    public void setup() {
        groceries = new LinkedList<GroceryInterface>();
        groceries.add(Groceries.getApple());
        groceries.add(Groceries.getMilk());
        groceries.add(Groceries.getBeef());
    }

    @Test(timeout = 100)
    public void testSubTotal() {
        AbstractReceipt receipt = Configuration.getReceiptImplementation(groceries, 0.0);
        double expectedSubTotal = Groceries.getApple().getPrice() + Groceries.getMilk().getPrice()
                + Groceries.getBeef().getPrice();
        assertEquals(expectedSubTotal, receipt.getSubtotal(), 0.00001);
    }

    @Test(timeout = 100)
    public void testSaleValue1() {
        AbstractReceipt receipt = Configuration.getReceiptImplementation(groceries, 0.25);
        double expectedSubTotal = Groceries.getApple().getPrice() + Groceries.getMilk().getPrice()
                + Groceries.getBeef().getPrice();
        assertEquals(expectedSubTotal, receipt.getSubtotal(), 0.00001);

        double expectedSaleValue = expectedSubTotal - (expectedSubTotal * 0.25);
        assertEquals(expectedSaleValue, receipt.getSaleValue(), 0.00001);
    }

    @Test(timeout = 100)
    public void testSaleValue2() {
        AbstractReceipt receipt = Configuration.getReceiptImplementation(groceries, 0.15);
        double expectedSubTotal = Groceries.getApple().getPrice() + Groceries.getMilk().getPrice()
                + Groceries.getBeef().getPrice();
        assertEquals(expectedSubTotal, receipt.getSubtotal(), 0.00001);

        double expectedSaleValue = expectedSubTotal - (expectedSubTotal * 0.15);
        assertEquals(expectedSaleValue, receipt.getSaleValue(), 0.00001);
    }

    // logic test 1
    @Test(timeout = 100)
    public void testSaleValue3() {
        AbstractReceipt receipt = Configuration.getReceiptImplementation(groceries, 0.50);
        double expectedSubTotal = Groceries.getMilk().getPrice() + Groceries.getBeef().getPrice()
                + Groceries.getApple().getPrice();
        assertEquals(expectedSubTotal, receipt.getSubtotal(), 0.00001);

        double expectedSaleValue = expectedSubTotal - (expectedSubTotal * 0.50);
        assertEquals(expectedSaleValue, receipt.getSaleValue(), 0.00001);
    }

    // logic test 2
    @Test(timeout = 100)
    public void testSaleValue4() {
        AbstractReceipt receipt = Configuration.getReceiptImplementation(groceries, 0.75);
        double expectedSubTotal = Groceries.getBeef().getPrice() + Groceries.getMilk().getPrice()
                + Groceries.getApple().getPrice();
        assertEquals(expectedSubTotal, receipt.getSubtotal(), 0.00001);

        double expectedSaleValue = expectedSubTotal - (expectedSubTotal * 0.75);
        assertEquals(expectedSaleValue, receipt.getSaleValue(), 0.00001);
    }

    // exception test
    @Test(timeout = 100, expected = IllegalArgumentException.class)
    public void TestIllegalArgumentException() {
        AbstractReceipt receipt = Configuration.getReceiptImplementation(groceries, 2.0);
        double expectedSubTotal = Groceries.getMilk().getPrice() + Groceries.getBeef().getPrice()
                + Groceries.getApple().getPrice();
        assertEquals(expectedSubTotal, receipt.getSubtotal(), 0.00001);

        double expectedSaleValue = expectedSubTotal - (expectedSubTotal * 2.0);
        assertEquals(expectedSaleValue, receipt.getSaleValue(), 0.00001);
    }
}
