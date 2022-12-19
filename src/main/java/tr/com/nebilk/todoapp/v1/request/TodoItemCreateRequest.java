package tr.com.nebilk.todoapp.v1.request;

import lombok.Data;

@Data
public class TodoItemCreateRequest {
    private String title;
    private String description;
}
