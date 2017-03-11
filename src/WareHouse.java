import floor_assets.*;
import java.util.ArrayList;

/*
 * Warehouse class acts as container for the entities of the project. 
 * Stores all the components required to execute the handling of
 * bumper fascias.
 */

public class WareHouse {
	
	private Floor floor;
	public FaxMachine fax;
	private Server server;
	private Worker workers;
	
	/*
	 * Initialize parameters needed to operate a Warehouse 
	 */
	
	public WareHouse(){
		this.floor = new Floor();
		this.fax = new FaxMachine();
		this.server = new Server();
		this.workers = new Worker();
		
	}
	
	/*
	 * Get the fax machine
	 */

	public String getFaxMachine() {
		return fax;
	}
	
	/*
	 * Set a fax for the WareHouse
	 */

	public void setFaxMachine(FaxMachine fax) {
		this.fax = fax;
	}
	
	/*
	 * Get the floor plan of the WareHouse
	 */

	public Floor getFloor() {
		return floor;
	}
	
	/*
	 * Set a floor plan for the WareHouse
	 */
	
	public void setFloor(Floor floor) {
		this.floor = floor;
	}
	
	/*
	 * Get the server for the WareHouse
	 */

	public Server getServer() {
		return server;
	}
	
	/*
	 * Set Server for the WareHouse
	 */

	public void setServer(Server server) {
		this.server = server;
	}
	
	/*
	 * Get the workers working in the WareHouse
	 */

	public Worker getWorkers() {
		return workers;
	}
	
	/*
	 * Set the workers for the WareHouse
	 */

	public void setWorkers(Worker workers) {
		this.workers = workers;
	}
	
}
