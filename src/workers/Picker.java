package workers;

import java.util.*;
import
import vehicles.Forklift;
import stocking.*;

/*
 * The Picker class, a worker in the warehouse
 */

public class Picker extends Worker {

	private Forklift forklift;
	
	/*
	 * Initialize a Picker object
	 */

	public Picker(String name, Warehouse warehouse) {
		super(name, warehouse);
		this.forklift = new Forklift();
	}
	
	/*
	 * Complete the designated picker task of picking stock
	 */

	public void doTask(String location) {
		String[] result = location.split(",");
		int i = result[0].charAt(0) - 'A';
		int[] A = new int[5];
		A[0] = 0;
		for (int j = 1; j < 5; j++){
			A[j] = Integer.parseInt(result[j]);
		}
		warehouse.floor.getZone()[i].getAisle()[A[0]].getRacks()[A[1]].getLevel()[A[2]].removeItem(A[3]) ;
	}
}
