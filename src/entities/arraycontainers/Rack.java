// Defines the package.
package entities.arraycontainers;

// Defines the imports.
import entities.Level;

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
     * @param layout the Integer[] which represents the layout, where each element is a Level and
     *        the respective Integer SKU.
     */
    public Rack(Aisle aisle, String[] layout) {
        super(aisle, new Level[layout.length]);
        for(int index = 0; index < layout.length; index++) {
            this.setItem(index, new Level(this, layout[index]));
        }
    }
}