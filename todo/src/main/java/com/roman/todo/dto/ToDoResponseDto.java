package com.roman.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToDoResponseDto {
    private Long id;
    private String title;
    private boolean completed;
}
