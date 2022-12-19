package tr.com.nebilk.todoapp.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.nebilk.todoapp.v1.model.TodoItemEntity;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItemEntity, Long> {
}
