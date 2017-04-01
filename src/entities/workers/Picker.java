// Defines the package.
package entities.workers;

// Defines the imports.
import java.lang.IllegalArgumentException;
import java.util.LinkedList;
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
 * The Picker class extends the worker class and takes an arguement of String
 * for it's doTask method. The Pickers job to stock off the shelves and bring
 * them to the marshalling area as per the server's instructions.
 */
public class Picker extends Worker implements TaskExecutor<String> {
	// Defines the Picker variables.
	private boolean isActive = false;
	private int currentPick = 0;
	private Forklift forklift = new Forklift();
	private LinkedList<String[]> PickingLocations;
	private PickingRequest pickingRequest;
	private static int DEFAULT_PICK_START = 0;
	private static int DEFAULT_PICK_END = 8;

	// Defines the constructor methods.
	/**
	 * The only contructor method for a Picker.
	 *
	 * @param name
	 *            the String name, should be the name of that picker, ideally it
	 *            is unique but the name doesn't necessarily have to be unique
	 *            as of now, which could cause some undesired results.
	 * @param warehouse
	 *            the Warehouse that the Picker belongs to.
	 */
	public Picker(String name, Warehouse warehouse) {
		super(name, warehouse);
		System.out.println("Constructing Picker (" + this.toString() + "), with String name \"" + name
				+ "\" and Warehouse " + warehouse.toString());
		this.isActive = false;
		this.PickingLocations = new LinkedList<>();
	}

	// Defines the functional methods.
	/**
	 * The doTask method for a Picker specifically serves to take in the String
	 * argument from a command given in the console.
	 *
	 * @param argument
	 *            The valid commands are "pick x", where "x" is a String number
	 *            between 1 and 8 inclusive, and must be called in order from 1
	 *            to 8.
	 */
	public void doTask(String pickNumber) {

		// IE currentPick == x, where x is the int in "pick x" of the argument.
		if (currentPick == Integer.parseInt(pickNumber) - 1) {
			String[] nextLocation = PickingLocations.pop();
			char zone = nextLocation[0].charAt(0);
			Integer aisle = Integer.parseInt(nextLocation[1]);
			Integer rack = Integer.parseInt(nextLocation[2]);
			Integer level = Integer.parseInt(nextLocation[3]);

			Floor floor = this.getWarehouse().getFloor();
			Zone currZone = floor.getItem(zone);
			Aisle currAisle = currZone.getItem(aisle);
			Rack currRack = currAisle.getItem(rack);
			Level currLevel = currRack.getItem(level);
			this.forklift.addItem(new Stock(currLevel.removeStock()));
			currentPick++;

			// Otherwise the picker is not on the specified pick.
		} else {
			throw new IllegalArgumentException(
					"The Picker " + this.getName() + " is not " + "on the given picking number, " + this.getName()
							+ " is on " + Integer.toString(this.currentPick));
		}

	}

	/**
	 * The hasPickingRequest method for a Picker returns whether or not the
	 * Picker has been assigned to a picking request by the Server, and they are
	 * currently picking it.
	 *
	 * @return the value true is given if Picker is currently picking a picking
	 *         request and false otherwise.
	 */
	public boolean hasPickingRequest() {
		System.out.println("Calling hasPickingRequest of " + this.toString() + ".");
		System.out.println("    Returning " + this.isActive + ".");
		return this.isActive;
	}

	/**
	 * The setPickingRequest method of Picker sets the picking request of the
	 * picker if the picker does not already have a picking request.
	 *
	 * @param pickingRequest
	 *            the LinkedList of String arrays, where the first parameter
	 *            should be the zone, then the aisle, rack, and level. It does
	 *            not concern the SKU of the item. This should only be called if
	 *            the Picker doesn't already have a picking request.
	 */
	public void setPickingLoactions(LinkedList<String[]> pickingLocations) {
		System.out.println("Calling setPickingRequest of " + this.toString() + " with " + pickingLocations.toArray()
				+ " as the pickingRequest argument.");
		System.out.println("    Checking if " + this.toString() + " has a current picking "
				+ "request of size zero, which is currently " + pickingLocations.toArray() + ".");
		if (PickingLocations.size() == 0) {
			this.PickingLocations = pickingLocations;
		} else {
			throw new IllegalArgumentException(
					"The picking request of Picker " + super.getName() + "(" + this.toString() + ") is already set.");
		}
	}

	public void setPickingRequest(PickingRequest newPickingRequest) {
		this.pickingRequest = newPickingRequest;
	}

	public void toMarshalling() {
		// Make the picker no longer active and dump the inventory into the
		// Marshalling inventory.
		// This should also notify the server, and add this to the activePicking
		// requests.
		if (currentPick == DEFAULT_PICK_END) {
			this.isActive = false;
			this.getWarehouse().getMarshalling().addStock(forklift);
			forklift.getInventory().clear();
			currentPick = DEFAULT_PICK_START;
			pickingRequest.setStatus(2);
		} else {
			throw new IllegalStateException("The Marshalling request of Picker" + super.getName() + "("
					+ this.toString() + ") must complete picking before instructed to Marshalling");
		}

	}
}
