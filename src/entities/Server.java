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
import java.lang.Integer;
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
    private final ArrayList<Level> lowLevels;
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
        this.inactivePickers = new LinkedList<>();
        this.lowLevels = new ArrayList<>();
        this.partialPickingRequest = new LinkedList<>();
        this.pickingRequests = new ArrayList<>();
        this.orderArray = this.parseTranslationFile();
        this.warehouse = warehouse;
    }

    public Warehouse getWarehouse() {
        return this.warehouse;
    }

    /**
     * Adds the worker to the inactivePickers list.
     *
     * @param worker the Picker to be added to the inactivePickers list.
     */
    public void addInactivePicker(Picker picker) {
        if(picker.hasPickingRequest()) {
            throw new IllegalArgumentException("The Picker " + picker.getName() + " already " +
                          " has a PickingRequest assigned.");
        } else {
            this.assignPicker(picker);
            if(picker.hasPickingRequest() == false) {
                this.inactivePickers.add(picker);
            }
        }
    }

    /**
     * The addOrder method of Server checks if the order is in translation array, and adds it the
     * SKUs to inactivePicks if it is, and creates a picking request, adding the picking request
     * to inactivePickingRequests if there is enough inactivePicks.
     *
     * @param order the Order to be added to the inactivePicks of Server, and potentially the
     *        inactivePickingRequests. order must have it's attributes exist in the Server's
     *        translationArray.
     */
    public void addOrder(Order order) {
        // This is used to check when the server has looked up the order's SKU.
        boolean foundOrder = false;

        for(int index = 0; (!(foundOrder) && index < this.orderArray.length); index++) {
            if(order.getColour().equals(this.orderArray[index][0]) &&
                order.getModel().equals(this.orderArray[index][1])) {
                foundOrder = true;
                this.partialPickingRequest.add(Integer.parseInt(this.orderArray[index][2]));
                this.partialPickingRequest.add(Integer.parseInt(this.orderArray[index][3]));
            }
        }

        if(foundOrder == false) {
            throw new IllegalArgumentException("The order given with colour \"" +
                          order.getColour() + "\" and model \"" + order.getModel() + "\" does " +
                          "not exist in the Server's translationArray.");
        }

        // If four orders have been placed, then creates a picking request.
        if(partialPickingRequest.size() == DEFAULT_PICKING_REQUEST_SIZE) {
            PickingRequest newPickingRequest = new PickingRequest(
                                                       this.partialPickingRequest.toArray(
                                                       new Integer[
                                                       DEFAULT_PICKING_REQUEST_SIZE]));
            pickingRequests.add(newPickingRequest);
            this.partialPickingRequest.clear();
            if (this.inactivePickers.size() > 0) {
                this.assignPicker(inactivePickers.pop());
            }
        } else {
            System.out.println("Not enough!");
        }
    }

    public void checkLevel(Level level) {
        if(level.getStock() <= DEFAULT_REFILL_QUANTITY) {
            boolean foundLevel = false;
            for(int index = 0; index < this.lowLevels.size() && foundLevel == false; index++) {
                if(level == this.lowLevels.get(index)) {
                    foundLevel = true;
                    throw new IllegalArgumentException("The level at location " +
                              level.getLocation() + " is being checked for low quantity, this " +
                              "should never occur because it has already been checked.");
                }
            }
            if(foundLevel = false || this.lowLevels.size() == 0) {
                System.out.println("    Warning: Level " + level.getLocation() + " must be replenished.");
                this.lowLevels.add(level);
            }
        }
    }

    public boolean hasLowLevels() {
        return this.lowLevels.size() != 0;
    }

    public String getLowLevelsString() {
        String lowLevelsString = new String();
        for(int index = 0; index < this.lowLevels.size(); index++) {
            lowLevelsString.concat("        " + this.lowLevels.get(index).getLocation() + "\n");
        }
        return lowLevelsString;
    }

    public void updateLevel(Level level) {
        if(level.getStock() > DEFAULT_REFILL_QUANTITY) {
            boolean foundLevel = false;
            for(int index = 0; index < this.lowLevels.size() && foundLevel == false; index++) {
                if(level == this.lowLevels.get(index)) {
                    foundLevel = true;
                    this.lowLevels.remove(level);
                }
            }
        }
    }

    /**
     * The getPickingRequests method of Server returns the PickingRequests of the server.
     *
     * @return the ArrayList<PickingRequest> which is the PickingRequests that the Server is
     *         keeping track of.
     */
    public ArrayList<PickingRequest> getPickingRequests() {
        System.out.println("Calling getPickingRequests of Server " + this.toString() + ".");
        System.out.println("    Returning " + this.pickingRequests.toString() + ".");
        return this.pickingRequests;
    }

    // Defines the helper methods.
    /**
     * The assignPicker method of Server assigns the Picker a PickingRequest.
     *
     * @param picker the Picker to be assigned a PickingRequest.
     */
    private void assignPicker(Picker picker) {
        boolean foundPickingRequest = false;
        for(int index = 0; index < this.pickingRequests.size() &&
                           foundPickingRequest == false; index++) {
            if(this.pickingRequests.get(index).getStatus() == 0) {
                foundPickingRequest = true;
                picker.setPickingRequest(this.pickingRequests.get(index));
            }
        }
    }

    /**
     * Parses the translation.csv file so that the server has a more readily useable form for the
     * sake of looking up SKU numbers of units, this is more efficient than parsing the file every
     * single time an SKU lookup is required. If translation.csv contains repeated SKUs, then an
     * IllegalFormatException is thrown.
     *
     * @return the String[][], is each element being a single fascia pair, the first element of
     *         the String[] is the model, the second is the colour, the third is the front, the
     *         fourth is the back.
     */
    private String[][] parseTranslationFile() {
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
            while ((line = bufferedReader.readLine()) != null) {
                String[] translatedLine = line.split(SPLIT_BY);

                if (translatedLine[2].equals(translatedLine[3])) {
                    throw new IllegalArgumentException("The SKU " + translatedLine[2] + " is " +
                                  "equal to " + translatedLine[3] + " and can't have the same " +
                                  "SKU.");
                } else {
                    for (int index = 0; index < parsedArrayList.size(); index++) {
                        String[] currentTranslation = parsedArrayList.get(index);
                        if (translatedLine[2].equals(currentTranslation[2]) ||
                            translatedLine[2].equals(currentTranslation[3])) {
                            throw new IllegalArgumentException("The SKU " + translatedLine[2] + 
                                          " already exists in the translation table.");
                        } else if (translatedLine[3].equals(currentTranslation[2]) ||
                            translatedLine[3].equals(currentTranslation[3])) {
                            throw new IllegalArgumentException("The SKU " + translatedLine[3] +
                                          " already exists in the translation table.");
                        } else if (translatedLine[0].equals(currentTranslation[0]) &&
                            translatedLine[1].equals(currentTranslation[1])) {
                            throw new IllegalArgumentException("The colour and model " +
                                          translatedLine[0] + " and " + translatedLine[1] +
                                          " already have been defined.");
                        }
                    }
                    parsedArrayList.add(new String[]{translatedLine[0], translatedLine[1],
                                                translatedLine[2], translatedLine[3]});
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
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
