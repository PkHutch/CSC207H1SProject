// Defines the package.
package entities.workers;

// Defines the imports.
import entities.Warehouse;

/**
 * The Worker class is mostly just a class that has a name, and warehouse, but the children of
 * workers should implement either TaskExecutor, or TaskGiver.
 */
public class Worker implements TaskEntity{
    // Defines the instance variables.
    private String name;
    private Warehouse warehouse;

    // Defines the constructors.
    /**
     * The main constructor for the Worker class.
     *
     * @param name the name of String, which is the name of the Worker, ideally is unique, but
     *        not necessary, however this could cause problems otherwise.
     * @param warehouse the Warehouse that the Worker belongs to.
     */
    public Worker(String name, Warehouse warehouse) {
        System.out.println("Constructing Worker (" + this.toString() + "), with argument name" +
            "\"" + name + "\", and Warehouse " + warehouse.toString() + ".");
        // Don't worry about this.
    }

    // Defines the functional methods.
    /**
     * The method for returning the name of the Worker.
     *
     * @return the String name of the Worker.
     */
    public String getName() {
        System.out.println("Calling getName of " + this.toString() + ".");
        System.out.println("    Returning \"" + this.name + "\".");
        // Don't worry about this.
    }

    /**
     * The method for returning the warehouse of the Worker.
     *
     * @return the Warehouse that the Worker belongs to.
     */
    public Warehouse getWarehouse() {
        System.out.println("Calling getWarehouse of " + this.toString() + ".");
        System.out.println("    Returning " + this.warehouse.toString() + ".");
        // Don't worry about this.
    }
}
