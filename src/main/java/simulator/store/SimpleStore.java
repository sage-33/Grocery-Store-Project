package simulator.store;

import java.util.ArrayList;
import java.util.List;

import simulator.checkout.AbstractRegister;
import simulator.checkout.CheckoutLineInterface;
import simulator.checkout.ExpressLine;
import simulator.checkout.NormalLine;
import simulator.checkout.SimpleRegister;
import simulator.checkout.Transaction;
import simulator.grocery.GroceryInterface;

/**
 * Constructs a SimpleStore that enters shoppers into either normal or express lines and then
 * enqueues them to registers while keeping track of the total sales, cost, and profit, average wait
 * time, and number of irate shoppers.
 * 
 * @author sagesilberman
 *
 */
public class SimpleStore extends AbstractGroceryStore {

    protected List<AbstractRegister> registers;
    private AbstractRegister reg1;
    private AbstractRegister reg2;
    protected List<CheckoutLineInterface> lines;
    private CheckoutLineInterface normalLine;
    private CheckoutLineInterface expressLine;
    private double totalSalesCounter;
    private double totalCostCounter;
    private double avgWaitTimeCounter;
    private int irateShopperCounter;

    /**
     * Constructs the registers and lines used by the shoppers
     */
    public SimpleStore() {
        registers = new ArrayList<AbstractRegister>();
        reg1 = new SimpleRegister();
        reg2 = new SimpleRegister();
        registers.add(reg1);
        registers.add(reg2);
        reg1.turnOn();
        reg2.turnOn();

        lines = new ArrayList<CheckoutLineInterface>();
        normalLine = new NormalLine();
        expressLine = new ExpressLine();
        lines.add(expressLine);
        lines.add(normalLine);
    }

    @Override
    public void tick() {
        if (!reg2.isBusy()) {
            if (!expressLine.isEmpty()) {
                reg1.processShopper(expressLine.dequeue());
            }
        }
        if (!reg2.isBusy()) {
            if (!normalLine.isEmpty()) {
                reg2.processShopper(normalLine.dequeue());
            }
        }
    }

    @Override
    public List<CheckoutLineInterface> getLines() {
        return lines;
    }

    @Override
    public List<Transaction> getTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        transactions.addAll(reg1.getTransactions());
        transactions.addAll(reg2.getTransactions());

        return transactions;
    }

    @Override
    public double getAverageWaitingTime() {
        avgWaitTimeCounter = 0;
        for (Transaction transaction : getTransactions()) {
            avgWaitTimeCounter += transaction.getShopper().getWaitingTime();
        }
        return avgWaitTimeCounter / getNumberOfShoppers();
    }

    @Override
    public double getTotalSales() {
        totalSalesCounter = 0;
        for (Transaction transaction : getTransactions()) {
            totalSalesCounter += transaction.getReceipt().getSaleValue();
        }
        return totalSalesCounter;
    }

    @Override
    public double getTotalCost() {
        totalCostCounter = 0;
        for (Transaction transaction : getTransactions()) {
            for (GroceryInterface g : transaction.getReceipt().getGroceries()) {
                totalCostCounter += g.getCost();
            }

        }
        totalCostCounter += (reg1.getRunningCost() + reg2.getRunningCost());

        return totalCostCounter;
    }

    @Override
    public double getTotalProfit() {
        return getTotalCost() - getTotalSales();
    }

    @Override
    public int getNumberOfShoppers() {
        return getTransactions().size();
    }

    @Override
    public int getNumberOfIrateShoppers() {
        irateShopperCounter = 0;
        for (Transaction transaction : getTransactions()) {
            if (transaction.getShopper().isIrate()) {
                irateShopperCounter++;
            }
        }
        return irateShopperCounter;
    }

}
