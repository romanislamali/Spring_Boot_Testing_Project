package com.roman.todo.service;

import com.roman.todo.model.Todo;

import java.util.List;

public interface TodoService {
    Todo saveTodo(Todo todo);
    List<Todo> findAllTodo();
    void deleteTodoById(Long id);
}
