package entities.taskentities;

public interface TaskExecutor<T> implements TaskEntity{
	void doTask(T argument);
}