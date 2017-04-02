package entities.workers;

import entities.taskentities.TaskExecutor;
import entities.Warehouse;
import entities.Level;

public class Replenisher extends Worker implements TaskExecutor<String> {

	public Replenisher(String name, Warehouse warehouse) {
		super(name, warehouse);
	}

	public void doTask(String location) {
		String[] level = location.split(" ");
		char zone = level[0].charAt(0);
		Integer aisle = Integer.parseInt(level[1]);
		Integer rack = Integer.parseInt(level[2]);
		Integer currlevel = Integer.parseInt(level[3]);

		Level nextLevel = this.getWarehouse().getFloor().getItem(zone).getItem(aisle).getItem(rack).getItem(currlevel);
		nextLevel.addStock(nextLevel.getMaxCapacity() - nextLevel.getStock());
	}
}