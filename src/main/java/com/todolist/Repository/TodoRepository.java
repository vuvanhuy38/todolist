package com.todolist.Repository;

import com.todolist.Entitys.TodoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<TodoEntity, String> {
    // Tìm theo trạng thái hoàn thành
    List<TodoEntity> findByCompleted(Boolean completed);

    // Tìm theo title (chứa keyword, không phân biệt hoa thường)
    List<TodoEntity> findByTitleContainingIgnoreCase(String keyword);

    // Tìm theo priority
    List<TodoEntity> findByPriority(Integer priority);

    // Tìm theo completed và sắp xếp theo createdAt giảm dần
    List<TodoEntity> findByCompletedOrderByCreatedAtDesc(Boolean completed);

    long countByCompleted(Boolean completed);
}
