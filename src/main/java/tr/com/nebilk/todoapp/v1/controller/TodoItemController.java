package tr.com.nebilk.todoapp.v1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.nebilk.todoapp.v1.model.TodoItemEntity;
import tr.com.nebilk.todoapp.v1.request.TodoItemCreateRequest;
import tr.com.nebilk.todoapp.v1.service.TodoItemService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TodoItemController {

    private final TodoItemService todoItemService;

    @PostMapping
    public ResponseEntity<TodoItemEntity> createTodo(@RequestBody TodoItemCreateRequest todoItemCreateRequest){
        return new ResponseEntity<>(todoItemService.createTodoItem(todoItemCreateRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TodoItemEntity>> getAllTodos(){
        return ResponseEntity.ok(todoItemService.findAllTodos());
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoItemEntity> getTodo(@PathVariable Long todoId){
        return ResponseEntity.ok(todoItemService.findTodo(todoId));
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<TodoItemEntity> updateTodo(@RequestBody TodoItemCreateRequest todoItemCreateRequest,
                                                     @PathVariable Long todoId){
        return ResponseEntity.ok(todoItemService.updateTodoItem(todoItemCreateRequest, todoId));
    }

    @PatchMapping("/{todoId}/completed")
    public ResponseEntity<TodoItemEntity> markAsCompleted(@PathVariable Long todoId){
        return ResponseEntity.ok(todoItemService.markAsCompleted(todoId));
    }

    @PatchMapping("/{todoId}/not-completed")
    public ResponseEntity<TodoItemEntity> markAsNotCompleted(@PathVariable Long todoId){
        return ResponseEntity.ok(todoItemService.markAsNotCompleted(todoId));
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Long> deleteTodo(@PathVariable Long todoId){
        todoItemService.deleteTodo(todoId);
        return ResponseEntity.ok(todoId);
    }
}
