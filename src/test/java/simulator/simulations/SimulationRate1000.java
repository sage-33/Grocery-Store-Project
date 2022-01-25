package simulator.simulations;

import simulator.bigbrother.BigBrother;
import simulator.store.AbstractGroceryStore;
import simulator.world.SimpleWorld;
import config.Configuration;

public class SimulationRate1000 {

	public static void main(String ... args){
		
		// Creates the world
		new SimpleWorld(1000);
		
		// Selects the store for the simulation
		AbstractGroceryStore store = Configuration.getProfitableStore();
		
		while(!BigBrother.getBigBrother().tick());
		
		System.out.println("Number Of Shoppers: " + store.getNumberOfShoppers());
		System.out.println("Average waiting time: " + store.getAverageWaitingTime());
		System.out.println("Number of Irate Shoppers: " + store.getNumberOfIrateShoppers());
		System.out.println("Total sales: " + store.getTotalSales());
		System.out.println("Total cost: " + store.getTotalCost());
		System.out.println("Total profit: " + store.getTotalProfit());
		
		if(store.getTotalProfit() > 0){
			System.out.println("The store was profitable.");
		}else {
			System.out.println("The store was not profitable.");
		}
		
	}
	
}
