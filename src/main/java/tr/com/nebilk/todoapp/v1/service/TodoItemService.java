package tr.com.nebilk.todoapp.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tr.com.nebilk.todoapp.v1.mapper.TodoItemMapper;
import tr.com.nebilk.todoapp.v1.model.TodoItemEntity;
import tr.com.nebilk.todoapp.v1.repository.TodoItemRepository;
import tr.com.nebilk.todoapp.v1.request.TodoItemCreateRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;

    public TodoItemEntity createTodoItem(TodoItemCreateRequest todoItemCreateRequest) {
        TodoItemEntity todoItemEntity = TodoItemMapper.INSTANCE.convert(todoItemCreateRequest);
        todoItemEntity.setCompleted(Boolean.FALSE);
        todoItemEntity.setCreatedAt(LocalDateTime.now());
        todoItemEntity.setModifiedAt(null);
        return todoItemRepository.save(todoItemEntity);
    }

    public TodoItemEntity updateTodoItem(TodoItemCreateRequest todoItemCreateRequest, Long todoItemId) {
        TodoItemEntity todo = findTodo(todoItemId);
        if(StringUtils.hasText(todoItemCreateRequest.getTitle())){
            todo.setTitle(todoItemCreateRequest.getTitle());
        }
        if(StringUtils.hasText(todoItemCreateRequest.getDescription())){
            todo.setDescription(todoItemCreateRequest.getDescription());
        }
        todo.setModifiedAt(LocalDateTime.now());
        return todoItemRepository.save(todo);
    }

    public TodoItemEntity markAsCompleted(Long todoItemId){
        return changeTodoCompletedStatus(todoItemId, Boolean.TRUE);
    }

    public TodoItemEntity markAsNotCompleted(Long todoItemId){
        return changeTodoCompletedStatus(todoItemId, Boolean.FALSE);
    }

    public TodoItemEntity findTodo(Long todoItemId){
        Optional<TodoItemEntity> byId = todoItemRepository.findById(todoItemId);
        return byId.orElseThrow(() -> new RuntimeException("Todo not found!!!"));
    }

    public void deleteTodo(Long todoItemId){
        findTodo(todoItemId);
        todoItemRepository.deleteById(todoItemId);
    }

    public List<TodoItemEntity> findAllTodos(){
        return todoItemRepository.findAll();
    }

    private TodoItemEntity changeTodoCompletedStatus(Long todoItemId, Boolean status){
        TodoItemEntity todo = findTodo(todoItemId);
        todo.setCompleted(status);
        return todoItemRepository.save(todo);
    }

}
