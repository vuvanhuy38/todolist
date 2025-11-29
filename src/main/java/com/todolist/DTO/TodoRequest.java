package com.todolist.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoRequest {
    private String title;

    private String description;

    private Boolean completed = false;

    private Integer priority;

    private LocalDateTime dueDate;
}
