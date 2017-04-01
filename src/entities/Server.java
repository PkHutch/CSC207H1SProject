// Defines the package.
package entities;

// Defines the imports.
import entities.workers.Picker;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class defines the Server, which keeps track of the different states of orders, and
 * effectively governs how the other entities interact in the warehouse.
 */
public class Server {
    // Defines the constants.
    private final static int DEFAULT_REFILL_QUANTITY = 5;
    private final static int DEFAULT_PICKING_REQUEST_SIZE = 8;

    // Defines instance variables.
    private final LinkedList<Picker> inactivePickers;
    private final LinkedList<Integer> partialPickingRequest;
    private final ArrayList<PickingRequest> pickingRequests;
    private final String[][] orderArray;
    private final Warehouse warehouse;

    // Defines the constructor methods.
    /**
     * The only contructor for Server.
     *
     * @param warehouse the warehouse which this server belongs to.
     */
    public Server(Warehouse warehouse) {
        System.out.println("Constructing Server " + this.toString() + " with argument " +
            "warehouse as " + warehouse.toString() + ".");
        this.inactivePickers = new LinkedList<>();
        this.partialPickingRequest = new LinkedList<>();
        this.pickingRequests = new ArrayList<>();
        this.orderArray = this.parseTranslationFile();
        this.warehouse = warehouse;
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
     * The assignPicker method of Server assigns the Picker a PickingRequest.
     *
     * @param picker the Picker to be assigned a PickingRequest.
     */
    private void assignPicker(Picker picker) {
        
    }

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
        // Already completed elsewhere.
        return new String[0][0];
    }
}
