// Defines the package.
package entities.arraycontainers;

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
     * @param layout the Integer[][][] which represents the layout, where the first dimension
     *        is the Aisles, the second is the Racks, and the third the Level's SKU Integers.
     */
    public Zone(Floor floor, Integer[][][] layout) {
        super(floor, new Aisle[layout.length]);
        for(int index = 0; index < layout.length; index++) {
            this.setItem(index, new Aisle(this, layout[index]));
        }
    }
}