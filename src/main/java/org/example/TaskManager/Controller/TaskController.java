package org.example.TaskManager.Controller;
import org.example.TaskManager.Dto.TaskRequestDto;
import org.example.TaskManager.Dto.TaskResponseDto;
import org.example.TaskManager.Entity.Status;
import org.example.TaskManager.Service.TaskService;
import org.example.TaskManager.Service.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.example.TaskManager.Entity.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    TaskServiceImpl taskService;

    TaskController(TaskServiceImpl taskService){
        this.taskService = taskService;
    }
    //if not above way for dependency injection
    //we would have autowired taskService and in config class using @bean create object of taskServiceImpl to inject

    //create task
    @PostMapping
    public ResponseEntity<TaskResponseDto> create(@RequestBody TaskRequestDto request) {
        TaskResponseDto response = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //read all taks
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTask(){
        List<TaskResponseDto> allTasks = taskService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(allTasks);
    }

    //find by id
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskByID(@PathVariable Long id){
        TaskResponseDto response = new TaskResponseDto();
        response = taskService.findTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //filter task by status
    public ResponseEntity<List<TaskResponseDto>> getTaskByStatus(@PathVariable Status status) {
        List<TaskResponseDto> taskByStatus = taskService.findTaskByStatus(status);
        return ResponseEntity.ok(taskByStatus);
    }

    //update task by id
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTaskByID(
            @PathVariable Long id,
            @RequestBody TaskRequestDto request
    ) {
        TaskResponseDto updatedTask = taskService.updateTaskById(id, request);
        return ResponseEntity.ok(updatedTask);
    }

    //delete task by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
