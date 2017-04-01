// Defines the package.
package entitycommands.workercommands;

import entities.Warehouse;
import entities.workers.Sequencer;

/**
 * A SequencerCommand is the class responsible for handling the execution of a Picker command given
 * by the console.
 */
public class SequencerCommand extends WorkerCommand<Sequencer> {
    // Defines the class constants.
    private static final String COMMAND = "Sequencer";

    // Defines constructor methods.
    /**
     * The default SequencerCommand constructor, this is the only constructor for a SequencerCommand.
     *
     * @param warehouse the Warehouse that is to be used for lookupWorker.
     */
    public SequencerCommand(Warehouse warehouse) {
        super(COMMAND, warehouse);
        // Add debug message.
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
    	String[] command = argument.split(" ");
    	if (command[0].equals(COMMAND)){
    		Sequencer sequencer = (Sequencer) this.lookupWorker(command[1]);
    		sequencer.doTask(argument);
    	}else{
    		System.out.println("You issued the task to a wrong Worker");
    	}
    }
}
