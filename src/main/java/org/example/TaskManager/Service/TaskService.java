package org.example.TaskManager.Service;

import org.example.TaskManager.Entity.Status;
import org.example.TaskManager.Entity.Task;
import java.util.List;

public interface TaskService {

    Task createTask(Task request);
    Task findTaskById(Long id);
    List<Task> getAllTasks();
    Task updateTaskById(Long id, Task request);
    void deleteTaskById(Long id);

    List<Task> findTaskByStatus(Status status);
}
