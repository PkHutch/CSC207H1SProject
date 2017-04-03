// Defines the package.
package entitycommands.workercommands;

// Defines the imports.
import entities.Warehouse;
import entities.workers.Replenisher;
import entities.workers.Worker;

/**
 * A ReplenisherCommand is the class responsible for handling the execution of a
 * Replenisher command given by the console.
 */
public class ReplenisherCommand extends WorkerCommand<Replenisher> {
    // Defines the class constants.
    private static final String COMMAND = "Replenisher";

    // Defines constructor methods.
    /**
     * The default ReplenisherCommand constructor, this is the only constructor
     * for a ReplenisherCommand.
     *
     * @param warehouse
     *            the Warehouse that is to be used in the lookupWorker of the
     *            super class.
     */
    public ReplenisherCommand(Warehouse warehouse) {
        super(COMMAND, warehouse);
        // Add debug message.
    }

    protected Replenisher lookupReplenisher(String name) {
        // Use the warehouse to find the worker with the name and instanceof T.
        // Then if the worker with the given name doesn't exist, create the
        // worker and notify the
        // console.
        Worker worker = super.lookupWorker(name);
        if (worker instanceof Replenisher) {
            return (Replenisher) worker;
        } else {
            Replenisher newWorker = new Replenisher(name, this.getWarehouse());
            System.out.println("The Worker with the name " + name + " was not found, so a Replenshier"
                    + "has been created in their place.");
            this.getWarehouse().addWorker(newWorker);
            return newWorker;
        }
    }

    /**
     * The executeCommand method of ReplenisherCommand tells the Replenisher to
     * replenish, and which Level to replenish.
     *
     * @param argument
     *            the String which should be the name of the Replenisher,
     *            followed by the command "replenish a b c d", where "a" is the
     *            zone character, "b" is the aisle, "c", is the rack, and "d" is
     *            the level. Anything else is not a valid argument.
     */
    public void executeCommand(String argument) {
        // First lookup the Replenisher using super.lookupWorker.
        // Then check the argument, split using the same method as in
        // OrderCommand.
        // If "replenish" then send to doTask of the Replenisher with the
        // remaining String of the argument
        // stripped of "replenish".
        // Otherwise IllegalArgumentException.
        // Don't forget debug prints.
        String[] splitArgument = argument.split(" ", 3);
        try {
            if (splitArgument[1].equals("replenishes")) {
                try {
                    this.lookupReplenisher(splitArgument[0]).doTask(splitArgument[3]);
                } catch (StringIndexOutOfBoundsException exception) {
                    throw new IllegalArgumentException(
                            "The command \"loads\" was given for the Replenisher command and a location, but got something else instead.");
                }
            } else if (splitArgument[1].equals("ready")) {
                if (splitArgument[1].length() > 5) {
                    throw new IllegalArgumentException(
                            "The command \"ready\" was given for the Replenisher command, and should have been followed by nothing, but was instead followed by \""
                                    + splitArgument[1].substring(5) + "\".");
                } else {
                    // this.lookupReplenisher(splitArgument[0]).setReady();
                }
            } else {
                throw new IllegalArgumentException("The command \"" + splitArgument[1]
                        + "\" is not a valid Replenisher command, the valid commands are \"replenishes\",\"ready\".");
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalArgumentException(
                    "The command \"Replenisher\" should be followed by the name of the Replenisher, and then a valid command, instead \""
                            + argument + "\" was given.");
        }
    }
}
