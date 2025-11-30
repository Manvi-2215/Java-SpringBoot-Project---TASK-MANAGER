package org.example.TaskManager.Dto;

import java.time.LocalDateTime;

public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime dueDate;

    // constructors
    public TaskResponseDto() {}

    public TaskResponseDto(Long id, String title, String description,
                           String status, LocalDateTime dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }
}
