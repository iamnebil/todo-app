package tr.com.nebilk.todoapp.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tr.com.nebilk.todoapp.v1.model.TodoItemEntity;
import tr.com.nebilk.todoapp.v1.request.TodoItemCreateRequest;

@Mapper
public interface TodoItemMapper {
    TodoItemMapper INSTANCE = Mappers.getMapper(TodoItemMapper.class);

    TodoItemEntity convert(TodoItemCreateRequest todoItemCreateRequest);

}
