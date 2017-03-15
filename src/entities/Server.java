// *NOTE* All commented out things are because of lack of proper implementation.
// Defines the package.
package entities;

// Imports necessary packages.
import entities.FaxMachine;
import entities.Warehouse;
import entities.workers.Loader;
import entities.workers.Picker;
//import entities.workers.Resupplier;
//import entities.workers.Sequencer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;
import java.util.LinkedList;

/**
 * This class defines the Server, which makes the majority of the logical decisions for a
 * warehouse. The specifics of the logical decisions that it makes are located in the
 * issueTask methods.
 */
public class Server {
    private ArrayList<String[]> translationArray;
    private ArrayList<Level> lowLevels;
    private final static int DEFAULT_REFILL_QUANTITY = 5;
    private final static int DEFAULT_PICKING_REQUEST_SIZE = 8;
    private LinkedList<Integer[]> activePickingRequests;
    private LinkedList<Picker> inactivePickers;
    private LinkedList<Integer[]> inactivePickingRequests;
    private LinkedList<Integer> inactivePicks;
    private LinkedList<Pallet> sequencedPickingRequests;
    private Warehouse warehouse;

    // Defines the constructor methods.
    /**
     * The constructor for Server which doesn't any values to it's attributes this should only be
     * called by the Warehouse that it belongs to.
     *
     * @param warehouse the warehouse which this server belongs to.
     */
    public void Server(Warehouse warehouse) {
        this.warehouse = warehouse;
        parseTranslation();
    }

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
        BufferedReader bufferedReader = null;
        String line;
        final String translationFile = "./resources/translation.csv";
        final String splitBy = ",";

        try {
            bufferedReader = new BufferedReader(new FileReader(translationFile));

            // Skips the first line.
            line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] translatedLine = line.split(splitBy);

                for(int index = 0; index < this.translationArray.size(); index++) {
                    String[] currentTranslation = translationArray.get(index);

                    if(translatedLine[2] == currentTranslation[2] ||
                        translatedLine[2] == currentTranslation[3] ||
                        translatedLine[3] == currentTranslation[3] ||
                        translatedLine[3] == currentTranslation[2] ||
                        translatedLine[2] == translatedLine[3]) {
                        throw new IllegalArgumentException("The SKU " + translatedLine[2] + " or "
                            + translatedLine[3] + " already exists in the translation source.");
                    } else {
                        this.translationArray.add(new String[]{translatedLine[0],
                            translatedLine[1], translatedLine[2], translatedLine[3]});
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
    }

    // Defines the functional methods.
    /**
     * Adds the worker to the inactivePickers list.
     *
     * @param worker the Picker to be added to the inactivePickers list.
     */
    public void addInactivePicker(Picker worker) {
        this.inactivePickers.add(worker);
    }

    public void issueTask(Picker taskEntity) {
        // A picker should not already have a picking request assigned.
        if(taskEntity.hasPickingRequest()) {
            throw new IllegalArgumentException(taskEntity.getName() + " already has a picking " +
                          "request assigned to them.");
        } else {
            Integer[] newPickingRequest = this.inactivePickingRequests.pop();

            // If the picking request is already active, that means that it has failed before and
            // should not be added to the activePickingRequests.
            if(this.activePickingRequests.contains(newPickingRequest) == false) {
                this.activePickingRequests.add(newPickingRequest);
            }

            // Give the picker the locations regardless.
//            taskEntity.setPickingRequest(this.warehouse.getWarehousePicking().optimize(newPickingRequest));
        }
    }

    public void issueTask(Level taskEntity) {
        if(taskEntity.numItem() <= DEFAULT_REFILL_QUANTITY) {
            this.lowLevels.add(taskEntity);
        }
    }

    public void issueTask(FaxMachine taskEntity) {
        Order faxOrder = taskEntity.removeOrder();

        // Looks up the order in the translation table, or translationArray, throws an error
        // if the stock specified in the order doesn't exist.
        for (int index = 0; index < this.translationArray.size(); index++) {
            if(faxOrder.getColour() == this.translationArray.get(index)[0] &&
                faxOrder.getModel() == this.translationArray.get(index)[1]) {
                this.inactivePicks.add(Integer.parseInt(this.translationArray.get(index)[2]));
                this.inactivePicks.add(Integer.parseInt(this.translationArray.get(index)[3]));
            } else {
                throw new IllegalArgumentException("This order requires stock which does " +
                              "not exist in the SKU lookup table.");
            }
        }
        // If four orders have been placed, then creates a picking request.
        if(inactivePicks.size() == DEFAULT_PICKING_REQUEST_SIZE) {
            Integer[] newPickingRequest = new Integer[DEFAULT_PICKING_REQUEST_SIZE];

            for(int counter = DEFAULT_PICKING_REQUEST_SIZE; counter >= 0; counter--) {
                newPickingRequest[counter] = inactivePicks.pop();
            }

            inactivePickingRequests.add(newPickingRequest);
        }
    }

    public void issueTask(Loader taskEntity) {
        this.sequencedPickingRequests.clear();
    }

    public boolean needsRefill() {
        return this.lowLevels.size() > 0;
    }

//    /**
//     * Reacts by the type of taskEntity, if the taskEntity is a TaskGiver, then the server takes 
//     * the appropriate action depending on the kind of TaskGiver that the taskEntity is, or if the
//     * taskEntity is a TaskExecutor, it reacts to the taskEntity by telling it what action it
//     * should complete, and the parameters of the action, by the string in doTask of that
//     * TaskExecutor.
//     * If the taskEntity is a FaxMachine, it gets the
//     *
//     * @param taskEntity the entity that is either given the task or giving a task.
//     * @throws IllegalArgumentException this is thrown whenever the TaskGiver doesn't have a
//     *         defined result in the issueTask cases.
//     */
//    public void issueTask(TaskEntity taskEntity) {
//        // If a Sequencer is calling issue task, then give it the current list of
//        // activePickingRequests to check.
//        if(taskEntity instanceof Sequencer) {
//            taskEntity.doTask(this.activeLinkedList);
//        // If a Replenisher is calling the function then it needs the low levels of the racks.
//        } else if(taskEntity instanceOf Replenisher) {
//            taskEntity.doTask(lowLevels);
//        } else {
//            throw new IllegalArgumentException("The TaskGiver does not have a defined result " +
//                          "when calling issueTask of the Server.");
//        }
//    }
}
