package org.example.TaskManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.scheduling.config.Task;

public class TaskRepository extends JpaRepository<Task, Long> {
}
