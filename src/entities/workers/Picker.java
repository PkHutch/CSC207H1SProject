// Defines the package.
package entities.workers;
// Defines the imports
import entities.Warehouse;
import java.util.LinkedList;


/**
 * The Picker class extends the worker class and takes an arguement of String
 * for it's doTask method. The Pickers job to stock off the shelves and bring
 * them to the marshalling area as per the server's instructions.
 */
public class Picker extends Worker {
	// Defines the Picker instance variables.
	private boolean isActive;
	private LinkedList<String[]> currentPickingRequest;

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
		this.currentPickingRequest = new LinkedList<String[]>();
	}

	// Defines the functional methods.
	/**
	 * The doTask method for a Picker specifically serves to take in the String
	 * argument from a command given in the console.
	 *
	 * @param argument
	 *            The valid commands are "x", where "x" is a String number
	 *            between 1 and 8 inclusive, and must be called in order from 1
	 *            to 8, for it to pick the "x"th item in it's picking request.
	 */
	public void doTask(String argument) {
		System.out.println("Calling doTask of " + this.toString() + 
				" with String argument \"" + argument + "\".");
		// Look at the legacy code for help, but the picker should pick the next
		// item in the list
		// from it's currentPickingRequest, make sure to pop so that the size of
		// LinkedList can be used.
		String[]command = argument.split(" ");
		switch(command[2].toLowerCase()){
			case "picks":            picks(command[3]+" "+ command[4]);
						            break;
			case "ready":           this.isActive = true;
						            break;
			case "to marshalling" : toMarshalling();
									break;
									
			default : System.out.println("That was an invalid command");
					                break;
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
		return isActive;
		// Already implemented.
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
	public void setPickingRequest(LinkedList<String[]> pickingRequest) {
		//dummy function
		this.currentPickingRequest.add(new String[1]);
	}

	/**
	 * The toMarshalling method of Picker is called once the Picker has picked
	 * all of their fascia and dumps the inventory of the Picker Forklift into
	 * the Marshalling inventory. It then sets the Picker to inactive.
	 */
	public void toMarshalling() {
		// Make the picker no longer active and dump the inventory into the
		// Marshalling inventory.
		// This should also notify the server, and add this to the activePicking
		// requests.
		this.isActive = false;
		//empty inventory(?)
	}
	
	public void picks(String argument){
		//adds item into inventory according to location
		System.out.println("Not yet implemented");
	}
}
