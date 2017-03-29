// Defines the package.
package entitycommands.workercommands;

/**
 * A ReplenisherCommand is the class responsible for handling the execution of a Replenisher
 * command given by the console.
 */
public class ReplenisherCommand extends WorkerCommand<Replenisher> {
    // Defines the class constants.
    private static final String COMMAND = "Replenisher";

    // Defines constructor methods.
    /**
     * The default ReplenisherCommand constructor, this is the only constructor for a
     * ReplenisherCommand.
     *
     * @param warehouse the Warehouse that is to be used in the lookupWorker of the super class.
     */
    public ReplenisherCommand(Warehouse warehouse) {
        super(COMMAND, warehouse);
        // Add debug message.
    }

    /**
     * The executeCommand method of ReplenisherCommand tells the Replenisher to replenish, and
     * which Level to replenish.
     *
     * @param argument the String which should be the name of the Replenisher, followed by the
     *        command "replenish a b c d", where "a" is the zone character, "b" is the aisle, 
     *        "c", is the rack, and "d" is the level. Anything else is not a valid argument.
     */
    public void executeCommand(String argument) {
        // First lookup the Replenisher using super.lookupWorker.
        // Then check the argument, split using the same method as in OrderCommand.
        // If "replenish" then send to doTask of the Replenisher with the remaining String of the argument
        // stripped of "replenish".
        // Otherwise IllegalArgumentException.
        // Don't forget debug prints.
    }
}