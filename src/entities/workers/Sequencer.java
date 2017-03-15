package entities.workers;

import entities.Worker;
import entities.Warehouse;

public class Sequencer extends Worker {

	public Sequencer(String name, Warehouse warehouse) {
		super(name, warehouse);
	}

	public void doTask(String location) {
		;
	}
}
