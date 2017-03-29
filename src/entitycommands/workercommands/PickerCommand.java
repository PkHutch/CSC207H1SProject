// Defines the package.
package entitycommands.workercommands;

/**
 * A PickerCommand is the class responsible for handling the execution of a Picker command given
 * by the console.
 */
public class PickerCommand extends WorkerCommand<Picker> {
    // Defines the class constants.
    private static final String COMMAND = "Picker";

    // Defines constructor methods.
    /**
     * The default PickerCommand constructor, this is the only constructor for a PickerCommand.
     *
     * @param warehouse the Warehouse that is to be used in the nameLookup.
     */
    public PickerCommand(Warehouse warehouse) {
        super(COMMAND, warehouse);
        // Add debug message.
    }

    /**
     * The executeCommand method of PickerCommand does one of three things, it tells the picker to
     * pick, it makes them ready and available to the server, or it sends them to marshalling of
     * the warehouse dumping their inventory.
     *
     * @param argument the String which should be the name of the Picker, followed by the command
     *        "pick x", where "x" is the number of the sequence in the assigned picking request
     *        that the picker should pick, starting at "1". The argument can also be followed by
     *        "to marshaling", or "ready". Anything else is not a valid argument.
     */
    public void executeCommand(String argument) {
        // First lookup the Picker using super. and use the returned result as the Picker in
        // question.
        // Then check the argument, split using the same method as in OrderCommand.
        // If "pick" then send to doTask of the Picker with the remaining String of the argument
        // stripped of "pick".
        // If "ready" then setInactive of the Picker of the warehouse.
        // If "to Marshaling" then call toMarshaling of Picker.
        // Otherwise IllegalArgumentException.
        // Don't forget debug prints.
    }
}