// Defines the package.
package entities;

// Defines the imports.
import entities.arraycontainers.Rack;

/**
 * The Level class contains Stock of a single SKU and is contained by a Rack.
 */
public class Level {
    // Defines the constants.
    private static final int DEFAULT_MAX_QUANTITY = 30;

    // Defines the instance variables.
    private Rack containedBy;
    private Integer sku;

    // Defines the constructors.
    /**
     * The default Level constructor, constructs the Level according to the DEFAULT_MAX_QUANTITY
     * for assigning the currentQuantity because no quantity has been given.
     *
     * @param rack the Rack object which contains this Level.
     * @param sku the Integer which this Level contains, this should be a valid sku as defined by
     *        the Server that the Level belongs to, but this is not inforced.
     */
    public Level(Rack rack, Integer sku) {
        System.out.println("Constructing Level " + this.toString() + " with argument rack as " +
            rack.toString() + ".");
        this.containedBy = rack;
        this.sku = sku;
    }
}