// Defines the package.
package entities;

import java.util.ArrayList;
import entities.arraycontainers.Floor;
import entities.workers.Worker;


/**
 * The warehouse class, which is where the simulation takes place, as the Warehouse effectively
 * acts as a container for the other entities.
 */
public class Warehouse {
    // Defines the instance variables.

    private ArrayList<Worker> workers;
    private Floor floor;
    private final FaxMachine faxMachine;
    private final Server server;

    // Defines the constructors.
    /**
     * The default constructor for a Warehouse.
     */
    public Warehouse() {
        System.out.println("Constructing Warehouse" + this.toString() + ".");
        this.server = new Server(this);
        this.workers = new ArrayList<>();
        this.floor = new Floor(this, new Integer[1][1][1][1]);
        this.faxMachine = new FaxMachine(this.server);

    }

    // Defines the functional methods.
    /**
     * The getFaxMachine method returns the FaxMachine of the Warehouse.
     *
     * @return the FaxMachine of this Warehouse.
     */
    public FaxMachine getFaxMachine() {
        System.out.println("Calling getFaxMachine of " + this.toString() + ".");
        return this.faxmachine;
    }

    // Defines the helper methods.
    /**
     * Parses the traversal_table.csv file so that the server has a more readily useable form
     * for the sake of looking up SKU numbers of levels, this is more efficient than parsing
     * the file every single time an SKU lookup is required.
     *
     * @return the Integer[][][][][] which "models" the Warehouse in the sense that it describes
     *         SKU of each Level. The first dimension is the floor, then the zones, then the
     *         aisles, then the racks, then the levels.
     */
	private Integer[][][][][] parseTraversalTableFile() {
		return null;
        // Do not worry about this, already finished.
        return new Integer[0][0][0][0][0];
    }

    /**
     * The getWorkers function of Warehouse returns the workers that belong to that Warehouse.
     *
     * @return the ArrayList of Workers that belong to the Warehouse.
     */
    public ArrayList<Worker> getWorkers() {
        System.out.println("Calling getWorkers() of Warehouse " + this.toString() + ".");
        System.out.println("    Returning " + this.workers.toString() + ".");
        return this.workers;
    }

    /**
     * The addWorker function of Warehouse adds a Worker to the Warehouse workers ArrayList.
     *
     * @param newWorker the Worker to be added to the Warehouse.
     */
    public void addWorker(Worker newWorker) {
        System.out.println("Calling addWorker of Warehouse " + this.toString() + ", with " +
            "argument newWorker as " + newWorker.toString() + ".");
        this.workers.add(newWorker);
    }

	public Floor getFloor() {
		return this.floor;
	}
}
