package com.roman.todo.repository;

import com.roman.todo.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

        // save test for todo
        Todo savedTodo = todoRepo.save(todo);
        assertNotNull(savedTodo.getId());

        // test equation the expected and actual todo title
        assertEquals("Learn Python", savedTodo.getTitle());
    }

    @Test
    public void getAllTodos(){
        List<Todo> todoList = todoRepo.findAll();

        // test for todos are not empty
        assertTrue(!todoList.isEmpty());
    }

    @Test
    public void deleteTodo(){
        List<Todo> todoList = todoRepo.findAll();
        if(!todoList.isEmpty()){
            todoRepo.deleteById(todoList.get(0).getId());
        }

    }
}