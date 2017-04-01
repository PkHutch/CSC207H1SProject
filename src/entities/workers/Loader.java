package entities.workers;

import entities.Warehouse;
import entities.taskentities.TaskGiver;

public class Loader extends Worker implements TaskGiver{

	public Loader(String name, Warehouse warehouse) {
		super(name, warehouse);
	}


	public void doTask() {
		//Dumps the pallets in Marshalling
		//Marshalling marshalling= this.getWarehouse().getMarshalling;
		//marshalling.clearPallets();
	}

}
