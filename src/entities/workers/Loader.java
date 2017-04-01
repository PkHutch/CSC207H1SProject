package entities.workers;

import entities.Warehouse;

public class Loader extends Worker{

	public Loader(String name, Warehouse warehouse) {
		super(name, warehouse);
	}


	public void doTask(Loader argument) {
		//Dumps the pallets in Marshalling
		//Marshalling marshalling= this.getWarehouse().getMarshalling;
		//marshalling.clearPallets();
	}

}
