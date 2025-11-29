package com.todolist.Service.impl;

import com.todolist.DTO.TodoRequest;
import com.todolist.DTO.TodoResponse;
import com.todolist.Entitys.TodoEntity;
import com.todolist.Repository.TodoRepository;
import com.todolist.Service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<TodoResponse> getAllTodos() {
        return todoRepository.findAll().stream()
                             .map(this::convertToResponse)
                             .collect(Collectors.toList());
    }

    @Override
    public Page<TodoResponse> getAllTodos(Pageable pageable) {
        return todoRepository.findAll(pageable)
                             .map(this::convertToResponse);
    }

    @Override
    public TodoResponse getTodoById(String id) {
        TodoEntity todo = findTodoById(id);
        return convertToResponse(todo);
    }

    @Override
    public TodoResponse createTodo(TodoRequest request) {
        TodoEntity todo = new TodoEntity();
        mapRequestToEntity(request, todo);
        todo.setCreatedAt(LocalDateTime.now());
        todo.setUpdatedAt(LocalDateTime.now());

        TodoEntity savedTodo = todoRepository.save(todo);
        return convertToResponse(savedTodo);
    }

    @Override
    public TodoResponse updateTodo(String id, TodoRequest request) {
        TodoEntity todo = findTodoById(id);
        mapRequestToEntity(request, todo);
        todo.setUpdatedAt(LocalDateTime.now());

        TodoEntity updatedTodo = todoRepository.save(todo);
        return convertToResponse(updatedTodo);
    }

    @Override
    public void deleteTodo(String id) {
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("Todo không tìm thấy với id: " + id);
        }
        todoRepository.deleteById(id);
    }

    @Override
    public TodoResponse toggleCompleted(String id) {
        TodoEntity todo = findTodoById(id);
        todo.setCompleted(!todo.getCompleted());
        todo.setUpdatedAt(LocalDateTime.now());

        TodoEntity updatedTodo = todoRepository.save(todo);
        return convertToResponse(updatedTodo);
    }

    @Override
    public List<TodoResponse> searchTodos(String keyword) {
        return todoRepository.findByTitleContainingIgnoreCase(keyword).stream()
                             .map(this::convertToResponse)
                             .collect(Collectors.toList());
    }

    @Override
    public List<TodoResponse> getTodosByCompleted(Boolean completed) {
        return todoRepository.findByCompleted(completed).stream()
                             .map(this::convertToResponse)
                             .collect(Collectors.toList());
    }

    @Override
    public List<TodoResponse> getTodosByPriority(Integer priority) {
        return todoRepository.findByPriority(priority).stream()
                             .map(this::convertToResponse)
                             .collect(Collectors.toList());
    }

    @Override
    public long countTodos() {
        return todoRepository.count();
    }

    @Override
    public long countByCompleted(Boolean completed) {
        return todoRepository.countByCompleted(completed);
    }

    // ============ Private Helper Methods ============

    private TodoEntity findTodoById(String id) {
        return todoRepository.findById(id)
                             .orElseThrow(() -> new RuntimeException("Todo không tìm thấy với id: " + id));
    }

    private void mapRequestToEntity(TodoRequest request, TodoEntity entity) {
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setCompleted(request.getCompleted() != null ? request.getCompleted() : false);
        entity.setPriority(request.getPriority());
        entity.setDueDate(request.getDueDate());
    }

    private TodoResponse convertToResponse(TodoEntity entity) {
        TodoResponse response = new TodoResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setCompleted(entity.getCompleted());
        response.setPriority(entity.getPriority());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setDueDate(entity.getDueDate());
        return response;
    }
}
