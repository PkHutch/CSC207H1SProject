package workers;

import warehouse.*;

public abstract class Worker {
	private String name;
	protected Warehouse warehouse;

	public Worker(String name, Warehouse warehouse) {
		this.name = name;
		this.warehouse = warehouse;
	}

	public String getName() {
		return this.name;
	}

}
