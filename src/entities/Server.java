// Defines the package.
package entities;
// Defines the imports.
import entities.workers.Picker;

/**
 * This class defines the Server, which keeps track of the different states of orders, and
 * effectively governs how the other entities interact in the warehouse.
 */
public class Server {
    // Defines the constructor methods.
    /**
     * The only contructor for Server.
     *
     * @param warehouse the warehouse which this server belongs to.
     */
    public Server(Warehouse warehouse) {
        System.out.println("Constructing Server " + this.toString() + " with argument " +
            "warehouse as " + warehouse.toString() + ".");
        // Already completed elsewhere.
    }

    /**
     * Adds the worker to the inactivePickers list.
     *
     * @param worker the Picker to be added to the inactivePickers list.
     */
    public void addInactivePicker(Picker worker) {
        // See the legacy code, but also check that the picker is inactive when this is called.
        // If the picker isn't inactive, throw exception.
        // Otherwise, double check that an inactivePickingRequest can't be assigned, if it can
        // Then assign it to the Picker, notifying the console.
    }

    /**
     * The addOrder method of Server checks if the order is in translation array, and adds it
     * the SKUs to inactivePicks if it is, and creates a picking request, adding the picking
     * request to inactivePickingRequests if there is enough inactivePicks.
     *
     * @param order the Order to be added to the inactivePicks of Server, and potentially the
     *        inactivePickingRequests. order must have it's attributes exist in the
     *        Server's translationArray.
     */
    public void addOrder(Order order) {
        // Already completed elsewhere.
    }

    // Defines the helper methods.
    /**
     * Parses the translation.csv file so that the server has a more readily useable form
     * for the sake of looking up SKU numbers of units, this is more efficient than parsing
     * the file every single time an SKU lookup is required. If translation.csv contains repeated
     * SKUs, then an IllegalFormatException is thrown.
     *
     * @return the String[][], is each element being a single fascia pair, the first element of
     *         the String[] is the model, the second is the colour, the third is the front, the
     *         fourth is the back.
     */

	private String[][] parseTranslationFile() {
		return null;
    }
}
