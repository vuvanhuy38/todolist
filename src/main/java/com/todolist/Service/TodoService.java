package com.todolist.Service;

import com.todolist.DTO.TodoRequest;
import com.todolist.DTO.TodoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TodoService {

    Page<TodoResponse> getAllTodos(Pageable pageable);

    TodoResponse getTodoById(String id);

    TodoResponse createTodo(TodoRequest request);

    TodoResponse updateTodo(String id, TodoRequest request);

    void deleteTodo(String id);

    TodoResponse toggleCompleted(String id);
}
