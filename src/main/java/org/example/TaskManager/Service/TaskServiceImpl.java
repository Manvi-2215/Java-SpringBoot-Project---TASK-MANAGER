package org.example.TaskManager.Service;

import org.example.TaskManager.Dto.TaskRequestDto;
import org.example.TaskManager.Dto.TaskResponseDto;
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


    public TaskResponseDto createTask(TaskRequestDto request) {

        Task entity = new Task();
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setStatus(request.getStatus());
        entity.setDueDate(request.getDueDate());

        Task saved = taskRepository.save(entity);

        // convert to response DTO
        TaskResponseDto response = new TaskResponseDto();
        response.setId(saved.getId());
        response.setTitle(saved.getTitle());
        response.setDescription(saved.getDescription());
        response.setStatus(saved.getStatus());
        response.setDueDate(saved.getDueDate());

        return response;
    }

    public TaskResponseDto findTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        TaskResponseDto response = new TaskResponseDto();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());

        return response;
    }

    public List<TaskResponseDto> getAllTasks(){
        //return taskRepository.findAll();
        List<Task> tasks = taskRepository.findAll();
        //now to convert each task in the list to a dto and then return list of response dto
        List<TaskResponseDto> taskDtoList = tasks.stream().map(task->{
            TaskResponseDto dto = new TaskResponseDto();
            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setStatus(task.getStatus());
            return dto;
        }).toList();
        return taskDtoList;
    }

    public TaskResponseDto updateTaskById(Long id, TaskRequestDto request) {
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
        Task updated = taskRepository.save(existing);

        // ENTITY → DTO
        TaskResponseDto response = new TaskResponseDto();
        response.setId(updated.getId());
        response.setTitle(updated.getTitle());
        response.setDescription(updated.getDescription());
        response.setStatus(updated.getStatus());
        response.setDueDate(updated.getDueDate());

        return response;
    }

    public List<TaskResponseDto> findTaskByStatus(Status status) {

        List<Task> tasks = taskRepository.findByStatus(status);

        return tasks.stream().map(task -> {
            TaskResponseDto dto = new TaskResponseDto();
            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setStatus(task.getStatus());
            return dto;
        }).toList();
    }

    public void deleteTaskById(Long id) {

        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task " + id + " not found");
        }

        taskRepository.deleteById(id);
    }
}
