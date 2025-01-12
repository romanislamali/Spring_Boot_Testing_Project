package com.roman.todo.repository;

import com.roman.todo.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoRepoTest {
    @Autowired
    private TodoRepo todoRepo;

    @Test
    public void testSaveAndFind(){
        Todo todo = new Todo();
        todo.setTitle("Learn Python");
        todo.setCompleted(false);

        Todo savedTodo = todoRepo.save(todo);
        assertNotNull(savedTodo.getId());
        assertEquals("Learn Python", savedTodo.getTitle());
    }
}