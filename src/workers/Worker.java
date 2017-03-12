package workers;

import stocking.*;

/*
 * The Workers class in which different workers perform different tasks
 */

public abstract class Worker {
	private String name;
	private Warehouse warehouse;
	
	/*
	 * Initialize a worker object
	 */

	public Worker(String name) {
		this.name = name;
	}
	
	/*
	 * Returns the name of the workers.
     * @return Returns the String of Workers.
	 */

	public String getName() {
		return this.name;
	}
	
	/*
	 * Abstract method in which the Workers do their appropriate task
	 */

	public abstract void doTask(String location);
}
