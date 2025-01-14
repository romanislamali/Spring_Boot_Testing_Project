package com.roman.todo.controller;

import com.roman.todo.dto.ToDoResponseDto;
import com.roman.todo.dto.TodoRequestDto;
import com.roman.todo.model.Todo;
import com.roman.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {

    private final TodoService todoService;

    TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @PostMapping("/add")
    public ToDoResponseDto addNewTodo(@RequestBody TodoRequestDto todo){
        return todoService.saveOrUpdateTodo(todo);
    }

    @PutMapping("/update")
    public  ToDoResponseDto updateTodo(@RequestBody TodoRequestDto todo){
        return todoService.saveOrUpdateTodo(todo);
    }

    @GetMapping("/get_all")
    public List<ToDoResponseDto> getAllTodos(){
        return todoService.findAllTodo();
    }

    @DeleteMapping("/delete_by_id/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodoById(id);
    }
}

