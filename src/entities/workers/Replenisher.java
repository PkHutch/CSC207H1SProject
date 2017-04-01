package entities.workers;

import entities.Warehouse;
import entities.taskentities.TaskExecutor;

public class Replenisher extends Worker implements TaskExecutor<String>{

	public Replenisher(String name, Warehouse warehouse) {
		super(name, warehouse);
	}
	
	public void doTask(){
		//replenishes
	}

}
