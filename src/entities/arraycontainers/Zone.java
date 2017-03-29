// Defines the package.
package entities.arraycontainers;

// Defines the imports.
import java.util.ArrayList;

/**
 * A Zone is an ArrayContainer with the parent being Floor, and the child being Aisle.
 */
public class Zone extends ArrayContainer<Floor, Aisle> {
    // Defines the constructors.
    /**
     * The default constructor for Zone, this calls the super class with the Floor as the
     * container, and according to the layout, creates the contained Aisles.
     *
     * @param floor the Floor object which contains this Zone object.
     * @param layout the Integer[][][][] which represents the layout, where the first dimension
     *        is this Zone, the second is the Aisles, the third is the Racks, the fourth is the
     *        the Level's SKU Integers.
     */
    public Zone(Floor floor, Integer[][][][] layout) {
        super(warehouse, new Aisle[layout.length]);
        System.out.println("Constructing Zone " + this.toString() + " with argument floor as " +
            floor.toString() + ", and argument layout as " + layout.toString() + ".");
        System.out.println("    Creating " + layout.length.toString() + " Aisles.");
        for(int index = 0; index < layout.length; index++) {
            this.setItem(index, new Aisle(this, layout[index]));
        }
    }
}