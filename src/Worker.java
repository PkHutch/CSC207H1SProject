import java.util.Arrays;

public abstract class Worker {
	
	private String name;
	
	/*
	 * Initialize parameter needed for each worker
	 */
	
	public Worker(){
		this.name = name;
	}
	
	/*
	 * get name of a worker
	 */

	public String getName() {
		return name;
	}
	
	/*
	 * abstract method that is implemented differently based on worker description
	 */
	
	abstract void doTask();
	
}
