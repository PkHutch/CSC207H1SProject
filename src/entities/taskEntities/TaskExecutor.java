package entities.taskEntities;

public interface TaskExecutor<T> implements TaskEntity{
	void doTask(T argument);
}