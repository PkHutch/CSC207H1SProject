import java.util.Arrays;

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
