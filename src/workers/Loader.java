package workers;

import stocking.*;
import vehicles.Trucks;

/*
 * The Loader class, a worker in the warehouse
 */

public class Loader extends Worker {
	private Trucks truck;
	
	/*
	 * Initialize a Loader Object
	 */

	public Loader(String name, Warehouse, warehouse) {
		super(name, warehouse);
		this.truck = new Trucks();
	}
	
	/*
	 * Complete the loader task of loading Stock onto trucks
	 */

	public void doTask(String location) {
		;
	}
}
