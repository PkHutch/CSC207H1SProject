package workers;

public abstract class Worker {
	private String name;

	public Worker(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void doTask() {
		;
	}
}
