// Defines the package.
package entities.workers;

// Defines the imports.
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.util.LinkedList;
import java.util.List;
import entities.linkedlistcontainers.Forklift;
import entities.Level;
import entities.Stock;
import entities.taskentities.TaskExecutor;
import entities.Warehouse;
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
        System.out.println("Constructing Picker (" + this.toString() + "), with String name \"" +
            name + "\" and Warehouse " + warehouse.toString());
        this.currentPick = 0;
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
     *        8 inclusive, and must be called in order from 1 to 8.
     */
    public void doTask(String pickNumber) {
        // IE currentPick == x, where x is the int in "pick x" of the argument.
        if (currentPick == Integer.parseInt(pickNumber) - 1) {
            Level nextLevel = this.pickingLocations.pop();
            Stock desiredStock = nextLevel.removeStock();
            String nextLocation = nextLevel.getLocation();
            System.out.println("Adding item to forklift of " + desiredStock.toString() + " from level at Location " + nextLocation);
            this.forklift.addItem(desiredStock);
            currentPick++;
        // Otherwise the picker is not on the specified pick.
        } else {
            throw new IllegalArgumentException("The Picker " + this.getName() + " is not on the" +
                          " given picking number, " + this.getName() + " is on " + 
                          Integer.toString(this.currentPick));
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
        System.out.println("Calling hasPickingRequest of " + this.toString() + ".");
        System.out.println("    Returning " + this.isActive + ".");
        return (this.pickingLocations.size() != 0);
    }

    /**
     * The setPickingRequest method of Picker sets the picking request of the picker if the picker
     * does not already have a picking request.
     *
     * @param pickingRequest the LinkedList of String arrays, where the first parameter should be
     *        the zone, then the aisle, rack, and level. It does not concern the SKU of the item.
     *        This should only be called if the Picker doesn't already have a picking request.
     */
    public void setPickingLocations(List<String> newPickingLocations) {
        System.out.println("Calling setPickingRequest of " + this.toString() + " with " +
            newPickingLocations.toArray() + " as the pickingRequest argument.");
        System.out.println("    Checking if " + this.toString() + " has a current picking " + 
            "request of size zero.");
        if(this.pickingLocations.size() == 0) {
            System.out.println("    The result is true, adding to the picking locations.");
            for(int index = 0; index < newPickingLocations.size(); index++) {
                String[] newLocation = newPickingLocations.get(index).split(",");
                this.pickingLocations.add(this.getWarehouse().getFloor().getLevel(
                    newLocation[0].charAt(0), Integer.parseInt(newLocation[1]),
                    Integer.parseInt(newLocation[2]), Integer.parseInt(newLocation[3])));
            }
        } else {
            throw new IllegalArgumentException("The picking request of Picker " +
                          super.getName() + "(" + this.toString() + ") is already set.");
        }
    }

    public void setPickingRequest(PickingRequest newPickingRequest) {
        this.pickingRequest = newPickingRequest;
    }

    public void setReady() {
        if(this.pickingLocations.size() == 0 && this.isActive == true) {
            this.isActive = false;
            this.getWarehouse().getServer().addInactivePicker(this);
        } else {
            throw new IllegalStateException("The picker is currently picking a picking request.");
        }
    }

    public void toMarshalling() {
        if (currentPick == DEFAULT_PICK_END) {
            this.isActive = true;
            this.getWarehouse().getMarshalling().dumpStock(forklift.getInventory());
            forklift.getInventory().clear();
            currentPick = DEFAULT_PICK_START;
            pickingRequest.setStatus(2);
        } else {
            throw new IllegalStateException("The Marshalling request of Picker " + 
                          super.getName() + "(" + this.toString() + ") must complete picking " +
                          "before instructed to Marshalling");
        }
    }
}
