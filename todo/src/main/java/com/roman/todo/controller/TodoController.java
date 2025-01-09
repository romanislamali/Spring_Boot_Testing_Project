package com.roman.todo.controller;

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
    public Todo addNewTodo(@RequestBody Todo todo){
        return todoService.saveTodo(todo);
    }

    @GetMapping("/get_all")
    public List<Todo> getAllTodos(){
        return todoService.findAllTodo();
    }

    @DeleteMapping("/delete_by_id/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodoById(id);
    }
}

