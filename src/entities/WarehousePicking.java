// Defines the package.
package entities;

// Defines the imports.
import java.lang.IllegalArgumentException;
import java.util.List;
import java.util.LinkedList;

/**
 * The WarehousePicking class is a mandatory class which contains the optimize
 * method. The optimize method is supposed to be given by another company, which
 * means that this is not an implementation that needs to be "implemented" when
 * employed to a client, but exists for testing and demonstration purposes.
 */
public class WarehousePicking {
    // Defines the instance variables.
    private final Level[] levels;

    /**
     * The primary and sole contructor of WarehousePicking, which initializes
     * the object with the data necessary for the optimize function.
     *
     * @param levels
     *            the Level[] which is utilized for the optimize function, this
     *            should be given by the Warehouse attribute Floor, and calling
     *            the method getLevels() on the Floor.
     */
    public WarehousePicking(Level[] levels) {
        this.levels = levels;
    }

    /**
     * Based on the Integer SKUs in List 'skus', return a List of locations,
     * where each location is a String containing 5 pieces of information: the
     * zone character (in the range ['A'..'B']), the aisle number (an integer in
     * the range [0..1]), the rack number (an integer in the range ([0..2]), and
     * the level on the rack (an integer in the range [0..3]), and the SKU
     * number. This is not the "real" implementation of optimize and the "real"
     * implementation will be provided by a third party at a later time.
     *
     * @param skus
     *            the list of SKUs to retrieve.
     * @return the List of locations.
     */
    public List<String> optimize(List<Integer> skus) {
        List<String> locations = new LinkedList<>();
        boolean levelFound = false;
        for (int skuIndex = 0; skuIndex < skus.size(); skuIndex++) {
            for (int levelIndex = 0; levelIndex < this.levels.length && levelFound == false; levelIndex++) {
                if (this.levels[levelIndex].getSKU().equals(skus.get(skuIndex))) {
                    levelFound = true;
                    locations.add(this.levels[levelIndex].getLocation());
                }
            }

            if (levelFound == false) {
                throw new IllegalArgumentException("The the call of optimize has resulted is "
                        + "invalid given the List of skus because an SKU does not exist in the "
                        + "floor, specifically the sku " + skus.get(skuIndex).toString() + ".");
            }

            levelFound = false;
        }
        return locations;
    }
}