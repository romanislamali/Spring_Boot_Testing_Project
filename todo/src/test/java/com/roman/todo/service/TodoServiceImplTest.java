package com.roman.todo.service;

import com.roman.todo.dto.ToDoResponseDto;
import com.roman.todo.dto.TodoRequestDto;
import com.roman.todo.model.Todo;
import com.roman.todo.repository.TodoRepo;
import com.roman.todo.serviceImpl.TodoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceImplTest {

    // Mock the dependency TodoRepo to avoid calling the actual database.
    @Mock
    private TodoRepo todoRepo;

    // Inject the mocked TodoRepo into the TodoServiceImpl.
    @InjectMocks
    private TodoServiceImpl todoService;

    // Initialize the Mockito annotations before each test runs.
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Opens the mocked objects for use.
    }

    @Test
    void testSaveOrUpdateTodo() {
        // Create a mock request DTO that will be passed to the service.
        TodoRequestDto requestDto = new TodoRequestDto();
        requestDto.setTitle("Test Todo");
        requestDto.setCompleted(false);

        // Define a Todo entity that will be converted from the DTO.
        Todo todo = Todo.builder()
                .title("Test Todo")
                .completed(false)
                .build();

        // Define the Todo entity returned by the mock repository after saving.
        Todo savedTodo = Todo.builder()
                .id(1L)
                .title("Test Todo")
                .completed(false)
                .build();

        // Mock the behavior of the save() method to return the savedTodo when called.
        when(todoRepo.save(todo)).thenReturn(savedTodo);

        // Act: Call the method under test.
        ToDoResponseDto responseDto = todoService.saveOrUpdateTodo(requestDto);

        // Assert: Verify the output and behavior.
        assertNotNull(responseDto);
        assertEquals(1L, responseDto.getId());
        assertEquals("Test Todo", responseDto.getTitle());
        assertFalse(responseDto.isCompleted());
        verify(todoRepo, times(1)).save(any(Todo.class));
    }

    @Test
    void testFindAllTodo() {
        // Create mock Todo entities that simulate database records.
        Todo todo1 = Todo.builder()
                .id(1L)
                .title("Test Todo 1")
                .completed(false)
                .build();

        Todo todo2 = Todo.builder()
                .id(2L)
                .title("Test Todo 2")
                .completed(true)
                .build();

        // Mock the findAll() method to return a list of mock Todo entities.
        when(todoRepo.findAll()).thenReturn(Arrays.asList(todo1, todo2));

        // Act: Call the method under test.
        List<ToDoResponseDto> responseDtos = todoService.findAllTodo();

        // Assert: Verify the output and behavior.
        assertNotNull(responseDtos);
        assertEquals(2, responseDtos.size());
        assertEquals("Test Todo 1", responseDtos.get(0).getTitle());
        assertEquals("Test Todo 2", responseDtos.get(1).getTitle());
        verify(todoRepo, times(1)).findAll();
    }

    @Test
    void testDeleteTodoById() {
        Long id = 1L;
        // Mock the deleteById() method to do nothing when called.
        doNothing().when(todoRepo).deleteById(id);

        // Act: Call the method under test.
        todoService.deleteTodoById(id);

        // Assert: Verify the behavior. Ensure deleteById() is called once
        verify(todoRepo, times(1)).deleteById(id);
    }
}