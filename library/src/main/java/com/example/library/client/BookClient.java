package com.example.library.client;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="book", path="/books")
public interface BookClient {

    @GetMapping
    public List<BookResponseDTO> getAll();

    @GetMapping("{book_id}")
    public BookResponseDTO getById(@PathVariable("book_id") int id);

    @PostMapping
    public ResponseEntity<BookResponseDTO> add(@RequestBody @Valid BookRequestDTO bookDTO);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id);

    @PutMapping("{book_id}")
    public ResponseEntity<BookResponseDTO> modify(@PathVariable("book_id") int id, @RequestBody BookRequestDTO bookRequestDTO);
}
