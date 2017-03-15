package entities.workers;

import java.util.*;

import entities.Stock;
import entities.Worker;
import entities.stocking.Fascia;
import entities.Warehouse;
import entities.vehicles.Forklift;

/*
 * The Picker class, a worker in the warehouse
 */

public class Picker extends Worker {

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
		this.warehouse.getFloor().getZone(c).getAisle().get(A[1]).getRacks().get(A[2]).getLevel().get(A[3])
				.removeItem(new Fascia(A[4]));
		forklift.addItem(new Fascia(A[4]));
		stack.push(new Fascia(A[4]));
		System.out.println("Depicted");
		if (this.warehouse.getFloor().getZone(c).getAisle().get(A[1]).getRacks().get(A[2]).getLevel().get(A[3])
				.numItem() <= 5) {
			throw new EmptyStackException();
		}
	}

}
