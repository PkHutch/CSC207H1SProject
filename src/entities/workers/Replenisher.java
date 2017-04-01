package entities.workers;

import entities.Warehouse;

public class Replenisher extends Worker {

	public Replenisher(String name, Warehouse warehouse) {
		super(name, warehouse);
	}
	
	public void doTask(){
		//replenishes
	}

}
