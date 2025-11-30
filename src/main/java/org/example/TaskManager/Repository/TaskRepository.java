package org.example.TaskManager.Repository;

import org.example.TaskManager.Entity.Task;
import org.example.TaskManager.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);
}
