package com.example.library.dto;

import lombok.Data;

@Data
public class BookResponseDTO {
    private int id;
    private String name;
    private String publisher;
    private String author;
}
