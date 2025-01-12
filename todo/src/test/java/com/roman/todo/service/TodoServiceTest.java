package com.roman.todo.service;

import com.roman.todo.model.Todo;
import com.roman.todo.repository.TodoRepo;
import com.roman.todo.serviceImpl.TodoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
    @Mock
    private TodoRepo todoRepo;

    @InjectMocks
    private TodoServiceImpl todoServiceimpl;

    @Test
    public void testSaveTodo(){
        Todo todo = new Todo();
        todo.setTitle("Learn Spring Boot");
        todo.setCompleted(true);

        Mockito.when(todoRepo.save(todo)).thenReturn(todo);
        Todo savedTodo = todoServiceimpl.saveTodo(todo);
        assertEquals("Learn Spring Boot", savedTodo.getTitle());
    }

}