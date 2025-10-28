package com.example.book.controller;


import com.example.book.dto.BookRequestDTO;
import com.example.book.dto.BookResponseDTO;
import com.example.book.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAll() {
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<BookResponseDTO> getById(@PathVariable("book_id") int id) {
        return new ResponseEntity<>(bookService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> add(@RequestBody @Valid BookRequestDTO bookDTO) {
        return new ResponseEntity<>(bookService.add(bookDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{book_id}")
    public ResponseEntity<BookResponseDTO> modify(@PathVariable("book_id") int id, @RequestBody BookRequestDTO bookRequestDTO) {
        return new ResponseEntity<>(bookService.modify(id, bookRequestDTO), HttpStatus.OK);
    }
}
