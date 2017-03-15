package entities.workers;

import java.util.*;
import entities.Worker;
import entities.vehicles.Forklift;
import entities.Stock;
import entities.Warehouse;

/*
 * The Picker class, a worker in the warehouse
 */

public class Picker extends Worker<String> {
	
	private Stack<Stock> stack;
	private Forklift forklift;

	public Picker(String name, Warehouse warehouse) {
		super(name, warehouse);
		this.forklift = new Forklift();
	}

	public void doTask(String location) {
		String[] result = location.split(",");
		char c = result[0].charAt(0);
		int[] A = new int[5];
		A[0] = 0;
		for (int j = 1; j < 5; j++) {
			A[j] = Integer.parseInt(result[j]);
		}
		this.getWarehouse().getFloor().getZone(result[0].charAt(0)).getAisle(Integer.parseInt(result[1])).getRack(Integer.parseInt(result[2])).getLevel(Integer.parseInt(result[3])).removeItem(new Stock(Integer.parseInt(result[4])));
		forklift.addItem(new Stock(A[4]));
		stack.push(new Stock(A[4]));
		if(warehouse.floor.getZone()[c].getAisle()[A[1]].getRacks()[A[2]].getLevel()[A[3]].numItem <= 5) {
			throw new EmptyStackException();
		}
	}

}
