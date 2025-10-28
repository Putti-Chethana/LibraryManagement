package com.example.library.controller;

import com.example.library.dto.*;
import com.example.library.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("library")
@RestController
public class LibraryController {
    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("books")
    public List<BookResponseDTO> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @GetMapping("books/{book_id}")
    public BookResponseDTO getBookById(@PathVariable("book_id") int id) {
        return libraryService.getBookById(id);
    }

    @PostMapping("books")
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody @Valid BookRequestDTO bookDTO) {
        return libraryService.addBook(bookDTO);
    }

    @DeleteMapping("books/{book_id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("book_id") int id) {
        return libraryService.deleteBook(id);
    }

    @PutMapping("books/{book_id}")
    public ResponseEntity<BookResponseDTO> modifyBook(@PathVariable("book_id") int id, @RequestBody BookRequestDTO bookRequestDTO) {
        return libraryService.modifyBook(id, bookRequestDTO);
    }


    @GetMapping("users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return libraryService.getAllUsers();
    }

    @GetMapping("users/{username}")
    public ResponseEntity<UserResponseDTO> getByUserName(@PathVariable String username) {
        return libraryService.getByUserName(username);
    }

    @PostMapping("users")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userRequestDTO) {
        return libraryService.addUser(userRequestDTO);
    }

    @DeleteMapping("users/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        return libraryService.deleteUser(username);
    }

    @PutMapping("users/{username}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String username, @RequestBody UserRequestDTO userRequest) {
        return libraryService.updateUser(username, userRequest);
    }

    @PostMapping("users/{username}/books/{bookId}")
    public ResponseEntity<LibraryResponseDTO> addBookToUser(@PathVariable String username, @PathVariable int bookId) {
        return new ResponseEntity<>(libraryService.addBookToUser(username, bookId), HttpStatus.CREATED);
    }

    @DeleteMapping("users/{username}/books/{bookId}")
    public ResponseEntity<Void> deleteBookFromUser(@PathVariable String username, @PathVariable int bookId) {
        libraryService.deleteBookFromUser(username, bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
