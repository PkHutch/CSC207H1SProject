// Defines the package.
package entities.arraycontainers;

// Defines the imports.
import entities.Level;
import entities.Warehouse;
import java.util.ArrayList;

/**
 * A Floor is an ArrayContainer with the parent being Warehouse, and the child being Zone.
 */
public class Floor extends ArrayContainer<Warehouse, Zone> {
    // Defines the constructors.
    /**
     * The default constructor for Floor, this calls the super class with the Warehouse as the
     * container, and according to the layout, creates the contained Zones.
     *
     * @param warehouse the Warehouse object which contains this Floor object.
     * @param layout the Integer[][][][] which represents the layout, where the first dimension
     *        is the Zones, the second is the Aisles, the third is the the Racks, and the fourth
     *        is the Levels SKU Integers.
     */
    public Floor(Warehouse warehouse, Integer[][][][] layout) {
        super(warehouse, new Zone[layout.length]);
        for (int index = 0; index < layout.length; index++) {
            this.setItem(index, new Zone(this, layout[index]));
        }
    }

    // Defines the functional methods.
    /**
     * The same as the getItem method of the super class, this gets the item at a given index but
     * instead a capital letter character is given.
     *
     * @param zone the capital letter character of the zone to return, ensure that a capital
     *        letter is given.
     * @return the zone specified by the letter given.
     */
    public Zone getItem(char zone) {
        return this.getItem(zone - 'A');
    }

    /**
     * The getLevel method of Floor returns the Level object at the given location, the input must
     * point to a valid Level object.
     *
     * @param zone the char input that is the zone the Level is contained in.
     * @param aisle the int input that is the aisle the Level is contained in.
     * @param rack the int input that is the rack the Level is conatained in.
     * @param level the int input that is the index of the Level.
     * @return the Level object, assuming the input is a valid location.
     */
    public Level getLevel(char zone, int aisle, int rack, int level) {
        return this.getItem(zone).getItem(aisle).getItem(rack).getItem(level);
    }

    /**
     * The getLevels method of Floor returns the Level objects contained by the objects which the
     * Floor contains.
     *
     * @return the array of Level objects which are contained by the objects contained by the
     *         Floor.
     */
    public Level[] getLevels() {
        ArrayList<Level> returnedArrayList = new ArrayList<>();
        Zone[] zones = this.getItems();
        for (int zoneIndex = 0; zoneIndex < zones.length; zoneIndex++) {
            Aisle[] aisles = zones[zoneIndex].getItems();
            for (int aisleIndex = 0; aisleIndex < aisles.length; aisleIndex++) {
            Rack[] racks = aisles[aisleIndex].getItems();
                for (int rackIndex = 0; rackIndex < racks.length; rackIndex++) {
                    Level[] levels = racks[rackIndex].getItems();
                    for (int levelIndex = 0; levelIndex < levels.length; levelIndex++) {
                        returnedArrayList.add(levels[levelIndex]);
                    }
                }
            }
        }
        return returnedArrayList.toArray(new Level[returnedArrayList.size()]);
    }
}