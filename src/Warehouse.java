import java.util.Arrays;
import floor_assets.*;

public class Warehouse {
	
	private Floor floor;
	private ArrayList<FaxMachine> faxMachines;
	private Server server;
	private Worker workers;
	
	/*
	 * Initialize parameters needed to operate a Warehouse 
	 */
	
	public Warehouse(){
		this.floor = new Floor();
		this.faxMachines = new ArrayList<FaxMachine>;
		this.server = new Server();
		this.workers = new Worker();
		
	}
	
	/*
	 * Get the fax machine
	 */

	public String getFaxMachines() {
		return this.faxMachines;
	}

	/*
	 * Get the floor plan of the WareHouse
	 */

	public Floor getFloor() {
		return floor;
	}

	/*
	 * Get the server for the WareHouse
	 */

	public Server getServer() {
		return server;
	}
	
	/*
	 * Get the workers working in the WareHouse
	 */

	public Worker getWorkers() {
		return workers;
	}	
}
