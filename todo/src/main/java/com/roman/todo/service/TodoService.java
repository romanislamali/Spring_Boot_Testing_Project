package com.roman.todo.service;

import com.roman.todo.dto.ToDoResponseDto;
import com.roman.todo.dto.TodoRequestDto;

import java.util.List;

public interface TodoService {
    ToDoResponseDto saveOrUpdateTodo(TodoRequestDto todo);
    List<ToDoResponseDto> findAllTodo();
    void deleteTodoById(Long id);
}
