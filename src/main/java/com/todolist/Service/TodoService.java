package com.todolist.Service;

import com.todolist.DTO.TodoRequest;
import com.todolist.DTO.TodoResponse;
import com.todolist.Entitys.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    List<TodoResponse> getAllTodos();

    Page<TodoResponse> getAllTodos(Pageable pageable);


    TodoResponse getTodoById(String id);

    TodoResponse createTodo(TodoRequest request);


    TodoResponse updateTodo(String id, TodoRequest request);

    void deleteTodo(String id);

    TodoResponse toggleCompleted(String id);

    List<TodoResponse> searchTodos(String keyword);


    List<TodoResponse> getTodosByCompleted(Boolean completed);

    List<TodoResponse> getTodosByPriority(Integer priority);

    long countTodos();

    long countByCompleted(Boolean completed);
}
