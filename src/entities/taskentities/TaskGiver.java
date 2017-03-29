// Defines the package.
package entities.taskentities;

/**
 * A TaskGiver is a TaskEntity who implements a doTask method. The purpose is to show that a
 * TaskGiver executes a task but giving a new state in the process, and needs no context on how to
 * execute it's task.
 */
public interface TaskGiver extends TaskEntity {
    // Defines the abstract methods.
    /**
     * The doTask method of a TaskGiver takes no arguments because a TaskGiver doesn't require
     * information on how to do it's given task, as they instead give tasks to task executors in
     * the sense that their responsibility is more so to change states of the program.
     */
    public abstract void doTask();
}
