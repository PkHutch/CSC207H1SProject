package entities.workers;

import entities.Warehouse;
import entities.stocking.Fascia;
import entities.Pallet;
import entities.vehicles.Trucks;
import entities.Worker;

public class Loader extends Worker {
	private Trucks truck;

	public Loader(String name, Warehouse warehouse) {
		super(name, warehouse);
		this.truck = new Trucks();
	}

	public void doTask() {
		Pallet front = warehouse.getMarshalling().removePallet(new Pallet());
		Pallet back = warehouse.getMarshalling().removePallet(new Pallet());
		truck.addItem(front);
		truck.addItem(back);
	}
}
