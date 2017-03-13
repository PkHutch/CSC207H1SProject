import java.util.ArrayList;
import workers.*;
import floor_assets.*;

/**
 * The warehouse class, which is where the simulation takes place.
 */
public class Warehouse {
	private ArrayList<FaxMachine> faxMachines;
	private Floor floor;
	private Server server;
	private ArrayList<Worker> workers;
	private Marshalling marshalling;

	/**
	 * Initializes a Warehouse object.
	 */
	public Warehouse() {
		this.faxMachines = new ArrayList<FaxMachine>();
		this.floor = new Floor();
		this.server = new Server();
		this.workers = new ArrayList<Worker>();
		this.marshalling = new Marshalling();
	}

	public Warehouse(int a, int b, int c, int d, int e) {
		this.server = new Server();
		this.faxMachines = new ArrayList<FaxMachine>();
		this.faxMachines.add(new FaxMachine());
		this.floor = new Floor(a, b, c, d, e);
		this.workers = new ArrayList<Worker>();
		this.marshalling = new Marshalling();
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
	 * Adds a Fax to the FaxMachine ArrayList
	 * 
	 * @return void
	 */
	public void addFax(FaxMachine f) {
		this.faxMachines.add(f);
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
	public ArrayList<Worker> getWorkers() {
		return workers;
	}

	/**
	 * Adds a worker to the Worker ArrayList, which do tasks within the
	 * warehouse/
	 * 
	 * @return void
	 */
	public void addWorker(Worker w) {
		this.workers.add(w);
	}
}
