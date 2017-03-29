package entities.taskentities;

public interface TaskExecutor<T>extends TaskEntity {
	// Defines the abstract methods.
    /**
     * The doTask method of a TaskExecutor should implement the task that the TaskExecutor
     * preforms, because a TaskExector doesn't give tasks, but instead it only executes, a
     * TaskExecutor takes a generic type argument, which is the information necessary for a
     * TaskExecutor to complete their task.
     *
     * @param argument the generic type T, an argument should be the information necessary for
     *        the entity to do their task.
     */
    public abstract void doTask(T argument);
}
