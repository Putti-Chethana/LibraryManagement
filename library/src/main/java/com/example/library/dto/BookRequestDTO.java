package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookRequestDTO {
    @NotBlank(message = "Book name cannot be empty")
    private String name;
    @NotBlank(message = "Publisher name cannot be empty")
    private String publisher;
    @NotBlank(message = "Author name cannot be empty")
    private String author;
}
