package workers;

import java.util.*;
import warehouse.Warehouse;
import vehicles.Forklift;
import stocking.*;

/*
 * The Picker class, a worker in the warehouse
 */

public class Picker extends Worker {

	private Forklift forklift;
	private Warehouse warehouse;

	public Picker(String name, Warehouse warehouse) {
		super(name);
		this.forklift = new Forklift();
		this.warehouse = warehouse;
	}

	public void doTask(String location) {
		String[] result = location.split(",");
		int i = result[0].charAt(0) - 'A';
		int[] A = new int[5];
		A[0] = 0;
		for (int j = 1; j < 5; j++) {
			A[j] = Integer.parseInt(result[j]);
		}

	}

	@Override
	public void doTask() {
		// TODO Auto-generated method stub

	}

}
