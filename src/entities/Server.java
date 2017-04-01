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
        System.out.println("Calling parseTranslationArray of Server " + this.toString() +
            ".");
        // Defines constants.
        final String TRANSLATION_FILE = "./resources/translation.csv";
        final String SPLIT_BY = ",";

        // Defines variables for reading the file.
        BufferedReader bufferedReader = null;
        String line;

        // Defines the ArrayList that will be converted to an array.
        ArrayList<String[]> parsedArrayList = new ArrayList<>();

        try {
            bufferedReader = new BufferedReader(new FileReader(TRANSLATION_FILE));
            // Skips the first line.
            line = bufferedReader.readLine();

            // Operating on all remaining lines.
            System.out.println("    Reading remaining contents of translation.csv.");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("    Parsing the line reading: " + line);
                String[] translatedLine = line.split(SPLIT_BY);

                System.out.println("    Checking if the line is valid.");
                if(translatedLine[2].equals(translatedLine[3])){
                    throw new IllegalArgumentException("The SKU " + translatedLine[2] + " is " +
                                  "equal to " + translatedLine[3] + " and can't have the same " +
                                  "SKU.");
                } else {
                    // Checks that the SKUs do not exist in the table.
                    System.out.println("    Checking the SKU doesn't already exist in the " +
                        "parsedTranslationArray.");
                    for(int index = 0; index < parsedArrayList.size(); index++) {
                        String[] currentTranslation = parsedArrayList.get(index);

                        System.out.println("    Checking if " + currentTranslation[2] + " and" +
                            " " + currentTranslation[3] + " are equal to " + translatedLine[2] +
                            " or " + translatedLine[3] + ".");
                        if(translatedLine[2].equals(currentTranslation[2]) ||
                            translatedLine[2].equals(currentTranslation[3])) {
                            throw new IllegalArgumentException("The SKU " + translatedLine[2] + 
                                          " already exists in the translation table.");
                        } else if(translatedLine[3].equals(currentTranslation[2]) || 
                            translatedLine[3].equals(currentTranslation[3])) {
                            throw new IllegalArgumentException("The SKU " + translatedLine[3] + 
                                          " already exists in the translation table.");
                        } else if(translatedLine[0].equals(currentTranslation[0]) &&
                            translatedLine[1].equals(currentTranslation[1])) {
                            throw new IllegalArgumentException("The colour and model " +
                                          translatedLine[0] + " and " + translatedLine[1] +
                                          " already have been defined.");
                        }
                    }

                    System.out.println("    They are not, adding to the translationArray.");
                    parsedArrayList.add(new String[]{translatedLine[0], translatedLine[1],
                        translatedLine[2], translatedLine[3]});
                }
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
		}
            }
        }

        return parsedArrayList.toArray(new String[parsedArrayList.size()][4]);
    }
}
