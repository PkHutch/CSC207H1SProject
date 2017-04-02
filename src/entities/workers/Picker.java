// Defines the package.
package entities.workers;

// Defines the imports.
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import entities.linkedlistcontainers.Forklift;
import entities.Level;
import entities.Stock;
import entities.taskentities.TaskExecutor;
import entities.Warehouse;
import entities.WarehousePicking;
import entities.arraycontainers.Aisle;
import entities.arraycontainers.Floor;
import entities.arraycontainers.Rack;
import entities.arraycontainers.Zone;
import entities.PickingRequest;

/**
 * The Picker class extends the worker class and takes an arguement of String for it's doTask
 * method. The Pickers job to stock off the shelves and bring them to the marshalling area as per
 * the server's instructions.
 */
public class Picker extends Worker implements TaskExecutor<String> {
    private static int DEFAULT_PICK_END = 8;
    private static int DEFAULT_PICK_START = 0;
    private static int DEFAULT_PICK_UNASSIGNED = -1;

    private int currentPick;
    private final Forklift forklift;
    private boolean isActive;
    private LinkedList<Level> pickingLocations;
    private PickingRequest pickingRequest;

    // Defines the constructor methods.
    /**
     * The only contructor method for a Picker.
     *
     * @param name the String name, should be the name of that picker, ideally it is unique but 
     *        the name doesn't necessarily have to be unique as of now, which could cause some
     *        undesired results.
     * @param warehouse the Warehouse that the Picker belongs to.
     */
    public Picker(String name, Warehouse warehouse) {
        super(name, warehouse);
        this.currentPick = DEFAULT_PICK_UNASSIGNED;
        this.forklift = new Forklift();
        this.isActive = true;
        this.pickingLocations = new LinkedList<>();
        this.pickingRequest = null;
    }

    // Defines the functional methods.
    /**
     * The doTask method for a Picker specifically serves to take in the String argument from a
     * command given in the console.
     *
     * @param argument The valid commands are "pick x", where "x" is a String number between 1 and
     *        8 inclusive, and must be called in order from 1 to 8. This command should only be
     *        after setReady() has been called, and the server has been assigned a picking request
     *        by the server.
     */
    public void doTask(String pickNumber) {
        if(this.hasPickingRequest() == true) {
            if(this.isActive == false) {
                try {
                    Integer pickInteger = Integer.parseInt(pickNumber);
                    if(currentPick == pickInteger - 1 && currentPick < DEFAULT_PICK_END) {
                        this.forklift.addItem(this.pickingLocations.pop().removeStock());
                        currentPick++;
                    } else if(currentPick == DEFAULT_PICK_END) {
                        throw new IllegalStateException("The Picker \"" + this.getName() + "\" is finished the current picking request, they should be sent to marshalling.");
                    } else {
                        throw new IllegalArgumentException("The Picker \"" + this.getName() + "\" is not on the given picking number they are on " + Integer.toString((this.currentPick + 1)) + ".");
                    }
                } catch(NumberFormatException exception) {
                    throw new IllegalArgumentException("The Picker \"" + this.getName() + "\" can not pick invalid number \"" + pickNumber + "\".");
                }
            } else {
                throw new IllegalStateException("The Picker \"" + this.getName() + "\" is not currently checked in as ready.");
            }
        } else {
            throw new IllegalStateException("The Picker \"" + this.getName() + "\" does not currently have a picking request assigned.");
        }
    }

    /**
     * The hasPickingRequest method for a Picker returns whether or not the Picker has been
     * assigned to a picking request by the Server, and they are currently picking it.
     *
     * @return the value true is given if Picker is currently picking a picking request and false
     *         otherwise.
     */
    public boolean hasPickingRequest() {
        return (this.currentPick >= DEFAULT_PICK_START);
    }

    /**
     * The setPickingRequest method of Picker sets the picking request of the picker if the picker
     * does not already have a picking request.
     *
     * @param pickingRequest the LinkedList of String arrays, where the first parameter should be
     *        the zone, then the aisle, rack, and level. It does not concern the SKU of the item.
     *        This should only be called if the Picker doesn't already have a picking request.
     */
    private void setPickingLocations(List<String> newPickingLocations) {
        for(int index = 0; index < newPickingLocations.size(); index++) {
            String[] newLocation = newPickingLocations.get(index).split(",");
            this.pickingLocations.add(this.getWarehouse().getFloor().getLevel(
                newLocation[0].charAt(0), Integer.parseInt(newLocation[1]),
                Integer.parseInt(newLocation[2]), Integer.parseInt(newLocation[3])));
        }
    }

    public void setPickingRequest(PickingRequest newPickingRequest) {
        if(this.hasPickingRequest() == false) {
            this.pickingRequest = newPickingRequest;
            this.currentPick = DEFAULT_PICK_START;
            this.setPickingLocations(new LinkedList<String>(this.getWarehouse().getWarehousePicking().optimize(new ArrayList<Integer>(Arrays.asList(newPickingRequest.getSKUs())))));
            newPickingRequest.setStatus(1);
        } else {
            throw new IllegalStateException("The Picker \"" + this.getName() + "\" already has a picking request.");
        }
    }

    public void setReady() {
        if(this.hasPickingRequest() == false) {
            if(this.isActive == true) {
                this.isActive = false;
                this.getWarehouse().getServer().addInactivePicker(this);
            } else {
                throw new IllegalStateException("The picker \"" + this.getName() + "\" is waiting for a picking request, more orders must be sent in.");
            }
        } else {
            throw new IllegalStateException("The picker \"" + this.getName() + "\" is already picking a picking request, the pick command should be used.");
        }
    }

    public void toMarshalling() {
        if (currentPick == DEFAULT_PICK_END) {
            this.isActive = true;
            this.getWarehouse().getMarshalling().dumpStock(this.forklift.popItems());
            currentPick = DEFAULT_PICK_UNASSIGNED;
            pickingRequest.setStatus(2);
        } else {
            throw new IllegalStateException("The Marshalling request of Picker " + 
                          super.getName() + "(" + this.toString() + ") must complete picking " +
                          "before instructed to Marshalling");
        }
    }
}
