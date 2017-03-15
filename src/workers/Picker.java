package workers;

import java.util.*;
import warehouse.*;
import vehicles.Forklift;
import stocking.*;

/*
 * The Picker class, a worker in the warehouse
 */

public class Picker extends Worker {
	
	private Stack<Stock> stack;
	private Forklift forklift;
	private Warehouse warehouse;

	public Picker(String name, Warehouse warehouse) {
		super(name, warehouse);
		this.forklift = new Forklift();
		this.warehouse = warehouse;
	}

	public void doTask(String location) {
		String[] result = location.split(",");
		char c = result[0].charAt(0);
		int[] A = new int[5];
		A[0] = 0;
		for (int j = 1; j < 5; j++) {
			A[j] = Integer.parseInt(result[j]);
		}
		warehouse.floor.getZone()[c].getAisle()[A[1]].getRacks()[A[2]].getLevel()[A[3]].removeItem(new Stock(A[4]));
		forklift.addItem(new Stock(A[4]));
		stack.push(new Stock(A[4]));
		if(warehouse.floor.getZone()[c].getAisle()[A[1]].getRacks()[A[2]].getLevel()[A[3]].numItem <= 5) {
			throw new EmptyStackException();
		}
	}

}
