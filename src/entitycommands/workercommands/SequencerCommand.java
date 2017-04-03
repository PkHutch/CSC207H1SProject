// Defines the package.
package entitycommands.workercommands;

// Defines the imports.
import entities.Warehouse;
import entities.workers.Sequencer;
import entities.workers.Worker;

/**
 * A SequencerCommand is the class responsible for handling the execution of a
 * Picker command given by the console.
 */
public class SequencerCommand extends WorkerCommand<Sequencer> {
    // Defines the class constants.
    private static final String COMMAND = "Sequencer";

    // Defines constructor methods.
    /**
     * The default SequencerCommand constructor, this is the only constructor
     * for a SequencerCommand.
     *
     * @param warehouse
     *            the Warehouse that is to be used for lookupWorker.
     */
    public SequencerCommand(Warehouse warehouse) {
        super(COMMAND, warehouse);
        // Add debug message.
    }

    protected Sequencer lookupSequencer(String name) {
        // Use the warehouse to find the worker with the name and instanceof T.
        // Then if the worker with the given name doesn't exist, create the
        // worker and notify the
        // console.
        Worker worker = super.lookupWorker(name);
        if (worker instanceof Sequencer) {
            return (Sequencer) worker;
        } else {
            Sequencer newWorker = new Sequencer(name, this.getWarehouse());
            System.out.println("The Worker with the name " + name + " was not found, so a Sequencer"
                    + "has been created in their place.");
            this.getWarehouse().addWorker(newWorker);
            return newWorker;
        }
    }

    /**
     * The executeCommand method of SequencerCommand tells the sequencer to
     * sequence, but doesn't serve any other purpose.
     *
     * @param argument
     *            the String which should be the name of the Sequencer, followed
     *            by the command "sequence", which tells the sequencer to
     *            sequence. Anything else is not a valid argument.
     */
    public void executeCommand(String argument) {
        // First lookup the Sequencer using super. and use the returned result
        // as the Sequencer in
        // question.
        // Then check the argument, split using the same method as in
        // OrderCommand.
        // If "sequence" then doTask of the Sequencer
        // Otherwise IllegalArgumentException.
        // Don't forget debug prints.
        String[] splitArgument = argument.split(" ");
        try {
            if (splitArgument[1].equals("sequences")) {
                try {
                    this.lookupSequencer(splitArgument[0]).doTask();
                } catch (StringIndexOutOfBoundsException exception) {
                    throw new IllegalArgumentException(
                            "The command \"loads\" was given for the Sequencer command, but got something else instead.");
                }
            } else if (splitArgument[1].equals("ready")) {
                if (splitArgument[1].length() > 5) {
                    throw new IllegalArgumentException(
                            "The command \"ready\" was given for the Sequencer command, and should have been followed by nothing, but was instead followed by \""
                                    + splitArgument[1].substring(5) + "\".");
                } else {
                    this.lookupSequencer(splitArgument[0]).setReady();
                }
            } else {
                throw new IllegalArgumentException("The command \"" + splitArgument[1]
                        + "\" is not a valid Sequencer command, the valid commands are \"sequences\",\"ready\".");
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalArgumentException(
                    "The command \"Sequencer\" should be followed by the name of the Sequencer, and then a valid command, instead \""
                            + argument + "\" was given.");
        }
    }
}
