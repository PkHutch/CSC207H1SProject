// Defines the package.
package entities;

/**
 * The warehouse class, which is where the simulation takes place, as the Warehouse effectively
 * acts as a container for the other entities.
 */
public class Warehouse {
    // Defines the constructors.
    /**
     * The default constructor for a Warehouse.
     */
    public Warehouse() {
        System.out.println("Constructing Warehouse" + this.toString() + ".");
    }

    // Defines the functional methods.
    /**
     * The getFaxMachine method returns the FaxMachine of the Warehouse.
     *
     * @return the FaxMachine of this Warehouse.
     */
    public FaxMachine getFaxMachine() {
        System.out.println("Calling getFaxMachine of " + this.toString() + ".");
        // Return the faxMachine.
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
    }
}
