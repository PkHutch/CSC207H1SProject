package entities.taskEntities;

public interface TaskExecutor<t> implements TaskEntity{
	void doTask(T argument);
}