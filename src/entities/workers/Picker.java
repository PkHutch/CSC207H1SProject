// Defines the package.
package entities.workers;

// Defines the imports.
import java.lang.IllegalArgumentException;
import java.util.LinkedList;
import entities.Level;
import entities.linkedlistcontainers.Forklift;
import entities.Stock;
import entities.taskentities.TaskExecutor;
import entities.Warehouse;
import entities.Worker;

/**
 * The Picker class extends the worker class and takes an arguement of String for it's doTask
 * method. The Pickers job to stock off the shelves and bring them to the marshalling area as
 * per the server's instructions.
 */
public class Picker extends Worker implements TaskExecutor<String> {
    // Defines the Picker variables.
    private boolean isActive = false;
    private int currentPick = 1;
    private Forklift forklift;
    private LinkedList<String[]> currentPickingRequest;
    private static int DEFAULT_PICK_START = 1;
    private static int DEFAULT_PICK_END = 8;

    // Defines the constructor methods.
    public Picker(String name, Warehouse warehouse) {
        super(name, warehouse);
        this.forklift = new Forklift();
    }

    // Defines the functional methods.
    /**
     * The method that does the main task that a Picker serves to do, specifically it
     * serves to take in the argument from a command given in the console. If the picker is ready
     * it adds the picker to the inactivePickers list of the server. If the picker is supposed to
     * pick then it ensures that the picker is picking in the correct order.s
     *
     * @param argument the String which tells the picker whether or not it is ready, or picking.
     * @throws IllegalArgumentException throws when the parameter given in String argument is not
     *         command for a Picker. Valid commands include ready, and pick, where pick is
     *         by a space or the number that the picker is currently on in their current
     *         picking request. Will also throw when the incorrect pick number is given, when
     *         ready is called an the picker is already picking, when a picker is told to go to
     *         marshalling prematurely, or a a picker is told to pick when a refill needs to be
     *         done.
     */
    public void doTask(String argument) {
        if(argument.equals("ready")) {
            if(this.currentPick == DEFAULT_PICK_START && this.isActive == false) {
                this.getWarehouse().getServer().addInactivePicker(this);
                this.getWarehouse().getServer().issueTask(this);
                this.isActive = true;
            } else {
                throw new IllegalArgumentException("The Picker " + this.getName() + " is " +
                               "currently picking, they can not be ready!");
            }
        } else if(argument.startsWith("pick")) {
            // If the warehouse needs a refill, a replenishing request must be made.
            if (this.getWarehouse().getServer().needsRefill()) {
                throw new IllegalArgumentException("The Picker " + this.getName() + " is not " +
                               "able to pick, a replenishing request must be made first.");
            // IE currentPick == x, where x is the int in "pick x" of the argument.
            } else if(currentPick == Integer.parseInt(argument.split(" ")[1])) {
                String[] nextLocation = currentPickingRequest.pop();

                Level nextLevel = this.getWarehouse().getFloor().getLevel(
                                     nextLocation[0].charAt(0), Integer.parseInt(nextLocation[1]),
                                     Integer.parseInt(nextLocation[2]),
                                     Integer.parseInt(nextLocation[3]));

                this.forklift.addItem(nextLevel.removeItem());
                this.getWarehouse().getServer().issueTask(nextLevel);
            // Otherwise the picker is not on the specified pick.
            } else {
                throw new IllegalArgumentException("The Picker " + this.getName() + " is not " +
                               "on the given picking number, " + this.getName() + " is on " +
                               Integer.toString(this.currentPick));
            }
        } else if(argument.equals("to Marshaling")) {
            if(this.currentPick == DEFAULT_PICK_END + 1) {
                LinkedList<Stock> currentInventory = this.forklift.getInventory();
                int inventorySize = currentInventory.size();

                for(int count = 0; count < inventorySize; count++) {
                    this.getWarehouse().getMarshalling().addStock(currentInventory.pop());
                }

                this.currentPick = DEFAULT_PICK_START;
                this.isActive = false;
            } else {
                throw new IllegalArgumentException("The Picker " + this.getName() + " can't " +
                               "go to marshalling, they do not have a full forklift!");
            }
        } else {
            throw new IllegalArgumentException(argument + " called on Picker " + this.getName() +
                           " is an illegal command!");
        }
    }

    public boolean hasPickingRequest() {
    	return this.isActive;
    }

    public void setPickingRequest(LinkedList<String[]> pickingRequest) {
        if(currentPickingRequest.size() == 0) {
            this.currentPickingRequest = pickingRequest;
        }
    }
}
