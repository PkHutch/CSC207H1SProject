package entities.workers;

import warehouse.*;
import stocking.Pallet;
import vehicles.Trucks;

public class Loader extends Worker {
	private Trucks truck;

	public Loader(String name, Warehouse warehouse) {
		super(name, warehouse);
		this.truck = new Trucks();
	}

	public void doTask(String location) {
		Pallet front = warehouse.getMarshalling().removePallet(warehouse.getMarshalling().marshallingPallet.get(0));
		Pallet back = warehouse.getMarshalling().removePallet(warehouse.getMarshalling().marshallingPallet.get(0));
		truck.addItem(front);
		truck.addItem(back);
	}
}
