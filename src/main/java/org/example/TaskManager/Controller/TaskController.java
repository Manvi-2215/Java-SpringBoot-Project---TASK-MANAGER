package org.example.TaskManager.Controller;

import org.example.TaskManager.Service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
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
        Task task = taskService.createTask(reuest);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    //read all taks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTask(){
        List<Task> allTasks = taskService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(allTasks);
    }

    //filter task by status
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskByID(@PathVariable Long id){
        Task taskById = taskService.findTaskById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(taskById);
    }

    //update task by id
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskByID(@PathVariable Long id, @RequestBody Task request){
        Task updatedTask = taskService.updateTaskById(id, request);
        return ResponseEntity.status(HttpStatus.FOUND).body(updatedTask);
    }

    //delete task by id
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTaskByID(@PathVariable Long id){
        taskService.deleteTaskById(id);
        return ResponseEntity<HttpStatus.OK>;
    }

}
