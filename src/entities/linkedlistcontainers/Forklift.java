// Defines the package.
package entities.linkedlistcontainers;

// Defines the imports.
import entities.Stock;

/**
 * A Forklift is just a LinkedListContainer of size 8.
 */
public class Forklift extends LinkedListContainer<Stock> {
    // Defines the variables.
    private static final int DEFAULT_INVENTORY_SIZE = 8;

    // Defines the constructors.
    /**
     * The only method and constructor of a Forklift, which is just a
     * LinkedListContainer.
     */
    public Forklift() {
        super(DEFAULT_INVENTORY_SIZE);
    }
}
