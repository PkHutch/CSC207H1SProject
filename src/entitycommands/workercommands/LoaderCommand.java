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

    protected Loader lookupLoader(String name) {
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
        String[] splitArgument = argument.split(" ", 2);
        try {
            if (splitArgument[1].equals("loads")) {
                try {
                    this.lookupLoader(splitArgument[0]).doTask();
                } catch (StringIndexOutOfBoundsException exception) {
                    throw new IllegalArgumentException(
                            "The command \"loads\" was given for the Loader command, but got something else instead.");
                }
            } else if (splitArgument[1].equals("ready")) {
                if (splitArgument[1].length() > 5) {
                    throw new IllegalArgumentException(
                            "The command \"ready\" was given for the Loader command, and should have been followed by nothing, but was instead followed by \""
                                    + splitArgument[1].substring(5) + "\".");
                } else {
                    this.lookupLoader(splitArgument[0]).setReady();
                }
            } else {
                throw new IllegalArgumentException("The command \"" + splitArgument[1]
                        + "\" is not a valid Loader command, the valid commands are \"loads\",\"ready\".");
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalArgumentException(
                    "The command \"Loader\" should be followed by the name of the Loader, and then a valid command, instead \""
                            + argument + "\" was given.");
        }
    }
}
