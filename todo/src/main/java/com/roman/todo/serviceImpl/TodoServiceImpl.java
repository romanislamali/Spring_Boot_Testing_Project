package com.roman.todo.serviceImpl;

import com.roman.todo.dto.ToDoResponseDto;
import com.roman.todo.dto.TodoRequestDto;
import com.roman.todo.model.Todo;
import com.roman.todo.repository.TodoRepo;
import com.roman.todo.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepo todoRepo;

    TodoServiceImpl(TodoRepo todoRepo){
        this.todoRepo = todoRepo;
    }

    @Override
    public ToDoResponseDto saveOrUpdateTodo(TodoRequestDto todoReq) {

        Todo todo = dtoToToDo(todoReq);

        Todo toDoRes = todoRepo.save(todo);

        ToDoResponseDto responseDto = toDoToDto(toDoRes);

        return responseDto;
    }

    private Todo dtoToToDo(TodoRequestDto todoReq) {
        return Todo.builder()
                .title(todoReq.getTitle())
                .completed(todoReq.isCompleted())
                .build();
    }

    private ToDoResponseDto toDoToDto(Todo toDoRes) {
        return ToDoResponseDto.builder()
                .id(toDoRes.getId())
                .title(toDoRes.getTitle())
                .completed(toDoRes.isCompleted())
                .build();
    }

    @Override
    public List<ToDoResponseDto> findAllTodo() {

        List<Todo> todo = todoRepo.findAll();

        return toDoListRes(todo);
    }

    private List<ToDoResponseDto> toDoListRes(List<Todo> todos) {
        return todos.stream()
                .map(todo -> new ToDoResponseDto(
                        todo.getId(),
                        todo.getTitle(),
                        todo.isCompleted()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTodoById(Long id) {
        todoRepo.deleteById(id);
    }
}
