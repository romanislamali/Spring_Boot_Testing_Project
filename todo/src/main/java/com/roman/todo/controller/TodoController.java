package com.roman.todo.controller;

import com.roman.todo.model.Todo;
import com.roman.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    public Todo addNewTodo(@RequestBody Todo todo){
        return todoService.saveTodo(todo);
    }

    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.findAllTodo();
    }

    @DeleteMapping
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodoById(id);
    }
}

