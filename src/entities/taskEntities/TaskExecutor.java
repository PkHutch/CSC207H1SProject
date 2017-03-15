package entities.taskentities;

import entities.TaskEntity;

public interface TaskExecutor<T> extends TaskEntity{
	void doTask(T argument);
}