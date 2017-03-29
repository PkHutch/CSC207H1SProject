// Defines the package.
package entitycommands;

/**
 * The abstract class command represents a command that is to be put into the main program.
 */
public abstract class EntityCommand {
    // Defines instance variables.
    private String command;

    // Defines constructors.
    /**
     * The main constructor for creating a command with some command name.
     *
     * @param command the String which defines what the command is called.
     */
    public EntityCommand(String command) {
        System.out.println("Contructing EntityCommand " + this.toString() + " with argument " +
            " command as " + command + ".");
        this.command = command;
    }

    // Defines the functional methods.
    /**
     * The executeCommand method is the main purpose of each command class, and should execute
     * the functionality of the command.
     *
     * @param argument the String argument should be the argument that a command requires to
     *        execute. Each command will have their own way of dealing with this String.
     */
    public abstract void executeCommand(String argument);

    /**
     * The getCommand method is for getting the name of the command.
     *
     * @return the return of type String, is the string name of the command.
     */
    public String getCommand() {
        System.out.println("Calling getCommand of " + this.toString() + ".");
        System.out.println("    Returning the command " + this.command + ".");
        return this.command;
    }
}