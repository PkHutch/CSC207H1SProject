// Defines the package.
package entitycommands.workercommands;

// Defines the imports.
import entities.Warehouse;
import entities.workers.Sequencer;

/**
 * A SequencerCommand is the class responsible for handling the execution of a Picker command given
 * by the console.
 */
public class SequencerCommand extends WorkerCommand<Sequencer> {
    // Defines the class constants.
    private static final String COMMAND = "Sequencer";

    // Defines the instance variables.
    private Warehouse warehouse;

    // Defines constructor methods.
    /**
     * The default SequencerCommand constructor, this is the only constructor for a SequencerCommand.
     *
     * @param warehouse the Warehouse that is to be used in both the execution of executeCommand
     *        and given to WorkerCommand for lookupWorker.
     */
    public PickerCommand(Warehouse warehouse) {
        super(COMMAND, warehouse);
        // Add debug message.
        this.warehouse = warehouse;
    }

    /**
     * The executeCommand method of SequencerCommand tells the sequencer to sequence, but doesn't
     * serve any other purpose.
     *
     * @param argument the String which should be the name of the Sequencer, followed by the
     *        command "sequence", which tells the sequencer to sequence. Anything else is not a
     *        valid argument.
     */
    public void executeCommand(String argument) {
        // First lookup the Sequencer using super. and use the returned result as the Sequencer in
        // question.
        // Then check the argument, split using the same method as in OrderCommand.
        // If "sequence" then doTask of the Sequencer
        // Otherwise IllegalArgumentException.
        // Don't forget debug prints.
    }
}