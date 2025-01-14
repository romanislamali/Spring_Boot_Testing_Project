package com.roman.todo.controller;

import com.roman.todo.dto.ToDoResponseDto;
import com.roman.todo.model.Todo;
import com.roman.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

//    @Test
//    public void testCreateTodo() throws Exception {
//        Todo todo = new Todo();
//        todo.setId(1L);
//        todo.setTitle("Learning Angular");
//        todo.setCompleted(true);
//
//        Mockito.when(todoService.saveTodo(Mockito.any(Todo.class))).thenReturn(todo);
//
//        mockMvc.perform(post("/api/v1/todo/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"title\":\"Learning Angular\",\"completed\":true}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("Learning Angular"))
//                .andExpect(jsonPath("$.completed").value(true))
//                .andExpect(jsonPath("$.id").value(1L));
//    }


    @Test
    public void testDeleteTodoById() throws Exception {
        // Arrange
        Long todoId = 1L;

        // Mock the service behavior
        Mockito.doNothing().when(todoService).deleteTodoById(todoId);

        // Act and Assert
        mockMvc.perform(delete("/api/v1/todo/delete_by_id/{id}", todoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that the service method was called
        Mockito.verify(todoService, Mockito.times(1)).deleteTodoById(todoId);
    }

}