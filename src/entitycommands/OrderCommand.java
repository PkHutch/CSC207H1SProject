// Defines the package.
package entitycommands;

import entities.FaxMachine;
import entities.Order;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

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
     * @param warehouse
     *            the faxmachine parameter of type FaxMachine is used so that
     *            the command knows which faxmachine to act on, this could be
     *            given in execute command however current only one faxmachine
     *            exists at a time, meaning that the commands will only act on
     *            one FaxMachine.
     */
    public OrderCommand(FaxMachine faxMachine) {
        super(COMMAND);
        this.faxMachine = faxMachine;
    }

    // Defines the functional methods.
    /**
     * The command execution method, which creates an order, and then adds it to
     * the Warehouse FaxMachine.
     *
     * @param argument
     *            the argument of the command being passed in, the first should
     *            be a model the second should be a colour, because it is an
     *            order for fascia.
     */
    public void executeCommand(String argument) {
        String[] thisOrder = argument.split(" ", 2);
        try {
            Order order = new Order(thisOrder[1], thisOrder[0]);
            this.faxMachine.addOrder(order);
        } catch(ArrayIndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("The command \"Order\" should be followed by the name of the fascia model, and then the colour, instead \"" + argument + "\" was given.");
        }
    }
}