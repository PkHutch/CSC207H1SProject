// Defines the package.
package entities.arraycontainers;

/**
 * An Aisle is an ArrayContainer with the parent being Zone, and the child being Rack.
 */
public class Aisle extends ArrayContainer<Zone, Rack> {
    // Defines the constructors.
    /**
     * The default constructor for Aisle, this calls the super class with the Zone as the
     * container, and according to the layout, creates the contained Rack.
     *
     * @param zone the Zone object which contains this Aisle object.
     * @param layout the Integer[][] which represents the layout, where the first dimension
     *        is the Racks, and the second is the Level's SKU Integers.
     */
    public Aisle(Zone zone, Integer[][] layout) {
        super(zone, new Rack[layout.length]);
        System.out.println("Constructing Aisle " + this.toString() + " with argument zone as " +
            zone.toString() + ", and argument layout as " + layout.toString() + ".");
        System.out.println("    Creating " + Integer.toString(layout.length) + " Racks.");
        for(int index = 0; index < layout.length; index++) {
            this.setItem(index, new Rack(this, layout[index]));
        }
    }
}