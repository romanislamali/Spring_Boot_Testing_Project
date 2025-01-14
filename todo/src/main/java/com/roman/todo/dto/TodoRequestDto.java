package com.roman.todo.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class TodoRequestDto {
    private Long id;
    private String title;
    private boolean completed;
}
