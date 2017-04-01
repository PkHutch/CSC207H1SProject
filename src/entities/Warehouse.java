// Defines the package.
package entities;

// Defines the imports.
import entities.workers.Worker;
import java.util.ArrayList;

/**
 * The warehouse class, which is where the simulation takes place, as the Warehouse effectively
 * acts as a container for the other entities.
 */
public class Warehouse {
    // Defines the instance variables.
    private final FaxMachine faxMachine;
    private final Server server;
    private final ArrayList<Worker> workers;
    
    // Defines the constructors.
    /**
     * The default constructor for a Warehouse.
     */
    public Warehouse() {
        System.out.println("Constructing Warehouse" + this.toString() + ".");
        this.server = new Server(this);
        this.workers = new ArrayList<>();
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
        return this.faxMachine;
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
        // Do not worry about this, already finished.
        return new Integer[0][0][0][0][0];
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public void addWorker(Worker newWorker) {
        this.workers.add(newWorker);
    }
}
