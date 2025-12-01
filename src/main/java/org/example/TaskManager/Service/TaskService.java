package org.example.TaskManager.Service;

import org.example.TaskManager.Dto.TaskRequestDto;
import org.example.TaskManager.Dto.TaskResponseDto;
import org.example.TaskManager.Entity.Status;
import org.example.TaskManager.Entity.Task;
import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto request);
    TaskResponseDto updateTaskById(Long id, TaskRequestDto request);
    TaskResponseDto findTaskById(Long id);
    List<TaskResponseDto> findTaskByStatus(Status status);
    void deleteTaskById(Long id);
    List<TaskResponseDto> getAllTasks();
}
