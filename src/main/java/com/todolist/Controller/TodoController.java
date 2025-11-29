package com.todolist.Controller;

import com.todolist.DTO.TodoRequest;
import com.todolist.DTO.TodoResponse;
import com.todolist.Entitys.TodoEntity;
import com.todolist.Service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // GET /api/todos - Lấy tất cả todos
    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) String search) {

        List<TodoResponse> todos;

        if (search != null && !search.isEmpty()) {
            todos = todoService.searchTodos(search);
        } else if (completed != null) {
            todos = todoService.getTodosByCompleted(completed);
        } else {
            todos = todoService.getAllTodos();
        }

        return ResponseEntity.ok(todos);
    }

    // GET /api/todos/{id} - Lấy todo theo ID
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodoById(@PathVariable String id) {
        TodoResponse todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    // POST /api/todos - Tạo mới todo
    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoRequest request) {
        TodoResponse createdTodo = todoService.createTodo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    // PUT /api/todos/{id} - Cập nhật todo
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(
            @PathVariable String id,
            @RequestBody TodoRequest request) {
        TodoResponse updatedTodo = todoService.updateTodo(id, request);
        return ResponseEntity.ok(updatedTodo);
    }

    // PATCH /api/todos/{id}/toggle - Đánh dấu hoàn thành/chưa hoàn thành
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TodoResponse> toggleCompleted(@PathVariable String id) {
        TodoResponse todo = todoService.toggleCompleted(id);
        return ResponseEntity.ok(todo);
    }

    // DELETE /api/todos/{id} - Xóa todo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}