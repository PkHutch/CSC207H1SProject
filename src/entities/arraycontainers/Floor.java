// Defines the package.
package entities.arraycontainers;

// Defines the imports.
import entities.Warehouse;

/**
 * A Floor is an ArrayContainer with the parent being Warehouse, and the child being Zone.
 */
public class Floor extends ArrayContainer<Warehouse, Zone> {
    // Defines the constructors.
    /**
     * The default constructor for Floor, this calls the super class with the Warehouse as
     * the container, and according to the layout, creates the contained Zones.
     *
     * @param warehouse the Warehouse object which contains this Floor object.
     * @param layout the Integer[][][][] which represents the layout, where the first dimension
     *        is the Zones, the second is the Aisles, the third is the the Racks, and the fourth
     *        is the Levels SKU Integers.
     */
    public Floor(Warehouse warehouse, Integer[][][][] layout) {
        super(warehouse, new Zone[layout.length]);
        System.out.println("Constructing Floor " + this.toString() + " with argument " +
            " warehouse as " + warehouse.toString() + ", and argument layout as " +
            layout.toString() + ".");
        System.out.println("    Creating " + Integer.toString(layout.length) + " Zones.");
        for(int index = 0; index < layout.length; index++) {
            this.setItem(index, new Zone(this, layout[index]));
        }
    }

    // Defines the functional methods.
    /**
     * The same as the getItem method of the super class, this gets the item at a given index
     * but instead a capital letter character is given.
     *
     * @param zone the capital letter character of the zone to return, ensure that a capital
     *        letter is given.
     * @return the zone specified by the letter given.
     */
    /*public Zone getItem(char zone) {
        return this.getItem(zone - 'A');
    }*/

    /**
     * The getLevel method of Floor returns a level object specified by the parameters given.
     *
     * @param zone the capital letter char which determines which Zone is accessed.
     * @param aisle the int which determines which Aisle is accessed.
     * @param rack the int which determines which Rack is accessed.
     * @param level the int which determines which Level is accessed.
     * @return the Level which was specified by the parameters.
     */
    /*public Level getLevel(char zone, int aisle, int rack, int level) {
        return this.getItem(zone).getItem(aisle).getItem(rack).getItem(level);
    }*/
}