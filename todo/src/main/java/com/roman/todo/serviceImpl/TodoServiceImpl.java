package com.roman.todo.serviceImpl;

import com.roman.todo.model.Todo;
import com.roman.todo.repository.TodoRepo;
import com.roman.todo.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepo todoRepo;

    TodoServiceImpl(TodoRepo todoRepo){
        this.todoRepo = todoRepo;
    }

    @Override
    public Todo saveTodo(Todo todo) {
        return todoRepo.save(todo);
    }

    @Override
    public List<Todo> findAllTodo() {
        return todoRepo.findAll();
    }

    @Override
    public void deleteTodoById(Long id) {
        todoRepo.deleteById(id);
    }
}
