package workers;

import warehouse.Marshalling;
import warehouse.Warehouse;
import stocking.Fascia;
import stocking.Pallet;
import vehicles.Trucks;

public class Loader extends Worker {
	private Trucks truck;
	private Warehouse warehouse;

	public Loader(String name, Warehouse warehouse) {
		super(name);
		this.warehouse = warehouse;
		this.truck = new Trucks();
	}

	public void doTask(String location) {
		Pallet front = warehouse.getMarshalling().removePallet(new Pallet());
		Pallet back = warehouse.getMarshalling().removePallet(new Pallet());
		truck.addItem(front);
		truck.addItem(back);
	}

	@Override
	public void doTask() {
		// TODO Auto-generated method stub

	}
}
