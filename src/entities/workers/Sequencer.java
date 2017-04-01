package entities.workers;

import entities.Warehouse;
import entities.taskentities.TaskGiver;

public class Sequencer extends Worker implements TaskGiver{

	public Sequencer(String name, Warehouse warehouse) {
		super(name, warehouse);
	}
	
	public void doTask() {
		
	}

}
