// *NOTE* All commented out things are because of lack of proper implementation.
// Defines the package.
package entities;

// Imports necessary packages.
//import entities.Warehouse;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;
import java.util.LinkedList;
import workers.Loader;
import workers.Picker;
import workers.Resupplier;
import workers.Sequencer;


/**
 * This class defines the Server, which makes the majority of the logical decisions for a
 * warehouse. The specifics of the logical decisions that it makes are located in the
 * issueTask methods.
 */
public class Server {
    private ArrayList<String[]> translationArray;
    private LinkedList<Loader> inactiveLoaders;
    private LinkedList<Picker> inactivePickers;
//    private LinkedList<int[]> inactivePickingRequests;
//    private LinkedList<Integer> inactivePicks;
    private LinkedList<Resupplier> inactiveResuppliers;
    private LinkedList<Sequencer> inactiveSequencers;
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
     * Adds the worker to the inactiveLoaders list.
     *
     * @param worker the Loader to be added to the inactiveLoaders list.
     */
    public void addInactiveLoader(Loader worker) {
        this.inactiveLoaders.add(worker);
    }

    /**
     * Adds the worker to the inactivePickerss list.
     *
     * @param worker the Picker to be added to the inactivePickers list.
     */
    public void addInactivePicker(Picker worker) {
        this.inactivePickers.add(worker);
    }

    /**
     * Adds the worker to the inactiveResuppliers list.
     *
     * @param worker the Resupplier to be added to the inactiveResuppliers list.
     */
    public void addInactiveResupplier(Resupplier worker) {
        this.inactiveResuppliers.add(worker);
    }

    /**
     * Adds the worker to the inactiveSequencers list.
     *
     * @param worker the Sequencer to be added to the inactiveSequencers list.
     */
    public void addInactiveSequencers(Sequencer worker) {
        this.inactiveSequencers.add(worker);
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
//        if(taskEntity instanceof FaxMachine) {
//            Order faxOrder = FaxMachine.getFax();
//
//            // Looks up the order in the translation table, or translationArray, throws an error
//            // if the stock specified in the order doesn't exist.
//            for (int index = 0; index < this.translationArray.size(); index++) {
//                if(faxOrder.colour == this.translationArray.get(index)[0] &&
//                    faxOrder.model == this.translationArray.get(index)[1]) {
//                    this.inactivePicks.add(Integer.parseInt(this.translationArray.get(index)[2]));
//                    this.inactivePicks.add(Integer.parseInt(this.translationArray.get(index)[3]));
//                } else {
//                    throw new IllegalArgumentException("This order requires stock which does " +
//                                  "not exist in the SKU lookup table.");
//                }
//
//            // If four orders have been placed, then create a picking request.
//            if(inactivePicks.size() == DEFAULT_PICKING_REQUEST_SIZE) {
//                int[] newPickingRequest = new int[DEFAULT_PICKING_REQUEST_SIZE];
//
//                for(int index = 0; index >= 0; index--) {
//                    newPickingRequest[index] = Integer.parseInt(inactivePicks.pop());
//                }
//
//                inactivePickingRequests.add(newPickingRequest);
//            }
//        // If a Picker is calling the function then there are one of two options, then it has yet
//        // to be assigned an inactive picking request.
//        } else if(taskEntity instanceOf Picker) {
//            // A picker should not already have a picking request assigned.
//            if(taskEntity.hasPickingRequest) {
//                throws new IllegalArgumentException(taskEntity.getName + " already has a " +
//                               "picking request assigned to them.)
//            } else {
//                activePickingRequests.add(inactivePickingRequest.pop());
//                taskEntity.setPickingRequest(this.warehouse.warehousePicking.optimize(this.
//                    activePickingRequests.getLast()));
//            }
//        } else {
//            throw new IllegalArgumentException("The TaskGiver does not have a defined result " +
//                          "when calling issueTask of the Server.");
//        }
//    }
}