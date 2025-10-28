package com.example.book.service;


import com.example.book.dto.BookRequestDTO;
import com.example.book.dto.BookResponseDTO;
import com.example.book.exception.BookNotFoundException;
import com.example.book.model.Book;
import com.example.book.repo.BookRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    BookRepo bookRepo;
    private final ModelMapper mapper;

    @Autowired
    public BookService(ModelMapper mapper, BookRepo bookRepo) {
        this.mapper = mapper;
        this.bookRepo = bookRepo;
    }

    public List<BookResponseDTO> getAll() {
        return bookRepo.findAll().stream().map(book -> mapper.map(book, BookResponseDTO.class)).toList();
    }

    public BookResponseDTO getById(int id) {
        Book book = bookRepo.findById(id).orElseThrow(()-> new BookNotFoundException("Book not found"));
        return mapper.map(book, BookResponseDTO.class);
    }

    public BookResponseDTO add(BookRequestDTO bookRequestDTO) {
        Book book= mapper.map(bookRequestDTO,Book.class);
        bookRepo.save(book);
        return mapper.map(book, BookResponseDTO.class);
    }

    public void delete(int id) {
        Book book = bookRepo.findById(id).orElseThrow(()-> new BookNotFoundException("No Book found"));
        bookRepo.delete(book);
    }

    public BookResponseDTO modify(int id, BookRequestDTO bookRequestDTO) {
        Book book = bookRepo.findById(id).orElseThrow(()->new BookNotFoundException("Book Not Found"));
        if(bookRequestDTO.getName()!=null && !bookRequestDTO.getName().isBlank()) {
            book.setName(bookRequestDTO.getName());
        }
        if(bookRequestDTO.getPublisher()!=null && !bookRequestDTO.getPublisher().isBlank()) {
            book.setPublisher(bookRequestDTO.getPublisher());
        }
        if(bookRequestDTO.getAuthor()!=null && !bookRequestDTO.getAuthor().isBlank()) {
            book.setAuthor(bookRequestDTO.getAuthor());
        }
        bookRepo.save(book);
        return mapper.map(book, BookResponseDTO.class);
    }

}
