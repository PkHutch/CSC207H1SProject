// Defines the package.
package entitycommands.workercommands;

// Defines the imports
import entities.Warehouse;
import entities.workers.Picker;
import entities.workers.Worker;

/**
 * A PickerCommand is the class responsible for handling the execution of a
 * Picker command given by the console.
 */
public class PickerCommand extends WorkerCommand<Picker> {
	// Defines the class constants.
	private static final String COMMAND = "Picker";

	// Defines constructor methods.
	/**
	 * The default PickerCommand constructor, this is the only constructor for a
	 * PickerCommand.
	 *
	 * @param warehouse
	 *            the Warehouse that is to be used in the lookupWorker of the
	 *            super class.
	 */
	public PickerCommand(Warehouse warehouse) {
		super(COMMAND, warehouse);
		// Add debug message.
	}

	protected Picker lookupWorker(String name) {
		// Use the warehouse to find the worker with the name and instanceof T.
		// Then if the worker with the given name doesn't exist, create the
		// worker and notify the
		// console.
		Worker worker = super.lookupWorker(name);
		if (worker instanceof Picker) {
			return (Picker) worker;
		} else {
			Picker newWorker = new Picker(name, this.getWarehouse());
			System.out.println("The Worker with the name " + name + " was not found, so a Picker"
					+ "has been created in their place.");
			this.getWarehouse().addWorker(newWorker);
			return newWorker;
		}
	}

	/**
	 * The executeCommand method of PickerCommand does one of three things, it
	 * tells the picker to pick, it makes them ready and available to the
	 * server, or it sends them to marshalling of the warehouse dumping their
	 * inventory.
	 *
	 * @param argument
	 *            the String which should be the name of the Picker, followed by
	 *            the command "pick x", where "x" is the number of the sequence
	 *            in the assigned picking request that the picker should pick,
	 *            starting at "1". The argument can also be followed by "to
	 *            marshaling", or "ready". Anything else is not a valid
	 *            argument.
	 */
	public void executeCommand(String argument) {
		// First lookup the Picker using super. and use the returned result as
		// the Picker in
		// question.
		// Then check the argument, split using the same method as in
		// OrderCommand.
		// If "pick" then send to doTask of the Picker with the remaining String
		// of the argument
		// stripped of "pick".
		// If "ready" then setInactive of the Picker of the warehouse.
		// If "to Marshaling" then call toMarshaling of Picker.
		// Otherwise IllegalArgumentException.
		// Don't forget debug prints.
		String[] command = argument.split(" ");
		Picker picker = (Picker) this.lookupWorker(command[0]);
		if (command[1].toLowerCase().equals("pick")) {
			picker.doTask(command[2]);
		} else if (command[1].toLowerCase().equals("to") && command[2].toLowerCase().equals("marshaling")) {
			picker.toMarshalling();
		} else if (command[1].toLowerCase().equals("ready")) {
                    picker.setReady();
                } else {
			System.out.println("You issued the task to a wrong worker");
		}
	}
}
