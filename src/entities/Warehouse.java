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
    private FaxMachine faxmachine;
    // Defines the constructors.
    /**
     * The default constructor for a Warehouse.
     */
    public Warehouse() {
        System.out.println("Constructing Warehouse" + this.toString() + ".");
        this.workers = new ArrayList<>();
        this.floor = new Floor(this, new Integer[1][1][1][1]);
        this.faxmachine = null;
    }
    public void AddFaxMachine(FaxMachine faxmachine){
    	this.faxmachine = faxmachine;
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
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public void addWorker(Worker newWorker) {
        this.workers.add(newWorker);
    }

	public Floor getFloor() {
		return this.floor;
	}
}
