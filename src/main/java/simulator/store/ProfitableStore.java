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
 * Constructs a Profitable Store that enters shoppers into either normal or express lines and then
 * enqueues them to registers, that turn on and off as necessary, while keeping track of the total
 * sales, cost, and profit, average wait time, and number of irate shoppers.
 * 
 * 
 * @author sagesilberman
 *
 */
public class ProfitableStore extends SimpleStore {

    // increase number of lines (express and normal) go to a line that is a specific register
    private AbstractRegister reg1;
    private AbstractRegister reg2;
    private AbstractRegister reg3;
    private AbstractRegister reg4;
    private AbstractRegister reg5;
    private AbstractRegister reg6;
    private AbstractRegister reg7;
    private AbstractRegister reg8;
    private AbstractRegister reg9;
    private AbstractRegister reg10;

    private List<CheckoutLineInterface> lines;
    private CheckoutLineInterface normalLine;
    private CheckoutLineInterface expressLine;
    private double totalCostCounter;

    /**
     * Constructs the registers and lines used by the shoppers
     */
    public ProfitableStore() {
        // express line registers
        reg1 = new SimpleRegister();
        reg1.turnOn();
        reg2 = new SimpleRegister();
        reg3 = new SimpleRegister();

        // normal line registers
        reg4 = new SimpleRegister();
        reg4.turnOn();
        reg5 = new SimpleRegister();
        reg5.turnOn();
        reg6 = new SimpleRegister();
        reg7 = new SimpleRegister();
        reg8 = new SimpleRegister();
        reg9 = new SimpleRegister();
        reg10 = new SimpleRegister();

        lines = new ArrayList<CheckoutLineInterface>();
        normalLine = new NormalLine();
        expressLine = new ExpressLine();
        lines.add(normalLine);
        lines.add(expressLine);
    }

    @Override
    public void tick() {
        if (!reg1.isBusy()) { // already on
            if (!expressLine.isEmpty()) {
                reg1.processShopper(expressLine.dequeue());
            }
            if (expressLine.size() > 2) {
                reg2.turnOn();
                if (!reg2.isBusy()) {
                    reg2.processShopper(expressLine.dequeue());
                } else if (!reg2.isBusy() && expressLine.size() < 2) {
                    reg2.turnOff();

                }
            }
        }
        if (reg1.isBusy() && reg2.isBusy() && expressLine.size() > 2) {
            reg3.turnOn();
            if (!reg3.isBusy()) {
                reg3.processShopper(expressLine.dequeue());
            } else if (!reg3.isBusy() && expressLine.size() < 2) {
                reg3.turnOff();
            }
        }
        if (!reg4.isBusy()) { // already on
            if (!normalLine.isEmpty()) {
                reg4.processShopper(normalLine.dequeue());
            }
        }
        if (!reg5.isBusy()) { // already on
            if (!normalLine.isEmpty()) {
                reg5.processShopper(normalLine.dequeue());
            }
        }
        if (reg4.isBusy() && reg5.isBusy() && normalLine.size() > 4) {
            reg6.turnOn();
            reg7.turnOn();
            reg8.turnOn();
        } else if (reg4.isBusy() && reg5.isBusy() && normalLine.size() > 7) {
            reg9.turnOn();
            reg10.turnOn();
            if (!reg9.isBusy()) {
                reg9.processShopper(normalLine.dequeue());
            }
            if (!reg10.isBusy()) {
                reg10.processShopper(normalLine.dequeue());
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
        transactions.addAll(reg3.getTransactions());
        transactions.addAll(reg4.getTransactions());
        transactions.addAll(reg5.getTransactions());
        transactions.addAll(reg6.getTransactions());
        transactions.addAll(reg7.getTransactions());
        transactions.addAll(reg8.getTransactions());
        transactions.addAll(reg9.getTransactions());
        transactions.addAll(reg10.getTransactions());

        return transactions;
    }

    @Override
    public double getTotalCost() {
        totalCostCounter = 0;
        for (Transaction transaction : getTransactions()) {
            for (GroceryInterface g : transaction.getReceipt().getGroceries()) {
                totalCostCounter += g.getCost();
            }

        }
        totalCostCounter += (reg1.getRunningCost() + reg2.getRunningCost() + reg3.getRunningCost()
                + reg4.getRunningCost() + reg5.getRunningCost() + reg6.getRunningCost()
                + reg7.getRunningCost() + reg8.getRunningCost() + reg9.getRunningCost()
                + reg10.getRunningCost());

        return totalCostCounter;
    }

}
