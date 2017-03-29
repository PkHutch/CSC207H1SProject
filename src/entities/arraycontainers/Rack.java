// Defines the package.
package entities.arraycontainers;

/**
 * A Rack is an ArrayContainer with the parent being Aisle, and the child being Level.
 */
public class Rack extends ArrayContainer<Aisle, Level> {
    // Defines the constructors.
    /**
     * The default constructor for Rack, this calls the super class with the Aisle as the
     * container, and according to the layout, creates the contained Levels.
     *
     * @param aisle the Aisle object which contains this Rack object.
     * @param layout the Integer[][] which represents the layout, where the first dimension
     *        is this Rack, and the second is the Level's SKU Integers.
     */
    public Rack(Aisle aisle, Integer[][] layout) {
        super(aisle, new Level[layout.length]);
        System.out.println("Constructing Rack " + this.toString() + " with argument aisle as " +
            aisle.toString() + ", and argument layout as " + layout.toString() + ".");
        System.out.println("    Creating " + Integer.toString(layout.length) + " Levels.");
        for(int index = 0; index < layout.length; index++) {
            this.setItem(index, new Level(this, layout[index]));
        }
    }
}