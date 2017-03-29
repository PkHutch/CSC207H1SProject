package entities.taskentities;

public interface TaskGiver extends TaskEntity {
	// Defines the abstract methods.
    /**
     * The doTask method of a TaskGiver takes no arguments because a TaskGiver doesn't require
     * information on how to do it's given task, as they instead give tasks to task executors in
     * the sense that their responsibility is more so to change states of the program.
     */
    public abstract void doTask();
}
