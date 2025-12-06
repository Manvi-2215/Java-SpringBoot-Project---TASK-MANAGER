package org.example.TaskManager.Service;

import org.example.TaskManager.Dto.TaskRequestDto;
import org.example.TaskManager.Dto.TaskResponseDto;
import org.example.TaskManager.Entity.Status;
import org.example.TaskManager.Exceptions.TaskNotFoundException;
import org.example.TaskManager.Repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.TaskManager.Entity.Task;
import java.util.List;

@Service
public class TaskServiceImpl {
    TaskRepository taskRepository;

    TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Autowired
    private ModelMapper mapper;


    public TaskResponseDto createTask(TaskRequestDto request) {

        //here i am converting request to entity
        Task entity = mapper.map(request, Task.class);

        Task saved = taskRepository.save(entity);

        // convert to response DTO

        TaskResponseDto response = mapper.map(saved, TaskResponseDto.class);

        return response;
    }

    public TaskResponseDto findTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));

        //convert entity into response
        TaskResponseDto response = mapper.map(task, TaskResponseDto.class);

        return response;
    }

    public List<TaskResponseDto> getAllTasks(){
        //return taskRepository.findAll();
        List<Task> tasks = taskRepository.findAll();
        //now to convert each task in the list to a dto and then return list of response dto
        List<TaskResponseDto> taskDtoList = tasks.stream().map(task->{
            TaskResponseDto dto = mapper.map(task, TaskResponseDto.class);
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
        return mapper.map(updated, TaskResponseDto.class);
    }

    public List<TaskResponseDto> findTaskByStatus(Status status) {

        List<Task> tasks = taskRepository.findByStatus(status);

        return tasks.stream().map(task -> {
            TaskResponseDto dto = mapper.map(task, TaskResponseDto.class);
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
