package workers;

import vehicles.Trucks;

public class Loader extends Worker {
	private Trucks truck;

	public Loader(String name, Warehouse warehouse) {
		super(name, warehouse);

		this.truck = new Trucks();
	}

	public void doTask(String location) {
		Pallet front = warehouse.marshalling.removePallets();
		Pallet back = warehouse.marshalling.removePallets();
		truck.addItem(front);
		truck.addItem(back);
	}
}
