package com.example.library.service;

import com.example.library.client.BookClient;
import com.example.library.client.UserClient;
import com.example.library.dto.*;
import com.example.library.exception.BookAlreadyGivenToUserException;
import com.example.library.exception.BookNotAllotedToUserException;
import com.example.library.exception.BooksExceedCapacityException;
import com.example.library.model.Library;
import com.example.library.repo.LibraryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LibraryService {

    private final LibraryRepo libraryRepo;
    private final BookClient bookClient;
    private final UserClient userClient;
    private final ModelMapper mapper;

    @Autowired
    public LibraryService(LibraryRepo libraryRepo, BookClient bookClient, UserClient userClient, ModelMapper mapper) {
        this.libraryRepo = libraryRepo;
        this.bookClient = bookClient;
        this.userClient = userClient;
        this.mapper = mapper;
    }

    public List<BookResponseDTO> getAllBooks() {
        return bookClient.getAll();
    }

    public BookResponseDTO getBookById(int id) {
        return bookClient.getById(id);
    }

    public ResponseEntity<BookResponseDTO> addBook(BookRequestDTO bookDTO) {
        return bookClient.add(bookDTO);
    }

    public ResponseEntity<Void> deleteBook(int id) {
        return bookClient.delete(id);
    }

    public ResponseEntity<BookResponseDTO> modifyBook(int id, BookRequestDTO bookRequestDTO) {
        return bookClient.modify(id, bookRequestDTO);
    }

    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return userClient.getAll();
    }

    public ResponseEntity<UserResponseDTO> getByUserName(String username) {
        return userClient.getByUserName(username);
    }

    public ResponseEntity<UserResponseDTO> addUser(UserRequestDTO userRequestDTO) {
        return userClient.add(userRequestDTO);
    }

    public ResponseEntity<Void> deleteUser(String username) {
        return userClient.delete(username);
    }

    public ResponseEntity<UserResponseDTO> updateUser(String username, UserRequestDTO userRequest) {
        return userClient.update(username, userRequest);
    }

    public LibraryResponseDTO addBookToUser(String username, int bookId) {
        getByUserName(username);
        getBookById(bookId);

        Optional<Library> libraryOptional = libraryRepo.findByUsernameAndBookId(username,  bookId);

        if(libraryOptional.isPresent()) {
            throw new BookAlreadyGivenToUserException("Book is already alloted to user.");
        }

        int count = libraryRepo.countByUsername(username);

        if(count>=3) {
            throw new BooksExceedCapacityException("Books for one user must be less than or equal to 3.");
        }

        Library library = new Library();
        library.setUsername(username);
        library.setBookId(bookId);
        libraryRepo.save(library);
        return mapper.map(library, LibraryResponseDTO.class);
    }

    public void deleteBookFromUser(String username, int bookId) {
        getByUserName(username);
        getBookById(bookId);

        Library library = libraryRepo.findByUsernameAndBookId(username, bookId).orElseThrow(()-> new BookNotAllotedToUserException("Book not there in user data."));

        libraryRepo.delete(library);
    }

}
