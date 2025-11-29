package com.todolist.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoResponse {
    private String id;
    private String title;
    private String description;
    private Boolean completed;
    private Integer priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;
}
