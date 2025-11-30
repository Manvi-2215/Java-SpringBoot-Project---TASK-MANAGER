package org.example.TaskManager.Controller;

import org.example.TaskManager.Entity.Status;
import org.example.TaskManager.Service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.example.TaskManager.Entity.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    TaskService taskService;

    TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    //create task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task request){
        Task task = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    //read all taks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTask(){
        List<Task> allTasks = taskService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(allTasks);
    }

    //find by id
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskByID(@PathVariable Long id){
        Task taskById = taskService.findTaskById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(taskById);
    }

    //filter task by status
    @GetMapping("/{status}")
    public ResponseEntity<List<Task>> getTaskByStatus(@PathVariable Status status){
        return ResponseEntity.status(HttpStatus.FOUND).body(taskService.findTaskByStatus(status));
    }

    //update task by id
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskByID(@PathVariable Long id, @RequestBody Task request){
        Task updatedTask = taskService.updateTaskById(id, request);
        return ResponseEntity.status(HttpStatus.FOUND).body(updatedTask);
    }

    //delete task by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id){
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

}
