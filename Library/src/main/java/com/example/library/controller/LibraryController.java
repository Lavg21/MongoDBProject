package com.example.library.controller;

import com.example.library.domain.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LibraryController {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
