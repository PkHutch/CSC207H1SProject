// *NOTE* All commented out things are because of lack of proper implementation.
// Defines the package.
package entities;

// Imports necessary packages.
//import entities.Warehouse;

/**
 * This class defines the Server, which makes the majority of the logical decisions for a
 * warehouse. The specifics of the logical decisions that it makes are located in the
 * issueTask methods.
 */
public class Server {
//    private Warehouse warehouse;

    // Defines the constructor methods.
    /**
     * The constructor for Server which doesn't any values to it's attributes this should only be
     * called by the Warehouse that it belongs to.
     *
     * @param warehouse the warehouse which this server belongs to.
     */
//    public void Server(Warehouse warehouse) {
//        this.warehouse = warehouse;
//        parseTranslation();
//    }

    // Defines the helper methods.
    /**
     * Parses the translation.csv file so that the server has a more readily useable form
     * for the sake of looking up SKU numbers of units, this is more efficient than parsing
     * the file every single time an SKU lookup is required.
     *
     * @throws IllegalFormatException this is called when there are repeated SKUs in the
     *         translation.csv file.
     */
    private void parseTranslation() {
        // Filler.
    }
}