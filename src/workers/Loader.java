package workers;

import vehicles.Trucks;

public class Loader extends Worker {
	private Trucks truck;

	public Loader(String name) {
		super(name);
		this.truck = new Trucks();
	}

	public void doTask() {
		;
	}
}
