// Defines the package.
package entitycommands.workercommands;

// Defines the imports
import entities.Warehouse;
import entities.workers.Picker;
import entities.workers.Worker;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

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
    }

    // Defines the functional methods.
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
        // The first part is the name, then the command, then the argument for
        // the command.
        String[] splitArgument = argument.split(" ", 2);
        try {
            if (splitArgument[1].startsWith("pick")) {
                try {
                    this.lookupPicker(splitArgument[0]).doTask(splitArgument[1].substring(5));
                } catch (StringIndexOutOfBoundsException exception) {
                    throw new IllegalArgumentException(
                            "The command \"pick\" was given for the Picker command, and should have been followed by a number, but was instead followed by nothing.");
                }
            } else if (splitArgument[1].startsWith("to Marshaling")) {
                if (splitArgument[1].length() > 13) {
                    throw new IllegalArgumentException(
                            "The command \"to marshaling\" was given for the Picker command, and should have been followed by nothing, but was instead followed by \""
                                    + splitArgument[1].substring(13) + "\".");
                } else {
                    this.lookupPicker(splitArgument[0]).toMarshalling();
                }
            } else if (splitArgument[1].startsWith("ready")) {
                if (splitArgument[1].length() > 5) {
                    throw new IllegalArgumentException(
                            "The command \"ready\" was given for the Picker command, and should have been followed by nothing, but was instead followed by \""
                                    + splitArgument[1].substring(5) + "\".");
                } else {
                    this.lookupPicker(splitArgument[0]).setReady();
                }
            } else {
                throw new IllegalArgumentException("The command \"" + splitArgument[1]
                        + "\" is not a valid Picker command, the valid commands are \"pick\", \"to marshaling\", and \"ready\".");
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalArgumentException(
                    "The command \"Picker\" should be followed by the name of the picker, and then a valid command, instead \""
                            + argument + "\" was given.");
        }
    }

    // Defines the helper methods.
    /**
    */
    private Picker lookupPicker(String name) {
        Worker worker = super.lookupWorker(name);
        if (worker instanceof Picker) {
            return (Picker) worker;
        } else {
            Picker newWorker = new Picker(name, this.getWarehouse());
            System.out.println(
                    "    The Picker with the name \"" + name + "\" was not found, so that Picker has been created.");
            this.getWarehouse().addWorker(newWorker);
            return newWorker;
        }
    }
}
