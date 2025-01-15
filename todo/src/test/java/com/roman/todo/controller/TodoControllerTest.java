package com.roman.todo.controller;

import com.roman.todo.dto.ToDoResponseDto;
import com.roman.todo.dto.TodoRequestDto;
import com.roman.todo.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc; // MockMvc is used to perform HTTP requests and verify responses.

    @MockBean
    private TodoService todoService; // Mock the TodoService to avoid real service calls.

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocked objects.
    }

    @Test
    void testAddNewTodo() throws Exception {
        // Arrange: Prepare the input and expected output.
        TodoRequestDto requestDto = new TodoRequestDto();
        requestDto.setTitle("Test Todo");
        requestDto.setCompleted(false);
        ToDoResponseDto responseDto = new ToDoResponseDto(1L, "Test Todo", false);

        // Mock the service method to return the expected response.
        when(todoService.saveOrUpdateTodo(any(TodoRequestDto.class))).thenReturn(responseDto);

        // Act & Assert: Perform the POST request and verify the response.
        mockMvc.perform(post("/api/v1/todo/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Todo\",\"completed\":false}"))
                .andExpect(status().isOk()) // Verify HTTP status 200 (OK).
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Todo"))
                .andExpect(jsonPath("$.completed").value(false));

        // Ensure the service method is called once.
        verify(todoService, times(1)).saveOrUpdateTodo(any(TodoRequestDto.class));
    }

    @Test
    void testUpdateTodo() throws Exception {
        // Arrange: Prepare the input and expected output.
        TodoRequestDto requestDto = new TodoRequestDto();
        requestDto.setTitle("Updated Todo");
        requestDto.setCompleted(true);
        ToDoResponseDto responseDto = new ToDoResponseDto(1L, "Updated Todo", true);

        // Mock the service method to return the expected response.
        when(todoService.saveOrUpdateTodo(any(TodoRequestDto.class))).thenReturn(responseDto);

        // Act & Assert: Perform the PUT request and verify the response.
        mockMvc.perform(put("/api/v1/todo/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Todo\",\"completed\":true}"))
                .andExpect(status().isOk()) // Verify HTTP status 200 (OK).
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Updated Todo"))
                .andExpect(jsonPath("$.completed").value(true));

        // Ensure the service method is called once.
        verify(todoService, times(1)).saveOrUpdateTodo(any(TodoRequestDto.class));
    }

    @Test
    void testGetAllTodos() throws Exception {
        // Arrange: Prepare the mock data.
        List<ToDoResponseDto> responseDtos = Arrays.asList(
                new ToDoResponseDto(1L, "Todo 1", false),
                new ToDoResponseDto(2L, "Todo 2", true)
        );

        // Mock the service method to return the mock data.
        when(todoService.findAllTodo()).thenReturn(responseDtos);

        // Act & Assert: Perform the GET request and verify the response.
        mockMvc.perform(get("/api/v1/todo/get_all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Verify HTTP status 200 (OK).
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Todo 1"))
                .andExpect(jsonPath("$[0].completed").value(false))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].title").value("Todo 2"))
                .andExpect(jsonPath("$[1].completed").value(true));

        // Ensure the service method is called once.
        verify(todoService, times(1)).findAllTodo();
    }

    @Test
    void testDeleteTodo() throws Exception {
        // Arrange: Mock the delete method to do nothing.
        doNothing().when(todoService).deleteTodoById(1L);

        // Act & Assert: Perform the DELETE request and verify the response.
        mockMvc.perform(delete("/api/v1/todo/delete_by_id/1"))
                .andExpect(status().isOk()); // Verify HTTP status 200 (OK).

        // Ensure the service method is called once.
        verify(todoService, times(1)).deleteTodoById(1L);
    }
}