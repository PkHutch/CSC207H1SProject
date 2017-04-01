// Defines the package.
package entitycommands.workercommands;

// Defines the imports.
import entities.Warehouse;
import entities.workers.Loader;
import entities.workers.Worker;

/**
 * A LoaderCommand is the class responsible for handling the execution of a
 * Loader command given by the console.
 */
public class LoaderCommand extends WorkerCommand<Loader> {
	// Defines the class constants.
	private static final String COMMAND = "Loader";

	// Defines constructor methods.
	/**
	 * The default LoaderCommand constructor, this is the only constructor for a
	 * LoaderCommand.
	 *
	 * @param warehouse
	 *            the Warehouse that is to be used in the contruction of
	 *            LoaderCommand, this is necessary for the lookupWorker.
	 */
	public LoaderCommand(Warehouse warehouse) {
		super(COMMAND, warehouse);
		// Add debug message.
	}

	protected Loader lookupWorker(String name) {
		// Use the warehouse to find the worker with the name and instanceof T.
		// Then if the worker with the given name doesn't exist, create the
		// worker and notify the
		// console.
		Worker worker = super.lookupWorker(name);
		if (worker instanceof Loader) {
			return (Loader) worker;
		} else {
			Loader newWorker = new Loader(name, this.getWarehouse());
			System.out.println("The Worker with the name " + name + " was not found, so a Loader"
					+ "has been created in their place.");
			this.getWarehouse().addWorker(newWorker);
			return newWorker;
		}
	}

	/**
	 * The executeCommand method of LoaderCommand tells the loader to load, but
	 * doesn't serve any other purpose.
	 *
	 * @param argument
	 *            the String which should be the name of the Loader, followed by
	 *            the command "loads", which tells the Loader to load. Anything
	 *            else is not a valid argument.
	 */
	public void executeCommand(String argument) {
		// First lookup the Loader using super.lookupWorker and use the returned
		// result as the
		// Loader in question.
		// Then check the argument, split using the same method as in
		// OrderCommand.
		// If "loads" then doTask of the Loader.
		// Otherwise IllegalArgumentException.
		// Don't forget debug prints.
		String[] command = argument.split(" ");
		Loader loader = this.lookupWorker(command[0]);
		if (command[1].toLowerCase().equals("loads")) {
			loader.doTask(command[1]);
		} else {
			System.out.println("No such command was found");
		}
	}
}
