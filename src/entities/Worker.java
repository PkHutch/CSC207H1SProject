package entities;

public abstract class Worker<T> {
	private String name;
	private Warehouse warehouse;

	public Worker(String name, Warehouse warehouse) {
		this.name = name;
		this.warehouse = warehouse;
	}

	public String getName() {
		return this.name;
	}

        public Warehouse getWarehouse() {
                return this.warehouse;
        }

	public abstract void doTask(T argument);
}
