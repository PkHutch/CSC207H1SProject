package entities.workers;

import warehouse.*;
import stocking.Fascia;
import stocking.Pallet;
import vehicles.Trucks;

public class Loader extends Worker {
	private Trucks truck;

	public Loader(String name, Warehouse warehouse) {
		super(name, warehouse);
		this.truck = new Trucks();
	}

	public void doTask(String location) {
		Pallet front = warehouse.getMarshalling().removePallet(new Pallet());
		Pallet back = warehouse.getMarshalling().removePallet(new Pallet());
		truck.addItem(front);
		truck.addItem(back);
	}
}
