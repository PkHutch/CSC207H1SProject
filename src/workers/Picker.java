package workers;

import java.util.*;
import Warehouse;
import vehicles.Forklift;
import stocking.*;


/*
 * The Picker class, a worker in the warehouse
 */

public class Picker extends Worker {

	private Forklift forklift;

	public Picker(String name) {
		super(name);
		this.forklift = new Forklift();
	}

	public void doTask(String location) {
		String[] result = location.split(",");
		int i = result[0].charAt(0) - 'A';
		int[] A = new int[5];
		A[0] = 0;
		for (int j = 1; j < 5; j++){
			A[j] = Integer.parseInt(result[j]);
		}
		warehouse.floor.getZone()[i].getAisle()[A[1]].getRacks()[A[2]].getLevel()[A[3]].removeItem(A[4]);
		forklift.addItem(new Stock(A[4]));
	}
}
