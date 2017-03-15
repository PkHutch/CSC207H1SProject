package entities.workers;

import entities.Warehouse;
import entities.Worker;
import entities.Pallet;
import entities.vehicles.Trucks;

public class Loader extends Worker {
	private Trucks truck;

	public Loader(String name, Warehouse warehouse) {
		super(name, warehouse);
		this.truck = new Trucks();
	}

	public void doTask() {
		Pallet front = this.getWarehouse().getMarshalling().removePallet(new Pallet());
		Pallet back = this.getWarehouse().getMarshalling().removePallet(new Pallet());
	}

	public void doTask(String location) {
		Pallet front = warehouse.getMarshalling().removePallet(warehouse.getMarshalling().marshallingPallet.get(0));
		Pallet back = warehouse.getMarshalling().removePallet(warehouse.getMarshalling().marshallingPallet.get(0));

		truck.addItem(front);
		truck.addItem(back);
	}
}
