// Defines the package.
package entitycommands;

/**
 * A command class subclass, the Order class is used to create orders.
 */
public class OrderCommand extends EntityCommand {
    // Defines the constants.
    private static final String COMMAND = "Order";

    // Defines the instance variables.
    private FaxMachine faxMachine;

    // Defines the constructors.
    /**
     * The main constructor of OrderCommand.
     *
     * @param warehouse the faxmachine parameter of type FaxMachine is used so that the command
     *        knows which faxmachine to act on, this could be given in execute command however
     *        current only one faxmachine exists at a time, meaning that the commands will only
     *        act on one FaxMachine.
     */
    public OrderCommand(FaxMachine faxmachine) {
        super(COMMAND);
        System.out.println("Constructing OrderCommand " + this.toString() + " with argument " + 
            "faxmachine as " + faxmachine.toString() + ".");
        // This is implemented elsewhere, do not worry.
    }

    // Defines the functional methods.
    /**
     * The command execution method, which creates an order, and then adds it to the Warehouse
     * FaxMachine.
     *
     * @param argument the argument of the command being passed in, the first should be a model
     *        the second should be a colour, because it is an order for fascia.
     */
    public void executeCommand(String argument) {
        System.out.println("Calling executeCommand of OrderCommand " + this.toString() + 
            " with argument argument as " + argument + ".");
        // This is implemented elsewhere, do not worry.
    }
}