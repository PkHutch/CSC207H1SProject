import java.util.ArrayList;
import workers.Worker;
import floor_assets.*;
import workers.*;

/**
 * The warehouse class, which is where the simulation takes place.
 */
public class Warehouse {
	private ArrayList<FaxMachine> faxMachines;
	private Floor floor;
	private Server server;
	private Worker workers;
	private ArrayList<Worker> workerss;

	/**
	 * Initializes a Warehouse object.
	 */
	public Warehouse() {
		this.faxMachines = new ArrayList<FaxMachine>();
		this.floor = new Floor();
		this.server = new Server();
		this.workers = new Picker("Alice");
	}

	public Warehouse(int a, int b, int c, int d, int e, String[] workers) {
		this.faxMachines = new ArrayList<FaxMachine>();
		this.faxMachines.add(new FaxMachine(""));
		this.floor = new Floor(a, b, c, d, e);
		for (int i = 0; i < workers.length; i++) {
			switch (workers[i]) {
			case "Picker":
				this.workerss.add(new Picker(workers[i]));
			case "Loader":
				this.workerss.add(new Loader(workers[i]));
			case "Sequencer":
				this.workerss.add(new Sequencer(workers[i]));
			case "Resupplier":
				this.workerss.add(new Resupplier(workers[i]));

			default:
				System.out.println("This job is currently unavailbe");
			}
		}
	}

	/**
	 * Returns the fax machines of the Warehouse.
	 * 
	 * @return Returns the ArrayList of FaxMachine.
	 */
	public ArrayList<FaxMachine> getFaxMachines() {
		return this.faxMachines;
	}

	/**
	 * Return the floor object of the warehouse.
	 * 
	 * @return Returns the object which is the floor attribute.
	 */
	public Floor getFloor() {
		return floor;
	}

	/**
	 * Returns the server, which makes the decisions for the warehouse.
	 * 
	 * @return Returns the server object for the warehouse.
	 */
	public Server getServer() {
		return server;
	}

	/**
	 * Returns the workers, which do tasks within the warehouse.
	 * 
	 * @return Returns the workers of the warehouse.
	 */
	public Worker getWorkers() {
		return workers;
	}
}
