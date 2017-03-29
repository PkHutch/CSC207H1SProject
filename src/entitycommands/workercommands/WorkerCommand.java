// Defines the package.
package entitycommands.workercommands;

// Defines the imports.
import entitycommands.EntityCommand;

/**
 * A WorkerCommand is an abstract class that should be utilized for the commands involving
 * workers, so that they can inherit the lookupWorker method.
 */
public abstract class WorkerCommand<T extends Worker> extends EntityCommand {
    // Defines the instance methods.
    private Warehouse warehouse;

    // Defines contructor methods.
    /**
     * The default constructor, a worker command must have a Warehouse so that the lookupWorker
     * is properly implemented.
     *
     * @param command the String which is responsible for calling the command.
     * @param warehouse the Warehouse which the lookupWorker requires inorder to look up the name
     *        of the worker requested.
     */
    public WorkerCommand(String command, Warehouse warehouse) {
        super(command);
        // Add debug message.
        this.warehouse = warehouse;
    }

    // Defines functional methods.
    /**
     * The executeCommand method is the main purpose of each command class, and should execute
     * the functionality of the command.
     *
     * @param argument the String argument should be the argument that a command requires to
     *        execute. Each command will have their own way of dealing with this String.
     */
    public abstract void executeCommand(String argument);

    /**
     * The lookupWorker method will either return the worker of the type T, which has the matching
     * name of the String given, and will notify the console if a new Worker had to be
     * instantiated inorder to facilitate the command.
     *
     * @param name the String name of the Worker to be looked up or returned.
     */
    protected Worker lookupWorker(String name) {
        // Use the warehouse to find the worker with the name and instanceof T.
        // Then if the worker with the given name doesn't exist, create the worker and notify the
        // console.
        return new Worker("test", this.warehouse);
    }
}