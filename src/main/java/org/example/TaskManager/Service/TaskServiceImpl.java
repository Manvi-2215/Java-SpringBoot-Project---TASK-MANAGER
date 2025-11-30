package org.example.TaskManager.Service;

import org.example.TaskManager.Entity.Status;
import org.example.TaskManager.Repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.example.TaskManager.Entity.Task;
import java.util.List;

@Service
public class TaskServiceImpl {
    TaskRepository taskRepository;

    TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task request){
        return taskRepository.save(request);
    }

    public Task findTaskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task updateTaskById(Long id, Task request){
        // 1. Fetch existing task
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task " + id + " not found"));

        // 2. Update fields safely
        if (request.getTitle() != null) {
            existing.setTitle(request.getTitle());
        }

        if (request.getDescription() != null) {
            existing.setDescription(request.getDescription());
        }

        if (request.getStatus() != null) {
            existing.setStatus(request.getStatus());
        }

        // 3. Save and return updated task
        return taskRepository.save(existing);
    }

    public List<Task> findTaskByStatus(Status status){
        return taskRepository.findByStatus(status);
    }

    public void deleteTaskById(Long id){
        taskRepository.deleteById(id);
    }
}
