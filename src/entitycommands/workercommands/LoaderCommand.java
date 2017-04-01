// Defines the package.
package entitycommands.workercommands;

import entities.Warehouse;
import entities.workers.Loader;

/**
 * A LoaderCommand is the class responsible for handling the execution of a Loader command given
 * by the console.
 */
public class LoaderCommand extends WorkerCommand<Loader> {
    // Defines the class constants.
    private static final String COMMAND = "Loader";

    // Defines constructor methods.
    /**
     * The default LoaderCommand constructor, this is the only constructor for a LoaderCommand.
     *
     * @param warehouse the Warehouse that is to be used in the contruction of LoaderCommand, this
     *        is necessary for the lookupWorker.
     */
    public LoaderCommand(Warehouse warehouse) {
        super(COMMAND, warehouse);
        // Add debug message.
    }

    /**
     * The executeCommand method of LoaderCommand tells the loader to load, but doesn't serve any
     * other purpose.
     *
     * @param argument the String which should be the name of the Loader, followed by the command
     *        "loads", which tells the Loader to load. Anything else is not a valid argument.
     */
    public void executeCommand(String argument) {
        // First lookup the Loader using super.lookupWorker and use the returned result as the
        // Loader in question.
        // Then check the argument, split using the same method as in OrderCommand.
        // If "loads" then doTask of the Loader.
        // Otherwise IllegalArgumentException.
        // Don't forget debug prints.
    	String[] command = argument.split(" ");
    	if (command[0].equals(COMMAND)){
    		Loader loader = (Loader) this.lookupWorker(command[1]);
    		loader.doTask(argument);
    	}else{
    		System.out.println("You issued the task to a wrong Worker");
    	}
    }
}
