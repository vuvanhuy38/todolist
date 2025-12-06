package com.todolist.Controller.resources;

import com.todolist.DTO.TodoRequest;
import com.todolist.DTO.TodoResponse;
import com.todolist.Service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoResources {

    private final TodoService todoService;

    // GET /api/todos - Lấy tất cả todos
    @GetMapping
    public ResponseEntity<Page<TodoResponse>> getAllTodos(
            Pageable pageable) {

        Page<TodoResponse> todos = todoService.getAllTodos(pageable);
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