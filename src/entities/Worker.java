package entities;

public abstract class Worker {
	private String name;
	private Warehouse warehouse;

	public Worker(String name, Warehouse warehouse) {
		this.name = name;
		this.warehouse = warehouse;
	}

	public String getName() {
		return this.name;
	}

	public abstract void doTask();
}
