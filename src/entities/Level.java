// Defines the package.
package entities;

// Defines the imports.
import entities.arraycontainers.Aisle;
import entities.arraycontainers.Floor;
import entities.arraycontainers.Rack;
import entities.arraycontainers.Zone;

/**
 * The Level class contains Stock of a single SKU and is contained by a Rack.
 */
public class Level {
    // Defines the constants.
    private static final int DEFAULT_MAX_QUANTITY = 30;

    // Defines the instance variables.
    private final Rack containedBy;
    private final String sku;
    private int stock;

    // Defines the constructors.
    /**
     * The default Level constructor, constructs the Level according to the
     * DEFAULT_MAX_QUANTITY for assigning the currentQuantity because no
     * quantity has been given.
     *
     * @param rack
     *            the Rack object which contains this Level.
     * @param sku
     *            the Integer which this Level contains, this should be a valid
     *            sku as defined by the Server that the Level belongs to, but
     *            this is not inforced.
     */
    public Level(Rack rack, String sku) {
        this.containedBy = rack;
        this.sku = sku;
        this.stock = 0;
    }

    // Defines the functional methods.
    public void addStock(int parseInt) {
        if (this.stock + parseInt <= DEFAULT_MAX_QUANTITY) {
            this.stock += parseInt;
        } else {
            System.out.println("You tried adding " + parseInt + " items to a " + this.stock + " level");
        }
    }

    public boolean atMaxCapacity() {
        if (this.stock == DEFAULT_MAX_QUANTITY) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * The getLocation method of Level returns the String need to access the
     * Level.
     *
     * @return the String location of the Level in the form of "zone, aisle,
     *         rack, level" where zone is a character, and aisle, rack, and
     *         level are all string integers of the Level location.
     */
    public String getLocation() {
        Rack rack = this.containedBy;
        Aisle aisle = rack.getContainer();
        Zone zone = aisle.getContainer();
        Floor floor = zone.getContainer();
        return Character.toString((char) ((int) 'A' + floor.getIndex(zone))) + ","
                + Integer.toString(zone.getIndex(aisle)) + "," + Integer.toString(aisle.getIndex(rack)) + ","
                + Integer.toString(rack.getIndex(this));
    }

    public int getMaxCapacity() {
        return DEFAULT_MAX_QUANTITY;
    }

    public String getSKU() {
        return sku;
    }

    public int getStock() {
        return this.stock;
    }

    public Stock removeStock() {
        if (this.stock - 1 >= 0) {
            this.stock -= 1;
            return new Stock(this.sku);
        } else {
            throw new IllegalStateException("The Level " + this.toString() + " can't have an "
                    + "item removed, there is not enough remaining stock.");
        }
    }
}
